package com.example.mvvmcleanarchitecture.feature_note.data.domain.use_case

import com.example.mvvmcleanarchitecture.feature_note.data.domain.model.Note
import com.example.mvvmcleanarchitecture.feature_note.data.repository.MockNoteRepository
import kotlinx.coroutines.runBlocking

import org.junit.Before
import org.junit.Test

class DeleteNoteTest {

    private lateinit var mockNoteRepository: MockNoteRepository
    private lateinit var addNote: AddNote
    private lateinit var deleteNote: DeleteNote


    @Before
    fun setUp() {

        mockNoteRepository = MockNoteRepository()
        addNote = AddNote(mockNoteRepository)
        deleteNote = DeleteNote(mockNoteRepository)
    }

    @Test
    fun `delete note test`() = runBlocking {
        val note =
            Note(title = "This is title", content = "This is content", timeStamp = System.currentTimeMillis(), color = 0)
        addNote(note = note)
        deleteNote(note)
    }
}