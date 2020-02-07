package com.example.countries

import com.example.countries.main.MainPresenter
import com.example.countries.main.interfaces.MainInterface
import com.nhaarman.mockitokotlin2.clearInvocations
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Test

import org.mockito.Mock
import org.mockito.MockitoAnnotations

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest() {

    lateinit var mockMainPresenter: MainPresenter

    @Mock
    lateinit var mainInterface: MainInterface


    @Before
    fun mockPresenter() {
        MockitoAnnotations.initMocks(this)
        mockMainPresenter = MainPresenter()
        with(mockMainPresenter) {
            attachView(mainInterface)
        }
    }

    @Test
    fun titleTest() {
        clearInvocations(mainInterface) // Clear previous events
        mockMainPresenter.setTitle("Title")
        verify(mainInterface).setTitle("Title")
    }

    @Test
    fun toastTest() {
        clearInvocations(mainInterface)
        mockMainPresenter.showToast("Message")
        verify(mainInterface).showMessage("Message")
    }
}
