package com.drabatx.tracksmp.data.network

import com.drabatx.tracksmp.data.model.response.TracksResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText

class ApiService(private val client: HttpClient) {
    suspend fun getTracks(): TracksResponse {
        val response = client.get("/contenedor/feedCM/mx/feedCM_mx.json")
        return response.body()
    }
}