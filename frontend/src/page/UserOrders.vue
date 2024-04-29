<template>
  <section id="container">
    <div class="sub_title_wrap">
      <h2 class="sub_title">my page</h2>
      <ol id="breadcrumb">
        <li><a href="/">home</a></li>
        <li><a href="/MainMyPage">my page</a></li>
        <li><a href="/UserOrders">주문관리</a></li>
        <li>주문/배송조회</li>
      </ol>
    </div>

    <MyPageComponent />

    <div class="mypage">
      <div id="divList" style="display: block">
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
      </div>
      <div class="notice_info_list mt55">
        <h3>안내 드립니다.</h3>
        <div class="info_step all nomember">
          <div class="step step1">
            <dl>
              <dt class="order_start">주문접수</dt>
              <dd>
                주문이 접수되어 입금확인 중입니다.
                <ul class="link">
                  <li>
                    <a
                      href="/CustomerCenter/FAQ?SchText=%EC%A3%BC%EB%AC%B8%EB%B0%A9%EB%B2%95"
                      target="_blank"
                    >
                      주문방법
                    </a>
                  </li>
                  <li>
                    <a
                      href="/CustomerCenter/FAQ?SchText=%EC%A3%BC%EB%AC%B8%EA%B2%B0%EC%A0%9C"
                      target="_blank"
                    >
                      주문/결제 확인
                    </a>
                  </li>
                </ul>
              </dd>
            </dl>
            <dl class="arrow">
              <dt class="pay_end">결제완료</dt>
              <dd>
                결제 및 무통장입금 확인이
                <br />
                완료되었습니다.
                <ul class="link">
                  <li>
                    <a
                      href="/CustomerCenter/FAQ?SchText=%EA%B2%B0%EC%A0%9C%EC%88%98%EB%8B%A8"
                      target="_blank"
                    >
                      결제수단 안내
                    </a>
                  </li>
                </ul>
              </dd>
            </dl>
            <p class="desc">취소, 옵션/배송지 변경 가능</p>
          </div>
          <div class="step step2">
            <dl class="arrow">
              <dt class="pdt_ready">상품준비중</dt>
              <dd>
                배송을 위한 상품을 준비하고 있습니다.
                <ul class="link">
                  <li>
                    <a
                      href="/CustomerCenter/FAQ?SchText=%EB%B0%B0%EC%86%A1%EB%B9%84"
                      target="_blank"
                      >배송비</a
                    >
                  </li>
                  <li>
                    <a
                      href="/CustomerCenter/FAQ?SchText=%EB%B0%B0%EC%86%A1%EC%A7%80%EC%97%B0"
                      target="_blank"
                    >
                      배송지연
                    </a>
                  </li>
                </ul>
              </dd>
            </dl>
            <p class="desc">
              취소 가능, 옵션/배송비 변경 불가
              <br />
              (판매자가 이미 상품 발송한 경우 취소요청 철회 가능성 있음)
            </p>
          </div>
          <div class="step step3">
            <dl class="arrow">
              <dt class="return_ing">배송중</dt>
              <dd>
                택배사로 상품이 전달되어
                <br />
                배송이 시작되었습니다.
                <ul class="link">
                  <li>
                    <a
                      href="/CustomerCenter/FAQ?SchText=%EB%B0%B0%EC%86%A1%EC%A7%80%EC%97%B0"
                      target="_blank"
                    >
                      배송지연
                    </a>
                  </li>
                </ul>
              </dd>
            </dl>
            <p class="desc">
              취소, 옵션/배송지 변경 불가
              <br />
              (고객센터로 문의)
            </p>
          </div>
          <div class="step step4">
            <dl>
              <dt class="deliver_end">배송완료</dt>
              <dd>
                상품이 고객님께 도착하여
                <br />
                배송이 완료되었습니다.
                <ul class="link">
                  <li>
                    <a
                      href="/CustomerCenter/FAQ?SchText=%EB%B0%98%ED%92%88%EC%8B%A0%EC%B2%AD"
                      target="_blank"
                    >
                      반품신청 안내
                    </a>
                  </li>
                  <li>
                    <a
                      href="/CustomerCenter/FAQ?SchText=%EA%B5%90%ED%99%98%EC%8B%A0%EC%B2%AD"
                      target="_blank"
                    >
                      교환신청 안내
                    </a>
                  </li>
                </ul>
              </dd>
            </dl>
            <p class="desc">
              반품/교환 가능
              <br />
              (배송완료 후 7일까지)
            </p>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import OrdersComponent from "../components/OrdersComponent.vue";
import { mapStores } from "pinia";
import { useOrdersStore } from "../stores/useOrdersStore";
import MyPageComponent from "@/components/MyPageComponent.vue";
export default {
  name: "UserOrders",
  data() {
    return {
      page: 1,
      size: 3,
    };
  },
  computed: {
    ...mapStores(useOrdersStore),
  },
  components: {
    OrdersComponent,
    MyPageComponent,
  },
  mounted() {
    this.ordersStore.getOrdersList(this.page, this.size);
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

.mypage .notice_info_list h3:before {
  content: "";
  background: url(//static.wconcept.co.kr/web/images/common/spr-mypage.png) -50px
    0 no-repeat;
  width: 15px;
  height: 15px;
  display: inline-block;
  vertical-align: -1px;
  margin-right: 8px;
}

.mypage .info_step.all {
  margin-top: 15px;
}

.mypage .info_step.all .step:first-child {
  width: 490px;
}
.mypage .info_step .step:first-child {
  margin-left: 0;
}
.mypage .info_step.nomember .step {
  height: 300px;
}
.mypage .info_step .step {
  position: relative;
  float: left;
  height: 250px;
  margin-left: 10px;
  padding: 30px;
  background: #fafafa;
}

.mypage .info_step.all .step1 dl:first-child {
  padding-right: 45px;
}

.mypage .info_step.all .step:first-child dl {
  width: 50%;
}

.mypage .info_step .step dl {
  position: relative;
  float: left;
  padding-left: 10px;
  font-family: "Noto Sans KR";
}

.mypage .info_step.all .step1 dl:first-child:before {
  position: absolute;
  top: 52px;
  right: 0;
  width: 2px;
  height: 100px;
  border-right: 1px dashed #dbdbdb;
  content: "";
}

.mypage .info_step .step dl dt {
  color: #333;
  font-size: 16px;
  line-height: 34px;
}

.mypage .info_step.all dl dt {
  font-size: 20px;
}

.mypage .info_step .step dl dt:before {
  display: inline-block;
  height: 34px;
  margin-right: 9px;
  background-repeat: no-repeat;
  background-size: 100%;
  background-position: center;
  vertical-align: top;
  content: "";
}

.mypage .info_step.all dl dt:before {
  margin-right: 12px;
}

.mypage .info_step.all dl dt.order_start:before {
  width: 28px;
  background-image: url(//static.wconcept.co.kr/web/images/common/ico_01_order.png);
}

.mypage .info_step .step dl dd {
  margin: 0;
  margin-top: 13px;
  color: #666;
  font-size: 13px;
  line-height: 1.6;
}

.mypage .info_step .step .link {
  margin-top: 20px;
  font-size: 12px;
}

.mypage .info_step .step .link a {
  display: inline-block;
  color: #666;
  position: relative;
  padding-right: 8px;
}

.mypage .info_step .step .link a:after {
  display: inline-block;
  width: 4px;
  height: 6px;
  background: url(//static.wconcept.co.kr/web/images/common/ico_arrow_6.png)
    no-repeat;
  background-size: 100%;
  content: "";
  position: absolute;
  top: 50%;
  right: 0;
  margin-top: -3px;
}

.mypage .info_step.all .step dl dd:after {
  position: absolute;
  top: 0;
  right: -16px;
  z-index: 1;
  width: 32px;
  height: 32px;
  background: url(//static.wconcept.co.kr/web/images/common/ico_deliivery_flow_32.png)
    no-repeat;
  background-size: 100%;
  content: "";
}

.mypage .info_step.all .step.step4 dl dd:after {
  content: none;
}

.mypage .info_step.all .step1 dl:nth-child(2),
.mypage .info_step.all .step2 dl:nth-child(2) {
  padding-left: 40px;
}

.mypage .info_step.all dl dt.pay_end:before {
  width: 40px;
  background-image: url(//static.wconcept.co.kr/web/images/common/ico_02_card.png);
}

.mypage .info_step .step .desc {
  position: absolute;
  top: 204px;
  left: 30px;
  padding-top: 20px;
  border-top: 1px solid #feddcc;
  color: #fa5500;
  font-size: 13px;
  line-height: 20px;
  text-align: center;
}

.mypage .info_step.all .step .desc {
  right: 30px;
}

.mypage .info_step .step .desc:before {
  position: absolute;
  top: 0;
  left: 50%;
  width: 1px;
  height: 10px;
  background: #feddcc;
  content: "";
}

.mypage .info_step .step:after {
  display: block;
  clear: both;
  content: "";
}

.mypage .info_step.all .step {
  width: 240px;
}

.mypage .info_step.all .step dl {
  width: 100%;
}

.mypage .info_step.all dl dt.pdt_ready:before {
  width: 30px;
  background-image: url(//static.wconcept.co.kr/web/images/common/ico_03_box.png);
}

.mypage .info_step.all .step dl.arrow:after {
  right: -52px;
}

.mypage .info_step .step dl dt.return_ing:before {
  width: 34px;
  background-image: url(//static.wconcept.co.kr/web/images/common/ico_04_delivery.png);
}

.mypage .info_step.all dl dt.deliver_end:before {
  width: 32px;
  background-image: url(//static.wconcept.co.kr/web/images/common/ico_05_completed.png);
}

.mypage .info_step.all .step4 .desc {
  width: 180px;
  left: inherit;
}

.mypage .info_step:after {
  display: block;
  clear: both;
  content: "";
}

.mypage .info_step:after {
  display: block;
  clear: both;
  content: "";
}
.orders-detail .review-btn {
  background-color: #4caf50;
  color: white;
  padding: 8px 16px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.orders-detail .review-btn:hover {
  background-color: #45a049;
}

.orders-detail .product {
  display: flex;
  align-items: center;
}

.orders-detail .product .item_aside {
  width: 150px;
  margin-right: 16px;
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
  width: calc(100% - 96px);
  margin-top: 50px;
}

.orders-detail .product .item_info p {
  margin: 0;
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
