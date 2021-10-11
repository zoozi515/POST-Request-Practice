package com.example.postrequestpractice

import com.google.gson.annotations.SerializedName

class Users {
    var data: List<UserDetails>? = null

    class UserDetails {

        @SerializedName("name")
        var name: String? = null

        @SerializedName("location")
        var location: String? = null

        @SerializedName("pk") // Primary Id
        var pk: Int? = null

        constructor(name: String?, location: String?, userID: Int?) {
            this.name = name
            this.location = location
            this.pk = pk
        }
    }
}