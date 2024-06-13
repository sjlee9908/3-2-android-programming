package com.example.final_project.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.final_project.data.db.entity.BusinessCardEntity

//Dao 인터페이스
@Dao
interface BusinessCardDao {
    @Insert
    suspend fun insert(user: BusinessCardEntity): Long  //삽입시 삽입된 항목의 id 반환

    @Update
    suspend fun update(user: BusinessCardEntity)  //업데이트

    @Query(value = "DELETE FROM users WHERE id = :id")  //삭제 쿼리
    suspend fun delete(id: Long)

    @Query(value = "SELECT * FROM users")  //전체 항목 select
    suspend fun getAll(): List<BusinessCardEntity>
}