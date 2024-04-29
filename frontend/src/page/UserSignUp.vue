<template>
  <div class="loadingio-spinner-spinner" v-if="userStore.isLoading">
    <div class="ldio-f4nnk2ltl0v">
      <div></div>
      <div></div>
      <div></div>
      <div></div>
      <div></div>
      <div></div>
      <div></div>
      <div></div>
      <div></div>
      <div></div>
      <div></div>
      <div></div>
    </div>
  </div>
  <form>
    <section id="contents">
      <div class="sub-title-wrap">
        <h2 class="sub-title">JOIN USER</h2>
      </div>
      <div class="user-join">
        <p class="table-notice">
          <span>*</span>
          필수 입력 항목
        </p>
        <table>
          <colgroup>
            <col style="width: 295px" />
          </colgroup>
          <tbody>
            <tr>
              <th>
                이메일 아이디
                <span>*</span>
              </th>
              <td>
                <div class="email va_m">
                  <input
                    type="text"
                    name="custid"
                    id="custEmailId"
                    placeholder="example@example.com"
                    v-model="user.userEmail"
                  />
                  <div id="validation1" class="incorrect">
                    <label id="chkcustid"
                      >본인 소유의 이메일을 입력해 주세요.</label
                    >
                  </div>
                </div>
              </td>
            </tr>
            <tr>
              <th>
                비밀번호
                <span>*</span>
              </th>
              <td>
                <input
                  type="password"
                  name="joinPw"
                  id="joinPw"
                  minlength="8"
                  maxlength="45"
                  placeholder="영문 대/소문자+숫자+특수문자 조합 8자리 이상"
                  v-model="user.userPassword"
                />
              </td>
            </tr>
            <tr>
              <th>
                비밀번호 확인
                <span>*</span>
              </th>
              <td>
                <input
                  type="password"
                  name="joinPwConfirm"
                  id="joinPwConfirm"
                  minlength="8"
                  maxlength="45"
                />
              </td>
            </tr>
            <tr>
              <th>
                이 름
                <span>*</span>
              </th>
              <td>
                <p class="join_name va_m">
                  <input
                    type="text"
                    name="joinName"
                    id="joinName"
                    maxlength="45"
                    placeholder="ex ) 홍길동"
                    v-model="user.userName"
                  />
                </p>
              </td>
            </tr>
            <tr>
              <th>
                생년 월일
                <span>*</span>
              </th>
              <td>
                <p class="join_name va_m">
                  <input
                    type="text"
                    name="joinBirth"
                    id="joinBirth"
                    maxlength="10"
                    placeholder="ex ) 1991-20-23"
                    v-model="user.userBirth"
                  />
                </p>
              </td>
            </tr>
            <tr>
              <th>
                성 별
                <span>*</span>
              </th>
              <td>
                <div class="genderOptions">
                  <label class="toggle">
                    <input
                      type="radio"
                      name="gender"
                      value="M"
                      v-model="user.userGender"
                    />
                    남자
                  </label>
                  <label class="toggle">
                    <input
                      type="radio"
                      name="gender"
                      value="W"
                      v-model="user.userGender"
                    />
                    여자
                  </label>
                </div>
              </td>
            </tr>
            <tr>
              <th>
                휴대전화 번호
                <span>*</span>
              </th>
              <td>
                <p class="join_name va_m">
                  <input
                    type="text"
                    name="joinPhoneNumber"
                    id="joinPhoneNumber"
                    maxlength="13"
                    placeholder="ex ) 010-0000-0000"
                    v-model="user.userPhoneNumber"
                  />
                </p>
              </td>
            </tr>
            <tr>
              <th>
                주 소
                <span>*</span>
              </th>
              <td>
                <p class="join_name va_m">
                  <input
                    type="text"
                    name="joinAddr"
                    id="joinAddr"
                    maxlength="45"
                    placeholder="ex ) 서울시 강동구 보라매로 47"
                    v-model="user.userAddr"
                  />
                </p>
              </td>
            </tr>
            <tr>
              <th>
                선호 스타일
                <span>*</span>
              </th>
              <td>
                <div class="styleOptions">
                  <label>
                    <input
                      type="radio"
                      name="style"
                      value="캐주얼"
                      v-model="user.preferStyle"
                    />
                    캐주얼
                  </label>
                  <label>
                    <input
                      type="radio"
                      name="style"
                      value="시크"
                      v-model="user.preferStyle"
                    />
                    시크
                  </label>
                  <label>
                    <input
                      type="radio"
                      name="style"
                      value="댄디"
                      v-model="user.preferStyle"
                    />
                    댄디
                  </label>
                  <label>
                    <input
                      type="radio"
                      name="style"
                      value="스트릿"
                      v-model="user.preferStyle"
                    />
                    스트릿
                  </label>
                  <label>
                    <input
                      type="radio"
                      name="style"
                      value="비지니스캐주얼"
                      v-model="user.preferStyle"
                    />
                    비지니스캐주얼
                  </label>
                  <label>
                    <input
                      type="radio"
                      name="style"
                      value="힙합"
                      v-model="user.preferStyle"
                    />
                    힙합
                  </label>
                  <label>
                    <input
                      type="radio"
                      name="style"
                      value="오피스"
                      v-model="user.preferStyle"
                    />
                    오피스
                  </label>
                  <label>
                    <input
                      type="radio"
                      name="style"
                      value="스포츠"
                      v-model="user.preferStyle"
                    />
                    스포츠
                  </label>
                </div>
              </td>
            </tr>
            <tr>
              <th>
                상체 유형
                <span>*</span>
              </th>
              <td>
                <div class="upperTypeOptions">
                  <label>
                    <input
                      type="radio"
                      name="upperType"
                      value="1"
                      v-model="user.upperType"
                    />
                    상체 마름
                  </label>
                  <label>
                    <input
                      type="radio"
                      name="upperType"
                      value="2"
                      v-model="user.upperType"
                    />
                    상체 보통
                  </label>
                  <label>
                    <input
                      type="radio"
                      name="upperType"
                      value="3"
                      v-model="user.upperType"
                    />
                    상체 비만
                  </label>
                </div>
              </td>
            </tr>
            <tr>
              <th>
                하체 유형
                <span>*</span>
              </th>
              <td>
                <div class="lowerTypeOptions">
                  <label>
                    <input
                      type="radio"
                      name="lowerType"
                      value="1"
                      v-model="user.lowerType"
                    />
                    하체 마름
                  </label>
                  <label>
                    <input
                      type="radio"
                      name="lowerType"
                      value="2"
                      v-model="user.lowerType"
                    />
                    하체 보통
                  </label>
                  <label>
                    <input
                      type="radio"
                      name="lowerType"
                      value="3"
                      v-model="user.lowerType"
                    />
                    하체 비만
                  </label>
                </div>
              </td>
            </tr>
          </tbody>
        </table>
        <div class="join_agreement">
          <p class="all_check_desc">
            <span class="input_button checkbox">
              <input
                type="checkbox"
                v-model="isChecked"
                @click="checkAll()"
                id="checkAll2"
                placeholder=""
                data-gtm-form-interact-field-id="0"
              />
              <label for="checkAll2">
                전체 동의합니다.
                <br />
                <span>
                  전체동의는 필수 및 선택정보에 대한 동의도 포함되어 있으며,
                  개별적으로도 동의를 선택하실 수 있습니다.
                  <br />
                  선택항목에 대한 동의를 거부하는 경우에도 회원가입 서비스는
                  이용 가능합니다.
                </span>
              </label>
            </span>
          </p>
          <ul>
            <li>
              <span class="input_button checkbox">
                <input
                  type="checkbox"
                  v-model="checkbox1"
                  id="checkAgree4"
                  name="all_agree04"
                />
                <label for="checkAgree4">만 14세 이상입니다. (필수)</label>
              </span>
            </li>
            <li>
              <span class="input_button checkbox">
                <input
                  type="checkbox"
                  v-model="checkbox2"
                  id="checkAgree1"
                  name="all_agree01"
                />
                <label for="checkAgree1">이용약관 동의(필수)</label>
              </span>
              <button type="button" class="micro_btn open-layer open-useinfo">
                내용보기
              </button>
            </li>
            <li>
              <span class="input_button checkbox">
                <input
                  type="checkbox"
                  v-model="checkbox3"
                  id="checkAgree2"
                  name="all_agree02"
                />
                <label for="checkAgree2"
                  >개인정보 수집 및 이용에 대한 동의(필수)</label
                >
              </span>
              <button type="button" class="micro_btn open-layer open-collect">
                내용보기
              </button>
            </li>
            <li>
              <span class="input_button checkbox">
                <input
                  type="checkbox"
                  v-model="checkbox4"
                  id="checkAgree3"
                  name="all_agree03"
                />
                <label for="checkAgree3">개인정보 수집 및 이용안내(선택)</label>
              </span>
              <button type="button" class="micro_btn open-layer open-collect2">
                내용보기
              </button>
            </li>
          </ul>
        </div>
        <div class="join-btn-wrap">
          <button
            type="button"
            id="btnCancel"
            name="button"
            class="btn"
            @click="goBack()"
          >
            취소
          </button>
          <button
            type="button"
            id="btnConfirm"
            name="button"
            class="btn black"
            @click="sendSignUpData()"
          >
            가입하기
          </button>
        </div>
      </div>
    </section>
  </form>
</template>

<script>
import { mapStores } from "pinia";
import { useUserStore } from "../stores/useUserStore";

export default {
  name: "UserSignUp",
  computed: {
    ...mapStores(useUserStore),
  },
  data() {
    return {
      user: {
        userEmail: "",
        userPassword: "",
        userName: "",
        userBirth: "",
        userGender: null,
        userPhoneNumber: "",
        userAddr: "",
        preferStyle: null,
        upperType: null,
        lowerType: null,
      },
      isChecked: false,
      checkbox1: false,
      checkbox2: false,
      checkbox3: false,
      checkbox4: false,
    };
  },
  watch: {
    checkbox1() {
      this.isChecked =
        this.checkbox1 && this.checkbox2 && this.checkbox3 && this.checkbox4;
    },
    checkbox2() {
      this.isChecked =
        this.checkbox1 && this.checkbox2 && this.checkbox3 && this.checkbox4;
    },
    checkbox3() {
      this.isChecked =
        this.checkbox1 && this.checkbox2 && this.checkbox3 && this.checkbox4;
    },
    checkbox4() {
      this.isChecked =
        this.checkbox1 && this.checkbox2 && this.checkbox3 && this.checkbox4;
    },
  },
  methods: {
    goBack() {
      window.location.href = "/UserLogIn";
    },
    checkAll() {
      this.checkbox1 = !this.checkbox1;
      this.checkbox2 = !this.checkbox2;
      this.checkbox3 = !this.checkbox3;
      this.checkbox4 = !this.checkbox4;
    },
    async sendSignUpData() {
      await this.userStore.sendSignUpData(this.user);
      if (this.userStore.isSuccess) {
        this.$router.push({ path: "/UserSignUpSuccess" });
      }
    },
  },
};
</script>

<style scoped>
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

.user-join p.table-notice {
  margin-top: -26px;
}

p.table-notice {
  font-size: 12px;
  font-family: "Noto Sans KR";
  color: #666;
  vertical-align: middle;
  text-align: right;
  margin-bottom: 3px;
}

p.table-notice span {
  color: #ff1414;
  vertical-align: middle;
  display: inline-block;
  padding-top: 2px;
}

table {
  border-top: 2px solid #171717;
  border-bottom: 1px solid #171717;
  table-layout: fixed;
  margin-bottom: 60px;
  width: 100%;
  border-collapse: separate;
  border-spacing: 0;
}

table tbody th {
  height: 68px;
  padding-left: 43px;
  font-family: "ProximaNova-Regular", "Noto Sans KR";
  color: #333;
  font-size: 14px;
  border-top: 1px solid #d9d9d9;
  text-align: left;
}

table tbody th span {
  color: #ff1414;
  display: inline-block;
  padding-top: 2px;
}

table tbody td {
  padding: 0 19px;
  color: #000;
  font-size: 14px;
  border-top: 1px solid #d9d9d9;
}

.va_m {
  vertical-align: middle;
}

table tbody td .email .va_m {
  display: inline-block;
}

.phone .select-list-box,
.phone input,
.phone span,
.email .select-list-box,
.email input,
.email span {
  float: left;
}

input {
  vertical-align: middle;
}

input[type="text"],
input[type="password"] {
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

.email .txt_email {
  display: inline-block;
  width: 22px;
  line-height: 40px;
  text-align: center;
}

.email .duplicate_email:read-only {
  color: #9d9d9d;
}

.email .duplicate_email {
  margin-right: 10px;
}

select {
  vertical-align: middle;
  text-transform: none;
  font: inherit;
  margin: 0;
  color: inherit;
}

.select-list-box {
  position: relative;
  box-sizing: border-box;
}

/* 메일 선택 시 바뀌게 되는 스타일 */

.select-list-box .selected-headline.select-list-selected {
  background-color: #fff;
  border: 1px solid #000;
}

.select-list-box .selected-headline {
  outline: none;
  height: 40px;
  background-color: #f2f2f2;
  border: 1px solid #f2f2f2;
  line-height: 38px;
  color: #010101;
  font-size: 14px;
  font-family: "ProximaNova-Regular", "Noto Sans KR";
  text-indent: 20px;
  display: block;
  position: relative;
  box-sizing: border-box;
}

.select-list-box .selected-headline > span {
  display: block;
  height: 38px;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
  padding-right: 25.5px;
}

.select-list-box .selected-headline:after {
  content: "";
  display: block;
  position: absolute;
  top: 17px;
  right: 20px;
  width: 11px;
  height: 7px;
  background: url(//static.wconcept.co.kr/web/images/common/spr-input.png) 0 -60px
    no-repeat;
}

.select-list-box .select-list {
  position: absolute;
  top: 40px;
  left: 0;
  width: 100%;
  border: 1px solid #000;
  background-color: #fff;
  z-index: 10;
  overflow: hidden;
  overflow-y: auto;
}

.select-list-box .select-list li a {
  display: block;
  line-height: 40px;
  height: 40px;
  color: #000;
  font-size: 14px;
  font-family: "Noto Sans KR";
  padding: 0 19px;
  -ms-text-overflow: ellipsis;
  text-overflow: ellipsis;
  white-space: nowrap;
  -ms-word-wrap: normal;
  word-wrap: normal;
  overflow: hidden;
}

a {
  text-decoration: none;
  background-color: transparent;
  vertical-align: middle;
}

.phone:after,
.email:after {
  display: block;
  clear: both;
  content: "";
}

table tbody td .incorrect {
  font-size: 12px;
  color: #fa5500;
  margin-top: 14px;
}

table tbody td .incorrect:before {
  content: "";
  background: url(//static.wconcept.co.kr/web/images/common/svg/ico_incorrect_p_11.svg)
    no-repeat center/100%;
  width: 11px;
  height: 11px;
  display: inline-block;
  margin: 1px 4px 0 16px;
  vertical-align: text-top;
}

table tbody td .incorrect {
  display: inline-block;
}

table tbody td input[type="text"],
table tbody td textarea,
table tbody td input[type="password"],
table tbody td input[type="tel"],
table tbody td input[type="number"] {
  width: 400px;
}

.gender-options {
  display: flex;
}

.gender-options label {
  margin-right: 20px;
}

.gender-options input {
  margin-right: 5px;
}

.style-options {
  display: flex;
}

.style-options label {
  margin-right: 10px;
}

.style-options input {
  margin-right: 5px;
}

.type-options {
  display: flex;
}

.type-options label {
  margin-right: 20px;
}

.type-options input {
  margin-right: 5px;
}

.user-join .join_agreement {
  margin-bottom: 60px;
}

.user-join .join_agreement p.all_check_desc {
  height: auto;
  padding-bottom: 20px;
  padding-top: 0;
}

.user-join .join_agreement > p {
  padding-left: 40px;
}

span.input_button {
  display: inline-block;
  position: relative;
  margin-right: 50px;
}

span.input_button input[type="radio"],
span.input_button input[type="checkbox"] {
  position: absolute;
  top: 0;
  left: 0;
  width: 25px;
  height: 25px;
  z-index: 1;
  opacity: 0;
}

.user-join .join_agreement p.all_check_desc label {
  font-size: 16px;
}

span.input_button input[type="radio"] + label,
span.input_button input[type="checkbox"] + label {
  display: block;
  color: #000;
  font-size: 14px;
  font-family: "ProximaNova-Regular", "Noto Sans KR";
  line-height: 25px;
  padding-left: 35px;
  position: relative;
}

span.input_button input[type="radio"] + label:before,
span.input_button input[type="checkbox"] + label:before {
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

.user-join .join_agreement p.all_check_desc label span {
  font-family: "Noto Sans KR";
  font-size: 14px;
  line-height: 25px;
  color: #666;
  display: block;
  margin-top: 5px;
}

.user-join .join_agreement ul li {
  position: relative;
  height: 50px;
  margin-bottom: 1px;
  background-color: #f2f2f2;
  padding-top: 13px;
  padding-left: 40px;
}

.user-join .join_agreement ul li button {
  position: absolute;
  top: 14px;
  right: 40px;
}

button.micro_btn {
  display: inline-block;
  min-width: 60px;
  padding: 0 8px;
  height: 20px;
  line-height: 18px;
  font-family: "ProximaNova-Regular", "Noto Sans KR";
  font-size: 12px;
  color: #000;
  border: 1px solid #333;
  background-color: #fff;
  text-align: center;
}

/* 동의 체크박스 표시 */
span.input_button.checkbox input[type="checkbox"]:checked + label:before {
  background-position: -30px 0;
}

span.input_button.checkbox label.checked:before {
  background-position: -30px 0;
}

.user-join .join-btn-wrap {
  text-align: center;
}

.user-join .join-btn-wrap .btn {
  margin: 0 8px;
}

.user-join .join-btn-wrap button.btn {
  display: inline-block;
  min-width: 180px;
  height: 50px;
  text-align: center;
  line-height: 48px;
  background-color: rgb(255, 255, 255);
  color: rgb(0, 0, 0);
  font-family: ProximaNova-Semibold, "Apple SD Gothic Neo", NotoSansKR-Medium,
    "Malgun Gothic", "맑은 고딕", sans-serif;
  font-size: 14px;
  padding: 0px 20px;
  border-width: 1px;
  border-style: solid;
  border-color: rgb(51, 51, 51);
  border-image: initial;
}

.user-join .join-btn-wrap button.btn.black {
  border-color: #000;
  background-color: #000;
  color: #fff;
}
@keyframes ldio-f4nnk2ltl0v {
  0% {
    opacity: 1;
  }
  100% {
    opacity: 0;
  }
}
.ldio-f4nnk2ltl0v div {
  position: fixed;
  top: 30%;
  left: 50%;
  transform: translate(-50%, -50%);
  z-index: 999; /* 다른 요소 위에 표시하기 위한 z-index 값 */
  animation: ldio-f4nnk2ltl0v linear 1s infinite;
  background: #fe718d;
  width: 18.240000000000002px;
  height: 36.480000000000004px;
  border-radius: 9.120000000000001px / 18.240000000000002px;
  transform-origin: 9.120000000000001px 79.04px;
}
.ldio-f4nnk2ltl0v div:nth-child(1) {
  transform: rotate(0deg);
  animation-delay: -0.9166666666666666s;
  background: #fe718d;
}
.ldio-f4nnk2ltl0v div:nth-child(2) {
  transform: rotate(30deg);
  animation-delay: -0.8333333333333334s;
  background: #f47e60;
}
.ldio-f4nnk2ltl0v div:nth-child(3) {
  transform: rotate(60deg);
  animation-delay: -0.75s;
  background: #f8b26a;
}
.ldio-f4nnk2ltl0v div:nth-child(4) {
  transform: rotate(90deg);
  animation-delay: -0.6666666666666666s;
  background: #abbd81;
}
.ldio-f4nnk2ltl0v div:nth-child(5) {
  transform: rotate(120deg);
  animation-delay: -0.5833333333333334s;
  background: #849b87;
}
.ldio-f4nnk2ltl0v div:nth-child(6) {
  transform: rotate(150deg);
  animation-delay: -0.5s;
  background: #6492ac;
}
.ldio-f4nnk2ltl0v div:nth-child(7) {
  transform: rotate(180deg);
  animation-delay: -0.4166666666666667s;
  background: #637cb5;
}
.ldio-f4nnk2ltl0v div:nth-child(8) {
  transform: rotate(210deg);
  animation-delay: -0.3333333333333333s;
  background: #6a63b6;
}
.ldio-f4nnk2ltl0v div:nth-child(9) {
  transform: rotate(240deg);
  animation-delay: -0.25s;
  background: #fe718d;
}
.ldio-f4nnk2ltl0v div:nth-child(10) {
  transform: rotate(270deg);
  animation-delay: -0.16666666666666666s;
  background: #f47e60;
}
.ldio-f4nnk2ltl0v div:nth-child(11) {
  transform: rotate(300deg);
  animation-delay: -0.08333333333333333s;
  background: #f8b26a;
}
.ldio-f4nnk2ltl0v div:nth-child(12) {
  transform: rotate(330deg);
  animation-delay: 0s;
  background: #abbd81;
}
.loadingio-spinner-spinner-pz89b3jiaad {
  width: 304px;
  height: 304px;
  display: inline-block;
  overflow: hidden;
  background: #ffffff;
}
.ldio-f4nnk2ltl0v div {
  box-sizing: content-box;
}
</style>
