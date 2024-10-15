# doodlin-spring-boot-template

## Subprojects

이 프로젝트는 [Gradle Multi-Project Builds](https://docs.gradle.org/current/userguide/multi_project_builds.html) 를 사용하며,
아래와 같은 subprojects 및 디렉토리로 구성되어있습니다.

```
.
├── concept (도메인)
│   ├── adaptor
│   │   ├── kafka
│   │   ├── mongo
│   │   ├── mysql
│   │   └── redis
│   ├── business
│   │   ├── application
│   │   └── domain
│   └── presentation
│       ├── api
│       ├── batch
│       ├── consumer
│       └── starter

```

---

## Business

- 비즈니스 플로우와 핵심 로직&가치를 다우는 모듈의 집합체다.
- 흔히 도메인 헥사고날이라고 불리우는 영역이다.

### Application

- 비즈니스 플로우와 트랜잭션을 관리하는 라이브러리형 모듈이다.
- 각종 요소들에 대한 DI를 지원하기 위한 Interface와 Usecase 의 구현체를 다룬다.
- Domain 모듈과 트랜잭션 관리를 위해 Spring-tx 라이브러리를 참조할 수 있다.
    - 엄격한 제약으로 하려면 Domain 모듈만 참조해야 하는 것이 올바르다.
    - 하지만 백엔드 팀이 했던 의사결정을 따르기 위해 Spring-tx까지 참조를 허용한다.
        - 모든 프로젝트를 Spring으로 포팅할 것이다.
        - 트랜잭션 관리의 책임을 Application 에서 가지겠다.

#### usecase

- 해당 레파지토리의 핵심 가치를 기준으로 하는 행동 기반의 기능 명세의 집합체 (Interface) 다.
- 시간의 흐름에 따라 세부 구현이 변경될 수 있어 Interface 로 설계한다.
- Presentation 모듈에서 참조해 사용한다.

#### Port

- 내, 외부로 연결되는 통신 경로의 집합체다.

##### Input

- 사용자 또는 시스템의 입력을 받는 Usecase 의 구현체를 다룬다.

##### Output

- 외부 시스템으로 입력을 전달하는 Adaptor를 위한 Interface를 다룬다.

### Domain

- 핵심 비즈니스 로직을 다루는 라이브러리형 모듈이다.
- 도메인 모델 및 서비스, DTO를 포함한다.
- 어떠한 모듈과 라이브러리도 참조하지 않는다.

## Adaptor

- 흔히 인프라스트럭쳐 레이어로 불리우는 영역이다.
- 외부 자원과 통신을 담당한다.
- Application, Domain 모듈을 참조할 수 있다.
- Application 모듈에 작성된 Outport Interface 를 참조해 구현한다.

### kafka

- kafka와의 통신을 담당하는 모듈이다.
- 해당 모듈은 두들린 카프카 라이브러리 모듈을 참조할 수 있다.

### mongo

- mongo와의의 통신을 담당하는 모듈이다.

### mysql

- mysql과의 통신을 담당하는 모듈이다.

### redis

- redis와의 통신을 담당하는 모듈이다.
-

## Presentation

- 단독 서버로 사용될 수 있거나, 서버를 로드하는 데 필요한 starter 라이브러리를 관리하는 모듈의 집합체이다.

### api

- HTTP Interface를 담당하는 서버형 모듈이다.
- 서버형 모듈을 제외한 모든 의존성을 참조할 수 있다.

### batch

- Batch Processing을 담당하는 서버형 모듈이다.
- 서버형 모듈을 제외한 모든 의존성을 참조할 수 있다.

### consumer

- AMQP Interface를 담당하는 서버형 모듈이다.
- 서버형 모듈을 제외한 모든 의존성을 참조할 수 있다.

### starter

- AWS 설정과 같이 서버형 모듈을 로드하기 위한 기본 의존성을 포함하는 모듈이다.

## SDK (Optional)

- 해당 Repository를 포함한 다른 Repository의 유지보수 편의성을 위한 Software development kit 이다.
- 해당 집합체는 선택에 의해 구성하는 것을 권장한다.

### client

- 외부 도메인에서 내 도메인을 쉽게 참조할 수 있도록 지원하는 라이브러리형 모듈이다.
- 기본적으로는 내 도메인에서 제공하는 HTTP API에 대한 구현체를 제공한다.

### model

- 외부 도메인에서 내 도메인을 쉽게 참조할 수 있도록 지원하는 라이브러리형 모듈이다.
- 주로 이벤트, 메시지, Payload를 제공한다.

---

## 해당 프로젝트를 사용하여 새로운 프로젝트를 생성할 시 해야할 것

1. concept을 검색하여 새로 생성할 서비스 이름으로 바꾼다 (예: ats, interview, integration)
2. 패키지 이름이 doodlin.greeting.concept 으로 되어 있으므로, 해당 패키지를 검색하여 새로 생성할 서비스 이름으로 바꾼다 (예: doodlin.greeting.interview)
3. 사용할 모듈에 대한 배포 git workflow를 작성한다. (예: greeting-api, greeting-batch, greeting-consumer)
