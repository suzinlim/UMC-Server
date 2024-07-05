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


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.study.apiPayload.ApiResponse;
import umc.study.validation.annotation.ExistStore;
import umc.study.web.dto.StoreResponseDTO;


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
      
    @GetMapping("/{storeId}/reviews")
    @Operation(summary = "특정 가게의 리뷰 목록 조회 API",description = "특정 가게의 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })

    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다!")
    })
    public ApiResponse<StoreResponseDTO.ReviewPreViewListDTO> getReviewList(@ExistStore @PathVariable(name = "storeId") Long storeId){
        return null;
    }
}