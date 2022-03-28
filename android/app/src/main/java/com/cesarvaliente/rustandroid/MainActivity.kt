package com.cesarvaliente.rustandroid

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), JNICallback {
    companion object {
        private const val TAG = "MainActivity"
        // Used to load the 'rust' library on application startup.
    }

    init {
        System.loadLibrary("rust")
    }

    var textView: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById<View>(R.id.textview) as TextView
        invokeCallbackViaJNI(this)
    }

    /**
     * A native method that is implemented by the 'rust' native library,
     * which is packaged with this application.
     */
    external fun invokeCallbackViaJNI(callback: JNICallback?)

    override fun callback(string: String?) {
        textView!!.append("KOTLIN: $string\n")
    }
}

interface JNICallback {
    fun callback(string: String?)
}
