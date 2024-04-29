<template>
  <div class="inner_shape_flex" :id="coupons.couponIdx">
    <h4 class="cpn_item_str">
      <strong>{{ coupons.couponDiscountRate }}%</strong>
    </h4>
    <span class="cpn_use_type">PC</span>
  </div>
  <div class="inner_shape_ctns">
    <p class="cpn_item_name">
      <strong>{{ coupons.couponName }}</strong>
    </p>
  </div>
  <div class="inner_shape_aside">
    <button type="button" class="btn_coupon_prod" disabled="disabled">
      <span>적용대상제한없음</span>
    </button>
    <span class="cpn_item_period"
      ><strong>{{ coupons.couponExpirationDate }}일 남음</strong></span
    >
  </div>
</template>

<script>
import { mapStores } from "pinia";
import { useCouponStore } from "../stores/useCouponStore";
export default {
  name: "CouponComponent",
  props: ["coupons"],
  computed: {
    ...mapStores(useCouponStore),
  },
  methods: {
    submitProductIdx() {
      console.log(this.orders.productIdx);
      console.log("click");
      this.reviewStore.productIdx = this.orders.productIdx;
      this.$router.push({ path: "/UserReview" });
      //window.location.href = "/UserReview";
    },
  },
};
</script>

<style scoped>
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
</style>
