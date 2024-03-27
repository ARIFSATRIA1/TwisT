package com.example.twist.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.twist.databinding.FragmentMainBinding
import com.example.twist.model.data.Video
import com.example.twist.view.adapter.ListVideoAdapter


class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewPager: ViewPager2

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}