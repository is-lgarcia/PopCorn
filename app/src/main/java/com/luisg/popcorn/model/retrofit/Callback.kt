package com.luisg.popcorn.model.retrofit

interface Callback<T> {
    fun onSuccess(result: T?)

    fun onFailed(exception: Exception)
}