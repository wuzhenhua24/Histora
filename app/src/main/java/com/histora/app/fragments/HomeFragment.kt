package com.histora.app.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.histora.app.EventDetailActivity
import com.histora.app.adapters.EventAdapter
import com.histora.app.data.SampleData
import com.histora.app.databinding.FragmentHomeBinding
import com.histora.app.models.HistoricalEvent

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var eventAdapter: EventAdapter
    private var allEvents: List<HistoricalEvent> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        loadEvents()
        setupButtons()
    }

    private fun setupRecyclerView() {
        eventAdapter = EventAdapter { event ->
            openEventDetail(event)
        }

        binding.eventsRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = eventAdapter
        }
    }

    private fun loadEvents() {
        allEvents = SampleData.getRecommendedEvents()
        eventAdapter.submitList(allEvents)
    }

    private fun setupButtons() {
        binding.startSimulationButton.setOnClickListener {
            // Open first event for simulation
            if (allEvents.isNotEmpty()) {
                openEventDetail(allEvents[0])
            }
        }
    }

    private fun openEventDetail(event: HistoricalEvent) {
        val intent = Intent(requireContext(), EventDetailActivity::class.java).apply {
            putExtra("event", event)
        }
        startActivity(intent)
    }

    fun filterEvents(query: String) {
        val filteredList = if (query.isEmpty()) {
            allEvents
        } else {
            allEvents.filter { event ->
                event.title.contains(query, ignoreCase = true) ||
                event.description.contains(query, ignoreCase = true)
            }
        }
        eventAdapter.submitList(filteredList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
