# Adaptor

## 역할

- Database, Third Party App과의 통신을 담당하는 모듈의 집합이다.
- Application, Domain 모듈을 참조할 수 있으며, Application 모듈에 작성된 Outport Interface 를 참조해 구현한다.
- Adaptor 내 모듈은 일반적으로 `외부 리소스를 대상 CURD`와 `도메인 모델로의 Mapping` 두 역할을 갖는다.
- MySQL, Mongo와 같은 플랫폼 단위로 모듈이 생성되기 때문에 해당 플랫폼에 종속된 테스팅 룰을 갖는다.
    - 일반적으로 RDB를 테스트할 때 In-MemoryDB인 H2를 사용하지만, MySQL 모듈은 실제 MySQL에 CURD 행위를 수행한다.
    - 이때, 플랫폼 환경은 [TestContainers](https://testcontainers.com/)와 [Docker](https://www.docker.com/)를 활용해서 구성한다.
    - 그리고 스키마 등에 대한 테스트 환경은 Entity Definition과 JPA에서 제공하는 ddl-auto를 사용해 구성할 수도 있고, 별도 SQL 파일로도 구성할 수 있다.