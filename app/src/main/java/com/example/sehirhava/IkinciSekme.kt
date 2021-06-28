package com.example.sehirhava

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_ikinci_sekme.*
import org.json.JSONObject

class IkinciSekme : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ikinci_sekme)
        var sehir_isim=""
        var intent=intent

        sehir_isim= intent.getStringExtra("sehir_isim").toString()
        hava_durumu(sehir_isim)


    }
    fun hava_durumu(Sehir:String){
        val url="http://api.openweathermap.org/data/2.5/weather?q=$Sehir&appid=af2a15bf60729e1d9748ae66012e6adc&lang=tr&units=metric"
        progressBar.visibility= View.VISIBLE
        val havaDurumObjesi=
            JsonObjectRequest(Request.Method.GET,url,null,object : Response.Listener<JSONObject>{
                override fun onResponse(response: JSONObject?) {
                    var main=response?.getJSONObject("main")
                    var sicaklik=main?.getInt("temp")
                    var weather=response?.getJSONArray("weather")
                    var description_of_weather=weather?.getJSONObject(0)?.getString("description")
                    var temp_for_text=description_of_weather?.toUpperCase()
                    var city=response?.getString("name")
                    var icon=weather?.getJSONObject(0)?.getString("icon")

                    textView.text="Şehir: ${Sehir}"
                    textView_sicaklik.text="Sıcaklık: ${sicaklik}"

                    if(icon!!.get(icon!!.length-1)=='n') {
                        Picasso.get().load("https://img.freepik.com/free-vector/night-view-riverside-with-moon-stars_104785-354.jpg").into(imageView)
                        textView_description.text="Hava Durumu: ${temp_for_text} (Gece)"
                    }else{
                        Picasso.get().load("https://wallpaperaccess.com/full/4025627.jpg\n").into(imageView)
                        textView_description.text="Hava Durumu: ${temp_for_text} (Gündüz)"
                    }
                    progressBar.visibility=View.GONE
                }
            },object : Response.ErrorListener{
                override fun onErrorResponse(error: VolleyError?) {
                }
            })
        Volley.newRequestQueue(this).add(havaDurumObjesi)
    }
}