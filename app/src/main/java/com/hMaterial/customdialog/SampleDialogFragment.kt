package com.hMaterial.customdialog

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.phonecheck.pccustomdialog.databinding.FragmentSampleDialogBinding

class SampleDialogFragment : Fragment() {

    private var _binding: FragmentSampleDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSampleDialogBinding.inflate(inflater)

        binding.btnShowDialog.setOnClickListener {

            MaterialDialog.Builder(this).apply {
                setTitle("Pop-Up Title")
                setMessage("Message goes here")
                setCancelable(true)
                setPositiveButton("Blue Button") {
                    Log.e("TAG", "Positive")
                }
                setNegativeButton("Red Button") {
                    Log.e("TAG", "Negative")
                }
                show()
            }
        }

        return _binding?.root
    }

    override fun onDestroy() {
        super.onDestroy()

        _binding = null
    }
}