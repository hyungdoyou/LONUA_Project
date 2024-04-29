<template>
  <div v-show="isActive" class="layer pop_product pop_shopping_bag">
    <div class="layer_cont" style="margin: -140.5px 0px 0px -225px">
      <p>
        선택하신 상품이
        <br />
        쇼핑백에 추가 되었습니다.
      </p>
      <div class="btn_wrap">
        <button type="button" class="btn gray small" @click="closeModal">
          쇼핑 계속하기
        </button>
        <button type="button" class="btn black small" @click="goToBasket">
          쇼핑백 확인
        </button>
      </div>
      <button type="button" class="btn_close" @click="closeModal">닫기</button>
    </div>
    <span></span>
  </div>
</template>

<script>
export default {
  data() {
    return {
      isActive: false,
    };
  },
  methods: {
    openModal() {
      this.isActive = true;
    },
    closeModal() {
      this.isActive = false;
    },
    goToBasket() {
      // 쇼핑백 확인 버튼 클릭 시의 동작을 추가할 수 있습니다.
      console.log('쇼핑백 확인');
      window.location.href = "/UserCart";
      // closeModal(); // 만약 확인 버튼을 누르면 모달을 닫고 싶다면 이런 식으로 호출합니다.
    },
  },
};
</script>
<style>
.layer.active,
.layer_ov_ly.actve {
  display: block;
}
.layer *,
.layer_ov_ly * {
  font-family: 'ProximaNova-Regular', 'Apple SD Gothic Neo', 'Noto Sans KR', 'Malgun Gothic', '맑은 고딕', sans-serif;
}
.layer_cont {
  position: fixed;
  top: 50%;
  left: 50%;
  z-index: 110;
  min-width: 480px;
  overflow: hidden;
  padding: 40px 40px 50px;
  border: 1px solid #000;
  background: #fff;
}
.layer.pop_shopping_bag .layer_cont {
  min-width: 450px;
}
.layer.pop_shopping_bag p {
  padding: 48px 0 20px;
  font-family: 'Noto Sans KR';
  font-size: 18px;
  line-height: 1.4;
  text-align: center;
}
.layer.pop_product .btn_wrap {
  margin-top: 30px;
  text-align: center;
}
.layer_cont {
  position: fixed;
  top: 50%;
  left: 50%;
  z-index: 110;
  min-width: 480px;
  overflow: hidden;
  padding: 40px 40px 50px;
  border: 1px solid #000;
  background: #fff;
}
.layer.pop_shopping_bag .layer_cont {
  min-width: 450px;
}
a.btn,
button.btn,
input.btn,
span.btn {
  display: inline-block;
  min-width: 180px;
  padding: 0 20px;
  height: 50px;
  text-align: center;
  line-height: 48px;
  border: 1px solid #333;
  background-color: #fff;
  color: #000;
  font-family: 'ProximaNova-Semibold', 'Apple SD Gothic Neo', 'NotoSansKR-Medium', 'Malgun Gothic', '맑은 고딕',
    sans-serif;
  font-size: 14px;
}
a.btn.gray,
button.btn.gray,
input.btn.gray,
span.btn.gray {
  border-color: #7D7D7D;
  background-color: #7D7D7D;
  color: #fff;
}
a.btn.small,
button.btn.small,
input.btn.small,
span.btn.small {
  height: 40px;
  font-size: 14px;
  line-height: 38px;
}
.layer.pop_product .btn_wrap .btn {
  min-width: 140px;
  margin: 0 8px;
}
a.btn.black,
button.btn.black,
input.btn.black,
span.btn.black {
  border-color: #000;
  background-color: #000;
  color: #fff;
}
.layer_cont .btn_close,
.layer_cont .pop_in_pop_close {
  overflow: hidden;
  position: absolute;
  top: 25px;
  right: 25px;
  width: 53px;
  height: 53px;
  padding: 15px;
  line-height: 99em;
  vertical-align: top;
}
.layer_cont .btn_close:before,
.layer_cont .pop_in_pop_close:before {
  display: block;
  width: 23px;
  height: 23px;
  background: url(//static.wconcept.co.kr/web/images/common/layer_close_23.png) no-repeat;
  background-size: 100%;
  content: '';
}
.layer > span,
.layer_ov_ly > span {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  z-index: 101;
  background: #0E0E0E;
  opacity: 0.4;
  filter: alpha(opacity=40);
  content: '';
  display: block;
}
</style>