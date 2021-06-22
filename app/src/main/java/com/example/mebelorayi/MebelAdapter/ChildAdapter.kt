package com.example.mebelorayi.MebelAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mebelorayi.R
import com.example.mebelorayi.data.ModelMebel.MagazinMebeli
import com.example.mebelorayi.data.ModelMebel.MebelClickListener
import kotlinx.android.synthetic.main.rv_order_item.view.*

class ChildAdapter(private val listener: MebelClickListener, private val remover: RemoverFromOrder) :
    RecyclerView.Adapter<ChildAdapter.ChildViewHolder>() {

    var item: MutableList<MagazinMebeli> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    fun removeAt(position: Int) {
        item.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, item.size)
    }


    inner class ChildViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun popModOrder(menu: MagazinMebeli, position: Int) {
            itemView.tvItemOrderName.text = menu.nameRus
            itemView.tvCurier.text = "Стоймость : ${menu.cost} сум"
            if (menu.quantity == null) {
                menu.quantity = 1.toString()
                itemView.tvQuantity.text = "Количество : ${menu.quantity}"
            } else {
                itemView.tvQuantity.text = "Количество : ${menu.quantity}"
            }
            val imageResName = "pictures${menu.id}"
            Glide
                .with(itemView)
                .load(
                    itemView.context.resources.getIdentifier(
                        imageResName,
                        "drawable",
                        itemView.context.packageName
                    )
                )
                .into(itemView.ivItemOrderPhoto)
            itemView.setOnClickListener {
                listener.onItemMenuClickListener(menu.id)
            }
            itemView.btnReOrder.setOnClickListener {
                remover.RemoverFromOrder(menu.id)
                removeAt(position)
            }


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildViewHolder {
        val itemView: View =
            LayoutInflater.from(parent.context).inflate(R.layout.rv_order_item, parent, false)
        return ChildViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return item.size
    }

    override fun onBindViewHolder(holder: ChildViewHolder, position: Int) {
        holder.popModOrder(item[position], position)
    }


}