package com.histora.app

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.histora.app.adapters.KeyFigureAdapter
import com.histora.app.databinding.ActivityEventDetailBinding
import com.histora.app.models.HistoricalEvent
import com.histora.app.utils.getSerializableExtraCompat

class EventDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEventDetailBinding
    private lateinit var event: HistoricalEvent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEventDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        event = intent.getSerializableExtraCompat<HistoricalEvent>("event")
            ?: run {
                finish()
                return
            }

        setupToolbar()
        displayEventDetails()
        setupKeyFigures()
        setupCausalityGraph()
        setupModifyButton()
    }

    private fun setupToolbar() {
        binding.toolbar.title = event.title
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun displayEventDetails() {
        binding.eventTitle.text = event.title
        binding.eventDate.text = event.date
        binding.eventCategory.text = event.category
        binding.eventBackground.text = event.background
    }

    private fun setupKeyFigures() {
        if (event.keyFigures.isNotEmpty()) {
            val adapter = KeyFigureAdapter(event.keyFigures)
            binding.keyFiguresRecyclerView.apply {
                layoutManager = LinearLayoutManager(this@EventDetailActivity)
                this.adapter = adapter
            }
        } else {
            binding.keyFiguresCard.visibility = android.view.View.GONE
        }
    }

    private fun setupCausalityGraph() {
        if (event.causalNodes.isNotEmpty()) {
            binding.causalityGraphView.setNodes(event.causalNodes)
        } else {
            binding.causalityCard.visibility = android.view.View.GONE
        }
    }

    private fun setupModifyButton() {
        binding.modifyHistoryButton.setOnClickListener {
            val intent = Intent(this, SimulationActivity::class.java).apply {
                putExtra("event", event)
            }
            startActivity(intent)
        }
    }
}
