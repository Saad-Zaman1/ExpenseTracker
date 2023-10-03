package com.saad.expensemanager.fragments

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
import com.saad.expensemanager.databinding.FragmentHomeBinding
import com.saad.expensemanager.utilities.SharedPrefs
import com.saad.expensemanager.viewmodels.ViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: ViewModel by viewModels()
    private lateinit var email: String

    /*
    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.i("Fragmenthome", "onAttach Method of home fragment")

    }
*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("Fragmenthome", "onCreate Method of home fragment")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i("Fragmenthome", "onCreateView Method of home fragment")
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i("Fragmenthome", "onViewCreate Method of home fragment")
//
//        val repository = (requireActivity().application as ExpenseApplication).expenseRepository
//
//        //.get(ViewModel::class.java)
//        viewModel = ViewModelProvider(this, ViewModelFactory(repository))[ViewModel::class.java]

        val adapter = adapter()
        viewModel.getExpense().observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
        val recycleLayout = binding.recycleIncludeLayout
        val recyclerView = recycleLayout.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
        val sp = SharedPrefs(requireContext())
        sp.getString("userEmail", "")
        email = "saad@gmail.com"

        viewModel.getTotalAmount(email, "Income").observe(viewLifecycleOwner) {
            var total = 0.0
            for (expense in it) {
                // Assuming that the amount is stored as a string
                val amount = expense.amount.toDoubleOrNull() ?: 0.0
                total += amount
            }
            binding.income = total.toString()
        }


        viewModel.getTotalAmount(email, "Expense").observe(viewLifecycleOwner) {
            var total = 0.0
            for (expense in it) {
                // Assuming that the amount is stored as a string
                val amount = expense.amount.toDoubleOrNull() ?: 0.0
                total += amount
            }
            binding.expense = total.toString()

        }

//        Toast.makeText(
//            requireContext(),
//            "${viewModel.totalAmount.value} and email: $email",
//            Toast.LENGTH_SHORT
//        )
//            .show()
    }
    /*
        override fun onViewStateRestored(savedInstanceState: Bundle?) {
            super.onViewStateRestored(savedInstanceState)
            Log.i("Fragmenthome", "onViewStateRestored Method of home fragment")

        }

        override fun onStart() {
            super.onStart()
            Log.i("Fragmenthome", "onStart Method of home fragment")

        }

        override fun onResume() {
            super.onResume()
            Log.i("Fragmenthome", "onResume Method of home fragment")

        }

        override fun onPause() {
            super.onPause()
            Log.i("Fragmenthome", "onPause Method of home fragment")
        }

        override fun onStop() {
            super.onStop()
            Log.i("Fragmenthome", "onStop Method of home fragment")

        }

        override fun onDestroyView() {
            super.onDestroyView()
            Log.i("Fragmenthome", "onDestroyView Method of home fragment")

        }

        override fun onDestroy() {
            super.onDestroy()
            Log.i("Fragmenthome", "onDestroy Method of home fragment")

        }

        override fun onDetach() {
            super.onDetach()
            Log.i("Fragmenthome", "onDetach Method of home fragment")

        }

        */
}