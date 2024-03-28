package com.example.twist.model.data.network.remote.retrofit

import androidx.annotation.RawRes
import com.example.twist.model.data.network.remote.response.LoginResponse
import com.example.twist.model.data.network.remote.response.RegisterResponse
import com.example.twist.model.data.params.LoginRequest
import com.example.twist.model.data.params.RegisterRequest
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {


    // Register Request

    @POST("auth/register")
    suspend fun register (
        @Body registerRequest: RegisterRequest
    ): RegisterResponse

    // Login Request
    @POST("auth/login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ) : LoginResponse



    @GET("posts")
    suspend fun listVideo(
        @Query("page") page: Int = 1,
        @Query("limit") limit : Int = 10
    )


    @GET("posts/{id}")
    fun detailStories(
        @Path("id") id: String
    )

    @GET("posts/{id}")
    fun comment(

    )




}