package com.examen.advancedandroidbootcamp_2024

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.examen.advancedandroidbootcamp_2024.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var mainBinding: ActivityMainBinding
    private  var count = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mainBinding.textView.text =count.toString()
        mainBinding.buttonCount.setOnClickListener(this)

    }
     onDe
    override fun onClick(v: View?) {
        when(v?.id){
            R.id.button_count ->{
                getCountDisplay()
            }
        }
    }

    fun getCountDisplay(){
        count ++
        mainBinding.textView.text = count.toString()
    }


}