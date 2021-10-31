package com.sap.demoPersistence.utils

import com.beust.klaxon.Parser
import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.PropertySource

@org.springframework.context.annotation.Configuration
@PropertySource("classpath:application.yml")
@EnableAutoConfiguration
class ConfigInitializer {

    @Autowired
    lateinit var mapper: ObjectMapper

    @Value("\${sapiot.edgePort}")
    lateinit var edgePort: String

    //@Value("\${sapiot.ingestionUrl}") --> non si leggono dal yml ma dall'Environment del deploy di K8S
    var ingestionUrl: String? = null

    //@Value("\${tenant.tokenUri}")
    var tokenUri: String? = null
        set(value) {System.getenv("OAUTH2_AUTH")}

    @Value("\${sapiot.authorizationGrantType}")
    lateinit var authorizationGrantType: String

    @Value("\${tenant.clientId}")
    lateinit var clientId: String

    @Value("\${tenant.clientSecret}")
    lateinit var clientSecret: String

    @Value("\${sapiot.deviceConnectivityUrl}")
    lateinit var deviceConnectivityUrl: String

    var deviceConnectivityUrlUnproxed: String = System.getenv("DEVICE_CONNECTIVITY")

    @Value("\${sapiot.gatewayApi}")
    lateinit var gatewayApi: String

    @Value("\${sapiot.deviceApi}")
    lateinit var deviceApi: String

    @Value("\${sapiot.deviceApiCloud}")
    lateinit var deviceApiCloud: String

    @Value("\${sapiot.certificateApi}")
    lateinit var certificateApi: String

    @Value("\${sapiot.sensorAlternateId}")
    lateinit var sensorAlternateId: String

    @Value("\${sapiot.sensorTypeAlternateId}")
    lateinit var sensorTypeAlternateId: String

    lateinit var certDir: String

    var bindings: String = System.getenv("SERVICE_BINDINGS")

    val allEnvs = System.getenv()

    fun getFromBindings(filterParamType : String, filterParamId : String): String?
    {
        val parser: Parser = Parser.default()
        val json: MutableMap<*, *> = parser.parse( this.bindings ) as MutableMap<*, *>
        val param: List<Map<String, String>> = json["bindings"] as List<Map<String, String>>

        Logger.info("Lista dei Bindings: $param")

        val urlRest = param.filter { map -> map["type"].equals(filterParamType) && map["id"].equals(filterParamId) }
            .map { map -> map["url"] }[0]

        Logger.info("REST API URL: $urlRest")

        return urlRest
    }
    companion object {
        var Logger = LoggerFactory.getLogger(ConfigInitializer::class.java)
    }

}