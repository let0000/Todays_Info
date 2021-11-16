package com.jignong.todays_info.Fragments

import android.R
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.jignong.todays_info.databinding.FragmentHomeBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jsoup.Jsoup

class HomeFragment : Fragment() {

    companion object {
        private var TAG = "로그"
        private var mBinding : FragmentHomeBinding? = null
    }

    private val weatherUrl = "https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=1&ie=utf8&query="
    private val covid19Url = "https://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&query=%EC%BD%94%EB%A1%9C%EB%82%98+%ED%99%95%EC%A7%84%EC%9E%90&oquery=%EC%A7%80%EC%97%AD+%EB%82%A0%EC%94%A8&tqi=hS%2FEPdprvhGss5t4i6Nssssss6C-509210"
    private val citycovidUrl = "http://ncov.mohw.go.kr/"

    private val cityarray = arrayOfNulls<String>(18)

    lateinit var weather_textview: TextView
    lateinit var totalcovid_textview: TextView
    lateinit var covid_textview: TextView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentHomeBinding.inflate(inflater, container, false)

        weather_textview = binding.weatherTextview
        totalcovid_textview = binding.totalcoivdTextview
        covid_textview = binding.coivdTextview

        //날씨 스피너
        var weather_city = resources.getStringArray(R.array.weather_city)
        var weather_adapter = ArrayAdapter<String>(requireContext(), R.layout.simple_list_item_1, weather_city)
        binding.weatherSpinner.adapter = weather_adapter
        binding.weatherSpinner.setSelection(0)
        binding.weatherSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (position) {
                    0 -> {
                        getWeather(weatherUrl, "서울", weather_textview)
                    }
                    1 -> {
                        getWeather(weatherUrl, "부산", weather_textview)
                    }
                    2 -> {
                        getWeather(weatherUrl, "대구", weather_textview)
                    }
                    3 -> {
                        getWeather(weatherUrl, "인천", weather_textview)
                    }
                    4 -> {
                        getWeather(weatherUrl, "광주", weather_textview)
                    }
                    5 -> {
                        getWeather(weatherUrl, "대전", weather_textview)
                    }
                    6 -> {
                        getWeather(weatherUrl, "울산", weather_textview)
                    }
                    7 -> {
                        getWeather(weatherUrl, "세종", weather_textview)
                    }
                    8 -> {
                        getWeather(weatherUrl, "경기", weather_textview)
                    }
                    9 -> {
                        getWeather(weatherUrl, "강원", weather_textview)
                    }
                    10 -> {
                        getWeather(weatherUrl, "충북", weather_textview)
                    }
                    11 -> {
                        getWeather(weatherUrl, "충남", weather_textview)
                    }
                    12 -> {
                        getWeather(weatherUrl, "전북", weather_textview)
                    }
                    13 -> {
                        getWeather(weatherUrl, "전남", weather_textview)
                    }
                    14 -> {
                        getWeather(weatherUrl, "경북", weather_textview)
                    }
                    15 -> {
                        getWeather(weatherUrl, "경남", weather_textview)
                    }
                    16 -> {
                        getWeather(weatherUrl, "제주", weather_textview)
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        mBinding = binding

        return mBinding?.root
    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }

    private fun getWeather(Url : String, city : String, textView: TextView){
        CoroutineScope(Dispatchers.IO).launch {
            val doc = Jsoup.connect(Url + city + "날씨").get()
            val temple = doc.select(".temperature_text")
            val tem = temple.get(0).text().substring(5)

            CoroutineScope(Dispatchers.Main).launch {
                textView.text = "$city 날씨는 : $tem"
                //Log.d(TAG, "select : $temple")
                Log.d(TAG, "$city : $tem")
            }
        }
    }

}