# Una

__Una :: Mobile Service to Help Team Project for DIMIGOIN__
-
[![Build Status](https://travis-ci.org/enoxaiming/Una.svg?branch=master)](https://travis-ci.org/enoxaiming/Una)
<img src="https://img.shields.io/badge/license-Apache-blue.svg" alt="License Apache">


Una는 함께를 뜻하는 라틴어로, 팀프로젝트를 위한 인적 네트워크 구축 및 관리 플랫폼입니다. </br> 교내 학생들의 IT관련 팀프로젝트 수준을 높이기 위해 만든 프로젝트로, 창의 IT 경진대회를 통해 1차적으로 한국디지털미디어고등학교 학생들을 위해 서비스 될 예정입니다. 

##Features
- Facebook을 통한 회원 관리
- 유저 주력 분야 수집 및 카테고리 분류
- 교내와 교외 대회 공지
- ~~프로젝트 진행을 위한 양식 배포~~
- 프로젝트 진행 상황 체크
- 수상시 유저 수상 내역에 기록 및 페이스북 페이지 업로드 (사전 권한 필요)

##Platform
- Android
- iOS (개발자 구함)

##Development Period
2016.10.05 ~ 12.??

##Work to do
###Design
- [ ] 안드로이드 레이아웃 (UI/UX) 디자인
- [ ] 애플리케이션 아이콘 제작
- [ ] 페이스북 페이지 이미지 제작
- [ ] 공지사항 이미지 제작

###Web Backend
- [ ] 유저 관리 DB 관리 -> 유저 카테고리 및 대회 데이터
- [ ] 프로젝트 리스트 관리

###Android
- [x] Facebook API 구현 (_디미고인 API는 추후에 생각_)
- [ ] 안드로이드 레이아웃 구현
  - [x] Splash Page
  - [ ] LoginPage
  - [ ] Setting Page
  - [ ] Project List Page
  - [ ] Project Enroll Page
  - [ ] Project Management Page
  - [ ] Project Apply Page
  - [ ] MyPage
  - [ ] The Hall Of Fame
  - [ ] Competition Info Page
  - [ ] Announcement Page
- [ ] 웹 백엔드 연동
	- [ ] Announcement
	- [ ] User Management
	- [ ] Project Infos
	- [ ] Competition Info
- [ ] 애니메이션 구현
- [ ] 기타 백엔드 처리


##Pages
- Splash Page
- Login Page -> Get User Info Page
- Main Page -> 메인페이지
- Setting Page -> 설정페이지 : 로그아웃 알림 등등
- Project List Page -> Project Apply Page
- Project Enroll Page -> 프로젝트 등록하기 : 담당자 연락 관리
- Project Management Page -> 등록한 프로젝트 관리 (신청한 사람, 프로젝트 정보 추가 및 관리)
- Project Apply Page -> 맘에 드는 프로젝트 신청하기
- MyPage : Profile, 프로젝트 참여 내역 (신청,참여,완료), 자기소개 etc
- The Hall of Fame -> 명예의 전당 : 프로젝트 우수 성공 사례 등
- Competition Info Page -> 대회 정보 페이지
- Announcement Page -> 공지사항

##API
- Retrofit2 - Server Sync
- ButterKnife - DI Framework
- Glide - ImageLoad
- Facebook Login API
- Realm - Mobile DataBase

##Contact
한국디지털미디어고등학교 14기 해킹방어과 하준혁

Email : <enoxaiming@naver.com>

Facebook : <http://www.facebook.com/hajh003>




