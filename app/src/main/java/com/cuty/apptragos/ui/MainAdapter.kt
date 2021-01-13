package com.cuty.apptragos.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cuty.apptragos.R
import com.cuty.apptragos.base.BaseViewHolder
import com.cuty.apptragos.data.data.Drink
import com.cuty.apptragos.data.data.DrinkList
import kotlinx.android.synthetic.main.tragos_row.view.*

class MainAdapter(
    private val context: Context, private val tragosList: List<Drink>,
    private val itemClickListener: OnTragoClickListener
) :
    RecyclerView.Adapter<BaseViewHolder<*>>() {
    interface OnTragoClickListener {
        fun onTragoClick(drink: Drink)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return MainViewHolder(
            LayoutInflater.from(context).inflate(R.layout.tragos_row, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return tragosList.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is MainViewHolder -> holder.bind(tragosList[position], position)
        }
    }

    inner class MainViewHolder(itemView: View) : BaseViewHolder<Drink>(itemView) {
        override fun bind(item: Drink, position: Int) {
            Glide.with(context).load(item.imagen).into(itemView.img_trago)
            itemView.txt_titulo.text = item.nombre
            itemView.txt_descrpcion.text = item.descripcion
            itemView.setOnClickListener {
                itemClickListener.onTragoClick(item)
            }
        }
    }
}