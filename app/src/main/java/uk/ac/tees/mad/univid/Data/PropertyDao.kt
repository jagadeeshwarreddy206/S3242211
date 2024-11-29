package uk.ac.tees.mad.univid.Data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PropertyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProperty(data: PropertyData)

    @Delete
    suspend fun deleteProperty(data: PropertyData)

    @Query("SELECT * FROM property_table")
    fun getAllProperties(): Flow<List<PropertyData>>

}