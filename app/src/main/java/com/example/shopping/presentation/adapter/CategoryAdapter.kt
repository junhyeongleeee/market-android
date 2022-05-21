package com.example.shopping.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.shopping.model.category.CategoryModel
import kotlin.study.shopping.R
import kotlin.study.shopping.databinding.ItemCategoryBinding

class CategoryAdapter : ListAdapter<CategoryModel, CategoryAdapter.ViewHolder>(CategoryDiffUtil) {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bindData(item : CategoryModel){
            val textView = itemView.findViewById<TextView>(R.id.name)

            textView.text = item.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCategoryBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }

    object CategoryDiffUtil : DiffUtil.ItemCallback<CategoryModel>() {

        override fun areItemsTheSame(oldItem: CategoryModel, newItem: CategoryModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: CategoryModel, newItem: CategoryModel): Boolean {
            return oldItem.id == newItem.id
        }
    }
}