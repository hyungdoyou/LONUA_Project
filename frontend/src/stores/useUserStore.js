import { defineStore } from "pinia";
import axios from "axios";

const backend = 'http://www.lonuamall.kro.kr/api';
// const backend = "http://localhost:8080";
const storedToken = sessionStorage.getItem("token");

export const useUserStore = defineStore("user", {
  state: () => ({
    user: {},
    isSuccess: false,
    mileage: 0,
    isLoading: false,
  }),
  actions: {
    // 회원정보 조회
    async getUserInfo() {
      try {
        let response = await axios.get(backend + "/user/read", {
          headers: {
            Authorization: `Bearer ${storedToken}`,
            "Content-Type": "application/json",
          },
        });

        this.user = response.data.result;
        this.mileage = response.data.result.mileage;
      } catch (e) {
        console.log(e);
        alert("잘못된 요청입니다.");
      }
    },
    // 로그인
    async sendUserLoginData(email, password) {
      let loginUser = { email: email, password: password };
      try {
        let response = await axios.post(backend + "/user/login", loginUser, {
          headers: {
            "Content-Type": "application/json",
          },
        });
        if (response.data.result.token !== null) {
          const token = response.data.result.token;
          sessionStorage.removeItem("token");
          sessionStorage.setItem("token", token);

          window.location.href = "/";
        }
      } catch (e) {
        this.user.email = "";
        this.user.password = "";
        sessionStorage.removeItem("token");
        alert(
          "이메일과 비밀번호가 일치하지 않습니다. 또한, 이메일 인증을 완료하지 않으신 분은 이메일 인증을 완료해주세요."
        );
      }
    },

    // 카카오 로그인
    sendKakao() {
      window.Kakao.Auth.authorize({
        redirectUri: "http://www.lonuamall.kro.kr/KakaoLogIn",
        // redirectUri: "http://localhost:8081/KakaoLogIn",
      });
    },

    // 회원가입
    async sendSignUpData(user) {
      try {
        this.isLoading = true;

        let response = await axios.post(backend + "/user/signup", user, {
          headers: {
            "Content-Type": "application/json",
          },
        });

        if (response.data.isSuccess === true) {
          this.isSuccess = true;
        } else {
          this.isSuccess = false;
        }
      } catch (e) {
        console.log(e);
        this.isSuccess = false;
        alert("잘못된 요청입니다.");
      } finally {
        this.isLoading = false;
      }
    },
    // 회원정보 수정-1(비밀번호 확인)
    async checkPassword(user) {
      try {
        let response = await axios.post(backend + "/user/checkPw", user, {
          headers: {
            Authorization: `Bearer ${storedToken}`,
            "Content-Type": "application/json",
          },
        });

        if (response.data === true) {
          this.isSuccess = true;
        } else {
          this.isSuccess = false;
        }
      } catch (e) {
        console.log(e);
        this.isSuccess = false;
        this.user.password = "";
        alert("비밀번호가 일치하지 않습니다. 다시 입력해주세요.");
      }
    },

    // 회원정보 수정-2(서버 요청)
    async sendUpdateData(updateData) {
      try {
        let data = {
          userPassword: updateData.userPassword || null,
          userPhoneNumber: updateData.userPhoneNumber || null,
          userAddr: updateData.userAddr || null,
          preferStyle: updateData.preferStyle,
          upperType: updateData.upperType || null,
          lowerType: updateData.lowerType || null,
        };
        let response = await axios.patch(backend + "/user/update", data, {
          headers: {
            Authorization: `Bearer ${storedToken}`,
            "Content-Type": "application/json",
          },
        });
        if (response.data.isSuccess === true) {
          alert("회원정보를 수정하였습니다.");
          window.location.href = "/MainMyPage";
        }
      } catch (e) {
        alert("잘못된 요청입니다.");
      }
    },

    // 회원탈퇴
    async submitCancel() {
      try {
        let response = await axios.get(backend + "/user/cancel", {
          headers: {
            Authorization: `Bearer ${storedToken}`,
            "Content-Type": "application/json",
          },
        });

        if (response.data.isSuccess === true) {
          alert(
            '회원 탈퇴가 성공적으로 처리되었습니다. 그동안 "LONUA" 를 이용해주셔서 감사합니다.'
          );
          sessionStorage.removeItem("token");
          window.location.href = "/";
        }
      } catch (e) {
        console.log(e);
        alert("잘못된 요청입니다.");
      }
    },
  },
});
