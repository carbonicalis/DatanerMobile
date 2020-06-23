package com.example.datanermobile.screens.workplace

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.datanermobile.databinding.ListItemWorkplaceBinding
import com.example.datanermobile.screens.workplace.network.Workplace


class WorkplaceAdapter(
    val clickListener: WorkplaceListener,
    private val workplaceDevicesListener: WorkplaceDevicesListener
) : ListAdapter<Workplace, WorkplaceAdapter.ViewHolder>(WorkplaceDiffCallback()) {


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.bind(item, clickListener, workplaceDevicesListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: ListItemWorkplaceBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            item: Workplace,
            clickListener: WorkplaceListener,
            workplaceDevicesListener: WorkplaceDevicesListener
        ) {
            binding.workplace = item
            binding.clickListener = clickListener
            binding.workplaceDevicesListener = workplaceDevicesListener
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

class WorkplaceListener(
    val clickListner: (workplaceId: Int, workplaceNumber: Int, workplaceName: String, floorId: Int) -> Unit
) {
    fun onClickUpdate(workplace: Workplace) =
        clickListner(
            workplace.workplaceId,
            workplace.number,
            workplace.description,
            workplace.floorId
        )
}

class WorkplaceDevicesListener(
    val clickListener: (workplaceId: Int) -> Unit
) {
    fun onClick(workplace: Workplace) = clickListener(workplace.workplaceId)
}
