package umc.study.web.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import umc.study.domain.Store;
import umc.study.domain.eums.StoreStatus;
import umc.study.domain.eums.StoreType;
import umc.study.validation.annotation.ExistMember;

import java.time.LocalDate;
import java.util.List;

public class StoreRequestDTO {
    @Getter
    public static class StoreDTO{
        @NotBlank
        String name;
        @NotBlank
        String address;
        @NotNull
        StoreType storeType;
        @NotNull
        StoreStatus storeStatus;
        @NotBlank
        String phoneNumber;
        @NotNull
        Integer minimumPrice;
        @NotNull
        Float score;
    }

    @Getter
    public static class MissionDTO {
        @NotNull
        Integer reward;
        @NotNull
        LocalDate deadline;
        @NotBlank
        String missionDetail;
        Long storeId;
    }

    @Getter
    public static class ReveiwDTO {
        @NotNull
        String storeName;

        @NotNull
        Integer score;

        List<String> imageUrl;

        @NotNull
        @Size(min = 1, max = 500)
        String content;

        @ExistMember
        Long memberId;
    }
}