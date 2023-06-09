package com.joseph.moviesmultiplatformapp.domain.models

@kotlinx.serialization.Serializable
data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val posterImageUrl: String,
    val releaseDate: String,
) {

    companion object {
        val avatar = Movie(
            title = "Аватар",
            id = 212,
            overview = "Бывший морпех Джейк Салли прикован к инвалидному креслу. Несмотря на немощное тело, Джейк в душе по-прежнему остается воином. Он получает задание совершить путешествие в несколько световых лет к базе землян на планете Пандора, где корпорации добывают редкий минерал, имеющий огромное значение для выхода Земли из энергетического кризиса",
            posterImageUrl = "https://xl.movieposterdb.com/11_08/2009/499549/xl_499549_8e9168cd.jpg",
            releaseDate = "20_20_2002"
        )
    }
}