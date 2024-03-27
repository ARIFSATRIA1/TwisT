package com.example.twist.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.VideoView
import androidx.recyclerview.widget.RecyclerView
import com.example.twist.model.data.Video
import com.example.twist.R

class ListVideoAdapter(private val listVideo: ArrayList<Video>): RecyclerView.Adapter<ListVideoAdapter.ListViewHolder>() {


    class ListViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

         private val videoView: VideoView = itemView.findViewById(R.id.videoViewPlay)
        @SuppressLint("ClickableViewAccessibility")
        fun bindVideo (video: Video) {
            itemView.findViewById<TextView>(R.id.description).apply {
                setText(video.videoDescription)
            }


            videoView.apply {
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

                setVideoPath(video.videoUrl)

                setOnClickListener {
                    if (videoView.isPlaying) {
                        videoView.pause()
                    } else {
                        videoView.start()
                    }
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



