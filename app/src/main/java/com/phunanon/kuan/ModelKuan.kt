package com.phunanon.kuan

import okhttp3.*
import java.util.Date


class ModelKuan(private val displayMessage: (message: Message) -> Unit) {

    val messages = ArrayList<Message>()
    var modelWebSockets: ModelWebSockets? = null

    init {
        val client = OkHttpClient()
        val request = Request.Builder().url("ws://echo.websocket.org").build()
        modelWebSockets = ModelWebSockets(::ReceiveMessage)
        val ws = client.newWebSocket(request, modelWebSockets)
        //client.dispatcher().executorService().shutdown()
    }

    fun SendMessage(text: String) {
        modelWebSockets?.SendMessage(text)
        val newMessage = Message(Date(), "Patrick", text)
        messages.add(newMessage)
        displayMessage.invoke(newMessage)
    }

    fun ReceiveMessage(text: String) {
        val newMessage = Message(Date(), "Echo server", text)
        messages.add(newMessage)
        displayMessage.invoke(newMessage)
    }
}

//https://howtoprogram.xyz/2016/12/24/websocket-client-example-okhttp/