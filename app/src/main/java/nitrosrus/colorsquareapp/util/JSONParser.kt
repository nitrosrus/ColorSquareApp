package nitrosrus.colorsquareapp.util

import nitrosrus.colorsquareapp.mvp.model.api.IJSONParser
import nitrosrus.colorsquareapp.mvp.model.entity.Photo
import nitrosrus.colorsquareapp.mvp.model.entity.User
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class JSONParser() : IJSONParser {

    override fun parseToUsersList(data: String): MutableList<User> {
        return parseToUsers(data)
    }

    override fun parseToPhotosList(data: String): MutableList<Photo> {
        return parseToPhoto(data)
    }

    private fun parseToUsers(data: String): MutableList<User> {
        val tempData = mutableListOf<User>()
        try {
            val jsonArray = JSONArray(data)
            (0 until jsonArray.length()).forEach {
                val item = jsonArray.getJSONObject(it)
                tempData.add(itemToUser(item))
            }
        } catch (e: JSONException) {
            e.printStackTrace()

        }
        return tempData
    }

    private fun itemToUser(item: JSONObject): User {
        return User(
            item.getString("id").toInt(),
            item.getString("name")
        )
    }

    private fun parseToPhoto(data: String): MutableList<Photo> {
        val tempData = mutableListOf<Photo>()
        try {
            val jsonArray = JSONArray(data)
            (0 until jsonArray.length()).forEach {
                val item = jsonArray.getJSONObject(it)
                tempData.add(itemToPhoto(item))
            }
        } catch (e: JSONException) {
            e.printStackTrace()

        }
        return tempData
    }

    private fun itemToPhoto(item: JSONObject): Photo {
        return Photo(
            item.getString("albumId").toInt(),
            item.getString("id").toInt(),
            item.getString("title"),
            item.getString("url")
        )
    }


}