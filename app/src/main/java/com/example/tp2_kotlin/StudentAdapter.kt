package com.example.tp2_kotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StudentAdapter(private val context: Context, private val students: List<Student>) :
    RecyclerView.Adapter<StudentAdapter.StudentViewHolder>(),Filterable {
    class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textView: TextView
        var imageView: ImageView
        var checkbox:CheckBox

        init {
            textView = itemView.findViewById(R.id.text)
            imageView = itemView.findViewById(R.id.Image)
            checkbox = itemView.findViewById(R.id.checkBox)
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
        holder.checkbox.isChecked = currentItem.isPresent

        holder.checkbox.setOnCheckedChangeListener { _, isChecked ->
            currentItem.isPresent = isChecked
        }
    }

    override fun getItemCount(): Int {
        return students.size
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filteredList = mutableListOf<Student>()
                if (constraint == null || constraint.isEmpty()) {
                    filteredList.addAll(students)
                } else {
                    val filterPattern = constraint.toString().lowercase().trim()
                    for (student in students) {
                        if (student.firstName.lowercase()
                                .contains(filterPattern) || student.lastName.lowercase()
                                .contains(filterPattern)
                        ) {
                            filteredList.add(student)
                        }
                    }
                }
                val results = FilterResults()
                results.values = filteredList
                return results
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                students.clear()
                students.addAll(results?.values as List<Student>)
                notifyDataSetChanged()
            }
        }

    }

    fun updateList(filteredList: List<Student>) {
        students.clear()
        students.addAll(filteredList)
        notifyDataSetChanged()

    }
}

private fun <E> List<E>.addAll(es: List<E>) {
    return this.addAll(es)
}

private fun <E> List<E>.clear() {
    return this.clear()
}
