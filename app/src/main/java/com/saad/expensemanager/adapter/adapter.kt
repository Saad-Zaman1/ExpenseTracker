package com.saad.expensemanager.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.saad.expensemanager.R
import com.saad.expensemanager.room.ExpenseEntity


class adapter : ListAdapter<ExpenseEntity, adapter.MyViewHolder>(DiffUtil()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_recycler_single_view, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title = view.findViewById<TextView>(R.id.titleTextView)
        val time = view.findViewById<TextView>(R.id.timeTextView)
        val date = view.findViewById<TextView>(R.id.dateTextView)
        val amount = view.findViewById<TextView>(R.id.moneyTextView)

        fun bind(item: ExpenseEntity) {
            if (item.isIncome == "Expense") {
                amount.setTextColor(Color.parseColor("#FF0000"))
            } else {
                amount.setTextColor(Color.parseColor("#008000"))
            }
            title.text = item.title
            time.text = item.time
            date.text = item.date
            amount.text = item.amount
        }

    }

    class DiffUtil : androidx.recyclerview.widget.DiffUtil.ItemCallback<ExpenseEntity>() {
        override fun areItemsTheSame(oldItem: ExpenseEntity, newItem: ExpenseEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ExpenseEntity, newItem: ExpenseEntity): Boolean {
            return oldItem == newItem
        }

    }

}