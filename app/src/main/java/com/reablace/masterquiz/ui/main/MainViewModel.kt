package com.reablace.masterquiz.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestoreException
import com.reablace.masterquiz.base.BaseViewModel
import com.reablace.masterquiz.common.FIREBASE_TIMEOUT
import com.reablace.masterquiz.firebase.firestore.FirestoreRepositoryContract
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import javax.inject.Inject

private const val TAG: String = "MainViewModel"

class MainViewModel @Inject constructor(
    private val firestoreRepository: FirestoreRepositoryContract) : BaseViewModel() {

    private val _tenancyId = MutableLiveData<String>()
    val tenancyId: LiveData<String> = _tenancyId


    fun getUserTenancy(userAuthId: String) {

        viewModelScope.launch {
            Log.w(TAG, "Show loading")
            _showLoading.value = true
            try {
                withTimeout(FIREBASE_TIMEOUT) {
                    firestoreRepository.getUserTenancy(userAuthId).let {
                        _tenancyId.value = it
                        Log.w(TAG, "Tenancy value: $it")
                    }
                }
            } catch (e: FirebaseFirestoreException) {
                Log.e(TAG, "Error conecting Firestore database", e)
            } finally {
                _showLoading.value = false
                Log.w(TAG, "Hide loading")
            }
        }
    }
}

