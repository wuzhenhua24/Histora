package com.histora.app.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import com.histora.app.databinding.ItemModifiableFactorBinding
import com.histora.app.models.ModifiableFactor
import com.histora.app.models.Modification

class ModifiableFactorAdapter(
    private val factors: List<ModifiableFactor>
) : RecyclerView.Adapter<ModifiableFactorAdapter.FactorViewHolder>() {

    private val selectedValues = mutableMapOf<String, String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactorViewHolder {
        val binding = ItemModifiableFactorBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FactorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FactorViewHolder, position: Int) {
        holder.bind(factors[position])
    }

    override fun getItemCount() = factors.size

    fun getSelectedModifications(): List<Modification> {
        return selectedValues.map { (factorId, newValue) ->
            val factor = factors.find { it.id == factorId }!!
            Modification(
                factorId = factorId,
                factorTitle = factor.title,
                originalValue = factor.originalValue,
                newValue = newValue,
                description = factor.description
            )
        }
    }

    inner class FactorViewHolder(
        private val binding: ItemModifiableFactorBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(factor: ModifiableFactor) {
            binding.factorTitle.text = factor.title
            binding.factorDescription.text = factor.description
            binding.originalValue.text = "原始值: ${factor.originalValue}"

            // Setup spinner with alternative values
            val options = listOf("保持原值") + factor.alternativeValues.map { it.value }
            val adapter = ArrayAdapter(
                binding.root.context,
                android.R.layout.simple_spinner_item,
                options
            )
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.valueSpinner.adapter = adapter

            binding.valueSpinner.setOnItemSelectedListener(object : android.widget.AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: android.widget.AdapterView<*>?,
                    view: android.view.View?,
                    position: Int,
                    id: Long
                ) {
                    if (position > 0) {
                        selectedValues[factor.id] = factor.alternativeValues[position - 1].value
                    } else {
                        selectedValues.remove(factor.id)
                    }
                }

                override fun onNothingSelected(parent: android.widget.AdapterView<*>?) {}
            })
        }
    }
}
