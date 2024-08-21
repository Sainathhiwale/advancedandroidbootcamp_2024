package com.examen.advancedandroidbootcamp_2024


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class MainActivityViewModel() : ViewModel() {
     var user = MutableLiveData<String>()

    init {
        user.value = "Sainath"
    }


}