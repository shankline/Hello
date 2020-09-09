package com.example.firstapp

import androidx.appcompat.app.AppCompatActivity
import android.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import Api.ApiClient
import Api.ApiInterface
import Activity.CourseItemClickListener
import com.example.myapplication.Database.HelloDatabase
import okhttp3.Callback

class CoursesActivity : AppCompatActivity(), CourseItemClickListener {
    lateinit var database: HelloDatabase
    override R.layout.activity_courses)
        database = Room.databaseBuilder(baseContext, HelloDatabase::class.java, "hello-db").build()
        fetchCourses()
    }

    fun fetchCourses() {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(baseContext)
        val accessToken = sharedPreferences.getString("ACCESS_TOKEN_KEY", "")

        val apiClient = ApiClient.buildService(ApiInterface::class.java)
        val coursesCall = apiClient.getCourses("Bearer " + accessToken)
        coursesCall.enqueue(object : Callback<CoursesResponse> {
            override fun onFailure(call: Call<CoursesResponse>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()
                fetchCoursesFromDatabase()
            }

            override fun onResponse(call: Call<CoursesResponse>, response: Response<CoursesResponse>) {
                if (response.isSuccessful) {
                    var courseList = response.body()?.courses as List<Course>
                    Thread {
                        courseList.forEach { course ->
                            database.courseDao().insertCourse(course)
                        }
                    }.start()

                    displayCourses(courseList)
                } else {
                    Toast.makeText(baseContext, response.errorBody().toString(), Toast.LENGTH_LONG)
                        .show()
                }
            }
        })
    }

    fun fetchCoursesFromDatabase(){
        Thread{
            val courses = database.courseDao().getAllCourses()

            runOnUiThread {
                displayCourses(courses)
            }
        }.start()
    }

    fun displayCourses(courses: List<Course>){
        var coursesAdapter = CoursesAdapter(courses, this)
        rvCourses.layoutManager = LinearLayoutManager(baseContext)
        rvCourses.adapter = coursesAdapter
    }

    override fun onItemClick(course: Course) {
        //obtain student id from shared preferences
        //courseId = course.courseId
        //make a post request https://github.com/owuor91/registration-api
    }
}