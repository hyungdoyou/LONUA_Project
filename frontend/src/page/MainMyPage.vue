<template>
  <section id="container">
    <div class="sub_title_wrap">
      <h2 class="sub_title">my page</h2>
      <ol id="breadcrumb">
        <li><a href="/">home</a></li>
        <li>my page</li>
      </ol>
    </div>

    <MyPageComponent />

    <div class="mypage">
      <div class="grade">
        <div class="info">
          <h3>
            <strong>{{ userStore.user.name }}</strong>
            님 안녕하세요.
          </h3>
          <span class="grade_icon wpeople">현재 LONUA PEOPLE</span>
          <p>
            현재 회원님의 등급은
            <span class="grade_text wpeople"
              >&nbsp;&nbsp;"&nbsp;&nbsp;{{
                userStore.user.gradeType
              }}&nbsp;&nbsp;"&nbsp;&nbsp;</span
            >
            입니다.
          </p>
          <div class="notice_wrap">
            <div class="notice"></div>
          </div>
        </div>
        <ul>
          <li>
            <a href="/UserCoupon">
              <h4>쿠폰</h4>
              <p>
                <span>{{ couponStore.coupons.length }}</span>
                개
              </p>
            </a>
          </li>
          <li>
            <a href="/UserMileage">
              <h4>마일리지</h4>
              <p>
                <span>{{ userStore.mileage.toLocaleString() }}</span>
                P
              </p>
            </a>
          </li>
        </ul>
      </div>

      <div class="mb60">
        <img
          src="//image.wconcept.co.kr/images/builder/1/4/138/315/쇼핑리워드 마이페이지 PC_20231130120554_20231228112002.jpg"
        />
      </div>
      <div class="table_title">
        <h3>최근 주문내역</h3>
      </div>
      <table class="cols" id="rowspanTb">
        <colgroup>
          <col style="width: 135px" />
          <col style="width: 155px" />
          <col />
          <col style="width: 150px" />
          <col style="width: 125px" />
          <col style="width: 145px" />
          <col style="width: 130px" />
        </colgroup>
        <thead>
          <tr>
            <th>주문일</th>
            <th>주문번호</th>
            <th>상품정보</th>
            <th>수량</th>
            <th>상품금액</th>
            <th>진행상황</th>
            <th>리뷰</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td colspan="7" v-show="ordersStore.isOrderExist">
              주문하신 내역이 없습니다.
            </td>
          </tr>
        </tbody>
        <tbody>
          <tr
            v-for="orders in ordersStore.ordersList"
            :key="orders.ordersIdx"
            class="orders-detail"
          >
            <OrdersComponent :orders="orders" />
          </tr>
        </tbody>
      </table>

      <div class="my_heart">
        <div class="table_title">
          <h3>MY ♥</h3>
        </div>
        <div class="mypage_myheart">
          <div class="thumbnail_list">
            <ul>
              <li class="no_data" v-show="likesStore.isLikesExist">
                <p>
                  관심 상품을 MY
                  <i
                    class="fa fa-heart-o"
                    aria-hidden="true"
                    style="font-size: 12px"
                  ></i>
                  ITEM으로 설정해보세요.<br />세일, 입고 등 알림을 받으실 수
                  있어요.
                </p>
                <button
                  type="button"
                  name="button"
                  class="btn small dark_gray arrow"
                  @click="goShopping()"
                >
                  상품 보러가기
                </button>
              </li>
              <li v-for="likes in likesStore.likesList" :key="likes.likesIdx">
                <LikesComponent :likes="likes" />
              </li>
              <li class="btn_wrap">
                <a href="/UserLikes">
                  <span>my ♡</span>
                  <em>item</em>
                  <p>{{ likesStore.likes.length }}</p>
                </a>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
// import VueJwtDecode from "vue-jwt-decode";
import LikesComponent from "../components/LikesComponent.vue";
import OrdersComponent from "../components/OrdersComponent.vue";
import MyPageComponent from "../components/MyPageComponent.vue";
import { mapStores } from "pinia";
import { useUserStore } from "../stores/useUserStore";
import { useLikesStore } from "../stores/useLikesStore";
import { useOrdersStore } from "../stores/useOrdersStore";
import { useCouponStore } from "@/stores/useCouponStore.js";
export default {
  name: "MainMyPage",
  data() {
    return {
      page: 1,
      size: 3,
    };
  },
  computed: {
    ...mapStores(useLikesStore, useOrdersStore, useCouponStore, useUserStore),
  },
  components: {
    LikesComponent,
    OrdersComponent,
    MyPageComponent,
  },
  mounted() {
    this.userStore.getUserInfo();
    this.likesStore.getLikeList();
    this.ordersStore.getOrdersList(this.page, this.size);
    this.couponStore.getCoupon();
  },
  methods: {
    goShopping() {
      window.location.href = "/";
    },
  },
};
</script>

<style scoped>
#snb .snb_header ul li.active {
  background-color: #0e0e0e;
  color: white;
}
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
  padding-top: 25px;
  padding-left: 154px;
  position: relative;
}

.mypage .grade .info h3 {
  font-size: 18px;
  letter-spacing: 3.18px;
  color: #000;
  margin-bottom: 20px;
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
  top: 15px;
  left: 40px;
}

.mypage .grade_icon.wpeople {
  background: url(https://github.com/hyungdoyou/LONUA_Project/assets/148875644/0783d604-80e2-44a4-8be4-4ad085a06174)
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

table {
  width: 100%;
  border-collapse: separate;
}

table {
  border-spacing: 0;
}
.orders-detail {
  height: 130px;
}
.mypage .my_heart .thumbnail_list ul li.no_data {
  width: 1030px;
  text-align: center;
  padding-top: 79px;
}
.mypage .my_heart .thumbnail_list ul li.no_data p {
  font-size: 14px;
  line-height: 20px;
  vertical-align: bottom;
  margin-bottom: 16px;
}

.fa {
  display: inline-block;
  font: normal normal normal 14px/1 FontAwesome;
  font-size: inherit;
  text-rendering: auto;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}
.fa-heart-o:before {
  content: "\f08a";
}

a.btn,
button.btn,
input.btn,
span.btn {
  display: inline-block;
  min-width: 180px;
  padding: 0 20px;
  height: 50px;
  text-align: center;
  line-height: 48px;
  border: 1px solid #333;
  background-color: #fff;
  color: #000;
  font-family: "ProximaNova-Semibold", "Apple SD Gothic Neo",
    "NotoSansKR-Medium", "Malgun Gothic", "맑은 고딕", sans-serif;
  font-size: 14px;
}

a.btn.dark_gray,
button.btn.dark_gray,
input.btn.dark_gray,
span.btn.dark_gray {
  border-color: #333;
  background-color: #333;
  color: #fff;
}

a.btn.small,
button.btn.small,
input.btn.small,
span.btn.small {
  height: 40px;
  font-size: 14px;
  line-height: 38px;
}

a.btn.arrow:after,
button.btn.arrow:after,
input.btn.arrow:after,
span.btn.arrow:after {
  content: "";
  display: inline-block;
  width: 5px;
  height: 9px;
  margin-left: 10px;
  background: url(//static.wconcept.co.kr/web/images/common/spr-common.png) -120px -60px
    no-repeat;
}
</style>
