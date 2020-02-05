// File ManyToManyDao.kt
// @Author pierre.antoine - 05/02/2020 - No copyright.

package com.uldskull.poc_relationship.room.manyToMany

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction

/**
 *   Interface "ManyToManyDao" :
 *   TODO: Fill interface use.
 **/
@Dao
interface ManyToManyDao {

    @Insert
    fun insertLeft(leftEntity: LeftEntity):Long?

    @Insert
    fun insertRight(rightEntity: RightEntity):Long?

    @Insert
    fun insertCross(leftRightCrossRef: LeftRightCrossRef):Long?


    @Transaction
    @Query("SELECT * FROM LeftEntity")
    fun getLeftWithRights(): List<LeftWithRights>

    @Transaction
    @Query("SELECT * FROM RightEntity")
    fun getRightWithLefts():List<RightWithLefts>
}