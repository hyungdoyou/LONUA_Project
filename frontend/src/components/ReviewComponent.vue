<template>
            <tr class="orders-detail" :id="reviews.reviewIdx">
              <td class="product" :id="reviews.productIdx">
                <div class="item_aside">
                  <div class="img">
                    <img
                      :src="reviews.productImage"
                    />
                  </div>
                </div>
                <div class="item_info">
                  <p class="brand">{{reviews.brandName}}</p>
                  <p class="product_name multiline">
                    {{reviews.productName}}
                  </p>
                </div>
              </td>
              <td>{{reviews.updatedAt}}</td>
              <td class="product">
                <div class="item_aside-review">
                  <div class="img">
                    <img
                      :src="reviews.reviewPhoto"
                    />
                  </div>
                </div>
                <div class="item_info">
                  <p class="product_name multiline">
                    {{reviews.reviewContent}}
                  </p>
                </div>
              </td>
              <td class="evaulation">{{reviews.evaluation}} 점</td>
              <td class="delete">
                <button @click="deleteReview" class="review-btn">리뷰 삭제</button>
              </td>
            </tr>
</template>

<script>
import { mapStores } from "pinia";
import { useReviewStore } from "@/stores/useReviewStore.js";
export default {
  name: "ReviewComponent",
  props: ["reviews"],
  computed: {
    ...mapStores(useReviewStore),
  },
  methods: {
    deleteReview() {
        console.log("delete");
        console.log(this.reviews.reviewIdx)
      this.reviewStore.deleteReview(this.reviews.reviewIdx);
    },
  }
};
</script>

<style scoped>

.orders-detail .product {
  display: flex;
  align-items: center;
}

.orders-detail .product .item_aside {
  width: 150px;
  margin-right: -24px;
}

.orders-detail .product .item_aside-review {
  width: 150px;
  margin-right: -24px;
  margin-left: 40px;
}

.orders-detail .product .img {
  width: 75px; /* 이미지의 원하는 크기로 조절 */
  overflow: hidden;
}

.orders-detail .product img {
  display: block;
  width: 100%;
  height: auto;
}

.orders-detail .product .item_info {
  width: calc(100% - 96px);
  margin-top: 6px;
}

.orders-detail .product .item_info p {
  margin: 0;
  margin-top: 16px;
  margin-bottom: -3px;
}

.orders-detail {
  height: 130px;
}

.orders-detail .product .img {
  max-width: 100%;
  margin-top: 3px;
  margin-bottom: -10px;
  margin-left: 5px;
  margin-right: -10px;
}

.orders-detail .product img {
  display: block;
  width: 100%;
  height: auto;
  max-height: 100%;
}

td.evaulation {
    text-align: center;
}

td.delete {
    text-align: center;
}

.orders-detail .review-btn {
  background-color: red;
  color: white;
  padding: 8px 16px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.orders-detail .review-btn:hover {
  background-color: red;
}
</style>
