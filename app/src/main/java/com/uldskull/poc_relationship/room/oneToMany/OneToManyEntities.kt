/** File OneToManyEntities.kt
 *   @Author pierre.antoine - 05/02/2020 - No copyright.
 **/

package com.uldskull.poc_relationship.room.oneToMany

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

/**
 * Parent entity.
 */
@Entity
data class OneToManyParentEntity(
    @PrimaryKey
    val parentEntityId: Long?,
    val name: String,
    val age: Int
)

/**
 * Child entity.
 */
@Entity
data class OneToManyChildEntity(
    @PrimaryKey
    val childEntityId: Long?,
    val childParentEntityId: Long?,
    val childEntityField: String
)

/**
 * Model of the one-to-many relationship.
 * - One instance that hold the parent entity
 * - One list of all corresponding child entity instance.
 *
 * @Relation on the child entity with :
 * - parentColumn set to the name of the primaryKey column of the parent entity
 * - entityColumn set to the name of the column of the child entity that references
 * the parent entity's primary key.
 */
data class OneToManyParentWithChildren(
    @Embedded
    val parent: OneToManyParentEntity,
    @Relation(
        parentColumn = "parentEntityId",
        entityColumn = "childParentEntityId"
    )
    val children: List<OneToManyChildEntity>
)