package com.saad.expensemanager.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.saad.expensemanager.R
import com.saad.expensemanager.activity.MainActivity
import com.saad.expensemanager.databinding.FragmentSignupBinding
import com.saad.expensemanager.utilities.SharedPrefs
import com.saad.expensemanager.viewmodels.ViewModelAuthentication
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class SignupFragment : Fragment() {
    private lateinit var binding: FragmentSignupBinding
    private val viewModelUser: ViewModelAuthentication by viewModels()

    @Inject
    lateinit var sharedPrefs: SharedPrefs

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_signup, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val sharedPrefs = SharedPrefs(requireContext())
//        val repository = (requireActivity().application as ExpenseApplication).expenseRepository
//
//        viewModelUser = ViewModelProvider(
//            this, ViewModelAuthenticationFactory(repository, sharedPrefs)
//        )[ViewModelAuthentication::class.java]
        binding.viewModel = viewModelUser
        binding.lifecycleOwner = this


        viewModelUser.toastMessage.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), "$it", Toast.LENGTH_SHORT).show()
        }


        viewModelUser.errorMessageEmail.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                binding.etEmail.error = it
                return@observe
            }
        }

        viewModelUser.errorMessageName.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                binding.etName.error = it
                return@observe
            }
        }

        viewModelUser.errorMessagePhone.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                binding.etPhone.error = it
                return@observe
            }
        }

        viewModelUser.errorMessagePassword.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                binding.etPass.error = it
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


    }


}