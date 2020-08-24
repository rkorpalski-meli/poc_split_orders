import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  id("org.springframework.boot") version "2.3.3.RELEASE"
  id("io.spring.dependency-management") version "1.0.10.RELEASE"
  kotlin("jvm") version "1.4.0"
  kotlin("plugin.spring") version "1.4.0"
  jacoco
}

group = "com.mercadolibre"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
  mavenCentral()
  maven( "https://repo.spring.io/milestone" )
  maven( "http://git.ml.com:8081/nexus/content/groups/Arquitectura" )
  maven( "http://git.ml.com/nexus/content/repositories/releases" )
  maven( "http://git.ml.com:8081/nexus/content/repositories/MLGrailsPlugins" )
}

dependencies {
  implementation("org.springframework.boot:spring-boot-starter-web") {
    exclude(module = "spring-boot-starter-tomcat")
  }
  implementation("org.springframework.boot:spring-boot-starter")
  implementation("org.springframework.boot:spring-boot-starter-jetty")
  implementation("org.springframework.boot:spring-boot-starter-actuator")
  implementation("org.jetbrains.kotlin:kotlin-reflect")
  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
  implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
  implementation ("com.mercadolibre:conductor:1.0.3")
  implementation("org.jeasy:easy-flows:0.2")
  implementation ("com.mercadolibre.fbm:fbm-rest-utils:4.35.0")

  developmentOnly("org.springframework.boot:spring-boot-devtools")
  testImplementation("org.springframework.boot:spring-boot-starter-test") {
    exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
  }
  testImplementation("io.mockk:mockk:1.9.1")
}

tasks.withType<Test> {
  useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
  kotlinOptions {
    freeCompilerArgs = listOf("-Xjsr305=strict")
    jvmTarget = "11"
  }
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
  jvmTarget = "11"
}

jacoco {
  toolVersion = "0.8.5"
  reportsDir = file("$buildDir/reports/jacoco")
}
