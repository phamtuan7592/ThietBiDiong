package com.example.tuan4_th2.baitap1

import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue


class LibraryViewModel : ViewModel() {
    private val library = Library(
        listOf(
            Book(1, "Dế Mèn Phiêu Lưu Ký", "Tô Hoài"),
            Book(2, "Tuổi Thơ Dữ Dội", "Phùng Quán"),
            Book(3, "Harry Potter", "J.K. Rowling"),
            Book(4, "Đắc Nhân Tâm", "Dale Carnegie")
        )
    )

    private val _students = listOf(
        Student("Nguyễn Văn A"),
        Student("Trần Thị B"),
        Student("Lê Văn C")
    )

    private var _student by mutableStateOf<Student?>(null)
    private var _showAvailableBooks by mutableStateOf(false)

    private val _availableBooks = mutableStateListOf<Book>()
    private val _borrowedBooks = mutableStateListOf<Book>()

    init {
        _availableBooks.addAll(library.getBorwBook())
        _student = _students.firstOrNull()
        refreshBorrowedBooks()
    }

    fun getStudents(): List<Student> = _students

    fun updateStudent(name: String) {
        val selected = _students.find { it.name == name }
        if (selected != null && _student != selected) {
            _student = selected
            _showAvailableBooks = false
            refreshAvailableBooks()
            refreshBorrowedBooks()
        }
    }

    fun borrowedBooks(): List<Book> = _borrowedBooks

    fun getAvailableBooks(): List<Book> = _availableBooks

    fun toggleBorrow(book: Book) {
        _student?.let {
            if (it.hasBorrowed(book.id)) {
                library.returnBook(book.id, it)
            } else {
                library.borrwBook(book.id, it)
            }
            refreshAvailableBooks()
            refreshBorrowedBooks()
        }
    }

    private fun refreshAvailableBooks() {
        _availableBooks.clear()
        _availableBooks.addAll(library.getBorwBook())
    }

    private fun refreshBorrowedBooks() {
        _borrowedBooks.clear()
        _student?.let {
            _borrowedBooks.addAll(library.getStuBorwBook(it))
        }
    }

    fun toggleShowAvailable() {
        _showAvailableBooks = !_showAvailableBooks
    }

    fun isShowingAvailable(): Boolean = _showAvailableBooks

    fun getCurrentStudentName(): String = _student?.name ?: ""
}

