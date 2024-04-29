<template>
  <button
    type="button"
    name="button"
    class="heart active"
    @click="deleteLikes()"
  ></button>
  <a :href="'/productdetail/' + likes.productIdx">
    <div class="img">
      <img :src="likes.productImage" />
    </div>
    <div class="text">
      <div class="text_wrap">
        <div class="brand">{{ likes.brandName }}</div>
        <div class="product ellipsis">{{ likes.productName }}</div>
      </div>
      <div class="price">
        <span class="discount_price">{{
          likes.salePrice.toLocaleString()
        }}</span>
        <!--판매가격-->
        <span class="base_price">{{ likes.price.toLocaleString() }}</span>
        <!--소비자가격-->
        <span class="discount_rate">{{
          calculateDiscountRate(likes.salePrice, likes.price)
        }}</span>
        <!--할인율-->
      </div>
      <div class="labels"></div>
    </div>
  </a>
</template>

<script>
import { mapStores } from "pinia";
import { useLikesStore } from "../stores/useLikesStore";
export default {
  name: "LikesComponent",
  props: ["likes"],
  computed: {
    ...mapStores(useLikesStore),
  },
  methods: {
    calculateDiscountRate(salePrice, price) {
      const discountRate = ((price - salePrice) / price) * 100;
      return `${discountRate.toFixed(2)}%`;
    },
    deleteLikes() {
      this.likesStore.cancelLike(this.likes.productIdx);
      const indexToRemove = this.likesStore.likes.findIndex(
        (item) => item.productIdx === this.likes.productIdx
      );
      if (indexToRemove !== -1) {
        this.likesStore.likes.splice(indexToRemove, 1);
      }
    },
  },
};
</script>

<style scoped>
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

.table_title .btn_more_noline:after {
  content: "";
  display: block;
  position: absolute;
  top: 6px;
  right: 0;
  width: 7px;
  height: 7px;
  background: url(//static.wconcept.co.kr/web/images/common/spr-common.png) -90px -60px
    no-repeat;
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
</style>
