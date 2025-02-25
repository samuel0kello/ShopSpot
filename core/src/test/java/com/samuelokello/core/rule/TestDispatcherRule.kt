// package com.samuelokello.core.rule
//
// import android.util.EventLogTags
// import kotlinx.coroutines.Dispatchers
// import kotlinx.coroutines.test.TestDispatcher
// import kotlinx.coroutines.test.UnconfinedTestDispatcher
// import kotlinx.coroutines.test.resetMain
// import kotlinx.coroutines.test.setMain
// import org.junit.rules.TestWatcher
// import org.junit.runner.Description
//
// class TestDispatcherRule(
//    val testDispatcher: TestDispatcher = UnconfinedTestDispatcher(),
// ) : TestWatcher() {
//    override fun starting(description: EventLogTags.Description?) {
//        Dispatchers.setMain(testDispatcher)
//    }
//
//    override fun finished(description: EventLogTags.Description?) {
//        Dispatchers.resetMain()
//    }
// }
