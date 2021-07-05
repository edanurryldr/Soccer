package com.edanuryildirim.futbolapi.view
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.edanuryildirim.futbolapi.R
import com.edanuryildirim.futbolapi.adapter.DetailAdapter
import com.edanuryildirim.futbolapi.adapter.RecyclerviewAdapter
import com.edanuryildirim.futbolapi.model.FutbolModel
import com.edanuryildirim.futbolapi.repository.AppRepository
import com.edanuryildirim.futbolapi.util.Resource
import com.edanuryildirim.futbolapi.util.errorSnack
import com.edanuryildirim.futbolapi.viewModel.PicsViewModel
import com.edanuryildirim.futbolapi.viewModel.ViewModelProviderFactory
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    private lateinit var viewModel: PicsViewModel
    lateinit var picsAdapter: DetailAdapter
    private var futbolModel:List<FutbolModel>?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        init()
    }

    private fun init() {
        recyclerViewDetail.setHasFixedSize(true)
        recyclerViewDetail.layoutManager = LinearLayoutManager(this)
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
                        picsAdapter = DetailAdapter(picsResponse)
                        futbolModel=picsResponse
                        recyclerViewDetail.adapter = picsAdapter
                    }
                }

                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        rootLayoutt.errorSnack(message,Snackbar.LENGTH_LONG)
                    }

                }

                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })
    }

    private fun hideProgressBar() {
        progres.visibility = View.GONE
    }

    private fun showProgressBar() {
        progres.visibility = View.VISIBLE
    }

}