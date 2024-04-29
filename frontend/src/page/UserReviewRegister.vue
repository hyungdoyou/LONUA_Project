<template>
  <section id="container" data-custno="9150592">
    <div class="sub_title_wrap">
      <h2 class="sub_title">my page</h2>
      <ol id="breadcrumb">
        <li><a href="/">home</a></li>
        <li><a href="/MainMyPage">my page</a></li>
        <li><a href="/UserQusetion">문의내역</a></li>
        <li>리뷰 작성</li>
      </ol>
    </div>

    <MyPageComponent />

    <div class="mypage">
      <div class="table_title contents">
        <h3>리뷰 작성</h3>
      </div>

      <!-- tab -->

      <div id="divList" style="display: block">
        <div class="container">
          <h2>♥ 소중한 리뷰 부탁드립니다 ♥</h2>
          <form id="reviewForm">
            <label for="reviewContent"></label>
            <textarea
              v-model="review.reviewContent"
              id="reviewContent"
              rows="5"
              placeholder="리뷰는 저희에게 큰 힘이 됩니다 >_<"
              required
            ></textarea>

            <label for="evaluation">별점</label>
            <input
              v-model="review.evaluation"
              type="number"
              id="evaluation"
              min="1"
              max="5"
              required
            />

            <label for="reviewPhoto">이미지</label>
            <input
              type="file"
              id="reviewPhoto"
              accept="reviewPhoto/*"
              @change="previewImage"
            />

            <div id="imagePreview">
              <img v-if="imagePreview" :src="imagePreview" alt="Preview" />
            </div>

            <button type="button1" @click.stop.prevent="submitReview()">
              리뷰 작성하기
            </button>
          </form>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import { mapStores } from "pinia";
import { useReviewStore } from "../stores/useReviewStore";
import MyPageComponent from "@/components/MyPageComponent.vue";
export default {
  data() {
    return {
      review: {
        reviewContent: "",
        evaluation: 0,
      },
      reviewPhoto: null,
      imagePreview: null,
    };
  },
  components: { MyPageComponent },
  computed: {
    ...mapStores(useReviewStore),
  },
  methods: {
    previewImage(event) {
      const file = event.target.files[0];
      if (file) {
        const reader = new FileReader();
        reader.onload = (e) => {
          this.imagePreview = e.target.result;
        };
        reader.readAsDataURL(file);
        this.reviewPhoto = file;
      }
    },
    async submitReview() {
       await this.reviewStore.submitReview(this.review, this.reviewPhoto);
    },
  },
};
</script>

<style scoped>
body {
  font-family: "Arial", sans-serif;
  background-color: #ffffff;
  margin: 0;
  padding: 0;
}

.container {
  max-width: 1200px;
  margin: 30px auto;
  background-color: #fff;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

h2 {
  color: #007bff;
}

form {
  display: flex;
  flex-direction: column;
}

label {
  margin: 10px;
  color: #343a40;
}

textarea,
input {
  margin-bottom: 16px;
  padding: 10px;
  border: 1px solid #ced4da;
  border-radius: 4px;
}

input[type="number"] {
  width: 60px;
}

button[type="button1"] {
  padding: 12px;
  background-color: #007bff;
  color: #fff;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

button[type="button1"]:hover {
  background-color: #0056b3;
}

#imagePreview {
  margin-bottom: 16px;
}

#imagePreview img {
  max-width: 100%;
  height: auto;
  border-radius: 4px;
}

.review-list-item-wrapper {
  display: flex;
  align-items: center;
  justify-content: space-between; /* space-around 대신 space-between으로 변경 */
  padding: 15px;
  padding-right: 230px;
  border-bottom: 1px solid #ddd;
  width: 1239px;
  margin: 0 auto; /* 가운데 정렬을 위해 margin 속성 추가 */
  box-sizing: border-box;
  position: relative;
}

.review-list-item {
  flex: 1;
  margin: 0 10px;
}

/* border-bottom을 부모 요소에 상대적으로 설정 */
.review-list-item-wrapper::before {
  content: "";
  position: absolute;
  left: 0;
  bottom: 0;
  width: 100%;
  height: 1px;
  background-color: #ddd;
}

.review-content {
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  overflow: hidden;
  margin-right: 170px;
  font-size: 20px;
}

.evaluation {
  font-size: 18px; /* 평점 폰트 크기 조절 */
  font-weight: bold; /* 평점 폰트 두껍게 설정 */
}

/* .review-list-item {
    margin-bottom: 10px;
  } */

/* img */
.review-Photo {
  flex: 0 0 30%;
  margin-right: 10px;
}

img {
  max-width: 70%; /* 이미지가 부모 요소의 너비를 초과하지 않도록 설정 */
  height: auto; /* 가로세로 비율을 유지하면서 이미지 크기 조절 */
  border-radius: 5px; /* 이미지에 둥근 테두리를 적용 */
}

/* img */

#snb .snb_header ul li.active a {
  color: #fff;
}
#snb .snb_header ul li.active {
  background-color: #0e0e0e;
}
.datepicker {
  display: inline-block;
  height: 30px;
  line-height: 28px;
  border: 1px solid #d9d9d9;
  background-color: #fff;
  position: relative;
  width: 122px;
}

.datepicker:before {
  content: "";
  display: block;
  position: absolute;
  top: 5px;
  right: 10px;
  background: url("//static.wconcept.co.kr/web/images/common/spr-input.png") -90px
    0 no-repeat;
  width: 16px;
  height: 17px;
}

.datepicker input {
  display: block;
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  border: none;
  background: none;
  padding-left: 15px;
  font-family: "ProximaNova-Light";
  font-size: 14px;
  line-height: 28px;
  height: 28px;
  color: #000;
}

.datepicker input:focus {
  border: none;
  background: none;
}

.datepicker .date_btn {
  height: 28px;
  width: 36px;
  display: block;
  position: absolute;
  top: 0;
  right: 0;
  overflow: hidden;
  text-indent: -9999px;
}
.mypage .date_picker_wrap .left .date_input_wrap {
  float: left;
  margin-left: 20px;
}
* {
  font-weight: normal;
  -webkit-box-sizing: border-box;
  -moz-box-sizing: border-box;
  box-sizing: border-box;
  font-weight: normal;
  font-style: normal;
}
button,
input,
optgroup,
select,
textarea {
  font: inherit;
  margin: 0;
  color: inherit;
}
button {
  overflow: visible;
}
button,
select {
  text-transform: none;
}
button,
html input[type="button"],
input[type="reset"],
input[type="submit"] {
  cursor: pointer;
  -webkit-appearance: button;
}
a,
img,
input,
button,
select,
textarea {
  vertical-align: middle;
}
button {
  width: auto;
  border: 0;
  padding: 0;
  background: none;
  font-weight: normal;
}
.mypage .date_picker_wrap {
  width: 100%;
  height: 70px;
  padding: 20px 0 0 60px;
  background-color: #f2f2f2;
  margin-bottom: 20px;
}

.mypage .date_picker_wrap:after {
  display: block;
  clear: both;
  content: "";
}

.mypage .date_picker_wrap .left {
  width: 637px;
  position: relative;
  vertical-align: top;
  float: left;
}

.mypage .date_picker_wrap .left:before {
  content: "";
  display: block;
  position: absolute;
  top: 4px;
  right: 0;
  width: 1px;
  height: 20px;
  border-left: 1px dashed #b5b5b5;
}

.mypage .date_picker_wrap .left:after {
  display: block;
  clear: both;
  content: "";
}

.mypage .date_picker_wrap .left button {
  display: block;
  float: left;
  padding: 0 16px;
  height: 30px;
  line-height: 28px;
  border: 1px solid #d9d9d9;
  background-color: #fff;
  text-align: center;
  font-size: 14px;
  color: #000;
  margin-left: 5px;
}

.mypage .date_picker_wrap .left button:first-child {
  margin-left: 0;
}

.mypage .date_picker_wrap .left button.active {
  background-color: #666;
  border-color: #666;
  color: #fff;
}

.mypage .date_picker_wrap .left button.btn_date_search {
  width: 80px;
  background-color: #0e0e0e;
  border-color: #0e0e0e;
  color: #fff;
  margin-left: 20px;
}

.mypage .date_picker_wrap .left .date_input_wrap {
  float: left;
  margin-left: 20px;
}

.mypage .date_picker_wrap .left .date_input_wrap .datepicker {
  display: block;
  float: left;
}

.mypage .date_picker_wrap .left .date_input_wrap span {
  display: block;
  float: left;
  height: 30px;
  width: 17px;
  line-height: 30px;
  text-align: center;
}

.mypage .date_picker_wrap .right {
  float: left;
  padding: 2px 0 0 60px;
}

.mypage .date_picker_wrap .right .input_button {
  margin-right: 45px;
}

.mypage .date_picker_wrap .right .input_button:last-child {
  margin-right: 0;
}

.mypage .date_picker_wrap.only_left {
  margin-top: 10px;
}

.mypage .date_picker_wrap.only_left .left {
  width: 100%;
  padding-left: 239px;
}

.mypage .date_picker_wrap.only_left .left:before {
  display: none;
}

.mypage .date_picker_wrap.only_left .left.blank {
  padding-left: 270px;
}
.review_notify.ty_tbl_bottom {
  width: 1240px;
  margin: 60px auto 0;
}

.review_notify.ty_tbl_bottom h4 {
  font-size: 16px;
  color: #000;
  line-height: 22px;
  padding-left: 23px;
  position: relative;
  margin-bottom: 8px;
}

.review_notify.ty_tbl_bottom h4:before {
  content: "";
  display: inline-block;
  width: 15px;
  height: 15px;
  margin: 0 4px 0 0;
  background: url(//static.wconcept.co.kr/web/images/common/ico_tooltip_15.png)
    no-repeat 0 0;
  background-size: cover;
  position: absolute;
  left: 0;
  top: 50%;
  margin-top: -7.5px;
}

.review_notify .dot_list {
  padding-left: 14px;
}

.review_notify .dot_list li {
  font-size: 12px;
  color: #333;
  line-height: 22px;
  position: relative;
  padding-left: 11px;
  font-family: "ProximaNova-Regular", "Apple SD Gothic Neo", "Noto Sans KR",
    "Malgun Gothic", "맑은 고딕", sans-serif;
}

.review_notify .dot_list li + li {
  margin-top: 8px;
}

.review_notify .dot_list li:before {
  content: "";
  display: inline-block;
  width: 2px;
  height: 2px;
  background: #707070;
  position: absolute;
  left: 0;
  top: 8px;
}

body.scrollOff {
  overflow: hidden;
  touch-action: none;
}

.review_guide_pop .layer_cont {
  width: 600px;
  height: auto;
  z-index: 120;
  padding: 40px;
}

.review_guide_pop .layer_cont + span {
  z-index: 115;
}

.review_guide_pop .review_upload_tip_ctns {
  overflow: hidden;
  height: 100%;
}

.review_guide_pop .layer_tit {
  border-bottom: 2px solid #000;
  padding-bottom: 25px;
  font-size: 24px;
  color: #333;
  line-height: 29px;
  margin-bottom: 0;
}

.unorder_list li {
  position: relative;
  padding-left: 15px;
  font-size: 14px;
  line-height: 17px;
  color: #666;
  font-family: "ProximaNova-Regular", "Apple SD Gothic Neo", "Noto Sans KR",
    "Malgun Gothic", "맑은 고딕", sans-serif;
}

.unorder_list li:before {
  content: "";
  display: inline-block;
  width: 3px;
  height: 3px;
  background: #171717;
  position: absolute;
  left: 0;
  top: 6px;
  border-radius: 50%;
  overflow: hidden;
}

.unorder_list li + li {
  margin-top: 10px;
}

.attention .lst_info ol,
.review_notify .dot_list ol {
  margin-top: 6px;
}

.review_notify .dot_list ol > li,
.attention .lst_info ol > li {
  padding-left: 8px;
  position: relative;
  word-break: keep-all;
  overflow-wrap: break-word;
  line-height: 20px;
}

.review_notify .dot_list ol > li + li {
  margin-top: 0;
}

.review_notify .dot_list ol > li:before,
.attention .lst_info ol > li:before {
  content: "";
  width: 3px;
  height: 1px;
  background: #666;
  display: inline-block;
  position: absolute;
  left: 0;
  top: 10px;
}

.review_upload_tip_notify.pt_top {
  margin-top: 30px;
}

.review_upload_tip_notify.pt_bottom {
  margin-top: 20px;
}

.review_upload_tip_notify.pt_top .unorder_list li {
  color: #000;
}

.review_upload_tip_notify.pt_top .unorder_list li:before {
  background: #000;
}

.review_upload_tip_notify.pt_bottom .unorder_list li {
  color: #777;
  font-size: 12px;
}

.review_upload_tip_notify.pt_bottom .unorder_list li:before {
  background: #777;
}

.review_upload_tip_attach h3 {
  font-size: 14px;
  line-height: 22px;
  font-weight: 500;
  margin: 22px 0 10px;
  text-align: left;
}

.review_upload_tip_attach_ctns {
  background: #fafafa;
  padding: 2px 0 24px;
}

.review_upload_tip_attach_ctns h4 {
  font-size: 13px;
  color: #777;
  line-height: 20px;
  letter-spacing: -0.26px;
  margin: 22px 0 8px;
  text-align: center;
}

.review_upload_tip_sample {
  display: -webkit-flex;
  display: -ms-flex;
  display: flex;
  justify-content: center;
  margin: 0 auto;
}

.review_upload_tip_sample .img {
  width: 80px;
  height: 80px;
  margin: 0 5px;
  position: relative;
}

.review_upload_tip_sample .img:before {
  content: "";
  display: inline-block;
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: rgba(0, 0, 0, 0.04);
}

.review_upload_tip_sample .img > img {
  max-width: 100%;
}
.review_benefit_container {
  display: flex;
  display: -ms-flex;
  display: -webkit-flex;
  width: 100%;
  justify-content: space-between;
  align-items: flex-start;
  font-family: "ProximaNova-Regular", "Apple SD Gothic Neo", "Noto Sans KR",
    "Malgun Gothic", "맑은 고딕", sans-serif;
  margin-bottom: 30px;
}

.review_benefit_ctns:nth-child(1) {
  width: 526px;
}

.review_benefit_ctns:nth-child(2) {
  width: 704px;
}

.review_benefit_ctns {
  min-height: 159px;
  text-align: center;
  background: #fafafa;
  padding: 30px 0;
}

.review_benefit_ctns .tit_review_benefit_sub {
  font-size: 18px;
  line-height: 26px;
  letter-spacing: -0.36px;
  margin-bottom: 20px;
  font-weight: 500;
}

.review_benefit_list {
  display: flex;
  display: -ms-flex;
  display: -webkit-flex;
  width: 100%;
  justify-content: center;
  align-items: center;
}

.review_benefit_item {
  font-size: 14px;
  color: #777;
  letter-spacing: -0.35px;
  line-height: 19px;
  flex: 1;
  position: relative;
}

.review_benefit_item > strong {
  display: block;
  color: #fa5500;
  margin-bottom: 4px;
  font-size: 24px;
  letter-spacing: -0.48px;
  line-height: 29px;
}

.review_benefit_item + .review_benefit_item:before {
  content: "";
  display: inline-block;
  width: 1px;
  height: 53px;
  background: #eaeaea;
  position: absolute;
  left: 0;
  top: 0;
}
.tit_review_benefit {
  font-size: 22px;
  color: #000;
  line-height: 40px;
  letter-spacing: -0.44px;
  margin: 40px 0 16px;
  text-align: left;
}
.tab {
  width: 100%;
  border-bottom: 2px solid #000;
}

.tab:after {
  display: block;
  clear: both;
  content: "";
}

.tab li {
  float: left;
  border: 1px solid #b5b5b5;
  border-bottom: none;
  border-left-width: 0;
  background-color: #fff;
  height: 60px;
}

.tab li:first-child {
  border-left-width: 1px;
}

.tab li button {
  display: block;
  height: 100%;
  width: 100%;
  text-align: center;
  color: #333;
  font-size: 18px;
  font-family: "Noto Sans KR";
}

.tab li.active {
  border: 2px solid #000;
  border-bottom: none;
  height: 62px;
  margin-bottom: -2px;
}

.tab li.active button {
  color: #000;
}
.tab li button {
  display: block;
  height: 100%;
  width: 100%;
  text-align: center;
  color: #333;
  font-size: 18px;
  font-family: "Noto Sans KR";
}
.tab li.active button {
  color: #000;
}
.tab.tab_item2 li {
  width: 50%;
}
.tab.tab_item3 li {
  width: 33.3333333333%;
}
.tab.tab_item4 li {
  width: 25%;
}
.tab.tab_item5 li {
  width: 20%;
}
.tab.tab_item6 li {
  width: 16.6666666667%;
}
.tab.tab_item7 li {
  width: 14.2857142857%;
}
.tab.tab_item8 li {
  width: 12.5%;
}
.tab.tab_item9 li {
  width: 11.1111111111%;
}
.tab.tab_item10 li {
  width: 10%;
}
.tab {
  width: 100%;
  border-bottom: 2px solid #000;
}
.mt10 {
  margin-top: 10px !important;
}
.table_title {
  position: relative;
  height: 50px;
}
.table_title.contents {
  height: 41px;
}
* {
  font-weight: normal;
  box-sizing: border-box;
  font-weight: normal;
  font-style: normal;
}

/* 요기까지 리뷰 */

.global_search {
  position: relative;
  width: 100%;
  max-width: 1920px;
  height: 88px;
  background-color: #fff;
  margin: 0 auto;
  padding-right: 20px;
}

.symbol {
  position: absolute;
  left: 20px;
  top: 32px;
}

#top_search {
  position: absolute;
  z-index: 101;
  left: 50%;
  top: 9px;
  transform: translateX(-50%);
  width: 392px;
  height: 70px;
  padding: 11px 16px 15px;
  background: #fff;
  box-sizing: border-box;
  font-weight: normal;
  font-style: normal;
}

#top_search::after {
  content: "";
  display: block;
  width: 360px;
  height: 1px;
  background: #000;
  position: absolute;
  bottom: 15px;
  left: 16px;
}

#top_search input {
  width: 335px;
  height: 44px;
  display: block;
  float: left;
  border: none;
  font-size: 14px;
  line-height: 44px;
  background: none;
  padding: 0;
}

#top_search.btn_search {
  display: block;
  float: left;
  position: relative;
  width: 25px;
  height: 44px;
  overflow: hidden;
  text-indent: -9999px;
}

#top_search.result_wrap {
  z-index: 101;
  width: 526px;
  border: 1px solid #e9e9e9;
  background: #fff;
  position: absolute;
  top: 61px;
  left: 16px;
}

.utility {
  float: right;
  padding-top: 26px;
  margin-right: -17px;
}

.utility li {
  text-transform: uppercase;
  font-family: "ProximaNova-Semibold";
  text-align: center;
  float: left;
  margin: 0;
  padding: 0;
  list-style: none;
}

.utility a {
  display: block;
  position: relative;
  padding: 0 17px 18px 17px;
  color: #000;
  vertical-align: middle;
  text-decoration: none;
}

.global_nav_wrap .nav > li.symbol {
  position: static;
  padding: 9px 7px 0 0;
}

nav {
  position: relative;
  width: 100%;
  top: 0;
  left: 0;
  right: 0;
  z-index: 100;
  min-width: 1280px;
  background: #fff;
  border-bottom: 1px solid #e9e9e9;
}

.global_nav_wrap .nav > li > a {
  display: flex;
  position: relative;
  text-transform: uppercase;
  color: #000;
  font-family: "ProximaNova-Bold", "NotoSansKR-Medium";
  font-size: 14px;
  line-height: 48px;
  padding: 0 13px;
  text-decoration: none;
  list-style: none;
  flex-direction: row;
}

.global_nav_wrap .nav {
  display: flex;
  box-sizing: border-box;
  font-weight: normal;
  font-style: normal;
  list-style: none;
}

.global_nav_wrap .nav.nav_left li:last-child a {
  padding-right: 30px;
}

.global_nav {
  display: flex;
  flex-direction: row;
  list-style-type: none;
}

.global_nav_wrap .category_btn > a {
  display: block;
  position: absolute;
  top: 0;
  width: 176px;
  font-family: "ProximaNova-Bold";
  font-size: 14px;
  line-height: 46px;
  padding: 0 0 0 14px;
  border: 1px solid #e9e9e9;
  text-align: left;
  overflow: hidden;
  vertical-align: middle;
  text-decoration: none;
  color: #000;
}

.dropdown-menu {
  display: flex;
}

.category-dropdown {
  display: none;
  position: absolute;
  /* 기타 스타일 */
}
.category:hover > .category-dropdown {
  display: flex;
  flex-direction: column;
}
.nav-item-women-dropdown .women-dropdown-content {
  display: none;
}
.nav-item-women-dropdown:hover .women-dropdown-content {
  display: flex;
  flex-direction: column;
}

/* 마이페이지 메인 */
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
  background-color: #e5e5e500;
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

.mypage .grade {
  width: 100%;
  padding: 36px 0;
  margin-bottom: 60px;
  background-color: #f8f8f8;
}

.mypage .grade .info {
  width: 698px;
  float: left;
  padding-left: 154px;
  position: relative;
}

.mypage .grade .info h3 {
  font-size: 18px;
  letter-spacing: 0.18px;
  color: #000;
  margin-bottom: 10px;
}

.mypage .grade .info h3 strong {
  font-size: 18px;
  color: #000;
  font-family: "NotoSansKR-Medium";
}

strong,
b {
  font-weight: normal;
}

.mypage .grade .info h3 a {
  display: inline-block;
  width: 90px;
  height: 24px;
  font-size: 12px;
  line-height: 24px;
  letter-spacing: 0;
  text-align: center;
  border: 1px solid #ccc;
  color: #333;
  background: #fff;
  vertical-align: top;
  margin-left: 10px;
}

.mypage .grade .info .grade_icon {
  position: absolute;
  top: 5px;
  left: 40px;
}

.mypage .grade_icon.wpeople {
  background: url(https://github.com/hyungdoyou/LONUA_Project/assets/148875644/35b2b760-c8f7-41ae-af61-a2da113c3aa9)
    no-repeat;
  background-size: 100%;
}

.mypage .grade_icon {
  display: block;
  width: 84px;
  height: 84px;
  overflow: hidden;
  text-indent: -9999px;
}

.mypage .grade .info p {
  font-size: 14px;
  line-height: 18px;
  letter-spacing: 0.14px;
}

p {
  margin: 0;
}

.mypage .grade .info p strong {
  font-family: "ProximaNova-Medium";
  color: #000;
  vertical-align: bottom;
}

.mypage .grade_text.wpeople {
  color: #000;
}

.mypage .grade_text {
  font-family: "ProximaNova-Medium";
  line-height: 1em;
}

.mypage .grade .info .notice_wrap {
  margin-top: 13px;
}

.mypage .grade .info .notice_wrap .notice:last-child {
  margin-bottom: 0;
}

.mypage .grade .info .notice_wrap .notice {
  position: relative;
  left: 5px;
  padding-left: 8px;
  color: #777;
  font-family: "ProximaNova-Regular", "Noto Sans KR";
  font-size: 13px;
  line-height: 17px;
  margin-bottom: 4px;
}

.mypage .grade .info .notice_wrap .notice:before {
  content: "";
  display: block;
  position: absolute;
  top: 8px;
  left: 0;
  width: 2px;
  height: 2px;
  background-color: #666;
}

.mypage .grade ul {
  float: left;
  height: 100px;
  width: 540px;
  display: table;
  padding-top: 4px;
}

.mypage .grade ul li {
  width: 50%;
  display: table-cell;
  height: 100%;
  border-left: 1px dashed #b5b5b5;
  vertical-align: middle;
  text-align: center;
}

.mypage .grade ul li a {
  display: block;
  font-family: "ProximaNova-Light", "Noto Sans KR";
  color: #333;
}

.mypage .grade ul li a h4 {
  font-size: 14px;
  margin-bottom: 17px;
  font-family: "ProximaNova-Light", "Noto Sans KR";
}

.mypage .grade ul li a p {
  font-size: 18px;
}

.mypage .grade ul li a p span {
  font-family: "ProximaNova-Regular";
  font-size: 28px;
  display: inline-block;
  border-bottom: 1px solid #000;
  color: #000;
  margin-right: 7px;
  line-height: 0.95em;
}

.mypage .grade:after {
  display: block;
  clear: both;
  content: "";
}

.mb60 {
  margin-bottom: 60px !important;
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

.table_title .btn_more_noline {
  color: #000;
  position: absolute;
  bottom: 8px;
  right: 0;
  line-height: 1em;
  padding-right: 9px;
  font-size: 16px;
  color: #000;
}

.table_title .btn_more_noline:after {
  content: "";
  display: block;
  position: absolute;
  top: 6px;
  right: 0;
  width: 7px;
  height: 7px;
  background: url(//static.wconcept.co.kr/web/images/common/spr-common.png) -90px -60px
    no-repeat;
}

table {
  border-top: 2px solid #171717;
  border-bottom: 1px solid #171717;
  table-layout: fixed;
  margin-bottom: 60px;
  width: 100%;
  border-collapse: separate;
  border-spacing: 0;
}

table.cols thead th {
  height: 68px;
  font-family: "NotoSansKR-Medium";
  font-size: 14px;
  padding: 0 14px;
  color: #000;
  text-align: center;
  vertical-align: middle;
  border-bottom: 1px solid #b5b5b5;
}

table.cols tbody tr td.no_data {
  height: 150px;
  color: #333;
  font-size: 14px;
}

table.cols tbody tr td {
  height: 68px;
  font-family: "ProximaNova-Regular", "Noto Sans KR";
  text-align: center;
  vertical-align: middle;
  padding: 14px 0;
}

table tbody tr:first-child th,
table tbody tr:first-child td {
  border-color: transparent;
  border: none;
}

.mypage .my_heart .table_title {
  border-bottom: 2px solid #000;
  padding-bottom: 10px;
  margin-bottom: 15px;
}

.mypage .my_heart .table_title .btn_more_noline {
  bottom: 8px;
}

.thumbnail_list {
  width: 100%;
  position: relative;
  overflow: hidden;
}

.mypage .my_heart .thumbnail_list ul {
  margin-right: -20px;
}

.mypage .my_heart .thumbnail_list ul li {
  width: 190px;
  margin-right: 20px;
}

.thumbnail_list ul li {
  float: left;
  margin-bottom: 50px;
  position: relative;
}

.thumbnail_list ul li .heart.active {
  background-position: -360px 0;
}

.thumbnail_list ul li .heart {
  display: block;
  width: 18px;
  height: 17px;
  position: absolute;
  top: 10px;
  right: 10px;
  background: url(//static.wconcept.co.kr/web/images/common/spr-common.png) -330px
    0 no-repeat;
  z-index: 1;
}

.thumbnail_list ul li a {
  font-family: "ProximaNova-Regular", "Noto Sans KR";
  color: #000;
}

.mypage .my_heart .thumbnail_list ul li .img {
  height: 253px;
}

.thumbnail_list ul li .img {
  position: relative;
  margin-bottom: 16px;
  overflow: hidden;
  background: url(//static.wconcept.co.kr/mobile/images/common/onerror_img_product.png)
    no-repeat center/100%;
  height: 260px;
}

.thumbnail_list ul li .img img {
  display: block;
  width: 100%;
  height: 100%;
  -o-object-fit: cover;
  object-fit: cover;
}

.thumbnail_list ul li .text {
  padding: 0 4px 0 6px;
}

.thumbnail_list ul li .text .brand {
  font-size: 14px;
  line-height: 16px;
  margin: 0 0 7px 0 !important;
  font-family: "ProximaNova-Semibold", "NotoSansKR-Medium";
  width: auto;
  min-height: auto;
  max-height: initial;
  white-space: nowrap;
  text-overflow: ellipsis;
  overflow: hidden;
}

.thumbnail_list ul li .text .front {
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
  margin-bottom: 8px;
  color: #777;
}

.thumbnail_list ul li .text .product {
  font-size: 12px;
  line-height: 16px;
  color: #777;
  height: 16px;
  overflow: hidden;
  margin-bottom: 16px;
}

.thumbnail_list ul li .text .price {
  position: relative;
  font-family: "ProximaNova-Regular";
  line-height: normal;
  margin-bottom: 14px;
  display: flex;
  justify-content: flex-start;
  align-items: baseline;
}

.thumbnail_list ul li .text .price .discount_price {
  font-family: "ProximaNova-Semibold";
  font-size: 16px;
  color: #333;
  margin-right: 4px;
}

.thumbnail_list ul li .text .price .base_price {
  font-size: 12px;
  color: #aaa;
  text-decoration: line-through;
}

.thumbnail_list ul li .text .price .discount_rate {
  display: block;
  position: absolute;
  top: 0;
  right: 0;
  font-family: "ProximaNova-Semibold";
  font-size: 16px;
  color: #ff4000;
}

.thumbnail_list ul li .labels {
  margin-top: 10px;
}

.labels {
  margin-bottom: 5px;
  max-height: 45px;
  overflow: hidden;
}

.labels:after {
  display: block;
  clear: both;
  content: "";
}

.mypage .my_heart .thumbnail_list ul li.btn_wrap {
  float: right;
}

.mypage .my_heart .thumbnail_list ul li {
  width: 190px;
  margin-right: 20px;
}

.mypage .my_heart .thumbnail_list ul li.btn_wrap a {
  display: block;
  height: 87px;
  width: 100%;
  padding: 25px 0 0 26px;
  border-bottom: 1px solid #e2e2e2;
  text-transform: uppercase;
  position: relative;
}

.mypage .my_heart .thumbnail_list ul li.btn_wrap a span {
  display: block;
  font-size: 14px;
  color: #666;
  line-height: 1em;
}

.mypage .my_heart .thumbnail_list ul li.btn_wrap a em {
  font-size: 20px;
  color: #000;
  line-height: 1em;
}

.mypage .my_heart .thumbnail_list ul li.btn_wrap a p {
  width: 48px;
  height: 48px;
  position: absolute;
  top: 19px;
  right: 26px;
  background-color: #333;
  color: #fff;
  border-radius: 24px;
  font-size: 20px;
  line-height: 48px;
  text-align: center;
}

.top_banner a {
  display: block;
  width: 100%;
  min-width: 1280px;
}

.top_banner a img {
  width: 100%;
}

.img_responsive {
  vertical-align: top;
  width: 100%;
}

header {
  width: 100%;
  height: 146px;
  position: relative;
  top: 0;
  left: 0;
  right: 0;
  z-index: 100;
  min-width: 1280px;
  font-family: "ProximaNova-Regular", "Noto Sans KR";
  display: block;
}

header .global_search {
  position: relative;
  width: 100%;
  max-width: 1920px;
  height: 88px;
  background-color: #fff;
  margin: 0 auto;
  padding-right: 20px;
}

header .symbol {
  position: absolute;
  left: 20px;
  top: 32px;
}

h1,
h2,
h3,
h4,
h5,
h6 {
  margin: 0;
  font-weight: normal;
  font-family: "ProximaNova-Thin", "Noto Sans KR";
}

header .symbol a {
  display: block;
}

header .symbol a img {
  display: block;
  margin-top: -20px;
}

a,
img,
input,
button,
select,
textarea {
  vertical-align: middle;
}

img {
  border: 0;
}

header #top_search {
  position: absolute;
  z-index: 101;
  left: 50%;
  top: 9px;
  transform: translateX(-50%);
  width: 392px;
  height: 70px;
  padding: 11px 16px 15px;
  background: #fff;
}

header #top_search input {
  width: 335px;
  height: 44px;
  display: block;
  float: left;
  border: none;
  font-size: 14px;
  line-height: 44px;
  background: none;
  padding: 0;
}

input[type="text"] {
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

header #top_search .btn_search {
  display: block;
  float: left;
  position: relative;
  width: 25px;
  height: 44px;
  overflow: hidden;
  text-indent: -9999px;
  background: url(//static.wconcept.co.kr/web/images/common/svg/ico_search_25.svg)
    no-repeat center 9px/100%;
}

header #top_search .result_wrap {
  z-index: 101;
  width: 526px;
  border: 1px solid #e9e9e9;
  background: #fff;
  position: absolute;
  top: 61px;
  left: 16px;
}

header #top_search .result {
  font-size: 0;
  white-space: nowrap;
}

header #top_search .column.left {
  width: 308px;
}

header #top_search .column {
  display: inline-block;
  position: relative;
  padding: 24px;
  vertical-align: top;
}

header #top_search .column .tit {
  font-family: "NotoSansKR-Medium";
  font-size: 14px;
  line-height: 17px;
  margin-bottom: 17px;
}

header #top_search .column .del_all {
  position: absolute;
  top: 25px;
  right: 25px;
  font-size: 11px;
  color: #9d9d9d;
}

button {
  vertical-align: middle;
  width: auto;
  border: 0;
  padding: 0;
  background: none;
  font-weight: normal;
}

header #top_search .column .list li.nodata {
  font-size: 12px;
  color: #9d9d9d;
}

header #top_search .column .list li {
  position: relative;
  cursor: pointer;
}

header #top_search .column li {
  font-size: 13px;
  line-height: 16px;
}

header #top_search .column-right {
  width: 216px;
  height: 353px;
  border-left: 1px solid #f5f5f5;
}

header #top_search .column-right li ~ li {
  margin-top: 12px;
}

header #top_search .column-right {
  display: inline-block;
  position: relative;
  padding: 24px;
  vertical-align: top;
}

header #top_search .column-right .tit {
  font-family: "NotoSansKR-Medium";
  font-size: 14px;
  line-height: 17px;
  margin-bottom: 17px;
}

header #top_search .column-right li {
  font-size: 13px;
  line-height: 16px;
}

header #top_search .column-right .rank li a {
  display: -webkit-box;
  display: -moz-box;
  display: flex;
  position: relative;
  text-align: left;
  font-size: 13px;
  line-height: 16px;
  justify-content: flex-start;
}

header #top_search .column-right .rank li a > em {
  display: block;
  width: 20px;
  height: 16px;
  font-family: "ProximaNova-Medium";
  font-style: italic;
  color: #9d9d9d;
}

header #top_search .column-right .rank li a p {
  padding-right: 26px;
  position: relative;
  display: inline-grid;
  max-width: calc(100% - 20px);
}

header #top_search .column-right .rank li a p span {
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
  letter-spacing: -0.28px;
  color: #555;
}

header #top_search .column-right .rank li a p strong {
  font-size: 10px;
  color: #fa5500;
  position: absolute;
  right: 0;
  top: 0;
}

header #top_search .result:after {
  display: block;
  clear: both;
  content: "";
}

header #top_search .result {
  font-size: 0;
  white-space: nowrap;
}

header #top_search .column.full {
  width: 100%;
  padding: 25px 24px;
}

header #top_search .result_wrap .bx_btn {
  height: 34px;
  border-top: 1px solid #f5f5f5;
  text-align: right;
  padding-right: 20px;
}

header #top_search .result_wrap .bx_btn .close {
  font-size: 11px;
  line-height: 32px;
  color: #9d9d9d;
}

header #top_search:after {
  content: "";
  display: block;
  width: 360px;
  height: 1px;
  background: #000;
  position: absolute;
  bottom: 15px;
  left: 16px;
}

header .utility {
  float: right;
  padding-top: 26px;
  margin-right: -17px;
}

header .utility:before,
header .utility:after {
  content: "";
  display: table;
}

header .utility li {
  text-transform: uppercase;
  font-family: "ProximaNova-Semibold";
  text-align: center;
  float: left;
}

header .utility a {
  display: block;
  position: relative;
  padding: 0 17px 18px 17px;
  color: #000;
}

.fa-solid {
  display: inline-block;
  width: 30px;
  height: 30px;
  background: transparent no-repeat center;
  vertical-align: top;
}

.fa-solid {
  font-size: 28px;
}

header .utility strong {
  position: absolute;
  bottom: 0;
  left: 0;
  right: 0;
  font-size: 12px;
  line-height: 12px;
}
/*--------------------------------------------*/
header nav {
  position: relative;
  width: 100%;
  top: 120px;
  left: 0;
  right: 0;
  z-index: 100;
  min-width: 1280px;
  background: #fff;
  border-bottom: 1px solid #e9e9e9;
}

header .global_nav_wrap {
  max-width: 1920px;
  height: 57px;
  margin: 0 auto;
  padding: 0 20px;
}

@media (max-width: 1365px) {
  header .global_nav_wrap .global_nav {
    justify-content: flex-start;
    padding-left: 0;
  }
}

header .global_nav_wrap .global_nav {
  display: flex;
}

header .global_nav_wrap .nav {
  display: flex;
}

header .global_nav_wrap .nav > li > a {
  display: block;
  position: relative;
  text-transform: uppercase;
  color: #000;
  font-size: 14px;
  line-height: 48px;
  padding: 0 13px;
  font-weight: 700;
  font-style: normal;
  letter-spacing: 2px;
  margin-left: 10px;
}

header .global_nav_wrap .nav > li > a:hover {
  border-bottom: 3px solid;
}

@font-face {
  font-family: "SejonghospitalBold";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2312-1@1.1/SejonghospitalBold.woff2")
    format("woff2");
  font-weight: 700;
  font-style: normal;
}

.nav-item {
  display: flex;
  padding-right: 20px;
}

.nav-item-dropdown {
  display: none;
}

header .global_nav_wrap .nav > li:hover > .nav-item-dropdown {
  z-index: 100;
  position: absolute;
  width: 100%;
  height: 309px;
  padding: 17px 42px;
  border-top: 1px solid grey;
  border-bottom: 1px solid grey;
  background-color: white;
  top: 55px;
  left: 0px;
  display: block;
}

.dropdown-menu {
  display: flex;
}

.dropdown-menu-list {
  display: flex;
  flex-flow: column wrap;
  height: 300px;
}

.dropdown-menu-header {
  width: 176px;
  height: 320px;
}

.dropdown-menu-list-item {
  width: 176px;
  height: 35px;
}

.dropdown-menu-list-item-link strong {
  font-weight: bold;
}

/*-------------------------------*/
/* 마이페이지 메인 */
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

.mypage .grade {
  width: 100%;
  padding: 36px 0;
  margin-bottom: 60px;
  background-color: #f8f8f8;
}

.mypage .grade .info {
  width: 698px;
  float: left;
  padding-left: 154px;
  position: relative;
}

.mypage .grade .info h3 {
  font-size: 18px;
  letter-spacing: 0.18px;
  color: #000;
  margin-bottom: 10px;
}

.mypage .grade .info h3 strong {
  font-size: 18px;
  color: #000;
  font-family: "NotoSansKR-Medium";
}

strong,
b {
  font-weight: normal;
}

.mypage .grade .info h3 a {
  display: inline-block;
  width: 90px;
  height: 24px;
  font-size: 12px;
  line-height: 24px;
  letter-spacing: 0;
  text-align: center;
  border: 1px solid #ccc;
  color: #333;
  background: #fff;
  vertical-align: top;
  margin-left: 10px;
}

.mypage .grade .info .grade_icon {
  position: absolute;
  top: 5px;
  left: 40px;
}

.mypage .grade_icon.wpeople {
  background: url(https://github.com/hyungdoyou/LONUA_Project/assets/148875644/35b2b760-c8f7-41ae-af61-a2da113c3aa9)
    no-repeat;
  background-size: 100%;
}

.mypage .grade_icon {
  display: block;
  width: 84px;
  height: 84px;
  overflow: hidden;
  text-indent: -9999px;
}

.mypage .grade .info p {
  font-size: 14px;
  line-height: 18px;
  letter-spacing: 0.14px;
}

p {
  margin: 0;
}

.mypage .grade .info p strong {
  font-family: "ProximaNova-Medium";
  color: #000;
  vertical-align: bottom;
}

.mypage .grade_text.wpeople {
  color: #000;
}

.mypage .grade_text {
  font-family: "ProximaNova-Medium";
  line-height: 1em;
}

.mypage .grade .info .notice_wrap {
  margin-top: 13px;
}

.mypage .grade .info .notice_wrap .notice:last-child {
  margin-bottom: 0;
}

.mypage .grade .info .notice_wrap .notice {
  position: relative;
  left: 5px;
  padding-left: 8px;
  color: #777;
  font-family: "ProximaNova-Regular", "Noto Sans KR";
  font-size: 13px;
  line-height: 17px;
  margin-bottom: 4px;
}

.mypage .grade .info .notice_wrap .notice:before {
  content: "";
  display: block;
  position: absolute;
  top: 8px;
  left: 0;
  width: 2px;
  height: 2px;
  background-color: #666;
}

.mypage .grade ul {
  float: left;
  height: 100px;
  width: 540px;
  display: table;
  padding-top: 4px;
}

.mypage .grade ul li {
  width: 50%;
  display: table-cell;
  height: 100%;
  border-left: 1px dashed #b5b5b5;
  vertical-align: middle;
  text-align: center;
}

.mypage .grade ul li a {
  display: block;
  font-family: "ProximaNova-Light", "Noto Sans KR";
  color: #333;
}

.mypage .grade ul li a h4 {
  font-size: 14px;
  margin-bottom: 17px;
  font-family: "ProximaNova-Light", "Noto Sans KR";
}

.mypage .grade ul li a p {
  font-size: 18px;
}

.mypage .grade ul li a p span {
  font-family: "ProximaNova-Regular";
  font-size: 28px;
  display: inline-block;
  border-bottom: 1px solid #000;
  color: #000;
  margin-right: 7px;
  line-height: 0.95em;
}

.mypage .grade:after {
  display: block;
  clear: both;
  content: "";
}

.mb60 {
  margin-bottom: 60px !important;
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

.table_title .btn_more_noline {
  color: #000;
  position: absolute;
  bottom: 8px;
  right: 0;
  line-height: 1em;
  padding-right: 9px;
  font-size: 16px;
  color: #000;
}

.table_title .btn_more_noline:after {
  content: "";
  display: block;
  position: absolute;
  top: 6px;
  right: 0;
  width: 7px;
  height: 7px;
  background: url(//static.wconcept.co.kr/web/images/common/spr-common.png) -90px -60px
    no-repeat;
}

table {
  border-top: 2px solid #171717;
  border-bottom: 1px solid #171717;
  table-layout: fixed;
  margin-bottom: 60px;
  width: 100%;
  border-collapse: separate;
  border-spacing: 0;
}

table.cols thead th {
  height: 68px;
  font-family: "NotoSansKR-Medium";
  font-size: 14px;
  padding: 0 14px;
  color: #000;
  text-align: center;
  vertical-align: middle;
  border-bottom: 1px solid #b5b5b5;
}

table.cols tbody tr td.no_data {
  height: 150px;
  color: #333;
  font-size: 14px;
}

table.cols tbody tr td {
  height: 68px;
  font-family: "ProximaNova-Regular", "Noto Sans KR";
  text-align: center;
  vertical-align: middle;
  padding: 14px 0;
}

table tbody tr:first-child th,
table tbody tr:first-child td {
  border-color: transparent;
  border: none;
}

.mypage .my_heart .table_title {
  border-bottom: 2px solid #000;
  padding-bottom: 10px;
  margin-bottom: 15px;
}

.mypage .my_heart .table_title .btn_more_noline {
  bottom: 8px;
}

.thumbnail_list {
  width: 100%;
  position: relative;
  overflow: hidden;
}

.mypage .my_heart .thumbnail_list ul {
  margin-right: -20px;
}

.mypage .my_heart .thumbnail_list ul li {
  width: 190px;
  margin-right: 20px;
}

.thumbnail_list ul li {
  float: left;
  margin-bottom: 50px;
  position: relative;
}

.thumbnail_list ul li .heart.active {
  background-position: -360px 0;
}

.thumbnail_list ul li .heart {
  display: block;
  width: 18px;
  height: 17px;
  position: absolute;
  top: 10px;
  right: 10px;
  background: url(//static.wconcept.co.kr/web/images/common/spr-common.png) -330px
    0 no-repeat;
  z-index: 1;
}

.thumbnail_list ul li a {
  font-family: "ProximaNova-Regular", "Noto Sans KR";
  color: #000;
}

.mypage .my_heart .thumbnail_list ul li .img {
  height: 253px;
}

.thumbnail_list ul li .img {
  position: relative;
  margin-bottom: 16px;
  overflow: hidden;
  background: url(//static.wconcept.co.kr/mobile/images/common/onerror_img_product.png)
    no-repeat center/100%;
  height: 260px;
}

.thumbnail_list ul li .img img {
  display: block;
  width: 100%;
  height: 100%;
  -o-object-fit: cover;
  object-fit: cover;
}

.thumbnail_list ul li .text {
  padding: 0 4px 0 6px;
}

.thumbnail_list ul li .text .brand {
  font-size: 14px;
  line-height: 16px;
  margin: 0 0 7px 0 !important;
  font-family: "ProximaNova-Semibold", "NotoSansKR-Medium";
  width: auto;
  min-height: auto;
  max-height: initial;
  white-space: nowrap;
  text-overflow: ellipsis;
  overflow: hidden;
}

.thumbnail_list ul li .text .front {
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
  margin-bottom: 8px;
  color: #777;
}

.thumbnail_list ul li .text .product {
  font-size: 12px;
  line-height: 16px;
  color: #777;
  height: 16px;
  overflow: hidden;
  margin-bottom: 16px;
}

.thumbnail_list ul li .text .price {
  position: relative;
  font-family: "ProximaNova-Regular";
  line-height: normal;
  margin-bottom: 14px;
  display: flex;
  justify-content: flex-start;
  align-items: baseline;
}

.thumbnail_list ul li .text .price .discount_price {
  font-family: "ProximaNova-Semibold";
  font-size: 16px;
  color: #333;
  margin-right: 4px;
}

.thumbnail_list ul li .text .price .base_price {
  font-size: 12px;
  color: #aaa;
  text-decoration: line-through;
}

.thumbnail_list ul li .text .price .discount_rate {
  display: block;
  position: absolute;
  top: 0;
  right: 0;
  font-family: "ProximaNova-Semibold";
  font-size: 16px;
  color: #ff4000;
}

.thumbnail_list ul li .labels {
  margin-top: 10px;
}

.labels {
  margin-bottom: 5px;
  max-height: 45px;
  overflow: hidden;
}

.labels:after {
  display: block;
  clear: both;
  content: "";
}

.mypage .my_heart .thumbnail_list ul li.btn_wrap {
  float: right;
}

.mypage .my_heart .thumbnail_list ul li {
  width: 190px;
  margin-right: 20px;
}

.mypage .my_heart .thumbnail_list ul li.btn_wrap a {
  display: block;
  height: 87px;
  width: 100%;
  padding: 25px 0 0 26px;
  border-bottom: 1px solid #e2e2e2;
  text-transform: uppercase;
  position: relative;
}

.mypage .my_heart .thumbnail_list ul li.btn_wrap a span {
  display: block;
  font-size: 14px;
  color: #666;
  line-height: 1em;
}

.mypage .my_heart .thumbnail_list ul li.btn_wrap a em {
  font-size: 20px;
  color: #000;
  line-height: 1em;
}

.mypage .my_heart .thumbnail_list ul li.btn_wrap a p {
  width: 48px;
  height: 48px;
  position: absolute;
  top: 19px;
  right: 26px;
  background-color: #333;
  color: #fff;
  border-radius: 24px;
  font-size: 20px;
  line-height: 48px;
  text-align: center;
}
</style>
