package ru.evgeniy.aaacourse

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        moveToMovieDetailsActivity()
    }

    fun moveToMovieDetailsActivity() {
        val intent = Intent(this, MovieDetailsActivity::class.java)

        startActivity(intent)
    }
}