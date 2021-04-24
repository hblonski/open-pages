package com.hb.pages.viewmodel

import androidx.lifecycle.ViewModel
import com.hadilq.liveevent.LiveEvent
import com.hb.pages.extensions.trigger

open class BaseViewModel : ViewModel() {
    val backAction: LiveEvent<Unit> = LiveEvent()

    fun onBackPressed() {
        backAction.trigger()
    }
}