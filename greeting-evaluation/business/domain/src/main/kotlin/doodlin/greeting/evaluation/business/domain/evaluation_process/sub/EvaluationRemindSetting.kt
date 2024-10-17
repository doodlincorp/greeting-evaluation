package doodlin.greeting.evaluation.business.domain.evaluation_process.sub

import java.time.ZonedDateTime

data class EvaluationRemindSetting(
    val id: Long,
    val workspaceId: Long,
    val openingId: Long,
    val processOnOpeningId: Long,
    val remindAfterNDays: Int,
    val remindTime: String,
    val enableRemind: Boolean,
    val createDate: ZonedDateTime,
    val updateDate: ZonedDateTime,
    val deletedAt: ZonedDateTime?
) {
    companion object {
        fun init(
            id: Long = 0,
            workspaceId: Long,
            openingId: Long,
            processOnOpeningId: Long,
            remindAfterNDays: Int,
            remindTime: String,
            enableRemind: Boolean
        ): EvaluationRemindSetting {
            return EvaluationRemindSetting(
                id = id,
                workspaceId = workspaceId,
                openingId = openingId,
                processOnOpeningId = processOnOpeningId,
                remindAfterNDays = remindAfterNDays,
                remindTime = remindTime,
                enableRemind = enableRemind,
                createDate = ZonedDateTime.now(),
                updateDate = ZonedDateTime.now(),
                deletedAt = null
            )
        }
    }
}
