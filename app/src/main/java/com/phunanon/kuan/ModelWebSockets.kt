package com.phunanon.kuan

import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString

class ModelWebSockets(private val receiveString: (message: String) -> Unit) : WebSocketListener() {

    private val NORMAL_CLOSURE_STATUS = 1000
    private var ws: WebSocket? = null

    override fun onOpen(webSocket: WebSocket, response: Response?) {
        ws = webSocket
        //webSocket.close(NORMAL_CLOSURE_STATUS, "Goodbye!")
    }

    override fun onMessage(webSocket: WebSocket?, text: String?) {
        receiveString(text!!)
    }

    override fun onMessage(webSocket: WebSocket?, bytes: ByteString) {
        receiveString(bytes.hex())
    }

    override fun onClosing(webSocket: WebSocket, code: Int, reason: String?) {
        webSocket.close(NORMAL_CLOSURE_STATUS, null)
        receiveString("Closing: $code $reason")
    }

    override fun onFailure(webSocket: WebSocket?, t: Throwable, response: Response?) {
        receiveString(t.message!!)
    }

    public fun SendMessage (message: String) = ws?.send(message)
}