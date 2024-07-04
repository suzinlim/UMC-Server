package umc.study.web.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import umc.study.domain.Region;
import umc.study.domain.eums.StoreStatus;
import umc.study.domain.eums.StoreType;

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
    public static class ReveiwDTO{
        @NotBlank
        String title;
        @NotNull
        Float score;
        @NotBlank
        String body;
    }
}
