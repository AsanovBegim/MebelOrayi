package com.example.mebelorayi.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import com.example.mebelorayi.data.ModelMebel.MagazinMebeli

@Dao
interface MenuDao {
    @Query("SELECT * FROM Cafee WHERE type =:type")
    fun getMenuByType(type: Int): List<MagazinMebeli>

    @Query("SELECT * FROM Cafee WHERE id = :id")
    fun getMenuById(id: Int) : MagazinMebeli

    @Update
    fun updateMenu(menu: MagazinMebeli)

    @Query("SELECT * FROM Cafee WHERE isOrdered=1")
    fun getAllFromIsOrdered() : List<MagazinMebeli>

//    @Query("DELETE FROM Cafee WHERE isOrdered=1")
//    fun removeFromOrder() : Int

    @Query("UPDATE Cafee SET isOrdered=0 WHERE isOrdered=1")
    fun deleteFromOrder() : Int
}