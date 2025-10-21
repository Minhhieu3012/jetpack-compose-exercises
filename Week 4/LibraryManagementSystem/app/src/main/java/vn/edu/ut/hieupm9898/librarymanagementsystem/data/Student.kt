package vn.edu.ut.hieupm9898.librarymanagementsystem.data

data class Student(
    val id: String,
    val name: String,
    val borrowBookIds: List<Int> = emptyList()
)