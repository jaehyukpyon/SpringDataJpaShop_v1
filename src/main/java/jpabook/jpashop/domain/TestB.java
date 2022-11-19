package jpabook.jpashop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class TestB {

    @Id
    private Long testBId;

    private String testBName;

    @OneToMany(mappedBy = "testB", fetch = FetchType.LAZY)
    private List<TestA> testAList = new ArrayList<>();

}
