package com.saad.expensemanager.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.saad.expensemanager.R
import com.saad.expensemanager.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
        private lateinit var binding: FragmentLoginBinding
    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.i("FrangmentLogin", "onAttach Method of login fragment")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("FragmentLogin","onCreate Method of login fragment")
    }
        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            binding = DataBindingUtil.inflate(inflater,R.layout.fragment_login,container,false)
            Log.i("FragmentLogin"," onCreateView Method of login fragment")
            return binding.root
        }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i("FragmentLogin","onViewCreate Method of login fragment")
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Log.i("FragmentLogin","onViewStateRestored Method of login fragment")

    }

    override fun onStart() {
        super.onStart()
        Log.i("FragmentLogin","onStart Method of login fragment")

    }

    override fun onResume() {
        super.onResume()
        Log.i("FragmentLogin","onResume Method of login fragment")

    }

    override fun onPause() {
        super.onPause()
        Log.i("FragmentLogin","onPause Method of login fragment")
    }

    override fun onStop() {
        super.onStop()
        Log.i("FragmentLogin","onStop Method of login fragment")

    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.i("FragmentLogin","onDestroy Method of login fragment")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("FragmentLogin","onDestroy Method of login fragment")

    }

    override fun onDetach() {
        super.onDetach()
        Log.i("FragmentLogin","onDetach Method of login fragment")

    }
    }