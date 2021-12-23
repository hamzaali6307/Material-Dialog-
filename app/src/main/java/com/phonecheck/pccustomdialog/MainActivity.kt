package com.phonecheck.pccustomdialog

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.phonecheck.pccustomdialog.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.root.setOnClickListener {

              PCDialog.Builder(this).apply {
                  setTitle("Pop-Up Title")
                  setMessage("Message goes here")
                  setCancelable(true)
                  setPositiveButton("Blue Button") { dialog ->
                      Log.e("TAG", "Positive")
                      dialog.dismiss()
                  }
                  setNegativeButton("Red Button") { dialog ->
                      dialog.dismiss()
                      Log.e("TAG", "Negative")
                  }
                  setNeutralButton("Gray Button") { dialog ->
                      dialog.dismiss()
                      Log.e("TAG", "Neutral")
                  }
                  show()
              }
        }
    }
}