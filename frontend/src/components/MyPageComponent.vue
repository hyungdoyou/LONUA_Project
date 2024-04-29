<template>
  <div id="snb" class="item6 mypage_snb">
    <div class="snb_header">
      <ul>
        <li :class="{ active: activeTab === 'UserLikes' }">
          <a href="/UserLikes" @click="setActiveTab('UserLikes')">MY ♥</a>
        </li>
        <li :class="{ active: activeTab === 'UserOrders' }">
          <a href="/UserOrders" @click="setActiveTab('UserOrders')">주문관리</a>
        </li>
        <li :class="{ active: activeTab === 'UserCoupon' }">
          <a href="/UserCoupon" @click="setActiveTab('UserCoupon')">쿠폰관리</a>
        </li>
        <li :class="{ active: activeTab === 'UserMileage' }">
          <a href="/UserMileage" @click="setActiveTab('UserMileage')"
            >쇼핑혜택</a
          >
        </li>
        <li :class="{ active: activeTab === 'UserUpdate1' }">
          <a href="/UserUpdate1" @click="setActiveTab('UserUpdate1')"
            >정보관리</a
          >
        </li>
        <li :class="{ active: activeTab === 'UserQnA' }">
          <a href="/UserQuestion" @click="setActiveTab('UserQnA')">문의내역</a>
        </li>
      </ul>
    </div>
    <div class="snb_sub">
      <div class="inner">
        <ul>
          <li>
            <a href="/UserLikes">MY ♥&nbsp; ITEM</a>
          </li>
        </ul>
        <ul>
          <li class="">
            <a href="/UserOrders">주문/배송조회</a>
          </li>
          <li class="">
            <a href="/UserCancelOrders">주문취소내역 조회</a>
          </li>
        </ul>
        <ul>
          <li class="">
            <a href="/UserCoupon">받은 쿠폰</a>
          </li>
        </ul>
        <ul>
          <li class=""><a href="/UserMileage">마일리지</a></li>
        </ul>
        <ul>
          <li class="">
            <a href="/UserUpdate1">회원정보 수정</a>
          </li>
          <li class="">
            <a href="/UserCancel">회원탈퇴</a>
          </li>
        </ul>
        <ul>
          <li class="">
            <a href="/UserQuestion">상품 Q&amp;A</a>
          </li>
          <li class="">
            <a href="/UserReview"> 리뷰 </a>
          </li>
        </ul>
      </div>
    </div>
    <div class="snb_close_wrap">
      <button type="button" name="button" class="snb_close"></button>
    </div>
  </div>
</template>

<script>
export default {
  name: "MyPageComponent",

  data() {
    return {
      activeTab: "",
    };
  },
  methods: {
    setActiveTab(tabName) {
      this.activeTab = tabName;
    },
  },
};
</script>

<style scoped>
#snb .snb_header ul li.active {
  background-color: #0e0e0e;
}
#snb .snb_header ul li.active a {
  color: #fff;
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
</style>
