package com.hb.pages.extensions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hb.pages.coroutines.launch

fun <T : ViewModel> T.launch(action: suspend T.() -> Unit) =
    launch(viewModelScope.coroutineContext) { action() }