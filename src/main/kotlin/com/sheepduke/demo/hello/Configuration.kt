package com.sheepduke.demo.hello

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "server")
open class Configuration {

    lateinit var port: String
    lateinit var environments: List<String>
}