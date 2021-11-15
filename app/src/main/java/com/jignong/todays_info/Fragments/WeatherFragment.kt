package com.jignong.todays_info.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jignong.todays_info.databinding.FragmentWeatherBinding

class WeatherFragment : Fragment() {

    private var mBinding : FragmentWeatherBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentWeatherBinding.inflate(inflater, container, false)

        mBinding = binding

        return mBinding?.root
    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }

}