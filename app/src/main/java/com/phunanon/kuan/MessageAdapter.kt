package com.phunanon.kuan

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class MessageAdapter (private val messages: ArrayList<Message>) : RecyclerView.Adapter<MessageAdapter.ViewHolder>()
{
    private val VIEW_SENT = 0
    private val VIEW_RECEIVED = 1

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val layout: Int = if (viewType == VIEW_SENT) R.layout.message_sent else R.layout.message_received
        return ViewHolder(
            LayoutInflater.from(viewGroup.context).inflate(
                layout,
                viewGroup,
                false
            )
        )
    }

    override fun getItemViewType(i: Int): Int = if (messages[i].name == "Patrick") VIEW_SENT else VIEW_RECEIVED
    override fun getItemCount(): Int = messages.size

    override fun onBindViewHolder(holder: ViewHolder, i: Int) {
        val message: Message = messages[i]
        holder.tvName.text = message.name
        holder.tvMessage.text = message.text
    }

    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName = itemView.findViewById(R.id.tv_name) as TextView
        val tvMessage = itemView.findViewById(R.id.tv_message) as TextView
    }
}