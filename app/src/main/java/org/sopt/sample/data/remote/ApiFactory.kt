package org.sopt.sample.data.remote

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

object ApiFactory {
    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://3.39.169.52:3000/")
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType())) //JSON TO KOTLIN, kotlinx-serialization-converter
            .build()
    }

    //::class.java => reflection으로 클래스의 모든 정보를 볼 수 있다. but, 부하가 심함.
    inline fun <reified T> create(): T = retrofit.create<T>(T::class.java)
}
//부하의 최소화를 위해 객체를 전체 프로젝트에서 단 한번만 만든다.
object ServicePool{
    val authService = ApiFactory.create<AuthService>()
}