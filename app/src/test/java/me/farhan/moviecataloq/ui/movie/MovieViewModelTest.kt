package me.farhan.moviecataloq.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import me.farhan.moviecataloq.data.model.Movie
import me.farhan.moviecataloq.data.repository.MovieCataloqRepository
import me.farhan.moviecataloq.util.DataDummy
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

/**
 * @author farhan
 * created at at 11:08 on 02/11/2020.
 */
@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: MovieCataloqRepository

    @Mock
    private lateinit var observer: Observer<List<Movie>>

    @Before
    fun setUp() {
        viewModel = MovieViewModel(repository)
    }

    @Test
    fun getMovies() {
        val dummyMovies = DataDummy.getMovies()
        val movies = MutableLiveData<List<Movie>>()
        movies.value = dummyMovies

        `when`(repository.getMovies()).thenReturn(movies)
        val entities = viewModel.getMovies().value
        verify(repository).getMovies()

        assertNotNull(entities)
        assertEquals(10, entities?.size)

        viewModel.getMovies().observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }
}