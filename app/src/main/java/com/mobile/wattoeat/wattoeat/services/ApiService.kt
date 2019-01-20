package com.mobile.wattoeat.wattoeat.services

import com.mobile.wattoeat.wattoeat.options.Option
import io.reactivex.Single
import retrofit2.http.POST

interface ApiService {
    companion object {
        fun create(): ApiService {
            val retrofit = makeRetrofit()
            return retrofit.create(ApiService::class.java)
        }

        val apiService by lazy {
            create()
        }
    }

    @POST("/options/new.json")
    fun postOption(): Single<Option>
}