<template>
  <div id="main-content" class="main-content">
    <div class="category-title">
      <h2>Promotion</h2>
    </div>

    <div class="men-category-title-image-container">
      <div class="title-image" style="
          background-image: url('https://wimage.wconcept.co.kr/msa/display/20230904105951146_5001.jpg?RS=600');
        ">
        <span>WEDNESDAY OASIS <br />
          24SS COLLECTION</span>
      </div>
      <div class="title-image" style="
          background-image: url('https://wimage.wconcept.co.kr/msa/display/20230904105627543_2741.jpg?RS=600');
        ">
        <span>Millo Archive <br />
          24SS COLLECTION</span>
      </div>
    </div>

    <div class="trending-now-title">
      <span>TRENDING NOW</span>
    </div>

    <div v-if="width <1200" class="splide_contoner">
      <!-- trending-now product -->
      <Splide :options="{
        type: 'loop',
        // width: &quot;auto&quot;,
        pagination: false,
        // gap: &quot;20px&quot;,
        perPage: 3,
      }" aria-label="My Favorite Images">
        <SplideSlide v-for="res in products" :key="res.productIdx">
          <ProductCardComponent v-bind:Product="res" v-bind:like="likesStore.indexList.includes(res.productIdx)" />
        </SplideSlide>
      </Splide>
    </div>
    <div v-else class="splide_contoner">
      <!-- trending-now product -->
      <Splide :options="{
        type: 'loop',
        // width: &quot;auto&quot;,
        pagination: false,
        // gap: &quot;20px&quot;,
        perPage: 5,
      }" aria-label="My Favorite Images">
        <SplideSlide v-for="res in products" :key="res.productIdx">
          <ProductCardComponent v-bind:Product="res" v-bind:like="likesStore.indexList.includes(res.productIdx)" />
        </SplideSlide>
      </Splide>
    </div>

  </div>
</template>

<script>
import { Splide, SplideSlide } from "@splidejs/vue-splide";
import "@splidejs/vue-splide/css";
import ProductCardComponent from "../components/ProductCardComponent.vue";
import { mapStores } from "pinia";
import { useLikesStore } from "../stores/useLikesStore.js";
import axios from "axios";
// import axios from 'axios'
export default {
  name: "MainPage",
  components: {
    Splide,
    SplideSlide,
    ProductCardComponent,
  },
  data() {
    return {
      active: "active",
      trends: [
        { trendIdx: 1, trendName: "가" },
        { trendIdx: 2, trendName: "나" },
        { trendIdx: 3, trendName: "다" },
        { trendIdx: 4, trendName: "라" },
        { trendIdx: 5, trendName: "마" },
      ],
      products: [
        { idx: 1, brandName: "가", productName: "가제품", price: 10000 },
        { idx: 2, brandName: "나", productName: "나제품", price: 20000 },
        { idx: 3, brandName: "다", productName: "다제품", price: 30000 },
      ],
      result: [
        {
          productIdx: "1",
          productImage: require("../assets/logo.png"),
          brandName: "brand",
          productName: "product1",
          priceBefore: "2012300",
          priceAfter: "1000",
          discount: "100%",
          likeState: false,
        },
        {
          productIdx: "2",
          productImage: require("../assets/logo.png"),
          brandName: "brand",
          productName: "product2",
          priceBefore: "2012300",
          priceAfter: "1000",
          discount: "100%",
          likeState: false,
        },
        {
          productIdx: "3",
          productImage: require("../assets/logo.png"),
          brandName: "brand",
          productName: "product3",
          priceBefore: "2012300",
          priceAfter: "1000",
          discount: "100%",
          likeState: false,
        },
      ],
      width: "0",
      height: "0",
    };
  },
  methods: {
    handleResize(event) {
      console.log(event);
      this.width = window.innerWidth;
      this.height = window.innerHeight;
    },

    clickedBtn(trendIdx) {
      if (trendIdx == 1) {
        this.result.productImage =
          "https://velog.velcdn.com/images/thanks9807/post/79cf8a8d-85ce-417c-b840-b1096a9ea90e/image.png";
      }
    },
    async getProductPage(page, size) {
      await axios
        .get(
          "http://www.lonuamall.kro.kr/api/product/list/" + page + "/" + size,
          // "http://localhost:8080/product/list/" + page + "/" + size
        )
        .then((res) => {
          console.log("getProductPage 성공!");
          console.log(res);
          this.products = res.data.result;
        })
        .catch((res) => {
          console.log("getProductPage 망했다! : ");
          console.log(res);
          this.products = res.data.result;
        });
    },
  },
  mounted() {
    this.likesStore.getMainLikeList();
    this.getProductPage(1, 15);
    window.addEventListener('resize', this.handleResize);
  },
  computed: {
    // 배열을 전달하지 않고, 스토어를 하나씩 전달합니다.
    // 각 스토어는 ID 뒤에 'Store'를 붙여서 액세스할 수 있습니다.
    // this.counterStore
    ...mapStores(useLikesStore),
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
a {
  text-decoration: none;
  color: black;
}

.category-nav {
  display: flex;
}

.nav-list {
  display: flex;
  list-style-type: none;
}

button {
  border: 0;
  background-color: transparent;
}

.category-nav-list-menu-dropdown {
  display: none;
  position: absolute;
}

.dropdown-trigger:hover>.category-nav-list-menu-dropdown {
  display: flex;
  flex-direction: column;
  list-style-type: none;
  border: 1px solid black;
  padding: 12px;
}

.category-men-content {
  margin: 0 auto;
}

.category-title {
  padding: 30px 0px;
  line-height: 44px;
  text-align: center;
  font-size: 44px;
}

.category-title>h2 {
  text-align: center;
  font-weight: 100;
}

.example {
  width: 200px;
  height: 200px;
  background-color: rebeccapurple;
}

.men-category-title-image-container {
  display: flex;
}

.title-image {
  background-repeat: no-repeat;
  width: 100%;
  height: 500px;
  background-size: 100% 500px;
  text-align: center;
}

.title-image>span {
  position: relative;
  top: 50%;
  color: white;
  font-size: 50px;
  font-weight: 50;
}

.sub-category-nav {
  display: flex;
  flex-flow: wrap;
  -webkit-box-pack: start;
  justify-content: flex-start;
  align-items: flex-start;
  transform: none !important;

  margin: 60px 0px 100px;
  border-top: 2px solid black;
  border-bottom: 2px solid black;
}

.sub-category-nav-item {
  margin: 0px 0px -1px;
  width: 14.2857%;
  height: 62px;
  border-left: 1px solid rgb(245, 245, 245);
  border-right: 1px solid rgb(245, 245, 245);
}

.sub-category-nav-item-btn {
  width: 100%;
  height: inherit;
  text-align: center;
  font-size: 15px;
  line-height: 22px;
  font-weight: 400;
  padding: 0px;
  color: rgb(85, 85, 85);
}

.trending-now-title {
  text-align: center;
  font-size: 38px;
  margin: 30px 0px;
  /* border-top: 2px solid black; */
}

.trending-now-content {
  display: flex;
  /* margin: 100px; */
  justify-content: center;
}

.trending-now-content-btn {
  text-align: center;
  font-size: 16px;
  font-weight: 900;
  color: gray;
  width: 100px;
  height: 60px;
}

.trending-now-content-btn.active {
  color: black;
}

.md-pick-title {
  text-align: center;
  font-size: 38px;
  margin: 30px 0px;
  /* border-top: 2px solid black; */
}

/* category-men.css */

.main-content {
  margin: 30px auto;
}

/* main.css */

.products-grid-container {
  grid-template-columns: repeat(6, minmax(calc(16.6667% - 18px), 1fr));
  gap: 34px 18px;
  width: 100%;
  display: grid;
  -webkit-box-align: start;
  align-items: start;
  margin-top: 50px;
}

.products-grid-item {
  position: relative;
  overflow: hidden;
  display: block;
  width: 100%;
  height: 100%;
}

.products-grid-item-image {
  position: relative;
  inset: 0px;
  box-sizing: border-box;
  padding: 0px;
  border: none;
  margin: auto;
  display: block;
  width: 100%;
}

.products-grid-item-info {
  display: flex;
  flex-direction: column;
}

.products-grid-item-info-top {
  display: flex;
  flex-direction: row;
  margin-bottom: 10px;
}

.products-grid-item-info-mid {
  display: flex;
  flex-direction: row;
  margin-bottom: 10px;
}

.products-grid-item-info-bottom {
  display: flex;
  flex-direction: row;
}

.products-grid-item-info-brand {}

.products-grid-item-info-name {
  text-align: left;
}

.products-grid-item-info-price-before {
  /* font-weight: 700; */
  text-decoration: line-through;
}

.products-grid-item-info-price-after {
  font-weight: 700;
}

.products-grid-item-info-price-discount {
  font-weight: 700;
  color: orange;
}

.products-grid-item-info-like-btn {
  position: absolute;
  right: 5px;
}

/* filter */
.filter-btn {
  border: 1px black solid;
  border-radius: 10px;
}

.filter-menu {
  opacity: 0;
  transform: translateY(20px);
  transition: opacity 1s ease, transform 1s ease;
}

/* product-grid.css */

.prioritys {
  position: absolute;
  right: 0px;
}

.priority.active {
  font-weight: 900;
}

.priority.unactive {
  color: gray;
  font-weight: 300;
}

.splide_contoner {
  height: 120%;
}
</style>
