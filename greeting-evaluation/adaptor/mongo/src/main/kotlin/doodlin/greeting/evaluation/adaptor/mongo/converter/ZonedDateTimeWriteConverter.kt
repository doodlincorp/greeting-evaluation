package doodlin.greeting.evaluation.adaptor.mongo.converter

import org.springframework.core.convert.converter.Converter
import java.time.ZonedDateTime
import java.util.*

internal class ZonedDateTimeWriteConverter : Converter<ZonedDateTime, Date> {
    override fun convert(source: ZonedDateTime): Date {
        return Date.from(source.toInstant())
    }
}
