package com.joseph.moviesmultiplatformapp.data.remote.api

import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

internal abstract class KtorApi {

    private companion object {
        const val API_KEY = "6e63c2317fbe963d76c3bdc2b785f6d1"
        const val BASE_URL = "https://api.themoviedb.org/"
    }

//    https://api.themoviedb.org/3/movie/popular?page=1&pageSize=6&api_key=6e63c2317fbe963d76c3bdc2b785f6d1
//    https://api.themoviedb.org/movie/popular?api_key=6e63c2317fbe963d76c3bdc2b785f6d1&page=1:

    val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                useAlternativeNames = false
            })
        }
    }

    fun HttpRequestBuilder.pathUrl(path: String) {
        url {
            takeFrom(BASE_URL)
            path("3", path)
            parameter("api_key", API_KEY)
        }
    }
}