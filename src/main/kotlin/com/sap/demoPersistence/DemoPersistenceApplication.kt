package com.sap.demoPersistence

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import org.slf4j.LoggerFactory.getLogger
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@OpenAPIDefinition(info = Info(title = "Kotlin Postgres Test", version = "1.0", description = "Test Service with Swagger"))

/*
 	Per visualizzare la documentazione delle API:
 	- concatenare alla porta la URI: /v3/api-docs per vedere la documentazione in JSON
 	- concatenare alla porta la URI: /v3/api-docs.yaml per scaricare documentazione in YAML
 	- concatenare alla porta la URI: /swagger-ui.html per vedere la UI dello swagger
 	- è possibile personalizzare la URI dell'api inserendo questa parte nel file yml properties
		springdoc:
		  api-docs:
			path: /test-custom
	- al momento Swagger 3 no funziona con gli endpoint funzionali
 */

// run gradle application with ./gradlew bootRun

class DemoPersistenceApplication {
	companion object {
		var Logger = getLogger(DemoPersistenceApplication::class.java)
	}
}

// Starting Application
fun main(args: Array<String>) {
	runApplication<DemoPersistenceApplication>(*args)
	DemoPersistenceApplication.Logger.info("Spring Boot Application Started!")
}
