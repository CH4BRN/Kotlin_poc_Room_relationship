// File OneToManyDao.kt
// @Author pierre.antoine - 05/02/2020 - No copyright.

package com.uldskull.poc_relationship.room.oneToMany

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction

/**
 *   Interface "OneToManyDao" :
 *   TODO: Fill interface use.
 **/
@Dao
interface OneToManyDao {
    @Insert
    fun insertParent(parentEntity: OneToManyParentEntity):Long?

    @Insert
    fun insertChild(childEntity: OneToManyChildEntity):Long?

    @Transaction
    @Query("SELECT * FROM OneToManyParentEntity")
    fun getParentWithChildren(): List<OneToManyParentWithChildren>
}