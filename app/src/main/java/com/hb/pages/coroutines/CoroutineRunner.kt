package com.hb.pages.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

typealias Runner = (CoroutineContext, suspend CoroutineScope.() -> Unit) -> Job

class CoroutineRunner private constructor() {

    var runner: Runner? = null

    val defaultRunner: Runner

    init {
        defaultRunner = { coroutineContext, action ->
            CoroutineScope(coroutineContext).launch(block = action)
        }
    }

    companion object {
        var instance: CoroutineRunner = CoroutineRunner()
    }
}

fun launch(coroutineContext: CoroutineContext, action: suspend CoroutineScope.() -> Unit) =
    with(CoroutineRunner.instance) {
        runner?.invoke(coroutineContext, action) ?: defaultRunner(coroutineContext, action)
    }