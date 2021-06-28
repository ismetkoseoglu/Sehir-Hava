package com.example.sehirhava

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.recycler_row.view.*
import org.json.JSONObject

class Adaptor(var dizi:Array<String>) :RecyclerView.Adapter<Adaptor.TutucuVH>(){
    class TutucuVH(itemView:View):RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TutucuVH {
        val layout=LayoutInflater.from(parent?.context).inflate(R.layout.recycler_row,parent,false)
        return TutucuVH(layout)
    }

    override fun onBindViewHolder(holder: TutucuVH, position: Int) {
        holder.itemView.text_sehir.text=dizi.get(position)
        var a=""
        holder.itemView.setOnClickListener {
            a=dizi.get(position)
            var intent=Intent(holder.itemView.context.applicationContext,IkinciSekme::class.java)
            intent.putExtra("sehir_isim",a)
            holder.itemView.context.startActivity(intent)
        }

    }
    override fun getItemCount(): Int {
        return  dizi.size
    }



}