package umc.study.converter;

import umc.study.domain.Review;
import umc.study.web.dto.ReviewRequestDTO;
import umc.study.web.dto.ReviewResponseDTO;


public class ReviewConverter {
    public static ReviewResponseDTO.ReviewResultDto toReviewResultDto(Review review) {
        return ReviewResponseDTO.ReviewResultDto.builder()
                .memberId(review.getMember().getId())
                .createdAt(review.getCreatedAt())
                .updatedAt(review.getUpdatedAt())
                .build();
    }

    public static Review toReview(ReviewRequestDTO.ReviewDto reviewRequest) {
        return Review.builder()
                .storeName(reviewRequest.getStoreName())
                .rate(reviewRequest.getRate())
                .imageUrls(reviewRequest.getImageUrl().toString())
                .content(reviewRequest.getContent())
                .build();
    }
}