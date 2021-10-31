package com.sap.demoPersistence.restController

import com.sap.demoPersistence.http.RestClientEdge
import com.sap.demoPersistence.http.RestClientHelper
import com.sap.demoPersistence.utils.ConfigInitializer
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/persistence")
class RestController {

    @Autowired
    lateinit var config: ConfigInitializer

    @Autowired
    lateinit var restClient: RestClientHelper

    @Autowired
    lateinit var restClientEdge: RestClientEdge

    @GetMapping("/checkContainer")
    fun testContainers() : String
    {
        return "Container Up and Running"
    }
    @GetMapping("/edgePort", produces = ["application/json"])
    fun getEdgePort() : String
    {
        return "Edge Port: ${config.edgePort}"
    }

    @GetMapping("/edgeParameters", produces = ["application/json"])
    fun getEdgeParameters() : Map<String, Any?>
    {
        config.apply {
            return mapOf(
                "edgePort" to edgePort,
                "tokenUri" to tokenUri,
                "authorizationGrantType" to authorizationGrantType,
                "deviceConnectivityUrlUnproxed" to deviceConnectivityUrlUnproxed,
                "Bindings" to bindings
            )
        }
    }

    @GetMapping("/getAllEnv", produces = ["application/json"])
    fun getAllEnv(): MutableMap<String, String> = config.allEnvs

    @PostMapping("/measures/{deviceAlternateId}")
    fun measures(@RequestBody body: String, @PathVariable(required = true) deviceAlternateId : String): ResponseEntity<String>
    {

        val edgeUrl = restClientEdge.getEdgeFromBindings()
        val finalUrl = "$edgeUrl/measures/$deviceAlternateId"

        return restClient.invokePost(body, finalUrl)
    }
    companion object {
        var Logger = LoggerFactory.getLogger(RestController::class.java)
    }

}