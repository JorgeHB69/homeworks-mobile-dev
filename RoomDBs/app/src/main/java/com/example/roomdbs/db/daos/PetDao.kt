package com.example.roomdbs.db.daos

import androidx.room.*
import com.example.roomdbs.db.models.Pet

@Dao
interface PetDao {
    @Query("SELECT * FROM Pet")
    suspend fun getAll(): List<Pet>

    @Query("SELECT * FROM Pet WHERE id = :id")
    suspend fun getById(id: Int): Pet

    @Insert
    suspend fun insertPet(pet: Pet): Long

    @Update
    suspend fun updatePet(pet: Pet)

    @Delete
    suspend fun deletePet(pet: Pet): Int
}
