package com.example.onlineshopsat.presentation.profile

import android.net.Uri
import androidx.compose.ui.res.painterResource
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshopsat.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(): ViewModel() {

    private var _stateProfile = MutableStateFlow<ProfileState>(ProfileState.Start)
    val stateProfile = _stateProfile.asStateFlow()

    private var _photoUri = Channel<Uri?>()
    val photoUri = _photoUri.receiveAsFlow()

    fun getPhoto(photoUri: Uri?) {
        viewModelScope.launch {
            _photoUri.send(photoUri)
        }
//        _stateProfile.value = ProfileState.Photo(
//            photoUri = photoUri
//        )
    }
}