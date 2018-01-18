package br.com.ladoleste.dicionarioaberto.ui

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
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)
}