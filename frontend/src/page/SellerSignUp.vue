<template>
  <form>
    <section id="contents">
      <div class="sub-title-wrap">
        <h2 class="sub-title">JOIN SELLER</h2>
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
                    v-model="seller.sellerEmail"
                    placeholder="example@example.com"
                  />
                  <div id="validation1" class="incorrect">
                    <label id="chkcustid">
                      본인 소유의 이메일을 입력해 주세요.
                    </label>
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
                  v-model="seller.sellerPassword"
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
                브랜드 IDX
                <span>*</span>
              </th>
              <td>
                <div class="join_name va_m">
                  <div class="email va_m">
                    <input
                      type="text"
                      class="duplicate_email"
                      id="brandName"
                      style="width: 210px"
                      readonly="readonly"
                      v-model="selectedBrand"
                    />
                    <select
                      class="normal"
                      style="width: 180px; display: none"
                      id="selectEmail"
                      v-model="seller.brandIdx"
                      @click="toggleSelectList"
                    >
                      <option value="" selected disabled>선택</option>
                      <option value="1">SPAO</option>
                      <option value="2">YALE</option>
                      <option value="3">COVERNAT</option>
                      <option value="4">INSILENCE</option>
                      <option value="5">MUSINSA STANDARD</option>
                      <option value="6">SATUR</option>
                    </select>

                    <div
                      style="width: 180px; outline-style: none"
                      class="select-list-box"
                      wck="selectEmail"
                      tabindex="0"
                    >
                      <div
                        tabindex="0"
                        title=" "
                        class="selected-brand-headline"
                        style="width: 180px"
                        @click="toggleSelectList"
                      >
                        <span>{{
                          selectedBrand
                            ? brands.find(
                                (brand) => brand.value === selectedBrand
                              ).label
                            : "브랜드 선택"
                        }}</span>
                      </div>

                      <ul
                        class="select-brandList"
                        :style="{
                          visibility: selectListVisible ? 'visible' : 'hidden',
                        }"
                      >
                        <li v-for="(brand, index) in brands" :key="index">
                          <a
                            :title="brand.value"
                            href="JavaScript:void(0);"
                            class="select-list-link"
                            style="outline-style: none"
                            @click="selectBrand(brand)"
                          >
                            {{ brand.label }}
                          </a>
                        </li>
                      </ul>
                    </div>
                  </div>
                </div>
              </td>
            </tr>
            <tr>
              <th>
                브랜드 고유키
                <span>*</span>
              </th>
              <td>
                <p class="join_name va_m">
                  <input
                    type="text"
                    name="brandUniqueKey"
                    id="brandUniqueKey"
                    maxlength="45"
                    v-model="seller.brandUniqueKey"
                  />
                </p>
              </td>
            </tr>
            <tr>
              <th>
                판매자명
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
                    v-model="seller.sellerName"
                  />
                </p>
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
                <label for="checkAgree2">
                  개인정보 수집 및 이용에 대한 동의(필수)
                </label>
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
                <label for="checkAgree3">
                  개인정보 수집 및 이용안내(선택)
                </label>
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
            @click="sendSellerSignUpData()"
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
import { useSellerStore } from "../stores/useSellerStore";

export default {
  name: "SellerSignUp",
  computed: {
    ...mapStores(useSellerStore),
  },
  data() {
    return {
      selectedBrand: "",
      selectListVisible: false,
      brands: [
        { value: "1", label: "SPAO" },
        { value: "2", label: "YALE" },
        { value: "3", label: "COVERNAT" },
        { value: "4", label: "INSILENCE" },
        { value: "5", label: "MUSINSA STANDARD" },
        { value: "6", label: "SATUR" },
      ],
      seller: {
        sellerEmail: "",
        sellerPassword: "",
        brandIdx: "",
        brandUniqueKey: "",
        sellerName: "",
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
      window.location.href = "/SellerLogIn";
    },
    checkAll() {
      this.checkbox1 = !this.checkbox1;
      this.checkbox2 = !this.checkbox2;
      this.checkbox3 = !this.checkbox3;
      this.checkbox4 = !this.checkbox4;
    },
    selectBrand(brand) {
      this.selectedBrand = brand.value;
      this.seller.brandIdx = brand.value;
      this.selectListVisible = false;
    },
    toggleSelectList() {
      this.selectListVisible = !this.selectListVisible;
    },
    async sendSellerSignUpData() {
      await this.sellerStore.sendSellerSignUpData(this.seller);
      if (this.sellerStore.isSuccess) {
        alert("회원가입이 완료되었습니다.");
        this.$router.push({ path: "/" });
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
  cursor: pointer;
  position: relative;
  box-sizing: border-box;
}

/* 메일 선택 시 바뀌게 되는 스타일 */
.select-list-box .selected-headline.select-list-selected {
  background-color: #fff;
  border: 1px solid #000;
}

.select-list-box .selected-brand-headline.select-brandList-selected {
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

.select-list-box .selected-brand-headline {
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

.select-list-box .selected-brand-headline > span {
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

.select-list-box .selected-brand-headline:after {
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

/* 메일 선택 시 바뀌게 되는 스타일 */

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

.select-list-box .select-brandList {
  position: absolute;
  height: auto;
  top: 40px;
  left: 0;
  width: 100%;
  border: 1px solid #000;
  background-color: #fff;
  z-index: 10;
  overflow: hidden;
  overflow-y: auto;
}

.select-list-box .select-brandList ul {
  margin: 0;
  padding: 0;
  list-style: none;
}

.select-list-box .select-brandList li {
  padding: 8px;
  cursor: pointer;
}

/* 선택창 헤드라인 스타일 */
.select-list-box .selected-brand-headline {
  cursor: pointer;
  text-align: center; /* 가운데 정렬 */
}

/* -------------------------------- */
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
</style>
