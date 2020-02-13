package ru.vstu.visdom.androidsample.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ru.vstu.visdom.androidsample.R
import ru.vstu.visdom.androidsample.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var tvHello: TextView
    private lateinit var btnHello: Button
    private lateinit var pbHello: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this, MainViewModel.Factory()).get(MainViewModel::class.java)

        setupUi()
        setupUx()
    }

    private fun setupUi() {
        tvHello = findViewById(R.id.hello_text_view)
        btnHello = findViewById(R.id.hello_button)
        pbHello = findViewById(R.id.hello_progress_bar)
    }

    private fun setupUx() {
        btnHello.setOnClickListener { viewModel.getAlbumById(1) }
        viewModel.isProgress.observe(this, Observer { showProgress(it) })
        viewModel.isError.observe(
            this,
            Observer { if (it) Toast.makeText(applicationContext, "network error", Toast.LENGTH_SHORT) }
        )
        viewModel.text.observe(this, Observer { tvHello.text = it })
    }

    private fun showProgress(isShow: Boolean) {
        tvHello.visibility = if (isShow) View.GONE else View.VISIBLE
        btnHello.visibility = if (isShow) View.GONE else View.VISIBLE
        pbHello.visibility = if (isShow) View.VISIBLE else View.GONE
    }
}
