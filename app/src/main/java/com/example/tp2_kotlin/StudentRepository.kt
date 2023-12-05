package com.example.tp2_kotlin

class StudentRepository {
    companion object {
        fun getStudentsCours(): ArrayList<Student> {
            return arrayListOf(
                Student("Amine", "BEN MOUSSA", "Homme"),
                Student("Amine", "BEN MOUSSA", "Homme"),
                Student("Eya", "BEN MOHAMED", "Femme"),
                Student("Amine", "BEN MOUSSA", "Homme"),
                Student("Amine", "BEN MOUSSA", "Homme"),
                Student("Amine", "BEN MOUSSA", "Homme"),
                Student("Eya", "BEN MOHAMED", "Femme"),
                Student("Eya", "BEN MOHAMED", "Femme"),
                Student("Amine", "BEN MOUSSA", "Homme"),
                Student("Eya", "BEN MOHAMED", "Femme"),
                Student("Amine", "BEN MOUSSA", "Homme"),
                Student("Eya", "BEN MOHAMED", "Femme"),
            )
        }

        fun getStudentsTP(): ArrayList<Student> {
            return arrayListOf(
                Student("Mohamed", "BEN MOUSSA", "Homme"),
                Student("Mohamed", "BEN MOUSSA", "Homme"),
                Student("mariem", "BEN MOHAMED", "Femme"),
                Student("Mohamed", "BEN MOUSSA", "Homme"),
                Student("Mohamed", "BEN MOUSSA", "Homme"),
                Student("Mohamed", "BEN MOUSSA", "Homme"),
                Student("mariem", "BEN MOHAMED", "Femme"),
                Student("mariem", "BEN MOHAMED", "Femme"),
                Student("Mohamed", "BEN MOUSSA", "Homme"),
                Student("mariem", "BEN MOHAMED", "Femme"),
                Student("Mohamed", "BEN MOUSSA", "Homme"),
                Student("mariem", "BEN MOHAMED", "Femme"),
            )
        }
    }

}