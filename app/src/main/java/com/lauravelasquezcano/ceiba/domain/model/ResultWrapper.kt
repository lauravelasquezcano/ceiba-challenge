package com.lauravelasquezcano.ceiba.domain.model

sealed class ResultWrapper<out T> {
    data class Success<out T>(val data: T) : ResultWrapper<T>()
    data class GenericError<out T>(val code : Int?, val message: String?) : ResultWrapper<T>()
}
