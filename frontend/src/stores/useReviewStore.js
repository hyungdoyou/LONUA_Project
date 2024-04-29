import { defineStore } from "pinia";
import axios from "axios";

const backend = "http://www.lonuamall.kro.kr/api";
// const backend = "http://localhost:8080";
const storedToken = sessionStorage.getItem("token");

export const useReviewStore = defineStore("review", {
  state: () => ({ reviews: [], productIdx: 0, isReviewExist: true, isSuccess: false }),
  actions: {
    async getReviewList() {
      try {
        let response = await axios.get(backend + "/review/list", {
          headers: {
            Authorization: `Bearer ${storedToken}`,
          },
        });
        this.reviews = response.data.result;
        if (response.data.result.length !== 0) {
          this.isReviewExist = false;
        }
      } catch (e) {
        console.log(e);
      }
    },
    async getReview(idx) {
      try {
        let response = await axios.get(backend + "/review/list/" + idx, {
          headers: {
            Authorization: `Bearer ${storedToken}`,
          },
        });
        // console.log(response.data.result)
        this.reviews = response.data.result;
      } catch (error) {
        console.error("에러 발생:", error);
        return error;
        // 에러 처리 로직 추가
      }
    },
    async submitReview(review, reviewPhoto) {
      try{
      let formData = new FormData();
        let data = {
          productIdx: this.productIdx, //2
          reviewContent: review.reviewContent,
          evaluation: review.evaluation,
        };
        let json = JSON.stringify(data);
        formData.append(
          "review",
          new Blob([json], { type: "application/json" })
        );
        formData.append("reviewPhoto", reviewPhoto);
        let response = await axios.post(
            "http://www.lonuamall.kro.kr/api/review/register",
            formData,
            {
              headers: {
                Authorization: `Bearer ${storedToken}`,
                "Content-Type": "multipart/form-data",
              },
            }
          );
        if(response.data.isSuccess === true){
          alert("리뷰작성이 완료됐습니다.");
          window.location.href = "/UserReview";
        }
      } catch (error) {
        console.error(error);
      }
    },
    async deleteReview(reviewIdx) {
      try {
        const response = await axios.delete(
          backend + "/review/delete/" + reviewIdx,
          {
            headers: {
              Authorization: `Bearer ${storedToken}`,
            },
          }
        );
        if (response.data.isSuccess === true) {
          alert("리뷰가 삭제되었습니다.");
          window.location.href = "/UserReview";
        } else {
          alert("리뷰를 삭제할 수 없습니다.");
        }
      } catch (e) {
        console.error(e);
      }
    },
  },
});
