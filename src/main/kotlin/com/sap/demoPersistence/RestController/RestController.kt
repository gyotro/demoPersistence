package com.sap.demoPersistence.RestController

import com.sap.demoPersistence.utils.Configuration
import org.apache.tomcat.util.http.parser.MediaType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.http.ResponseEntity
import org.springframework.util.MimeTypeUtils.APPLICATION_JSON
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/persistence")
class RestController {

    @Autowired
    lateinit var config: Configuration

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

}