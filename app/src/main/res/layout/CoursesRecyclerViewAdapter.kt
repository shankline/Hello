package com.example.firtsapp

import android.view.LayoutInflater
import android.view.SurfaceHolder
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import ...

class CoursesRecyclerViewAdapter(val coursesList: List<Courses>
):
  RecyclerView.Adapter<CoursesRecyclerViewAdapter.NamesViewHolder> (){
    override fun onCreateViewHolder(parent:ViewGroup, viewType: Int): NamesViewHolder{
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.row_course_item, parent,false)
        return NamesViewHolder(item)
    }

    override fun getItemCount(): Int {
       return coursesList.size

}
override fun onBindViewHolder(holder: NamesViewHolder, position: Int){
    holder.rowView.tvCourses.text = coursesList[position].toString()
}
class NamesViewHolder(val rowView: View) : RecyclerView.ViewHolder(rowView)
}

class Courses {

}
