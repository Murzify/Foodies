package com.murzify.foodies.core.network.impl

import com.murzify.foodies.core.network.api.WorkTestServer
import com.murzify.foodies.core.network.model.CategoryDto
import com.murzify.foodies.core.network.model.ProductDto
import com.murzify.foodies.core.network.model.TagDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Inject

class WorkTestServerImpl @Inject constructor() : WorkTestServer {

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
            url("https://anika1d.github.io/WorkTestServer/")
        }
    }

    override suspend fun products(): List<ProductDto> = httpClient.get("Products.json").body()

    override suspend fun categories(): List<CategoryDto> = httpClient.get("Categories.json").body()

    override suspend fun tags(): List<TagDto> = httpClient.get("Tags.json").body()
}