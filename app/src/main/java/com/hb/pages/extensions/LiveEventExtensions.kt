package com.hb.pages.extensions

import com.hadilq.liveevent.LiveEvent

fun LiveEvent<Unit>.trigger() {
    postValue(Unit)
}