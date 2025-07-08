package jpabook.jpashop;

import com.fasterxml.jackson.datatype.hibernate5.jakarta.Hibernate5JakartaModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JpashopApplication {

	public static void main(String[] args) {

		Hello hello = new Hello();
		hello.setData("hello");
		String data = hello.getData();

		SpringApplication.run(JpashopApplication.class, args);
	}
	/**
	 * 참고: 앞에서 계족 강조했듯이 정말 간단한 애플리케이션이 아니면 엔티티를 API 응답으로 외부로 노출하는 것은 좋지 않다.
	 *  따라서 Hibernate5JakartaModule 을 사용하기 보다는 DTO로 변환해서 반환하는 것이 더 좋은 방법이다.
	 * * */

	@Bean
	Hibernate5JakartaModule hibernate5JakartaModule(){
		Hibernate5JakartaModule hibernate5Module = new Hibernate5JakartaModule();
		//강제 지연 로딩 설정
//		hibernate5Module.configure(Hibernate5JakartaModule.Feature.FORCE_LAZY_LOADING, true);
		return hibernate5Module;
	}

}
