package com.example.final_project.ui.businesscardlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.final_project.data.db.entity.BusinessCardEntity
import com.example.final_project.repository.BusinessCardRepository
import kotlinx.coroutines.launch

//명함 뷰모델 <- BusinessCardRepository(DatabaseDataSource)를 받아 사용
class BusinessCardListViewModel(
    private val repository: BusinessCardRepository
) : ViewModel() {

    //모든 명함 목록
    private val _allBusinessCards = MutableLiveData<List<BusinessCardEntity>>()
    //livedata
    val allBusinessCards: LiveData<List<BusinessCardEntity>>
        get() = _allBusinessCards

    //모든 명함목록 얻기 래핑 함수
    //_allBusinessCards업데이트
    fun getBusinessCards() = viewModelScope.launch {
        _allBusinessCards.postValue(repository.getAllBusinessCards())
    }

}