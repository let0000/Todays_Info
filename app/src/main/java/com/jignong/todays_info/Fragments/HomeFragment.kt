package com.jignong.todays_info.Fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jignong.todays_info.MainActivity
import com.jignong.todays_info.MyAdapter
import com.jignong.todays_info.databinding.FragmentHomeBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import com.jignong.todays_info.R as Resource

class HomeFragment : Fragment() {

    companion object {
        private var TAG = "로그"
        private var mBinding : FragmentHomeBinding? = null
    }

    lateinit var binding: FragmentHomeBinding

    private val weatherUrl = "https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=1&ie=utf8&query="
    private val covid19Url = "https://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&query=%EC%BD%94%EB%A1%9C%EB%82%98+%ED%99%95%EC%A7%84%EC%9E%90&oquery=%EC%A7%80%EC%97%AD+%EB%82%A0%EC%94%A8&tqi=hS%2FEPdprvhGss5t4i6Nssssss6C-509210"
    private val newsUrl = "https://news.naver.com/main/home.naver"

    var news : ArrayList<news> = arrayListOf()

    lateinit var weather_textview: TextView
    lateinit var totalcovid_textview: TextView
    lateinit var covid_textview: TextView
    lateinit var news_recyclerview : RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(inflater, container, false)

        weather_textview = binding.weatherTextview
        totalcovid_textview = binding.totalcoivdTextview
        covid_textview = binding.coivdTextview
        news_recyclerview = binding.newsRecyclerview

        //날씨 스피너
        var weather_city = resources.getStringArray(Resource.array.weather_city)
        var weather_adapter = ArrayAdapter<String>(requireContext(), Resource.layout.item_spinner, weather_city)
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

        //코로나 스피너
        var covid_city = resources.getStringArray(Resource.array.covid_city)
        var covid_adapter = ArrayAdapter<String>(requireContext(), Resource.layout.item_spinner, covid_city)
        binding.covidSpinner.adapter = covid_adapter
        binding.covidSpinner.setSelection(0)
        binding.covidSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (position) {
                    0 -> {
                        getCityCovid(covid19Url, covid_textview, 1,1)
                    }
                    1 -> {
                        getCityCovid(covid19Url, covid_textview, 1,5)
                    }
                    2 -> {
                        getCityCovid(covid19Url, covid_textview, 1,4)
                    }
                    3 -> {
                        getCityCovid(covid19Url, covid_textview, 1,3)
                    }
                    4 -> {
                        getCityCovid(covid19Url, covid_textview, 2,6)
                    }
                    5 -> {
                        getCityCovid(covid19Url, covid_textview, 2,2)
                    }
                    6 -> {
                        getCityCovid(covid19Url, covid_textview, 2,7)
                    }
                    7 -> {
                        getCityCovid(covid19Url, covid_textview, 3,2)
                    }
                    8 -> {
                        getCityCovid(covid19Url, covid_textview, 1,2)
                    }
                    9 -> {
                        getCityCovid(covid19Url, covid_textview, 2,3)
                    }
                    10 -> {
                        getCityCovid(covid19Url, covid_textview, 2,1)
                    }
                    11 -> {
                        getCityCovid(covid19Url, covid_textview, 1,7)
                    }
                    12 -> {
                        getCityCovid(covid19Url, covid_textview, 2,5)
                    }
                    13 -> {
                        getCityCovid(covid19Url, covid_textview, 2,8)
                    }
                    14 -> {
                        getCityCovid(covid19Url, covid_textview, 1,8)
                    }
                    15 -> {
                        getCityCovid(covid19Url, covid_textview, 1,6)
                    }
                    16 -> {
                        getCityCovid(covid19Url, covid_textview, 3,1)
                    }
                    17 -> {
                        getCityCovid(covid19Url, covid_textview, 2,4)
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        // 뉴스 카테고리 스피너 추가
        var category = resources.getStringArray(Resource.array.category)
        var news_adapter = ArrayAdapter<String>(requireContext(), Resource.layout.item_spinner, category)
        binding.newsSpinner.adapter = news_adapter
        binding.newsSpinner.setSelection(0)
        binding.newsSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (position) {
                    0 -> {
                        news.clear()
                        getNews(newsUrl, "politics")
                    }
                    1 -> {
                        news.clear()
                        getNews(newsUrl, "economy")
                    }
                    2 -> {
                        news.clear()
                        getNews(newsUrl, "society")
                    }
                    3 -> {
                        news.clear()
                        getNews(newsUrl, "life")
                    }
                    4 -> {
                        news.clear()
                        getNews(newsUrl, "world")
                    }
                    5 -> {
                        news.clear()
                        getNews(newsUrl, "it")
                    }
                }

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }

        getCovid(covid19Url, totalcovid_textview)

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
            val comparison = doc.select("#main_pack > section.sc_new.cs_weather_new._cs_weather > div._tab_flicking > div.content_wrap > div.open > div:nth-child(1) > div > div.weather_info > div > div.temperature_info > p")
            val summaryarray = comparison.text().split(" ")

            CoroutineScope(Dispatchers.Main).launch {
                textView.text = "기온 $tem / ${summaryarray[3]} \n " +
                        "${summaryarray[0]} ${summaryarray[1]} ${summaryarray[2]}"
                Log.d(TAG, "$summaryarray")
                Log.d(TAG, "$city : $tem")
            }
        }
    }

    private fun getCovid(Url : String , textView: TextView ){
        CoroutineScope(Dispatchers.IO).launch {
            val doc = Jsoup.connect(Url).get()
            val covid = doc.select("#_cs_production_type > div > div.main_tab_area > div > div > ul > li.info_01")
            val total = covid.text().split(" ")
            CoroutineScope(Dispatchers.Main).launch {
                textView.text = "확진환자  :  ${total[1]} ( + ${total[2]} )"
                Log.d(TAG, "$total")
            }
        }
    }

    private fun getCityCovid(Url : String, textView: TextView, x : Int, y : Int){
        CoroutineScope(Dispatchers.IO).launch {
            val doc = Jsoup.connect(Url).get()
            val city = doc.select("#_cs_production_type > div > div:nth-child(4) > div > div:nth-child(3) > div:nth-child($x) > div > table > tbody > tr:nth-child($y)")
            val citytotal = city.text().split(" ")
            CoroutineScope(Dispatchers.Main).launch {
                textView.text = "${citytotal[1]} ( + ${citytotal[2]} )"
                Log.d(TAG, "${citytotal}")
            }
        }
    }

    private fun getNews(Url : String, category : String){
        CoroutineScope(Dispatchers.IO).launch {
            val doc = Jsoup.connect(Url).get()
            val headline = doc.select("#section_$category > div.com_list > div > ul")

            for (i in 0 until 3) {
                val title = headline.select("li a").get(i).text()
                val news_url = headline.select("li a").get(i).attr("href")
                val writing = headline.select("li span[class=writing]").get(i).text()
                val list = news(title, news_url, writing)
                news.add(list)
            }

            CoroutineScope(Dispatchers.Main).launch {
                Log.d(TAG, "뉴스 : ${news[0].title}")
                Log.d(TAG, "링크 : ${news[0].news_url}")
                Log.d(TAG, "언론사 : ${news[0].writing} ")

                news_recyclerview.layoutManager = LinearLayoutManager(requireContext())
                news_recyclerview.adapter = MyAdapter(news)

            }
        }
    }

}

data class news (val title : String, val news_url : String, val writing : String)