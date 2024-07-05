package umc.study.converter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import umc.study.domain.*;
import umc.study.web.dto.StoreRequestDTO;
import umc.study.web.dto.StoreResponseDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class StoreConverter {
    public static Store toStore(StoreRequestDTO.StoreDTO request) {
        return Store.builder()
                .name(request.getName())
                .address(request.getAddress())
                .storeType(request.getStoreType())
                .storeStatus(request.getStoreStatus())
                .minimumPrice(request.getMinimumPrice())
                .phoneNumber(request.getPhoneNumber())
                .score(request.getScore())
                .build();
    }

    public static Mission toMission(StoreRequestDTO.MissionDTO request, Store store) {
        return Mission.builder()
                .reward(request.getReward())
                .deadline(request.getDeadline())
                .missionDetail(request.getMissionDetail())
                .store(store)
                .build();
    }

    public static Review toReview(StoreRequestDTO.ReveiwDTO reviewRequest, Member member) {
        return Review.builder()
                .storeName(reviewRequest.getStoreName())
                .score(reviewRequest.getScore())
                .imageUrls(reviewRequest.getImageUrl().toString())
                .content(reviewRequest.getContent())
                .member(member)
                .build();
    }

    public static StoreResponseDTO.AddStoreResultDTO toAddStoreResultDTO(Store store) {
        return StoreResponseDTO.AddStoreResultDTO.builder()
                .storeId(store.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static StoreResponseDTO.AddMissionResultDTO toAddMissionResultDTO(Mission mission) {
        return StoreResponseDTO.AddMissionResultDTO.builder()
                .storeId(mission.getStore().getId())
                .missionId(mission.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static StoreResponseDTO.ReviewResultDto toReviewResultDto(Review review) {
        return StoreResponseDTO.ReviewResultDto.builder()
                .memberId(review.getMember().getId())
                .createdAt(review.getCreatedAt())
                .updatedAt(review.getUpdatedAt())
                .build();
    }
}
