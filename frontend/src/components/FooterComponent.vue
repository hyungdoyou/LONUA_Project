<template>
  <div class="footer">
    <div class="inner-footer">
      <nav class="inner-footer-nav">
        <ul>
          <li>
            <router-link to="/"> 회사소개 </router-link>
          </li>
          <li>
            <router-link to="/"> 브랜드 등록 상담 </router-link>
          </li>
          <li>
            <router-link to="/"> 제휴문의 </router-link>
          </li>
          <li>
            <router-link to="/"> 이용약관 </router-link>
          </li>
          <li>
            <router-link to="/"> 개인정보처리방침 </router-link>
          </li>
          <li>
            <router-link to="/"> 고객센터 </router-link>
          </li>
          <li>
            <router-link to="/"> 채용정보 </router-link>
          </li>
          <li>
            <router-link to="/"> 회사소개 </router-link>
          </li>
        </ul>
      </nav>
      <div class="left">
        <p class="address">
          상호명 : ㈜LONUA ㅣ 대표자 : D.P ㅣ 주소 : 서울특별시 동작구 보라매로
          87, SFC 빌딩(4층)<br />사업자등록번호 : 000-00-00000 ㅣ 통신판매업신고
          : 제2024호-서울동작-00847호
          <a href="/" target="_blank" rel="noreferrer" class="micro-btn"
            >사업자정보확인</a
          >
          │ 개인정보보호책임자 : D.P │ 호스팅서비스 : ㈜LONUA<br />COPYRIGHT ⓒ
          ㈜LONUA ALL RIGHTS RESERVED
        </p>
        <div class="right">
          <h3 class="title">소비자피해보상보험</h3>
          <p>
            고객님은 안전거래를 위해 현금 결제 시, Wconcept 에서 가입한<br />소비자피해보상보험
            서비스를 이용하실 수 있습니다.
          </p>
          <p>
            보상대상 : 미배송/반품, 환불거부/쇼핑몰부도
            <a href="/" target="_blank" rel="noreferrer" class="micro-btn"
              >서비스 가입사실 확인</a
            >
          </p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "FooterComponent",
  components: {},
};
</script>

<style scoped>
/* 여기부터 footer */
.footer {
  padding-bottom: env(safe-area-inset-bottom);
}

.inner-footer {
  padding-top: 100px;
  padding-bottom: 40px;
}

.inner-footer-nav {
  background-color: rgb(132, 132, 132);
}

.inner-footer-nav ul {
  width: 1280px;
  margin: 0px auto;
  display: flex;
  flex-flow: row;
  justify-content: center;
  align-items: center;
  list-style: none;
}

.inner-footer-nav a {
  display: block;
  padding: 23px 44px 20px;
  font-size: 14px;
  color: rgb(255, 255, 255);
}

.inner-footer-nav a:link {
  text-decoration: none;
}

.left {
  padding-top: 40px;
  width: 1280px;
  margin: 0px auto;
  display: flex;
  flex-flow: row;
  -webkit-box-pack: center;
  justify-content: center;
  align-items: flex-start;
  letter-spacing: -0.005em;
  font-size: 12px;
  color: rgb(102, 102, 102);
}

.left .address {
  width: 854px;
  line-height: 20px;
}

.left .micro-btn {
  display: inline-block;
  min-width: 60px;
  padding: 0px 8px;
  height: 20px;
  line-height: 18px;
  font-size: 12px;
  color: rgb(0, 0, 0);
  border: 1px solid rgb(51, 51, 51);
  background-color: rgb(255, 255, 255);
  text-align: center;
}

.right {
  line-height: 18px;
  margin-left: 22px;
  padding-left: 22px;
  border-left: 1px solid rgb(229, 229, 229);
}

.title {
  color: rgb(0, 0, 0);
}

.right .micro-btn {
  font-size: 11px;
  margin-left: 7px;
}

</style>
