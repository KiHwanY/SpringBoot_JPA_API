package jpabook.jpashop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {
    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @NotEmpty // null 보안
    private String name;

    @JsonIgnore // 해당 어노테이션을 추가하면 연관관계로 이어진 데이터는 안가져오고 회원 정보만 가져올 수 있게 한다.
    @Embedded // 내장 타입
    private Address address;


    @OneToMany(mappedBy = "member") // 연관관계 주인 설정 -> FK
    private List<Order> orders = new ArrayList<>();


}
