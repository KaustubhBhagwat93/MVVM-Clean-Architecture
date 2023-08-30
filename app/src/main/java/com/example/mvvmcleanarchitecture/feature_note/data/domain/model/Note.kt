package com.example.mvvmcleanarchitecture.feature_note.data.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mvvmcleanarchitecture.theme.BabyBlue
import com.example.mvvmcleanarchitecture.theme.LightGreen
import com.example.mvvmcleanarchitecture.theme.RedOrange
import com.example.mvvmcleanarchitecture.theme.RedPink
import com.example.mvvmcleanarchitecture.theme.Violet

@Entity
data class Note(
    val title: String,
    val content: String,
    val timeStamp: Long,
    val color: Int,
    @PrimaryKey val id: Int? = null
){
    companion object{
        val notesColors = listOf(RedOrange,LightGreen,Violet, BabyBlue,RedPink)
    }
}
