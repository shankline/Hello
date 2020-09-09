package Activity

import Api.ApiClient
import Api.ApiInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import  androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.fragment_second.*
import models.RegistractionResponse
import okhttp3.*

class RegistrationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        class RegistrationActivity(
            firstName: String,
            lastName: String,
            email: String,
            phoneNumber: String
        ) : AppCompatActivity() {
            val userName= edUserName.text.toString()
            val password = edPassword.text.toString()
            val passwordConfirmation = edConfirmPassword.text.toString()

            var requestBody = MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("first_name", firstName)
                .addFormDataPart("last_name", lastName)
                .addFormDataPart("email", email)
                .addFormDataPart("phone_number", phoneNumber)
                .addFormDataPart("password", password)
                .build()

            registerUser(requestBody)
            Toast.makeText(baseContext, lastName, Toast.LENGTH_SHORT).show()
        }
    }


    fun registerUser(requestBody: RequestBody) {
        var apiClient = ApiClient.buildService(ApiInterface::class.java)
        var registrationCall = apiClient.registerUser(requestBody).apply {
            enqueue(object : Callback<RegistractionResponse> {
                override fun onFailure(call: Call<RegistractionResponse>, t: Throwable) {
                    Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()
                }

                override fun onResponse(
                    call: Call<RegistractionResponse>,
                    response: Response<RegistractionResponse>
                ) {
                    if (response.isSuccessful) {
                        Toast.makeText(baseContext, response.body()?.message, Toast.LENGTH_LONG).show()
                        startActivity(Intent(baseContext, MainActivity::class.java))
                    } else {
                        Toast.makeText(baseContext, response.errorBody().toString(), Toast.LENGTH_LONG)
                            .show()
                    }
                }
            })
        }
    }

    private fun enqueue(any: Any) {
        TODO("Not yet implemented")
    }
}

private fun Any.registerUser(requestBody: RequestBody): Any {

}

