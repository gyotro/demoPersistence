package com.sap.demoPersistence.http

import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Configuration
import org.springframework.http.*
import org.springframework.web.client.RestTemplate

@Configuration
class RestClientHelper {

    fun invokePost(jsonPost: String, stringURL: String): ResponseEntity<String> {

        val restTemplate = RestTemplate()
        val headers = HttpHeaders()
        headers.accept = listOf<MediaType>(MediaType.APPLICATION_JSON)
        val request: HttpEntity<String> = HttpEntity<String>(headers)
        val responsePost: ResponseEntity<String> = restTemplate
            .exchange(stringURL, HttpMethod.POST, request, String::class.java)

        Logger.info("Chiamata API REST con status: ${responsePost.statusCode}")
        Logger.info("Chiamata API REST con Body: ${responsePost.body}")

       return responsePost

    }

    companion object {
        var Logger = LoggerFactory.getLogger(RestClientHelper::class.java)
    }
}