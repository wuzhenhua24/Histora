package com.histora.app

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.histora.app.adapters.ModifiableFactorAdapter
import com.histora.app.adapters.ScenarioAdapter
import com.histora.app.data.SampleData
import com.histora.app.databinding.ActivitySimulationBinding
import com.histora.app.models.HistoricalEvent
import com.histora.app.models.Simulation
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SimulationActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySimulationBinding
    private lateinit var event: HistoricalEvent
    private lateinit var factorAdapter: ModifiableFactorAdapter
    private var currentSimulation: Simulation? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySimulationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        event = intent.getSerializableExtra("event") as? HistoricalEvent
            ?: run {
                finish()
                return
            }

        setupToolbar()
        displayEvent()
        setupFactors()
        setupButtons()
    }

    private fun setupToolbar() {
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun displayEvent() {
        binding.eventTitle.text = event.title
    }

    private fun setupFactors() {
        factorAdapter = ModifiableFactorAdapter(event.modifiableFactors)
        binding.factorsRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@SimulationActivity)
            adapter = factorAdapter
        }
    }

    private fun setupButtons() {
        binding.startSimulationButton.setOnClickListener {
            runSimulation()
        }

        binding.exportReportButton.setOnClickListener {
            exportReport()
        }

        binding.shareButton.setOnClickListener {
            shareSimulation()
        }
    }

    private fun runSimulation() {
        val selectedModifications = factorAdapter.getSelectedModifications()

        if (selectedModifications.isEmpty()) {
            Toast.makeText(this, "请至少选择一个要修改的因素", Toast.LENGTH_SHORT).show()
            return
        }

        // Show loading
        binding.loadingOverlay.visibility = View.VISIBLE

        // Simulate API call delay
        lifecycleScope.launch {
            delay(2000)

            // Generate simulation results
            currentSimulation = SampleData.getSampleSimulation(event.id)

            // Hide loading and show results
            binding.loadingOverlay.visibility = View.GONE
            displayResults()
        }
    }

    private fun displayResults() {
        currentSimulation?.let { simulation ->
            binding.resultsSection.visibility = View.VISIBLE

            val scenarioAdapter = ScenarioAdapter(simulation.scenarios)
            binding.scenariosRecyclerView.apply {
                layoutManager = LinearLayoutManager(this@SimulationActivity)
                adapter = scenarioAdapter
            }

            // Scroll to results
            binding.root.post {
                val scrollView = binding.root.getChildAt(1) as androidx.core.widget.NestedScrollView
                scrollView.smoothScrollTo(0, binding.resultsSection.top)
            }
        }
    }

    private fun exportReport() {
        currentSimulation?.let {
            Toast.makeText(this, "导出功能开发中...", Toast.LENGTH_SHORT).show()
            // TODO: Implement PDF export
        }
    }

    private fun shareSimulation() {
        currentSimulation?.let {
            Toast.makeText(this, "分享功能开发中...", Toast.LENGTH_SHORT).show()
            // TODO: Implement share functionality
        }
    }
}
