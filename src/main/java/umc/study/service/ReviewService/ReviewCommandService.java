package umc.study.service.ReviewService;

import umc.study.domain.Review;
import umc.study.web.dto.ReviewRequestDTO;

import java.util.List;

public interface ReviewCommandService {
    Review addReview(ReviewRequestDTO.ReviewDto request);
    List<Review> getMyReviews();
}