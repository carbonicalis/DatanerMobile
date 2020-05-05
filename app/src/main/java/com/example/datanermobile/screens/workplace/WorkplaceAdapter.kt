package com.example.datanermobile.screens.workplace

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.datanermobile.databinding.ListItemWorkplaceBinding
import com.example.datanermobile.screens.workplace.database.Workplace


class WorkplaceAdapter(val clickListener: WorkplaceListener) :
    ListAdapter<Workplace, WorkplaceAdapter.ViewHolder>(WorkplaceDiffCallback()) {


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.bind(item, clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: ListItemWorkplaceBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            item: Workplace,
            clickListener: WorkplaceListener
        ) {
            binding.workplace = item
            binding.clickListener = clickListener
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

class WorkplaceListener(val clickListner: (workplaceId: Int, workplaceNumber: Int,workplaceName: String ) -> Unit) {
    fun onClickUpdate(workplace: Workplace) = clickListner(workplace.workplaceId, workplace.number, workplace.description)
}