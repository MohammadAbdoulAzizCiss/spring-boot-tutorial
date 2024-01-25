plugins {
	java
	id("org.springframework.boot") version "3.1.4"
	id("io.spring.dependency-management") version "1.1.3"
}

group = "com.maac"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	// https://mvnrepository.com/artifact/io.micrometer/micrometer-registry-prometheus
	implementation("io.micrometer:micrometer-registry-prometheus:1.12.2")
	// https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-actuator
	implementation("org.springframework.boot:spring-boot-starter-actuator:3.2.2")
	compileOnly("org.projectlombok:lombok")
  	developmentOnly("org.springframework.boot:spring-boot-devtools")
 	runtimeOnly("com.mysql:mysql-connector-j")	
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("com.h2database:h2")





}

tasks.withType<Test> {
	useJUnitPlatform()
}
