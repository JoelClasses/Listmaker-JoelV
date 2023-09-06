package com.raywenderlich.listmaker

import androidx.recyclerview.widget.RecyclerView
import com.raywenderlich.listmaker.databinding.ListItemViewHolderBinding
// ViewHolder for displaying individual tasks in a list.
class ListItemViewHolder(val binding: ListItemViewHolderBinding)
    : RecyclerView.ViewHolder(binding.root)