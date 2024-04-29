import { defineStore } from "pinia";
import axios from "axios";

const backend = 'http://www.lonuamall.kro.kr/api';
// const backend = "http://localhost:8080";
const storedToken = sessionStorage.getItem("token");
export const useLikesStore = defineStore("likes", {
  state: () => ({
    isLoading: false,
    likes: [],
    indexList: [],
    likesList: [],
    isSuccess: false,
    isLikesExist: true,
  }),
  actions: {
    async getMainLikeList() {
      this.isLoading = true;

      await axios
        .get(backend + "/likes/list", {
          headers: {
            "Content-Type": "application/json",
          },
        })
        .then((res) => {
          console.log("getLikeList 성공!");
          this.likes = res.data.result;
          this.likesList = res.data.result;
          this.likes.map((product) => {
            this.indexList.push(product.productIdx);
          });
        })
        .catch((res) => {
          console.log("getLikeList 실패!");
          console.log(res);
        });
      console.log(this.indexList);
      this.isLoading = false;
    },
    async getLikeList() {
      this.isLoading = true;

      await axios
        .get(backend + "/likes/list", {
          headers: {
            Authorization: `Bearer ${storedToken}`,
            "Content-Type": "application/json",
          },
        })
        .then((res) => {
          console.log("getLikeList 성공!");
          if (res.data.result.length !== 0) {
            this.isLikesExist = false;
          }
          this.likes = res.data.result;
          this.likesList = res.data.result;
          this.likes.map((product) => {
            this.indexList.push(product.productIdx);
          });
        })
        .catch((res) => {
          console.log("getLikeList 실패!");
          console.log(res);
        });
      console.log(this.indexList);
      this.isLoading = false;
    },
    async addLike(productIdx) {
      await axios
        .get(backend + "/likes/" + productIdx, {
          headers: {
            Authorization: `Bearer ${storedToken}`,
            "Content-Type": "application/json",
          },
        })
        .then((res) => {
          console.log(res);
        })
        .catch((res) => {
          console.log("망했다! : " + res);
          alert("로그인 후 시도해주세요");
        });
    },

    async cancelLike(productIdx) {
      await axios
        .delete(backend + "/likes/cancel/" + productIdx, {
          headers: {
            Authorization: `Bearer ${storedToken}`,
            "Content-Type": "application/json",
          },
        })
        .then((res) => {
          console.log(res);
          this.productPage = res.data.result;
          this.isSuccess = true;
        })
        .catch((res) => {
          console.log("망했다! : " + res);
          alert("로그인 후 시도해주세요");
          this.isSuccess = false;
        });
    },
  },
});
