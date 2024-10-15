package doodlin.greeting.concept.adaptor.mongo.converter

import org.springframework.core.convert.converter.Converter
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.util.*

internal class ZonedDateTimeReadConverter : Converter<Date, ZonedDateTime> {
    override fun convert(date: Date): ZonedDateTime {
        return date.toInstant().atZone(ZoneOffset.UTC)
    }

    fun convertNullableDate(date: Date?): ZonedDateTime? {
        return date?.toInstant()?.atZone(ZoneOffset.UTC)
    }
}
