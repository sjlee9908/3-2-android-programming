package com.example.final_project

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //NavController
    private lateinit var navController: NavController
    //앱 상단 바
    private lateinit var appBarConfiguration: AppBarConfiguration

    //onCreate함수
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //앱 바 지정
        setSupportActionBar(app_toolbar)
        //네비게이션 컨트롤러 생성
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(navController.graph)

        //네비게이션 컨트롤러와 앱바 연결
        setupActionBarWithNavController(navController, appBarConfiguration)

        //네비게이션 컨트롤러의 목적지가 변경될때 앱 바의 아이콘 변경
        navController.addOnDestinationChangedListener { _,_,_ ->
            supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back)
        }
    }

    //뒤로가기
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) ||super.onSupportNavigateUp()
    }
}