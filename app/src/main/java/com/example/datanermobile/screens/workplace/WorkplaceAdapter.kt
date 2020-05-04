package com.example.datanermobile.screens.workplace

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.datanermobile.R
import com.example.datanermobile.databinding.ListItemWorkplaceBinding
import com.example.datanermobile.screens.workplace.database.Workplace


class WorkplaceAdapter :
    ListAdapter<Workplace, WorkplaceAdapter.ViewHolder>(WorkplaceDiffCallback()) {


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: ListItemWorkplaceBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Workplace) {
            binding.workplace = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemWorkplaceBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }

}

class WorkplaceDiffCallback : DiffUtil.ItemCallback<Workplace>() {
    override fun areItemsTheSame(oldItem: Workplace, newItem: Workplace): Boolean {
        return oldItem.workplaceId == newItem.workplaceId
    }

    override fun areContentsTheSame(oldItem: Workplace, newItem: Workplace): Boolean {
        return oldItem == newItem
    }
}