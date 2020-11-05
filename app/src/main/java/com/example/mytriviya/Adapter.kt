package com.example.mytriviya

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mytriviya.model.ModelClass
import kotlinx.android.synthetic.main.fragment_preview.view.*
import kotlinx.android.synthetic.main.headline_card_layout.view.*

class Adapter(): RecyclerView.Adapter<Adapter.ViewHolder>() {
    private val differCallback = object : DiffUtil.ItemCallback<ModelClass>() {
        override fun areItemsTheSame(oldItem: ModelClass, newItem: ModelClass): Boolean {
            return oldItem.pId == newItem.pId
        }

        override fun areContentsTheSame(oldItem: ModelClass, newItem: ModelClass): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.headline_card_layout,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model =differ.currentList[position]

         holder.itemView.apply {
             date.text="Game ${position+1} : ${model.time}"
             name.text=model.name
             q1.text=model.questions[0]
              a1.text=model.answers[0]
             q2.text=model.questions[1]
             a2.text=model.answers[1]
         }
    }

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)

}