package com.example.final_project.ui.businesscard

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.final_project.R
import com.example.final_project.repository.BusinessCardRepository
import kotlinx.coroutines.launch

//명함 뷰모델 <- BusinessCardRepository(DatabaseDataSource)를 받아 사용
class BusinessCardViewModel(
    private val repository: BusinessCardRepository
) : ViewModel() {
    
    //명함 항목의 상태를 저장
    private val _businessCardStateEventData = MutableLiveData<BusinessCardState>()
    val businessCardStateEventData: LiveData<BusinessCardState>
        get() = _businessCardStateEventData  //livedata를 통해 데이터 변경이 즉각 반영

    //동작에 따른 문자열 id를 저장
    private val _messageEventData = MutableLiveData<Int>()
    val messageEventData: LiveData<Int>  //livedata를 통해 데이터 변경이 즉각 반영
        get() = _messageEventData

    //id의 유무에 따른 insert, update 
    fun addOrUpdateBusinessCard(name: String, email: String, company:String, position: String, phoneNumber:String, id: Long = 0) {
        //id가 주어짐 -> 업데이트
        if (id > 0) {
            updateBusinessCard(id, name, email, company, position, phoneNumber)
        }
        //id가 주어지지 않음 -> 새로 삽입
        else {
            insertBusinessCard(name, email, company, position, phoneNumber)
        }
    }
    
    //insert 래핑 함수
    //코루틴 사용
    private fun insertBusinessCard(name: String, email: String, company: String, position: String, phoneNumber: String) = 
        viewModelScope.launch {
        try {  //예외 x
            val id = repository.insertBussinessCard(name, email, company, position, phoneNumber)  //삽입된 항목의 id반환
            if(id > 0) {
                _businessCardStateEventData.value = BusinessCardState.Updated  //updated로 현재 상태 변경 
                _messageEventData.value = R.string.user_added_success  //문자열 id 변경
            }
        }
        catch (ex: Exception) {  //예외 o
            _messageEventData.value = R.string.user_added_error  //문자열 id 변경
        }
    }

    //update 래핑 함수
    private fun updateBusinessCard(id: Long, name: String, email: String, company: String, position: String, phoneNumber: String) = viewModelScope.launch {
        try {
            repository.updateBusinessCard(id, name, email, company, position, phoneNumber)
            _businessCardStateEventData.value = BusinessCardState.Inserted
            _messageEventData.value = R.string.user_updated_success

        } catch (ex: Exception) {
            _messageEventData.value = R.string.user_updated_error
        }
    }

    //delete 래핑 함수
    fun removeBusinessCard(id: Long) = viewModelScope.launch {
        try {
            if(id > 0) {
                repository.deleteBusinessCard(id)
                _businessCardStateEventData.value = BusinessCardState.Deleted
                _messageEventData.value = R.string.user_deleted_success
            }
        } catch (ex: Exception) {
            _messageEventData.value = R.string.user_deleted_error
        }
    }

    //삽입, 수정, 삭제 중 하나의 상태
    //sealed 클래스이므로 다음 중 1개의 클래스로밖에 표현이 안됨
    sealed class BusinessCardState {
        object Inserted : BusinessCardState()
        object Updated: BusinessCardState()
        object Deleted: BusinessCardState()
    }
}