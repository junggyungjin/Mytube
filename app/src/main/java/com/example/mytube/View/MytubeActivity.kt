package com.example.mytube.View

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.example.mytube.Data.Youtube
import com.example.mytube.Network.MasterApplication
import com.example.mytube.R
import com.example.mytube.databinding.ActivityMytubeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MytubeActivity : AppCompatActivity() {
    lateinit var binding: ActivityMytubeBinding
    lateinit var glide: RequestManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMytubeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        (application as MasterApplication).service.getYoutubeList().enqueue(
            object : Callback<ArrayList<Youtube>>{
                override fun onResponse(
                    call: Call<ArrayList<Youtube>>,
                    response: Response<ArrayList<Youtube>>
                ) {
                    if (response.isSuccessful) {
                        glide = Glide.with(this@MytubeActivity)
                        val youtubeList = response.body()
                        val adapter = MytubeAdapter(
                            youtubeList!!,
                            LayoutInflater.from(this@MytubeActivity),
                            glide,
                            this@MytubeActivity
                        )
                        binding.mytubeRecycler.adapter = adapter
                        binding.mytubeRecycler.layoutManager = LinearLayoutManager(this@MytubeActivity)
                    }
                }

                override fun onFailure(call: Call<ArrayList<Youtube>>, t: Throwable) {

                }
            }
        )
    }
}

class MytubeAdapter(
    val mytubeList : ArrayList<Youtube>,
    val inflater : LayoutInflater,
    val glide : RequestManager,
    val activity: Activity
): RecyclerView.Adapter<MytubeAdapter.ViewHolder>() {

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val title : TextView
        val thumbnail : ImageView
        val content : TextView

        init {
            title = itemView.findViewById(R.id.mytube_title)
            thumbnail = itemView.findViewById(R.id.mytube_thumbnail)
            content = itemView.findViewById(R.id.mytube_content)

            itemView.setOnClickListener {
                val position : Int = adapterPosition
                val intent = Intent(activity, MytubeDetailActivity::class.java)
                intent.putExtra("video_url", mytubeList.get(position).video)
                activity.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.mytube_item_view_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.setText(mytubeList.get(position).title)
        holder.content.setText(mytubeList.get(position).content)
        glide.load(mytubeList.get(position).thumbnail).into(holder.thumbnail)
    }

    override fun getItemCount(): Int {
        return mytubeList.size
    }
}