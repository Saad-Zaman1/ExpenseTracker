package com.saad.expensemanager.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.saad.expensemanager.R
import com.saad.expensemanager.databinding.FragmentSignupBinding
import com.saad.expensemanager.repository.Repository
import com.saad.expensemanager.room.DatabaseHelper
import com.saad.expensemanager.viewmodels.ViewModelAuthentication
import com.saad.expensemanager.viewmodels.ViewModelAuthenticationFactory

class SignupFragment : Fragment() {
    private lateinit var binding: FragmentSignupBinding
    private lateinit var viewModelUser: ViewModelAuthentication
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_signup, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dao = DatabaseHelper.getDatabase(requireContext()).expenseDao()
        val userDao = DatabaseHelper.getDatabase(requireContext()).userDao()
        val repository = Repository(dao, userDao)
        viewModelUser = ViewModelProvider(
            this,
            ViewModelAuthenticationFactory(repository)
        )[ViewModelAuthentication::class.java]
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


    }


}