package umc.study.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.apiPayload.exception.handler.MemberHandler;
import umc.study.apiPayload.exception.handler.StoreHandler;
import umc.study.converter.StoreConverter;
import umc.study.domain.*;
import umc.study.repository.*;
import umc.study.web.dto.StoreRequestDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class StoreCommandServiceImpl implements StoreCommandService {

    private final StoreRepository storeRepository;
    private final RegionRepository regionRepository;
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;

    // 가게 추가
    @Override
    @Transactional
    public Store addStore(StoreRequestDTO.StoreDTO request) {
        Region region = regionRepository.findByAddressContaining(request.getAddress())
                .orElseGet(() -> {
                    // 임의의 지역 추가, 나중에 주소에서 지역 추출해서 Region 추가 필요
                    Region region1 = Region.builder()
                            .name("서울")
                            .address(request.getAddress())
                            .build();
                    return regionRepository.save(region1);
                });

        Store newStore = StoreConverter.toStore(request);
        newStore.setRegion(region);

        return storeRepository.save(newStore);
    }

    // 리뷰 추가
    @Override
    @Transactional
    public Review addReview(StoreRequestDTO.ReveiwDTO request) {
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));

        Review review = StoreConverter.toReview(request, member);
        return reviewRepository.save(review);
    }

    @Override
    public List<Review> getMyReviews() {
        return reviewRepository.findAll();
    }

    // 미션 추가
    @Override
    public Mission addMission(StoreRequestDTO.MissionDTO request) {
        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new StoreHandler(ErrorStatus.STORE_NOT_FOUND));

        Mission mission = StoreConverter.toMission(request, store);
        return missionRepository.save(mission);
      
    @Override
    public Review createReview(Long memberId, Long storeId, StoreRequestDTO.ReveiwDTO request) {

        Review review = StoreConverter.toReview(request);

        review.setMember(memberRepository.findById(memberId).get());
        review.setStore(storeRepository.findById(storeId).get());

        return reviewRepository.save(review);
    }
}
