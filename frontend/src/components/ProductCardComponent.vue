<template>
  <div class="productCard">
    <a :href="'/productdetail/' + Product.productIdx">
      <img
        :src="Product.productImage"
        :alt="Product.productImage"
        class="productsCardImage"
      />
    </a>
    <div class="productCardInfo">
      <div class="productCardInfoTop">
        <div v-text="Product.brandName" class="brandName"></div>
        <button
          class="products-grid-item-info-like-btn"
          @click.stop.prevent="clickLike"
        >
          <i v-if="!like" class="fa-regular fa-heart"></i>
          <i v-if="like" class="fa-solid fa-heart" style="color: red"></i>
        </button>
      </div>
      <a :href="'/productdetail/' + Product.productIdx">
        <div v-text="Product.productName" class="productName"></div>

        <div class="productCardInfoBottom">
          <div v-text="price.toLocaleString()" class="priceBefore"></div>
          <div class="salePrice">
            <span class="discount">{{
              (100 - (Product.salePrice / Product.price) * 100).toFixed(0) + "%"
            }}</span>
            <div v-text="salePrice.toLocaleString()" class="priceAfter"></div>
          </div>
        </div>
      </a>
    </div>
  </div>
</template>

<script>
import { mapStores } from "pinia";
import { useLikesStore } from "../stores/useLikesStore.js";
export default {
  name: "ProductCardComponent",
  data() {
    return {
      price : "0",
      salePrice: "0",
    };
  },
  props: ["Product", "like"],
  methods: {
    clickLike: function () {
      console.log("likebutton clicked");
      const storedToken = sessionStorage.getItem("token");
      if (storedToken === null) {
        alert("로그인 후 좋아요 기능을 이용할 수 있습니다.");
        return;
      }

      // 좋아요 취소
      if (this.like) {
        this.likesStore.cancelLike(this.Product.productIdx);

        this.likesStore.indexList = this.likesStore.indexList.filter(
          (index) => {
            return this.Product.productIdx != index;
          }
        );
      } else {
        //좋아요 추가
        this.likesStore.addLike(this.Product.productIdx);
        // this.likeState = !this.likeState;

        this.likesStore.indexList.push(this.Product.productIdx);
      }

      // TODO: 백엔드에 like 추가 요청
    },
  },
  computed: {
    // 배열을 전달하지 않고, 스토어를 하나씩 전달합니다.
    // 각 스토어는 ID 뒤에 'Store'를 붙여서 액세스할 수 있습니다.
    // this.counterStore
    ...mapStores(useLikesStore),
  },
  mounted() {
    //this.Product.salePrice = this.Product.salePrice.toLocalString();
    this.salePrice = this.Product.salePrice;
    this.price = this.Product.price;
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
* {
  text-decoration: none;
}

button {
  background: none;
  border: none;
}

.productCard {
  position: relative;
  overflow: hidden;
  display: block;
  max-width: 250px;
  max-height: 600px;
  width: 100%;
  /* height: 100%; */
  margin: 0px auto;
}

.productsCardImage {
  position: relative;
  inset: 0px;
  box-sizing: border-box;
  padding: 0px;
  border: none;
  margin: auto;
  display: block;
  max-width: 300px;
  max-height: 400px;
  width: 100%;
  height: 75%;
}

.productCardInfo {
  display: flex;
  flex-direction: column;
  height: 100%;
  margin: 10px auto;
}

.productCardInfoTop {
  display: flex;
  flex-direction: row;
  margin-bottom: 10px;
}

.products-grid-item-info-mid {
  display: flex;
  flex-direction: row;
  margin-bottom: 10px;
}

.productCardInfoBottom {
  display: flex;
  flex-direction: column;
}

.productName {
  text-align: left;
  margin-bottom: 10px;
  color: black;
}

.brandName {
  width: 80%;
  text-align: left;
  color: black;
}

.priceBefore {
  /* font-weight: 700; */
  text-decoration: line-through;
  font-size: 15px;
  color: gray;
}

.priceAfter {
  font-weight: 700;
  color: black;
  font-size: 17px;
  margin-left: 12px;
}

.discount {
  position: relative;
  font-weight: 700;
  color: orange;
}

.products-grid-item-info-like-btn {
  position: absolute;
  right: 5px;
}
.salePrice {
  display: flex;
  flex-direction: row;
}
</style>
