package com.edanuryildirim.futbolapi.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.edanuryildirim.futbolapi.R
import com.edanuryildirim.futbolapi.adapter.RecyclerviewAdapter
import com.edanuryildirim.futbolapi.model.FutbolModel
import com.edanuryildirim.futbolapi.repository.AppRepository
import com.edanuryildirim.futbolapi.util.Resource
import com.edanuryildirim.futbolapi.util.errorSnack
import com.edanuryildirim.futbolapi.viewModel.PicsViewModel
import com.edanuryildirim.futbolapi.viewModel.ViewModelProviderFactory
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var  pref:savesharePrefences
    private lateinit var viewModel: PicsViewModel
    lateinit var picsAdapter: RecyclerviewAdapter
    private var futbolModel:List<FutbolModel>?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pref=savesharePrefences(this)
        initViews()
        switch()
        init()
    }

    private fun init() {
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        setupViewModel()
    }

    private fun setupViewModel() {
        val repository = AppRepository()
        val factory = ViewModelProviderFactory(application, repository)
        viewModel = ViewModelProvider(this, factory).get(PicsViewModel::class.java)
        getPictures()
    }

    private fun getPictures() {
        viewModel.picsData.observe(this, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { picsResponse ->
                        picsAdapter = RecyclerviewAdapter(picsResponse)
                        futbolModel=picsResponse
                        recyclerView.adapter = picsAdapter
                    }
                }

                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        rootLayout.errorSnack(message,Snackbar.LENGTH_LONG)
                    }

                }

                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })
    }

    private fun hideProgressBar() {
        progress.visibility = View.GONE
    }

    private fun showProgressBar() {
        progress.visibility = View.VISIBLE
    }
    fun Fikstur(view:View) {
        val intent =  Intent(this,DetailActivity::class.java)
        startActivity(intent)
    }

    fun Gecis(view:View){
        val i =  Intent(this,SwipeActivity::class.java)
        i.putParcelableArrayListExtra("futbolResponseModel",futbolModel as? ArrayList)
        startActivity(i)
    }
    fun switch(){
        switchh.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                pref.state = true;
                delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_NO;
            }else{
                pref.setState(false);
                delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_YES;
            }
        }
}
    private fun initViews(){
        if(pref.getState()){
            delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_NO;
        }else{
            delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_YES;
        }
        if(pref.state){
            switchh.isChecked = true;
        }
    }

}