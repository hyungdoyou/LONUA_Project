![header](https://capsule-render.vercel.app/api?type=Waving&color=F7BE81&height=250&section=header&text=👕LONUA👕&desc=All%20For%20Individual%20Customized%20Fashion&descSize=20&descAlign=50&descAlignY=70&fontSize=100&animation=fadeIn&fontColor=B404AE)


<br>

### 📌 기술 스택

<br>

&nbsp;&nbsp;&nbsp;&nbsp;<img src="https://img.shields.io/badge/HTML5-E34F26?style=flat&logo=HTML5&logoColor=white">&nbsp;&nbsp;&nbsp;&nbsp;<img src="https://img.shields.io/badge/CSS-1572B6?style=flat&logo=CSS3&logoColor=white&color=darkblue">&nbsp;&nbsp;&nbsp;&nbsp;<img src="https://img.shields.io/badge/JavaScript-F7DF1E?style=flat&logo=JavaScript&logoColor=black">&nbsp;&nbsp;&nbsp;&nbsp;<img src="https://img.shields.io/badge/Vue-FC08D?style=flat&logo=Vue.js&logoColor=black&color=lightgreen">
&nbsp;&nbsp;&nbsp;&nbsp;<img src="https://img.shields.io/badge/nginx-%23009639.svg?style=flat&logo=nginx&logoColor=white"></a>
&nbsp;&nbsp;&nbsp;&nbsp;<img src="https://img.shields.io/badge/Pinia-0285C9?style=flat&color=dark"></a></a>
&nbsp;&nbsp;&nbsp;&nbsp;<img src="https://img.shields.io/badge/Amazon AWS-232F3E?style=flat&logo=AmazonAWS&logoColor=black&color=orange"/></a></a>
&nbsp;&nbsp;&nbsp;&nbsp;<img src="https://img.shields.io/badge/Amazon S3-569A31?style=flat&logo=Amazon S3&logoColor=white&color=red"/></a></a>

---

<br>

### 💾 프로젝트 설계

### &nbsp;&nbsp;➡ &nbsp;[ 피그마(Figma) 바로가기](https://www.figma.com/file/y0feF1Z0msfu71CPTd7Xaj/LONUA-FIGMA?type=design&node-id=0-1&mode=design)

<br>

---

### 💻 기능 명세서

<br>
<br>

<details>
<summary><b>🫅🏻 회&nbsp;&nbsp;&nbsp;&nbsp;원</b></summary><br>
    <div>
         <details>
         <summary><b>회원 가입 및 이메일 인증</b></summary>
                  <br>
         <p><b>➡ 회원이 회원 정보 [ 이메일, 패스워드, 이름, 생년월일, 성별, 전화번호, 집주소, 선호 스타일, <br>
         &nbsp;&nbsp;　상체 유형, 하체 유형 ] 를 입력하여 회원 가입을 진행한다.</b></p>
         <p><b>➡ 회원이 입력한 이메일로 온 인증메일을 통해 이메일 인증을 완료한 뒤 로그인한다.</b></p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-2nd-developer_passion-fashion/assets/148875644/9d9cd537-4604-45cf-99d2-7ff0e4c6f420"/></p>
         </details><br>
	 <details>
         <summary><b>본인의 회원 정보 수정</b></summary>
                  <br>
         <p><b>➡ 회원이 마이페이지의 "회원정보 수정" 페이지에서 비밀번호를 입력 후, 비밀번호가 일치하면 <br>
         &nbsp;&nbsp;　회원정보 수정 페이지로 이동된다.</b></p>
         <p><b>➡ 회원이 수정하고 싶은 내용 [ 집 주소, 전화번호, 선호 스타일, 상/하체 유형 등 ] 을 입력하여<br> 
         &nbsp;&nbsp;　회원 정보를 수정한다.</b></p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-2nd-developer_passion-fashion/assets/148875644/97aff7be-60d4-48a7-b39f-1d137c67149a"/></p>
         </details><br>
	 <details>
         <summary><b>회원 탈퇴</b></summary>
                  <br>
         <p><b>➡ 회원이 마이페이지의 "회원 탈퇴" 페이지에서 회원 탈퇴를 진행한다. ( 회원의 상태 "status" 가 False로 변경 )</b></p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-2nd-developer_passion-fashion/assets/148875644/96057624-189b-4b51-82b1-026b6af326fb"/></p>
         </details><br>
	 <details>
         <summary><b> 이메일 로그인</b></summary>
                  <br>
         <p><b>➡ 회원이 [ 이메일, 패스워드 ] 를 입력하여 로그인을 시도한다.</b></p>
         <p><b>➡ 로그인이 성공하면 세션 스토리지에 발급받은 jwt 토큰이 저장된다.</b></p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-2nd-developer_passion-fashion/assets/148875644/aaf4e0be-dd46-4ee0-997b-a7ad85af33ab"/></p>
         </details><br>
	 <details>
         <summary><b>카카오 로그인</b></summary>
                  <br>
         <p><b>➡ 회원이 카카오 계정을 통해 회원가입/로그인 후 서비스를 이용한다.</b></p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-3rd-developer_passion-fashion/assets/148875644/49d732e8-ba59-4359-8e0b-44feb120b302"/></p>
         </details><br>
	 <details>
         <summary><b>쿠폰 조회 및 사용</b></summary>
                  <br>
         <p><b>➡ 회원이 마이페이지의 "받은 쿠폰" 페이지에서 쿠폰 전체 목록을 조회한다.</b></p>
         <p><b>➡ "받은 쿠폰" 은 장바구니에서 주문/결제 전에 적용할 수 있다.</b></p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-2nd-developer_passion-fashion/assets/148875644/306e26d9-c18e-4c34-9276-36dc4bc639e9"/></p>
         </details><br>
	 <details>
         <summary><b>상품 전체 목록 및 상품 세부정보 조회</b></summary>
                  <br>
         <p><b>➡ 회원이 헤더에서 전체 상품의 목록을 조회한다.</b></p>
         <p><b>➡ 회원이 보고싶은 상품을 클릭하여 상품의 세부 정보를 조회한다.</b></p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-3rd-developer_passion-fashion/assets/40519125/baa2c14d-40ea-4f9c-9830-39d1315a9c1d"/></p>
         </details><br>
	 <details>
         <summary><b>브랜드별 상품 목록 조회</b></summary>
                  <br>
         <p><b>➡ 회원이 헤더에서 원하는 "브랜드를 선택" 하여 브랜드 별 상품 목록을 조회한다.</b></p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-3rd-developer_passion-fashion/assets/148875644/ba629cb3-cd0a-41e4-b20b-4ce9d754c4d2"/></p>
         </details><br>
         	 <details>
         <summary><b>카테고리별 상품 목록 조회</b></summary>
                  <br>
         <p><b>➡ 회원이 헤더에서 원하는 "카테고리를 선택" 하여 카테고리 별 상품 목록을 조회한다.</b></p><br>
         <p><img src="https://github.com/hyungdoyou/LONUA_Project/assets/148875644/82be464e-b0d4-4621-90c4-374e8a8ac866"/></p>
         </details><br>
         	 <details>
         <summary><b>스타일별 상품 목록 조회</b></summary>
                  <br>
         <p><b>➡ 회원이 헤더에서 원하는 "스타일을 선택" 하여 스타일 별 상품 목록을 조회한다.</b></p><br>
         <p><img src="https://github.com/hyungdoyou/LONUA_Project/assets/148875644/6e006319-b193-4816-b7e6-2cdf2fbaa608"/></p>
         </details><br>
                  	 <details>
         <summary><b>상/하체 유형 상품 목록 조회</b></summary>
                  <br>
         <p><b>➡ 회원이 회원가입 시 입력한 상/하체 유형에 따라 본인과 동일한 회원들이 많이 구매한 상품 목록을 조회한다.</b></p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-3rd-developer_passion-fashion/assets/148875644/a9893a2a-1d4b-47ee-9d07-fcf2e9ffb833"/></p>
         </details><br>
	 <details>
         <summary><b>상품에 대한 좋아요 기능</b></summary>
                  <br>
         <p><b>➡ 회원이 원하는 상품에 "♡" 클릭하여 "좋아요" 를 누르고, 마이페이지의 "My ♥ Item" 페이지에서 목록을 조회한다.</b></p>
         <p><b>➡ 좋아요 페이지에서 상품 클릭 시 상품 상세 페이지로 이동하며, "좋아요" 취소도 가능하다.</b></p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-2nd-developer_passion-fashion/assets/148875644/0998f808-0a9e-44e0-8b83-61b49fedc20a"/></p>
         </details><br>
	 <details>
         <summary><b>장바구니에 상품 등록</b></summary>
                  <br>
         <p><b>➡ 회원이 상품 상세 페이지에서 원하는 상품을 장바구니에 담는다.</b></p>
          <p><b>➡ 회원이 장바구니 페이지에서 장바구니에 담긴 상품 목록 전체를 조회한다.</b></p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-2nd-developer_passion-fashion/assets/148875644/dc80a4a8-4424-474a-8197-61b24654a7cc"/></p>
         </details><br>
	 <details>
         <summary><b>장바구니에 담긴 상품 1개 삭제 및 전체 삭제</b></summary>
                  <br>
         <p><b>➡ 회원이 장바구니에 담긴 상품 1개를 클릭하여 삭제한다.</b></p>
         <p><b>➡ 회원이 "전체상품 삭제" 버튼을 클릭 하여 장바구니에 담긴 상품 전체를 삭제한다.</b></p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-3rd-developer_passion-fashion/assets/40519125/0650804b-4d39-4c76-a80c-5f5569763681"/></p>
         </details><br>
         	 <details>
         <summary><b>상품 주문 및 결제</b></summary>
                  <br>
         <p><b>➡ 회원이 장바구니에서 원하는 상품만 선택하여 주문한다.</b></p>
         <p><b>➡ 회원이 주문 시 원하는 상품에 쿠폰을 적용하여 할인된 가격으로 주문한다.</b></p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-2nd-developer_passion-fashion/assets/148875644/3af41715-e383-4ed5-9261-5f7ed4e42316"/></p>
         </details><br>
	 <details>
         <summary><b>전체 주문 내역 조회 및 리뷰 작성</b></summary>
                  <br>
         <p><b>➡ 회원이 주문한 상품의 전체 목록을 조회한다.</b></p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-3rd-developer_passion-fashion/assets/148875644/392e92a5-efef-4b47-9728-c619c1b289ed"/></p>
         </details><br>
	 <details>
         <summary><b>등록한 리뷰 조회 및 삭제</b></summary>
                  <br>
         <p><b>➡ 회원이 마이페이지의 "리뷰" 페이지에서 등록한 리뷰 목록을 조회 및 삭제한다.</b></p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-2nd-developer_passion-fashion/assets/148875644/8cad0ad8-09de-4d46-861b-1371507beb06"/></p>
         </details><br>
	 <details>
         <summary><b>상품에 대한 문의</b></summary>
                  <br>
         <p><b>➡ 회원이 상품 상세페이지에서 [ 질문 유형, 질문 제목, 질문 내용 ] 을 입력 하여 질문을 등록한다.</b></p>
           <p><b>➡ 해당 상품에 등록된 질문들을 상품 상세페이지에서 확인한다.</b></p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-2nd-developer_passion-fashion/assets/148875644/11c37b9f-e7f3-40bb-828b-a5c1470a1e14"/></p>
         </details><br>
	 <details>
         <summary><b>질문 조회 및 삭제</b></summary>
                  <br>
         <p><b>➡ 회원이 마이페이지의 "질문 페이지" 에서 등록 했던 질문들을 조회하고, 삭제한다.</b></p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-2nd-developer_passion-fashion/assets/148875644/af4a9e3a-62b6-4ab2-8127-c882080cfb87"/></p>
         </details><br>
    </div>
</details>

---

<br>
<br>
<details>
<summary><b>🤵🏻‍♂️ 판매자</b></summary><br>
    <div>
    	 <details>
         <summary><b>판매자 회원가입</b></summary>
                  <br>
         <p><b>➡ 판매자가 [ 이메일 아이디, 패스워드, 브랜드명, 브랜드 고유키, 판매자명 ] 을 입력하여 회원가입한다. <br><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-2nd-developer_passion-fashion/assets/148875644/81fa9faa-a9ed-4a73-a487-7f822004922d"/></p>
         </details><br>
    	 <details>
         <summary><b>판매자 로그인</b></summary>
                  <br>
         <p><b>➡ 판매자가 [ 이메일 아이디, 패스워드 ] 를 입력하여 로그인한다.</b></p><br>
         <p><img src="https://github.com/beyond-sw-camp/be02-2nd-developer_passion-fashion/assets/148875644/6de7b500-7ac2-43b9-b9a9-6cdea32c4e76"/></p>
         </details><br>
	 <details>
         <summary><b>판매 상품 등록</b></summary>
                  <br>
         <p><b>➡ 브랜드(판매자)가 [ 브랜드명, 카테고리, 스타일, 상품명, 상품수량, 가격, 상의 및 하의에 대한 치수,<br> 
         &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;상품 이미지 및 상품 설명 이미지 ] 를 입력하여 판매 상품을 등록한다.</b></p><br>
         <p><img src="https://github.com/hyungdoyou/LONUA_Project/assets/148875644/a2c60f39-efe1-4ffa-916b-f88726e397f7"/></p>
         </details><br>
    </div>
</details>

---
