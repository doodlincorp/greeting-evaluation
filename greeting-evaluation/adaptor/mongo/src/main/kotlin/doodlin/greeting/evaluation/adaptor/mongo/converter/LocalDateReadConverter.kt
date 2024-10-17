package doodlin.greeting.evaluation.adaptor.mongo.converter

import org.springframework.core.convert.converter.Converter
import java.time.LocalDate
import java.time.ZoneOffset
import java.time.ZonedDateTime

internal class LocalDateReadConverter : Converter<LocalDate, ZonedDateTime> {
    override fun convert(date: LocalDate): ZonedDateTime {
        return date.atStartOfDay(ZoneOffset.UTC)
    }

    fun convertNullableDate(date: LocalDate?): ZonedDateTime? {
        return date?.atStartOfDay(ZoneOffset.UTC)
    }
}
