package com.seoulcontest.firstcitizen.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.seoulcontest.firstcitizen.R
import com.seoulcontest.firstcitizen.viewmodel.MainViewModel
import com.seoulcontest.firstcitizen.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var fragment: Fragment
    lateinit var mainBinding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_main)
        // 2019.09.12 Main View 및 DataBinding by Hudson
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        // view 초기화
        initView()


        val mainModel = MainViewModel("Test Text")
        mainBinding.main = mainModel
        // add- Pull Request

    }


    override fun onClick(v: View?) {

        var mainCounter: Int = 0

        when (v!!.id) {

            mainBinding.fabPoint.id -> Toast.makeText(
                this,
                "Point Clicked",
                Toast.LENGTH_SHORT
            ).show()

            mainBinding.fabInformation.id -> Toast.makeText(
                this,
                "Information Clicked",
                Toast.LENGTH_SHORT
            ).show()

            mainBinding.fabMain.id -> Toast.makeText(
                this,
                "Main Clicked",
                Toast.LENGTH_SHORT
            ).show()

        }


    }


    fun initView(): Unit {

        mainBinding.fabPoint.setOnClickListener(this)
        mainBinding.fabInformation.setOnClickListener(this)
        mainBinding.fabMain.setOnClickListener(this)

    }


}
