package com.example.twist.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.twist.view.adapter.ListVideoAdapter
import com.example.twist.model.data.Video
import com.example.twist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var viewPager: ViewPager2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewPager = binding.viewPager2

        val videoList = ArrayList<Video>()

        val videoItem1 = Video()
        videoItem1.videoUrl = "https://mazwai.com/videvo_files/video/free/2014-07/small_watermarked/see-ming_lee--everchanging_cloudscape_preview.webm"
        videoItem1.videoDescription = "video 1 "
        videoList.add(videoItem1)

        val videoItem2 = Video()
        videoItem2.videoUrl = "https://mazwai.com/videvo_files/video/free/2019-09/small_watermarked/190828_07_MarinaBayatNightDrone_UHD_23_preview.webm?v=1160939"
        videoItem2.videoDescription = "video 2"
        videoList.add(videoItem2)

        viewPager.adapter = ListVideoAdapter(videoList)

    }


}