package com.example.countries

import android.content.Context
import com.example.countries.countries.CountriesInterface
import com.example.countries.countries.CountriesPresenter
import com.example.countries.details.DetailsInterface
import com.example.countries.details.DetailsPresenter
import com.example.countries.main.MainPresenter
import com.example.countries.main.interfaces.MainInterface
import com.example.countries.networking.CountryInteractorInterface
import com.example.countries.networking.TestContextProvider
import com.example.countries.networking.models.Country
import com.example.countries.repository.models.AllCountriesModel
import com.example.countries.repository.models.CountriesModel
import com.nhaarman.mockitokotlin2.clearInvocations
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.test.KoinTest
import org.koin.test.mock.declareMock
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


class CountryPresenterTest : KoinTest {

    lateinit var countryPresenter: CountriesPresenter


    val interactor by lazy { declareMock<CountryInteractorInterface>() }

    @Mock
    lateinit var countriesInterface: CountriesInterface

    @Mock
    lateinit var application: Context


    @Before
    fun initMocks() {
        MockitoAnnotations.initMocks(this)
        startKoin {
            androidContext(application)
            modules(listOf(networkModule, countriesModule))
        }
    }

    @Test
    fun test() = runBlocking {
        whenever(interactor.getAllCountries()).thenReturn(AllCountriesModel(error = "Error"))
        countryPresenter = CountriesPresenter(interactor)
        countryPresenter.coroutineContextProvider = TestContextProvider()
        countryPresenter.attachView(countriesInterface)
        verify(countriesInterface).showLoader()
        verify(countriesInterface).hideLoader()
        verify(countriesInterface).showToastMessage("Error")
    }

}

class DetailsPresenterTest : KoinTest {

    lateinit var presenter: DetailsPresenter

    @Mock
    lateinit var application: Context

    @Mock
    lateinit var detailsView: DetailsInterface

    val interactor by lazy { declareMock<CountryInteractorInterface>() }

    @Before
    fun initMocks() {
        MockitoAnnotations.initMocks(this)
        startKoin {
            androidContext(application)
            modules(listOf(networkModule, countriesModule))
        }
    }

    @Test
    fun showErrorTest() = runBlocking {
        presenter.attachView(detailsView)
        clearInvocations(detailsView)
        presenter = DetailsPresenter(interactor, "")
        presenter.coroutineContextProvider = TestContextProvider()
        whenever(interactor.getCountryByName("")).thenReturn(CountriesModel(error = "Error"))
        verify(detailsView).showProgressBar()
        verify(detailsView).hideProgressBar()
    }
}
