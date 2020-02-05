// File PocDao.kt
// @Author pierre.antoine - 03/02/2020 - No copyright.

package com.uldskull.poc_relationship.room.oneToOne

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction

/**
 *   Interface "PocDao" :
 *   Dao to insert and get one to one relations.
 **/
@Dao
interface OneToOneDao {

    /**
     * Insert parent
     */
    @Insert
    fun insertParent(OneToOneParentEntity: OneToOneParentEntity): Long

    /**
     * Insert child
     */
    @Insert
    fun insertChild(oneToOneChildEntity: OneToOneChildEntity): Long

    /**
     * Get parent and children.
     */
    @Transaction
    @Query("SELECT * FROM OneToOneParentEntity")
    fun getParentAndChildren(): List<OneToOneParentAndChildren>


}