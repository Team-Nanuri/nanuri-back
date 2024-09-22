![image](https://github.com/user-attachments/assets/318b47c6-58cb-4fc5-917f-717b3eda9b23)![image](https://github.com/user-attachments/assets/ce3371cf-07c7-4cbe-a593-36fbc26a0fb5)# 해커핑 - 나누리
## 서비스 요약
> 나눌 이, 나누리!
> 교내 외국인 학생 및 교류 학생을 대상으로 한 나눔 서비스

## 주제 구분
E타입 경북대에 다니는 다양한 배경의 학우들을 위한 서비스

## 팀원 소개
### TEAM: HeackerPing
|성명|스택|
|------|---|
|고희연|프론트엔드 개발|
|손홍석|프론트엔드 개발|
|이상희|백엔드 개발|
|정현정|백엔드 개발|

## 시연 영상
- [Youtube 링크](https://youtu.be/SCFQNbUV3GA)
- [Frontend Repository](https://github.com/Team-Nanuri/nanuri-frontend)

## 서비스 소개
### 서비스 개요

본교에 재학 중인 유학생, 교환/복수학위 학생, 썸머/윈터스쿨 학생들과 타대학 교류학생들을 위한 나눔 서비스이다.
사용자는 나누거나 빌려주고 싶은 물건을 올릴 수 있다,
사용자는 필요한 품목을 검색해 찾아보고, 원하는 상품을 나누는 상대방에게 채팅을 걸어 대화할 수 있다.


### 타서비스와의 차별점

다국어 서비스로 현재 한국어와 영어를 지원하며, 검색 결과가 없는 경우 다른 언어의 검색어를 추천 받아 편하게 검색해볼 수 있다.

### 구현 내용 및 결과물
1. 회원가입과 로그인: Username과 Password를 통해 서비스에 가입하며, 가입 시 KNUIN 학적 정보 화면을 업로드해 AI로 외국인 학생인지 검증받아야 한다.
![image](https://github.com/user-attachments/assets/2efd603f-0e39-4057-be49-d2779e7bf7d4)
![image](https://github.com/user-attachments/assets/50d6f0b3-a5d9-4d46-906e-d9cbef8b2899)

3. 나눔 등록 기능: 사진과 본문, 제목, 나눔 유형과 대여 유형이라면 대여 기간, 물품 카테고리 등의 나눔하고자 하는 정보를 등록한다.
![image](https://github.com/user-attachments/assets/3a42bdef-0b9d-45f6-9c02-cdd19a70cb14)

5. 나눔 조회 기능: 검색을 통해 품목을 검색할 수 있으며, 여러 필터링으로 조건부 검색을 수행할 수 있다.
![image](https://github.com/user-attachments/assets/d052400c-46e3-45ed-b280-8b927be42703)
![image](https://github.com/user-attachments/assets/4fd8f291-d343-4bb6-8089-482ad27beab3)

6. 나눔 좋아요 기능: 원하는 품목을 좋아요하고, 좋아요하는 나눔 품목을 따로 조회할 수 있다.
![image](https://github.com/user-attachments/assets/2c209d4f-7f7b-4c84-8989-4dc3395624b6)

### 구현 방식
프론트엔드
React
Typescript
React-Query (서버와의 비동기 통신 및 캐싱, 사버 상태 관리)
Axios (HTTP 클라이언트)
TailwindCSS (유틸리티 퍼스트 CSS 프레임워크)
Shadcn (UI 컴포넌트 라이브러리)

백엔드
Java & SpringBoot
AWS에 배포하여 EC2와 S3
OpenAI API와 PapagoAPI 활용

## 향후 개선 혹은 발전 방안
- 지원 언어 확장: 영어와 한국어 외에도 지원하는 언어 확장
- 기증자-수혜자 간의 중개 센터 기능을 확장하여 서비스 사용에 있어 일정과 장소에 구애 받지 않고 참여할 수 있도록 개선할 수 있습니다.
- 본교 학생 자치 기구, 국제 학생 지원 센터, 기숙사 운영팀, 대구광역시와의 협력 체계를 구축합니다. 이를 통해 기관은 학생 복지를 증진하거나 자원 효율성을 높일 수 있으며, 나누리 서비스는 사용자를 유치하고 서비스의 지속 가능성을 높입니다.
- 
