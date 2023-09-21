package com.example.mvvmcleanarchitecture.feature_note.data.domain.use_case

import com.example.mvvmcleanarchitecture.feature_note.data.domain.model.InvalidNoteException
import com.example.mvvmcleanarchitecture.feature_note.data.domain.model.Note
import com.example.mvvmcleanarchitecture.feature_note.data.repository.MockNoteRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class AddNoteTest {

    private lateinit var mockNoteRepository: MockNoteRepository
    private lateinit var addNote: AddNote

    @Before
    fun setUp() {
        mockNoteRepository = MockNoteRepository()
        addNote = AddNote(mockNoteRepository)
    }

    @Test(expected = InvalidNoteException::class)
    fun `add note by blank title, InvalidNoteException`() = runBlocking {
        val note = Note(title = "", content = "abc", timeStamp = 0, color = 0)
        addNote(note = note)
        val exception = InvalidNoteException("The title of the note can't be empty.")
        assertThat(exception).hasCauseThat().isInstanceOf(InvalidNoteException::class.java)
        assertThat(exception).hasMessageThat().startsWith("The title")
        assertThat(exception).hasMessageThat().endsWith("can't be empty.")
    }

    @Test(expected = InvalidNoteException::class)
    fun `add note by blank content, InvalidNoteException`() = runBlocking {
        val note = Note(title = "abc", content="" , timeStamp = System.currentTimeMillis() , color = 0)
        addNote(note = note)
        val exception = InvalidNoteException("The content of the note can't be empty.")
        assertThat(exception).hasCauseThat().isInstanceOf(InvalidNoteException::class.java)
        assertThat(exception).hasMessageThat().startsWith("The content")
        assertThat(exception).hasMessageThat().endsWith("can't be empty.")
    }

    @Test
    fun `add correct note, check entry`() = runBlocking {
        val note =
            Note(title = "This is title", content = "This is content", timeStamp = System.currentTimeMillis(), color = 0)
        addNote(note = note)
        assertThat(mockNoteRepository.getNotes().first().size).isEqualTo(1)
        assertThat(mockNoteRepository.getNotes().first()[0].title).contains("This is title")
        assertThat(mockNoteRepository.getNotes().first()[0].content).contains("This is content")
    }
}