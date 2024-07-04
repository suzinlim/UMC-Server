package umc.study.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.apiPayload.exception.handler.FoodCategoryHandler;
import umc.study.converter.MemberConverter;
import umc.study.converter.MemberPreferConverter;
import umc.study.converter.StoreConverter;
import umc.study.domain.FoodCategory;
import umc.study.domain.Member;
import umc.study.domain.Region;
import umc.study.domain.Store;
import umc.study.domain.mapping.MemberPrefer;
import umc.study.repository.FoodCategoryRepository;
import umc.study.repository.MemberRepository;
import umc.study.repository.RegionRepository;
import umc.study.repository.StoreRepository;
import umc.study.web.dto.MemberRequestDTO;
import umc.study.web.dto.StoreRequestDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreCommandServiceImpl implements StoreCommandService {

    private final StoreRepository storeRepository;
    private final RegionRepository regionRepository;

    @Override
    @Transactional
    public Store addStore(StoreRequestDTO.StoreDTO request) {
        Region region = regionRepository.findByAddressContaining(request.getAddress())
                .orElseThrow(() -> new IllegalArgumentException("해당 주소에 대한 지역을 찾을 수 없습니다. 주소: " + request.getAddress()));

        Store newStore = StoreConverter.toStore(request);
        newStore.setRegion(region);

        return storeRepository.save(newStore);
    }
}
