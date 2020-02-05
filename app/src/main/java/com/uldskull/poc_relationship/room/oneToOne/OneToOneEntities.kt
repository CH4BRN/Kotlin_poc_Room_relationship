/** File OneToOneEntities.kt
 *   @Author pierre.antoine - 05/02/2020 - No copyright.
 **/

package com.uldskull.poc_relationship.room.oneToOne

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity
data class OneToOneParentEntity(
    @PrimaryKey
    val parentEntityId: Long?,
    val name: String,
    val age: Int
)

@Entity
data class OneToOneChildEntity(
    @PrimaryKey
    val childEntityId: Long?,
    val parentOwnerId: Long?
)

/**
Model of the one-to-one relationship
- one instance of the parent entity
- one instance of the child entity

@Relation annotation on the child entity instance
with
- parentColumn set to the name of the primary key column of the parent entity
- entityColumn set to the name of the column of the child entity that references the parent
entity's primary key.
 */
data class OneToOneParentAndChildren(
    @Embedded
    val OneToOneParentEntity: OneToOneParentEntity,
    @Relation(
        parentColumn = "parentEntityId",
        entityColumn = "parentOwnerId"
    )
    val oneToOneChildEntity: OneToOneChildEntity
)
