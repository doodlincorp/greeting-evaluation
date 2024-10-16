package doodlin.greeting.concept.adaptor.mongo.converter

import org.springframework.core.convert.converter.Converter
import java.time.LocalDate
import java.time.ZonedDateTime

internal class LocalDateWriteConverter : Converter<ZonedDateTime, LocalDate> {
    override fun convert(source: ZonedDateTime): LocalDate {
        return source.toLocalDate()
    }
}
