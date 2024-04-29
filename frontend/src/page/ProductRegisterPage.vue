<template>
  <br /><br /><br />
  <form id="productForm" enctype="multipart/form-data">
    <label for="productName">상품 이름:</label>
    <input type="text" id="productName" name="productName" required />

    <label for="quantity">수량:</label>
    <input type="text" id="quantity" name="quantity" required />

    <label for="price">가격:</label>
    <input type="text" id="price" name="price" required />

    <label for="saleprice">할인가격:</label>
    <input type="text" id="saleprice" name="saleprice" required />

    <label for="brand">브랜드:</label>
    <select id="brand" name="brand">
      <option value="none">=== 선택 ===</option>
      <option value="1">SATUR</option>
      <option value="2">SPAO</option>
      <option value="3">INSILENCE</option>
      <option value="4">MUSINSA STANDARD</option>
      <option value="5">LMOOD</option>
      <option value="6">COVERNAT</option>
      <option value="7">YALE</option>
    </select>

    <label for="category">카테고리:</label>
    <select id="category" name="category" v-model="category">
      <option value="none" selected>=== 선택 ===</option>
      <option value="1">상의</option>
      <option value="2">하의</option>
      <option value="3">아우터</option>
      <option value="4">원피스</option>
      <option value="5">스커트</option>
      <option value="6">신발</option>
      <option value="7">모자</option>
      <option value="8">가방</option>
      <option value="9">액세서리</option>
      <option value="10">스포츠용품</option>
    </select>

    <label for="style">스타일:</label>
    <select id="style" name="style">
      <option value="none">=== 선택 ===</option>
      <option value="1">캐주얼</option>
      <option value="2">시크</option>
      <option value="3">댄디</option>
      <option value="4">스트릿</option>
      <option value="5">비지니스 캐주얼</option>
      <option value="6">힙합</option>
      <option value="7">오피스</option>
      <option value="8">스포츠</option>
    </select>

    <!-- 상의 사이즈 입력 -->
    <div v-if="category == 1 || category == 3 || category == 4">
      <label for="shoulderWidth">어깨 너비:</label>
      <input
        type="number"
        id="shoulderWidth"
        name="shoulderWidth"
        step="1"
        placeholder="cm"
      />

      <label for="chestSize">가슴 둘레:</label>
      <input
        type="number"
        id="chestSize"
        name="chestSize"
        step="1"
        placeholder="cm"
      />

      <label for="armLength">팔 길이:</label>
      <input
        type="number"
        id="armLength"
        name="armLength"
        step="1"
        placeholder="cm"
      />

      <label for="topLength">상의 총 길이:</label>
      <input
        type="number"
        id="topLength"
        name="topLength"
        step="1"
        placeholder="cm"
      />
    </div>
    <!-- 하의 사이즈 입력 -->
    <div v-if="category == 2 || category == 4 || category == 5">
      <label for="waistline">허리 둘레:</label>
      <input
        type="number"
        id="waistline"
        name="waistline"
        step="1"
        placeholder="cm"
      />

      <label for="hipCircumference">엉덩이 둘레:</label>
      <input
        type="number"
        id="hipCircumference"
        name="hipCircumference"
        step="1"
        placeholder="cm"
      />

      <label for="thighCircumference">허벅지 둘레:</label>
      <input
        type="number"
        id="thighCircumference"
        name="thighCircumference"
        step="1"
        placeholder="cm"
      />

      <label for="crotchLength">밑위 길이:</label>
      <input
        type="number"
        id="crotchLength"
        name="crotchLength"
        step="1"
        placeholder="cm"
      />

      <label for="hemLength">밑단 길이:</label>
      <input
        type="number"
        id="hemLength"
        name="hemLength"
        step="1"
        placeholder="cm"
      />

      <label for="totalBottomLength">하의 총 길이:</label>
      <input
        type="number"
        id="totalBottomLength"
        name="totalBottomLength"
        step="1"
        placeholder="cm"
      />
    </div>
    <label for="thumbnail">상품 썸네일 사진 업로드:</label>
    <input
      type="file"
      id="thumbnail"
      name="thumbnail"
      accept="image/*"
      multiple
    />

    <label for="image">상품 사진 업로드:</label>
    <input type="file" id="image" name="image" accept="image/*" multiple />

    <button type="button" @click="sendData()">상품 등록</button>
    <button type="button" @click="goToUrl('/')">취소</button>
  </form>
</template>

<script>
import axios from "axios";
export default {
  name: "ProductRegisterPage",
  data() {
    return {
      category: null,
    };
  },
  methods: {
    async sendData() {
      const backend = 'http://www.lonuamall.kro.kr/api';
      // const backend = "http://localhost:8080";
      const productNameElement = document.getElementById("productName");
      const productName = productNameElement ? productNameElement.value : 0;

      const quantityElement = document.getElementById("quantity");
      const quantity = quantityElement ? quantityElement.value : 0;

      const priceElement = document.getElementById("price");
      const price = priceElement ? priceElement.value : 0;

      const salepriceElement = document.getElementById("saleprice");
      const saleprice = salepriceElement ? salepriceElement.value : 0;

      const brandElement = document.getElementById("brand");
      const brand = brandElement ? brandElement.value : 0;

      const categoryElement = document.getElementById("category");
      const category = categoryElement ? categoryElement.value : 0;

      const styleElement = document.getElementById("style");
      const style = styleElement ? styleElement.value : 0;

      const shoulderWidthElement = document.getElementById("shoulderWidth");
      const shoulderWidth = shoulderWidthElement
        ? shoulderWidthElement.value
        : 0;

      const chestSizeElement = document.getElementById("chestSize");
      const chestSize = chestSizeElement ? chestSizeElement.value : 0;

      const armLengthElement = document.getElementById("armLength");
      const armLength = armLengthElement ? armLengthElement.value : 0;

      const topLengthElement = document.getElementById("topLength");
      const topLength = topLengthElement ? topLengthElement.value : 0;

      const waistlineElement = document.getElementById("waistline");
      const waistline = waistlineElement ? waistlineElement.value : 0;

      const thighCircumferenceElement =
        document.getElementById("thighCircumference");
      const thighCircumference = thighCircumferenceElement
        ? thighCircumferenceElement.value
        : 0;

      const hipCircumferenceElement =
        document.getElementById("hipCircumference");
      const hipCircumference = hipCircumferenceElement
        ? hipCircumferenceElement.value
        : 0;

      const crotchLengthElement = document.getElementById("crotchLength");
      const crotchLength = crotchLengthElement ? crotchLengthElement.value : 0;

      const hemLengthElement = document.getElementById("hemLength");
      const hemLength = hemLengthElement ? hemLengthElement.value : 0;

      const totalBottomLengthElement =
        document.getElementById("totalBottomLength");
      const totalBottomLength = totalBottomLengthElement
        ? totalBottomLengthElement.value
        : 0;

      const thumbnailElement = document.getElementById("thumbnail");
      const thumbnail = thumbnailElement ? thumbnailElement.files : 0;

      const imageElement = document.getElementById("image");
      const image = imageElement ? imageElement.files : 0;

      // 기타 필요한 정보 수집

      let data = {
        brand_idx: brand,
        category_idx: category,
        style_idx: style,
        productName: productName,
        quantity: quantity,
        price: price,
        salePrice: saleprice,
        shoulderWidth: shoulderWidth,
        chestSize: chestSize,
        armLength: armLength,
        topLength: topLength,
        waistline: waistline,
        hipCircumference: hipCircumference,
        thighCircumference: thighCircumference,
        crotchLength: crotchLength,
        hemLength: hemLength,
        totalBottomLength: totalBottomLength,
        // 추가 필드들 추가
      };

      let formData = new FormData();
      let json = JSON.stringify(data);

      formData.append(
        "product",
        new Blob([json], { type: "application/json" })
      );
      Array.from(thumbnail).map((image) => {
        formData.append("productImage", image);
      });

      Array.from(image).map((image) => {
        formData.append("productIntrodImage", image);
      });

      let response = await axios.post(backend + "/product/register", formData, {
        "Content-Type": "multipart/form-data",
      });

      console.log(response.data);

      if (response.data.id !== null) {
        alert("상품등록에 성공했습니다.");
        window.location.href =
          "/productdetail/" + response.data.result.productIdx;
      }
    },

    // 취소 버튼 시 메인페이지로
    goToUrl(url) {
      window.location.href = url;
    },
  },
};
</script>

<style scoped>
body {
  font-family: Arial, sans-serif;
  margin: 20px;
}

form {
  max-width: 700px;
  margin: 0 auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 8px;
}

/* 레이블 스타일 */
label {
  display: block;
  margin-bottom: 8px;
  font-weight: bold;
}

/* 입력 필드 스타일 */
input[type="text"],
input[type="number"],
select,
button {
  width: 100%;
  padding: 10px;
  margin-bottom: 16px;
  box-sizing: border-box;
}

/* 파일 업로드 필드 스타일 */
input[type="file"] {
  margin-bottom: 16px;
}

/* 버튼 스타일 */
button {
  background-color: #4caf50;
  color: white;
  border: none;
  padding: 10px;
  border-radius: 4px;
  cursor: pointer;
}

button.cancel {
  background-color: #ccc;
}

/* 버튼 호버 상태 스타일 */
button:hover {
  background-color: #45a049;
}
</style>
