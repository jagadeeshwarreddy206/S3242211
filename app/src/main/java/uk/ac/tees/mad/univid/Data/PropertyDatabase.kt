package uk.ac.tees.mad.univid.Data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PropertyData::class], version = 1, exportSchema = false)
abstract class PropertyDatabase : RoomDatabase() {
    abstract fun propertyDao(): PropertyDao

}