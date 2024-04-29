import { defineStore } from "pinia";
import axios from "axios";


const backend = 'http://www.lonuamall.kro.kr/api';
// const backend = "http://localhost:8080";
export const useProductStore = defineStore("product", {
  state: () => ({ idx:0 , product:"", isLoading: true}),
  actions: {
    async addCartAxios() {
      
      let data = {
        // 변수를 어떻게 받아올것인가
        productIdx: this.idx,
      };
      // if (storedToken == null) {
      //   window.location.href = "/UserLogIn";
      // }

      let request = JSON.stringify(data);

      let token = sessionStorage.getItem("token");
      
      try {
        await axios.post(backend + "/cart/register", request, {
          headers: {
            "Content-Type": "application/json",
            Authorization: "Bearer " + token,
          },
        });

        this.isLoading = false;
        
      } catch (error) {
        console.error("에러 발생:", error);
        // 에러 처리 로직 추가
      }
    },
    async getProduct(idx) {
    // itemdidx를 가져와야한다 지금은 임의적으로 1로 했다
      let response = await axios.get(backend + "/product/" +idx);

      try {
        this.product = response.data.result;
        this.isLoading = false;
      } catch {
        return "error!";
      }
    },
  },
});
