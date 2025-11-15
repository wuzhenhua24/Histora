package com.histora.app.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.histora.app.databinding.ItemKeyFigureBinding
import com.histora.app.models.KeyFigure

class KeyFigureAdapter(
    private val figures: List<KeyFigure>
) : RecyclerView.Adapter<KeyFigureAdapter.KeyFigureViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KeyFigureViewHolder {
        val binding = ItemKeyFigureBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return KeyFigureViewHolder(binding)
    }

    override fun onBindViewHolder(holder: KeyFigureViewHolder, position: Int) {
        holder.bind(figures[position])
    }

    override fun getItemCount() = figures.size

    class KeyFigureViewHolder(
        private val binding: ItemKeyFigureBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(figure: KeyFigure) {
            binding.figureName.text = figure.name
            binding.figureRole.text = figure.role
            binding.figureDescription.text = figure.description
        }
    }
}
