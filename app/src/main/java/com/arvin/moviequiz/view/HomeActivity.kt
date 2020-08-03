package com.arvin.moviequiz.view

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.arvin.moviequiz.R
import com.arvin.moviequiz.databinding.ContentHomeBinding
import com.arvin.moviequiz.viewmodels.HomeViewModel
import kotlinx.android.synthetic.main.content_home.*

class HomeActivity : AppCompatActivity(),View.OnClickListener {
    private lateinit var homeViewModel: HomeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

        DataBindingUtil.setContentView<ContentHomeBinding>(
            this, R.layout.content_home
        ).apply {
            this.lifecycleOwner = this@HomeActivity
            this.viewModel = homeViewModel
        }

        btnA.setOnClickListener(this)
        btnB.setOnClickListener(this)
        btnC.setOnClickListener(this)
        btnD.setOnClickListener(this)
        fabNext.setOnClickListener(this)

        homeViewModel.checkAnswer.observe(this, androidx.lifecycle.Observer {
            if(it.first == 0){
                when(it.second){
                    0 -> btnA.setBackgroundColor(Color.RED)
                    1 -> btnB.setBackgroundColor(Color.RED)
                    2 -> btnC.setBackgroundColor(Color.RED)
                    3 -> btnD.setBackgroundColor(Color.RED)
                }
            }
            else{
                when(it.second){
                    0 -> btnA.setBackgroundColor(Color.GREEN)
                    1 -> btnB.setBackgroundColor(Color.GREEN)
                    2 -> btnC.setBackgroundColor(Color.GREEN)
                    3 -> btnD.setBackgroundColor(Color.GREEN)
                }
            }
            homeViewModel.setRightOrWrongAnswerCount(it.first)

        })
    }

    override fun onClick(v: View?) {
       when(v?.id){
           R.id.btnA-> homeViewModel.setValueCheckAnswer(0)
           R.id.btnB-> homeViewModel.setValueCheckAnswer(1)
           R.id.btnC-> homeViewModel.setValueCheckAnswer(2)
           R.id.btnD-> homeViewModel.setValueCheckAnswer(3)
           R.id.fabNext->{
               btnA.setBackgroundColor(Color.GRAY)
               btnB.setBackgroundColor(Color.GRAY)
               btnC.setBackgroundColor(Color.GRAY)
               btnD.setBackgroundColor(Color.GRAY)
               homeViewModel.getMovies()
           }
       }
    }
}
