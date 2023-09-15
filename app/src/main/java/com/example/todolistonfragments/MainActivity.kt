package com.example.todolistonfragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.todolistonfragments.databinding.ActivityMainBinding
import com.example.todolistonfragments.utilities.APP_ACTIVITY
import com.example.todolistonfragments.utilities.AppPreferences

class MainActivity : AppCompatActivity() {


    lateinit var mToolbar: Toolbar
    lateinit var navController: NavController

    private var _binding: ActivityMainBinding? = null
    private val mBinding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        APP_ACTIVITY = this
        mToolbar = mBinding.toolbar

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        //mNavController = Navigation.findNavController(this, R.id.nav_host_fragment)
        setSupportActionBar(mToolbar)
        title = "Notes"
        AppPreferences.getPreferences(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}