package com.example.final_project.data.db.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

//명함 객체 -> parcelize를 통해 직렬화(캐시를 통해 로드된 데이터를 빠르게 꺼내기 위함)
@Parcelize
@Entity(tableName = "users")
data class BusinessCardEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,  //기본키
    //기본 컬럼
    val name: String,
    val email: String,
    val company: String,
    val position: String,
    val phoneNumber: String
) : Parcelable