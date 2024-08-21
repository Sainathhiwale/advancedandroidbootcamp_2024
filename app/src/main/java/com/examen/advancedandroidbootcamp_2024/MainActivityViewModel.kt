package com.examen.advancedandroidbootcamp_2024

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class MainActivityViewModel() : ViewModel() {
     var count = MutableLiveData<Int>()
   /*  val countData: LiveData<Int>

         get() = count
*/
    init {
        count.value = 0
    }

    fun updateCount() {
        count.value = count.value?.plus(1)
    }

}