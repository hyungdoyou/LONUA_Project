<template>
  <div class="top_banner">
    <a
      href="javascript:void(0);"
      target="_self"
      title="설연휴세일"
      class="center-block"
    >
      <img
        src="//image.wconcept.co.kr/images/builder/0/3/14/275/PC_top_banner_1920x100_01_20240126180614.jpg"
        alt="설연휴세일"
        class="img_responsive"
      />
    </a>
  </div>
  <header class>
    <div class="global_search">
      <h1
        class="symbol"
      >
        <a href="/">
          <img
            src="https://github.com/hyungdoyou/LONUA_Project/assets/148875644/0a6a9978-2b0d-499f-85a3-4da69d548aff"
            alt="LONUA"
          />
        </a>
      </h1>
      <ul class="utility">
        <li v-show="!isLogin()">
          <a href="/UserSignUp">
            <i class="fa-solid fa-user-plus"></i>
            <strong>join</strong>
          </a>
        </li>
        <li v-show="isLogin()">
          <a href="/" @click="logout">
            <i class="fa-solid fa-arrow-right-to-bracket"></i>
            <strong>logout</strong>
          </a>
        </li>
        <li v-show="!isLogin()">
          <a href="/UserLogIn">
            <i class="fa-solid fa-arrow-right-to-bracket"></i>
            <strong>login</strong>
          </a>
        </li>
        <li v-show="isSellerLogin()">
          <a href="/productRegister">
            <i class="fa-brands fa-shopify"></i>
            <strong>register</strong>
          </a>
        </li>
        <li v-show="isLogin() && !isSellerLogin()">
          <a href="/UserLikes">
            <i class="fa-solid fa-heart"></i>
            <strong>heart</strong>
          </a>
        </li>
        <li v-show="!isSellerLogin()">
          <a href="/MainMyPage">
            <i class="fa-solid fa-user"></i>
            <strong>my</strong>
          </a>
        </li>
        <li v-show="!isSellerLogin()">
          <a href="/UserCart">
            <i class="fa-solid fa-cart-shopping"></i>
            <strong id="basketCount">cart</strong>
          </a>
        </li>
      </ul>
      <nav>
        <div class="global_nav_wrap">
          <div class="global_nav">
            <ul class="nav nav_left" id="actionbar">
              <li class="nav-item">
                <a href="/brand"> BRAND </a>
                <div class="nav-item-dropdown">
                  <div class="dropdown-menu">
                    <div class="dropdown-menu-header">
                      <ul class="dropdown-menu-list">
                        <li
                          v-for="(brand, idx) in brandStore.brandPage"
                          :key="idx"
                          class="dropdown-menu-list-item"
                        >
                          <a
                            class="dropdown-menu-list-item-link"
                            :href="'/brand/' + brand.brandIdx"
                          >
                            {{ brand.brandName }}
                          </a>
                        </li>
                      </ul>
                    </div>
                  </div>
                </div>
              </li>
              <li class="nav-item">
                <a href="/product"> CATEGORY </a>
                <div class="nav-item-dropdown">
                  <div class="dropdown-menu">
                    <div class="dropdown-menu-header">
                      <ul class="dropdown-menu-list">
                        <li
                          v-for="(category, idx) in categorys"
                          :key="idx"
                          class="dropdown-menu-list-item"
                        >
                        <a class="dropdown-menu-list-item-link" :href="'/product/category/'+(idx+1)">{{
                            category.categoryName
                          }}</a>
                        </li>
                      </ul>
                    </div>
                  </div>
                </div>
              </li>
              <li class="nav-item">
                <a href="/product"> STYLE </a>
                <div class="nav-item-dropdown">
                  <div class="dropdown-menu">
                    <div class="dropdown-menu-header">
                      <ul class="dropdown-menu-list">
                        <li
                          v-for="(style, idx) in styles"
                          :key="idx"
                          class="dropdown-menu-list-item"
                        >
                        <a class="dropdown-menu-list-item-link" :href="'/product/style/'+(idx+1)">{{
                            style.styleName
                          }}</a>
                        </li>
                      </ul>
                    </div>
                  </div>
                </div>
              </li>
              <li class="nav-item">
                <a href="/product/type"> SAME BODY TYPE </a>
                <!-- <div class="nav-item-dropdown">
                  <div class="dropdown-menu">
                    <div class="dropdown-menu-header">
                      <ul class="dropdown-menu-list">
                        <li
                          v-for="(type, idx) in Types"
                          :key="idx"
                          class="dropdown-menu-list-item"
                        >
                          <a class="dropdown-menu-list-item-link" href="/product">
                            {{ type.typeName }}
                          </a>
                        </li>
                      </ul>
                    </div>
                  </div>
                </div> -->
              </li>
            </ul>
          </div>
        </div>
      </nav>
    </div>
  </header>
</template>

<script>
import { mapStores } from "pinia";
import { useBrandStore } from "../stores/useBrandStore";
export default {
  name: "HeaderComponent",
  computed: {
    ...mapStores(useBrandStore),
  },
  mounted(){
      this.brandStore.getBrnadPage(1,30);
      this.brands = this.brandStore.brandPage;
    },
  data() {
    return {
      // TODO DB에서든 어디서든 추가
      brands: [
        { brandName: "전체" },
        { brandName: "SATUR" },
        { brandName: "SPAO" },
        { brandName: "INSILENCE" },
        { brandName: "STANDARD" },
        { brandName: "MUSINSA" },
        { brandName: "LMOOD" },
        { brandName: "COVERNAT" },
        { brandName: "YALE" },
      ],
      categorys: [
        { categoryName: "상의" },
        { categoryName: "하의" },
        { categoryName: "아우터" },
        { categoryName: "원피스" },
        { categoryName: "스커트" },
        { categoryName: "신발" },
        { categoryName: "모자" },
        { categoryName: "가방" },
        { categoryName: "액세서리" },
        { categoryName: "스포츠/용품" },
      ],
      styles: [
        { styleName: "캐주얼" },
        { styleName: "시크" },
        { styleName: "댄디" },
        { styleName: "스트릿" },
        { styleName: "비지니스 캐주얼" },
        { styleName: "힙합" },
        { styleName: "오피스" },
        { styleName: "스포츠" },
      ],
      Types: [
        { typeName: "전체" },
        { typeName: "상체 유형" },
        { typeName: "하체 유형" },
      ],
    };
  },
  methods: {
    isLogin() {
      const token = sessionStorage.getItem("token");
      if (token !== null) {
        return true;
      } else {
        return false;
      }
    },
    isSellerLogin() {
      const token = sessionStorage.getItem("token");
      if (token) {
        const role = JSON.parse(atob(token.split(".")[1])).ROLE || [];
        if (role.includes("ROLE_SELLER")) {
          return true;
        } else {
          return false;
        }
      } else {
        return false;
      }
    },
    logout() {
      sessionStorage.removeItem("token");
      window.location.href = "/";
      this.isLogin = false;
    },
  },
};
</script>

<style scoped>
ol,
ul,
li {
  margin: 0;
  padding: 0;
  list-style: none;
}

a {
  color: black;
  text-decoration: none;
  background-color: transparent;
  vertical-align: middle;
}

button {
  border: 0px;
  background-color: transparent;
  padding: 0px;
  cursor: pointer;
  vertical-align: middle;
  outline: none;
  appearance: none;
}

body,
input,
select,
textarea,
button {
  font-family: ProximaNova-Regular, "Noto Sans KR", "Apple SD Gothic Neo",
    "Malgun Gothic", "맑은 고딕", sans-serif;
  line-height: 1;
}

.fa {
  display: inline-block;
  font: normal normal normal 14px/1 FontAwesome;
  font-size: inherit;
  text-rendering: auto;
}

.fa-heart-o:before {
  content: "\f08a";
}

.top_banner a {
  display: block;
  width: 100%;
  min-width: 1280px;
}

.img_responsive {
  vertical-align: top;
  width: 100%;
}

header {
  width: 100%;
  height: 146px;
  position: relative;
  top: 0;
  left: 0;
  right: 0;
  z-index: 100;
  min-width: 1280px;
  font-family: "ProximaNova-Regular", "Noto Sans KR";
  display: block;
}

header .global_search {
  position: relative;
  width: 100%;
  max-width: 1920px;
  height: 88px;
  background-color: #fff;
  margin: 0 auto;
  padding-right: 20px;
}

header .symbol {
  position: absolute;
  left: 20px;
  top: 32px;
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

header .symbol a {
  display: block;
}

header .symbol a img {
  display: block;
  margin-top: -20px;
}

a,
img,
input,
button,
select,
textarea {
  vertical-align: middle;
}

img {
  border: 0;
}

header #top_search {
  position: absolute;
  z-index: 101;
  left: 50%;
  top: 9px;
  transform: translateX(-50%);
  width: 392px;
  height: 70px;
  padding: 11px 16px 15px;
  background: #fff;
}

header #top_search input {
  width: 335px;
  height: 44px;
  display: block;
  float: left;
  border: none;
  font-size: 14px;
  line-height: 44px;
  background: none;
  padding: 0;
}

input[type="text"] {
  height: 40px;
  line-height: 38px;
  padding-left: 20px;
  border: none;
  background-color: #f2f2f2;
  border: 1px solid #f2f2f2;
  font-size: 14px;
  font-family: "ProximaNova-Regular", "Noto Sans KR";
  outline: none;
}

header #top_search .btn_search {
  display: block;
  float: left;
  position: relative;
  width: 25px;
  height: 44px;
  overflow: hidden;
  text-indent: -9999px;
  background: url(//static.wconcept.co.kr/web/images/common/svg/ico_search_25.svg)
    no-repeat center 9px/100%;
}

header #top_search .result_wrap {
  z-index: 101;
  width: 526px;
  border: 1px solid #e9e9e9;
  background: #fff;
  position: absolute;
  top: 61px;
  left: 16px;
}

header #top_search .result {
  font-size: 0;
  white-space: nowrap;
}

header #top_search .column.left {
  width: 308px;
}

header #top_search .column {
  display: inline-block;
  position: relative;
  padding: 24px;
  vertical-align: top;
}

header #top_search .column .tit {
  font-family: "NotoSansKR-Medium";
  font-size: 14px;
  line-height: 17px;
  margin-bottom: 17px;
}

header #top_search .column .del_all {
  position: absolute;
  top: 25px;
  right: 25px;
  font-size: 11px;
  color: #9d9d9d;
}

button {
  vertical-align: middle;
  width: auto;
  border: 0;
  padding: 0;
  background: none;
  font-weight: normal;
}

header #top_search .column .list li.nodata {
  font-size: 12px;
  color: #9d9d9d;
}

header #top_search .column .list li {
  position: relative;
  cursor: pointer;
}

header #top_search .column li {
  font-size: 13px;
  line-height: 16px;
}

header #top_search .column-right {
  width: 216px;
  height: 353px;
  border-left: 1px solid #f5f5f5;
}

header #top_search .column-right li ~ li {
  margin-top: 12px;
}

header #top_search .column-right {
  display: inline-block;
  position: relative;
  padding: 24px;
  vertical-align: top;
}

header #top_search .column-right .tit {
  font-family: "NotoSansKR-Medium";
  font-size: 14px;
  line-height: 17px;
  margin-bottom: 17px;
}

header #top_search .column-right li {
  font-size: 13px;
  line-height: 16px;
}

header #top_search .column-right .rank li a {
  display: -webkit-box;
  display: -moz-box;
  display: flex;
  position: relative;
  text-align: left;
  font-size: 13px;
  line-height: 16px;
  justify-content: flex-start;
}

header #top_search .column-right .rank li a > em {
  display: block;
  width: 20px;
  height: 16px;
  font-family: "ProximaNova-Medium";
  font-style: italic;
  color: #9d9d9d;
}

header #top_search .column-right .rank li a p {
  padding-right: 26px;
  position: relative;
  display: inline-grid;
  max-width: calc(100% - 20px);
}

header #top_search .column-right .rank li a p span {
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
  letter-spacing: -0.28px;
  color: #555;
}

header #top_search .column-right .rank li a p strong {
  font-size: 10px;
  color: #fa5500;
  position: absolute;
  right: 0;
  top: 0;
}

header #top_search .result:after {
  display: block;
  clear: both;
  content: "";
}

header #top_search .result {
  font-size: 0;
  white-space: nowrap;
}

header #top_search .column.full {
  width: 100%;
  padding: 25px 24px;
}

header #top_search .result_wrap .bx_btn {
  height: 34px;
  border-top: 1px solid #f5f5f5;
  text-align: right;
  padding-right: 20px;
}

header #top_search .result_wrap .bx_btn .close {
  font-size: 11px;
  line-height: 32px;
  color: #9d9d9d;
}

header #top_search:after {
  content: "";
  display: block;
  width: 360px;
  height: 1px;
  background: #000;
  position: absolute;
  bottom: 15px;
  left: 16px;
}

header .utility {
  position: sticky;
  right: 17px;
  float: right;
  padding-top: 26px;
  margin-right: -17px;
}

header .utility:before,
header .utility:after {
  content: "";
  display: table;
}

header .utility li {
  text-transform: uppercase;
  font-family: "ProximaNova-Semibold";
  text-align: center;
  float: left;
}

header .utility a {
  display: block;
  position: relative;
  padding: 0 17px 18px 17px;
  color: #000;
}

.fa-solid {
  display: inline-block;
  width: 30px;
  height: 30px;
  background: transparent no-repeat center;
  vertical-align: top;
}

.fa-solid {
  font-size: 28px;
}

.fa-brands {
  font-size: 30px;
}

header .utility strong {
  position: absolute;
  bottom: 0;
  left: 0;
  right: 0;
  font-size: 12px;
  line-height: 12px;
}

/*--------------------------------------------*/
header nav {
  position: relative;
  width: 100%;
  top: 120px;
  left: 0;
  right: 0;
  z-index: 100;
  min-width: 1280px;
  background: #fff;
  border-bottom: 1px solid #e9e9e9;
}

header .global_nav_wrap {
  max-width: 1920px;
  height: 57px;
  margin: 0 auto;
  padding: 0 20px;
}

@media (max-width: 1365px) {
  header .global_nav_wrap .global_nav {
    justify-content: flex-start;
    padding-left: 0;
    height: 100%;
  }
}

header .global_nav_wrap .global_nav {
  display: flex;
  height: 100%;
}

header .global_nav_wrap .nav {
  display: flex;
  height: 100%;
}

header .global_nav_wrap .nav > li > a {
  display: block;
  position: relative;
  text-transform: uppercase;
  color: #000;
  font-size: 14px;
  line-height: 48px;
  padding: 0 13px;
  font-weight: 700;
  font-style: normal;
  letter-spacing: 2px;
  margin-left: 10px;
}

header .global_nav_wrap .nav > li > a:hover {
  border-bottom: 3px solid;
}

@font-face {
  font-family: "SejonghospitalBold";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2312-1@1.1/SejonghospitalBold.woff2")
    format("woff2");
  font-weight: 700;
  font-style: normal;
}

.nav-item {
  display: flex;
  padding-right: 20px;
  height: 100%;
}

.nav-item-dropdown {
  display: none;
}

header .global_nav_wrap .nav > li:hover > .nav-item-dropdown {
  z-index: 100;
  position: absolute;
  width: 100%;
  height: 309px;
  padding: 17px 42px;
  border-top: 1px solid grey;
  border-bottom: 1px solid grey;
  background-color: white;
  top: 55px;
  left: 0px;
  display: block;
}

.dropdown-menu {
  display: flex;
}

.dropdown-menu-list {
  display: flex;
  flex-flow: column wrap;
  height: 300px;
}

.dropdown-menu-header {
  width: 176px;
  height: 320px;
}

.dropdown-menu-list-item {
  width: 176px;
  height: 35px;
}

.dropdown-menu-list-item-link strong {
  font-weight: bold;
}
</style>
