package Activity

import models.Course

interface CourseItemClickListener {
    fun onItemClick(course: Course)
}