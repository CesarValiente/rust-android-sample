package com.cesarvaliente.rustandroid

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.cesarvaliente.rustandroid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), JNICallback {
    companion object {
        private const val TAG = "MainActivity"
        // Used to load the 'rust' library on application startup.
    }

    init {
        System.loadLibrary("rust")
    }

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
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
