package lijewski.template.app.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import lijewski.template.presentation.main.MainViewModel
import lijewski.template.utils.CoroutineTestRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    private lateinit var sut: MainViewModel

    @Before
    fun setUp() {
        sut = MainViewModel()
    }

    @Test
    fun `viewmodel empty test`() {

    }
}
