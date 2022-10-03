package com.example.standardtask.utilities

import android.app.Dialog
import android.content.Context
import com.example.standardtask.R

class ProgressbarDialog (context: Context){

    private var progressbarDialog : Dialog

    init {
        progressbarDialog = Dialog(context)
        progressbarDialog.setCancelable(false)
        progressbarDialog.setContentView(R.layout.progress_dialog)

    }

    fun show() {
        progressbarDialog.show()
    }

    fun dismiss() {
            progressbarDialog.dismiss()
//            progressbarDialog = null

    }
}