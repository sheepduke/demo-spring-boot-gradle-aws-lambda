package com.sheepduke.demo.hello

import com.amazonaws.serverless.proxy.spring.SpringBootLambdaContainerHandler
import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestStreamHandler
import java.io.InputStream
import java.io.OutputStream

@Suppress("unused")
class LambdaHandler : RequestStreamHandler {

    private val handler = SpringBootLambdaContainerHandler.getAwsProxyHandler(
            Application::class.java)

    override fun handleRequest(input: InputStream, output: OutputStream, context: Context) {
        handler.proxyStream(input, output, context)
    }
}
