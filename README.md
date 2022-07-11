# Spring Framework의 기본개념이해

## Spring Framework의 특징

**1)IoC(Inversion of Control, 제어역행) Container**

스프링 컨테이너가 필요에 따라 개발자 대신 Bean들을 관리(제어)해주는 행위

Spring에서는 xml파일 또는 어노테이션 방식으로 스프링 컨테이너에 Bean(객체)를 등록하기만 하면, 스프링 컨테이너에서 Bean의 생명주기(생성→의존성 설정→초기화→소멸)을 전부 관리해준다.

![image](https://user-images.githubusercontent.com/74875490/178189554-bf8342fe-893a-4de3-8b55-653e3fe50df0.png)
<br>
→약한 결합을 이용하여 객체 간 의존관계를 쉽게 변경할 수 있다.

→제어의 역전을 통해서 코드의 재사용성과 유지보수성을 높일 수 있다.

**2)POJO(Plain Old Java Object 방식)**

: 단순히 자바빈즈 객체를 의미한다.

**3)Dependency Injection(의존성 주입)**

어떤 객체가 스프링 컨테이너를 통해서 또 다른 객체와 의존성이 맺어지는 행위

- 의존성 주입이 적용되지 않은 경우

```java
<Gun.java>
public class Gun{
}

<Soldier.java>
public class Solider{
	private Gun gun;
	
	public Solider(){
		gun=new Gun();
	}
}
```

Soldier 클래스 내부에서 Gun 객체를 생성하였기 때문에 의존성을 주입받은 것이 아닌, 의존성을 스스로 만든 것이라고 볼 수 있다.

- 의존성 주입을 적용한 경우

```java
<Gun.java>

@Component// 스프링 컨테이너에 Bean으로 등록
public class Gun{
}

<Soldier.java>

public class Soldier{
	@Autowired //스프링 컨테이너에 있는 Gun 타입의 Bean을 주입
	private Gun gun;
}
```

Gun 객체(Bean)을 스프링 컨테이너에 등록하고, 스프링 컨테이너에서 Soldier의 gun 멤버 변수에 Bean을 주입하였다.

![image](https://user-images.githubusercontent.com/74875490/178189606-3479c7ce-3f5a-4394-aa2e-5ee803890ec5.png)
<br>

※참고

[https://velog.io/@damiano1027/Spring-의존성-주입-제어의-역전](https://velog.io/@damiano1027/Spring-%EC%9D%98%EC%A1%B4%EC%84%B1-%EC%A3%BC%EC%9E%85-%EC%A0%9C%EC%96%B4%EC%9D%98-%EC%97%AD%EC%A0%84)

## SpringMVC 구조

![image](https://user-images.githubusercontent.com/74875490/178189675-77081f05-3c8e-4f11-a4f3-4d14899ad6a9.png)
<br>

**Request→DispatcherServlet→HandlerMapping→Controller→Service→DAO→DB→DAO→Service→Controller→DispatcherServlet→ViewResolver→View→Response**

Spring의 동작순서는 다음과 같다.

1.클라이언트가 Request 요청을 하면 DispatcherServlet이 요청을 받는다.

2.DispatcherServlet에서 받은 요청을 HandlerMapping에게 보내 해당 요청을 처리할 수 있는 Controller를 찾는다.

3.실제 로직 처리(Controller→Service→DAO→DB→Service→Controller)

4.로직 처리 후 ViewResolver를 통해 view 화면을 찾는다.

5.View 화면을 최종 클라이언트에게 전송한다.

※참고

[https://intro0517.tistory.com/151](https://intro0517.tistory.com/151)

## **Spring 웹 계층**

API를 만들기 위해서는 총 3개의 클래스가 필요하다.

- Request 데이터를 받을 Dto
- API 요청을 받을 Controller
- 트랜잭션, 도메인 기능 간의 순서를 보장하는 Service

![image](https://user-images.githubusercontent.com/74875490/178189750-46257a45-893d-4fe7-82ea-b885f2c25293.png)
<br>

- Web Layer
    - 흔히 사용하는 컨트롤러(@Controller)와 JSP/Freemaker 등 뷰 템플릿 영역이다.
    - 이외에도 필터(@Filter), 인터셉터, 컨트롤러 어드바이스(@ControllerAdvice) 등 외부요청과응답에 대한 전반적인 영역을 이야기합니다.
- Service Layer
    - @Service에 사용되는 서비스 영역이다.
    - 일반적으로 Controller와 Dao의 중간 영역에서 사용된다.
    - @Transaction이 사용되어야 하는 영역이기도 하다.
- Repository Layer
    - Database와 같이 데이터 저장소에 접근하는 영역이다.
- Dtos
    - 계층 간의 데이터 교환을 위한 객체를 이야기한다.
- Domain Model
    - 개발 대상을 모든 사람이 동일한 관점에서 이해할 수 있고 공유할 수 있도록 단순화시킨것
    - 예를 들어 탭시 앱이라고 하면 배차, 탑승, 요금 등이 모두 도메인이 될 수 있다.