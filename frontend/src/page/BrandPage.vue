<template>
      <div class="loadingio-spinner-spinner" v-if="isLoading">
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
  <!-- brand content -->
  <div v-for="brand in brandPage" :key="brand.brandIdx" class="brand" >
      <div class="brand-info"><a href="#">{{ brand.brandName }}</a></div>
      <div class="brand-itemContainer">

          <Splide :options='{
              type: "loop",
              width: "100%",
              pagination: false,
              gap: "20px",
              perPage: 3,
          }' aria-label="My Favorite Images">
              <!-- <SplideSlide v-for="res in result" :key="res.productIdx">
                  <ProductCardComponent v-bind:Product="res" v-bind:like="likesStore.indexList.includes(res.productIdx)"/>
              </SplideSlide> -->
              <SplideSlide v-for="product in brand.getBrandProductResList" :key="product.productIdx">
                  <ProductCardComponent v-bind:Product="product" v-bind:like="likesStore.indexList.includes(product.productIdx)"/>
              </SplideSlide>


          </Splide>
      </div>

  </div>
</template>

<script>
import { Splide, SplideSlide } from '@splidejs/vue-splide';
import '@splidejs/vue-splide/css';
import axios from 'axios';
import { mapStores } from "pinia";
import { useLikesStore } from "../stores/useLikesStore.js";
// import '@splidejs/vue-splide/css/core';
import ProductCardComponent from '../components/ProductCardComponent.vue';

export default {
  name: 'BrandPage',
  components: {
      Splide,
      SplideSlide,
      ProductCardComponent,
  },
  data() {
      return {
        isLoading: false,

          msg: "BrandPage",
          result: [{
              productIdx: "1",
              productImage: require("../assets/logo.png"),
              brandName: "brand",
              productName: "product1",
              priceBefore: "2012300",
              priceAfter: "1000",
              discount: "100%",
              likeState: false,
          }, {
              productIdx: "2",
              productImage: require("../assets/logo.png"),
              brandName: "brand",
              productName: "product2",
              priceBefore: "2012300",
              priceAfter: "1000",
              discount: "100%",
              likeState: false,
          }, {
              productIdx: "3",
              productImage: require("../assets/logo.png"),
              brandName: "brand",
              productName: "product3",
              priceBefore: "2012300",
              priceAfter: "1000",
              discount: "100%",
              likeState: false,
          }, {
              productIdx: "4",
              productImage: require("../assets/logo.png"),
              brandName: "brand",
              productName: "product4",
              priceBefore: "2012340",
              priceAfter: "1400",
              discount: "100%",
              likeState: false,
          }],
          brandPage: [],
          productPage: [],
      }
  },
  methods: {
      async getBrnadAll() {
        this.isLoading = true;
          const backend = 'http://www.lonuamall.kro.kr/api';
        //   let backend = "http://localhost:8080";
          await axios.get(backend + "/brand/listall").then((res) => {
              console.log(res);
              this.brandPage = res.data.result;
              this.isLoading = false;
          }).catch((res) => {
              console.log("망했다! : " + res);
              this.isLoading = false;
          });

      },


      async getBrnadPage(page, size) {
          const backend = 'http://www.lonuamall.kro.kr/api';
        //   let backend = "http://localhost:8080";
          await axios.get(backend + "/brand/list/" + page + "/" + size).then((res) => {
              console.log(res);
              this.brandPage = res.data.result;
              
          }).catch((res) => {
              console.log("망했다! : " + res);
          });

      },


      

      async getProductPageByBrandIdx(brandIdx, page, size) {
          console.log(brandIdx);
          await axios.get(
            "http://www.lonuamall.kro.kr/api/product/brand/" + brandIdx + "/" + page + "/" + size,
              // "http://localhost:8080/product/brand/" + brandIdx + "/" + page + "/" + size,
          ).then((res)=>{
              console.log("getProductPageByBrandIdx 성공!");
              console.log(res);
              this.productPage.push(res.data.result);

          }).catch((res) => {
              console.log("getProductPageByBrandIdx 망했다! : ");
              console.log(res);
              this.productPage.push(this.result);
          });
      },
      async init(){
          // await this.getBrnadPage(1, 10);
          // this.brandPage.map(async (brand)=>{
          //     await this.getProductPageByBrandIdx(brand.brandIdx,1,15);
          //     console.log(this.likesStore.indexList);
          //     //console.log(this.likesStore.indexList.includes(3));
          // });
          await this.getBrnadAll();
      }

  },
  mounted() {
      console.log("mounted");
      this.init();
      this.likesStore.getLikeList();
      // this.brandPage = this.brandPage.data.result;
      // console.log(result);

  },
  computed: {
  // 배열을 전달하지 않고, 스토어를 하나씩 전달합니다.
  // 각 스토어는 ID 뒤에 'Store'를 붙여서 액세스할 수 있습니다.
  // this.counterStore
  ...mapStores(useLikesStore)
},
  
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.brand-info {
  content: "max-content";
  font-size: large;
  font-weight: 900;
  border-top: 1px gray solid;
  margin: 0px 20px;
  width: 200px;

}

.brand-itemContainer {
  width: 80%;
}

.brand {
  
  display: flex;
  flex-direction: row;
  max-height: 400px;
  min-height: 330px;
  margin: 60px auto;
  width: 100%;
  box-sizing: border-box;
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
