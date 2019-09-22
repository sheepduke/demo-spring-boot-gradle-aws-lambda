import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm").version("1.3.50")
}

repositories {
    jcenter()
}

dependencies {
    // Use Kotlin standard library.
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // Use Spring Boot framework.
    implementation("org.springframework.boot:spring-boot-starter-web:2.1.8.RELEASE")
    // Wrapper for Spring Boot.
    implementation("com.amazonaws.serverless:aws-serverless-java-container-spring:1.3.2")

    // Use Jackson with Kotlin.
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.9.+")
    // Use YAML file as application properties.
    implementation("org.yaml:snakeyaml:1.24")

    testImplementation("junit:junit")
}

object Constants {
    const val mainClassName = "com.sheepduke.demo.hello.ApplicationKt"
    const val appName = "demo-hello"
    const val appVersion = "1.0.0"
}

// Define task for building Zip file compatible with Amazon Lambda.
tasks.register<Zip>("buildZip") {
    archiveBaseName.set(Constants.appName)
    archiveVersion.set(Constants.appVersion)

    from(tasks.getByName("compileKotlin"))
    from(tasks.getByName("processResources"))
    into("lib") {
        from(configurations.compileClasspath) {
            exclude("tomcat-embed-*")
        }
    }
}

tasks.getByName("assemble").dependsOn("buildZip")

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}
