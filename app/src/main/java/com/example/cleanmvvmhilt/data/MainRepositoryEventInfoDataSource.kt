package com.example.cleanmvvmhilt.data

import android.util.Log
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import javax.inject.Inject


//Данный класс хранит в себе логику работы по получению даннх из сети
//Результат работы - Возвращаем серриализованный JSON в виде класса UsefulActivityDto

private const val BASE_URL = "https://www.boredapi.com"

class MainRepositoryEventInfoDataSource @Inject constructor() {

    private lateinit var usefulActivityDto:UsefulActivityDto

    suspend fun loadActivityInfo(): UsefulActivityDto {

        coroutineScope{
            launch{
                //Ловим ошибку в библиотеки Retrofit
                try {
                    val serverResponse = RetrofitInstance.randomEventApi.getEventInfo()
                    val responseBody = serverResponse.body()
                        ?: throw java.lang.Error("error in MainRepositoryEventInfoDataSource.loadActivityInfo(). Скорее всего не включени интернет!")
                    val status = serverResponse.code()

                    usefulActivityDto = responseBody

                    Log.d("123", status.toString())
                    Log.d("123", responseBody.activity)
                    Log.d("123", responseBody.type)

                }
                catch (t:Throwable){
                    throw java.lang.Error("error in MainRepositoryEventInfoDataSource.loadActivityInfo(). Скорее всего не включени интернет!")
                }
            }
        }
        return usefulActivityDto
    }


}

private object RetrofitInstance{
    //Создадим клиен okHttp - с помощью него, мы будем мониторить (в логах) какой именно запрос на сервер мы отправили
    //и что именно нам вернулось.

    //Создаем ИНТЕРСЕПТОР
    val interceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY //Будем выводить в ЛОГ только ТЕЛО ответа
    }

    //Создаем okHttp КЛИЕНТ:
    val okHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

    //Экземпляр Ретрофита
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient) //Передаем КЛИЕНТ для перехвата и вывода ТЕЛА ответа в ЛОГ
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    //Ретрофит сервис:
    val randomEventApi:RandomEventApi = retrofit.create(RandomEventApi::class.java)

}
private interface RandomEventApi{
    @GET("/api/activity")
    suspend fun getEventInfo():Response<UsefulActivityDto>
}