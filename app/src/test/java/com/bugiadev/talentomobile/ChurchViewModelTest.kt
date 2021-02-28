package com.bugiadev.talentomobile

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.bugiadev.talentomobile.domain.models.AddressModel
import com.bugiadev.talentomobile.domain.models.ChurchModel
import com.bugiadev.talentomobile.domain.repository.ChurchRepository
import com.bugiadev.talentomobile.lifecycle.observeOnce
import com.bugiadev.talentomobile.ui.main.ChurchViewModel
import com.bugiadev.talentomobile.rules.RxSchedulerRule
import com.bugiadev.talentomobile.ui.presentation.toDisplay
import com.bugiadev.talentomobile.ui.viewmodel.UnexpectedError
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.subjects.SingleSubject
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ChurchViewModelTest {
    @get:Rule
    val schedulerRule = RxSchedulerRule()

    @get:Rule
    val instantRule = InstantTaskExecutorRule()

    private val repository: ChurchRepository = mockk()
    lateinit var viewModel: ChurchViewModel

    @Before
    fun setup() {
        viewModel = ChurchViewModel(repository)
    }

    @Test
    fun `catholic churches retrieved successfully`() {
        // Given
        val subject = SingleSubject.create<List<ChurchModel>>()
        val model = mockChurchModel(isCatholic = true)
        every { repository.getCatholicChurches() } returns subject

        // When
        viewModel.getCatholicChurches()

        // Then
        verify(exactly = 1) { repository.getCatholicChurches() }
        viewModel.loading.observeOnce { assert(it) }
        subject.onSuccess(listOf(model))
        viewModel.loading.observeOnce { assert(!it) }
        viewModel.catholicChurches.observeOnce {
            it.size == 1 && it[0].id == model.id
        }
    }

    @Test
    fun `catholic churches not retrieved due to an error`() {
        // Given
        val subject = SingleSubject.create<List<ChurchModel>>()
        every { repository.getCatholicChurches() } returns subject

        // When
        viewModel.getCatholicChurches()

        // Then
        verify(exactly = 1) { repository.getCatholicChurches() }
        viewModel.loading.observeOnce { assert(it) }
        subject.onError(Throwable())
        viewModel.loading.observeOnce { assert(!it) }
        viewModel.error.observeOnce { assert(it is UnexpectedError) }
    }

    @Test
    fun `non catholic churches retrieved successfully`() {
        // Given
        val subject = SingleSubject.create<List<ChurchModel>>()
        val model = mockChurchModel(isCatholic = false)
        every { repository.getNonCatholicChurches() } returns subject

        // When
        viewModel.getNonCatholicChurches()

        // Then
        verify(exactly = 1) { repository.getNonCatholicChurches() }
        viewModel.loading.observeOnce { assert(it) }
        subject.onSuccess(listOf(model))
        viewModel.loading.observeOnce { assert(!it) }
        viewModel.nonCatholicChurches.observeOnce {
            it.size == 1 && it[0].id == model.id
        }
    }

    @Test
    fun `non catholic churches not retrieved due to an error`() {
        // Given
        val subject = SingleSubject.create<List<ChurchModel>>()
        every { repository.getNonCatholicChurches() } returns subject

        // When
        viewModel.getNonCatholicChurches()

        // Then
        verify(exactly = 1) { repository.getNonCatholicChurches() }
        viewModel.loading.observeOnce { assert(it) }
        subject.onError(Throwable())
        viewModel.loading.observeOnce { assert(!it) }
        viewModel.error.observeOnce { assert(it is UnexpectedError) }
    }

    @Test
    fun `on catholic church selected by user`() {
        // Given
        val display = mockChurchModel(true).toDisplay()

        // When
        viewModel.onChurchSelected(display)

        // Then
        viewModel.selectedChurch.observeOnce {
            assert(it.id == display.id)
        }
    }

    @Test
    fun `on non catholic temple selected by user`() {
        // Given
        val display = mockChurchModel(false).toDisplay()

        // When
        viewModel.onTempleSelected(display)

        // Then
        viewModel.selectedTemple.observeOnce {
            assert(it.id == display.id)
        }
    }

    // region MOCKS
    private fun mockChurchModel(isCatholic: Boolean) =
        ChurchModel(
            id = "fakeId",
            name = "fakeName",
            description = "fakeDescription",
            isCatholic = isCatholic,
            address = mockAddressModel()
        )

    private fun mockAddressModel() =
        AddressModel(
            locality = "fakeLocality",
            street = "fakeStreet",
            zipCode = "fakeZipCode"
        )
    // endregion
}