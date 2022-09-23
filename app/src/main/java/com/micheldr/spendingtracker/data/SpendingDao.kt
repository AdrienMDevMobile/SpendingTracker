package com.micheldr.spendingtracker.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.micheldr.spendingtracker.data.Spending
import org.threeten.bp.OffsetDateTime

@Dao
interface SpendingDao {
    @Query("SELECT * FROM spending order by date")
    suspend fun getAll(): List<Spending>

    @Query("SELECT * FROM spending where date between :dateBegin AND :dateEnd")
    suspend fun getByDate(dateBegin: OffsetDateTime, dateEnd:OffsetDateTime): List<Spending>

    @Insert
    suspend fun insertAll(vararg spendings: Spending)

    /*
    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<User>

    FIND By something
    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): User

    @Delete
    fun delete(user: User)
     */
}