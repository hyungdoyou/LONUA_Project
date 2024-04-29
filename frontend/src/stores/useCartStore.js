import { defineStore } from "pinia";
import axios from "axios";

const backend = "http://www.lonuamall.kro.kr/api";
// const backend = "http://localhost:8080";
const storedToken = sessionStorage.getItem("token");

export const useCartStore = defineStore("cart", {
  state: () => ({
    cartList: [],
    totalPrice: 0,
    totalPoint: 0,
    cartCount: 0,
    isChecked: true,
    productIdxList: [],
    orderCount: 0,
    discountPrice: 0,
    couponIdxList: [],
    ordersCartIdxList: [],
    productCouponMap: {},
    isCartExist: true,
    isLoading: false,
    productNameList: [],
    ordersProductName: "",
    userMileage: 0
  }),
  actions: {
    updateTotalPrice(updatePrice) {
      this.totalPrice = updatePrice;
      this.totalPoint = Math.floor(this.totalPrice / 100);
      this.cartCount = this.productIdxList.length;
    },
    // 장바구니 목록 조회
    async getCartList(page, size) {
      let response = await axios.get(backend + `/cart/list/${page}/${size}`, {
        headers: {
          Authorization: `Bearer ${storedToken}`,
          "Content-Type": "application/json",
        },
      });
      this.cartList = response.data.result;
      if (this.cartList.length !== 0) {
        this.isCartExist = false;
      }

      this.productIdxList = this.cartList.map((cart) => cart.productIdx);
      this.productNameList = this.cartList.map((cart) => cart.productName);
      this.ordersCartIdxList = this.cartList.map((cart) => cart.cartIdx);
      this.cartList.forEach((cart) => {
        const productIdx = cart.productIdx;
        this.productCouponMap[productIdx] = 0;
      });

      // console.log(this.productCouponMap);
      // console.log(this.productIdxList);
      // console.log(this.ordersCartIdxList);
      // console.log(this.productNameList);

      this.calculateTotal();
    },
    calculateTotal() {
      let totalAmount = 0;
      for (let i = 0; i < this.cartList.length; i++) {
        totalAmount += this.cartList[i].salePrice;
      }

      this.totalPrice = totalAmount;

      this.totalPoint = Math.floor(this.totalPrice / 100).toLocaleString();
      this.cartCount = this.cartList.length;
    },

    // 장바구니에 담긴 상품 1개 삭제
    async deleteCartItem(cartIdx) {
      try {
        let response = await axios.delete(backend + `/cart/delete/${cartIdx}`, {
          headers: {
            Authorization: `Bearer ${storedToken}`,
            "Content-Type": "application/json",
          },
        });

        if (response.data.isSuccess === true) {
          alert("장바구니에서 상품이 삭제되었습니다.");
          window.location.href = "/UserCart";
        }
      } catch (e) {
        console.log(e);
        alert("잘못된 요청입니다.");
      }
    },

    // 장바구니 전체 삭제
    async deleteAllCartItem() {
      try {
        let response = await axios.delete(backend + "/cart/deleteAll", {
          headers: {
            Authorization: `Bearer ${storedToken}`,
            "Content-Type": "application/json",
          },
        });

        if (response.data.isSuccess === true) {
          alert("장바구니에 담긴 상품을 모두 삭제하였습니다.");
          window.location.href = "/UserCart";
        }
      } catch (e) {
        console.log(e);
        alert("잘못된 요청입니다.");
      }
    },

    // 장바구니 선택상품 주문
    createOrderNum() {
      const date = new Date();
      const year = date.getFullYear();
      const month = String(date.getMonth() + 1).padStart(2, "0");
      const day = String(date.getDate()).padStart(2, "0");

      let orderNum = year + month + day;
      for (let i = 0; i < 10; i++) {
        orderNum += Math.floor(Math.random() * 8);
      }
      return orderNum;
    },

    submitOrder() {
      console.log(this.totalPoint);
      this.isLoading = true;

      if (this.productNameList.length > 1) {
        console.log(this.productNameList.length);
        this.ordersProductName =
          this.productNameList[0] + " 등 " + this.productNameList.length + "건";
      } else {
        this.ordersProductName = this.productNameList[0];
      }

      // 아임포트 초기화
      const IMP = window.IMP;
      if (IMP) {
        IMP.init("imp74685073");
      } else {
        console.error("IMP 객체를 찾을 수 없습니다.");
        return;
      }

      this.totalPoint = this.totalPoint.toString();
      if (this.totalPoint.includes(",")) {
        this.userMileage = parseInt(this.totalPoint.replace(",", ""));
      } else {
        this.userMileage = parseInt(this.totalPoint);
      }

      // 주문 정보 생성
      const orderData = {
        amount: this.totalPrice,
        impUid: "",
        merchantUid: this.createOrderNum(),
        productName: this.ordersProductName,
        payMethod: "kakoPay",
        productIdxList: this.productIdxList,
        couponIdxList: this.couponIdxList,
        productCouponMap: this.productCouponMap,
        ordersCartIdxList: this.ordersCartIdxList,
        mileage: this.userMileage,
      };

      // 아임포트 결제 요청
      IMP.request_pay(
        {
          pg: "kakaopay.TC0ONETIME",
          pay_method: orderData.payMethod,
          merchant_uid: orderData.merchantUid,
          name: orderData.productName,
          amount: orderData.amount,
          buyer_email: "lonua@gmail.com",
          buyer_name: "LONUA",
          buyer_tel: "010-1234-5678",
          buyer_addr: "서울특별시 성수동",
          buyer_postcode: "123-456",
        },
        (rsp) => {
          if (rsp.success) {
            orderData.impUid = rsp.imp_uid;
            axios
              .post(backend + "/orders/kakaopay", orderData, {
                headers: {
                  Authorization: `Bearer ${storedToken}`,
                },
              })
              .then((response) => {
                console.log(response.data);
                if (response.data.isSuccess === true) {
                  window.location.href = "/UserOrdersSuccess";
                  this.isLoading = false;
                } else {
                  alert("잘못 요청하였습니다.");
                  this.isLoading = false;
                }
              })
              .catch((error) => {
                console.error("주문 실패:", error);
                this.isLoading = false;
              });
          } else {
            console.log(rsp);
            this.isLoading = false;
          }
        }
      );
    },
  },
});
