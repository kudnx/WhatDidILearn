package com.devventure.whatdidilearn.data

import androidx.room.TypeConverter
import com.devventure.whatdidilearn.R

class Converters {
    @TypeConverter
    fun levelToInt(level: UnderstandingLevel): Int {
        return level.ordinal
    }

    @TypeConverter
    fun intToLevel(color : Int): UnderstandingLevel {
        return when(color) {
            UnderstandingLevel.LOW.ordinal    -> UnderstandingLevel.LOW
            UnderstandingLevel.MEDIUM.ordinal -> UnderstandingLevel.MEDIUM
            else  -> UnderstandingLevel.HIGH
        }
    }
}