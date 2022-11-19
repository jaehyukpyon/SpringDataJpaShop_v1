package jpabook.jpashop;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JpashopApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpashopApplication.class, args);
	}

	@Bean
	public Hibernate5Module hibernate5Module() {
		return new Hibernate5Module();
		// 이 설정은 Proxy가 초기화 됐으면 JSON으로 출력하지만, 만약 Proxy가 초기화 되어 있지 않다면 그냥 null로 출력한다.
		// List의 경우에도 size()와 같은 메서드를 호출해서 한 번 초기화했으면 리스트 안의 내용 (엔티티)를 출력하지만, size()와 같은 메서드
		// 를 호출한 적이 없으면 그냥 null로 출력한다. (만약 이 Bean을 등록 안 했다면, List의경우 size()와 같은 메서드로 초기화를 했든 안
		// 했든 무조건 출력한다.
	}

}
