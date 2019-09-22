package org.koin.sample

import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.sample.view.simple.MySimplePresenter
import org.koin.test.KoinTest
import org.koin.test.get
import org.koin.test.inject
import org.koin.test.mock.declareMock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify

class MySimplePresenterTest : KoinTest {

    val presenter: MySimplePresenter by inject()

    @Before
    fun before() {
        startKoin {
            printLogger()
            modules(appModule)
        }
    }

    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun `say hello`() {
        // reuse the lazy injected presenter
        assertTrue(presenter.sayHello().contains("Hello Koin"))
    }

    @Test
    fun `say hello with mock`() {
        declareMock<HelloRepository>()
        // retrieve the HelloRepository mock
        val mock = get<HelloRepository>()
        // retrieve actual presenter (injected with mock)
        val presenter = get<MySimplePresenter>()
        presenter.sayHello()

        verify(mock, times(1)).giveHello()
    }
}