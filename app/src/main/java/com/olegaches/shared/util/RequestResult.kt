package com.olegaches.shared.util


sealed class RequestResult<out T> {
    data class Loading<T>(val data: T? = null): RequestResult<T>()
    data class Success<T>(val data: T): RequestResult<T>()
    data class Error<T>(val data: T? = null, val error: Throwable): RequestResult<T>()
}

inline fun <I, O> RequestResult<I>.map(transform: (I) -> O): RequestResult<O> {
    return when(this) {
        is RequestResult.Error -> {
            RequestResult.Error(data = data?.let { transform(it) }, error = this.error)
        }
        is RequestResult.Loading -> RequestResult.Loading(data = data?.let { transform(it) })
        is RequestResult.Success -> RequestResult.Success(data = transform(data))
    }
}