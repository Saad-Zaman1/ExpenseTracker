package com.saad.expensemanager.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.saad.expensemanager.R
import com.saad.expensemanager.databinding.ActivitySplashBinding
import com.saad.expensemanager.utilities.SharedPrefs

class SplashActivity : AppCompatActivity() {
    lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)

        val sp = SharedPrefs(this)

        binding.ivHome?.alpha = 0f
        binding.ivHome?.animate()?.setDuration(1000)?.alpha(1f)?.withEndAction {
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
            finish()
//            if (!sp.getBoolean(GlobalVariables.isLoggedIn)) {
//                val i = Intent(this, AuthenticationActivity::class.java)
//                startActivity(i)
//                finish()
//
//            } else {
//                val i = Intent(this, MainActivity::class.java)
//                startActivity(i)
//                finish()
//
//            }
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }
    }

}