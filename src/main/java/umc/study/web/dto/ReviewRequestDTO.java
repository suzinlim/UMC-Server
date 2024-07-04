package umc.study.web.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import umc.study.validation.annotation.ExistMember;

import java.util.List;

public class ReviewRequestDTO {
    @Getter
    public static class ReviewDto {
        @NotNull
        String storeName;

        @NotNull
        Integer score;

        List<String> imageUrl;

        @NotNull
        @Size(min = 1, max = 500)
        String content;

        @ExistMember
        private Long memberId;
    }
}
