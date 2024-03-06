package com.caglaakgul.ecabsandroidchallenge.ui.list

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.caglaakgul.ecabsandroidchallenge.R
import com.caglaakgul.ecabsandroidchallenge.base.BaseFragment
import com.caglaakgul.ecabsandroidchallenge.databinding.FragmentEventListBinding
import com.caglaakgul.ecabsandroidchallenge.extension.navigateSafe
import com.caglaakgul.ecabsandroidchallenge.extension.observe
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EventListFragment : BaseFragment<FragmentEventListBinding, EventListViewModel>() {
    override fun getLayoutRes(): Int = R.layout.fragment_event_list

    override fun getViewModelClass() = EventListViewModel::class.java

    private val eventListAdapter by lazy {
        EventListAdapter()
    }

    companion object{
        const val EVENT_REQUEST_DELAY = 10000L
    }

    private var repeatingJob: Job? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapter()
        startRepeatingRequest()
    }

    private fun setAdapter() {
        binding.rvEventList.adapter = eventListAdapter

        eventListAdapter.setItemClickListener {
            navigateSafe(EventListFragmentDirections.actionFirstFragmentToSecondFragment(it))
        }
    }

    override fun initListeners() {

    }

    override fun initObservers() {
        observe(viewModel.eventListLiveData) {
            eventListAdapter.submitList(it + eventListAdapter.currentList)
        }
    }

    private fun startRepeatingRequest() {
        repeatingJob = viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                while (true) {
                    delay(EVENT_REQUEST_DELAY)
                    viewModel.getEventList()
                }
            }
        }
    }

    override fun onDestroyView() {
        repeatingJob?.cancel()
        super.onDestroyView()
    }

    override fun onPause() {
        repeatingJob?.cancel()
        super.onPause()
    }
}