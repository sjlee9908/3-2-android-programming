package com.example.final_project.repository

import com.example.final_project.data.db.entity.BusinessCardEntity

//데이터베이스 접근 함수 인터페이스
interface BusinessCardRepository {

    //삽입
    suspend fun insertBussinessCard(name: String, email: String, company: String, position: String, phoneNumber: String) : Long

    //업데이트
    suspend fun updateBusinessCard(id: Long, name: String, email: String, company: String, position: String, phoneNumber: String)

    //삭제
    suspend fun deleteBusinessCard(id: Long)

    //모든 항목 얻기
    suspend fun getAllBusinessCards(): List<BusinessCardEntity>

}