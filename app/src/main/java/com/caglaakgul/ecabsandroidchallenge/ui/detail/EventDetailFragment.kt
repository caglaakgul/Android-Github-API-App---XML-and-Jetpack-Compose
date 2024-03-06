package com.caglaakgul.ecabsandroidchallenge.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.caglaakgul.ecabsandroidchallenge.ui.detail.views.EventDetailScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EventDetailFragment : Fragment() {
    private val viewModel: EventDetailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: EventDetailFragmentArgs by navArgs()
        args.event.let { viewModel.setEvent(it) }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        return ComposeView(requireContext()).apply {
            setContent {
                EventDetailScreen(viewModel)
            }
        }
    }
}