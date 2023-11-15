package com.fahmi.testapp.presentation.employee

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fahmi.testapp.base.dispatcher.DispatcherProvider
import com.fahmi.testapp.domain.model.EmployeeRequest
import com.fahmi.testapp.domain.model.EmployeeUIModel
import com.fahmi.testapp.domain.resource.EmployeeResource
import com.fahmi.testapp.domain.usecase.CreateEmployeeUseCase
import com.fahmi.testapp.domain.usecase.DeleteEmployeeUseCase
import com.fahmi.testapp.domain.usecase.GetEmployeeUseCase
import com.fahmi.testapp.domain.usecase.UpdateEmployeeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmployeeViewModel @Inject constructor(
    private val dispatcher: DispatcherProvider,
    private val getEmployeeUseCase: GetEmployeeUseCase,
    private val createEmployeeUseCase: CreateEmployeeUseCase,
    private val updateEmployeeUseCase: UpdateEmployeeUseCase,
    private val deleteEmployeeUseCase: DeleteEmployeeUseCase
) : ViewModel() {

    private val _employee: MutableLiveData<List<EmployeeUIModel>> = MutableLiveData()
    val employee: LiveData<List<EmployeeUIModel>> get() = _employee

    private val _result: MutableLiveData<String> = MutableLiveData()
    val result: LiveData<String> get() = _result

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun getEmployee() = viewModelScope.launch {
        getEmployeeUseCase.invoke().collect { resource ->
            when (resource) {
                EmployeeResource.Loading -> {
                    _isLoading.value = true
                }

                is EmployeeResource.Success -> {
                    _employee.value = resource.data
                    _isLoading.value = false
                }

                is EmployeeResource.Error -> {
                    _isLoading.value = false
                }
            }
        }
    }

    fun createEmployee(request: EmployeeRequest) = viewModelScope.launch(dispatcher.io) {
        _isLoading.postValue(true)
        _result.postValue(createEmployeeUseCase.invoke(request))
    }

    fun updateEmployee(
        employeeId: String,
        update: EmployeeRequest
    ) = viewModelScope.launch(dispatcher.io) {
        _isLoading.postValue(true)
        _result.postValue(updateEmployeeUseCase.invoke(employeeId, update))
    }

    fun deleteEmployee(employeeId: String) = viewModelScope.launch(dispatcher.io) {
        _isLoading.postValue(true)
        _result.postValue(deleteEmployeeUseCase.invoke(employeeId))
    }
}