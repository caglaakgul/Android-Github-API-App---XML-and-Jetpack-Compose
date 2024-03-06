package com.caglaakgul.ecabsandroidchallenge

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.caglaakgul.ecabsandroidchallenge.data.repository.EventsRepository
import com.caglaakgul.ecabsandroidchallenge.ui.list.EventListViewModel
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28])
class EventListViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutinesRule = MainCoroutinesRule()

    private lateinit var viewModel: EventListViewModel
    private val eventsRepository: EventsRepository = mockk()
    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        viewModel = EventListViewModel(eventsRepository)
    }
}