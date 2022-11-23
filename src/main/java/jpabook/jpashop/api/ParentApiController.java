package jpabook.jpashop.api;

import jpabook.jpashop.domain.Parent;
import jpabook.jpashop.repository.ParentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ParentApiController {

    private final ParentRepository parentRepository;

    @GetMapping(value = "/api/parent/v1")
    public Parent parentV1() {
        Parent p1 = parentRepository.findOne(1000L);
        Parent p2 = parentRepository.findOne(2000L);

        p1.getChildList().size(); // Parent table에 1000, 2000이 저장 돼 있고 이 메서드 호출 순간 IN Query가 발생 된다. select c.* from child c where c.parent_id IN (1000, 2000) 이런 식으로...

        return p1;
    }

}
