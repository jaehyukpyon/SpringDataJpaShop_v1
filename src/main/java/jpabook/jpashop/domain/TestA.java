package jpabook.jpashop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class TestA {

    @Id
    private Long testAId;

    private String testAName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "testb_id")
    private TestB testB;

}
