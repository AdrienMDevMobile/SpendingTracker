package com.micheldr.spendingtracker.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import java.util.*

@Dao
interface SpendingDao {
    @Insert
    suspend fun insertAll(vararg spendings: Spending)

    @Query("SELECT * FROM spending order by date LIMIT + :amount OFFSET :offset")
    fun getSpendingsPaged(offset: Int, amount: Int): List<Spending>
    //    @Query("SELECT * FROM spending WHERE ROWNUM >=  :offset AND ROWNUM < :offset + :amount order by date")

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