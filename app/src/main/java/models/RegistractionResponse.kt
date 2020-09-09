package models

data class RegistractionResponse(
    @SerializedName("message") var message: String,
    @SerializedName("student") var student: student
)
