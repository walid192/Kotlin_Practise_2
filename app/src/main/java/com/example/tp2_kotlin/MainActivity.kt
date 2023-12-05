package com.example.tp2_kotlin

import android.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tp2_kotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var students: ArrayList<Student> = StudentRepository.getStudentsCours()
    val matieres = listOf<String>("Cours", "TP")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        //spinner
        val spinner: Spinner by lazy { binding.spinner }
        spinner.adapter = ArrayAdapter<String>(this, R.layout.simple_dropdown_item_1line, matieres)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                val toast = Toast.makeText(
                    applicationContext,
                    "Vous avez choisi : ${matieres[position]}",
                    Toast.LENGTH_SHORT
                )
                toast.show()
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {
            }
        }

        //recyclerView
        val recyclerview: RecyclerView = binding.recyclerView
        val adapter = StudentAdapter(this@MainActivity, students)
        recyclerview.layoutManager = LinearLayoutManager(this@MainActivity)
        recyclerview.adapter = adapter


        val seachEditText = binding.search
        val filter = adapter.filter

        seachEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                filter.filter(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                filter.filter(s.toString())
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filter.filter(s.toString())
            }
        })
        val filterCheckBox = binding.filterCheckBox
        filterCheckBox.setOnCheckedChangeListener { _, isChecked ->
            val filteredList = if (isChecked) {
                students.filter { it.isPresent }
            } else {
                students
            }
            adapter.dataFilterList = filteredList as ArrayList<Student>
            adapter.notifyDataSetChanged()
        }
    }
}
