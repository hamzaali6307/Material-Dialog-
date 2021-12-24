package com.phonecheck.pccustomdialog.ui.dialog

import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.phonecheck.pccustomdialog.databinding.DialogAlertBinding
import com.phonecheck.pccustomdialog.ui.interfaces.PCDialogNotifier
import com.phonecheck.pccustomdialog.utils.isVisible
import com.phonecheck.pccustomdialog.utils.setMultipleClickListener

internal class PCCustomDialog : DialogFragment(), View.OnClickListener {

    private var _binding: DialogAlertBinding? = null
    private val binding get() = _binding!!

    private var mPositiveButtonListener: PCDialogNotifier? = null
    private var mNegativeButtonListener: PCDialogNotifier? = null
    private var mNeutralButtonListener: PCDialogNotifier? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogAlertBinding.inflate(inflater)

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

            btnPass.isVisible(mPositiveButtonText.isNotEmpty())
            btnFail.isVisible(mNegativeButtonText.isNotEmpty())
            btnRetry.isVisible(mNeutralButtonText.isNotEmpty())

            tvTitle.text = title
            tvMessage.text = message
            btnPass.text = mPositiveButtonText
            btnFail.text = mNegativeButtonText
            btnRetry.text = mNeutralButtonText

            setMultipleClickListener(
                btnPass, btnFail, btnRetry
            )
        }
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
            dialog.window!!.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            dialog.window!!.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))
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

            binding.btnPass -> mPositiveButtonListener?.onClick(dialog!!)

            binding.btnFail -> mNegativeButtonListener?.onClick(dialog!!)

            binding.btnRetry -> mNeutralButtonListener?.onClick(dialog!!)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (dialog != null && dialog!!.isShowing) dialog!!.dismiss()
        _binding = null
    }
}


