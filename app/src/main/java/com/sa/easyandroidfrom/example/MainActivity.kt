package com.sa.easyandroidfrom.example


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mix.setOnClickListener {
            val intent = Intent(this, MixFormActivity::class.java)
            startActivity(intent)
        }

        allMandatory.setOnClickListener {
            val intent = Intent(this, MandatoryFormActivity::class.java)
            startActivity(intent)
        }
    }
}
