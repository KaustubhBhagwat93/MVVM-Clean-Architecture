package com.example.mvvmcleanarchitecture.feature_note.data.domain.use_case

import com.example.mvvmcleanarchitecture.feature_note.data.domain.model.Note
import com.example.mvvmcleanarchitecture.feature_note.data.domain.util.NoteOrder
import com.example.mvvmcleanarchitecture.feature_note.data.domain.util.OrderType
import com.example.mvvmcleanarchitecture.feature_note.data.repository.MockNoteRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetNotesTest {

    private lateinit var getNotes : GetNotes
    private lateinit var mockNoteRepository: MockNoteRepository


    @Before
    fun setUp(){
        mockNoteRepository = MockNoteRepository()
        getNotes = GetNotes(mockNoteRepository)

        val notesToInsert = mutableListOf<Note>()
        ('a' ..'z').forEachIndexed {index, c ->
            notesToInsert.add(
                Note(
                    title = c.toString(),
                    content = c.toString(),
                    timeStamp = index.toLong(),
                    color = index
                )
            )
        }
        notesToInsert.shuffle()
        runBlocking {
            notesToInsert.forEach {
                mockNoteRepository.insertNote(it)
            }
        }
    }

    @Test
    fun `Order notes by title ascending, correct order`()= runBlocking{
         val notes = getNotes(NoteOrder.Title(OrderType.Ascending)).first()

        for (i in 0.. notes.size-2){
            assertThat(notes[i].title).isLessThan(notes[i+1].title)
        }
    }

    @Test
    fun `Order notes by title descending, correct order`()= runBlocking{
         val notes = getNotes(NoteOrder.Title(OrderType.Descending)).first()

        for (i in 0.. notes.size-2){
            assertThat(notes[i].title).isGreaterThan(notes[i+1].title)
        }
    }

    @Test
    fun `Order notes by date ascending, correct order`()= runBlocking{
         val notes = getNotes(NoteOrder.Date(OrderType.Ascending)).first()

        for (i in 0.. notes.size-2){
            assertThat(notes[i].timeStamp).isLessThan(notes[i+1].timeStamp)
        }
    }

    @Test
    fun `Order notes by date descending, correct order`()= runBlocking{
         val notes = getNotes(NoteOrder.Date(OrderType.Descending)).first()

        for (i in 0.. notes.size-2){
            assertThat(notes[i].timeStamp).isGreaterThan(notes[i+1].timeStamp)
        }
    }

    @Test
    fun `Order notes by color ascending, correct order`()= runBlocking{
         val notes = getNotes(NoteOrder.Color(OrderType.Ascending)).first()

        for (i in 0.. notes.size-2){
            assertThat(notes[i].color).isLessThan(notes[i+1].color)
        }
    }

    @Test
    fun `Order notes by color descending, correct order`()= runBlocking{
         val notes = getNotes(NoteOrder.Color(OrderType.Descending)).first()

        for (i in 0.. notes.size-2){
            assertThat(notes[i].color).isGreaterThan(notes[i+1].color)
        }
    }
}