package com.jbrunoo.seoul_go.data.dataSource.local.room.user

import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey val id: String,
    val displayedName: String? = null,
    val profilePictureUri: String? = null
)