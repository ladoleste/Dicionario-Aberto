package br.com.ladoleste.dicionarioaberto.ui

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import br.com.ladoleste.dicionarioaberto.R
import br.com.ladoleste.dicionarioaberto.app.inflate
import br.com.ladoleste.dicionarioaberto.app.toHtml
import br.com.ladoleste.dicionarioaberto.dto.SuperEntry
import kotlinx.android.synthetic.main.item_definicao.view.*

class AdapterDefinicoes(private val definicoes: List<SuperEntry>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount() = definicoes.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent.inflate(R.layout.item_definicao))

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val senses = definicoes[position].entry.sense

        var x = ""
        senses.forEachIndexed({ i, s ->
            x += String.format("%s. %s<br/><br/>", (i + 1), s.def)
        })

        x = x
                .replace("_.+?_".toRegex(), "<i>$0</i>")
                .replace("_", "")
                .dropLast(10)

        holder.itemView.tv_def.text = x.toHtml()
        holder.itemView.tv_title.text = definicoes[position].entry.form?.orth + temp(position)
    }

    private fun temp(position: Int): String {
        return when (position) {
            0 -> "¹"
            1 -> "²"
            2 -> "³"
            else -> " ($position)"
        }
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)
}