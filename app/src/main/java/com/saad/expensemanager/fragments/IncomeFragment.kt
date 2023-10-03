package com.saad.expensemanager.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.saad.expensemanager.R
import com.saad.expensemanager.adapter.adapter
import com.saad.expensemanager.databinding.FragmentIncomeBinding
import com.saad.expensemanager.viewmodels.ViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IncomeFragment : Fragment() {
    private lateinit var binding: FragmentIncomeBinding
    private val viewModel: ViewModel by viewModels()
    private lateinit var email: String
    private lateinit var type: String

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.i("FragmentIncome", "onAttach Method of Income fragment")

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("FragmentIncome", "onCreate Method of Income fragment")

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i("Fragmentexpense", "onCreateView Method of income fragment")
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_income, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i("FragmentIncome", "onViewCreate Method of Income fragment")
//        val repository = (requireActivity().application as ExpenseApplication).expenseRepository
//
//        //.get(ViewModel::class.java)
//        viewModel = ViewModelProvider(this, ViewModelFactory(repository))[ViewModel::class.java]
        email = "saad@gmail.com"
        type = "Income"

        val adapter = adapter()
        viewModel.getAllExpenseType(email, type).observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        val recycleLayout = binding.recycleIncludeLayoutIncome
        val recyclerView = recycleLayout.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Log.i("FragmentIncome", "onViewStateRestored Method of Income fragment")

    }

    override fun onStart() {
        super.onStart()
        Log.i("FragmentIncome", "onStart Method of Income fragment")

    }

    override fun onResume() {
        super.onResume()
        Log.i("FragmentIncome", "onResume Method of Income fragment")

    }

    override fun onPause() {
        super.onPause()
        Log.i("FragmentIncome", "onPause Method of Income fragment")
    }

    override fun onStop() {
        super.onStop()
        Log.i("FragmentIncome", "onStop Method of Income fragment")

    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.i("FragmentIncome", "onDestroy Method of Income fragment")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("FragmentIncome", "onDestroy Method of Income fragment")

    }

    override fun onDetach() {
        super.onDetach()
        Log.i("FragmentIncome", "onDetach Method of Income fragment")

    }
}