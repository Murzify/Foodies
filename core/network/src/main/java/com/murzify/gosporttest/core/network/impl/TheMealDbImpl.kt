package com.murzify.gosporttest.core.network.impl

import com.murzify.gosporttest.core.network.api.TheMealDb
import com.murzify.gosporttest.core.network.model.CategoriesDto
import com.murzify.gosporttest.core.network.model.MealsDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import java.io.IOException
import java.net.UnknownHostException
import javax.inject.Inject

class TheMealDbImpl @Inject constructor(): TheMealDb {

    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                }
            )
        }
        defaultRequest {
            contentType(ContentType.Application.Json)
            url("https://themealdb.com/api/json/v1/1/")
        }
    }

    override suspend fun search(): MealsDto = httpClient.get("search.php?s").body()

    override suspend fun categories(): CategoriesDto = httpClient.get("categories.php").body()
}