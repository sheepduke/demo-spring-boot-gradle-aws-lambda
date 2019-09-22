package com.sheepduke.demo.hello

import org.springframework.stereotype.Service

@Service
class HelloService {

    fun makeHelloMessage(name: String): String {
        return "Hello, $name"
    }
}