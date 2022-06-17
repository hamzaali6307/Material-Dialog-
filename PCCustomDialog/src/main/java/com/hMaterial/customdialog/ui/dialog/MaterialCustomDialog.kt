package com.hMaterial.customdialog.ui.dialog

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
import com.airbnb.lottie.LottieAnimationView
import com.phonecheck.pccustomdialog.R
import com.phonecheck.pccustomdialog.databinding.DialogCustomAlertBinding
import com.hMaterial.customdialog.ui.interfaces.MaterialDialogNotifier
import com.hMaterial.customdialog.utils.Constants


internal class MaterialCustomDialog : DialogFragment(), View.OnClickListener {

    private var _binding: DialogCustomAlertBinding? = null
    private val binding get() = _binding!!

    private var mPositiveButtonListener: MaterialDialogNotifier? = null
    private var mNegativeButtonListener: MaterialDialogNotifier? = null
    private var mNeutralButtonListener: MaterialDialogNotifier? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = DialogCustomAlertBinding.inflate(inflater)

        initViews()

        return _binding?.root
    }

    private fun initViews() {

        val bundle = arguments

        val title = bundle?.getString("mTitle")!!
        val message = bundle.getString("mMessage")!!
        val mDialogImageType = bundle.getString("mDialogType")!!
        val mPositiveButtonText = bundle.getString("mPositiveButtonText")!!
        val mNegativeButtonText = bundle.getString("mNegativeButtonText")!!
        val mNeutralButtonText = bundle.getString("mNeutralButtonText")!!

        with(binding) {

            tvDialogTitle.setUpText(title)
            tvDialogMessage.setUpText(message)

            llLoading.setUpDialogImage(mDialogImageType)

            btnDialogPositive.setUpButton(mPositiveButtonText)
            btnDialogNegative.setUpButton(mNegativeButtonText)
            btnDialogNeutral.setUpButton(mNeutralButtonText)
        }
    }

    private fun TextView.setUpText(title: String) {

        visibility = if (title.isNotEmpty()) View.VISIBLE else View.GONE
        text = title
    }

    private fun LottieAnimationView.setUpDialogImage(dialogImage: String) {
        visibility = if (dialogImage != Constants.DEFAULT) View.VISIBLE else View.GONE
        setAnimation(
            when (dialogImage) {
                Constants.SUCCESS -> R.raw.success
                else -> R.raw.anim_error
            }
        )
    }

    private fun Button.setUpButton(title: String) {

        visibility = if (title.isNotEmpty()) View.VISIBLE else View.GONE
        text = title
        setOnClickListener(this@MaterialCustomDialog)
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

    fun setPositiveCallBack(listener: MaterialDialogNotifier) {
        this.mPositiveButtonListener = listener
    }

    fun setNegativeCallBack(listener: MaterialDialogNotifier) {
        this.mNegativeButtonListener = listener
    }

    fun setNeutralCallBack(listener: MaterialDialogNotifier) {
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


