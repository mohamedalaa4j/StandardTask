package com.example.standardtask.utilities

import android.app.Dialog
import android.content.Context
import com.example.standardtask.R
import java.util.*

object Utilities {
    private lateinit var progressbarDialog: Dialog

    fun showProgressDialog(context: Context) {
        progressbarDialog = Dialog(context)
        progressbarDialog.setCancelable(false)
        progressbarDialog.setContentView(R.layout.progress_dialog)
        progressbarDialog.show()
    }

    fun cancelProgressDialog() {
        if (progressbarDialog.isShowing) {
            progressbarDialog.dismiss()
        }
    }

    fun deviceLanguage(): String {

        return Locale.getDefault().language
    }

}