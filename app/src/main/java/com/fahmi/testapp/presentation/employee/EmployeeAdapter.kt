package com.fahmi.testapp.presentation.employee

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fahmi.testapp.databinding.ItemEmployeeBinding
import com.fahmi.testapp.domain.model.EmployeeUIModel
import com.fahmi.testapp.presentation.utils.loadImage


class EmployeeAdapter :
    ListAdapter<EmployeeUIModel, EmployeeAdapter.PokemonViewHolder>(diffCallBack) {
    private var onUpdateItemEmployee: ((EmployeeUIModel) -> Unit)? = null
    private var onDeleteItemEmployee: ((String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemEmployeeBinding.inflate(layoutInflater, parent, false)
        return PokemonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bind(getItem(position), onUpdateItemEmployee, onDeleteItemEmployee)
    }

    class PokemonViewHolder(private val binding: ItemEmployeeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            item: EmployeeUIModel,
            onUpdateItemEmployee: ((EmployeeUIModel) -> Unit)?,
            onDeleteItemEmployee: ((String) -> Unit)?
        ) {
            with(binding) {
                tvName.text = item.employeeName
                tvAge.text = item.employeeAge
                tvSalary.text = item.employeeSalary
                ivEmployee.loadImage(item.profileImage)
                ivEmployee.visibility = if (item.isProfileImageExist) View.VISIBLE else View.GONE
                btnEdit.setOnClickListener { onUpdateItemEmployee?.invoke(item) }
                btnDelete.setOnClickListener { onDeleteItemEmployee?.invoke(item.id) }
            }
        }
    }

    companion object {
        val diffCallBack = object : DiffUtil.ItemCallback<EmployeeUIModel>() {
            override fun areItemsTheSame(
                oldItem: EmployeeUIModel,
                newItem: EmployeeUIModel
            ): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: EmployeeUIModel,
                newItem: EmployeeUIModel
            ): Boolean = oldItem == newItem

        }
    }

    fun setOnUpdateEmployee(itemUpdate: (EmployeeUIModel) -> Unit) {
        this.onUpdateItemEmployee = itemUpdate
    }

    fun setOnDeleteEmployee(itemDelete: (String) -> Unit) {
        this.onDeleteItemEmployee = itemDelete
    }
}