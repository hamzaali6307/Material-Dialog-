package com.phonecheck.pccustomdialog

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.phonecheck.pccustomdialog.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var dialog: PCDialog.Builder? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().replace(R.id.fl_main, SampleDialogFragment())
            .commit()


        binding.btnShow.setOnClickListener {

            dialog = PCDialog.Builder(this).apply {
                setTitle("Pop-Up Title")
                setMessage("Message goes here")
                setCancelable(true)
                setPositiveButton("Blue Button") {
                    Log.e("TAG", "Positive")
                }
                setNegativeButton("Red Button") {
                    Log.e("TAG", "Negative")
                }
                setNeutralButton("Gray Button") {
                    Log.e("TAG", "Neutral")
                }
                show()
            }
        }
    }

}