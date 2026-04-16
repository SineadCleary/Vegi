package ie.setu.vegi.data.room

import android.net.Uri
import androidx.room.TypeConverter
import ie.setu.vegi.data.models.VegStatus
import java.util.Date

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }

    @TypeConverter
    fun fromVegStatus(value: VegStatus): String = value.name

    @TypeConverter
    fun toVegStatus(value: String): VegStatus = VegStatus.valueOf(value)

    @TypeConverter
    fun fromUri(uri: Uri?): String? {
        return uri?.toString()
    }

    @TypeConverter
    fun toUri(uriString: String?): Uri? {
        return uriString?.let { Uri.parse(it) }
    }
}
