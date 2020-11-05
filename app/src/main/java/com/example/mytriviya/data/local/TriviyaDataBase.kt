package com.example.mytriviya.data.local

import android.content.Context
import androidx.room.*
import com.example.mytriviya.model.ModelClass


@Database(
    entities = [ModelClass::class],
    version = 3,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class TriviyaDataBase : RoomDatabase() {

   abstract fun getTriviaDao() : TriviaDao
    companion object {

        const val DB_NAME = "t_db"

        @Volatile
        private var INSTANCE: TriviyaDataBase? = null

        fun getDbInstance(context: Context): TriviyaDataBase {

            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TriviyaDataBase::class.java,
                    DB_NAME
                ).addMigrations(*DatabaseMigrations.MIGRATIONS).build()

                INSTANCE =instance
                return instance
            }



        }
    }


}