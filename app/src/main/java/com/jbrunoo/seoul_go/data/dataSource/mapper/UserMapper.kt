package com.jbrunoo.seoul_go.data.dataSource.mapper

import android.net.Uri
import com.jbrunoo.seoul_go.data.dataSource.local.room.user.UserEntity
import com.jbrunoo.seoul_go.domain.model.User

fun UserEntity.toUser(): User {
    return User(
        id = this.id,
        userName = this.displayedName,
        userProfileUri = this.profilePictureUri
    )
}

fun User.toUserEntity(): UserEntity {
    return UserEntity(
        id = this.id,
        displayedName = this.userName,
        profilePictureUri = this.userProfileUri.toString()
    )
}