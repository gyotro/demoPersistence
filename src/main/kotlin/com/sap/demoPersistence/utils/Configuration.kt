package com.sap.demoPersistence.utils

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.PropertySource

@org.springframework.context.annotation.Configuration
@PropertySource("classpath:application.yml")
@EnableAutoConfiguration
class Configuration {

    @Autowired
    lateinit var mapper: ObjectMapper

    @Value("\${sapiot.edgePort}")
    var edgePort: Int? = null

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

    var deviceConnectivityUrlUnproxed: String? = null
        set(value) { System.getenv("DEVICE_CONNECTIVITY") }

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

    var bindings: String? = null
        set(value) {System.getenv("SERVICE_BINDINGS")}

}