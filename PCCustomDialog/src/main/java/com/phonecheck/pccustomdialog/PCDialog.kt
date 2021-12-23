package com.phonecheck.pccustomdialog

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.phonecheck.pccustomdialog.ui.PCDialogNotifier
import com.phonecheck.pccustomdialog.ui.dialog.PCCustomDialog

open class PCDialog {

    protected var mTitle = ""
    protected var mMessage = ""
    protected var mPositiveButtonText = ""
    protected var mNegativeButtonText = ""
    protected var mNeutralButtonText = ""

    protected var isCancelable = true

    protected var mPositiveButtonListener: PCDialogNotifier? = null
    protected var mNegativeButtonListener: PCDialogNotifier? = null
    protected var mNeutralButtonListener: PCDialogNotifier? = null

    protected lateinit var mContext: AppCompatActivity

    open fun Builder.show() {
        val dialog = PCCustomDialog()
        val bundle = Bundle().apply {
            putString("mTitle", mTitle)
            putString("mMessage", mMessage)
            putString("mPositiveButtonText", mPositiveButtonText)
            putString("mNegativeButtonText", mNegativeButtonText)
            putString("mNeutralButtonText", mNeutralButtonText)
        }
        dialog.arguments = bundle
        dialog.isCancelable = isCancelable

        if (mPositiveButtonListener != null) {
            dialog.setPositiveCallBack(mPositiveButtonListener!!)
        }

        if (mNegativeButtonListener != null) {
            dialog.setNegativeCallBack(mNegativeButtonListener!!)
        }

        if (mNeutralButtonListener != null) {
            dialog.setNeutralCallBack(mNeutralButtonListener!!)
        }

        dialog.show(mContext.supportFragmentManager, "")
    }

     class Builder(context: AppCompatActivity) : PCDialog() {

         init {
            super.mContext = context
        }

        fun setTitle(title: String): Builder {
            super.mTitle = title
            return this
        }

        fun setMessage(message: String): Builder {
            super.mMessage = message
            return this
        }

        fun setCancelable(cancelable: Boolean): Builder {
            super.isCancelable = cancelable
            return this
        }

        fun setPositiveButton(text: String, onClick: (dialog: Dialog) -> Unit): Builder {
            super.mPositiveButtonText = text
            val listener = object : PCDialogNotifier {
                override fun onClick(dialog: Dialog) {
                    onClick(dialog)
                }
            }
            super.mPositiveButtonListener = listener
            return this
        }

        fun setNegativeButton(text: String, onClick: (dialog: Dialog) -> Unit): Builder {
            super.mNegativeButtonText = text
            val listener = object : PCDialogNotifier {
                override fun onClick(dialog: Dialog) {
                    onClick(dialog)
                }
            }
            super.mNegativeButtonListener = listener
            return this
        }

        fun setNeutralButton(text: String, onClick: (dialog: Dialog) -> Unit): Builder {
            super.mNeutralButtonText = text
            val listener = object : PCDialogNotifier {
                override fun onClick(dialog: Dialog) {
                    onClick(dialog)
                }
            }
            super.mNeutralButtonListener = listener
            return this
        }
    }
}