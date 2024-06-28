package umc.study.service.ReviewService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.converter.ReviewConverter;
import umc.study.domain.Review;
import umc.study.repository.MemberRepository;
import umc.study.repository.ReviewRepository;
import umc.study.web.dto.ReviewRequestDTO;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;

    @Override
    @Transactional
    public Review addReview(ReviewRequestDTO.ReviewDto request) {
        Review review = ReviewConverter.toReview(request);
        return reviewRepository.save(review);
    }

    @Override
    public List<Review> getMyReviews() {
        return reviewRepository.findAll();
    }
}
