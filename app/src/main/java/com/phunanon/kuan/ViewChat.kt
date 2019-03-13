package com.phunanon.kuan

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_main.*

class ViewChat : AppCompatActivity() {

    val model = ModelKuan(::displayMessage)
    val messageAdapter: MessageAdapter = MessageAdapter(model.messages)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv_messages.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        rv_messages.adapter = messageAdapter

        btn_send.setOnClickListener {
            val message = et_user_message.text.toString();
            if (message != "") {
                model.SendMessage(message);
                et_user_message.text.clear();
            }
        }
    }

    fun displayMessage (message: Message)
    {
        val numItems = rv_messages.adapter?.itemCount
        if (numItems!! > 0) rv_messages.smoothScrollToPosition(numItems - 1)
        rv_messages?.post { messageAdapter.notifyDataSetChanged() }
    }
}
