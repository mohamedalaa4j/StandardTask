package com.example.standardtask.utilities

import android.app.Dialog
import android.content.Context
import com.example.standardtask.R

object Utilities {

    private var progressbarDialog: Dialog? = null

     fun showProgressDialog(context: Context) {
        progressbarDialog = Dialog(context)
        progressbarDialog!!.setCancelable(false)
        progressbarDialog?.setContentView(R.layout.progress_dialog)
        progressbarDialog?.show()
    }

     fun cancelProgressDialog() {
        if (this.progressbarDialog != null) {
            progressbarDialog?.dismiss()
            progressbarDialog = null
        }
    }

}