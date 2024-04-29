<template>
  <section id="container">
    <div class="sub_title_wrap">
      <h2 class="sub_title">my page</h2>
      <ol id="breadcrumb">
        <li><a href="/">home</a></li>
        <li><a href="/MainMyPage">my page</a></li>
        <li><a href="/UserUpdate1">정보관리</a></li>
        <li>회원정보 수정</li>
      </ol>
    </div>

    <MyPageComponent />

    <div class="mypage">
      <div class="table_title contents">
        <h3>회원정보 수정</h3>
      </div>
      <form name="frmjoin" id="frmjoin" method="post">
        <table class="tbl_modify">
          <colgroup>
            <col width="315" />
            <col />
          </colgroup>
          <tbody>
            <tr>
              <th>비밀번호</th>
              <td>
                <input
                  type="password"
                  name="joinPw"
                  id="joinPw"
                  minlength="8"
                  maxlength="45"
                  v-model="updateData.userPassword"
                  placeholder="영문 대/소문자+숫자+특수문자 조합 8자리 이상"
                />
              </td>
            </tr>
            <tr>
              <th>비밀번호 확인</th>
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
              <th>휴대전화 번호</th>
              <td>
                <p class="join_name va_m">
                  <input
                    type="text"
                    name="joinPhoneNumber"
                    id="joinPhoneNumber"
                    maxlength="13"
                    v-model="updateData.userPhoneNumber"
                    placeholder="ex ) 010-0000-0000"
                  />
                </p>
              </td>
            </tr>
            <tr>
              <th>주 소</th>
              <td>
                <p class="join_name va_m">
                  <input
                    type="text"
                    name="joinAddr"
                    id="joinAddr"
                    maxlength="45"
                    v-model="updateData.userAddr"
                    placeholder="ex ) 서울시 강동구 보라매로 47"
                  />
                </p>
              </td>
            </tr>
            <tr>
              <th>선호 스타일</th>
              <td>
                <div class="styleOptions">
                  <label>
                    <input
                      type="radio"
                      name="style"
                      value="캐주얼"
                      v-model="updateData.preferStyle"
                    />
                    캐주얼
                  </label>
                  <label>
                    <input
                      type="radio"
                      name="style"
                      value="시크"
                      v-model="updateData.preferStyle"
                    />
                    시크
                  </label>
                  <label>
                    <input
                      type="radio"
                      name="style"
                      value="댄디"
                      v-model="updateData.preferStyle"
                    />
                    댄디
                  </label>
                  <label>
                    <input
                      type="radio"
                      name="style"
                      value="스트릿"
                      v-model="updateData.preferStyle"
                    />
                    스트릿
                  </label>
                  <label>
                    <input
                      type="radio"
                      name="style"
                      value="비지니스캐주얼"
                      v-model="updateData.preferStyle"
                    />
                    비지니스캐주얼
                  </label>
                  <label>
                    <input
                      type="radio"
                      name="style"
                      value="힙합"
                      v-model="updateData.preferStyle"
                    />
                    힙합
                  </label>
                  <label>
                    <input
                      type="radio"
                      name="style"
                      value="오피스"
                      v-model="updateData.preferStyle"
                    />
                    오피스
                  </label>
                  <label>
                    <input
                      type="radio"
                      name="style"
                      value="스포츠"
                      v-model="updateData.preferStyle"
                    />
                    스포츠
                  </label>
                </div>
              </td>
            </tr>
            <tr>
              <th>상체 유형</th>
              <td>
                <div class="upperTypeOptions">
                  <label>
                    <input
                      type="radio"
                      name="upperType"
                      value="1"
                      v-model="updateData.upperType"
                    />
                    상체 마름
                  </label>
                  <label>
                    <input
                      type="radio"
                      name="upperType"
                      value="2"
                      v-model="updateData.upperType"
                    />
                    상체 보통
                  </label>
                  <label>
                    <input
                      type="radio"
                      name="upperType"
                      value="3"
                      v-model="updateData.upperType"
                    />
                    상체 비만
                  </label>
                </div>
              </td>
            </tr>
            <tr>
              <th>하체 유형</th>
              <td>
                <div class="lowerTypeOptions">
                  <label>
                    <input
                      type="radio"
                      name="lowerType"
                      value="1"
                      v-model="updateData.lowerType"
                    />
                    하체 마름
                  </label>
                  <label>
                    <input
                      type="radio"
                      name="lowerType"
                      value="2"
                      v-model="updateData.lowerType"
                    />
                    하체 보통
                  </label>
                  <label>
                    <input
                      type="radio"
                      name="lowerType"
                      value="3"
                      v-model="updateData.lowerType"
                    />
                    하체 비만
                  </label>
                </div>
              </td>
            </tr>
          </tbody>
        </table>
        <div class="bx_btn_center">
          <button
            class="btn"
            type="button"
            name="button"
            @click="window.location.href = '/MainMyPage'"
          >
            취소
          </button>
          <button
            class="btn black"
            type="button"
            name="button"
            @click="sendUpdateData()"
          >
            수정
          </button>
        </div>
      </form>
    </div>
  </section>
</template>

<script>
import { mapStores } from "pinia";
import { useUserStore } from "../stores/useUserStore";
import MyPageComponent from "@/components/MyPageComponent.vue";

export default {
  name: "UserUpdate2",
  computed: {
    ...mapStores(useUserStore),
  },
  data() {
    return {
      updateData: {
        userPassword: "",
        userPhoneNumber: "",
        userAddr: "",
        preferStyle: null,
        upperType: null,
        lowerType: null,
      },
    };
  },
  methods: {
    async sendUpdateData() {
      await this.userStore.sendUpdateData(this.updateData);
    },
  },
  components: { MyPageComponent },
};
</script>

<style scoped>
#container .sub_title_wrap {
  padding-top: 55px;
  padding-bottom: 36px;
  position: relative;
  min-width: 1240px;
}

#container .sub_title_wrap .sub_title {
  color: #000;
}

#container .sub_title {
  font-family: "ProximaNova-Thin", "Noto Sans KR";
  font-size: 44px;
  line-height: 44px;
  color: #333;
  text-align: center;
  text-transform: uppercase;
}

h2,
h3,
h4 {
  margin: 0;
  font-weight: normal;
}

#breadcrumb {
  width: 100%;
  text-align: center;
  margin-top: 16px;
}

#breadcrumb li {
  display: inline-block;
  font-family: "ProximaNova-Regular", "Noto Sans KR";
  font-size: 11px;
  text-transform: uppercase;
  vertical-align: middle;
  color: #000;
}

#breadcrumb li a {
  text-transform: uppercase;
  color: #676767;
  vertical-align: top;
}

#breadcrumb li:not(:last-child):after {
  content: "";
  display: inline-block;
  width: 3px;
  height: 5px;
  vertical-align: top;
  margin: 5px 6px 0 10px;
  font-size: 0;
  background: url(//static.wconcept.co.kr/web/images/common/spr-common.png) -60px -60px
    no-repeat;
}

#snb {
  width: 100%;
  min-width: 1240px;
  border-bottom: 1px solid #e6e6e6;
  background-color: #fff;
  margin-bottom: 60px;
}

#snb .snb_header {
  width: 100%;
  min-width: 1240px;
  background-color: #f3f3f3;
  border-top: 1px solid #e6e6e6;
}

#snb .snb_header ul {
  width: 1240px;
  margin: 0 auto;
}

#snb.item6 .snb_header ul li:first-child {
  width: 210px;
}

#snb .snb_header ul li {
  text-align: center;
  float: left;
  position: relative;
  line-height: 41px;
}

#snb .snb_header ul li a {
  font-size: 14px;
  color: #333;
}

#snb .snb_header ul:first-child:before {
  content: none; /* 또는 content: ''; */
}

#snb .snb_header ul li:before {
  content: "";
  width: 1px;
  height: 25px;
  background-color: #e5e5e5;
  display: block;
  position: absolute;
  top: 12px;
  left: 0;
}

#snb.item6 .snb_header ul li {
  width: 206px;
}

#snb .snb_header ul:after {
  display: block;
  clear: both;
  content: "";
}

#snb .snb_sub {
  width: 100%;
  border-top: 1px solid #e6e6e6;
}

#snb .snb_sub .inner {
  width: 1240px;
  margin: 0 auto;
  padding: 9px 0;
  display: table;
}

#snb.item6 .snb_sub ul:first-child {
  width: 210px;
}

#snb .snb_sub .inner ul {
  display: table-cell;
  position: relative;
}

#snb .snb_sub .inner ul li {
  text-align: center;
  line-height: 25px;
  vertical-align: top;
}

#snb .snb_sub .inner ul li a {
  font-size: 12px;
  color: #4c4c4c;
}

#snb .snb_sub .inner ul:first-child:before {
  content: none; /* 또는 content: ''; */
}

#snb .snb_sub .inner ul:before {
  content: "";
  width: 1px;
  height: 120%;
  background-color: #e5e5e5;
  display: block;
  position: absolute;
  top: 0;
  left: 0;
}

#snb.item6 .snb_sub ul {
  width: 206px;
}

#snb .snb_close_wrap {
  position: relative;
  width: 1240px;
  margin: 0 auto;
}

.mypage {
  font-family: "ProximaNova-Regular", "Apple SD Gothic Neo", "Noto Sans KR",
    "Malgun Gothic", "맑은 고딕", sans-serif;
}

.mypage {
  width: 1240px;
  margin: 0 auto;
}

/*-----------------------------------------------*/

.table_title.contents {
  height: 41px;
}

.table_title {
  position: relative;
  height: 50px;
}

.table_title h3,
.table_title h4 {
  color: #000;
  font-family: "ProximaNova-Regular", "Noto Sans KR";
  font-size: 24px;
  line-height: 36px;
  display: inline-block;
  position: absolute;
  top: 0;
  left: 0;
}

form {
  margin: 0;
}

table {
  border-top: 2px solid #171717;
  border-bottom: 1px solid #171717;
  table-layout: fixed;
  margin-bottom: 60px;
}

table {
  width: 100%;
  border-collapse: separate;
}

table tbody tr:first-child th,
table tbody tr:first-child td {
  border-color: transparent;
  border: none;
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

table tbody td {
  padding: 0 19px;
  color: #000;
  font-size: 14px;
  border-top: 1px solid #d9d9d9;
}

table tbody td input[type="text"],
table tbody td textarea,
table tbody td input[type="password"],
table tbody td input[type="tel"],
table tbody td input[type="number"] {
  width: 400px;
}

table tbody td input[type="text"],
table tbody td textarea,
table tbody td input[type="password"],
table tbody td input[type="tel"],
table tbody td input[type="number"] {
  width: 400px;
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

.mypage .bx_btn_center {
  text-align: center;
}

.mypage .bx_btn_center .btn:first-child {
  margin-right: 16px;
}

.mypage .bx_btn_center .btn {
  font-size: 16px;
}

.bx_btn_center .btn {
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

a.btn.black,
button.btn.black,
input.btn.black,
span.btn.black {
  border-color: #000;
  background-color: #000;
  color: #fff;
}
</style>
