<template>
  <section id="container">
    <div class="sub_title_wrap">
      <h2 class="sub_title">my page</h2>
      <ol id="breadcrumb">
        <li><a href="/">home</a></li>
        <li><a href="/MainMyPage">my page</a></li>
        <li><a href="/UserUpdate1">정보관리</a></li>
        <li>회원탈퇴</li>
      </ol>
    </div>

    <MyPageComponent />

    <div class="mypage">
      <div class="bx_customer">
        불편사항이 있으셨나요?
        <span>
          고객센터
          <strong>(0000-0000)</strong>
        </span>
        로 연락주시면 서비스개선을 위해 최선을 다하겠습니다.
      </div>
      <div class="table_title contents sub">
        <h4>회원탈퇴 신청</h4>
      </div>
      <table class="tbl_member_leave">
        <colgroup>
          <col width="315" />
          <col />
        </colgroup>
        <tbody>
          <tr>
            <th>탈퇴사유</th>
            <td>
              <span class="input_button">
                <input
                  type="checkbox"
                  id="checkbox01"
                  name="outtypeS"
                  value="01"
                />
                <label for="checkbox01">배송서비스가 마음에 들지 않아요</label>
              </span>
              <span class="input_button">
                <input
                  type="checkbox"
                  id="checkbox02"
                  name="outtypeS"
                  value="02"
                />
                <label for="checkbox02"
                  >상품, 가격, 품질이 마음에 들지 않아요</label
                >
              </span>
              <span class="input_button">
                <input
                  type="checkbox"
                  id="checkbox03"
                  name="outtypeS"
                  value="03"
                />
                <label for="checkbox03"
                  >교환/반품 서비스가 마음에 들지 않아요</label
                >
              </span>
              <span class="input_button">
                <input
                  type="checkbox"
                  id="checkbox04"
                  name="outtypeS"
                  value="04"
                />
                <label for="checkbox04">회원 혜택이 부족해요</label>
              </span>
              <span class="input_button">
                <input
                  type="checkbox"
                  id="checkbox06"
                  name="outtypeS"
                  value="06"
                />
                <label for="checkbox06">개인정보 유출이 우려돼요</label>
              </span>
              <span class="input_button">
                <input
                  type="checkbox"
                  id="checkbox07"
                  name="outtypeS"
                  value="07"
                />
                <label for="checkbox07">이용빈도가 낮아요</label>
              </span>
              <span class="input_button">
                <input
                  type="checkbox"
                  id="checkbox08"
                  name="outtypeS"
                  value="08"
                />
                <label for="checkbox08">사이트 이용이 불편해요</label>
              </span>
              <span class="input_button">
                <input
                  type="checkbox"
                  id="checkbox99"
                  name="outtypeS"
                  value="99"
                />
                <label for="checkbox99">기타</label>
              </span>
            </td>
          </tr>
          <tr>
            <th>고객님의 의견</th>
            <td>
              <textarea
                name="outmemo"
                id="outmemo"
                cols="30"
                rows="10"
                placeholder="LONUA 에 대한 불만사항이나 의견이 있으시면 기재 부탁드립니다."
                style="width: 820px; height: 270px"
              ></textarea>
            </td>
          </tr>
        </tbody>
      </table>
      <ul class="bx_check mt30">
        <li>
          <span class="input_button">
            <input
              type="checkbox"
              id="checkbox9"
              name="outagree1"
              placeholder=""
              data-gtm-form-interact-field-id="1"
            />
            <label for="checkbox9"
              >회원탈퇴 안내를 모두 확인하였으며 탈퇴에 동의합니다.(필수)</label
            >
          </span>
        </li>
        <li>
          <span class="input_button">
            <input
              type="checkbox"
              id="checkbox10"
              name="outagree2"
              placeholder=""
              data-gtm-form-interact-field-id="2"
            />
            <label for="checkbox10"
              >고객님의 쿠폰 및 포인트, 상품권 잔여금액 자동소멸에
              동의합니다.(필수)</label
            >
          </span>
        </li>
      </ul>
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
          @click="userStore.submitCancel()"
        >
          탈퇴
        </button>
      </div>
      <div class="notice_info_list mt60 mb60">
        <h3>안내 드립니다.</h3>
        <ul>
          <li>
            회원 탈퇴 시점을 기준으로 배송중 혹은 반품이나 교환 중에 있는 물품이
            없을 때만 탈퇴 처리가 가능합니다.
          </li>
          <li>
            회원 탈퇴 시 보유하고 계신 쿠폰 및 포인트, 상품권이 자동 소멸되며
            복구되지 않습니다.
          </li>
          <li>회원 탈퇴 후 30일 동안 재가입이 제한됩니다.</li>
          <li>
            회원 탈퇴 후 재가입 방지 목적으로 30일 동안 개인정보(이메일아이디,
            CI/DI, 휴대폰번호)를 보관하며, 재가입 제한 기간 경과 후 즉시
            파기됩니다.
          </li>
          <li>
            단, 전자상거래 등에서의 소비자보호에 관한 법률, 통신비밀보호법 등
            관련 법령의 규정에 의하여 아래와 같이 개인정보가 일정 기간
            보관됩니다.
            <p>
              - 계약 또는 청약철회 등에 관한 기록 : 5년, 대금결제 및 재화 등의
              공급에 관한 기록 : 5년, 소비자의 불만 또는 분쟁처리에 관한 기록 :
              3년, 웹사이트 방문 기록 : 3개월
            </p>
          </li>
        </ul>
      </div>
    </div>
  </section>
</template>

<script>
import MyPageComponent from "../components/MyPageComponent.vue";
import { mapStores } from "pinia";
import { useUserStore } from "../stores/useUserStore";

export default {
  name: "UserCancel",
  computed: {
    ...mapStores(useUserStore),
  },
  components: {
    MyPageComponent,
  },
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

.mypage .grade {
  width: 100%;
  padding: 36px 0;
  margin-bottom: 60px;
  background-color: #f8f8f8;
}

.mypage .grade .info {
  width: 698px;
  float: left;
  padding-left: 154px;
  position: relative;
}

.mypage .grade .info h3 {
  font-size: 18px;
  letter-spacing: 0.18px;
  color: #000;
  margin-bottom: 10px;
}

.mypage .grade .info h3 strong {
  font-size: 18px;
  color: #000;
  font-family: "NotoSansKR-Medium";
}

strong,
b {
  font-weight: normal;
}

.mypage .grade .info h3 a {
  display: inline-block;
  width: 90px;
  height: 24px;
  font-size: 12px;
  line-height: 24px;
  letter-spacing: 0;
  text-align: center;
  border: 1px solid #ccc;
  color: #333;
  background: #fff;
  vertical-align: top;
  margin-left: 10px;
}

.mypage .grade .info .grade_icon {
  position: absolute;
  top: 5px;
  left: 40px;
}

.mypage .grade_icon.wpeople {
  background: url(https://github.com/hyungdoyou/LONUA_Project/assets/148875644/35b2b760-c8f7-41ae-af61-a2da113c3aa9)
    no-repeat;
  background-size: 100%;
}

.mypage .grade_icon {
  display: block;
  width: 84px;
  height: 84px;
  overflow: hidden;
  text-indent: -9999px;
}

.mypage .grade .info p {
  font-size: 14px;
  line-height: 18px;
  letter-spacing: 0.14px;
}

p {
  margin: 0;
}

.mypage .grade .info p strong {
  font-family: "ProximaNova-Medium";
  color: #000;
  vertical-align: bottom;
}

.mypage .grade_text.wpeople {
  color: #000;
}

.mypage .grade_text {
  font-family: "ProximaNova-Medium";
  line-height: 1em;
}

.mypage .grade .info .notice_wrap {
  margin-top: 13px;
}

.mypage .grade .info .notice_wrap .notice:last-child {
  margin-bottom: 0;
}

.mypage .grade .info .notice_wrap .notice {
  position: relative;
  left: 5px;
  padding-left: 8px;
  color: #777;
  font-family: "ProximaNova-Regular", "Noto Sans KR";
  font-size: 13px;
  line-height: 17px;
  margin-bottom: 4px;
}

.mypage .grade .info .notice_wrap .notice:before {
  content: "";
  display: block;
  position: absolute;
  top: 8px;
  left: 0;
  width: 2px;
  height: 2px;
  background-color: #666;
}

.mypage .grade ul {
  float: left;
  height: 100px;
  width: 540px;
  display: table;
  padding-top: 4px;
}

.mypage .grade ul li {
  width: 50%;
  display: table-cell;
  height: 100%;
  border-left: 1px dashed #b5b5b5;
  vertical-align: middle;
  text-align: center;
}

.mypage .grade ul li a {
  display: block;
  font-family: "ProximaNova-Light", "Noto Sans KR";
  color: #333;
}

.mypage .grade ul li a h4 {
  font-size: 14px;
  margin-bottom: 17px;
  font-family: "ProximaNova-Light", "Noto Sans KR";
}

.mypage .grade ul li a p {
  font-size: 18px;
}

.mypage .grade ul li a p span {
  font-family: "ProximaNova-Regular";
  font-size: 28px;
  display: inline-block;
  border-bottom: 1px solid #000;
  color: #000;
  margin-right: 7px;
  line-height: 0.95em;
}

.mypage .grade:after {
  display: block;
  clear: both;
  content: "";
}

.mypage .bx_customer {
  margin-bottom: 60px;
  padding: 26px 0;
  border: 2px solid #f8f8f8;
  font-size: 16px;
  text-align: center;
  color: #666;
}

.mypage .bx_customer span {
  color: #000;
}

.mypage .bx_customer strong {
  font-family: "ProximaNova-Regular";
  font-size: 18px;
}

strong,
b {
  font-weight: normal;
}

.table_title.sub {
  height: 42px;
}

.table_title {
  position: relative;
}

.table_title h3,
.table_title h4 {
  color: #000;
  font-family: "ProximaNova-Regular", "Noto Sans KR";
  font-size: 18px;
  line-height: 36px;
  display: inline-block;
  position: absolute;
  top: 0;
  left: 0;
}

.mypage .tbl_member_leave {
  margin-bottom: 0;
}

table {
  border-top: 2px solid #171717;
  border-bottom: 1px solid #171717;
  table-layout: fixed;
  margin-bottom: 60px;
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

.mypage .tbl_member_leave td {
  padding: 14px 0;
}

.mypage .tbl_member_leave span {
  margin: 0 30px 6px 0;
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

span.input_button input[type="radio"] + label,
span.input_button input[type="checkbox"] + label {
  display: block;
  color: #000;
  font-size: 14px;
  font-family: "ProximaNova-Regular", "Noto Sans KR";
  line-height: 25px;
  padding-left: 31px;
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

span.input_button input[type="checkbox"]:checked + label:before {
  background-position: -30px 0;
}

th,
td {
  word-break: break-all;
  background-clip: padding-box;
}

.mypage .tbl_member_leave span:nth-child(3) ~ span {
  margin-bottom: 0;
}

table tbody td {
  padding: 0 19px;
  color: #000;
  font-size: 14px;
  border-top: 1px solid #d9d9d9;
}

textarea {
  height: 270px;
  font-size: 14px;
  line-height: 22px;
  letter-spacing: -0.28px;
  color: #777;
  padding: 14px 20px;
}

textarea {
  resize: none;
  border: 1px solid #bfbfbf;
}

textarea {
  overflow: auto;
}

input[type="text"],
textarea,
input[type="password"],
input[type="tel"],
input[type="number"] {
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

.mypage .bx_check {
  margin-bottom: 52px;
}

.mt30 {
  margin-top: 30px !important;
}

.mypage .bx_check span.input_button input[type="checkbox"] + label {
  color: #666;
}

.mypage .bx_check li:last-child {
  margin-top: 14px;
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

.mb60 {
  margin-bottom: 60px !important;
}

.mt60 {
  margin-top: 60px !important;
}

.mypage .notice_info_list h3 {
  line-height: 15px;
  vertical-align: middle;
  margin-bottom: 8px;
  font-size: 16px;
}

.mypage .notice_info_list h3:before {
  content: "";
  background: url(//static.wconcept.co.kr/web/images/common/spr-mypage.png) -50px
    0 no-repeat;
  width: 15px;
  height: 15px;
  display: inline-block;
  vertical-align: -1px;
  margin-right: 8px;
}

.mypage .notice_info_list > ul,
.mypage .notice_info_list .color_gray {
  padding-left: 7px;
}

.mypage .notice_info_list > ul > li,
.mypage .notice_info_list .color_gray > li {
  font-size: 12px;
  line-height: 22px;
  position: relative;
  padding-left: 11px;
  color: #333;
  font-family: "ProximaNova-Regular", "Noto Sans KR";
}

.mypage .notice_info_list > ul > li:before,
.mypage .notice_info_list .color_gray > li:before {
  width: 2px;
  height: 2px;
  background-color: #666;
  position: absolute;
  top: 9px;
  left: 0;
  content: "";
  display: block;
}

#snb .snb_header ul li a.active {
  background-color: #0e0e0e;
}
</style>
