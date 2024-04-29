<template>
  <td class="chkbox">
    <span class="input_button square">
      <input
        type="checkbox"
        name="cartIdx"
        :id="cart.cartIdx"
        v-model="checked"
        @click="selectCart()"
      />
      <label for="checkbox2">&nbsp;</label>
    </span>
  </td>

  <td class="product">
    <div class="item_aside">
      <button type="button" class="btn_like_pick">
        <span>마이하트</span>
      </button>
      <a :href="'/productdetail/' + cart.productIdx">
        <div class="img">
          <img :src="cart.productImage" />
        </div>
      </a>
    </div>
    <div class="item_info" :id="cart.productIdx">
      <a :href="'/productdetail/' + cart.productIdx">
        <p class="brand">{{ cart.brandName }}</p>
        <p class="product_name multiline">{{ cart.productName }}</p>
        <p class="option">옵션 : 미설정</p>
      </a>
    </div>
  </td>

  <td>
    <div class="each">
      <button type="button" name="button" class="btn_up btn_plus" disabled>
        수량올림
      </button>
      <input
        type="text"
        id="orderstyle_191742674"
        value="1"
        onchange="WCKBasket.ChangeStock(this, '1', '194','191742674');"
        data-rowcnt="0"
        readonly="readonly"
      />
      <button type="button" name="button" class="btn_down btn_minus">
        수량내림
      </button>
    </div>
    <button
      type="button"
      name="button"
      id="qtybutton_191742674"
      style="color: #d9d9d9"
      class="micro_btn btn_each"
    >
      변경
    </button>
  </td>

  <td>
    <div class="price_wrap" data-subtotal="totalBox_300323316417">
      <em>{{ cart.price.toLocaleString() }}</em>
      <strong>{{
        isCouponApplied
          ? finalPrice.toLocaleString()
          : cart.salePrice.toLocaleString()
      }}</strong
      >원
      <p class="last_price_tip" v-show="isCouponApplied">
        {{ discountedPrice.toLocaleString() }}원 할인되었어요!
      </p>
    </div>
  </td>

  <td>
    <div class="btn_wrap" id="couponBtn_0">
      <button
        type="button"
        class="btn_apply_coupon open-layer open-pop_apply_coupon"
        @click="openCouponModal()"
      >
        <span>쿠폰적용</span>
      </button>
    </div>
  </td>
  <td class="td_delete">
    <div class="btn_wrap_delete">
      <button
        type="button"
        class="btn btn_now_delete"
        @click="cartStore.deleteCartItem(cart.cartIdx)"
      >
        삭제
      </button>
    </div>
  </td>
  <!-------쿠폰 적용 모달 창-------------------->
  <div v-show="applyCoupon" class="layer pop_apply_coupon active">
    <div class="layer_cont" style="margin: -387px 0px 0px -300px">
      <h2 class="layer_tit">쿠폰 적용하기</h2>
      <div
        class="custom_scroll ps-container ps-theme-default"
        data-ps-id="c1d33995-1957-8e3a-9c69-36e957beec9a"
      >
        <div class="coupon_list_radio">
          <h3><strong>할인쿠폰</strong></h3>
          <ul>
            <li v-for="coupon in couponStore.coupons" :key="coupon.couponIdx">
              <span class="input_button radio mid">
                <input
                  type="radio"
                  name="couponRadio1"
                  value="36863689"
                  @click="updatePrice(coupon)"
                />
                <label for="couponRadio1_36863689">
                  <em
                    ><strong>{{ coupon.couponDiscountRate }}%</strong></em
                  >
                  <div>
                    <p class="tit">
                      <strong>{{ coupon.couponName }}</strong>
                    </p>
                    <p class="desc">
                      <span class="date"
                        ><strong
                          >{{ coupon.couponExpirationDate }}일 남음</strong
                        ></span
                      >
                      <!-- <em class="used" v-show="isCouponUse">사용중</em> -->
                    </p>
                  </div>
                </label>
              </span>
            </li>
          </ul>
        </div>
        <!-- <div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px">
            <div class="ps-scrollbar-x" tabindex="0" style="left: 0px; width: 0px"></div>
          </div>
          <div class="ps-scrollbar-y-rail" style="top: 0px; right: 0px">
            <div class="ps-scrollbar-y" tabindex="0" style="top: 0px; height: 0px"></div>
          </div> -->
      </div>
      <button type="button" class="btn_close" @click="closeCouponModal()">
        닫기
      </button>
      <div class="btn_wrap">
        <ul class="discount_box">
          <input type="hidden" id="cp_hdn_optidx" value="0" />
          <input type="hidden" id="cp_hdn_orderamt" value="125100" />
          <li>
            <h4>판매금액</h4>
            <p>
              <span id="cp_orderamt">{{
                cart.salePrice.toLocaleString()
              }}</span>
              원
            </p>
          </li>
          <li>
            <h4>할인금액</h4>
            <p>
              <span id="cp_discountamt">{{
                discountedPrice.toLocaleString()
              }}</span>
              원
            </p>
          </li>
          <li>
            <h4>쿠폰적용금액</h4>
            <p>
              <span id="cp_totalamt">{{ finalPrice.toLocaleString() }}</span>
              원
            </p>
          </li>
        </ul>
        <button
          type="button"
          class="btn_apply_discount"
          @click="applyFinalPrice()"
        >
          할인 적용
        </button>
      </div>
    </div>
    <span></span>
  </div>
</template>

<script>
import { mapStores } from "pinia";
import { useCartStore } from "../stores/useCartStore";
import { useCouponStore } from "../stores/useCouponStore";

export default {
  name: "CartComponent",
  props: ["cart", "coupons"],
  data() {
    return {
      checked: true,
      applyCoupon: false,
      selectedCoupon: null,
      discountedPrice: 0,
      finalPrice: 0,
      isCouponApplied: false,
    };
  },
  computed: {
    ...mapStores(useCartStore, useCouponStore),
  },

  methods: {
    // 쿠폰 선택 기능
    updatePrice(selectedCoupon) {
      this.selectedCoupon = selectedCoupon;

      if (this.selectedCoupon) {
        const discountRate = parseFloat(this.selectedCoupon.couponDiscountRate);
        this.discountedPrice = Math.round(
          (this.cart.salePrice * discountRate) / 100
        );
        this.finalPrice = this.cart.salePrice - this.discountedPrice;
      } else {
        this.discountedPrice = 0;
      }
    },

    // 쿠폰 적용 기능
    async applyFinalPrice() {
      if (this.selectedCoupon == null) {
        alert("적용할 쿠폰이 없습니다.");
      } else {
        const success = await this.couponStore.applyCoupon(
          this.selectedCoupon.couponIdx
        );

        if (success) {
          this.isCouponApplied = true;

          this.applyCoupon = false;

          this.cartStore.totalPrice =
            this.cartStore.totalPrice - this.discountedPrice;

          this.cartStore.totalPoint = Math.floor(
            this.cartStore.totalPrice / 100
          );

          this.couponStore.isCouponApply = true;
          this.couponStore.discountPrice += this.discountedPrice;

          this.cartStore.couponIdxList.push(this.selectedCoupon.couponIdx);
          console.log(this.cartStore.couponIdxList);

          this.cartStore.productCouponMap[this.cart.productIdx] =
            this.selectedCoupon.couponIdx;

          console.log(this.cartStore.productCouponMap);

          this.couponStore.couponStatus = false;
        }
      }
    },

    // 장바구니 체크 기능
    selectCart() {
      console.log(this.checked);
      this.checked = !this.checked;
      if (this.checked) {
        if (!this.cartStore.productIdxList.includes(this.cart.productIdx)) {
          this.cartStore.productIdxList.push(this.cart.productIdx);
          this.cartStore.ordersCartIdxList.push(this.cart.cartIdx);
          this.cartStore.productNameList.push(this.cart.productName);
        }
        // console.log(this.cartStore.productIdxList);
        // console.log(this.cartStore.ordersCartIdxList);
        // console.log(this.cartStore.productNameList);

        this.cartStore.updateTotalPrice(
          this.cartStore.totalPrice + this.cart.salePrice
        );
      } else {
        this.cartStore.isChecked = false;
        const indexToRemove = this.cartStore.productIdxList.indexOf(
          this.cart.productIdx
        );

        const indexToCartRemove = this.cartStore.ordersCartIdxList.indexOf(
          this.cart.cartIdx
        );

        const indexPrdocutNameRemove = this.cartStore.productNameList.indexOf(
          this.cart.productName
        );

        if (indexToRemove !== -1) {
          this.cartStore.productIdxList.splice(indexToRemove, 1);
          this.cartStore.ordersCartIdxList.splice(indexToCartRemove, 1);
          this.cartStore.productNameList.splice(indexPrdocutNameRemove, 1);
        }

        // console.log(this.cartStore.productIdxList);
        // console.log(this.cartStore.ordersCartIdxList);
        // console.log(this.cartStore.productNameList);
        this.cartStore.updateTotalPrice(
          this.cartStore.totalPrice - this.cart.salePrice          
        );
      }
    },
    // 모달창 띄우는 메서드
    openCouponModal() {
      this.couponStore.getCoupon();
      this.applyCoupon = true;
    },
    closeCouponModal() {
      this.applyCoupon = false;
    },
  },
};
</script>

<style scoped>
.shoppingbag .tbl_product td.chkbox {
  vertical-align: top;
  padding: 20px 0;
}

.shoppingbag .tbl_product td {
  padding: 14px 0;
  text-align: center;
  height: auto;
  border-top-width: 0;
  border-bottom: 1px solid #f9f9f9;
}

.shoppingbag .tbl_product td .btn_wrap {
  padding: -3px 0 0 20px;
}

table.cols tbody tr td {
  font-family: "ProximaNova-Regular", "Noto Sans KR";
}

table tbody tr:first-child th,
table tbody tr:first-child td {
  border-color: transparent;
  border: none;
}

table tbody td {
  color: #000;
  font-size: 14px;
}

.shoppingbag .tbl_product td.product {
  padding: 20px 10px 20px 10px;
  display: flex;
  justify-content: flex-start;
  align-items: flex-start;
}

table.cols tbody tr td.product {
  text-align: left;
  font-family: "Noto Sans KR";
}

.shoppingbag .tbl_product td {
  height: auto;
  border-top-width: 0;
  border-bottom: 1px solid #f9f9f9;
}

.shoppingbag .tbl_product td.product .item_aside {
  width: 80px;
  position: relative;
  margin-right: 16px;
}

.shoppingbag .tbl_product td.product .btn_like_pick {
  display: inline-block;
  position: absolute;
  width: 30px;
  height: 30px;
  top: 0;
  right: 0;
  z-index: 5;
  text-indent: -9999px;
}

.shoppingbag .tbl_product td.product .btn_like_pick:after {
  content: "";
  display: inline-block;
  width: 14px;
  height: 13px;
  background: url(//static.wconcept.co.kr/web/images/common/svg/ico_cart_heart_white_off.svg)
    center center no-repeat;
  background-size: contain;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  position: absolute;
}

.shoppingbag .tbl_product td.product .img {
  width: 80px;
  height: 107px;
  display: block;
  position: relative;
}

.shoppingbag .tbl_product td.product .img img {
  display: block;
  width: 100%;
  margin-right: 0;
  float: none;
}

.shoppingbag .tbl_product td.product img {
  position: relative;
}

.shoppingbag .tbl_product td.product .item_info {
  width: calc(100% - 96px);
}

.shoppingbag .tbl_product td.product .item_info a {
  display: block;
}

.shoppingbag .tbl_product td.product p.brand {
  font-family: "ProximaNova-Semibold", "Noto Sans KR";
  font-weight: 500;
  line-height: 18px;
  margin-bottom: 4px;
}

table.cols tbody tr td.product p.brand {
  font-size: 14px;
  color: #000;
  width: auto;
  min-height: auto;
  margin: 0 0 6px 0;
}

table.cols tbody tr td.product p {
  display: block;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
  color: #808080;
  font-size: 12px;
  line-height: 1em;
}

.shoppingbag .tbl_product td.product p.product_name.multiline {
  overflow: hidden;
  text-overflow: ellipsis;
}

.shoppingbag .tbl_product td.product p.product_name {
  height: 32px;
  margin-bottom: 8px;
  white-space: normal;
  line-height: 16px;
  color: #000;
  font-size: 13px;
  word-break: keep-all;
  overflow-wrap: break-word;
}

.shoppingbag .tbl_product td.product p.option {
  font-family: "Noto Sans KR";
  font-size: 12px;
  line-height: 16px;
  color: #808080;
}

table.cols tbody tr td.product p.option {
  margin-bottom: 4px;
}

.shoppingbag .tbl_product .btn_opt {
  position: relative;
  display: inline-block;
  height: 16px;
  margin-top: 8px;
  padding: 0 12px 0 0;
  border-bottom: 1px solid #333;
  font-size: 12px;
  color: #333;
}

.shoppingbag .tbl_product .btn_opt:after {
  content: "";
  display: block;
  width: 8px;
  height: 8px;
  position: absolute;
  top: 50%;
  transform: translateY(-50%);
  right: 0;
  background: url(//static.wconcept.co.kr/web/images/common/svg/ico_cart_arrow_opt.svg)
    center center no-repeat;
  background-size: contain;
}

.shoppingbag .tbl_product .each {
  width: 45px;
  height: 25px;
  margin: 0 auto;
  border: 1px solid #dfdfdf;
  position: relative;
  padding-right: 18px;
}

.shoppingbag .tbl_product .each .btn_up {
  border-bottom: 1px solid #dfdfdf;
  background-position: 7px -46px;
}

.shoppingbag .tbl_product .each button {
  width: 19px;
  height: 11px;
  background: url(//static.wconcept.co.kr/web/images/common/spr-bag.png) 0 0
    no-repeat;
  text-indent: -999em;
  overflow: hidden;
  position: absolute;
  top: 0;
  right: 0;
}

.shoppingbag .tbl_product .each input,
.shoppingbag .tbl_product .each button {
  margin: 0;
  padding: 0;
  border: none;
  background-color: #fff;
  vertical-align: top;
  font-family: "ProximaNova-Regular";
}

.shoppingbag .tbl_product .each input {
  width: 23px;
  height: 23px;
  border-right: 1px solid #dfdfdf;
  font-size: 12px;
  text-align: center;
  line-height: normal;
}

.shoppingbag .tbl_product .each .btn_down {
  background-position: -43px -46px;
  top: auto;
  bottom: 0;
}

.shoppingbag .tbl_product .each:after {
  display: block;
  clear: both;
  content: "";
}

.shoppingbag .tbl_product .btn_each {
  min-width: 45px;
  margin-top: 6px;
}

.shoppingbag .tbl_product .micro_btn {
  height: 20px;
  padding: 0;
  border: 1px solid #c4c4c4;
  line-height: 20px;
  color: #333;
  font-size: 12px;
  text-align: center;
}

a.micro_btn,
button.micro_btn,
input.micro_btn,
span.micro_btn {
  display: inline-block;
  min-width: 60px;
  padding: 0 8px;
  height: 20px;
  line-height: 18px;
  font-family: "ProximaNova-Regular", "Noto Sans KR";
  font-size: 12px;
  color: #000;
  border: 1px solid #333;
  background-color: #fff;
  text-align: center;
}

.shoppingbag .tbl_product td .price_wrap {
  font-size: 14px;
  font-family: "ProximaNova-Regular", "Noto Sans KR";
  color: #000;
  line-height: 20px;
  position: relative;
}

.shoppingbag .tbl_product td .price_wrap em:not(.option) {
  font-size: 12px;
  color: #c4c4c4;
  text-decoration: line-through;
  line-height: 14px;
  margin-bottom: 2px;
}

.shoppingbag .tbl_product td .price_wrap em,
.shoppingbag .tbl_product td .price_wrap .option {
  display: block;
}

.shoppingbag .tbl_product td .price_wrap strong {
  font-size: 16px;
  font-family: "ProximaNova-Regular", "Noto Sans KR";
}

strong,
b {
  font-weight: normal;
}

.shoppingbag .tbl_product .btn_wrap {
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 4px;
}

.shoppingbag .tbl_product button.btn_now,
.shoppingbag .tbl_product button.btn_gift {
  border: 1px solid #c4c4c4;
  color: #333;
  line-height: 38px;
  height: 40px;
  min-width: 110px;
  text-align: center;
  padding: 0 0;
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

.shoppingbag .tbl_product button.btn_delete {
  width: 20px;
  height: 20px;
  position: relative;
  text-indent: -9999px;
}

.shoppingbag .tbl_product button.btn_delete:after {
  content: "";
  display: inline-block;
  width: 11px;
  height: 11px;
  background: url(//static.wconcept.co.kr/web/images/common/svg/ico_basket_delete_20.svg)
    0 0 no-repeat;
  background-size: contain;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
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

span.input_button.square input[type="checkbox"] {
  width: 20px;
  height: 20px;
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

span.input_button.square input[type="checkbox"] + label::before {
  width: 20px;
  height: 20px;
  background-image: url(//static.wconcept.co.kr/web/images/common/svg/ico_checkbox_square_20.svg);
  background-position: 0 0;
}

span.input_button.square input[type="checkbox"]:checked + label::before {
  background-image: url(//static.wconcept.co.kr/web/images/common/svg/ico_checkbox_square_s_20.svg);
  background-position: 0 0;
}

.shoppingbag .tbl_product .btn_apply_coupon,
.shoppingbag .tbl_product .btn_get_coupon {
  min-width: 69px;
  font-size: 11px;
  line-height: 22px;
  text-align: center;
  height: 41px;
  border: 1px solid #000;
}
.shoppingbag .tbl_product .btn_apply_coupon {
  background-color: #000;
  color: #fff;
}

.shoppingbag .tbl_product button.btn_now_delete {
  border: 1px solid #c4c4c4;
  color: #333;
  line-height: 38px;
  height: 40px;
  min-width: 80px;
  text-align: center;
  padding: 0 10px;
}

.shoppingbag .tbl_product .btn_wrap_delete {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  gap: 4px;
}

.shoppingbag .tbl_product td .btn_wrap_delete {
  padding: 0 10px 0 35px;
}

.shoppingbag .tbl_product td.td_delete {
  padding: 14px 0;
  text-align: center;
  height: auto;
  border-top-width: 0;
  border-bottom: 1px solid #f9f9f9;
}

.shoppingbag .tbl_product td.td_delete {
  height: auto;
  border-top-width: 0;
  border-bottom: 1px solid #f9f9f9;
}

.layer.active,
.layer_ov_ly.active {
  display: block;
}

.layer *,
.layer_ov_ly * {
  font-family: "ProximaNova-Regular", "Apple SD Gothic Neo", "Noto Sans KR",
    "Malgun Gothic", "맑은 고딕", sans-serif;
}

.layer_cont {
  position: fixed;
  top: 50%;
  left: 50%;
  z-index: 110;
  min-width: 480px;
  overflow: hidden;
  padding: 40px 40px 50px;
  border: 1px solid #000;
  background: #fff;
}

.layer.pop_apply_coupon .layer_cont {
  width: 600px;
  padding: 40px 0 98px;
}

.layer.pop_coupon .layer_cont,
.layer.pop_coupon_choice .layer_cont,
.layer.pop_apply_coupon .layer_cont {
  padding-left: 0;
  padding-right: 0;
}

.layer_tit {
  color: #333;
  font-family: "Noto Sans KR";
  font-size: 24px;
  margin-bottom: 24px;
}

.layer.pop_coupon .layer_tit,
.layer.pop_cupon_down .layer_tit,
.layer.pop_coupon_choice .layer_tit,
.layer.pop_apply_coupon .layer_tit {
  margin: 0 40px;
  padding-bottom: 20px;
  border-bottom: 2px solid #000;
  line-height: 36px;
}

.ps-container {
  -ms-touch-action: auto;
  touch-action: auto;
  overflow: hidden !important;
  -ms-overflow-style: none;
  position: relative;
}

.layer.pop_apply_coupon .custom_scroll {
  height: 546px;
  padding-bottom: 30px !important;
}

.layer.pop_coupon .custom_scroll,
.layer.pop_coupon_choice .custom_scroll,
.layer.pop_apply_coupon .custom_scroll {
  padding: 0 40px;
}

.layer.pop_apply_coupon .coupon_list_radio:first-child {
  margin-top: 30px;
}

.layer.pop_apply_coupon .coupon_list_radio h3 {
  font-family: "NotoSansKR-Medium";
  font-size: 18px;
  line-height: 26px;
  margin-bottom: 20px;
}

span.input_button {
  display: inline-block;
  position: relative;
  margin-right: 50px;
}

.layer.pop_apply_coupon .coupon_list_radio li .input_button {
  width: 100%;
  margin: 0;
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

span.input_button.mid input[type="radio"],
span.input_button.mid input[type="checkbox"] {
  width: 20px;
  height: 20px;
}

.layer.pop_apply_coupon .coupon_list_radio li .input_button input,
.layer.pop_apply_coupon
  .coupon_list_radio
  li
  .input_button
  input
  + label:before {
  top: 0;
  margin-top: 0;
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

span.input_button.mid input[type="radio"] + label,
span.input_button.mid input[type="checkbox"] + label {
  padding-left: 29px;
  line-height: 20px;
}

.layer.pop_apply_coupon .coupon_list_radio li .input_button label {
  padding-left: 120px;
}

span.input_button input[type="radio"] + label:before,
span.input_button input[type="checkbox"] + label:before {
  background: url(//static.wconcept.co.kr/web/images/common/spr-input.png) 0 0
    no-repeat;
  content: "";
  display: block;
  width: 25px;
  height: 25px;
  position: absolute;
  top: 0;
  left: 0;
}

span.input_button.mid input[type="radio"] + label:before,
span.input_button.mid input[type="checkbox"] + label:before {
  width: 20px;
  height: 20px;
}

span.input_button.mid input[type="radio"] + label:before {
  background-image: url(//static.wconcept.co.kr/web/images/common/svg/ico_radio_n_20.svg);
}

span.input_button.mid input[type="radio"]:checked + label:before {
  background-image: url(//static.wconcept.co.kr/web/images/common/svg/ico_radio_c_20.svg);
}

.layer.pop_apply_coupon .coupon_list_radio li .input_button input,
.layer.pop_apply_coupon
  .coupon_list_radio
  li
  .input_button
  input
  + label:before {
  top: 0;
  margin-top: 0;
}

.layer.pop_apply_coupon .coupon_list_radio li .input_button label > em {
  font-family: "ProximaNova-Medium";
  font-size: 16px;
  color: #fa5500;
  position: absolute;
  top: 0;
  left: 32px;
}

.layer.pop_apply_coupon .coupon_list_radio li .input_button label .tit {
  font-size: 14px;
  line-height: 20px;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  overflow: hidden;
  overflow-wrap: break-word;
}

.layer.pop_apply_coupon .coupon_list_radio li .input_button label .desc {
  display: -webkit-flex;
  display: flex;
  font-size: 11px;
  line-height: 14px;
  color: #777;
}

.layer.pop_apply_coupon .coupon_list_radio li .input_button label .desc * {
  margin-top: 3px;
}

.layer.pop_apply_coupon .coupon_list_radio li ~ li {
  margin-top: 16px;
}

.layer_cont .btn_close,
.layer_cont .pop_in_pop_close {
  overflow: hidden;
  position: absolute;
  top: 25px;
  right: 25px;
  width: 53px;
  height: 53px;
  padding: 15px;
  line-height: 99em;
  vertical-align: top;
}

.layer_cont .btn_close:before,
.layer_cont .pop_in_pop_close:before {
  display: block;
  width: 23px;
  height: 23px;
  background: url(//static.wconcept.co.kr/web/images/common/layer_close_23.png)
    no-repeat;
  background-size: 100%;
  content: "";
}

.layer.pop_apply_coupon .btn_wrap {
  height: 100px;
  display: flex;
  -webkit-align-items: center;
  align-items: center;
  -webkit-justify-content: space-between;
  justify-content: space-between;
  position: absolute;
  left: 0;
  right: 0;
  bottom: 0;
  background: #fff;
  padding: 0 40px;
}

.layer.pop_apply_coupon .btn_wrap:before {
  content: "";
  display: block;
  border-top: 1px solid #000;
  position: absolute;
  top: 0;
  left: 40px;
  right: 40px;
}

.layer.pop_apply_coupon .discount_box {
  width: calc(100% - 160px);
  display: -webkit-flex;
  display: flex;
  -webkit-justify-content: center;
  justify-content: center;
  align-items: center;
}

.layer.pop_apply_coupon .discount_box li {
  position: relative;
  text-align: center;
}

.layer.pop_apply_coupon .discount_box li h4 {
  font-size: 12px;
  line-height: 15px;
  margin-bottom: 5px;
  color: #aaa;
}

.layer.pop_apply_coupon .discount_box li p {
  font-family: "ProximaNova-Semibold", "Noto Sans KR", sans-serif;
  font-weight: 500;
  font-size: 12px;
  white-space: nowrap;
  line-height: 16px;
}

.layer.pop_apply_coupon .discount_box li p span {
  font-family: "ProximaNova-Semibold";
  font-size: 16px;
}

.layer.pop_apply_coupon .discount_box li:nth-of-type(2) {
  padding: 0 12px;
  margin: 0 12px;
}

.layer.pop_apply_coupon .discount_box li:nth-of-type(2):before,
.layer.pop_apply_coupon .discount_box li:nth-of-type(2):after {
  content: "";
  display: block;
  position: absolute;
  top: 50%;
  width: 14px;
  height: 14px;
  background-repeat: no-repeat;
  background-size: 100%;
  margin-top: -7px;
}

.layer.pop_apply_coupon .discount_box li:nth-of-type(2):before {
  left: 0;
  background-image: url(//static.wconcept.co.kr/mobile/images/common/svg/ico_minus_14.svg);
}

.layer.pop_apply_coupon .discount_box li:nth-of-type(2) * {
  color: #fa5500;
}

.layer.pop_apply_coupon .discount_box li:nth-of-type(2) > p {
  padding: 0 12px;
  min-width: 60px;
}

.layer.pop_apply_coupon .discount_box li:nth-of-type(2):after {
  right: 0;
  background-image: url(//static.wconcept.co.kr/mobile/images/common/svg/ico_equal_14.svg);
}

.layer.pop_apply_coupon .btn_apply_discount {
  width: 140px;
  height: 50px;
  font-size: 14px;
  background: #171717;
  color: #fff;
}

.layer > span,
.layer_ov_ly > span {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  z-index: 101;
  background: #0e0e0e;
  opacity: 0.4;
  filter: alpha(opacity=40);
  content: "";
  display: block;
}

.last_price_tip {
  display: -webkit-flex;
  display: flex;
  justify-content: center;
  flex-wrap: nowrap;
  white-space: nowrap;
  background-color: #fa5500;
  color: #fff;
  line-height: 24px;
  font-size: 12px;
  padding: 0 10px;
  border-radius: 5px;
  margin: 10px auto 0;
  position: absolute;
  left: 50%;
  transform: translateX(-50%);
}

.last_price_tip:before {
  content: "";
  background: url(//static.wconcept.co.kr/mobile/images/common/svg/ico_orange_tip.svg)
    0 0 no-repeat;
  background-size: cover;
  width: 7px;
  height: 7px;
  display: inline-block;
  position: absolute;
  margin-top: -6px;
}

.layer.pop_apply_coupon .coupon_list_radio li .input_button label .desc * ~ * {
  position: relative;
  padding-left: 8px;
  margin-left: 6px;
}

.layer.pop_apply_coupon .coupon_list_radio li .input_button label .desc em {
  color: #fa5500;
}
</style>
