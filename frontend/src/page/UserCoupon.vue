<template>
  <section id="container">
    <div class="sub_title_wrap">
      <h2 class="sub_title">my page</h2>
      <ol id="breadcrumb">
        <li><a href="/">home</a></li>
        <li><a href="/MainMyPage">my page</a></li>
        <li><a href="/UserCoupon"></a>쿠폰관리</li>
        <li><a href="/UserCoupon"></a>받은 쿠폰</li>
      </ol>
    </div>

    <MyPageComponent />
    <div class="mypage">
      <div class="table_title contents">
        <h3>쿠폰</h3>
      </div>
      <div class="mb60">
        <img
          src="//image.wconcept.co.kr/images/builder/1/4/138/316/pc_1240x100_20221212095117_20230926164822_20240131152554.jpg"
        />
      </div>
      <!-- 쿠폰 리스트 -->
      <div class="my_coupon_available_list">
        <div
          class="coupon_overall_shape"
          v-for="coupons in couponStore.coupons"
          :key="coupons.couponIdx"
        >
          <CouponComponent :coupons="coupons" />
        </div>
      </div>
      <ul class="pagination mb60">
        <li class="first">
          <a href="javascript:;" style="cursor: default" title="처음">first</a>
        </li>
        <li class="prev">
          <a href="javascript:;" style="cursor: default">prev</a>
        </li>
        <li class="active">
          <a
            href="javascript:;"
            style="cursor: default; margin: 0 5px 0 5px"
            title="1"
            >1</a
          >
        </li>
        <li class="next">
          <a href="javascript:;" style="cursor: default">next</a>
        </li>
        <li class="last">
          <a href="javascript:;" style="cursor: default" title="마지막">last</a>
        </li>
      </ul>
      <div class="my_coupon_usage_notify">
        <h3>쿠폰 사용 안내</h3>
        <ul class="list_dot">
          <li>
            일부 상품은 쿠폰 적용이 불가 할 수 있으며, 유효기간 및 적용 대상이
            변경될 수 있습니다.
          </li>
          <li>
            중복 할인이 불가한 쿠폰의 경우 사용에 제한이 있을 수 있습니다.
          </li>
          <li>
            상품쿠폰은 상품 단위로만 적용 가능하며, 장바구니 쿠폰은 전체 상품에
            적용 가능합니다. (일부상품 제외)
          </li>
          <li>유효기간이 지난 쿠폰은 재지급 되지 않습니다.</li>
          <li>
            할인쿠폰의 할인금액이 상품의 판매가를 초과할 경우 사용이
            불가능합니다.
          </li>
        </ul>
      </div>
    </div>
  </section>
</template>

<script>
import MyPageComponent from "../components/MyPageComponent.vue";
import CouponComponent from "../components/CouponComponent.vue";
import { mapStores } from "pinia";
import { useCouponStore } from "@/stores/useCouponStore.js";

export default {
  name: "UserCoupon",
  computed: {
    ...mapStores(useCouponStore),
  },
  components: {
    MyPageComponent,
    CouponComponent,
  },
  mounted() {
    this.couponStore.getCoupon();
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
/*-------------------------여기부터 본문-------------------------*/

.mypage {
  width: 1240px;
  margin: 0 auto;
}

.mypage {
  font-family: "ProximaNova-Regular", "Apple SD Gothic Neo", "Noto Sans KR",
    "Malgun Gothic", "맑은 고딕", sans-serif;
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

.mb60 {
  margin-top: 35px;
  margin-bottom: 60px !important;
}

.my_coupon_available_list {
  display: flex;
  justify-content: flex-start;
  align-items: flex-start;
  flex-wrap: wrap;
  padding-bottom: 20px;
  margin-top: 40px;
}

.coupon_overall_shape {
  border: 1px solid #e9e9e9;
  padding: 24px 26px 20px;
  height: 196px;
  font-family: "ProximaNova-Regular", "Noto Sans KR", serif;
}

.my_coupon_available_list .coupon_overall_shape {
  width: 400px;
  margin: 0 9.5px 20px;
}

.my_coupon_available_list .coupon_overall_shape:nth-child(3n + 1) {
  margin-left: 0;
}

.inner_shape_flex {
  margin-top: 10px;
  display: flex;
  justify-content: flex-start;
  align-items: center;
  position: relative;
}

.inner_shape_flex .cpn_item_str {
  font-size: 28px;
  line-height: 30px;
  color: #fa5500;
  font-family: "ProximaNova-Medium", "Noto Sans KR", serif;
  font-weight: 500;
  margin-right: 20px;
}

.inner_shape_flex .cpn_use_type {
  border: 1px solid #e9e9e9;
  font-size: 12px;
  height: 20px;
  line-height: 20px;
  padding: 0 4px;
  color: #9d9d9d;
}

.inner_shape_ctns {
  margin-top: 14px;
}

.cpn_item_name {
  font-size: 17px;
  color: #000;
  line-height: 24px;
  white-space: normal;
  overflow: hidden;
  font-weight: 500;
}

.cpn_item_str strong,
.cpn_item_name strong,
.cpn_item_period strong {
  font-weight: bold;
}

.inner_shape_column {
  display: flex;
  justify-content: flex-start;
  align-items: center;
  color: #7b7b7b;
  font-size: 14px;
  height: 18px;
  flex-wrap: wrap;
  margin-top: 4px;
}

.inner_shape_ctns .inner_shape_column {
  height: 42px;
  align-items: flex-start;
}

.inner_shape_column span.cpn_item_apply_amount {
  margin-right: 6px;
}

.inner_shape_aside {
  margin-top: 30px;
  height: 24px;
  position: relative;
}

button[disabled],
html input[disabled] {
  cursor: default;
}

.inner_shape_aside .btn_coupon_prod {
  border: 1px solid #c4c4c4;
  padding: 4px 17px 2px 10px;
  font-size: 12px;
  color: #7b7b7b;
  position: relative;
  border-radius: 20px;
  line-height: 16px;
}

.inner_shape_aside .btn_coupon_prod:disabled {
  padding-right: 10px;
  background: #f5f5f5;
  border-color: #f5f5f5;
}

.inner_shape_aside .cpn_item_period {
  position: absolute;
  right: 0;
  font-size: 14px;
  line-height: 20px;
  color: #7b7b7b;
  font-family: "ProximaNova-Regular", "Noto Sans KR", serif;
  font-weight: 500;
}
/*----------------------페이지 스타일 번호----------------------*/
.pagination {
  display: table;
  margin: 0 auto -8px;
  font-size: 0;
  text-align: center;
}

.pagination li {
  display: table-cell;
  width: 33px;
  line-height: 22px;
  height: 22px;
  vertical-align: text-top;
}

.pagination li.first,
.pagination li.prev,
.pagination li.next,
.pagination li.last {
  margin: 0;
  position: relative;
}

.pagination li.first {
  margin-right: 20px;
}

.pagination li a {
  display: block;
  font-family: "ProximaNova-Light";
  font-size: 14px;
  color: #333;
  text-align: center;
  letter-spacing: -0.01em;
  line-height: 16px;
}

.pagination li.first a,
.pagination li.prev a,
.pagination li.next a,
.pagination li.last a {
  overflow: hidden;
  text-indent: -9999px;
  background: url(//static.wconcept.co.kr/web/images/common/spr-common.png) 0 0
    no-repeat;
  height: 11px;
  width: 12px;
  position: absolute;
  top: 0;
  left: 0;
  margin-top: 3px;
}

.pagination li.first a {
  background-position: 0 -30px;
  left: 8px;
}

.pagination li.prev {
  margin-right: 22px;
}

.pagination li.prev a {
  width: 6px;
  background-position: -30px -30px;
  left: 7px;
}

.pagination li.active a {
  font-family: "ProximaNova-Bold";
}

.pagination li.next {
  margin-left: 23px;
}

.pagination li.next a {
  width: 6px;
  background-position: -60px -30px;
  left: auto;
  right: 7px;
}

.pagination li.last {
  margin-left: 20px;
}

.pagination li.last a {
  background-position: -90px -30px;
  left: auto;
  right: 8px;
}

.my_coupon_usage_notify {
  padding-top: 40px;
  border-top: 1px solid #e9e9e9;
}

.my_coupon_usage_notify h3 {
  color: #000;
  font-size: 15px;
  font-weight: 400;
  margin-bottom: 14px;
}

.list_dot li {
  position: relative;
  padding-left: 12px;
  font-family: "ProximaNova-Regular", "Noto Sans KR";
  font-size: 12px;
  line-height: 22px;
  color: #666;
}

.my_coupon_usage_notify ul.list_dot li {
  color: #7b7b7b;
  line-height: 18px;
  font-size: 13px;
  padding-left: 9px;
}

.list_dot li:after {
  content: "";
  display: block;
  width: 3px;
  height: 3px;
  border-radius: 50%;
  background-color: #171717;
  position: absolute;
  top: 9px;
  left: 0;
}

.my_coupon_usage_notify ul.list_dot li:after {
  background: #7b7b7b;
  border-radius: 50%;
  top: 50%;
  margin-top: -2px;
}

.my_coupon_usage_notify ul.list_dot li + li {
  margin-top: 10px;
}
</style>
