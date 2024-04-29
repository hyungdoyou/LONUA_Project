<template>
  <section id="contents">
    <div class="sub-title-wrap">
      <h2 class="sub-title">주문을 완료하였습니다.</h2>
    </div>
  </section>
</template>

<script>
export default {
  name: "UserOrdersSuccess",
};
</script>

<style scoped>
section {
  display: block;
}

.sub-title-wrap {
  padding-top: 200px;
  padding-bottom: 300px;
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
</style>
