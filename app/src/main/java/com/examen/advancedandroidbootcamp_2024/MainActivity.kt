package com.examen.advancedandroidbootcamp_2024

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.examen.advancedandroidbootcamp_2024.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]

        mainBinding.textView.text =viewModel.getCurrentCount().toString()
        mainBinding.buttonCount.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.button_count ->{
                getCountDisplay()
            }
        }
    }

    fun getCountDisplay(){
        mainBinding.textView.text = viewModel.getUpdateCount().toString()
    }


}