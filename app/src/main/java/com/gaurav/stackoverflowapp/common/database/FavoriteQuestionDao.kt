package com.gaurav.stackoverflowapp.common.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gaurav.stackoverflowapp.question.FavoriteQuestion
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteQuestionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(question: FavoriteQuestion)

    @Query("SELECT * FROM favorite")
    fun observe(): Flow<List<FavoriteQuestion>>

    @Query("SELECT * FROM favorite WHERE id = :id")
    suspend fun getById(id: String): FavoriteQuestion?

    @Query("SELECT * FROM favorite WHERE id = :id")
    fun observeById(id: String): Flow<FavoriteQuestion?>

    @Query("DELETE FROM favorite WHERE id = :id")
    suspend fun delete(id: String)
}