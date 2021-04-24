package com.hb.pages.activity

import android.app.Activity
import android.content.Intent
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import com.hb.pages.extensions.cancel

open class BaseActivity : AppCompatActivity() {

    protected fun finalize(data: Pair<String, Parcelable>? = null) {
        val intent = Intent()
        data?.let {
            intent.putExtra(data.first, data.second)
        }
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    override fun onBackPressed() {
        cancel()
    }
}