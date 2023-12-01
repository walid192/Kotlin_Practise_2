package com.example.tp2_kotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StudentAdapter(private val context: Context, private val students: List<Student>) :
    RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
         var textView: TextView
         var imageView: ImageView

        init {
            textView = itemView.findViewById(R.id.text)
            imageView = itemView.findViewById(R.id.Image)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): StudentAdapter.StudentViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.student_item, parent, false)
        return StudentViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: StudentAdapter.StudentViewHolder, position: Int) {
        val currentItem = students[position]
        holder.textView.text = "${currentItem.firstName} ${currentItem.lastName}"
        val genderImageResource = if (currentItem.gender.equals("Homme", ignoreCase = true)) {
            R.drawable.boy
        } else {
            R.drawable.girl
        }
        holder.imageView.setImageResource(genderImageResource)
    }

    override fun getItemCount(): Int {
        return students.size
    }
    }
