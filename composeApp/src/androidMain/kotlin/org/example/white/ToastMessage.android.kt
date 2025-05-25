package org.example.white

import android.content.Context
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext

actual fun showToastMsg(msg: String, duration: ToastDurationType) {

    val mContext = MyApp.instance
    mContext?.let {
        when (duration) {
            ToastDurationType.SHORT -> Toast.makeText(it, msg, Toast.LENGTH_SHORT).show()
            ToastDurationType.LONG -> Toast.makeText(it, msg, Toast.LENGTH_LONG).show()
        }
    }

}