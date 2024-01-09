package com.example.mytasks

import androidx.room.*
import kotlin.coroutines.Continuation

@Dao
interface DAO {
    @Insert
    suspend fun insertTask(entity: Entity)

    @Update
    suspend fun updateTask(entity: Entity)

    @Delete
    suspend fun deleteTask(entity: Entity)

    @Query("Delete from MyTask")
    suspend fun deleteAll()

    @Query("Select * from MyTask")
    suspend fun getTasks():List<CardInfo>


}