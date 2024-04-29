<template>
  <section id="container">
    <div class="sub_title_wrap">
      <h2 class="sub_title">my page</h2>
      <ol id="breadcrumb">
        <li><a href="/">home</a></li>
        <li><a href="/MainMyPage">my page</a></li>
        <li><a href="/UserQuestion">문의내역</a></li>
        <li><a href="/UserQuestion">상품 Q&A</a></li>
      </ol>
    </div>

    <MyPageComponent />
    <!-- 여기까지 mymainpage header 부분-->
    <div class="mypage">
      <div class="table_title">
        <h3>상품Q&amp;A</h3>
      </div>
      <div id="divList" style="display: block">
        <table class="cols mb45 my-qna">
          <colgroup>
            <col style="width: 390px" />
            <col />
          </colgroup>
          <thead>
            <tr>
              <th>상품정보</th>
              <th>문의내용</th>
            </tr>
          </thead>
          <tbody v-show="qnaStore.isQuestionExist">
            <tr>
              <td colspan="2" class="no_data">
                작성하신 상품&nbsp;Q&amp;A&nbsp;가 없습니다.<br /><br />
                상품&nbsp;Q&amp;A는 문의하실 상품의 상세페이지에서 작성하실 수
                있습니다.
              </td>
            </tr>
          </tbody>
          <br />
          <br />
          <tbody
            v-for="questions in qnaStore.qnas"
            :key="questions.questionIdx"
          >
            <QuestionComponent :questions="questions" /><br /><br />
          </tbody>
        </table>

        <ul class="pagination mb60">
          <li class="first">
            <a href="javascript:;" style="cursor: default" title="처음"
              >first</a
            >
          </li>
          <li class="prev">
            <a href="javascript:;" style="cursor: default">prev</a>
          </li>
          <li class="active">
            <a
              href="javascript:;"
              style="cursor: default; margin: 0 5px 0 5px"
              title="1"
              >1</a
            >
          </li>
          <li class="next">
            <a href="javascript:;" style="cursor: default">next</a>
          </li>
          <li class="last">
            <a href="javascript:;" style="cursor: default" title="마지막"
              >last</a
            >
          </li>
        </ul>
      </div>
      <!-- list// -->

      <div class="notice_info_list">
        <h3>안내 드립니다.</h3>
        <ul>
          <li>
            상품과 관련없는 내용, 비방, 광고, 불건전한 내용의 글은 사전 동의
            없이 삭제될 수 있습니다.
          </li>
          <li>
            아직 궁금하신 점이 있으신가요? 고객센터(0000-0000)로 문의해주세요.
          </li>
        </ul>
      </div>
    </div>
  </section>
</template>

<script>
import QuestionComponent from "../components/QuestionComponent.vue";
import MyPageComponent from "@/components/MyPageComponent.vue";
import { mapStores } from "pinia";
import { useQnaStore } from "@/stores/useQnaStore.js";

export default {
  name: "UserQuestion",
  data() {
    return {};
  },
  computed: {
    ...mapStores(useQnaStore),
  },
  mounted() {
    this.qnaStore.getQna();
  },
  components: { MyPageComponent, QuestionComponent },
};
</script>

<style scoped>
.table-container {
  margin: auto;
  max-width: 800px; /* 원하는 폭으로 조절하세요 */
  width: 100%;
}
table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 20px;
}

tr:nth-child(even) {
  background-color: #f9f9f9;
  border-top: 1px solid #dddddd;
}
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

table {
  border-spacing: 0;
}

table.cols thead th {
  height: 68px;
  font-family: "NotoSansKR-Medium";
  font-size: 14px;
  padding: 0 14px;
  color: #000;
  text-align: center;
  vertical-align: middle;
  border-bottom: 1px solid #b5b5b5;
}

table.cols tbody tr td.no_data {
  height: 150px;
  color: #333;
  font-size: 14px;
}

table.cols tbody tr td {
  height: 68px;
  font-family: "ProximaNova-Regular", "Noto Sans KR";
  text-align: center;
  vertical-align: middle;
  padding: 14px 0;
}

table tbody tr:first-child th,
table tbody tr:first-child td {
  border-color: transparent;
  border: none;
}
/*-------------------------여기부터 본문-------------------------*/

.mypage {
  width: 1240px;
  margin: 0 auto;
}

.mypage {
  font-family: "ProximaNova-Regular", "Apple SD Gothic Neo", "Noto Sans KR",
    "Malgun Gothic", "맑은 고딕", sans-serif;
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

table {
  border-top: 2px solid #171717;
  border-bottom: 1px solid #171717;
  table-layout: fixed;
  margin-bottom: 60px;
}

.mb45 {
  margin-bottom: 45px !important;
}

.mypage tr.active td {
  vertical-align: top;
}

.my-qna tbody tr.active td {
  background-color: #fff;
}

table.cols tbody tr td.product {
  text-align: left;
  padding-left: 20px;
  font-family: "Noto Sans KR";
}

table.cols tbody tr td a {
  color: #000;
}

.mypage table.cols tbody tr td.product a {
  display: block;
  overflow: hidden;
}

table.cols tbody tr td img {
  width: 60px;
  display: block;
  float: left;
  margin-right: 20px;
}

.mypage table.cols tbody tr td.product img,
.mypage .tbl_review.cols .tbl_review--thumb {
  height: 80px;
  background: rgba(0, 0, 0, 0.04);
}

.mypage table.cols tbody tr td.product a div {
  position: relative;
  margin-bottom: 2px;
  margin-left: 80px;
}

table.cols tbody tr td.product p {
  display: block;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
  color: #808080;
  font-size: 12px;
  vertical-align: top;
  line-height: 1em;
}

table.cols tbody tr td.product p.brand {
  font-size: 14px;
  color: #000;
  width: auto;
  min-height: auto;
  margin: 0 0 6px 0;
}

.mypage table.cols tbody tr td.product p {
  line-height: normal;
}

.mypage table.cols tbody tr td.product p.brand {
  margin-bottom: 5px;
}

.mypage table.cols tbody tr td.product p.product_name {
  margin-bottom: 10px;
}

.mypage td.question .tit {
  position: relative;
}

.mypage td.question .icon_ready,
.mypage td.question .icon_finish,
.mypage td.question .icon_ing {
  display: block;
  overflow: hidden;
  height: 18px;
  width: 50px;
  background: url(//static.wconcept.co.kr/web/images/common/spr-mypage.png) 0 0
    no-repeat;
  text-indent: -999em;
}

.mypage td.question .icon_ready {
  background-position: -400px 0;
}

.mypage td.question .tit .date {
  display: inline-block;
  position: relative;
  padding-right: 10px;
  margin: 18px 6px 0 0;
  color: #666;
}

.mypage td.question .tit .date:before {
  content: "";
  display: block;
  width: 1px;
  height: 15px;
  background-color: #b5b5b5;
  position: absolute;
  top: 3px;
  right: 0;
}

.mypage td.question .tit .secret {
  position: relative;
  padding-right: 25px;
}

strong,
b {
  font-weight: normal;
}

.mypage td.question .tit .secret:before {
  content: "";
  width: 15px;
  height: 18px;
  position: absolute;
  top: 0;
  right: 0;
  background: url(//static.wconcept.co.kr/web/images/common/spr-mypage.png) -400px -50px
    no-repeat;
}

a.micro_btn,
button.micro_btn,
input.micro_btn,
span.micro_btn {
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
a.micro_btn.black,
button.micro_btn.black,
input.micro_btn.black,
span.micro_btn.black {
  background-color: #000;
  border-color: #000;
  color: #fff;
}

.mypage td.question .tit .btn_del {
  position: absolute;
  top: 45%;
  right: -80px;
}

.mypage td.question .cont {
  display: none;
  margin-top: 36px;
  padding: 26px 0 14px 0;
  border-top: 1px solid #d9d9d9;
}

.mypage tr.active td .cont {
  display: block;
}

.mypage td.question .cont .tit_sub {
  display: block;
  font-family: "NotoSansKR-Medium";
}

.mypage td.question .cont .txt {
  margin-top: 15px;
}

.mypage td.question {
  padding: 14px 100px 14px 30px;
  text-align: left;
}

.pagination {
  display: table;
  margin: 0 auto -8px;
  font-size: 0;
  text-align: center;
}

.pagination li {
  display: table-cell;
  width: 33px;
  line-height: 22px;
  height: 22px;
  vertical-align: text-top;
}

.pagination li.first,
.pagination li.prev,
.pagination li.next,
.pagination li.last {
  margin: 0;
  position: relative;
}

.pagination li.first {
  margin-right: 20px;
}

.pagination li a {
  display: block;
  font-family: "ProximaNova-Light";
  font-size: 14px;
  color: #333;
  text-align: center;
  letter-spacing: -0.01em;
  line-height: 16px;
}

.pagination li.first a,
.pagination li.prev a,
.pagination li.next a,
.pagination li.last a {
  overflow: hidden;
  text-indent: -9999px;
  background: url(//static.wconcept.co.kr/web/images/common/spr-common.png) 0 0
    no-repeat;
  height: 11px;
  width: 12px;
  position: absolute;
  top: 0;
  left: 0;
  margin-top: 3px;
}

.pagination li.first a {
  background-position: 0 -30px;
  left: 8px;
}

.pagination li.prev {
  margin-right: 22px;
}

.pagination li.prev a {
  width: 6px;
  background-position: -30px -30px;
  left: 7px;
}

.pagination li.active a {
  font-family: "ProximaNova-Bold";
}

.pagination li.next {
  margin-left: 23px;
}

.pagination li.next a {
  width: 6px;
  background-position: -60px -30px;
  left: auto;
  right: 7px;
}

.pagination li.last {
  margin-left: 20px;
}

.pagination li.last a {
  background-position: -90px -30px;
  left: auto;
  right: 8px;
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
</style>
