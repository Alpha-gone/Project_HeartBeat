# API

## 사용자 인증 관련

|Endpoint|Method|Description|
|:---|:---|:---|
|/auth/signup|POST|회원가입|
|/auth/signin|POST|로그인|
|/auth/signout|POST|로그아웃|

## 사용자 정보

|Endpoint|Method|Description|
|:---|:---|:---|
|/user/info|GET|사용자 정보 조회|
|/user/update|PUT|사용자 정보 수정|
|/user/delete|DELETE|사용자 삭제|

## 그룹 관련

|Endpoint|Method|Description|
|:---|:---|:---|
|/group/list|GET|그룹 목록 조회|
|/group/info|GET|그룹 정보 조회|
|/group/userlist|GET|그룹 멤버 목록 조회|
|/group/create|POST|그룹 생성|
|/goup/update|PUT|그룹 정보 수정|
|/group/delete|DELETE|그룹 삭제|
|/group/join|POST|그룹 가입|
|/group/leave|POST|그룹 탈퇴|

## 메세지 관련

|Endpoint|Method|Description|
|:---|:---|:---|
|/message/list|GET|메세지 목록 조회|
|/message/create|POST|메세지 생성|
|/message/update|PUT|메세지 수정|
|/message/delete|DELETE|메세지 삭제|