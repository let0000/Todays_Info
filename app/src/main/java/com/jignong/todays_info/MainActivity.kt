package com.jignong.todays_info

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.jignong.todays_info.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(mBinding.root)

        // 네비게이션을 담는 호스트
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment

        // 네비게이션 컨트롤러
        val navController = navHostFragment.navController

        // 바텀 네이게이션 뷰와 네비게이션을 묶어준다.
        NavigationUI.setupWithNavController(mBinding.bottomNav, navController)

    }
}