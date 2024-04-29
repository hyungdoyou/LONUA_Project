import { defineStore } from "pinia";
import axios from "axios";

const backend = 'http://www.lonuamall.kro.kr/api';
// const backend = "http://localhost:8080";
const storedToken = sessionStorage.getItem("token");

export const useCouponStore = defineStore("coupon", {
  state: () => ({
    coupons: [],
    couponStatus: false,
    isCouponApply: false,
    discountPrice: 0,
  }),
  actions: {
    async getCoupon() {
      try {
        const response = await axios.get(backend + "/coupon/list", {
          headers: {
            Authorization: `Bearer ${storedToken}`,
          },
        });

        this.coupons = response.data.result;
        this.discoutRate = response.data.result.couponDiscountRate;
      } catch (error) {
        console.error(error);
      }
    },

    async applyCoupon(couponIdx) {
      try {
        const response = await axios.get(
          backend + "/coupon/apply/" + couponIdx,
          {
            headers: {
              Authorization: `Bearer ${storedToken}`,
            },
          }
        );

        if (response.data.isSuccess === true) {
          return true;
        } else {
          alert("이미 적용 또는 사용한 쿠폰입니다.");
          return false;
        }
      } catch (error) {
        return false;
      }
    },
  },
});
