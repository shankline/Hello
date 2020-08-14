package com.example.myapplication

data class RegistractionResponse(
    @SerializedName("message") var message: String,
    @SerializedName("student") var student: student
)
