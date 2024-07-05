package umc.study.service.StoreService;

import umc.study.domain.Mission;
import umc.study.domain.Region;
import umc.study.domain.Review;
import umc.study.domain.Store;
import umc.study.web.dto.StoreRequestDTO;

import java.util.List;

public interface StoreCommandService {
    Store addStore(StoreRequestDTO.StoreDTO request);

    Review addReview(StoreRequestDTO.ReveiwDTO request);
    List<Review> getMyReviews();

    Mission addMission(StoreRequestDTO.MissionDTO request);

    Review createReview(Long memberId, Long storeId, StoreRequestDTO.ReveiwDTO request);
}