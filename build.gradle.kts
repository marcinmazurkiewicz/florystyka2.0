import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("groovy")
	id("org.springframework.boot") version "3.1.1"
	id("io.spring.dependency-management") version "1.1.0"
	kotlin("jvm") version "1.8.22"
	kotlin("plugin.spring") version "1.8.22"
	kotlin("plugin.jpa") version "1.8.22"
	kotlin("plugin.allopen") version "1.8.22"
}

group = "io.mazurkiewicz"
version = "4.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

allOpen {
	annotation("jakarta.persistence.Entity")
	annotation("jakarta.persistence.Embeddable")
	annotation("jakarta.persistence.MappedSuperclass")
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.cloud:spring-cloud-starter-bootstrap:4.0.3")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.15.1")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("com.google.guava:guava:31.1-jre")
	implementation("org.thymeleaf:thymeleaf:3.0.15.RELEASE")
	implementation("org.xhtmlrenderer:flying-saucer-pdf:9.1.22")
	implementation("commons-beanutils:commons-beanutils:1.9.4")
	implementation("org.liquibase:liquibase-core")
	runtimeOnly("io.micrometer:micrometer-registry-prometheus")
	runtimeOnly("org.postgresql:postgresql")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.apache.groovy:groovy-all:4.0.13")
	testImplementation("org.spockframework:spock-core:2.4-M1-groovy-4.0")
	testImplementation("org.spockframework:spock-spring:2.4-M1-groovy-4.0")
	testImplementation("com.playtika.testcontainers:embedded-postgresql:3.0.0-RC8")
}

dependencyManagement {
	imports {
		mavenBom("org.testcontainers:testcontainers-bom:1.17.2")
	}
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
	environment("SPRING_PROFILES_ACTIVE", "test")
}