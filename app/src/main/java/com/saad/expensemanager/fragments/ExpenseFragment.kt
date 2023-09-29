package com.saad.expensemanager.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.saad.expensemanager.R
import com.saad.expensemanager.adapter.adapter
import com.saad.expensemanager.databinding.FragmentExpenseBinding
import com.saad.expensemanager.repository.Repository
import com.saad.expensemanager.room.DatabaseHelper
import com.saad.expensemanager.viewmodels.ViewModel
import com.saad.expensemanager.viewmodels.ViewModelFactory


class ExpenseFragment : Fragment() {
    private lateinit var binding: FragmentExpenseBinding
    private lateinit var viewModel: ViewModel
    private lateinit var email: String
    private lateinit var type: String
    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.i("Fragmentexpense", "onAttach Method of expense fragment")

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("Fragmentexpense", "onCreate Method of expense fragment")

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Log.i("Fragmentexpense", "onCreateView Method of expense fragment")
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_expense, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i("Fragmentexpense", "onViewCreate Method of expense fragment")
        val dao = DatabaseHelper.getDatabase(requireContext()).expenseDao()
        val userDao = DatabaseHelper.getDatabase(requireContext()).userDao()
        val repository = Repository(dao, userDao)
        //.get(ViewModel::class.java)
        viewModel = ViewModelProvider(this, ViewModelFactory(repository))[ViewModel::class.java]
        email = "saad@gmail.com"
        type = "Expense"

        val adapter = adapter()
        viewModel.getAllExpenseType(email, type).observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        val recycleLayout = binding.recycleIncludeLayoutExpense
        val recyclerView = recycleLayout.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter

    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Log.i("Fragmentexpense", "onViewStateRestored Method of expense fragment")

    }

    override fun onStart() {
        super.onStart()
        Log.i("Fragmentexpense", "onStart Method of expense fragment")

    }

    override fun onResume() {
        super.onResume()
        Log.i("Fragmentexpense", "onResume Method of expense fragment")

    }

    override fun onPause() {
        super.onPause()
        Log.i("Fragmentexpense", "onPause Method of expense fragment")
    }

    override fun onStop() {
        super.onStop()
        Log.i("Fragmentexpense", "onStop Method of expense fragment")

    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.i("Fragmentexpense", "onDestroy Method of expense fragment")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("Fragmentexpense", "onDestroy Method of expense fragment")

    }

    override fun onDetach() {
        super.onDetach()
        Log.i("Fragmentexpense", "onDetach Method of expense fragment")

    }

}