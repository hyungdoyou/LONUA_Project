import { createRouter, createWebHistory } from "vue-router";
import VueJwtDecode from "vue-jwt-decode";

import MainPage from "../page/MainPage.vue";
import ProductPage from "../page/ProductPage.vue";
import BrandPage from "../page/BrandPage.vue";
import MainMyPage from "../page/MainMyPage.vue";
import SellerSignUp from "../page/SellerSignUp.vue";
import UserCancel from "../page/UserCancel.vue";
import UserCancelOrders from "../page/UserCancelOrders.vue";
import UserCart from "../page/UserCart.vue";
import UserLogIn from "../page/UserLogIn.vue";
import SellerLogIn from "../page/SellerLogIn.vue";
import UserOrders from "../page/UserOrders.vue";
import UserSignUp from "../page/UserSignUp.vue";
import UserSignUpSuccess from "../page/UserSignUpSuccess.vue";
import UserUpdate1 from "../page/UserUpdate1.vue";
import UserUpdate2 from "../page/UserUpdate2.vue";
import Productregister from "../page/ProductRegisterPage.vue";
import ProductDetail from "../page/ProductDetail.vue";
import UserCoupon from "../page/UserCoupon.vue";
import UserOrdersSuccess from "../page/UserOrdersSuccess.vue";
import UserMileage from "../page/UserMileage.vue";
import UserLikes from "../page/UserLikes.vue";
import UserReview from "../page/UserReview";
import UserQuestion from "../page/UserQuestion.vue";
import BrandDetailPage from "../page/BrandDetailPage.vue";
import UserReviewRegister from "../page/UserReviewRegister.vue";
import KakaoLogIn from "../page/KakaoLogIn";
import CategoryProductPage from "../page/CategoryProductPage.vue";
import BodyTypeProductPage from "../page/BodyTypeProductPage.vue";
import StyleProductPage from "../page/StyleProductPage.vue";

const router = createRouter({
  history: createWebHistory(),
  routes: [
    { path: "/", component: MainPage },
    {
      path: "/KakaoLogIn/:token",
      name: "KakaoLogIn",
      component: () => import("../page/KakaoLogIn.vue"),
      props: true,
    },
    { path: "/product/category/:CategoryIdx", component: CategoryProductPage },
    { path: "/product/style/:StyleIdx", component: StyleProductPage },
    { path: "/KakaoLogIn", component: KakaoLogIn },
    { path: "/product", component: ProductPage },
    { path: "/brand", component: BrandPage },
    { path: "/productdetail/:idx", component: ProductDetail },
    { path: "/SellerSignUp", component: SellerSignUp },
    { path: "/UserLogIn", component: UserLogIn },
    { path: "/SellerLogIn", component: SellerLogIn },
    { path: "/UserSignUp", component: UserSignUp },
    { path: "/UserSignUpSuccess", component: UserSignUpSuccess },
    { path: "/UserQuestion", component: UserQuestion },
    { path: "/brand/:idx", component: BrandDetailPage },

    // 권한 필요 페이지
    { path: "/product/type", component: BodyTypeProductPage },
    { path: "/UserReviewRegister", component: UserReviewRegister },
    { path: "/UserCoupon", component: UserCoupon },
    { path: "/UserMileage", component: UserMileage },
    { path: "/productRegister", component: Productregister },
    { path: "/UserOrdersSuccess", component: UserOrdersSuccess },
    { path: "/MainMyPage", component: MainMyPage },
    { path: "/UserCancel", component: UserCancel },
    { path: "/UserCancelOrders", component: UserCancelOrders },
    { path: "/UserCart", component: UserCart },
    { path: "/UserOrders", component: UserOrders },
    { path: "/UserUpdate1", component: UserUpdate1 },
    { path: "/UserUpdate2", component: UserUpdate2 },
    { path: "/UserLikes", component: UserLikes },
    { path: "/UserReview", component: UserReview },
  ],
});

export default router;

router.beforeEach((to, from, next) => {
  // 로그인이 필요한 페이지
  const authPages = [
    "/product/type",
    "/UserReviewRegister",
    "/UserMileage",
    "/productregister",
    "/UserCoupon",
    "/UserOrdersSuccess",
    "/MainMyPage",
    "/UserCancel",
    "/UserCancelOrders",
    "/UserCart",
    "/UserOrders",
    "/UserUpdate1",
    "/UserUpdate2",
    "/UserLikes",
    "/UserQnA",
    "/UserReview"
  ];

  if (authPages.includes(to.fullPath)) {
    const storedToken = sessionStorage.getItem("token");
    if (storedToken === null) {
      alert("로그인 후 이용하실 수 있습니다.")
      next("/UserLogIn");
    } else {
      const tokenData = VueJwtDecode.decode(storedToken);

      console.log(tokenData);
      const currentTime = Math.floor(Date.now() / 1000);
      if (tokenData.exp < currentTime) {
        sessionStorage.removeItem("token");
        alert("로그인 유지시간이 만료되었습니다. 다시 로그인해주세요.")
        next("/UserLogIn");
      } else {
        next();
      }
    }
  } else {
    next();
  }
});
