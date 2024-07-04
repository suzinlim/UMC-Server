package umc.study.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import umc.study.domain.common.BaseEntity;
import umc.study.domain.eums.StoreStatus;
import umc.study.domain.eums.StoreType;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Store extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 255)
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "VARCHAR(10)")
    private StoreType storeType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "VARCHAR(15) DEFAULT 'INACTIVE'")
    private StoreStatus storeStatus;

    @Column(name = "minimum_price", nullable = false)
    private Integer minimumPrice;

    @Column(name = "phone_number", nullable = false, length = 15)
    @Pattern(regexp = "^\\+?[0-9. ()-]{7,15}$", message = "유효하지 않은 전화번호입니다.")
    private String phoneNumber;

    @Min(value = 0, message = "최소 점수는 0점입니다.")
    @Max(value = 5, message = "최대 점수는 5점입니다.")
    private Float score;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Mission> missionList = new ArrayList<>();

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Review> reviewList = new ArrayList<>();
}
