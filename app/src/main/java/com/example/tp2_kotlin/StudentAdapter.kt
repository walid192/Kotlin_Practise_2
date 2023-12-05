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
import java.util.Locale

class StudentAdapter(private val context: Context, private val students: ArrayList<Student>) :
    RecyclerView.Adapter<StudentAdapter.StudentViewHolder>(),Filterable {
    var dataFilterList = ArrayList<Student>()


    init {
        dataFilterList=students
    }

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
        val currentItem = dataFilterList[position]
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
        return dataFilterList.size
    }

    override fun getFilter(): Filter {
        return object : Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    dataFilterList = students
                } else {
                    val resultList = ArrayList<Student>()
                    for (student in students) {
                        if (student.firstName.lowercase(Locale.ROOT)
                                .contains(charSearch.lowercase(Locale.ROOT))
                        ) {
                            resultList.add(student)
                        }
                    }
                    dataFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = dataFilterList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                dataFilterList = results?.values as ArrayList<Student>
                notifyDataSetChanged()
            }

        }
    }


}

