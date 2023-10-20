package com.saad.expensemanager.activity

import android.app.DatePickerDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.saad.expensemanager.R
import com.saad.expensemanager.databinding.ActivityMainBinding
import com.saad.expensemanager.databinding.BottomSheetLayoutBinding
import com.saad.expensemanager.fragments.ExpenseFragment
import com.saad.expensemanager.fragments.HistoryFragment
import com.saad.expensemanager.fragments.HomeFragment
import com.saad.expensemanager.fragments.IncomeFragment
import com.saad.expensemanager.viewmodels.ViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: ViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.toastMessage.observe(this) { message ->
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }

        replaceFragment(HomeFragment())

        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> replaceFragment(HomeFragment())
                R.id.income -> replaceFragment(IncomeFragment())
                R.id.expense -> replaceFragment(ExpenseFragment())
                R.id.history -> replaceFragment(HistoryFragment())

            }
            true
        }

        binding.floatingActionButton.setOnClickListener {
            showBottomSheet()
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }

    private fun showBottomSheet() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
        val dialog = Dialog(this)

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        val bindingDialog: BottomSheetLayoutBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.bottom_sheet_layout,
            null,
            false
        )
        dialog.setContentView(bindingDialog.root)
        bindingDialog.viewModel = viewModel
        bindingDialog.lifecycleOwner = this

        dialog.show()
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.attributes?.windowAnimations = R.style.DialogAnimation
        dialog.window?.setGravity(Gravity.BOTTOM)


        val defaultDate = "$year-${month + 1}-$dayOfMonth"
        viewModel.updateDate(defaultDate)

        viewModel.errorMessageTitle.observe(this) {
            if (it.isNotEmpty())
                bindingDialog.titleEditText.error = it
            return@observe
        }
        viewModel.errorMessageAmount.observe(this) {
            if (it.isNotEmpty()) {
                bindingDialog.expenseEditText.error = it
                return@observe
            }
        }
        bindingDialog.dateEditText.setOnClickListener {
            val datePickerDialog = DatePickerDialog(
                this,
                { _, selectedYear, selectedMonth, selectedDayOfMonth ->
                    val selectedCalendar = Calendar.getInstance()
                    selectedCalendar.set(selectedYear, selectedMonth, selectedDayOfMonth)
                    val currentDate = Calendar.getInstance()

                    if (selectedCalendar.after(currentDate)) {
                        Toast.makeText(this, "Cannot select future date", Toast.LENGTH_SHORT).show()
                    } else {
                        val selectedDate = "$selectedYear-${selectedMonth + 1}-$selectedDayOfMonth"
                        viewModel.updateDate(selectedDate)
                    }
                },
                year,
                month,
                dayOfMonth
            )
            datePickerDialog.datePicker.maxDate = System.currentTimeMillis()
            datePickerDialog.show()
        }
        val currentTime = SimpleDateFormat("hh:mm a", Locale.getDefault()).format(Date())
        viewModel.updateTime(currentTime)

//        viewModel.hidebottomsheet.observe(this) {
//            if (it == true) {
//                dialog.dismiss()
//            }
//        }
    }
}