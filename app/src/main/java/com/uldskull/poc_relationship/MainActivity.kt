package com.uldskull.poc_relationship

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.uldskull.poc_relationship.room.manyToMany.LeftEntity
import com.uldskull.poc_relationship.room.manyToMany.LeftRightCrossRef
import com.uldskull.poc_relationship.room.manyToMany.RightEntity
import com.uldskull.poc_relationship.room.oneToMany.OneToManyChildEntity
import com.uldskull.poc_relationship.room.oneToMany.OneToManyParentEntity
import com.uldskull.poc_relationship.room.oneToMany.OneToManyParentWithChildren
import com.uldskull.poc_relationship.room.oneToOne.OneToOneChildEntity
import com.uldskull.poc_relationship.room.oneToOne.OneToOneParentEntity
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.application.deleteDatabase("poc-db")
        var displayer = findViewById<TextView>(R.id.displayer)

        manyToManyDemo()
    }

    fun manyToManyDemo() {
        //  Creates the left entity
        var leftId = MyApp?.database?.manyToManyDao()?.insertLeft(
            LeftEntity(
                leftEntityId = null,
                leftEntityName = "I_Am_Left"
            )
        )
        //  Creates the right entity
        var rightId = MyApp?.database?.manyToManyDao()?.insertRight(
            RightEntity(
                rightEntityId = null,
                rightEntityName = "I_Am_Right"
            )
        )

        //  Create the cross entity
        var crossId = MyApp?.database?.manyToManyDao()?.insertCross(
            LeftRightCrossRef(
                leftEntityId = leftId!!,
                rightEntityId = rightId!!
            )
        )

        var leftWithRightList = MyApp?.database?.manyToManyDao()?.getLeftWithRights()
        leftWithRightList?.forEach {
            var name = it.left.leftEntityName
            it.rights.forEach {
                Log.i("TEST TEST", name + " " + it.rightEntityName)
            }

        }

        var rightWithLeftList = MyApp?.database?.manyToManyDao()?.getRightWithLefts()
        rightWithLeftList?.forEach {
            var name = it.right.rightEntityName
            it.lefts.forEach {
                Log.i("TEST TEST", name + " " + it.leftEntityName)
            }
        }
    }

    fun oneToMany_demo() {
        //  Creates the parent entity
        var id = MyApp.database?.oneToManyDao()?.insertParent(
            OneToManyParentEntity(
                parentEntityId = null,
                name = "User_user",
                age = 10
            )
        )
        //  Creates the children entities
        var child1Id = MyApp.database?.oneToManyDao()?.insertChild(
            OneToManyChildEntity(
                childEntityId = null,
                childEntityField = "child_1",
                childParentEntityId = id
            )
        )

        var child2Id = MyApp.database?.oneToManyDao()?.insertChild(
            OneToManyChildEntity(
                childEntityId = null,
                childEntityField = "child_2",
                childParentEntityId = id
            )
        )

        var parentAndChildren = MyApp.database?.oneToManyDao()?.getParentWithChildren()

        var aggregate: OneToManyParentWithChildren? = parentAndChildren?.first()

        var parent: OneToManyParentEntity? = aggregate?.parent

        aggregate?.children?.forEach {
            Log.i("TESTDB", aggregate.parent.name + " " + it.childEntityField)
        }


    }

    /**
     * OneToOne relation demonstration.
     */
    fun oneToOne_demo() {

        //  Creates the parent entity
        var id = MyApp.database?.oneToOneDao()?.insertParent(
            OneToOneParentEntity(
                parentEntityId = null,
                name = "user_user",
                age = 10
            )
        )
        //  Creates the child entity
        var childId = MyApp.database?.oneToOneDao()?.insertChild(
            OneToOneChildEntity(
                childEntityId = null,
                parentOwnerId = id
            )
        )


        var userAndLibrary =
            MyApp.database?.oneToOneDao()?.getParentAndChildren()

        userAndLibrary?.forEach {
            Log.i(it.OneToOneParentEntity.name, it.oneToOneChildEntity.childEntityId.toString())
        }
    }
}


class Converter {

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return if (value == null) null else Date(value)
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun stringToPlayers(value: String): List<Long> {
        val listPlayers = object : TypeToken<Long>() {}.type
        return Gson().fromJson(value, listPlayers)
    }

    @TypeConverter
    fun playersToString(list: List<Long>): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}
