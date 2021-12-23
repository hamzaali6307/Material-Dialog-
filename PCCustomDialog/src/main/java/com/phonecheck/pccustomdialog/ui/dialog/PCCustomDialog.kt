package com.phonecheck.pccustomdialog.ui.dialog

import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.DialogFragment
import com.phonecheck.pccustomdialog.ui.PCDialogNotifier
import com.phonecheck.pccustomdialog.ui.theme.*

internal class PCCustomDialog : DialogFragment() {

    private var mPositiveButtonListener: PCDialogNotifier? = null
    private var mNegativeButtonListener: PCDialogNotifier? = null
    private var mNeutralButtonListener: PCDialogNotifier? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val bundle = arguments

        val title = bundle?.getString("mTitle")!!
        val message = bundle.getString("mMessage")!!

        val mPositiveButtonText = bundle.getString("mPositiveButtonText")!!
        val mNegativeButtonText = bundle.getString("mNegativeButtonText")!!
        val mNeutralButtonText = bundle.getString("mNeutralButtonText")!!

        return ComposeView(requireContext()).apply {
            setContent {
                PCCustomDialogTheme {
                    Surface(
                        color = MaterialTheme.colors.background,
                        modifier = Modifier.padding(20.dp),
                        shape = RoundedCornerShape(18.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentSize(Alignment.Center)
                                .padding(10.dp)
                        ) {

                            Spacer(modifier = Modifier.height(10.dp))

                            Text(
                                text = title,
                                modifier = Modifier.fillMaxWidth(),
                                style = TextStyle(
                                    color = Color.Black,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center,
                                    fontFamily = fonts,
                                    fontSize = 20.sp
                                ),
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            Text(
                                text = message,
                                modifier = Modifier.fillMaxWidth(),
                                style = TextStyle(
                                    color = Color.Black,
                                    fontWeight = FontWeight.Normal,
                                    textAlign = TextAlign.Center,
                                    fontFamily = fonts,
                                    fontSize = 16.sp
                                )
                            )

                            Spacer(modifier = Modifier.height(20.dp))

                            if (mPositiveButtonText.isNotEmpty()) {

                                Button(
                                    onClick = {
                                        mPositiveButtonListener?.onClick(dialog!!)
                                    }, shape = RoundedCornerShape(23.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        backgroundColor = colorSuccess,
                                        contentColor = Color.White
                                    ),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(50.dp)

                                ) {
                                    Text(
                                        text = mPositiveButtonText,
                                        style = MaterialTheme.typography.button
                                    )
                                }

                                Spacer(modifier = Modifier.height(8.dp))
                            }

                            if (mNegativeButtonText.isNotEmpty()) {

                                Button(
                                    onClick = {
                                        mNegativeButtonListener?.onClick(dialog!!)
                                    }, shape = RoundedCornerShape(23.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        backgroundColor = colorFailure,
                                        contentColor = Color.White
                                    ),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(50.dp)

                                ) {
                                    Text(
                                        text = mNegativeButtonText,
                                        style = MaterialTheme.typography.button,
                                    )
                                }

                                Spacer(modifier = Modifier.height(8.dp))
                            }

                            if (mNeutralButtonText.isNotEmpty()) {

                                Button(
                                    onClick = {
                                        mNeutralButtonListener?.onClick(dialog!!)
                                    }, shape = RoundedCornerShape(23.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        backgroundColor = colorRetry,
                                        contentColor = Color.Black
                                    ),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(50.dp)
                                ) {
                                    Text(
                                        text = mNeutralButtonText,
                                        style = MaterialTheme.typography.button
                                    )
                                }
                            }
                        }
                    }
                }
            }

            dialog!!.window!!.apply {
                requestFeature(Window.FEATURE_NO_TITLE)
                setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))
            }
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
}


