<template>
  <section id="container" data-custno="9150592">
    <div class="sub_title_wrap">
      <h2 class="sub_title">my page</h2>
      <ol id="breadcrumb">
        <li><a href="/">home</a></li>
        <li><a href="/MainMyPage">my page</a></li>
        <li><a href="/UserQuestion">문의내역</a></li>
        <li>리뷰</li>
      </ol>
    </div>

    <MyPageComponent />
    <div class="mypage">
      <div class="mb40">
          <img
            src="//image.wconcept.co.kr/images/builder/1/4/138/426/pc_1240x100_20221212095117_20230926164822_20240131153054.jpg"
            alt="24.02 친구초대"
          />
      </div>
      <div class="table_title contents">
        <h3>리뷰</h3>
      </div>
      <div class="table_title only_right mt20" id="paginganchor">
        <div class="count">
          총
          <span id="emCnt">{{reviewStore.reviews.length}}</span>
          건
        </div>
      </div>
      <div id="divList" style="display: block">
        <table class="cols mb45">
          <colgroup>
            <col />
            <col style="width: 170px" />
            <col style="width: 400px" />
            <col style="width: 100px" />
            <col style="width: 150px" />
          </colgroup>
          <thead>
            <tr>
              <th>상품정보</th>
              <th>주문일</th>
              <th>리뷰내용</th>
              <th>별점</th>
              <th>삭제</th>
            </tr>
          </thead>

          <tbody v-show="reviewStore.isReviewExist">
            <tr class="empty">
              <td colspan="4" class="no_data">작성 가능한 리뷰가 없습니다.</td>
            </tr>
          </tbody>
          <tbody v-for="reviews in reviewStore.reviews" :key="reviews.reviewIdx">
            <ReviewComponent :reviews="reviews"/>
          </tbody>
        </table>
        <ul class="pagination"></ul>
      </div>
      <div class="review_notify ty_tbl_bottom">
        <h4>리뷰 유의사항</h4>
        <ul class="dot_list">
          <li>
            리뷰 검수 중 운영 정책에 따라 노출 제한 처리될 수 있습니다.
            <ol>
              <li>실제 촬영한 사진이 아닌 경우</li>
              <li>
                상품의 식별이 어려운 경우(초점이 맞지 않거나, 포장된 채로 촬영
                또는 특정 부위만 촬영한 경우)
              </li>
              <li>동일한 사진을 반복되어 올리는 경우</li>
              <li>
                상품과 관련 없는 내용이거나 판매/홍보성 글 등 부적합한 내용인
                경우
              </li>
            </ol>
          </li>
          <li>
            노출 제한 처리 후 사진 리뷰, 일반 리뷰로 재게시될 수 있으며, 재게시
            되는 경우 리뷰유형에 맞는 포인트가 적립됩니다.
          </li>
          <li>작성하신 리뷰의 저작권은 " LONUA " 에 귀속됩니다.</li>
          <li>
            동일 상품을 여러개 구매하신 경우 리뷰는 1개만 작성하실 수 있습니다.
          </li>
          <li>
            작성하신 리뷰 내용 및 첨부파일에 개인정보가 노출되지 않도록 주의해
            주세요.
          </li>
        </ul>
      </div>
    </div>
  </section>
</template>

<script>
import ReviewComponent from "../components/ReviewComponent.vue";
import MyPageComponent from "@/components/MyPageComponent.vue";
import { mapStores } from "pinia";
import { useReviewStore } from "@/stores/useReviewStore.js";

export default {
  computed: {
    ...mapStores(useReviewStore),
  },
  components: {
    MyPageComponent, ReviewComponent
  },
  mounted() {
    this.reviewStore.getReviewList();
  },
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

table {
  border-top: 2px solid #171717;
  border-bottom: 1px solid #171717;
  table-layout: fixed;
  margin-bottom: 60px;
}

table {
  width: 100%;
  border-collapse: separate;
}

table {
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

.mypage .notice_info_list.mt55 {
  margin-top: 55px;
}

.mypage .notice_info_list h3 {
  line-height: 15px;
  vertical-align: middle;
  margin-bottom: 8px;
  font-size: 16px;
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
/*------------------- 리뷰 메인 ---------------------- */
.mypage {
  width: 1240px;
  margin: 0 auto;
}

.mypage {
  font-family: "ProximaNova-Regular", "Apple SD Gothic Neo", "Noto Sans KR",
    "Malgun Gothic", "맑은 고딕", sans-serif;
}

.mb40 {
  margin-bottom: 40px !important;
}
.center-block {
  display: block;
  margin-left: auto;
  margin-right: auto;
}

img {
  border: 0;
}

a,
img,
input,
button,
select,
textarea {
  vertical-align: middle;
}

.table_title {
  position: relative;
  height: 50px;
}

.table_title.contents {
  height: 41px;
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

.mt10 {
  margin-top: 10px !important;
}

.tab {
  width: 100%;
  border-bottom: 2px solid #000;
}

.tab li {
  float: left;
  border: 1px solid #b5b5b5;
  border-bottom: none;
  border-left-width: 0;
  background-color: #fff;
  height: 60px;
}

.tab li.active {
  border: 2px solid #000;
  border-bottom: none;
  height: 62px;
  margin-bottom: -2px;
}

.tab.tab_item2 li {
  width: 50%;
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

.tab:after {
  display: block;
  clear: both;
  content: "";
}

.table_title.only_right {
  height: 23px;
  margin-bottom: 10px;
  margin-top: 30px;
}

.table_title .count {
  position: absolute;
  bottom: 9px;
  right: 0;
  font-size: 18x;
  color: #666;
  line-height: 1em;
}

.table_title.only_right .count {
  bottom: auto;
  top: 0;
}

.table_title .count span {
  color: #010101;
}

table {
  width: 100%;
  border-collapse: separate;
}

table {
  border-top: 2px solid #171717;
  border-bottom: 1px solid #171717;
  table-layout: fixed;
  margin-bottom: 60px;
}

.mb45 {
  margin-bottom: 45px !important;
}

.mypage table.cols tbody tr.empty td.no_data {
  height: 190px;
  color: #333;
  font-size: 20px;
  line-height: 22px;
  padding: 80px 0 80px;
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

.orders-detail .product {
  display: flex;
  align-items: center;
}

.orders-detail .product .item_aside {
  width: 150px;
  margin-right: -50px;
}

.orders-detail .product .item_aside-review {
  width: 150px;
  margin-right: 1px;
  margin-left: 40px;
}
.orders-detail .product .img {
  width: 75px; /* 이미지의 원하는 크기로 조절 */
  overflow: hidden;
}

.orders-detail .product img {
  display: block;
  width: 100%;
  height: auto;
}

.orders-detail .product .item_info {
  width: calc(100% - 215px);
  margin-top: 11px;
}

.orders-detail .product .item_info p {
  margin: 0;
  margin-top: 20px;
  margin-bottom: 20px;
}

.orders-detail {
  height: 130px;
}

.orders-detail .product .img {
  max-width: 100%;
  margin-top: 45px;
  margin-bottom: -10px;
  margin-left: 40px;
  margin-right: -10px;
}

.orders-detail .product img {
  display: block;
  width: 100%;
  height: auto;
  max-height: 100%;
}
</style>
