package Activity

import Api.ApiClient
import Api.ApiInterface
import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.example.myapplication.R
import kotlinx.android.synthetic.main.fragment_second.*
import models.Course
import models.LoginResponse
import okhttp3.*

class MainActivity : AppCompatActivity() {

    fun onCreate(savedInstanceState: Bundle?, tvRegister: Any) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvRegister.setOnClickListener {
            val intent = Intent(baseContext, RegistrationActivity::class.java)
            startActivity(intent)
        }

        btnLogin.setOnClickListener {
            var email = edUserName.text.toString()
            var password = edConfirmPassword.text.toString()

            val requestBody = MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("email", email)
                .addFormDataPart("password", password)
                .build()

            loginUser(requestBody,)
        }
    }

    fun loginUser(requestBody: RequestBody, awesomeValidation: Any){
        val apiClient = ApiClient.buildService(ApiInterface::class.java)
        val loginCall = apiClient.loginstudent(requestBody)

        loginCall.enqueue(object : Callback<LoginResponse> {
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful){
                    Toast.makeText(baseContext, response.body()?.message, Toast.LENGTH_LONG).show()
                    var accessToken = response.body()?.accessToken
                    var sharedPreferences = PreferenceManager.getDefaultSharedPreferences(baseContext)
                    var editor = sharedPreferences.edit()
                    editor.putString("ACCESS_TOKEN_KEY", accessToken)
                    editor.apply()
                    val intent = Intent(baseContext, Course::class.java)
                    startActivity(intent)
                    finish()
                }
                else{
                    Toast.makeText(baseContext, response.errorBody().toString(), Toast.LENGTH_LONG).show()
                }
            }
        }
                private void Registractionform() {
            if (awesomeValidation.validate()) {
                Toast.makeText(this, "Registration Successfull", Toast.LENGTH_LONG).show();


            }
        }

            @Override
            public void onClick(View view) {
                if (view == button  login) {
                    Registractionform();
                }
            }
    }
    }

private fun Any.setOnClickListener(function: () -> Unit) {

}
}