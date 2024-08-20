package com.examen.advancedandroidbootcamp_2024

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.examen.advancedandroidbootcamp_2024.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var viewModelFactory: MainActivityViewModelFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModelFactory = MainActivityViewModelFactory(125)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainActivityViewModel::class.java)
        mainBinding.insertButton.setOnClickListener(this)
        viewModel.totalData.observe(this, Observer {
            mainBinding.resultTextView.text = it.toString()
        })

    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.insert_button ->{
                getCountDisplay()
            }
        }
    }

    private fun getCountDisplay() {
        viewModel.setTotal(mainBinding.inputEditText.text.toString().toInt())
    }


}