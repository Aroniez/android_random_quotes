package com.aroniez.quotes.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.aroniez.quotes.R
import com.aroniez.quotes.api.RetrofitAdapter
import com.aroniez.quotes.api.callbacks.FortunesCallback
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        if (supportActionBar != null) {
            supportActionBar!!.title = "Fortune Quotes"
        }

        loadFortunesFromServer()

        refreshQuote.setOnClickListener { loadFortunesFromServer() }

    }

    private fun loadFortunesFromServer() {
        showLoadingProgress()
        val callback = RetrofitAdapter.createAPI().fortunes()
        callback.enqueue(object : Callback<FortunesCallback> {
            override fun onFailure(call: Call<FortunesCallback>, t: Throwable) {
                showMessageLayout(getString(R.string.placeholder_message_quote))
            }

            override fun onResponse(call: Call<FortunesCallback>, response: Response<FortunesCallback>) {
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        if (response.body() != null) {
                            val fortunes = response.body()!!.fortune
                            if (fortunes.isNullOrEmpty()) {
                                showMessageLayout(getString(R.string.placeholder_message_quote))
                            } else {
                                val quote = fortunes.joinToString().trim().replace("\\s{2,}", " ")
                                showMessageLayout(quote)
                            }
                        } else {
                            showMessageLayout(getString(R.string.placeholder_message_quote))
                        }
                    } else {
                        showMessageLayout(getString(R.string.placeholder_message_quote))
                    }

                } else {
                    showMessageLayout(getString(R.string.placeholder_message_quote))
                }
            }
        })
    }

    private fun showLoadingProgress() {
        refreshQuote.visibility = View.GONE
        baseProgressBar.visibility = View.VISIBLE
        baseMessageTextView.visibility = View.GONE
    }

    private fun showMessageLayout(message: String) {
        baseProgressBar.visibility = View.GONE
        refreshQuote.visibility = View.VISIBLE
        baseMessageTextView.visibility = View.VISIBLE
        baseMessageTextView.text = message

    }
}
