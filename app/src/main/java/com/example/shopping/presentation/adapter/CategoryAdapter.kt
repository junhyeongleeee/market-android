package com.example.shopping.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.shopping.data.entity.category.CategoryEntity
import kotlin.study.shopping.R
import kotlin.study.shopping.databinding.ItemCategoryBinding

class CategoryAdapter : ListAdapter<CategoryEntity, CategoryAdapter.ViewHolder>(CategoryDiffUtil) {



    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(item : CategoryEntity){

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCategoryBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    object CategoryDiffUtil : DiffUtil.ItemCallback<CategoryEntity>() {

        override fun areItemsTheSame(oldItem: CategoryEntity, newItem: CategoryEntity): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: CategoryEntity, newItem: CategoryEntity): Boolean {
            return oldItem.id == newItem.id
        }
    }
}