<template>
  <div class="content">
      <div class="brand-info-container">
          <div class="brand-main-img">
              <img :src="brand.brandImage"/>
          </div>
          <div class="brand-main-info">
              <div class="brand-main-info-left">
                  <img :src="brand.brandImage"/>
              </div>
              <div class="brand-main-info-right">
                  <div class="brand-main-info-name">
                      <h2>{{ brand.brandName }}</h2>
                  </div>
                  <div class="brand-main-info-name">
                      {{ brand.brandIntroduction }}
                  </div>
              </div>
          </div>
      </div>
      <!-- All product -->
      <div>
          <section>
              
              <!-- 6 x 10 -->
              <div class="products-grid-container">
                  <!-- 자바 스크립트로 생성 -->
                  <ProductCardComponent v-for="(product, idx) in productPage" :key="idx" :Product="product"
                      v-bind:like="likesStore.indexList.includes(product.productIdx)" />
                  
              </div>

          </section>
      </div>
  </div>
</template>

<script>
import ProductCardComponent from '../components/ProductCardComponent.vue';
import axios from 'axios';
import { mapStores } from "pinia";
import { useLikesStore } from "../stores/useLikesStore.js";
export default {
  components: {
      ProductCardComponent,
  },
  name: 'BrandDetailPage',

  data() {
      return {
          msg: "BrandDetailPage",
          productPage: [],
          demo: [1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6, 7, 7, 8, 9, 0],
          brand: { brandName:"abcd"},
      }
  },
  methods: {
      async getProductPage(page, size) {
          const backend = 'http://www.lonuamall.kro.kr/api';
          // let backend = "http://localhost:8080";
          await axios.get(backend + "/product/brand/"+this.$route.params.idx+"/" + page + "/" + size).then((res) => {
              //console.log(res);
              this.productPage = res.data.result;

          }).catch((res) => {
              console.log("망했다! : " + res);
          });

      },

      async getBrandDetail(idx) {
          const backend = 'http://www.lonuamall.kro.kr/api';
          // let backend = "http://localhost:8080";
          await axios.get(backend + "/brand/"+idx).then((res) => {
              //console.log(res);
              this.brand = res.data.result;

          }).catch((res) => {
              console.log("망했다! : " + res);
          });

      },


      async init() {
          await this.getBrandDetail(this.$route.params.idx);
          await this.getProductPage(1, 30);
          
      }

  },
  mounted() {
      console.log("mounted");
      this.init();
      

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
</script >

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.products-grid-container {
  grid-template-columns: repeat(6, minmax(calc(16.6667% - 18px), 1fr));
  grid-template-rows: auto;
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
  margin-bottom: 10px
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

.brand-main-info {
  display: flex;
  flex-direction: row;
  margin: 0px auto;
  justify-content: center;
}

.brand-main-info-left {
  /* background-color: rgb(131, 248, 131); */
  width : 25%;
  max-width : 300px;
  min-width: 100px;
}

.brand-main-info-left img{
  /* border: 1px solid black;
  border-radius: 50%; */
  display: block;
  margin: 10px auto;
  width: 100%;
}

.brand-main-info-right {
  width : 60%;
  margin : 10px 30px;
}
.brand-main-img {
  /* text-align: center; */
  /* background-color: rgb(131, 248, 131); */
  height: 200px;
}
.brand-main-img img{
  /* width: 100%; */
  display: block;
  max-height: 200px;
  margin: 10px auto;
}
</style>
