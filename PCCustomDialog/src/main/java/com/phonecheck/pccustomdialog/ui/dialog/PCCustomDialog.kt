package com.phonecheck.pccustomdialog.ui.dialog

import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.phonecheck.pccustomdialog.databinding.DialogCustomAlertBinding
import com.phonecheck.pccustomdialog.ui.interfaces.PCDialogNotifier


internal class PCCustomDialog : DialogFragment(), View.OnClickListener {

    private var _binding: DialogCustomAlertBinding? = null
    private val binding get() = _binding!!

    private var mPositiveButtonListener: PCDialogNotifier? = null
    private var mNegativeButtonListener: PCDialogNotifier? = null
    private var mNeutralButtonListener: PCDialogNotifier? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogCustomAlertBinding.inflate(inflater)

        initViews()

        return _binding?.root
    }

    private fun initViews() {

        val bundle = arguments

        val title = bundle?.getString("mTitle")!!
        val message = bundle.getString("mMessage")!!

        val mPositiveButtonText = bundle.getString("mPositiveButtonText")!!
        val mNegativeButtonText = bundle.getString("mNegativeButtonText")!!
        val mNeutralButtonText = bundle.getString("mNeutralButtonText")!!

        with(binding) {

            tvDialogTitle.setUpText(title)
            tvDialogMessage.setUpText(message)

            btnDialogPositive.setUpButton(mPositiveButtonText)
            btnDialogNegative.setUpButton(mNegativeButtonText)
            btnDialogNeutral.setUpButton(mNeutralButtonText)
        }
    }

    private fun TextView.setUpText(title: String) {

        visibility = if (title.isNotEmpty()) View.VISIBLE else View.GONE
        text = title
    }

    private fun Button.setUpButton(title: String) {

        visibility = if (title.isNotEmpty()) View.VISIBLE else View.GONE
        text = title
        setOnClickListener(this@PCCustomDialog)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window!!.requestFeature(Window.FEATURE_NO_TITLE)
        return dialog
    }

    override fun onStart() {
        super.onStart()
        val dialog = dialog
        if (dialog != null) {
            val window = dialog.window
            window!!.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            window.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))
        }
    }

    fun setPositiveCallBack(listener: PCDialogNotifier) {
        this.mPositiveButtonListener = listener
    }

    fun setNegativeCallBack(listener: PCDialogNotifier) {
        this.mNegativeButtonListener = listener
    }

    fun setNeutralCallBack(listener: PCDialogNotifier) {
        this.mNeutralButtonListener = listener
    }

    override fun onClick(v: View?) {

        dialog?.dismiss()

        when (v) {

            binding.btnDialogPositive -> mPositiveButtonListener?.onClick(dialog!!)

            binding.btnDialogNegative -> mNegativeButtonListener?.onClick(dialog!!)

            binding.btnDialogNeutral -> mNeutralButtonListener?.onClick(dialog!!)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (dialog != null && dialog!!.isShowing) dialog!!.dismiss()

        mPositiveButtonListener = null
        mNegativeButtonListener = null
        mNeutralButtonListener = null

        _binding = null
    }
}


