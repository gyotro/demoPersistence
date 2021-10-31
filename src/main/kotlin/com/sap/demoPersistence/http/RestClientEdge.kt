package com.sap.demoPersistence.http


import com.sap.demoPersistence.utils.ConfigInitializer
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import java.net.URL

@Configuration
class RestClientEdge {

    @Autowired
    lateinit var configuration: ConfigInitializer

    // Qui si potrebbe restituire un Either<String, String>
    fun getEdgeFromBindings(): String{
        var sURL = configuration.getFromBindings("REST", "sap-iot-gateway")
        var url: URL = URL(sURL)

        var apiRest = "http://" + url.host + ":" + configuration.edgePort

        Logger.info("URL API REST: $apiRest")

        return apiRest
    }

    companion object {
        var Logger = LoggerFactory.getLogger(RestClientEdge::class.java)
    }
}