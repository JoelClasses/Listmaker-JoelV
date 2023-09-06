package com.raywenderlich.listmaker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.raywenderlich.listmaker.databinding.ListSelectionViewHolderBinding
// Adapter for managing and displaying lists in the RecyclerView.
class ListSelectionRecyclerViewAdapter(val lists: MutableList<TaskList>, val clickListener: ListSelectionRecyclerViewClickListener) :
    RecyclerView.Adapter<ListSelectionViewHolder>() {

    // Interface for handling list item clicks.
    interface ListSelectionRecyclerViewClickListener {
        fun listItemClicked(list: TaskList)
    }

    // Create a new ViewHolder for the RecyclerView.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListSelectionViewHolder {
        val binding = ListSelectionViewHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListSelectionViewHolder(binding)
    }

    // Return the number of lists.
    override fun getItemCount(): Int {
        return lists.size
    }

    // Bind data to the ViewHolder.
    override fun onBindViewHolder(holder: ListSelectionViewHolder, position: Int) {
        holder.binding.itemNumber.text = (position + 1).toString()
        holder.binding.itemString.text = lists[position].name
        holder.itemView.setOnClickListener {
            clickListener.listItemClicked(lists[position])
        }
    }

    // Notify the adapter when a new list is added.
    fun listsUpdated() {
        notifyItemInserted(lists.size-1)
    }
}
