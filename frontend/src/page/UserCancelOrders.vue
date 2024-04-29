<template>
  <section id="container">
    <div class="sub_title_wrap">
      <h2 class="sub_title">my page</h2>
      <ol id="breadcrumb">
        <li><a href="/">home</a></li>
        <li><a href="/MainMyPage">my page</a></li>
        <li><a href="/UserOrders">주문관리</a></li>
        <li>주문취소내역 조회</li>
      </ol>
    </div>

    <MyPageComponent />

    <div class="mypage">
      <div id="divList" style="display: block">
        <table class="cols tbl_gray" id="rowspanTb">
          <colgroup>
            <col style="width: 100px" />
            <col style="width: 120px" />
            <col style="width: 100px" />
            <col />
            <col style="width: 173px" />
            <col style="width: 195px" />
            <col style="width: 155px" />
          </colgroup>
          <thead>
            <tr>
              <th>신청일</th>
              <th>주문번호</th>
              <th>유형</th>
              <th>상품정보</th>
              <th>수량</th>
              <th>상품금액</th>
              <th>진행상황</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td colspan="7">취소/교환/반품 신청하신 내역이 없습니다.</td>
            </tr>
          </tbody>
        </table>
        <!-- 여기에 주문내역 추가-->
        <ul class="pagination"></ul>
      </div>
      <div class="table_title sub mt60">
        <h4>주문취소 안내</h4>
      </div>
      <div class="info_step cancel tab2_content tab2_content1 active">
        <div class="step step1">
          <dl>
            <dt class="cancel_wait">취소대기중</dt>
            <dd>
              판매자가 취소 가능여부를
              <br />
              판단중에 있습니다. 판매자에 의해
              <br />
              취소가 불가능할 수 있습니다.
              <p class="link">(취소철회 고객센터로 문의)</p>
            </dd>
          </dl>
          <dl>
            <dt class="cancel_end" id="cancel">취소완료</dt>
            <dd>
              취소승인이 완료되었습니다.
              <br />
              결제수단별 환불절차에 따라
              <br />
              환불이 진행 됩니다.
              <p class="link">
                <a href="#none" class="open-layer open-pop_refund_guide"
                  >결제수단별 환불 안내</a
                >
              </p>
            </dd>
          </dl>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import MyPageComponent from "@/components/MyPageComponent.vue";

export default {
  name: "UserCancelOrders",
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

.mypage .notice_info_list.mt55 {
  margin-top: 55px;
}

.mypage .notice_info_list h3 {
  line-height: 15px;
  vertical-align: middle;
  margin-bottom: 8px;
  font-size: 16px;
}

h1,
h2,
h3,
h4,
h5,
h6 {
  margin: 0;
  font-weight: normal;
  font-family: "ProximaNova-Thin", "Noto Sans KR";
}
.mt60 {
  margin-top: 60px !important;
}
.table_title {
  position: relative;
  height: 50px;
}

.table_title.sub {
  height: 42px;
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

.table_title h4 {
  font-size: 18px;
  padding-left: 35px;
}

.tab2_content.active {
  display: block;
}

.mypage .info_step.cancel,
.mypage .info_step.return {
  margin-top: 20px;
}

.mypage .info_step .step {
  position: relative;
  float: left;
  height: 250px;
  margin-left: 10px;
  padding: 30px;
  background: #fafafa;
}

.mypage .info_step .step:first-child {
  margin-left: 0;
}

.mypage .info_step.cancel .step {
  width: 490px;
}

.mypage .info_step .step dl {
  position: relative;
  float: left;
  padding-left: 10px;
  font-family: "Noto Sans KR";
}

.mypage .info_step.cancel .step dl {
  width: 50%;
}

.mypage .info_step.cancel .step1 dl:first-child:before,
.mypage .info_step.cancel .step2 dl:first-child:before {
  position: absolute;
  top: 50px;
  right: 0;
  width: 2px;
  height: 90px;
  border-right: 1px dashed #dbdbdb;
  content: "";
}

.mypage .info_step .step dl dt {
  color: #333;
  font-size: 16px;
  line-height: 34px;
}

.mypage .info_step .step dl dt:before {
  display: inline-block;
  height: 34px;
  margin-right: 9px;
  background-repeat: no-repeat;
  background-size: 100%;
  background-position: center;
  vertical-align: top;
  content: "";
}

.mypage .info_step .step dl dt.cancel_wait:before {
  width: 22px;
  background-image: url(//static.wconcept.co.kr/web/images/common/ico_01_sandglass.png);
}

.mypage .info_step .step dl dd {
  margin: 0;
  margin-top: 13px;
  color: #666;
  font-size: 13px;
  line-height: 1.6;
}

.mypage .info_step .step .link {
  margin-top: 20px;
  font-size: 12px;
}

.mypage .info_step.cancel .cancel_wait:after {
  content: "";
  position: absolute;
  top: 0;
  right: -16px;
  width: 32px;
  height: 32px;
  background: url(//static.wconcept.co.kr/web/images/common/ico_deliivery_flow_32.png)
    no-repeat;
  background-size: 100%;
}

.mypage .info_step.cancel .step1 dl:nth-child(2),
.mypage .info_step.cancel .step2 dl:nth-child(2) {
  padding-left: 40px;
}

.mypage .info_step .step dl dt.cancel_end:before {
  width: 34px;
  background-image: url(//static.wconcept.co.kr/web/images/common/ico_02_cancel.png);
}

.mypage .info_step .step .link a {
  display: inline-block;
  color: #666;
  position: relative;
  padding-right: 8px;
}

.mypage .info_step .step .link a:after {
  display: inline-block;
  width: 4px;
  height: 6px;
  background: url(//static.wconcept.co.kr/web/images/common/ico_arrow_6.png)
    no-repeat;
  background-size: 100%;
  content: "";
  position: absolute;
  top: 50%;
  right: 0;
  margin-top: -3px;
}

.mypage .info_step .step:after {
  display: block;
  clear: both;
  content: "";
}

.mypage .info_step:after {
  display: block;
  clear: both;
  content: "";
}
</style>
