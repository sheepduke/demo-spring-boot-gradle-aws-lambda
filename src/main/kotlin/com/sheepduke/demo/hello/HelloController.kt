package com.sheepduke.demo.hello

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController("/")
@Suppress("unused")
class HelloController(
        @Autowired private val service: HelloService,
        private val config: Configuration) {

    @PostMapping("/hello")
    fun hello(@RequestBody request: HelloRequest): HelloResponse {
        // Print values read from application.yaml file.
        println("Port: ${config.port}")
        println("Environments:")
        config.environments.forEach { println("- $it") }

        // Check user input.
        if (request.name.isEmpty()) {
            throw BadRequestException()
        }
        return HelloResponse(service.makeHelloMessage(request.name))
    }
}

@ResponseStatus(HttpStatus.BAD_REQUEST)
class BadRequestException : RuntimeException()

data class HelloResponse(
        val message: String)

data class HelloRequest(
        var name: String)