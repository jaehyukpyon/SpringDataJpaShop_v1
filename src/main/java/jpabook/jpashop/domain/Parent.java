package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Parent {

    @Id
    private Long parentId;

    private String parentName;

    @OneToMany(mappedBy = "parent")
    private List<Child> childList = new ArrayList<>();

}
