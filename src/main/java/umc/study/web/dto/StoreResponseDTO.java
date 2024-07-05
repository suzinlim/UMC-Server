package umc.study.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class StoreResponseDTO {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor

    public static class AddStoreResultDTO{
        Long storeId;
        LocalDateTime createdAt;

    public static class ReviewPreViewListDTO{
        List<ReviewPreViewDTO> reviewList;
        Integer listSize;
        Integer totalPage;
        Long totalElements;
        Boolean isFirst;
        Boolean isLast;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AddMissionResultDTO{
        Long storeId;
        Long missionId;
        LocalDateTime createdAt;

    public static class ReviewPreViewDTO{
        String ownerNickname;
        Float score;
        String content;
        LocalDate createdAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor

    public static class ReviewResultDto {
        Long memberId;
        LocalDateTime createdAt;
        LocalDateTime updatedAt;

    public static class CreateReviewResultDTO{
        Long reviewId;
        LocalDateTime createdAt;
    }
}