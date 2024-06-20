package umc.study.apiPayload.code.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.study.apiPayload.code.BaseCode;
import umc.study.apiPayload.code.ReasonDTO;

@Getter
@AllArgsConstructor
public enum SuccessStatus implements BaseCode {

    // 가장 일반적인 응답
    _OK(HttpStatus.OK, "COMMON200", "성공입니다."),

    // 멤버 관련 응답
    MEMBER_CREATED(HttpStatus.CREATED, "MEMBER201", "멤버가 성공적으로 생성되었습니다."),
    MEMBER_UPDATED(HttpStatus.OK, "MEMBER2001", "멤버 정보가 성공적으로 업데이트되었습니다."),
    MEMBER_DELETED(HttpStatus.OK, "MEMBER2002", "멤버가 성공적으로 삭제되었습니다."),

    // 게시글 관련 응답
    ARTICLE_CREATED(HttpStatus.CREATED, "ARTICLE201", "게시글이 성공적으로 생성되었습니다."),
    ARTICLE_UPDATED(HttpStatus.OK, "ARTICLE2001", "게시글이 성공적으로 수정되었습니다."),
    ARTICLE_DELETED(HttpStatus.OK, "ARTICLE2002", "게시글이 성공적으로 삭제되었습니다."),
    ARTICLE_FETCHED(HttpStatus.OK, "ARTICLE2003", "게시글이 성공적으로 조회되었습니다."),

    // 인증 관련 응답
    AUTHENTICATION_SUCCESS(HttpStatus.OK, "AUTH200", "인증에 성공했습니다."),
    TOKEN_VALID(HttpStatus.OK, "AUTH2001", "토큰이 유효합니다."),
    ACCESS_GRANTED(HttpStatus.OK, "AUTH2002", "접근이 허가되었습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ReasonDTO getReason() {
        return ReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(true)
                .build();
    }

    @Override
    public ReasonDTO getReasonHttpStatus() {
        return ReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(true)
                .httpStatus(httpStatus)
                .build();
    }
}