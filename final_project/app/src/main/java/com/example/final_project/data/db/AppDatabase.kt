package com.example.final_project.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.final_project.data.db.dao.BusinessCardDao
import com.example.final_project.data.db.entity.BusinessCardEntity

//Database 추상 클래스
@Database(entities = [BusinessCardEntity::class], version = 2)
abstract class AppDatabase : RoomDatabase() {

    //Dao 인터페이스
    abstract val businessCardDao: BusinessCardDao

    //데이터베이스 인스턴스 객체
    companion object {
        //마이그레이션: 1->2
        val MIGRATION_1_2 = object : Migration(1, 2){
            override fun migrate(db: SupportSQLiteDatabase){
                db.execSQL("ALTER TABLE users ADD COLUMN company [TEXT]")
                db.execSQL("ALTER TABLE users ADD COLUMN position [TEXT]")
                db.execSQL("ALTER TABLE users ADD COLUMN phoneNumber [TEXT]")
            }
        }


        //데이터베이스 인스턴스 -> cpu 캐시 사용 X
        @Volatile
        private var INSTANCE: AppDatabase? = null

            fun getInstance(context: Context) : AppDatabase {
                synchronized(this) {
                    var instance: AppDatabase? = INSTANCE
                    if (instance == null) {
                        instance = Room.databaseBuilder(
                            context,
                            AppDatabase::class.java,
                            "final_project_database"
                        )
                            .addMigrations(MIGRATION_1_2)  //마이그레이션 진행
                            .build()
                    }
                    return instance
                }
        }
    }
}