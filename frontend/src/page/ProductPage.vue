<template>
  <div class="content">
      <!-- All product -->
      <div>
          <section style="margin: 30px;">
              <!-- <div class="filter-menu" id="filter-menu1">
                  <p>This element will fade in slowly.</p>
              </div> -->
              <!-- <div class="filter-btns">
                  <button class="filter-initial">
                      <i class="fa-solid fa-xmark"></i>
                  </button>
                  <button class="filter-btn">
                      클리어런스
                      <i class="fa-solid fa-angle-down"></i>
                  </button>
                  <button class="filter-btn">
                      컬러
                      <i class="fa-solid fa-angle-down"></i>
                  </button>
                  <button class="filter-btn">
                      가격
                      <i class="fa-solid fa-angle-down"></i>
                  </button>
                  <button class="filter-btn">
                      할인<i class="fa-solid fa-angle-down"></i>
                  </button>
                  <button class="filter-btn">
                      혜택<i class="fa-solid fa-angle-down"></i>
                  </button>
              </div> -->
              <!-- <div class="prioritys">
                  <button class="priority active" id="lastest" onclick="priorityActive('lastest')">
                      신상품순
                  </button>
                  <button class="priority" id="bestSeller" onclick="priorityActive('bestSeller')">
                      판매순
                  </button>
                  <button class="priority" id="discount-rate" onclick="priorityActive('discount-rate')">
                      할인율순
                  </button>
                  <button class="priority" id="cheapest" onclick="priorityActive('cheapest')">
                      낮은가격순
                  </button>
                  <button class="priority" id="expensive" onclick="priorityActive('expensive')">
                      높은가격순
                  </button>
                  <button class="priority" id="review" onclick="priorityActive('review')">
                      리뷰순
                  </button>
              </div> -->
              <!-- 6 x 10 -->
              <div class="products-grid-container">
                  <!-- 자바 스크립트로 생성 -->
                  <ProductCardComponent v-for="(product, idx) in productPage" :key="idx" :Product="product" v-bind:like="likesStore.indexList.includes(product.productIdx)" />
                  
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
  name: 'ProductPage',
  data() {
      return {
          msg: "ProductPage",
          productPage: [],
          demo: [1, 2, 3],
      }
  },
  methods: {
      async getProductPage(page, size) {
          const backend = 'http://www.lonuamall.kro.kr/api';
          // let backend = "http://localhost:8080";
          await axios.get(backend + "/product/list/" + page + "/" + size).then((res) => {
              console.log(res);
              this.productPage = res.data.result;

          }).catch((res) => {
              console.log("망했다! : " + res);
          });

      },


      async init() {
          await this.getProductPage(1, 30);
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
</style>
