package nitrosrus.colorsquareapp.mvp.model.api


import nitrosrus.colorsquareapp.mvp.model.entity.Photo
import nitrosrus.colorsquareapp.mvp.model.entity.User

interface IJSONParser {
    fun parseToUsersList(data: String): MutableList<User>
    fun parseToPhotosList(data: String): MutableList<Photo>
}