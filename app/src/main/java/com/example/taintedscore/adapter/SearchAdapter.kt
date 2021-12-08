package com.example.taintedscore.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.taintedscore.R
import com.example.taintedscore.data.ReverseClickHolder
import com.example.taintedscore.data.SearchResponseData

class SearchAdapter(
    private val receivedList: List<SearchResponseData>,
    private val listener: SearchAdapter.OnItemClickListener
    ) : RecyclerView.Adapter<SearchAdapter.GViewHolder>() {

    inner class GViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val receivedName: TextView = itemView.findViewById(R.id.received_game_name)
        val receivedYear: TextView = itemView.findViewById(R.id.recgame_year)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
                val sRdata = receivedList[position]
                ReverseClickHolder().neededData=sRdata

            }
        }
    }
    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.games_row, parent, false)
        return GViewHolder(view)
    }

    override fun onBindViewHolder(holder: GViewHolder, position: Int) {
        val currentInList = receivedList[position]
        holder.receivedName.text = currentInList.name
        holder.receivedYear.text = currentInList.yearpublished.toString()
    }

    override fun getItemCount() = receivedList.size
}

