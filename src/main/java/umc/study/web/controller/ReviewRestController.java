package umc.study.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.study.apiPayload.ApiResponse;
import umc.study.converter.ReviewConverter;
import umc.study.domain.Review;
import umc.study.service.ReviewService.ReviewCommandService;
import umc.study.web.dto.ReviewRequestDTO;
import umc.study.web.dto.ReviewResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/mypage/reviews")
@RequiredArgsConstructor
public class ReviewRestController {

    private final ReviewCommandService reviewCommandService;

    @PostMapping("/")
    public ApiResponse<ReviewResponseDTO.ReviewResultDto> createReview(@RequestBody @Valid ReviewRequestDTO.ReviewDto request) {
        Review review = reviewCommandService.addReview(request);
        return ApiResponse.onSuccess(ReviewConverter.toReviewResultDto(review));
    }

    @GetMapping("/")
    public ApiResponse<List<ReviewResponseDTO.ReviewResultDto>> getMyReviews() {
        List<Review> reviews = reviewCommandService.getMyReviews(); // 해당 유저의 리뷰 가져오기
        List<ReviewResponseDTO.ReviewResultDto> responseDTOs = reviews.stream()
                .map(ReviewConverter::toReviewResultDto)
                .collect(Collectors.toList());
        return ApiResponse.onSuccess(responseDTOs);
    }
}