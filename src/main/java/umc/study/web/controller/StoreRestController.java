package umc.study.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.study.apiPayload.ApiResponse;
import umc.study.converter.MemberConverter;
import umc.study.converter.StoreConverter;
import umc.study.domain.Mission;
import umc.study.domain.Review;
import umc.study.domain.Store;
import umc.study.domain.mapping.MemberMission;
import umc.study.service.MemberService.MemberCommandService;
import umc.study.service.StoreService.StoreCommandService;
import umc.study.web.dto.MemberRequestDTO;
import umc.study.web.dto.MemberResponseDTO;
import umc.study.web.dto.StoreRequestDTO;
import umc.study.web.dto.StoreResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreRestController {

    private final StoreCommandService storeCommandService;
    private final MemberCommandService memberCommandService;

    @PostMapping("/")
    public ApiResponse<StoreResponseDTO.AddStoreResultDTO> createReview(@RequestBody @Valid StoreRequestDTO.StoreDTO request) {
        Store store = storeCommandService.addStore(request);
        return ApiResponse.onSuccess(StoreConverter.toAddStoreResultDTO(store));
    }

    @PostMapping("/missions")
    public ApiResponse<StoreResponseDTO.AddMissionResultDTO> createMission(@RequestBody @Valid StoreRequestDTO.MissionDTO request) {
        Mission mission = storeCommandService.addMission(request);
        return ApiResponse.onSuccess(StoreConverter.toAddMissionResultDTO(mission));
    }

    @PostMapping("/missions/challenge")
    public ApiResponse<MemberResponseDTO.MemberMissionResultDTO> createMemberMission(@RequestBody @Valid MemberRequestDTO.MemberMissionRequestDTO request) {
        MemberMission memberMission = memberCommandService.challengeMission(request);
        return ApiResponse.onSuccess(MemberConverter.toMemberMissionResultDTO(memberMission));
    }

    @PostMapping("/reviews")
    public ApiResponse<StoreResponseDTO.ReviewResultDto> createReview(@RequestBody @Valid StoreRequestDTO.ReveiwDTO request) {
        Review review = storeCommandService.addReview(request);
        return ApiResponse.onSuccess(StoreConverter.toReviewResultDto(review));
    }

    @GetMapping("/reviews")
    public ApiResponse<List<StoreResponseDTO.ReviewResultDto>> getMyReviews() {
        List<Review> reviews = storeCommandService.getMyReviews();
        List<StoreResponseDTO.ReviewResultDto> responseDTOs = reviews.stream()
                .map(StoreConverter::toReviewResultDto)
                .collect(Collectors.toList());
        return ApiResponse.onSuccess(responseDTOs);
    }
}
