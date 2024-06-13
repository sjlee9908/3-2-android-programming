package com.example.final_project.repository

import com.example.final_project.data.db.dao.BusinessCardDao
import com.example.final_project.data.db.entity.BusinessCardEntity

//데이터베이스 접근 함수 구현
class DatabaseDataSource(
    private val businessCardDao: BusinessCardDao
) : BusinessCardRepository {
    
    //삽입
    override suspend fun insertBussinessCard(name: String, email: String, company: String, position: String, phoneNumber: String): Long {
        val businessCard = BusinessCardEntity(
            name = name,
            email = email,
            company = company,
            position = position,
            phoneNumber = phoneNumber
        )

        return businessCardDao.insert(businessCard)
    }

    //비즈니스 카드 업데이트
    override suspend fun updateBusinessCard(id: Long, name: String, email: String, company: String, position: String, phoneNumber: String) {
        val businessCard = BusinessCardEntity(
            id = id,
            name = name,
            email = email,
            company = company,
            position = position,
            phoneNumber = phoneNumber
        )
        businessCardDao.update(businessCard)
    }

    //비즈니스 카드 삭제
    override suspend fun deleteBusinessCard(id: Long) {
        businessCardDao.delete(id)
    }

    //모든 비즈니스 카드 얻기
    override suspend fun getAllBusinessCards(): List<BusinessCardEntity> {
        return businessCardDao.getAll()
    }

}