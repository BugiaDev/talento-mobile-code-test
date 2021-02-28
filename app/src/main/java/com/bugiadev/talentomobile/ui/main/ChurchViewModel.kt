package com.bugiadev.talentomobile.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bugiadev.talentomobile.domain.models.ChurchModel
import com.bugiadev.talentomobile.domain.repository.ChurchRepository
import com.bugiadev.talentomobile.ui.presentation.ChurchDisplay
import com.bugiadev.talentomobile.ui.viewmodel.BaseViewModel
import com.bugiadev.talentomobile.utils.SingleLiveEvent
import com.bugiadev.talentomobile.utils.prepareForUI
import com.bugiadev.talentomobile.utils.subscribe
import javax.inject.Inject

class ChurchViewModel @Inject constructor(
        private val repository: ChurchRepository
) : BaseViewModel() {
    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _nonCatholicChurches = MutableLiveData<List<ChurchModel>>()
    val nonCatholicChurches: LiveData<List<ChurchModel>> = _nonCatholicChurches

    private val _catholicChurches = MutableLiveData<List<ChurchModel>>()
    val catholicChurches: LiveData<List<ChurchModel>> = _catholicChurches

    private val _selectedChurch = SingleLiveEvent<ChurchDisplay>()
    val selectedChurch: LiveData<ChurchDisplay> = _selectedChurch

    private val _selectedTemple = SingleLiveEvent<ChurchDisplay>()
    val selectedTemple: LiveData<ChurchDisplay> = _selectedTemple

    fun getCatholicChurches() {
        repository.getCatholicChurches()
            .doOnSubscribe { _loading.postValue(true) }
            .doFinally { _loading.postValue(false) }
            .prepareForUI()
            .subscribe(
                disposables = disposables,
                onSuccess = { churches ->
                    _catholicChurches.postValue(churches)
                },
                onError = ::handleError
            )
    }

    fun getNonCatholicChurches() {
        repository.getNonCatholicChurches()
             .doOnSubscribe { _loading.postValue(true) }
             .doFinally { _loading.postValue(false) }
             .prepareForUI()
             .subscribe(
                  disposables = disposables,
                  onSuccess = { churches ->
                      _nonCatholicChurches.postValue(churches)
                  },
                  onError = ::handleError
             )
    }

    fun onChurchSelected(church: ChurchDisplay) {
        // Make nice stuff with this info (as analytics or whatever...)
        _selectedChurch.postValue(church)
    }

    fun onTempleSelected(temple: ChurchDisplay) {
        // Make nice stuff with this info (as analytics or whatever...)
        _selectedTemple.postValue(temple)
    }
}