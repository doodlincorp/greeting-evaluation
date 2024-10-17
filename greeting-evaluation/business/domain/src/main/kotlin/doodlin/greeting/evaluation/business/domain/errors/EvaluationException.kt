package doodlin.greeting.evaluation.business.domain.errors

enum class EvaluationException(val message: String) {
    NOT_FOUND_EVALUATION("평가 정보가 존재하지 않습니다."),
    NOT_FOUND_EVALUATION_REMIND_SETTING("평가 리마인드 설정 정보가 존재하지 않습니다."),
    NOT_FOUND_EVALUATION_CALCULATION("평가 계산 옵션이 존재하지 않습니다."),
    NOT_FOUND_EVALUATION_TEMPLATE_CALCULATION("평가 템플릿 계산 옵션이 존재하지 않습니다."),
    MUST_HAVE_WEIGHT_VALUE("가중치 계산 옵션에는 가중치 값이 필수입니다."),
    MUST_HAVE_SCORE_VALUE("가중치 계산 옵션에는 점수 값이 필수입니다."),
    CAN_ONLY_MODIFY_OWN_EVALUATION("자신이 작성한 평가만 수정 가능합니다."),
    CAN_ONLY_MODIFY_SAME_OPENING("같은 공고 내 평가만 공개/비공개 설정 가능합니다."),
    CAN_ONLY_DELETE_OWN_EVALUATION("자신이 작성한 평가만 삭제 가능합니다."),
    NOT_FOUND_DETAIL_EVALUATION_PAPER("상세평가지가 존재하지 않습니다."),
    MAX_EVALUATION_PAPERS("[ %s ] 채용 절차 단계의 평가지 갯수가 %s개를 초과했습니다."),
    DIFFERENT_EVALUATION_SIZE("평가의 갯수가 수정 전과 다릅니다."),
    DIFFERENT_DETAIL_EVALUATION_WITH_PAPER_SIZE("평가의 갯수가 평가지의 갯수와 다릅니다."),
    NOT_ALLOW_PASS_APPLICANT("이미 채용이 확정된 지원자 입니다. [새로고침] 후 다시 시도해 주세요."),
    CAN_NOT_SELECT_EVALUATION_OPTION("선택할 수 없는 평가정보입니다."),
    INVALID_EVALUATION_OPTION_SIZE("평가 옵션의 갯수가 %d개 설정되어야 합니다."),
    INVALID_TOTAL_SCORE_SHOW_TYPE("잘못된 전체 평점 보기 형식입니다."),
    INVALID_SCORE_SHOW_TYPE("잘못된 평점 보기 형식입니다."),
    MUST_USE_SCORE_TYPE("점수체계가 활성화되어야 합니다."),
    INVALID_EVALUATION_METHOD("‘점수 체계’와 ‘평가 내용 입력란’ 중 하나는 활성화되어야 합니다."),
    DELETE_REASON_IS_NULL("삭제 사유를 입력해주세요."),
    NOT_PROCESS_EVALUATOR("해당 채용단계의 평가자가 아닙니다."),
    NOT_FOUND_APPLICANT_EVALUATION_STATUS("지원자 평가 정보가 존재하지 않습니다."),
    INVALID_EVALUATION_WEIGHT("상세 평가 가중치의 합은 100% 이어야 합니다."),
    MUST_HAVE_DETAIL_EVALUATION_EXCEPT_MANUAL("수기 계산을 제외한 계산 옵션에서는 상세 평가지가 최소 1개 이상이어야 합니다."),
    NOT_VALID_SCORE_AND_WEIGHT("가중치 계산을 위해서는 상세 평가와 가중치가 함께 있거나 둘 다 없어야 합니다."),
    NOT_FOUND_PROCESS_ON_OPENING_CALCULATION("평가표 계산 옵션이 존재하지 않습니다."),
    NOT_FOUND_EVALUATION_TEMPLATE("평가 템플릿 정보가 존재하지 않습니다."),
    EVALUATION_WEIGHT_ONLY_WEIGHT_SCORE("가중치는 가중치 계산 옵션에서만 입력할 수 있습니다."),
    ;

    fun options(vararg options: Any): String {
        return this.message.format(*options)
    }
}
