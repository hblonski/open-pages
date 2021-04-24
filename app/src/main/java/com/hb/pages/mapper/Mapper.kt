package com.hb.pages.mapper

interface Mapper<F, T> {
    fun map(from: F): T
    fun unmap(from: T): F
}