package com.examen.advancedandroidbootcamp_2024

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.examen.advancedandroidbootcamp_2024.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        mainBinding.buttonSubmit.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.button_submit ->{
                getTextDisplay()
            }
        }
    }

    private fun getTextDisplay() {
        val text = mainBinding.editText.text.toString()
        mainBinding.textView.text = text
    }
}