package com.mobile.wattoeat.wattoeat.services

import com.mobile.wattoeat.wattoeat.models.UserModel
import retrofit2.http.GET
import io.reactivex.Observable

interface ApiService {
    companion object {
        fun create(): ApiService {
            val retrofit = makeRetrofit()
            return retrofit.create(ApiService::class.java)
        }
    }

    @GET("users")
    fun getUsers(): Observable<List<UserModel>>
}