<template>
  <div></div>
</template>

<script>
import router from "@/router";

export default {
  created() {
    const receivedToken = this.$route.params.token;
    // const currentUrl = window.location.href;
    // const urlParams = new URLSearchParams(currentUrl);
    // const receivedToken = urlParams.get("token");

    console.log("token", receivedToken);
    if (receivedToken) {
      sessionStorage.setItem("token", receivedToken);
      router.push({ path: "/" });
    } else {
      window.alert("로그인에 실패하였습니다.");
      router.push({ path: "/UserLogIn" });
    }
  },
};
</script>

<style scoped></style>
