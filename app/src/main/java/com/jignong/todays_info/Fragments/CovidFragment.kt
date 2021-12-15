package com.jignong.todays_info.Fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.jignong.todays_info.databinding.FragmentCovidBinding
import com.jignong.todays_info.databinding.FragmentWeatherBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jsoup.Jsoup

class CovidFragment : Fragment() {

    companion object {
        private var TAG = "로그"
        private var mBinding : FragmentCovidBinding? = null
    }

    lateinit var binding: FragmentCovidBinding

    private val covid19Url = "https://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&query=%EC%BD%94%EB%A1%9C%EB%82%98+%ED%99%95%EC%A7%84%EC%9E%90&oquery=%EC%A7%80%EC%97%AD+%EB%82%A0%EC%94%A8&tqi=hS%2FEPdprvhGss5t4i6Nssssss6C-509210"

    lateinit var incheon_covid_textview: TextView
    lateinit var seoul_covid_textview: TextView
    lateinit var gyeonggi_covid_textview: TextView
    lateinit var gangwon_covid_textview: TextView
    lateinit var quarantine_covid_textview: TextView
    lateinit var chungnam_covid_textview: TextView
    lateinit var sejong_covid_textview: TextView
    lateinit var chungbuk_covid_textview: TextView
    lateinit var daejeon_covid_textview: TextView
    lateinit var daegu_covid_textview: TextView
    lateinit var gyeongbuk_covid_textview: TextView
    lateinit var ulsan_covid_textview: TextView
    lateinit var kyungnam_covid_textview: TextView
    lateinit var busan_covid_textview: TextView
    lateinit var jeonbuk_covid_textview: TextView
    lateinit var jeonnam_covid_textview: TextView
    lateinit var gwangju_covid_textview: TextView
    lateinit var jeju_covid_textview: TextView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCovidBinding.inflate(inflater, container, false)

        incheon_covid_textview = binding.incheonCovidTextview
        seoul_covid_textview = binding.seoulCovidTextview
        gyeonggi_covid_textview = binding.gyeonggiCovidTextview
        gangwon_covid_textview = binding.gangwonCovidTextview
        quarantine_covid_textview = binding. quarantineCovidTextview
        chungnam_covid_textview = binding.chungnamCovidTextview
        sejong_covid_textview = binding.sejongCovidTextview
        chungbuk_covid_textview = binding.chungbukCovidTextview
        daejeon_covid_textview = binding.daejeonCovidTextview
        daegu_covid_textview = binding.daeguCovidTextview
        gyeongbuk_covid_textview = binding.gyeongbukCovidTextview
        ulsan_covid_textview = binding.ulsanCovidTextview
        kyungnam_covid_textview = binding.kyungnamCovidTextview
        busan_covid_textview = binding.busanCovidTextview
        jeonbuk_covid_textview = binding.jeonbukCovidTextview
        jeonnam_covid_textview = binding.jeonnamCovidTextview
        gwangju_covid_textview = binding.gwangjuCovidTextview
        jeju_covid_textview = binding.jejuCovidTextview

        getCityCovid(covid19Url, incheon_covid_textview, 1,3, "인천")
        getCityCovid(covid19Url, seoul_covid_textview, 1,1, "서울")
        getCityCovid(covid19Url, gyeonggi_covid_textview, 1,5, "경기")
        getCityCovid(covid19Url, gangwon_covid_textview, 2,1, "강원")
        getCityCovid(covid19Url, quarantine_covid_textview, 2,6, "검역")
        getCityCovid(covid19Url, chungnam_covid_textview, 1,7, "충남")
        getCityCovid(covid19Url, sejong_covid_textview, 3,2, "세종")
        getCityCovid(covid19Url, chungbuk_covid_textview, 2,3, "충북")
        getCityCovid(covid19Url, daejeon_covid_textview, 2,2, "대전")
        getCityCovid(covid19Url, daegu_covid_textview, 1,4, "대구")
        getCityCovid(covid19Url, gyeongbuk_covid_textview, 1,8, "경북")
        getCityCovid(covid19Url, ulsan_covid_textview, 2,7, "울산")
        getCityCovid(covid19Url, kyungnam_covid_textview, 1,6, "경남")
        getCityCovid(covid19Url, busan_covid_textview, 1,5, "부산")
        getCityCovid(covid19Url, jeonbuk_covid_textview, 2,4, "전북")
        getCityCovid(covid19Url, jeonnam_covid_textview, 2,8, "전남")
        getCityCovid(covid19Url, gwangju_covid_textview, 2,5, "광주")
        getCityCovid(covid19Url, jeju_covid_textview, 3,1, "제주")

        mBinding = binding

        return mBinding?.root
    }

    override fun onDestroyView() {
        mBinding = null
        super.onDestroyView()
    }

    private fun getCityCovid(Url : String, textView: TextView, x : Int, y : Int , cityname : String){
        CoroutineScope(Dispatchers.IO).launch {
            val doc = Jsoup.connect(Url).get()
            val city = doc.select("#_cs_production_type > div > div:nth-child(4) > div > div:nth-child(3) > div:nth-child($x) > div > table > tbody > tr:nth-child($y)")
            val citytotal = city.text().split(" ")
            CoroutineScope(Dispatchers.Main).launch {
                textView.text = "$cityname \n${citytotal[1]}\n( + ${citytotal[2]} )"
            }
        }
    }

}