package com.caglaakgul.ecabsandroidchallenge

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description
@ExperimentalCoroutinesApi
class MainCoroutinesRule(
    val testDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()
) : TestWatcher() {

    val testScope = TestScope(testDispatcher)

    override fun starting(description: Description?) {
        Dispatchers.setMain(testDispatcher)
    }

    override fun finished(description: Description?) {
        Dispatchers.resetMain()
    }

}
