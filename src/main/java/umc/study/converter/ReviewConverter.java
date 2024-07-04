package umc.study.converter;

import umc.study.domain.Member;
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

    public static Review toReview(ReviewRequestDTO.ReviewDto reviewRequest, Member member) {
        return Review.builder()
                .storeName(reviewRequest.getStoreName())
                .score(reviewRequest.getScore())
                .imageUrls(reviewRequest.getImageUrl().toString())
                .content(reviewRequest.getContent())
                .member(member)
                .build();
    }
}