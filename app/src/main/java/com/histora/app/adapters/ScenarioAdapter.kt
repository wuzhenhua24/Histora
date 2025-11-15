package com.histora.app.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.histora.app.databinding.ItemScenarioBinding
import com.histora.app.models.Scenario

class ScenarioAdapter(
    private val scenarios: List<Scenario>
) : RecyclerView.Adapter<ScenarioAdapter.ScenarioViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScenarioViewHolder {
        val binding = ItemScenarioBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ScenarioViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ScenarioViewHolder, position: Int) {
        holder.bind(scenarios[position])
    }

    override fun getItemCount() = scenarios.size

    class ScenarioViewHolder(
        private val binding: ItemScenarioBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(scenario: Scenario) {
            binding.scenarioTitle.text = scenario.title
            binding.scenarioDescription.text = scenario.description

            scenario.probability?.let {
                binding.probability.text = "可能性: ${(it * 100).toInt()}%"
                binding.probabilityBar.progress = (it * 100).toInt()
            }

            // Display key changes
            val changesText = scenario.keyChanges.joinToString("\n") { "• $it" }
            binding.keyChanges.text = changesText

            // Display timeline events
            val timelineText = scenario.timeline.events.joinToString("\n") { event ->
                "${event.date}: ${event.title}"
            }
            binding.timelineEvents.text = timelineText
        }
    }
}
