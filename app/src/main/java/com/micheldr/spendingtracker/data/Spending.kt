package com.micheldr.spendingtracker.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.threeten.bp.OffsetDateTime

@Entity
data class Spending(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long? = null,
    @ColumnInfo(name = "value") val value: Int,
    @ColumnInfo(name = "reason") val reason: String,
    @ColumnInfo(name = "date") val date: OffsetDateTime? = null,
    @ColumnInfo(name = "highlight") val highlight:Boolean = false
)