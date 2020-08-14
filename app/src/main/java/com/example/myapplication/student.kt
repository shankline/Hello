package com.example.myapplication

data class student (
    @SerializedName("student_id") var studentId: String,
    @SerializedName("first_name") var firstName: String,
    @SerializedName("last_name") var lastName: String,
    @SerializedName("email") var email: String,
    @SerializedName("phone_number") var phoneNumber: String,
    @SerializedName("image_url") var imageUrl: String
)

annotation class SerializedName(val student: String)

