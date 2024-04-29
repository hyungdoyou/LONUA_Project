<template>
  <div class="loadingio-spinner-spinner" v-if="cartStore.isLoading">
    <div class="ldio-f4nnk2ltl0v">
      <div></div>
      <div></div>
      <div></div>
      <div></div>
      <div></div>
      <div></div>
      <div></div>
      <div></div>
      <div></div>
      <div></div>
      <div></div>
      <div></div>
    </div>
  </div>
  <section id="contents">
    <div class="sub-title-wrap">
      <h2 class="sub-title">SHOPPING BAG</h2>
    </div>
    <div class="shoppingbag">
      <ul class="step">
        <li class="active">쇼핑백</li>
        <li>주문결제</li>
        <li>주문완료</li>
      </ul>
      <div class="mb60 basket_promotion_banner">
        <img
          src="//image.wconcept.co.kr/images/builder/1/4/138/318/PC_mypage_1240x100_02_20231106104834.jpg"
        />
      </div>
      <div class="bag-wrap">
        <div class="bag-main">
          <h3 class="tit first_title">
            쇼핑백 상품
            <span>
              (
              <em>{{ cartStore.cartCount }}</em>
              )
            </span>
          </h3>
          <table class="cols tbl_product">
            <colgroup>
              <col style="width: 20px" />
              <col />
              <col style="width: 80px" />
              <col style="width: 150px" />
              <col style="width: 150px" />
              <col style="width: 130px" />
              <col style="width: 30px" />
            </colgroup>
            <thead>
              <tr>
                <th>
                  <!-- <span class="input_button small square">
                    <input
                      type="checkbox"
                      name="checkAll"
                      id="checkAll"
                      class="check_all"
                      v-model="cartStore.isChecked"
                      @click="checkAll()"
                    />
                    <label for="checkAll" @click="cartStore.toggleAllCart()"
                      >&nbsp;</label
                    >
                  </span> -->
                </th>
                <th>상품정보</th>
                <th>수량</th>
                <th>가격</th>
                <th>쿠폰할인</th>
                <th colspan="2">쇼핑백 삭제</th>
              </tr>
              <tr v-show="cartStore.isCartExist">
                <td colspan="7" style="text-align: center" class="noCart">
                  <p>쇼핑백에 담긴 상품이 없습니다.</p>
                  <p></p>
                </td>
              </tr>
              <!------------여기에 장바구니 리스트 출력------------------>
              <tr v-for="cart in cartStore.cartList" :key="cart.cartIdx">
                <CartComponent :cart="cart" />
              </tr>
              <!------------여기에 장바구니 리스트 출력------------------>
            </thead>
            <tbody>
              <tr class="calculate_sub" id="totalBox_300323316417">
                <td colspan="7">
                  <input type="hidden" class="deliveryPrice" value="0" />
                  <div class="calculate_item">
                    <p class="calculate_aside">
                      <span class="totalPrice">{{
                        cartStore.totalPrice.toLocaleString()
                      }}</span>
                      원 +
                      <span class="num">0</span>
                      원(배송비) =
                      <strong class="totalPrice">{{
                        cartStore.totalPrice.toLocaleString()
                      }}</strong>
                      원
                    </p>
                  </div>
                </td>
              </tr>
            </tbody>
          </table>
          <div class="tbl_under_box">
            <div class="bx_btn">
              <button
                type="button"
                name="button"
                class="btn"
                @click="cartStore.deleteAllCartItem()"
              >
                전체상품 삭제
              </button>
              <button
                type="button"
                name="button"
                class="btn gray"
                @click="continueShopping()"
              >
                쇼핑계속하기
              </button>
            </div>
            <div class="shoppingbag_count_limit_notify">
              <ul class="unorder_list">
                <li>쇼핑백에 상품은 최대 100개까지 담을 수 있습니다.</li>
              </ul>
            </div>
          </div>
        </div>
        <div class="payment" style="left: 949.258px">
          <div class="bx_total">
            <ul>
              <li>
                <strong>총 상품 금액</strong>
                <p>
                  <em class="totalPrice">{{
                    cartStore.totalPrice.toLocaleString()
                  }}</em>
                  원
                </p>
              </li>
              <li>
                <strong>배송비</strong>
                <p>
                  <span>+&nbsp;&nbsp;</span>
                  <em id="counterTotdeliveryamt">0</em>
                  원
                </p>
              </li>
              <li v-show="couponStore.isCouponApply">
                <strong>할인쿠폰</strong>
                <p>
                  <span>-</span
                  ><em id="counterTotalApplyCouponAmt">{{
                    couponStore.discountPrice.toLocaleString()
                  }}</em
                  >원
                </p>
              </li>
              <li class="total">
                <strong>총 결제금액</strong>
                <p>
                  <em class="totalPrice" id="totalOrdersPrice">{{
                    cartStore.totalPrice.toLocaleString()
                  }}</em>
                  원
                </p>
              </li>
              <li class="point">
                <strong>적립예정 포인트</strong>
                <p>
                  <em id="totalPoint">{{
                    cartStore.totalPoint.toLocaleString()
                  }}</em>
                  p
                </p>
              </li>
            </ul>
            <button
              type="button"
              name="button"
              @click="cartStore.submitOrder()"
              class="btn_order"
            >
              선택상품 주문하기
            </button>

            <button type="button" class="btn_gift_selected">선물하기</button>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import CartComponent from "../components/CartComponent.vue";
import { mapStores } from "pinia";
import { useCartStore } from "../stores/useCartStore";
import { useCouponStore } from "../stores/useCouponStore";

export default {
  name: "UserCart",
  data() {
    return {
      page: 1,
      size: 5,
    };
  },
  computed: {
    ...mapStores(useCartStore, useCouponStore),
  },
  methods: {
    continueShopping() {
      window.location.href = "/";
    },
    checkAll() {
      this.checkbox1 = !this.checkbox1;
      this.checkbox2 = !this.checkbox2;
      this.checkbox3 = !this.checkbox3;
      this.checkbox4 = !this.checkbox4;
    },
  },
  components: {
    CartComponent,
  },
  mounted() {
    this.cartStore.getCartList(this.page, this.size);
  },
};
</script>

<style scoped>
.section {
  display: block;
}

.sub-title-wrap {
  padding-top: 55px;
  padding-bottom: 36px;
  position: relative;
  min-width: 1240px;
}

.sub-title {
  color: #000;
  font-family: "ProximaNova-Thin", "Noto Sans KR";
  font-size: 44px;
  line-height: 44px;
  text-align: center;
  text-transform: uppercase;
}

.shoppingbag {
  width: 1240px;
  margin: 0 auto;
}

.shoppingbag .step {
  width: 305px;
  margin: -16px auto 80px;
  text-align: center;
}

.shoppingbag .step li.active {
  color: #000;
}

.shoppingbag .step li {
  display: inline;
  font-size: 14px;
  color: #a1a1a1;
  position: relative;
}

.shoppingbag .step li + li {
  padding-left: 37px;
}

.shoppingbag .step li + li:before {
  content: "";
  width: 7px;
  height: 12px;
  background: url(//static.wconcept.co.kr/web/images/common/ico_order_step_12.png)
    no-repeat;
  background-size: 100%;
  position: absolute;
  top: 3px;
  left: 13px;
}

.mb60 {
  margin-bottom: 60px !important;
}

.center-block {
  display: block;
  margin-left: auto;
  margin-right: auto;
}

a {
  vertical-align: middle;
  text-decoration: none;
  color: #000;
  background-color: transparent;
  cursor: pointer;
}

img {
  vertical-align: middle;
  border: 0;
}

.shoppingbag .bag-wrap {
  position: relative;
  margin-bottom: 60px;
}

.shoppingbag .bag-wrap .bag-main {
  float: left;
  width: 920px;
}

.shoppingbag .tit {
  position: relative;
  margin-bottom: 15px;
  font-size: 24px;
  line-height: 34px;
}

h3 {
  margin: 0;
  font-weight: normal;
  font-family: "ProximaNova-Thin", "Noto Sans KR";
}

.shoppingbag .tit span {
  font-size: 26px;
  font-family: "ProximaNova-Light";
  vertical-align: middle;
}

.shoppingbag .tit span,
.shoppingbag .tit em {
  display: inline-block;
  vertical-align: middle;
  font-family: "ProximaNova-Regular";
}

em {
  font-style: normal;
}

.shoppingbag .tbl_product {
  margin-bottom: 60px;
}

table {
  border-top: 2px solid #171717;
  border-bottom: 1px solid #171717;
  table-layout: fixed;
  width: 100%;
  border-collapse: separate;
  border-spacing: 0;
}

.shoppingbag .tbl_product th {
  padding: 25px 0;
  border-bottom-color: #d9d9d9;
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

.shoppingbag .tbl_product .input_button.small {
  width: 17px;
}

span.input_button.small {
  margin-right: 0;
}

span.input_button {
  display: inline-block;
  position: relative;
  margin-right: 50px;
}

span.input_button.square input[type="checkbox"] {
  width: 20px;
  height: 20px;
}

span.input_button input[type="radio"],
span.input_button input[type="checkbox"] {
  position: absolute;
  top: 0;
  left: 0;
  width: 25px;
  height: 25px;
  z-index: 1;
  opacity: 0;
}

.shoppingbag .tbl_product .input_button.small input[type="checkbox"] + label {
  padding: 0;
}

span.input_button.small input[type="radio"] + label,
span.input_button.small input[type="checkbox"] + label {
  padding-left: 24px;
  color: #666;
  font-family: "ProximaNova-Light", "Noto Sans KR";
  line-height: 17px;
}

span.input_button input[type="radio"] + label,
span.input_button input[type="checkbox"] + label {
  display: block;
  color: #000;
  font-size: 14px;
  font-family: "ProximaNova-Regular", "Noto Sans KR";
  line-height: 25px;
  padding-left: 35px;
  position: relative;
}

span.input_button.square input[type="checkbox"]:checked + label::before {
  background-image: url(//static.wconcept.co.kr/web/images/common/svg/ico_checkbox_square_s_20.svg);
  background-position: 0 0;
}

span.input_button.square input[type="checkbox"] + label::before {
  width: 20px;
  height: 20px;
  background-image: url(//static.wconcept.co.kr/web/images/common/svg/ico_checkbox_square_20.svg);
  background-position: 0 0;
}

span.input_button input[type="radio"] + label:before,
span.input_button input[type="checkbox"] + label:before {
  background: url(//static.wconcept.co.kr/web/images/common/spr-input.png) 0 0
    no-repeat;
  content: "";
  display: block;
  position: absolute;
  top: 0;
  left: 0;
}

.shoppingbag .bx_btn button:first-child {
  margin-right: 10px;
}

a.btn,
.shoppingbag button.btn,
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

.shoppingbag .bx_btn button {
  height: 50px;
  line-height: 50px;
}

a.btn.gray,
button.btn.gray,
input.btn.gray,
span.btn.gray {
  border-color: #7d7d7d;
  background-color: #7d7d7d;
  color: #fff;
}

.shoppingbag .tbl_product tr.calculate_sub td {
  padding: 0 0 0 0;
  border-width: 0 0 0 0;
}

.shoppingbag .tbl_product .calculate_item {
  display: -webkit-flex;
  display: flex;
  justify-content: flex-end;
  padding: 14px 44px;
  font-size: 16px;
  line-height: 24px;
  color: #000;
  gap: 20px;
  background: #f9f9f9;
}

table tbody td p {
  display: inline-block;
}

.shoppingbag .tbl_product .calculate_item .calculate_aside strong {
  font-family: "ProximaNova-Semibold", "Noto Sans KR";
  font-weight: 500;
}

.shoppingbag .tbl_product + .tbl_under_box {
  margin-top: -10px;
}

.shoppingbag .tbl_under_box {
  overflow: hidden;
  display: -webkit-flex;
  display: -ms-flex;
  display: flex;
  justify-content: space-between;
  align-items: flex-start;
}

.shoppingbag .bx_btn button:first-child {
  margin-right: 10px;
}

.shoppingbag .bx_btn button {
  height: 50px;
  line-height: 50px;
}

a.btn.gray,
button.btn.gray,
input.btn.gray,
span.btn.gray {
  border-color: #7d7d7d;
  background-color: #7d7d7d;
  color: #fff;
}

.shoppingbag .bx_btn:after {
  display: block;
  clear: both;
  content: "";
}

.shoppingbag .tbl_under_box .shoppingbag_count_limit_notify {
  width: 460px;
}

.shoppingbag_count_limit_notify .unorder_list li {
  position: relative;
  padding-left: 0.563rem;
  font-size: 13px;
  color: #7b7b7b;
  line-height: 19px;
  word-break: keep-all;
}

.shoppingbag_count_limit_notify .unorder_list li:before {
  content: "";
  display: inline-block;
  width: 3px;
  height: 3px;
  border-radius: 1.5px;
  background: #7b7b7b;
  position: absolute;
  left: 0;
  top: 8px;
}

.shoppingbag .bag-wrap .payment {
  float: right;
  width: 300px;
  margin-top: 49px;
  padding: 19px;
  border: 1px solid #e9e9e9;
}

.shoppingbag .bx_total li:first-child {
  margin-top: 0;
}

.shoppingbag .bx_total li {
  margin: 9px 0 0 0;
  color: #333;
  overflow: hidden;
  display: -webkit-flex;
  display: flex;
  justify-content: space-between;
  align-items: center;
  flex-wrap: wrap;
}

.shoppingbag .bx_total li:not(.promotion) strong {
  width: calc(100% - 114px);
}

.shoppingbag .bx_total li strong {
  font-size: 14px;
  text-align: left;
}

.shoppingbag .bx_total li strong,
.shoppingbag .bx_total li p {
  display: inline-block;
}

.shoppingbag .bx_total li * {
  font-family: "ProximaNova-Regular", "Noto Sans KR";
}

strong,
b {
  font-weight: normal;
}

.shoppingbag .bx_total li p {
  width: 114px;
  text-align: right;
  font-family: "ProximaNova-Regular";
  font-size: 14px;
}

.shoppingbag .bx_total li p:not(.extra_fee_ctns) em {
  margin-right: 2px;
  font-size: 18px;
}

.shoppingbag .bx_total li p:not(.extra_fee_ctns) > span {
  color: #000;
  font-size: 12px;
  vertical-align: 2px;
}

.shoppingbag .bx_total li.total {
  margin-top: 20px;
  padding-top: 20px;
  border-top: 1px solid #d9d9d9;
  width: 100%;
}

.shoppingbag .bx_total li.total strong {
  color: #fa5500;
}

.shoppingbag .bx_total li.total p:not(.extra_fee_ctns) {
  color: #fa5500;
}

.shoppingbag .bx_total li.total p:not(.extra_fee_ctns) em {
  font-size: 20px;
  font-family: "ProximaNova-Semibold", "Noto Sans KR";
}

.shoppingbag .bx_total li.point {
  margin-top: 10px;
  color: #666;
  overflow: visible;
}

.shoppingbag .bx_total li.point strong {
  font-size: 12px;
}

.shoppingbag .bx_total li.point p {
  font-size: 14px;
  font-family: "ProximaNova-Light";
}

.shoppingbag .bx_total li.point p em {
  font-size: 14px;
}

.shoppingbag .bx_total li.point:after {
  display: block;
  clear: both;
  content: "";
}

.shoppingbag .bag_wrap .payment:not(.final) .btn_order {
  height: 60px;
  line-height: 60px;
}

.shoppingbag .btn_order {
  width: 100%;
  height: 70px;
  background-color: #000;
  font-family: "ProximaNova-Regular", "Noto Sans KR";
  font-size: 16px;
  line-height: 70px;
  color: #fff;
  margin-top: 20px;
}

.shoppingbag .btn_gift_selected {
  width: 100%;
  height: 44px;
  line-height: 42px;
  font-size: 14px;
  color: #000;
  background: #fff;
  border: 1px solid #000;
  margin-top: 10px;
}

.shoppingbag .bag-wrap:after {
  display: block;
  clear: both;
  content: "";
}
.shoppingbag .tbl_product td.noCart {
  padding: 14px 0;
  text-align: center;
  height: 100px;
  border-top-width: 0;
  border-bottom: 1px solid #f9f9f9;
}
table tbody td p {
  display: inline-block;
}

@keyframes ldio-f4nnk2ltl0v {
  0% {
    opacity: 1;
  }
  100% {
    opacity: 0;
  }
}
.ldio-f4nnk2ltl0v div {
  position: fixed;
  top: 30%;
  left: 50%;
  transform: translate(-50%, -50%);
  z-index: 999; /* 다른 요소 위에 표시하기 위한 z-index 값 */
  animation: ldio-f4nnk2ltl0v linear 1s infinite;
  background: #fe718d;
  width: 18.240000000000002px;
  height: 36.480000000000004px;
  border-radius: 9.120000000000001px / 18.240000000000002px;
  transform-origin: 9.120000000000001px 79.04px;
}
.ldio-f4nnk2ltl0v div:nth-child(1) {
  transform: rotate(0deg);
  animation-delay: -0.9166666666666666s;
  background: #fe718d;
}
.ldio-f4nnk2ltl0v div:nth-child(2) {
  transform: rotate(30deg);
  animation-delay: -0.8333333333333334s;
  background: #f47e60;
}
.ldio-f4nnk2ltl0v div:nth-child(3) {
  transform: rotate(60deg);
  animation-delay: -0.75s;
  background: #f8b26a;
}
.ldio-f4nnk2ltl0v div:nth-child(4) {
  transform: rotate(90deg);
  animation-delay: -0.6666666666666666s;
  background: #abbd81;
}
.ldio-f4nnk2ltl0v div:nth-child(5) {
  transform: rotate(120deg);
  animation-delay: -0.5833333333333334s;
  background: #849b87;
}
.ldio-f4nnk2ltl0v div:nth-child(6) {
  transform: rotate(150deg);
  animation-delay: -0.5s;
  background: #6492ac;
}
.ldio-f4nnk2ltl0v div:nth-child(7) {
  transform: rotate(180deg);
  animation-delay: -0.4166666666666667s;
  background: #637cb5;
}
.ldio-f4nnk2ltl0v div:nth-child(8) {
  transform: rotate(210deg);
  animation-delay: -0.3333333333333333s;
  background: #6a63b6;
}
.ldio-f4nnk2ltl0v div:nth-child(9) {
  transform: rotate(240deg);
  animation-delay: -0.25s;
  background: #fe718d;
}
.ldio-f4nnk2ltl0v div:nth-child(10) {
  transform: rotate(270deg);
  animation-delay: -0.16666666666666666s;
  background: #f47e60;
}
.ldio-f4nnk2ltl0v div:nth-child(11) {
  transform: rotate(300deg);
  animation-delay: -0.08333333333333333s;
  background: #f8b26a;
}
.ldio-f4nnk2ltl0v div:nth-child(12) {
  transform: rotate(330deg);
  animation-delay: 0s;
  background: #abbd81;
}
.loadingio-spinner-spinner-pz89b3jiaad {
  width: 304px;
  height: 304px;
  display: inline-block;
  overflow: hidden;
  background: #ffffff;
}
.ldio-f4nnk2ltl0v div {
  box-sizing: content-box;
}
</style>
