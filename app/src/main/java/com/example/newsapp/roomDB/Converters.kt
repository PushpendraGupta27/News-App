package com.example.newsapp.roomDB

import androidx.room.TypeConverters
import com.example.newsapp.model.response.Source

class Converters {
    @TypeConverters
    fun fromSource(source: Source): String? {
        return source.name
    }

    @TypeConverters
    fun toSource(name: String): Source {
        return Source(name, name)
    }
}
