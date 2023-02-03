package com.micheldr.spendingtracker.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import java.util.*
import org.threeten.bp.OffsetDateTime

@Dao
interface SpendingDao {
    @Insert
    suspend fun insertAll(vararg spendings: Spending)

    @Query("SELECT * FROM spending order by date desc LIMIT + :amount OFFSET :offset")
    fun getSpendingsPaged(offset:Int, amount : Int): List<Spending>
    //    @Query("SELECT * FROM spending WHERE ROWNUM >=  :offset AND ROWNUM < :offset + :amount order by date")

    @Query("DELETE FROM spending WHERE date > :selectedDate")
    fun deleteBeforeDate(selectedDate: OffsetDateTime)
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