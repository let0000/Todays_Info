package com.jignong.todays_info.Fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.jignong.todays_info.databinding.FragmentHomeBinding
import com.jignong.todays_info.databinding.FragmentWeatherBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jsoup.Jsoup

class WeatherFragment : Fragment() {

    companion object {
        private var TAG = "로그"
        private var mBinding : FragmentWeatherBinding? = null
    }

    lateinit var binding : FragmentWeatherBinding

    private val weatherUrl = "https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=1&ie=utf8&query="

    lateinit var incheon_weather_textview: TextView
    lateinit var seoul_weather_textview: TextView
    lateinit var chuncheon_weather_textview: TextView
    lateinit var gangneung_weather_textview: TextView
    lateinit var ulleungdo_weather_textview: TextView
    lateinit var sejong_weather_textview: TextView
    lateinit var cheongju_weather_textview: TextView
    lateinit var daejeon_weather_textview: TextView
    lateinit var daegu_weather_textview: TextView
    lateinit var pohang_weather_textview: TextView
    lateinit var ulsan_weather_textview: TextView
    lateinit var busan_weather_textview: TextView
    lateinit var jeonju_weather_textview: TextView
    lateinit var gwangju_weather_textview: TextView
    lateinit var mokpo_weather_textview: TextView
    lateinit var jeju_weather_textview: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentWeatherBinding.inflate(inflater, container, false)

        incheon_weather_textview = binding.incheonWeatherTextview
        seoul_weather_textview = binding.seoulWeatherTextview
        chuncheon_weather_textview = binding.chuncheonWeatherTextview
        gangneung_weather_textview = binding.gangneungWeatherTextview
        ulleungdo_weather_textview = binding.ulleungdoWeatherTextview
        sejong_weather_textview = binding.sejongWeatherTextview
        cheongju_weather_textview = binding.cheongjuWeatherTextview
        daejeon_weather_textview = binding.daejeonWeatherTextview
        daegu_weather_textview = binding.daeguWeatherTextview
        pohang_weather_textview = binding.pohangWeatherTextview
        ulsan_weather_textview = binding.ulsanWeatherTextview
        busan_weather_textview = binding.busanWeatherTextview
        jeonju_weather_textview = binding.jeonjuWeatherTextview
        gwangju_weather_textview = binding.gwangjuWeatherTextview
        mokpo_weather_textview = binding.mokpoWeatherTextview
        jeju_weather_textview = binding.jejuWeatherTextview

        getWeather(weatherUrl, "인천", incheon_weather_textview)
        getWeather(weatherUrl, "서울", seoul_weather_textview)
        getWeather(weatherUrl, "춘천", chuncheon_weather_textview)
        getWeather(weatherUrl, "강릉", gangneung_weather_textview)
        getWeather(weatherUrl, "울릉도", ulleungdo_weather_textview)
        getWeather(weatherUrl, "세종", sejong_weather_textview)
        getWeather(weatherUrl, "청주", cheongju_weather_textview)
        getWeather(weatherUrl, "대전", daejeon_weather_textview)
        getWeather(weatherUrl, "대구", daegu_weather_textview)
        getWeather(weatherUrl, "포항", pohang_weather_textview)
        getWeather(weatherUrl, "울산", ulsan_weather_textview)
        getWeather(weatherUrl, "부산", busan_weather_textview)
        getWeather(weatherUrl, "전주", jeonju_weather_textview)
        getWeather(weatherUrl, "광주", gwangju_weather_textview)
        getWeather(weatherUrl, "목포", mokpo_weather_textview)
        getWeather(weatherUrl, "제주", jeju_weather_textview)

        mBinding = binding

        return mBinding?.root
    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }

    private fun getWeather(Url : String, city : String, weather: TextView){
        CoroutineScope(Dispatchers.IO).launch {
            val doc = Jsoup.connect(Url + city + "날씨").get()
            val temple = doc.select(".temperature_text")
            val tem = temple.get(0).text().substring(5)
            val comparison = doc.select("#main_pack > section.sc_new.cs_weather_new._cs_weather > div._tab_flicking > div.content_wrap > div.open > div:nth-child(1) > div > div.weather_info > div > div.temperature_info > p")
            val summaryarray = comparison.text().split(" ")

            CoroutineScope(Dispatchers.Main).launch {
                weather.text = "$city \n $tem \n ${summaryarray[3]}"
            }
        }
    }

}