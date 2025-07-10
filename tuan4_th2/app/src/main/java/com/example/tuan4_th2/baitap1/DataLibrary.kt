package com.example.tuan4_th2.baitap1

data class Book(
    val id: Int,
    val title: String,
    val auth: String
)
open class User(open val name: String)

class Student(override val name: String) : User(name) {

    private val borrowedBook = mutableSetOf<Int>()

    fun borrow(bookId: Int) {
        borrowedBook.add(bookId)
    }

    fun returnBook(bookId: Int) {
        borrowedBook.remove(bookId)
    }

    fun hasBorrowed(bookId: Int) : Boolean = bookId in borrowedBook

}


class Library(private val books: List<Book>) {

    private val stuBorwBook = mutableMapOf<Int, Student>()

    fun getBorwBook(): List<Book> = books.filter { it.id !in stuBorwBook }

    fun getStuBorwBook(student: Student) : List<Book> = books.filter { student.hasBorrowed(it.id) }

    fun borrwBook(bookId: Int,student: Student) {
        if(bookId !in stuBorwBook){
            stuBorwBook[bookId] = student
            student.borrow(bookId)
        }
    }

    fun returnBook(bookId: Int, student: Student){
        if(stuBorwBook[bookId] == student){
            stuBorwBook.remove(bookId)
            student.returnBook(bookId)
        }
    }
}