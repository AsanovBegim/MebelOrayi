package com.example.mebelorayi.data.ModelMebel

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Cafee")
data class MagazinMebeli (
   @PrimaryKey
   val id: Int,

   @ColumnInfo(name = "type")
    val type: Int,

   @ColumnInfo(name = "nameRus")
   val nameRus: String,

   @ColumnInfo(name= "details")
   val details: String,

   @ColumnInfo(name = "ingredients")
   val ingredients: String,

   @ColumnInfo(name = "cost")
   val cost: String,

   @ColumnInfo(name = "isOrdered")
   var isOrdered : Int?,

   @ColumnInfo(name= "quantity")
   var quantity : String?

)