package umc.study.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.converter.StoreConverter;
import umc.study.domain.Region;
import umc.study.domain.Store;
import umc.study.repository.RegionRepository;
import umc.study.repository.StoreRepository;
import umc.study.web.dto.StoreRequestDTO;

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
}
