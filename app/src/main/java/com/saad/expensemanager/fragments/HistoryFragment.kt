package com.saad.expensemanager.fragments

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.saad.expensemanager.R
import com.saad.expensemanager.adapter.adapter
import com.saad.expensemanager.databinding.FragmentHistoryBinding
import com.saad.expensemanager.repository.Repository
import com.saad.expensemanager.room.DatabaseHelper
import com.saad.expensemanager.viewmodels.ViewModelFactoryHistory
import com.saad.expensemanager.viewmodels.ViewModelHistory
import java.util.Calendar


class HistoryFragment : Fragment() {
    private lateinit var binding: FragmentHistoryBinding
    private lateinit var viewModelHistory: ViewModelHistory
    private lateinit var getDate: String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_history, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dao = DatabaseHelper.getDatabase(requireContext()).expenseDao()
        val userDao = DatabaseHelper.getDatabase(requireContext()).userDao()
        val repository = Repository(dao, userDao)
        viewModelHistory =
            ViewModelProvider(
                this,
                ViewModelFactoryHistory(repository)
            )[ViewModelHistory::class.java]


        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
        val defaultDate = "$year-${month + 1}-$dayOfMonth"
        getDate = defaultDate
        binding.dateEditText.setText(defaultDate.toString())
        viewModelHistory.updateDate(defaultDate)
        binding.dateEditText.setOnClickListener {
            val datePickerDialog = DatePickerDialog(
                requireContext(),
                { _, selectedYear, selectedMonth, selectedDayOfMonth ->
                    val selectedCalendar = Calendar.getInstance()
                    selectedCalendar.set(selectedYear, selectedMonth, selectedDayOfMonth)
                    val currentDate = Calendar.getInstance()

                    if (selectedCalendar.after(currentDate)) {
                        Toast.makeText(
                            requireContext(),
                            "Cannot select future date",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        val selectedDate = "$selectedYear-${selectedMonth + 1}-$selectedDayOfMonth"
//                        getDate = selectedDate
                        Toast.makeText(
                            requireContext(),
                            "$selectedDate",
                            Toast.LENGTH_SHORT
                        ).show()
                        binding.dateEditText.setText(selectedDate.toString())

                        viewModelHistory.updateDate(selectedDate)
                    }
                },
                year,
                month,
                dayOfMonth
            )
            datePickerDialog.datePicker.maxDate = System.currentTimeMillis()
            datePickerDialog.show()
        }
        val adapter = adapter()
        viewModelHistory.selectedDate.observe(viewLifecycleOwner) {

            viewModelHistory.getByDate(it).observe(viewLifecycleOwner) { list ->
                adapter.submitList(list)
            }
        }

//        viewModelHistory.getByDate(getDate).observe(viewLifecycleOwner) {
//            adapter.submitList(it)
//        }
        val recycleLayout = binding.recycleIncludeLayoutHistory
        val recyclerView = recycleLayout.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter


    }

}