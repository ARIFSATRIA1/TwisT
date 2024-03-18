package com.example

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.VideoView
import androidx.recyclerview.widget.RecyclerView
import com.example.twist.R

class ListVideoAdapter(private val listVideo: ArrayList<Video>): RecyclerView.Adapter<ListVideoAdapter.ListViewHolder>() {


    class ListViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        fun bindVideo (video: Video) {
            itemView.findViewById<TextView>(R.id.description).apply {
                setText(video.videoDescription)
            }
            itemView.findViewById<VideoView>(R.id.videoViewPlay).apply {
                setVideoPath(video.videoUrl)
                setOnPreparedListener { mp ->
                    mp.start()
                    val videoRatio : Float = mp.videoHeight.toFloat() / mp.videoHeight.toFloat()
                    val screenRatio: Float = this.width.toFloat() / this.height.toFloat()
                    val scale = videoRatio/screenRatio

                    if (scale >= 1f) {
                        this.scaleX = scale
                    } else {
                        this.scaleY = scale
                    }
                }
                setOnCompletionListener {
                    it.start()
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.scroll_video_item,parent,false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val currentVideo = listVideo[position]
        holder.bindVideo(currentVideo)
    }

    override fun getItemCount(): Int {
        return listVideo.size
    }

}