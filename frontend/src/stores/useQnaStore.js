import { defineStore } from "pinia";
import axios from "axios";

const backend = 'http://www.lonuamall.kro.kr/api';
// const backend = "http://localhost:8080";
const storedToken = sessionStorage.getItem("token");

export const useQnaStore = defineStore("qna", {
  state: () => ({ qnas: [], productQnas: [], isQuestionExist: true }),
  actions: {
    async getQna() {
      try {
        const response = await axios.get(backend + "/question/list/1/10", {
          headers: {
            Authorization: `Bearer ${storedToken}`,
          },
        });

        this.qnas = response.data.result;
        if (response.data.result.length !== 0) {
          this.isQuestionExist = false;
        }
      } catch (error) {
        console.error("에러 발생:", error);
        // 에러 처리 로직 추가
      }
    },
    async getProductQna(idx) {
      try {
        const response = await axios.get(
          backend + "/question/productQnA/list/" + idx + "/1/50"
        );
        
        this.productQnas = response.data.result;
      } catch (error) {
        console.error("에러 발생:", error);
        // 에러 처리 로직 추가
      }
    },
    async registerProductQna(data) {
      let token = sessionStorage.getItem("token");
      let postRegisterQuestionReq = JSON.stringify(data);
      try {
        await axios.post(
          backend + "/question/register", postRegisterQuestionReq,
          {
            headers: {
              "Content-Type": "application/json",
              'Authorization': "Bearer " + token,
            },
          }
        );
        window.location.reload();
        
      } catch (error) {
        console.error("에러 발생:", error);
        // 에러 처리 로직 추가
      }
    },
    async deleteQuestion(questionIdx) {
      try {
        const response = await axios.delete(
          backend + "/question/delete/" + questionIdx,
          {
            headers: {
              Authorization: `Bearer ${storedToken}`,
            },
          }
        );
        if (response.data.isSuccess === true) {
          alert("질문이 삭제되었습니다.");
          window.location.href = "/UserQuestion";
        } else {
          alert("질문을 삭제할 수 없습니다.");
        }
      } catch (e) {
        console.error(e);
      }
    },
  },
});
