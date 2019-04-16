package nah.prayer.nahdialoglib.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Gravity
import android.view.Window
import android.view.WindowManager


/**
 * Created by S on 2019-04-02.
 */

abstract class BaseDialog internal constructor(context: Context) :
    Dialog(context, android.R.style.Theme_Translucent_NoTitleBar) {

    internal abstract val layoutResource: Int
    internal abstract fun setLayout()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val window = window

        val lpWindow = WindowManager.LayoutParams()
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND
        lpWindow.dimAmount = 0.8f
        window!!.attributes = lpWindow

        setContentView(layoutResource)

        val dm = context.resources.displayMetrics
        val width = (dm.widthPixels * 0.8).toInt()
        window.attributes.width = width
        window.setGravity(Gravity.CENTER)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)

        setLayout()

    }

}
