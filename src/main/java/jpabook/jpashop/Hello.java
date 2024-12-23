package jpabook.jpashop;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Hello {

    private String data;

    private String id;

    private String name;

    private LocalDateTime regDt;

    private LocalDateTime updateDt;

}
