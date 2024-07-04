package umc.study.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import umc.study.domain.eums.StoreType;

public class StoreRequestDTO {
    @Getter
    public static class StoreDTO{
        @NotBlank
        String name;
        @NotBlank
        String address;
        StoreType storeType;
        @NotBlank
        String phoneNumber;
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
