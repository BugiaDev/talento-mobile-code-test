package com.bugiadev.talentomobile

import com.bugiadev.talentomobile.data.datasource.ChurchDataSource
import com.bugiadev.talentomobile.data.entities.*
import com.bugiadev.talentomobile.data.repository.ChurchRepositoryImpl
import com.bugiadev.talentomobile.domain.repository.ChurchRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class ChurchRepositoryImplTest {
    private val dataSource: ChurchDataSource = mockk()
    private lateinit var repository: ChurchRepository

    @Before
    fun setup() {
        repository = ChurchRepositoryImpl(
                dataSource = dataSource
        )
    }

    @Test
    fun `catholic churches are retrieved successfully`() {
        // Given
        val data = mockCatholicChurchEntity()
        every { dataSource.getCatholicChurches() } returns Single.just(data)

        // When
        val sut = repository.getCatholicChurches().test()

        // Then
        verify(exactly = 1) { dataSource.getCatholicChurches() }
        sut.assertComplete()
        sut.assertNoErrors()
        sut.assertValue {
            it.size == 1 && it[0].id == mockCatholicChurch().id
        }
    }

    @Test
    fun `non catholic churches are retrieved successfully`() {
        // Given
        val data = mockNonCatholicChurchEntity()
        every { dataSource.getNonCatholicChurches() } returns Single.just(data)

        // When
        val sut = repository.getNonCatholicChurches().test()

        // Then
        verify(exactly = 1) { dataSource.getNonCatholicChurches() }
        sut.assertComplete()
        sut.assertNoErrors()
        sut.assertValue {
            it.size == 1 && it[0].id == mockNonCatholicChurch().id
        }
    }

    // region MOCKS
    private fun mockNonCatholicChurchEntity() =
        NonCatholicChurchEntity(
                data = listOf(mockNonCatholicChurch())
        )

    private fun mockNonCatholicChurch() =
        NonCatholicChurch(
                id = "fakeId",
                name = "fakeName",
                description = "fakeDescription",
                address = mockChurchAddress()
        )

    private fun mockCatholicChurchEntity() =
        CatholicChurchEntity(
                data = listOf(mockCatholicChurch())
        )

    private fun mockCatholicChurch() =
        CatholicChurch(
                id = "fakeId",
                name = "fakeName",
                description = "fakeDescription",
                address = mockChurchAddress()
        )

    private fun mockChurchAddress() =
        Address(
                locality = "fakeLocality",
                zipCode = "fakeZipCode",
                street = "fakeStreet"
        )
    // endregion
}