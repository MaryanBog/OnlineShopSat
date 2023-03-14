package com.example.onlineshopsat.presentation.profile

import android.net.Uri

sealed class ProfileState {
    object Start : ProfileState()
    object Error : ProfileState()
    class Photo(val photoUri: Uri?) : ProfileState()
}
