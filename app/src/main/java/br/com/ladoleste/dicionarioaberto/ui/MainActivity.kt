package br.com.ladoleste.dicionarioaberto.ui

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import br.com.ladoleste.dicionarioaberto.R
import br.com.ladoleste.dicionarioaberto.dto.Definicoes
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.util.concurrent.TimeUnit


class MainActivity : BaseActivity() {

    private lateinit var viewModel: MainViewModel

    private val rlDefinicoes by lazy {
        rl_definicoes.setHasFixedSize(true)
        rl_definicoes.layoutManager = LinearLayoutManager(this)
        rl_definicoes
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        val adapter = ArrayAdapter<String>(this, android.R.layout.select_dialog_item, emptyList())

        et_entrada.threshold = 1
        et_entrada.setAdapter(adapter)
        et_entrada.setTextColor(Color.BLUE)
        et_entrada.onItemClickListener = AdapterView.OnItemClickListener { _, _, _, _ ->
            bt_definir.performClick()
        }

        et_entrada.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

                if (s.isNullOrBlank())
                    return

                cDispose.add(viewModel.completarPalavra(s.toString())
                        .debounce(1, TimeUnit.SECONDS)
                        .subscribe({
                            adapter.clear()
                            adapter.addAll(it.list)
                            adapter.filter.filter(et_entrada.text, et_entrada)
                        }, {
                            Snackbar.make(root_view, it.message
                                    ?: getString(R.string.unknown_error), Snackbar.LENGTH_SHORT).show()
                        })
                )

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        et_entrada.setOnEditorActionListener { _, ei, _ ->
            if (ei == EditorInfo.IME_ACTION_DONE) {
                bt_definir.performClick()
            }
            false
        }

        bt_definir.setOnClickListener {

            if (et_entrada.text.isNotBlank()) {

                pb_loading.visibility = View.VISIBLE

                cDispose.add(viewModel.obterDefinicao(et_entrada.text.toString()).subscribe({
                    sucess(it)
                }, {
                    error1(it)
                }))

            } else {
                et_entrada.error = "Informe uma palavra"
            }
        }

        bt_limpar.setOnClickListener {
            et_entrada.setText("")
            rlDefinicoes.adapter = AdapterDefinicoes(emptyList())
        }

        //room()

    }

    private fun error1(it: Throwable) {
        pb_loading.visibility = View.GONE

        when (it) {
            is HttpException -> {

                val mensagem = when (it.code()) {
                    404 -> "Palavra não encontraada"
                    else -> getString(R.string.unknown_error)
                }

                Snackbar.make(root_view, it.message ?: mensagem, Snackbar.LENGTH_SHORT).show()
            }
            is SocketTimeoutException -> Snackbar.make(root_view, it.message
                    ?: "Servidor ocupado, tente novamente em instantes", Snackbar.LENGTH_SHORT).show()
            else -> Snackbar.make(root_view, it.message ?: it.message
            ?: getString(R.string.unknown_error), Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun sucess(it: Definicoes) {
        pb_loading.visibility = View.GONE
        rlDefinicoes.adapter = AdapterDefinicoes(it.superEntry)
        pb_loading.visibility = View.GONE
        et_entrada.clearFocus()
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(et_entrada.windowToken, 0)
    }

//    private fun room() {
//
//        val db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database").allowMainThreadQueries().build()
//
//        db.testeDao().insertAll(Teste(37, "Anderson"))
//        db.testeDao().insertAll(Teste(1, "Miguel"))
//
//        db.testeDao().getAll().forEach {
//            Timber.d(it.toString())
//        }
//
//    }
}