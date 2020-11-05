package com.example.mytriviya

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_history.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryActivity : AppCompatActivity() {
    private  val TAG = "HistoryActivity"
    private val adapter by inject<Adapter>()
    private val viewModel by viewModel<TriviaViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setUpRecycler()
       viewModel.getAllFromDb()
        viewModel.dataFromDb.observe(this, {
            Log.e(TAG, "onCreate: $it")
            adapter.differ.submitList(it)
        })
    }

    private fun setUpRecycler() {
        postsRecyclerView.adapter = adapter
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                supportFinishAfterTransition()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}