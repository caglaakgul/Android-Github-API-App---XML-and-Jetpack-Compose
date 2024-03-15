package com.caglaakgul.ecabsandroidchallenge.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.caglaakgul.ecabsandroidchallenge.MainCoroutinesRule
import com.caglaakgul.ecabsandroidchallenge.data.repository.EventsRepository
import com.caglaakgul.ecabsandroidchallenge.ui.list.EventListViewModel
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@Config(manifest=Config.NONE)
@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)
class EventListViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutinesRule()

    private lateinit var viewModel: EventListViewModel
    private lateinit var eventsRepository: EventsRepository

    @Before
    @ExperimentalCoroutinesApi
    fun setup(){
        eventsRepository = mockk(relaxed = true)
        viewModel = EventListViewModel(eventsRepository)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `is getEventList() called when VM created`() = runTest {
        verify(exactly = 1) { eventsRepository.getEventList() }
    }
}