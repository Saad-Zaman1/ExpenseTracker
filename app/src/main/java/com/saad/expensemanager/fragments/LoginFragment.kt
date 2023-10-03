package com.saad.expensemanager.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.saad.expensemanager.R
import com.saad.expensemanager.activity.MainActivity
import com.saad.expensemanager.databinding.FragmentLoginBinding
import com.saad.expensemanager.utilities.SharedPrefs
import com.saad.expensemanager.viewmodels.ViewModelAuthentication
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val viewModelUser: ViewModelAuthentication by viewModels()

    @Inject
    lateinit var sharedPrefs: SharedPrefs

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.i("FrangmentLogin", "onAttach Method of login fragment")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("FragmentLogin", "onCreate Method of login fragment")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        Log.i("FragmentLogin", " onCreateView Method of login fragment")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i("FragmentLogin", "onViewCreate Method of login fragment")

        //study dependency injection

//        val repository = (requireActivity().application as ExpenseApplication).expenseRepository
//
//        viewModelUser = ViewModelProvider(
//            this, ViewModelAuthenticationFactory(repository, sharedPrefs)
//        )[ViewModelAuthentication::class.java]

        binding.viewModel = viewModelUser
        binding.lifecycleOwner = this

        viewModelUser.errorMessageEmail.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                binding.etEmailLogin.error = it
                return@observe
            }
        }
        viewModelUser.errorMessagePassword.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                binding.etPassLogin.error = it
                return@observe
            }
        }
        viewModelUser.isLoggedin.observe(viewLifecycleOwner) {
            if (it == true) {
                val intent = Intent(requireContext(), MainActivity::class.java)
                startActivity(intent)
                requireActivity().finish()
            }
        }

        viewModelUser.toastMessage.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), "$it", Toast.LENGTH_SHORT).show()
        }

        binding.tvGotosignup.setOnClickListener {
            val transaction = requireActivity()
                .supportFragmentManager
                .beginTransaction()
            transaction.replace(R.id.frame_layout, SignupFragment())

//            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Log.i("FragmentLogin", "onViewStateRestored Method of login fragment")

    }

    override fun onStart() {
        super.onStart()
        Log.i("FragmentLogin", "onStart Method of login fragment")

    }

    override fun onResume() {
        super.onResume()
        Log.i("FragmentLogin", "onResume Method of login fragment")

    }

    override fun onPause() {
        super.onPause()
        Log.i("FragmentLogin", "onPause Method of login fragment")
    }

    override fun onStop() {
        super.onStop()
        Log.i("FragmentLogin", "onStop Method of login fragment")

    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.i("FragmentLogin", "onDestroy Method of login fragment")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("FragmentLogin", "onDestroy Method of login fragment")

    }

    override fun onDetach() {
        super.onDetach()
        Log.i("FragmentLogin", "onDetach Method of login fragment")

    }
}