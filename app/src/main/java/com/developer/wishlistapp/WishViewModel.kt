package com.developer.wishlistapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class WishViewModel (): ViewModel() {
    var wishTitle by mutableStateOf("")
    var wishDescriptionState by mutableStateOf("")

    fun onWishTitleChanged(newString: String){
        wishTitle = newString
    }

    fun onWishDescriptionChanged(newString: String){
        wishDescriptionState = newString
    }
}