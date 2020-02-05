// File MyDatabase.kt
// @Author pierre.antoine - 03/02/2020 - No copyright.

package com.uldskull.poc_relationship

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.uldskull.poc_relationship.room.manyToMany.LeftEntity
import com.uldskull.poc_relationship.room.manyToMany.LeftRightCrossRef
import com.uldskull.poc_relationship.room.manyToMany.ManyToManyDao
import com.uldskull.poc_relationship.room.manyToMany.RightEntity
import com.uldskull.poc_relationship.room.oneToMany.OneToManyChildEntity
import com.uldskull.poc_relationship.room.oneToMany.OneToManyDao
import com.uldskull.poc_relationship.room.oneToMany.OneToManyParentEntity
import com.uldskull.poc_relationship.room.oneToOne.OneToOneChildEntity
import com.uldskull.poc_relationship.room.oneToOne.OneToOneDao
import com.uldskull.poc_relationship.room.oneToOne.OneToOneParentEntity


@Database(
    entities = arrayOf(
        OneToOneParentEntity::class,
        OneToOneChildEntity::class,
        OneToManyParentEntity::class,
        OneToManyChildEntity::class,
        LeftEntity::class,
        RightEntity::class,
        LeftRightCrossRef::class
    ),
    version = 2
)
@TypeConverters(Converter::class)
abstract class MyDatabase : RoomDatabase() {
    abstract fun oneToOneDao(): OneToOneDao
    abstract fun oneToManyDao(): OneToManyDao
    abstract fun manyToManyDao(): ManyToManyDao
}