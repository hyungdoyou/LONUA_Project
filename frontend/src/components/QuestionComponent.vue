<template>
  <tr class="active">
    <td class="product" :id="questions.questionIdx">
      <a :href="'/productdetail/' + questions.productIdx">
        <img :src="questions.productImage" />
        <div>
          <p class="brand">{{ questions.brandName }}</p>
          <p class="product_name">
            {{ questions.productName }}
          </p>
        </div>
      </a>
    </td>
    <td class="question" @click="showQuestion()">
      <div class="tit">
        <em class="icon_ready">접수완료</em>
        <span class="date">{{ questions.createdAt }}</span>
        <a href="javascript:;">{{ questions.questionType }}</a
        ><strong class="secret" v-show="questions.isSecret"></strong>
        <button
          type="button"
          class="micro_btn black btn_del"
          @click="deleteQuestion()"
        >
          삭제
        </button>
      </div>
      <div class="cont" v-show="isClicked">
        <div class="ask">
          <strong class="tit_sub">{{ questions.questionTitle }}</strong>
          <p class="txt">{{ questions.questionContent }}</p>
        </div>
      </div>
    </td>
  </tr>
</template>

<script>
import { mapStores } from "pinia";
import { useQnaStore } from "@/stores/useQnaStore.js";
export default {
  name: "QuestionComponent",
  props: ["questions"],
  computed: {
    ...mapStores(useQnaStore),
  },
  data() {
    return {
      isClicked: false,
    };
  },
  methods: {
    submitProductIdx() {
      console.log(this.orders.productIdx);
      console.log("click");
      this.reviewStore.productIdx = this.orders.productIdx;
      this.$router.push({ path: "/UserReview" });
      //window.location.href = "/UserReview";
    },
    showQuestion() {
      this.isClicked = !this.isClicked;
    },
    deleteQuestion() {
      this.qnaStore.deleteQuestion(this.questions.questionIdx);
    },
  },
};
</script>

<style scoped>
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
</style>
