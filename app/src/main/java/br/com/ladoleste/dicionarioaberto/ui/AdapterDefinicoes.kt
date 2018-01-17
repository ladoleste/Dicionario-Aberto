package br.com.ladoleste.dicionarioaberto.ui

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import br.com.ladoleste.dicionarioaberto.R
import br.com.ladoleste.dicionarioaberto.app.inflate
import br.com.ladoleste.dicionarioaberto.app.toHtml
import br.com.ladoleste.dicionarioaberto.dto.Sense
import kotlinx.android.synthetic.main.item_definicao.view.*

class AdapterDefinicoes(private val definicoes: List<Sense>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount() = definicoes.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent.inflate(R.layout.item_definicao))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val def = definicoes[position].def
                .replace("<br/>", "\n")
                .replace("_.+?_".toRegex(), "<i>$0<</i>")
                .replace("_", "")

        holder.itemView.tv_def.text = String.format("%s. %s", position + 1, def).toHtml()
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)
}