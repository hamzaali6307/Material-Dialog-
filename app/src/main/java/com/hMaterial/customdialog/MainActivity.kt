package com.hMaterial.customdialog

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.HMaterial.customdialog.R
import com.HMaterial.customdialog.databinding.ActivityMainBinding
import com.hMaterial.customdialog.utils.Constants.SUCCESS

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var dialog: MaterialDialog.Builder? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().replace(R.id.fl_main, SampleDialogFragment())
            .commit()


        binding.btnShow.setOnClickListener {

            dialog = MaterialDialog.Builder(this).apply {
                setTitle("Pop-Up Title")
                setMessage("Message goes here")
                setCancelable(true)
                setDialogTypeImage(SUCCESS)
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