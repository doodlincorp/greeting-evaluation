package doodlin.greeting.evaluation.business.domain.model.evaluation_process

import java.time.ZonedDateTime

data class EvaluationRemindSetting(
    val id: Long = 0,
    val workspaceId: Int,
    val openingId: Int,
    val processOnOpeningId: Int,
    val remindAfterNDays: Int,
    val remindTime: String,
    val enableRemind: Boolean = false,
    val createDate: ZonedDateTime = ZonedDateTime.now(),
    val updateDate: ZonedDateTime = ZonedDateTime.now(),
) {
    companion object {
        fun saveOf(
            id: Long = 0,
            workspaceId: Int,
            openingId: Int,
            processOnOpeningId: Int,
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
                enableRemind = enableRemind
            )
        }
    }
}
