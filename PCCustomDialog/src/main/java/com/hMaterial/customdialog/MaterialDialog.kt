package com.hMaterial.customdialog

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.hMaterial.customdialog.ui.dialog.MaterialCustomDialog
import com.hMaterial.customdialog.ui.interfaces.MaterialDialogNotifier
import com.hMaterial.customdialog.utils.Constants

object MaterialDialog {

    internal var mTitle = ""
    internal var mMessage = ""
    internal var mPositiveButtonText = ""
    internal var mNegativeButtonText = ""
    internal var mNeutralButtonText = ""
    internal var mDialogType = Constants.DEFAULT

    internal var isCancelable = true

    internal var mPositiveButtonListener: MaterialDialogNotifier? = null
    internal var mNegativeButtonListener: MaterialDialogNotifier? = null
    internal var mNeutralButtonListener: MaterialDialogNotifier? = null


    class Builder(context: AppCompatActivity) {

        private var dialog: MaterialCustomDialog? = null

        fun isShowing(): Boolean = dialog != null && dialog!!.isVisible

        fun dismiss() {
            if (dialog != null && dialog!!.isVisible) dialog!!.dismiss()
        }

        private var dialogContext: Any = context

        constructor(fragmentContext: Fragment) : this(
            (fragmentContext.requireActivity() as AppCompatActivity)
        ) {
            dialogContext = fragmentContext
        }

        init {
            mTitle = ""
            mMessage = ""
            mPositiveButtonText = ""
            mNegativeButtonText = ""
            mNeutralButtonText = ""
            mDialogType = Constants.DEFAULT
            mPositiveButtonListener = null
            mNegativeButtonListener = null
            mNeutralButtonListener = null
        }

        fun setTitle(title: String): Builder {
            mTitle = title
            return this
        }
        fun setDialogTypeImage(dialogImage: String): Builder {
            mDialogType = dialogImage

            return this
        }

        fun setMessage(message: String): Builder {
            mMessage = message
            return this
        }

        fun setCancelable(cancelable: Boolean): Builder {
            isCancelable = cancelable
            return this
        }

        fun setPositiveButton(text: String, onClick: (dialog: Dialog) -> Unit): Builder {
            mPositiveButtonText = text
            val listener = object : MaterialDialogNotifier {
                override fun onClick(dialog: Dialog) {
                    onClick(dialog)
                }
            }
            mPositiveButtonListener = listener
            return this
        }

        fun setNegativeButton(text: String, onClick: (dialog: Dialog) -> Unit): Builder {
            mNegativeButtonText = text
            val listener = object : MaterialDialogNotifier {
                override fun onClick(dialog: Dialog) {
                    onClick(dialog)
                }
            }
            mNegativeButtonListener = listener
            return this
        }

        fun setNeutralButton(text: String, onClick: (dialog: Dialog) -> Unit): Builder {
            mNeutralButtonText = text
            val listener = object : MaterialDialogNotifier {
                override fun onClick(dialog: Dialog) {
                    onClick(dialog)
                }
            }
            mNeutralButtonListener = listener
            return this
        }


        fun show() {
            dialog = MaterialCustomDialog()
            val bundle = Bundle().apply {
                putString("mTitle", mTitle)
                putString("mMessage", mMessage)
                putString("mDialogType", mDialogType)
                putString("mPositiveButtonText", mPositiveButtonText)
                putString("mNegativeButtonText", mNegativeButtonText)
                putString("mNeutralButtonText", mNeutralButtonText)
            }
            dialog!!.arguments = bundle
            dialog!!.isCancelable = isCancelable

            if (mPositiveButtonListener != null) {
                dialog!!.setPositiveCallBack(mPositiveButtonListener!!)
            }

            if (mNegativeButtonListener != null) {
                dialog!!.setNegativeCallBack(mNegativeButtonListener!!)
            }

            if (mNeutralButtonListener != null) {
                dialog!!.setNeutralCallBack(mNeutralButtonListener!!)
            }
            if (dialog!!.isVisible) dialog!!.dismiss()

            if (dialogContext is AppCompatActivity) {
                val conn = dialogContext as AppCompatActivity
                dialog!!.show(conn.supportFragmentManager, "")
            } else if (dialogContext is Fragment) {
                val conn = dialogContext as Fragment
                dialog!!.show(conn.childFragmentManager, "")
            }
        }
    }
}