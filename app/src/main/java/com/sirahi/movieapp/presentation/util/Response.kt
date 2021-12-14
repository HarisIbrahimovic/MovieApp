package com.sirahi.movieapp.presentation.util


sealed class Response<T>(val data:T?,val errorMessage:String?){
    class Success<T>(data: T):Response<T>(data,null)
    class Error<T>(data:T,errorMessage:String?):Response<T>(data,errorMessage)
}
