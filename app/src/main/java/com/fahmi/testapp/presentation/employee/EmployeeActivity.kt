package com.fahmi.testapp.presentation.employee

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.fahmi.testapp.R
import com.fahmi.testapp.base.abstraction.BaseActivity
import com.fahmi.testapp.databinding.ActivityEmployeeBinding
import com.fahmi.testapp.domain.model.EmployeeRequest
import com.fahmi.testapp.domain.model.EmployeeUIModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class EmployeeActivity : BaseActivity<ActivityEmployeeBinding, EmployeeViewModel>() {
    override val getViewBinding: (LayoutInflater) -> ActivityEmployeeBinding
        get() = ActivityEmployeeBinding::inflate
    override val getViewModelClass: Class<EmployeeViewModel>
        get() = EmployeeViewModel::class.java
    private val employeeAdapter by lazy { EmployeeAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupView()
        viewModel.getEmployee()
        setupObserve()
    }

    private fun setupObserve() {
        viewModel.employee.observe(this) {
            employeeAdapter.submitList(it)
        }
        viewModel.result.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            binding.progressBar.visibility = View.GONE
        }
        viewModel.isLoading.observe(this) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
    }

    private fun setupView() {
        binding.rvPokemon.layoutManager = LinearLayoutManager(this)
        binding.rvPokemon.adapter = employeeAdapter

        employeeAdapter.setOnDeleteEmployee { viewModel.deleteEmployee(it) }
        employeeAdapter.setOnUpdateEmployee { setupDialog("Update Employee", it) }
        binding.tvAddEmployee.setOnClickListener { setupDialog("Add Employee") }
    }

    private fun setupDialog(title: String, itemEmployee: EmployeeUIModel? = null) {
        val dialog = Dialog(this)
        dialog.setTitle(title)
        dialog.setContentView(R.layout.view_dialog)
        dialog.setCanceledOnTouchOutside(false)
        val width = resources.displayMetrics.widthPixels
        dialog.window?.setLayout(6 * width / 7, LinearLayout.LayoutParams.WRAP_CONTENT)

        val submitButton = dialog.findViewById<Button>(R.id.btn_submit)
        val name = dialog.findViewById<EditText>(R.id.et_name)
        val salary = dialog.findViewById<EditText>(R.id.et_salary)
        val age = dialog.findViewById<EditText>(R.id.et_age)

        if (itemEmployee != null) {
            with(itemEmployee) {
                name.setText(employeeName)
                salary.setText(employeeSalary)
                age.setText(employeeAge)
            }
        }

        submitButton.setOnClickListener {
            val request =
                EmployeeRequest(age.text.toString(), name.text.toString(), salary.text.toString())
            if (itemEmployee == null) {
                viewModel.createEmployee(request)
            } else {
                viewModel.updateEmployee(itemEmployee.id, request)
            }
        }

        dialog.show()
    }
}