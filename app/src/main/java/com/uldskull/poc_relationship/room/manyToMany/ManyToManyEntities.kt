/** File ManyToManyEntities.kt
 *   @Author pierre.antoine - 05/02/2020 - No copyright.
 **/

package com.uldskull.poc_relationship.room.manyToMany

import androidx.annotation.NonNull
import androidx.room.*

@Entity
data class LeftEntity(
    @PrimaryKey(autoGenerate = true)
    val leftEntityId:Long?,
    val leftEntityName:String
)

@Entity
data class RightEntity(
    @PrimaryKey(autoGenerate = true)
    val rightEntityId:Long?,
    val rightEntityName:String
)

@Entity(primaryKeys = ["leftEntityId", "rightEntityId"])
data class LeftRightCrossRef(
    @NonNull
    val leftEntityId: Long,
    @NonNull
    val rightEntityId: Long
)

data class LeftWithRights(
    @Embedded
    val left:LeftEntity,
    @Relation(
        parentColumn = "leftEntityId",
        entityColumn = "rightEntityId",
        associateBy = Junction(LeftRightCrossRef::class)
    )
    val rights:List<RightEntity>
)

data class RightWithLefts(
    @Embedded
    val right:RightEntity,
    @Relation(
        parentColumn = "rightEntityId",
        entityColumn = "leftEntityId",
        associateBy = Junction(LeftRightCrossRef::class)
    )
    val lefts:List<LeftEntity>
)