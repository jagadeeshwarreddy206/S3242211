package uk.ac.tees.mad.univid.Data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import uk.ac.tees.mad.univid.Converters

@Database(entities = [PropertyData::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class PropertyDatabase : RoomDatabase() {
    abstract fun propertyDao(): PropertyDao

}