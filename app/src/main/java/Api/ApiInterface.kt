package Api

import models.Course
import models.LoginResponse
import models.RegistractionResponse

package com.example.hello
import ke.co.hello.RegistrationResponse
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST


interface ApiInterface {
    @POST("register")
    fun registerStudent(@Body requestBody: RequestBody): retrofit2.Call<RegistractionResponse>

    @POST("login")
    fun loginStudent(@Body requestBody: RequestBody): okhttp3.Call<LoginResponse>

    @GET("courses")
    fun getCourses(@Header("Authorization") accessToken: String): okhttp3.Call<Course>
}