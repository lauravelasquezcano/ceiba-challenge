package com.lauravelasquezcano.ceiba.app.ui.utils

import android.app.Dialog
import android.content.Context
import android.view.Window
import com.lauravelasquezcano.ceiba.R

class ProgressDialog(context: Context) {

    private var progressDialog: Dialog = Dialog(context, R.style.ProgressDialogStyle)

    init {
        with(progressDialog){
            setCancelable(false)
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setContentView(R.layout.dialog_progress)
            create()
        }
    }

    fun showProgress() {
        if (progressDialog.isShowing.not()) progressDialog.show()
    }

    fun hideProgress() {
        progressDialog.dismiss()
    }
}