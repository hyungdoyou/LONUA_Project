<template>
  <section id="container">
    <div class="sub_title_wrap">
      <h2 class="sub_title">my page</h2>
      <ol id="breadcrumb">
        <li><a href="/">home</a></li>
        <li><a href="/MainMyPage">my page</a></li>
        <li><a href="/UserMileage">쇼핑헤택</a></li>
        <li>마일리지</li>
      </ol>
    </div>

    <MyPageComponent />

    <div class="mypage">
      <div class="table_title contents disc">
        <h3></h3>
      </div>
      <div class="w_point">
        <ul>
          <li class="point">
            <p class="title"></p>
            <p>
              <strong id="availablePoint"></strong>
            </p>
          </li>
          <li class="point_icon plus">
            <p class="title">
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;잔여
              마일리지&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;
              <strong id="expectingPoint">{{
                userStore.mileage.toLocaleString()
              }}</strong>
              P
              <span class="tooltip tooltip_icon_notice tooltipstered"></span>
            </p>
            <p></p>
          </li>
          <li class="minus">
            <p class="title">
              <em></em>
              <span class="tooltip tooltip_icon_notice tooltipstered"></span>
            </p>
            <p>
              <strong id="expiredPoint"></strong>
            </p>
          </li>
        </ul>
      </div>
    </div>
  </section>
</template>

<script>
import { mapStores } from "pinia";
import { useUserStore } from "../stores/useUserStore";
import MyPageComponent from "@/components/MyPageComponent.vue";

export default {
  name: "UserMileage",
  computed: {
    ...mapStores(useUserStore),
  },
  mounted() {
    this.userStore.getUserInfo();
  },
  components: { MyPageComponent },
};
</script>

<style scoped>
#container .sub_title_wrap {
  padding-top: 55px;
  padding-bottom: 36px;
  position: relative;
  min-width: 1240px;
}

#container .sub_title_wrap .sub_title {
  color: #000;
}

#container .sub_title {
  font-family: "ProximaNova-Thin", "Noto Sans KR";
  font-size: 44px;
  line-height: 44px;
  color: #333;
  text-align: center;
  text-transform: uppercase;
}

h2,
h3,
h4 {
  margin: 0;
  font-weight: normal;
}

#breadcrumb {
  width: 100%;
  text-align: center;
  margin-top: 16px;
}

#breadcrumb li {
  display: inline-block;
  font-family: "ProximaNova-Regular", "Noto Sans KR";
  font-size: 11px;
  text-transform: uppercase;
  vertical-align: middle;
  color: #000;
}

#breadcrumb li a {
  text-transform: uppercase;
  color: #676767;
  vertical-align: top;
}

#breadcrumb li:not(:last-child):after {
  content: "";
  display: inline-block;
  width: 3px;
  height: 5px;
  vertical-align: top;
  margin: 5px 6px 0 10px;
  font-size: 0;
  background: url(//static.wconcept.co.kr/web/images/common/spr-common.png) -60px -60px
    no-repeat;
}

#snb {
  width: 100%;
  min-width: 1240px;
  border-bottom: 1px solid #e6e6e6;
  background-color: #fff;
  margin-bottom: 60px;
}

#snb .snb_header {
  width: 100%;
  min-width: 1240px;
  background-color: #f3f3f3;
  border-top: 1px solid #e6e6e6;
}

#snb .snb_header ul {
  width: 1240px;
  margin: 0 auto;
}

#snb.item6 .snb_header ul li:first-child {
  width: 210px;
}

#snb .snb_header ul li {
  text-align: center;
  float: left;
  position: relative;
  line-height: 41px;
}

#snb .snb_header ul li a {
  font-size: 14px;
  color: #333;
}

#snb .snb_header ul:first-child:before {
  content: none; /* 또는 content: ''; */
}

#snb .snb_header ul li:before {
  content: "";
  width: 1px;
  height: 25px;
  background-color: #e5e5e5;
  display: block;
  position: absolute;
  top: 12px;
  left: 0;
}

#snb.item6 .snb_header ul li {
  width: 206px;
}

#snb .snb_header ul:after {
  display: block;
  clear: both;
  content: "";
}

#snb .snb_sub {
  width: 100%;
  border-top: 1px solid #e6e6e6;
}

#snb .snb_sub .inner {
  width: 1240px;
  margin: 0 auto;
  padding: 9px 0;
  display: table;
}

#snb.item6 .snb_sub ul:first-child {
  width: 210px;
}

#snb .snb_sub .inner ul {
  display: table-cell;
  position: relative;
}

#snb .snb_sub .inner ul li {
  text-align: center;
  line-height: 25px;
  vertical-align: top;
}

#snb .snb_sub .inner ul li a {
  font-size: 12px;
  color: #4c4c4c;
}

#snb .snb_sub .inner ul:first-child:before {
  content: none; /* 또는 content: ''; */
}

#snb .snb_sub .inner ul:before {
  content: "";
  width: 1px;
  height: 120%;
  background-color: #e5e5e5;
  display: block;
  position: absolute;
  top: 0;
  left: 0;
}

#snb.item6 .snb_sub ul {
  width: 206px;
}

#snb .snb_close_wrap {
  position: relative;
  width: 1240px;
  margin: 0 auto;
}

.mypage {
  font-family: "ProximaNova-Regular", "Apple SD Gothic Neo", "Noto Sans KR",
    "Malgun Gothic", "맑은 고딕", sans-serif;
}

.mypage {
  width: 1240px;
  margin: 0 auto;
}

/*-----------------------------------------------*/

.table_title.contents {
  height: 41px;
}

.table_title {
  position: relative;
  height: 50px;
}

.table_title h3,
.table_title h4 {
  color: #000;
  font-family: "ProximaNova-Regular", "Noto Sans KR";
  font-size: 24px;
  line-height: 36px;
  display: inline-block;
  position: absolute;
  top: 0;
  left: 0;
}

.mypage .wrap_pwd {
  width: 1234px;
  height: 200px;
  border: 3px solid #f8f8f8;
}

.mypage .wrap_pwd .bx_guide {
  margin: 48px 0 0 332px;
  font-size: 14px;
}

.mypage .wrap_pwd .bx_inp {
  margin: 26px 6px 0 332px;
}

.mypage .wrap_pwd input[type="password"] {
  height: 40px;
  margin-right: 5px;
}

input[type="text"],
textarea,
input[type="password"],
input[type="tel"],
input[type="number"] {
  height: 40px;
  line-height: 38px;
  padding-left: 20px;
  border: none;
  background-color: #f2f2f2;
  border: 1px solid #f2f2f2;
  font-size: 14px;
  font-family: "ProximaNova-Regular", "Noto Sans KR";
  outline: none;
}

.mypage .wrap_pwd .bx_inp .btn {
  min-width: 140px;
}

a.btn.small,
button.btn.small,
input.btn.small,
span.btn.small {
  height: 40px;
  font-size: 14px;
  line-height: 38px;
}

a.btn.black,
button.btn.black,
input.btn.black,
span.btn.black {
  border-color: #000;
  background-color: #000;
  color: #fff;
}

.mypage .wrap_pwd .incorrect {
  margin-top: 8px;
}

.mypage p.incorrect {
  color: #fa5500;
  padding-top: 20px;
}

.mypage .w_point {
  background-color: #fff;
  height: 134px;
  margin-bottom: 60px;
  padding: 17px 0;
}

.mypage .w_point ul {
  display: table;
  height: 100%;
  width: 100%;
}

.mypage .w_point ul li.point_icon.plus {
  display: table-cell;
  position: relative;
  width: 50%;
  height: 100%;
  padding-left: 60px;
  text-align: left;
  border-right: 1px dashed #b5b5b5;
  border-left: 1px dashed #b5b5b5;
  border-top: 1px dashed #b5b5b5;
  border-bottom: 1px dashed #b5b5b5;
}

.mypage .w_point ul li p {
  font-size: 14px;
  color: #333;
}
.mypage .w_point ul li p.title {
  margin-bottom: 55px;
}

.mypage .w_point ul li p {
  font-size: 30px;
  color: #333;
}
strong,
b {
  font-weight: normal;
}

.mypage .w_point ul li p strong {
  display: inline-block;
  font-size: 30px;
  border-bottom: 1px solid #000;
  line-height: 1em;
  font-family: "ProximaNova-Regular";
}

.mypage .w_point ul li.point_icon:after {
  content: "";
  display: block;
  width: 60px;
  height: 60px;
  background: url(//static.wconcept.co.kr/web/images/common/spr-mypage.png) 0 -50px
    no-repeat;
  position: absolute;
  top: 50%;
  right: 60px;
  margin-top: -30px;
}
</style>
