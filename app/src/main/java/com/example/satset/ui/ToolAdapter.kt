package com.example.satset.ui

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.satset.data.AiTool
import com.example.satset.databinding.ItemToolBinding

class ToolAdapter(
    private val ratingText: (AiTool) -> String,
    private val onClick: (AiTool) -> Unit
) : ListAdapter<AiTool, ToolAdapter.VH>(Diff) {

    class VH(val b: ItemToolBinding) : RecyclerView.ViewHolder(b.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VH(ItemToolBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: VH, position: Int) {
        val t = getItem(position)
        with(holder.b) {
            tvAvatar.text = t.name.take(1).uppercase()
            tvAvatar.backgroundTintList = ColorStateList.valueOf(Color.parseColor(t.color))
            tvName.text = t.name
            tvCategory.text = t.category
            tvDesc.text = t.shortDesc
            tvRating.text = ratingText(t)
            root.setOnClickListener { onClick(t) }
        }
    }

    object Diff : DiffUtil.ItemCallback<AiTool>() {
        override fun areItemsTheSame(a: AiTool, b: AiTool) = a.id == b.id
        override fun areContentsTheSame(a: AiTool, b: AiTool) = a == b
    }
}