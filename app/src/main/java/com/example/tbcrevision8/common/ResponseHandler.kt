package com.example.tbcrevision8.common

sealed class ResponseHandler<T>( val isLoading:Boolean){
    class Success<T> (val response:T):ResponseHandler<T>(false)
    class Error<T> ( val errorResponse:String):ResponseHandler<T>(false)
    class InProcess<T> ():ResponseHandler<T>(true)
}