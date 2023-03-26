package com.joseph.moviesmultiplatformapp.domain.utils

internal interface Mapper<From, To> {

    fun map(from: From): To
}