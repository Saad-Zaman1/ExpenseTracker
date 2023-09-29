package com.saad.expensemanager.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.saad.expensemanager.R
import com.saad.expensemanager.databinding.ActivityAuthenticationBinding
import com.saad.expensemanager.fragments.SignupFragment
import com.saad.expensemanager.viewmodels.ViewModelAuthentication

class AuthenticationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthenticationBinding
    private lateinit var viewModel: ViewModelAuthentication
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_authentication)


//        val dao = DatabaseHelper.getDatabase(applicationContext).expenseDao()
//        val userDao = DatabaseHelper.getDatabase(applicationContext).userDao()
//        val repository = Repository(dao, userDao)
//        //.get(ViewModel::class.java)
//        viewModel = ViewModelProvider(
//            this,
//            ViewModelAuthenticationFactory(repository)
//        )[ViewModelAuthentication::class.java]
//
//        binding.viewModel = viewModel
//        binding.lifecycleOwner = this

        replaceFragment(SignupFragment())


    }

    fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
        fragmentTransaction.replace(R.id.frame_layout_authentication, fragment)
        fragmentTransaction.commit()
    }
}