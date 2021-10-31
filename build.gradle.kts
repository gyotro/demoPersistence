import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.5.5"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.5.31"
	kotlin("plugin.spring") version "1.5.31"
	kotlin("plugin.jpa") version "1.5.31"
}

group = "com.sap"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
	mavenCentral()
	maven("https://jitpack.io")
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-actuator:2.5.5")
	implementation("org.springframework.boot:spring-boot-starter-web:2.5.5")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.0")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	// required library for Swagger
	implementation("org.springdoc:springdoc-openapi-ui:1.5.11")
	// required for Koxon
	implementation("com.beust:klaxon:5.5")

	// to be enabled only with Postgresql up and running
	//runtimeOnly("org.postgresql:postgresql")
	//implementation("org.springframework.boot:spring-boot-starter-data-jpa")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}
// skipping test clases
/*
tasks.withType<Test> {
	useJUnitPlatform()
}
*/