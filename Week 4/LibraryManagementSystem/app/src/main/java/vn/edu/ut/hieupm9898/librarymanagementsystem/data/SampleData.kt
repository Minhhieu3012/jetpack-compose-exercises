package vn.edu.ut.hieupm9898.librarymanagementsystem.data

object SampleData {
    val BookList = listOf(
        Book(id = 1, title = "Sách 01", author = "Tac gia A"),
        Book(id = 2, title = "Sách 02", author = "Tac gia B"),
        Book(id = 3, title = "Sách 03", author = "Tac gia C"),
        Book(id = 4, title = "Sách 04", author = "Tac gia D")
    )

    val StudentList = listOf(
        Student(id = "30122005", name = "Phan Minh Hieu", borrowBookIds = listOf(1, 2, 3)),
        Student(id = "12345678", name = "Nguyen Van A", borrowBookIds = listOf(1)),
        Student(id = "87654321", name = "Nguyen Van B", borrowBookIds = emptyList()),
        Student(id = "15672348", name = "Nguyen Van C", borrowBookIds = listOf(3, 4))
    )
}