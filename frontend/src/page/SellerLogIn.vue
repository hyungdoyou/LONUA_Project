<template>
  <section id="contents">
    <div class="sub-title-wrap">
      <h2 class="sub-title">Login</h2>
    </div>

    <div class="user-join">
      <div class="join-contents">
        <div class="selectUser">
          <label>
            <input
              type="radio"
              id="user"
              name="user"
              value="user"
              @click="selectUser()"
            />
            일반 회원 로그인
          </label>
          <label>
            <input
              type="radio"
              id="seller"
              name="user"
              value="seller"
              @click="selectSeller()"
              checked
            />
            판매자 로그인
          </label>
        </div>
        <div class="login-wrap">
          <form>
            <div class="input-group">
              <p>
                <label for="input_id">이메일 아이디</label>
                <input
                  type="text"
                  name="custId"
                  id="custId"
                  maxlength="50"
                  class="full_width"
                  v-model="sellerEmail"
                  placeholder="이메일 아이디를 @까지 정확하게 입력하세요"
                />
              </p>
              <p class="password">
                <label for="input_pw">비밀번호</label>
                <input
                  type="password"
                  name="custPw"
                  id="custPw"
                  maxlength="20"
                  v-model="sellerPassword"
                  placeholder="영문 대/소문자, 숫자, 특수문자를 반드시 포함하여 8자리 이상 입력"
                />
              </p>
              <!-- <span class="input-button checkbox">
                <input type="checkbox" name="idSave" id="idSave" value="Y" />
                <label for="idSave">이메일 아이디 저장</label>
              </span> -->
              <br />
            </div>
            <div class="btn-group">
              <button
                type="button"
                class="btn full_width black"
                @click="
                  sellerStore.sendSellerLoginData(sellerEmail, sellerPassword)
                "
              >
                로그인
              </button>
            </div>
          </form>
        </div>
        <div class="new-join-wrap">
          <div class="join-email">
            <p class="tit-join-email">
              일반 이메일 계정으로 LONUA를 이용해 보세요.
            </p>
            <div class="join-opt">
              <div class="btn-wrap">
                <a class="btn" href="/SellerSignUp">
                  <span id="joinEmailButton">이메일로 판매자 가입하기</span>
                </a>
              </div>
            </div>
          </div>
          <div class="join-sns">
            <p class="tit-join-sns">
              카카오 SNS계정으로 LONUA를 이용해 보세요.
            </p>
            <div class="join-opt">
              <div class="btn-wrap">
                <a
                  id="kakao-login-btn"
                  href="javascript:loginWithKakao()"
                  onclick="sendKakao()"
                  class="btn sns-login ty-kakao"
                >
                  <span>카카오로 시작하기</span>
                </a>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import { mapStores } from "pinia";
import { useSellerStore } from "../stores/useSellerStore";

export default {
  name: "SellerLogIn",
  methods: {
    selectUser() {
      this.$router.push({ path: "/UserLogIn" });
    },
    selectSeller() {
      this.$router.push({ path: "/SellerLogIn" });
    },
  },
  computed: {
    ...mapStores(useSellerStore),
  },
};
</script>

<style scoped>
form {
  margin: 0;
}

p {
  margin: 0;
}
input[type="text" i] {
  padding-block: 1px;
  padding-inline: 2px;
}

input {
  text-rendering: auto;
  color: fieldtext;
  letter-spacing: normal;
  word-spacing: normal;
  line-height: normal;
  text-transform: none;
  text-indent: 0px;
  text-shadow: none;
  display: inline-block;
  text-align: start;
  appearance: auto;
  -webkit-rtl-ordering: logical;
  cursor: text;
  background-color: field;
  margin: 0em;
  padding: 1px 0px;
  border-width: 2px;
  border-style: inset;
  border-color: -internal-light-dark(rgb(118, 118, 118), rgb(133, 133, 133));
  border-image: initial;
  padding-block: 1px;
  padding-inline: 2px;
}

a,
img,
input,
button,
select,
textarea {
  vertical-align: middle;
}

button,
input,
optgroup,
select,
textarea {
  font: inherit;
  margin: 0;
  color: inherit;
}

section {
  display: block;
}

.sub-title-wrap {
  padding-top: 55px;
  padding-bottom: 36px;
  position: relative;
  min-width: 1240px;
}

.sub-title {
  color: #000;
  font-family: "ProximaNova-Thin", "Noto Sans KR";
  font-size: 44px;
  line-height: 44px;
  text-align: center;
  text-transform: uppercase;
}

.user-join {
  width: 1240px;
  margin: 0 auto;
}

.user-join .join-contents {
  padding-top: 40px;
  border-top: 2px solid #171717;
}

.selectUser {
  text-align: center;
  margin-bottom: 40px;
}

.selectUser label {
  margin-right: 20px;
}

.selectUser input:checked + label {
  font-weight: bold;
}

.join-contents .join-sns {
  visibility: hidden;
}

.user-join .login-wrap {
  width: 735px;
  margin: 0 auto;
}

.user-join .login-wrap .input-group {
  float: left;
  width: 525px;
  padding-bottom: 16px;
  margin-bottom: 26px;
}

.user-join .input-group {
  position: relative;
}

.user-join .login-wrap .input-group p label {
  float: left;
  width: 105px;
  padding-bottom: 0;
  line-height: 40px;
}

.user-join .input-group p label {
  display: block;
  color: #333;
  font-size: 14px;
}

.user-join .input-group p input[type="text"] {
  margin-bottom: 0;
}

.user-join .login-wrap .input-group p input {
  float: left;
  width: 420px;
}

input[type="text"],
[type="password"] {
  height: 40px;
  line-height: 38px;
  padding-left: 20px;
  border: none;
  background-color: #f2f2f2;
  border: 1px solid #f2f2f2;
  font-size: 14px;
  font-family: "ProximaNova-Regular", "Noto Sans KR";
  outline: none;
}

.user-join .login-wrap .input-group p::after {
  display: block;
  clear: both;
  content: "";
}

.password {
  margin-top: 20px !important;
}

.user-join .login-wrap .input-group p input[type="password"] {
  margin-bottom: 17px;
}

.user-join .login-wrap .input-group .input-button {
  margin: 0 0 20px 105px;
}

span.input-button {
  display: inline-block;
  position: relative;
  margin-right: 50px;
}

span.input-button input[type="checkbox"] {
  position: absolute;
  top: 0;
  left: 0;
  width: 25px;
  height: 25px;
  z-index: 1;
  opacity: 0;
}

input[type="checkbox"] {
  padding: 0;
}

input[type="checkbox"],
input[type="radio"] {
  box-sizing: border-box;
}

span.input-button input[type="checkbox"] + label {
  display: block;
  color: #000;
  font-size: 14px;
  font-family: "ProximaNova-Regular", "Noto Sans KR";
  line-height: 25px;
  padding-left: 35px;
  position: relative;
}

span.input-button input[type="checkbox"] + label:before {
  background: url(//static.wconcept.co.kr/web/images/common/spr-input.png) 0 0
    no-repeat;
  content: "";
  display: block;
  width: 25px;
  height: 25px;
  position: absolute;
  top: 0;
  left: 0;
}

.user-join .login-wrap .btn-group {
  float: right;
  width: 190px;
}

.user-join .login-wrap .btn-group .btn {
  height: 100px;
}

.user-join .btn-group .btn {
  margin-bottom: 0;
}

.btn-group button.btn.full_width {
  width: 100%;
  font-size: 16px;
}

.btn-group button.btn.black {
  border-color: #000;
  background-color: #000;
  color: #fff;
}

.btn-group button.btn {
  display: inline-block;
  min-width: 180px;
  padding: 0 20px;
  height: 50px;
  text-align: center;
  line-height: 48px;
  border: 1px solid #333;
  background-color: #fff;
  color: #000;
  font-family: "ProximaNova-Semibold", "Apple SD Gothic Neo",
    "NotoSansKR-Medium", "Malgun Gothic", "맑은 고딕", sans-serif;
  font-size: 14px;
}

.user-join .btn-group .link {
  font-size: 0;
  margin-top: 20px;
  text-align: center;
  white-space: nowrap;
}

.user-join .btn-group .link li {
  display: inline-block;
  position: relative;
  white-space: nowrap;
}

.user-join .btn-group .link li a {
  font-size: 12px;
  padding: 5px 15px;
  color: #777;
  display: inline-block;
}

a {
  background-color: transparent;
}

.user-join .btn-group .link .second:before {
  position: absolute;
  top: 4px;
  left: 0;
  width: 1px;
  height: 12px;
  background: #e2e2e2;
  content: "";
}

.user-join .login-wrap:after {
  clear: both;
  display: block;
  content: "";
}

.join-email a.btn:hover {
  cursor: pointer;
}

label:hover {
  cursor: pointer;
}

input[type="radio"]:hover {
  cursor: pointer;
}

/* 여기부터 새로운 회원 가입 */
.new-join-wrap {
  border: 1px solid #ccc;
  border-width: 1px 0 1px 0;
}

.new-join-wrap .join-email {
  display: flex;
  justify-content: center;
  margin: 0 auto;
  padding: 50px 0;
  position: relative;
}

.new-join-wrap [class*="tit-join-"] {
  width: 354px;
  font-size: 14px;
  padding-top: 15px;
  color: #777;
}

.new-join-wrap .join-opt {
  width: 326px;
}

.new-join-wrap .join-opt .btn {
  width: 100%;
  display: block;
}

a.btn {
  display: inline-block;
  min-width: 180px;
  padding: 0 20px;
  height: 50px;
  text-align: center;
  line-height: 48px;
  border: 1px solid #333;
  background-color: #fff;
  color: #000;
  font-family: "ProximaNova-Semibold", "Apple SD Gothic Neo",
    "NotoSansKR-Medium", "Malgun Gothic", "맑은 고딕", sans-serif;
  font-size: 14px;
}

.new-join-wrap .join-sns {
  display: flex;
  justify-content: center;
  margin: 0 auto;
  padding: 50px 0;
  position: relative;
}

.new-join-wrap .join-sns:before {
  content: "";
  display: block;
  width: 100%;
  height: 1px;
  background: #f1f1f1;
  position: absolute;
  top: 0;
}

.new-join-wrap .tit-join-sns {
  color: #777;
}

.new-join-wrap [class*="tit-join-"] {
  width: 354px;
  font-size: 14px;
  padding-top: 15px;
}

.new-join-wrap .sns-login.ty-kakao {
  background: #fae100;
}

.new-join-wrap .sns-login {
  border-width: 0;
  height: 50px;
  font-size: 14px;
}

.new-join-wrap .sns-login.ty-kakao > span {
  display: flex;
  justify-content: center;
  align-items: center;
  line-height: 50px;
}

.new-join-wrap .sns-login.ty-kakao > span:before {
  content: "";
  display: inline-block;
  width: 32px;
  height: 32px;
  background: url(//static.wconcept.co.kr/web/images/common/svg/ico_kakao_32.svg)
    no-repeat 0 0;
  background-size: contain;
  margin-right: 10px;
}

.new-join-wrap .sns-type {
  display: flex;
  justify-content: center;
  border-width: 0;
  margin-top: 24px;
}

.new-join-wrap .sns-type li {
  border-width: 0;
  float: initial;
  width: auto;
  margin: 0 10px;
}

.new-join-wrap .sns-type li a {
  display: block;
  position: relative;
  width: 46px;
  height: 46px;
  font-size: 0;
  text-indent: -9999px;
  margin: 0 auto;
  padding-top: 0;
}

.new-join-wrap .sns-type li a:before {
  content: "";
  display: block;
  width: 46px;
  height: 46px;
}
.user-join .sns-type:after {
  display: block;
  clear: both;
  content: "";
}
</style>
