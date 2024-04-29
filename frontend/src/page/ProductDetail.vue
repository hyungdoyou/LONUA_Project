<template>
  <div class="loadingio-spinner-spinner" v-if="productStore.isLoading">
    <div class="ldio-f4nnk2ltl0v">
      <div></div>
      <div></div>
      <div></div>
      <div></div>
      <div></div>
      <div></div>
      <div></div>
      <div></div>
      <div></div>
      <div></div>
      <div></div>
      <div></div>
    </div>
  </div>
  <div id="productIdx"></div>
  <section id="container">
    <div class="pdt">
      <div class="pdt_detail">
        <div class="pdt_head">
          <div class="left_col">
            <div class="img_goods">
              <div class="img_area">
                <Splide
                  :options="{
                    type: 'slide',
                    rewind: true,
                    width: '100%',
                    perPage: 1,
                  }"
                  aria-label="My Favorite Images"
                >
                  <!-- <SplideSlide v-for="res in result" :key="res.productIdx">
                  <ProductCardComponent v-bind:Product="res" v-bind:like="likesStore.indexList.includes(res.productIdx)"/>
              </SplideSlide> -->
                  <SplideSlide
                    v-for="(image, index) in productStore.product.productImages"
                    :key="index"
                  >
                    <img :width="imgWidth" height="700" :src="image" />
                  </SplideSlide>
                </Splide>
              </div>
              <!-- <img id="img_01" width="500" height="700" v-if="productStore.product.productImages &&
                productStore.product.productImages.length > 0
                " :src="productStore.product.productImages[0]" /> -->
            </div>
            <!-- <div class="gallery_wrap">
              <ul id="gallery" style="max-width: 525px">
                <li id="gallery_img">
                  <a class="active" href="#" v-for="(image, index) in productStore.product
                    .productImages" :key="index">
                    <img width="30px" height="40px" margin-right="10px" :src="image" />
                  </a>
                </li>
              </ul>
            </div> -->
          </div>

          <div class="right_col pdt_info">
            <div class="h_group">
              <button type="button" class="my_brand"></button>
              <div class="h_group">
                <h2 class="brand">
                  <a>{{ productStore.product.brandName }}</a>
                </h2>
                <!-- ITDEV-1740 product_front div 신규생성하여 감싸줌 -->
                <div class="product_front">
                  <p class="front" id="spanOutItemProductName"></p>
                  <h3 class="product cottonusa">
                    {{ productStore.product.productName }}
                  </h3>
                  <!-- //공유 -->
                </div>
                <!-- //ITDEV-1740 -->
              </div>
            </div>
            <div class="price_wrap">
              <dl class="price item2">
                <dt class="normal">정상가</dt>
                <dd class="normal">
                  <em>{{ formattedPrice }}</em> 원
                </dd>
                <dt class="sale">할인적용가</dt>
                <dd class="sale">
                  <em>{{ formattedSalePrice }}</em
                  ><text>원</text>
                  <span class="discount_percent"
                    >{{
                      (
                        (1 -
                          productStore.product.salePrice /
                            productStore.product.price) *
                        100
                      ).toFixed(0)
                    }}%</span
                  >
                </dd>
              </dl>
            </div>
            <ul class="btn_group">
              <li>
                <button
                  type="button"
                  data-origin-place="top"
                  class="btn black"
                  name="maincart"
                  @click="openModal"
                >
                  쇼핑백담기
                </button>
                <ModalComponent ref="cartModal"></ModalComponent>
              </li>
            </ul>

            <dl class="info cottonusa">
              <dt>상품 요약 정보</dt>
              <dd>
                <ul>
                  <li>
                    <span>상품코드</span>
                    {{ productStore.product.productIdx }}
                  </li>
                  <li>
                    <span>소재</span>
                    상세페이지 참조
                  </li>
                  <li>
                    <span>배송비</span>
                    &#xBB34;&#xB8CC; 제주 및 도서지역 추가배송비 상품상세 영역
                    고지 개선
                    <p>
                      (제주 : 3,000&#xC6D0; / 도서산간 : 5,000&#xC6D0;) 추가
                    </p>
                  </li>
                  <li>
                    <span>배송정보</span>
                    &#xACB0;&#xC81C;&#xC644;&#xB8CC; &#xD6C4; &#xD3C9;&#xADE0;
                    2&#xC77C; &#xC774;&#xB0B4;
                    &#xCD9C;&#xACE0;(&#xACF5;&#xD734;&#xC77C; &#xC81C;&#xC678;)
                  </li>
                </ul>
              </dd>
            </dl>
          </div>
        </div>
      </div>
      <!-- tab -->
      <ul id="detail" class="tab tab_item4">
        <li class="active"><a href="#detail">DETAIL</a></li>
        <li><a href="#review">REVIEW </a></li>
        <li><a href="#qa">Q&amp;A</a></li>
        <li><a href="#delivery">RETURN &amp; DELIVERY</a></li>
      </ul>
      <div class="pdt_contents detail">
        <div
          class="marketing"
          v-for="(introimage, index) in productStore.product
            .productIntrodImages"
          :key="index"
        >
          <!--제품 상세페이지 이미지 띄우는곳-->
          <img :src="introimage" /> <br />
        </div>
        <p class="responsibility_noti">
          본 상품 정보 및 거래 조건의 내용은 판매자가 직접 등록한 것으로서
          등록된 정보에 대한 책임은 판매자에게 있습니다.
        </p>
      </div>

      <ul id="review" class="tab tab_item4">
        <li><a href="#detail">DETAIL</a></li>
        <li class="active"><a href="#review">REVIEW </a></li>
        <li><a href="#qa">Q&amp;A</a></li>
        <li><a href="#delivery">RETURN &amp; DELIVERY</a></li>
      </ul>
      <!-- //tab -->
      <div class="pdt_contents pdt_review">
        <div id="reviewList">
          <table class="pdt_review_table tbl_review">
            <colgroup>
              <col style="width: 160px" />
              <col style="width: auto" />
            </colgroup>
            <thead>
              <tr v-if="reviewStore.reviews && reviewStore.reviews.length > 0">
                <th>평가</th>
                <th>리뷰</th>
              </tr>
            </thead>
            <tbody
              v-if="!reviewStore.reviews || reviewStore.reviews.length === 0"
            >
              <tr>
                <td colspan="2" class="no_data">
                  첫 리뷰어가 되어 보세요!<br />추가 <span>포인트</span> 적립해
                  드려요.
                  <a href="/UserOrders">리뷰 작성하러 가기</a>
                </td>
              </tr>
            </tbody>
            <tbody v-if="reviewStore.reviews && reviewStore.reviews.length > 0">
              <tr v-for="(review, index) in reviewStore.reviews" :key="index">
                <td>
                  <div class="pdt_review_align_wrap">
                    <div class="pdt_review_valign">
                      <div class="star-grade">
                        {{
                          review.evaluation === 1
                            ? "★"
                            : review.evaluation === 2
                            ? "★★"
                            : review.evaluation === 3
                            ? "★★★"
                            : review.evaluation === 4
                            ? "★★★★"
                            : review.evaluation === 5
                            ? "★★★★★"
                            : ""
                        }}
                      </div>
                    </div>
                  </div>
                </td>
                <td>
                  <ul class="pdt_review_photo">
                    <li class="open-layer open-pop_photoDetail">
                      <img :src="review.reviewPhoto" />
                    </li>
                  </ul>

                  <div class="pdt_review_info">
                    <div class="product_review_info_left">
                      <div class="pdt_review_option">
                        <p>
                          <span></span>
                        </p>
                      </div>
                    </div>
                    <p class="product_review_info_right">
                      <em>{{ review.name }}</em>
                      <span>{{ review.updatedAt }}</span>
                    </p>
                  </div>

                  <div class="pdt_review_detail">
                    <p class="pdt_review_text">
                      {{ review.reviewContent }}
                    </p>
                  </div>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
      <!-- //REVIEW contents -->
      <!--Q&A contents //-->
      <!-- tab -->
      <ul id="qa" class="tab tab_item4">
        <li><a href="#detail">DETAIL</a></li>
        <li><a href="#review">REVIEW </a></li>
        <li class="active"><a href="#qa">Q&amp;A</a></li>
        <li><a href="#delivery">RETURN &amp; DELIVERY</a></li>
      </ul>
      <!-- //tab -->
      <div class="pdt_contents qna">
        <div class="fr" style="text-align: right">
          <button type="button" class="btn black" @click="openQaModal">
            상품문의
          </button>
        </div>
        <productQaRegisterModal ref="qaModal"></productQaRegisterModal>

        <table class="cols tbl_qna my-qna">
          <colgroup>
            <col style="width: 98px" />
            <col />
            <col style="width: 115px" />
          </colgroup>
          <tbody
            v-if="!qnaStore.productQnas || qnaStore.productQnas.length === 0"
          >
            <tr>
              <td colspan="3" class="no_data">
                상품문의가 없습니다.<br />
                <span>질문</span>은 언제나 환영!
              </td>
            </tr>
          </tbody>
          <tbody>
            <template
              v-for="(productQna, index) in qnaStore.productQnas"
              :key="index"
            >
              <tr class="">
                <td>
                  <em class="icon_finish">{{
                    productQna.hasAnswer ? "답변완료" : "답변 대기 중"
                  }}</em>
                </td>
                <td class="question">
                  <p class="tit">
                    <a @click="qnaDetailtoggle(index)" style="cursor: pointer">
                      {{ productQna.questionTitle }}
                    </a>
                  </p>
                  <span class="name">{{ productQna.userName }}</span>
                </td>
                <td class="date">{{ productQna.updatedAt }}</td>
              </tr>
              <tr class="question_detail" v-if="qnaDetail === index">
                <td colspan="3">
                  <div class="cont">
                    <div class="ask">
                      <strong class="tit_sub">질문</strong>
                      <p class="txt">{{ productQna.questionContent }}</p>
                    </div>
                    <!-- <div class="answer">
                      <strong class="tit_sub">답변</strong>
                      <p class="txt">
                        <span class="date">2024.02.04 04:07</span>
                        답변 테이블도 만들었어야 했네..?
                      </p>
                    </div> -->
                  </div>
                </td>
              </tr>
            </template>
          </tbody>
        </table>

        <!-- qa write -->

        <!-- //qa write -->
        <!-- qa read -->
        <div id="qaList"></div>
      </div>
      <!-- //Q&A contents -->
      <!--Return & Delivery contents //-->
      <!-- tab -->
      <ul id="delivery" class="tab tab_item4">
        <li><a href="#detail">DETAIL</a></li>
        <li><a href="#review">REVIEW </a></li>
        <li><a href="#qa">Q&amp;A</a></li>
        <li class="active"><a href="#delivery">RETURN &amp; DELIVERY</a></li>
      </ul>
      <!-- //tab -->
      <div class="pdt_contents delivery">
        <div class="title">
          <h5>배송/교환/반품/AS 관련 유의사항</h5>
          <p>
            상품상세설명에 배송/교환/반품/취소 관련 안내가 기재된 경우 다음
            안내사항보다 우선 적용됩니다.
          </p>
        </div>
        <table>
          <colgroup>
            <col width="270" />
            <col />
          </colgroup>
          <tbody>
            <tr>
              <th>배송정보</th>
              <td>
                <ul>
                  <li>
                    상품별로 상품 특성 및 배송지에 따라 배송유형 및 소요기간이
                    달라집니다.
                  </li>
                  <li>
                    일부 주문상품 또는 예약상품의 경우 기본 배송일 외에 추가
                    배송 소요일이 발생될 수 있습니다.
                  </li>
                  <li>
                    동일 브랜드의 상품이라도 상품별 출고일시가 달라 각각 배송될
                    수 있습니다.
                  </li>
                  <li>
                    제주 및 도서산간 지역은 출고, 반품, 교환시 추가 배송비(항공,
                    도선료)가 부과 될 수 있습니다.
                  </li>
                  <li>
                    상품의 배송비는 공급업체의 정책에 따라 다르오며 공휴일 및
                    휴일은 배송이 불가합니다.
                  </li>
                </ul>
              </td>
            </tr>
            <tr>
              <th>취소/반품/교환 안내</th>
              <td>
                <ul>
                  <!--  [ITDEV-4073]교환 반품 회수지정보    -->
                  <li>
                    회수지 정보 : &#xACBD;&#xAE30;&#xB3C4;
                    &#xC6A9;&#xC778;&#xC2DC; &#xCC98;&#xC778;&#xAD6C;
                    &#xC774;&#xB3D9;&#xC74D;
                    &#xB355;&#xC131;&#xC0B0;&#xB2E8;2&#xB85C; 5-24
                    &#xBA54;&#xC774;&#xC800;&#xC6D4;&#xB4DC;
                    &#xBB3C;&#xB958;&#xC13C;&#xD130;
                  </li>
                  <li>
                    동일 브랜드의 상품이라도 교환/반품 회수지가 다를 수
                    있습니다. 상품상세 정보 또는 회수지 정보를 확인하신 후
                    반드시 지정된 회수지로 보내주세요.
                  </li>
                  <li>
                    상품을 지정된 회수지가 아닌곳으로 보내실 경우 택배 분실 또는
                    재발송에 따른 추가 비용이 발생할 수 있습니다.
                  </li>
                  <li>
                    고객센터나 "My Page>주문취소/교환/반품 신청"을 통한
                    교환/반품 접수 없이 상품을 회수지로 보내실 경우 재발송에
                    따른 추가 비용이 발생할 수 있으며 경우에 따라서는
                    교환/반품이 어려울 수 있습니다.
                  </li>
                  <!--  [ITDEV-4073]교환 반품 회수지정보    // -->
                  <li class="bold">
                    상품하자 이외 사이즈, 색상교환 등 단순 변심에 의한 교환/반품
                    택배비 고객부담으로 왕복택배비가 발생합니다. (전자상거래
                    등에서의 소비자보호에 관한 법률 제18조(청약 철회등)9항에
                    의거 소비자의 사정에 의한 청약 철회 시 택배비는 소비자
                    부담입니다.)
                  </li>
                  <li>
                    결제완료 직후 즉시 주문취소는 "MY Page> 취소/교환/반품
                    신청"에서 직접 처리 가능합니다.
                  </li>
                  <li>
                    주문완료 후 재고 부족 등으로 인해 주문 취소 처리가 될 수도
                    있는 점 양해 부탁드립니다.
                  </li>
                  <li>
                    주문상태가 상품준비중인 경우 취소신청이 가능하며 판매자의
                    승인 여부(이미 배송을 했거나 포장을 완료한 경우)에 따라
                    취소가 불가능할 수 있습니다.
                  </li>
                  <li>
                    교환 신청은 최초 1회에 한하며, 교환 배송 완료 후에는 추가
                    교환 신청은 불가합니다.
                  </li>
                  <li>
                    반품/교환은 미사용 제품에 한해 배송완료 후 7일 이내 접수하여
                    주십시오.
                  </li>
                  <li>
                    임의반품은 불가하오니 반드시 고객센터나 "MY Page>
                    주문취소/교환/반품 신청"을 통해서 신청접수를 하시기
                    바랍니다.
                  </li>
                  <li>
                    상품하자, 오배송의 경우 택배비 무료로 교환/반품이 가능하지만
                    모니터의 색상차이, 착용감, 사이즈의 개인의 선호도는 상품의
                    하자 사유가 아닙니다.
                  </li>
                  <!--<li>단 위생용품 및 가전, 가구, 귀금속 등의 경우 취소/교환/반품 요청이 제한될 수 있습니다.</li>-->
                  <!--<li>주문제작 상품 및 상품의 본품박스, 택 등이 제거되어 있을 경우 반품 / 교환이 불가능합니다.</li>-->
                  <li>
                    고객 부주의로 상품이 훼손, 변경된 경우 반품 / 교환이 불가능
                    합니다.
                  </li>
                  <li>
                    취소/반품 대금환불이 지연 시 전자상거래법에 의거하여
                    환불지연 배상처리 절차가 진행됩니다.
                  </li>
                </ul>
              </td>
            </tr>
            <tr>
              <th>반품/교환 불가능한 경우</th>
              <td>
                <ul>
                  <li>
                    제품을 사용 또는 훼손한 경우, 사은품 누락, 상품 TAG, 보증서,
                    상품 부자재가 제거 혹은 분실된 경우
                  </li>
                  <li>
                    밀봉포장을 개봉했거나 내부 포장재를 훼손 또는 분실한
                    경우(단, 제품확인을 위한 개봉 제외)
                  </li>
                  <li>
                    시간이 경과되어 재판매가 어려울 정도로 상품가치가 상실된
                    경우
                  </li>
                  <li>
                    고객님의 요청에 따라 주문 제작되어 고객님 외에 사용이 어려운
                    경우
                  </li>
                  <li>배송된 상품이 설치가 완료된 경우(가전, 가구 등)</li>
                  <li>
                    기타 전자상거래 등에서의 소비자보호에 관한 법률이 정하는
                    청약철회 제한사유에 해당하는 경우
                  </li>
                </ul>
              </td>
            </tr>
            <tr>
              <th>A/S 안내</th>
              <td>
                <ul>
                  <li>
                    A/S 기준이나 가능여부는 브랜드와 상품에 따라 다르므로 관련
                    문의는 더블유컨셉 고객센터를 통해 부탁드립니다.
                  </li>
                  <li>
                    상품불량에 의한 반품, 교환, A/S, 환불, 품질보증 및 피해보상
                    등에 관한 사항은 소비자분쟁해결기준(공정거래위원회 고시)에
                    따라 받으실 수 있습니다.
                  </li>
                </ul>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
    <!-- tab -->
  </section>
</template>

<script>
import { mapStores } from "pinia";
import { useProductStore } from "@/stores/useProductStore.js";
import { useReviewStore } from "@/stores/useReviewStore.js";
import { useQnaStore } from "@/stores/useQnaStore.js";
import ModalComponent from "@/components/ModalComponent.vue";
import productQaRegisterModal from "@/components/ProductQaRegisterModal.vue";
import { Splide, SplideSlide } from "@splidejs/vue-splide";
import "@splidejs/vue-splide/css";
export default {
  name: "ProductDetailPage",
  data() {
    return {
      qnaDetail: false,
      imgWidth: "0",
    };
  },
  components: {
    ModalComponent,
    productQaRegisterModal,
    Splide,
    SplideSlide,
  },
  computed: {
    ...mapStores(useProductStore, useReviewStore, useQnaStore),

    formattedPrice() {
      // 가격을 천 단위로 구분된 문자열로 변환
      const price = this.productStore.product.price;
      if (price !== undefined && price !== null) {
        return price.toLocaleString();
      }
      return "";
    },
    formattedSalePrice() {
      // 가격을 천 단위로 구분된 문자열로 변환
      const salePrice = this.productStore.product.salePrice;
      if (salePrice !== undefined && salePrice !== null) {
        return salePrice.toLocaleString();
      }
      return "";
    },
  },
  methods: {
    openModal() {
      this.productStore.addCartAxios();
      // 모달 열기
      this.$refs.cartModal.openModal();
    },
    qnaDetailtoggle(index) {
      this.qnaDetail = this.qnaDetail === index ? null : index;
    },
    openQaModal() {
      // 모달 열기
      this.$refs.qaModal.openModal();
    },
  },

  created() {
    this.productStore.idx = this.$route.params.idx;
    this.qnaStore.getProductQna(this.$route.params.idx);
    this.productStore.getProduct(this.$route.params.idx);
    this.reviewStore.getReview(this.$route.params.idx);
  },
  mounted() {
    this.imgWidth = "500";
    this.productStore.isLoading = false;
  },
};
</script>

<style scoped>
.pdt_contents.qna .cols.tbl_qna.my-qna .no_data span {
  color: #fa5500;
}
.pdt_contents.qna .cols.tbl_qna.my-qna .no_data {
  font-size: 20px;
  line-height: 28px;
  letter-spacing: -0.4px;
  text-align: center;
  color: #333;
  padding: 80px 0;
}
.pdt_contents.pdt_review .pdt_review_table td.no_data a {
  display: block;
  width: 198px;
  font-size: 14px;
  line-height: 40px;
  color: #fff;
  background: #777;
  margin: 20px auto 0;
}
.pdt_contents.pdt_review .pdt_review_table td.no_data span {
  color: #fa5500;
}
.pdt_contents.pdt_review .pdt_review_table td.no_data {
  font-size: 20px;
  line-height: 28px;
  letter-spacing: -0.4px;
  text-align: center;
  color: #333;
  padding: 80px 0;
}

/** 리뷰 css start */
.pdt_contents.pdt_review .pdt_review_info p * {
  display: inline-block;
  font-family: "ProximaNova-Regular", "Noto Sans KR";
  font-size: 14px;
  line-height: 22px;
  vertical-align: top;
  color: #777;
}

.pdt_contents.pdt_review .product_review_info_right em {
  margin-right: 20px;
  float: left;
}

.pdt_contents.pdt_review .pdt_review_info {
  font-size: 14px;
  line-height: 22px;
  display: -webkit-box;
  display: flex;
  -webkit-justify-content: space-between;
  justify-content: space-between;
}

.pdt_contents.pdt_review .pdt_review_text {
  display: block;
  font-size: 25px;
  line-height: 80px;
  color: #333;
  width: 100%;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
  cursor: pointer;
}

.pdt_contents.pdt_review .pdt_review_photo li img {
  width: 100px;
  height: 100px;
  object-fit: cover;
  float: left;
  margin-left: 10px;
}

.pdt_review_detail.pdt_review {
  position: relative;
  min-height: 107px;
  font-family: "ProximaNova-Regular", "Noto Sans KR";
  padding: 30px 0;
  margin-left: 10px;
  text-align: left;
}

#centereva {
  text-align: center;
}

/** 리뷰 css end */
/* 상품 문의 관련 start */
.pdt_contents.qna .tbl_qna tr.question_detail .cont .tit_sub {
  display: block;
  font-family: "NotoSansKR-Medium";
  margin-bottom: 10px;
  font-size: 20px;
}

.pdt_contents.qna .tbl_qna tr.question_detail .cont .answer {
  margin-top: 27px;
  padding-top: 21px;
  border-top: 1px dashed #e9e9e9;
}

.pdt_contents.qna .tbl_qna tr.question_detail td {
  text-align: left;
  background: #f7f7f7;
  padding: 0 25px;
  border: none;
}

.pdt_contents.qna .tbl_qna tr.active.question_detail {
  display: table-row;
}

.pdt_contents.qna .tbl_qna tr.question_detail .cont {
  padding: 20px 90px 22px 73px;

  line-height: 1.5;
}

table {
  border-top: 2px solid #171717;
  border-bottom: 1px solid #171717;
  table-layout: fixed;
  margin-bottom: 60px;
}

.pdt_contents.qna .tbl_qna tr td {
  height: auto;
  padding: 24px 0;
  font-family: "ProximaNova-Regular", "Noto Sans KR";
  text-align: center;
  vertical-align: top;
}

/* .pdt_contents.qna .icon_finish {
    width: 50px;
    background-position: 500px 100px;
} */
.pdt_contents.qna .tbl_qna tr td.question {
  height: auto;
  text-align: left;
  position: relative;
}

.pdt_contents.qna .tbl_qna tr td.question .name {
  width: 172px;
  display: block;
  position: absolute;
  top: 24px;
  right: 0;
}

.pdt_contents.qna .tbl_qna tr td.date {
  text-align: right;
  padding-right: 25px;
}

.pdt_contents.qna .icon_ready,
.pdt_contents.qna .icon_rock {
  display: inline-block;
  overflow: hidden;
  height: 18px;
  background: url(//static.wconcept.co.kr/web/images/common/spr-mypage.png) 0 0
    no-repeat;
  line-height: 99em;
  vertical-align: top;
}

.pdt_contents.qna .icon_rock {
  width: 15px;
  margin: 0 0 0 7px;
  background-position: -400px -50px;
}

table tbody td {
  padding: 0 19px;
  color: #000;
  font-size: 14px;
  border-top: 1px solid #d9d9d9;
}

/* 상품 문의 관련 end */
#gallery_img,
img {
  /* margin-right: 5px; */
  display: block;
  margin: auto;
}

.pdt_review_table {
  width: 100%;
  border-collapse: collapse;
  margin-bottom: 20px;
}

/* Style the table headers */
.pdt_review_table th {
  background-color: #dddddd;
  padding: 10px;
  text-align: left;
  border: 1px solid #dddddd;
  border-bottom: 1px solid #dddddd;
}

/* Style the table rows */
.pdt_review_table tbody tr {
  border: 1px solid #dddddd;
  border-bottom: 1px solid;
}

/* Style alternating rows for better readability */
.pdt_review_table tbody tr:nth-child(even) {
  background-color: #f9f9f9;
}

/* Style the cells within the table */
.pdt_review_table td {
  padding: 10px;
  text-align: left;
  border: 1px solid #dddddd;
  margin-left: 10px;
}

/* Style the image within the table */
.pdt_review_table img {
  max-width: 100%;
  width: 100px;
  height: 150px;
  display: block;
  margin: 0 auto;
}

/*! normalize.css v3.0.3 | MIT License | github.com/necolas/normalize.css */

html {
  font-family: sans-serif;
  -ms-text-size-adjust: 100%;
  -webkit-text-size-adjust: 100%;
}

body {
  margin: 0;
}

audio,
canvas,
progress,
video {
  display: inline-block;
  vertical-align: baseline;
}

audio:not([controls]) {
  display: none;
  height: 0;
}

[hidden],
template {
  display: none;
}

a {
  background-color: transparent;
}

a:active,
a:hover {
  outline: 0;
}

abbr[title] {
  border-bottom: 1px dotted;
}

b,
strong {
  font-weight: bold;
}

dfn {
  font-style: italic;
}

h1 {
  font-size: 2em;
  margin: 0.67em 0;
}

mark {
  color: #000;
  background: #ff0;
}

small {
  font-size: 80%;
}

sub,
sup {
  font-size: 75%;
  line-height: 0;
  position: relative;
  vertical-align: baseline;
}

sup {
  top: -0.5em;
}

sub {
  bottom: -0.25em;
}

img {
  border: 0;
}

svg:not(:root) {
  overflow: hidden;
}

figure {
  margin: 1em 40px;
}

hr {
  box-sizing: content-box;
  height: 0;
}

pre {
  overflow: auto;
}

code,
kbd,
pre,
samp {
  font-family: monospace, monospace;
  font-size: 1em;
}

button,
input,
optgroup,
select,
textarea {
  font: inherit;
  margin: 0;
  color: inherit;
}

button {
  overflow: visible;
}

button,
select {
  text-transform: none;
}

button,
html input[type="button"],
input[type="reset"],
input[type="submit"] {
  cursor: pointer;
}

button[disabled],
html input[disabled] {
  cursor: default;
}

button::-moz-focus-inner,
input::-moz-focus-inner {
  padding: 0;
  border: 0;
}

input {
  line-height: normal;
}

input[type="checkbox"],
input[type="radio"] {
  box-sizing: border-box;
  padding: 0;
}

input[type="number"]::-webkit-inner-spin-button,
input[type="number"]::-webkit-outer-spin-button {
  height: auto;
}

input[type="search"] {
  box-sizing: content-box;
}

input[type="search"]::-webkit-search-cancel-button,
input[type="search"]::-webkit-search-decoration {
  -webkit-appearance: none;
}

fieldset {
  margin: 0 2px;
  padding: 0.35em 0.625em 0.75em;
  border: 1px solid #c0c0c0;
}

legend {
  padding: 0;
  border: 0;
}

textarea {
  overflow: auto;
}

optgroup {
  font-weight: bold;
}

table {
  border-spacing: 0;
  border-collapse: collapse;
}

td,
th {
  padding: 0;
}

* {
  font-weight: normal;
  -webkit-box-sizing: border-box;
  -moz-box-sizing: border-box;
  box-sizing: border-box;
  font-weight: normal;
  font-style: normal;
}

html,
body {
  width: 100%;
  height: 100%;
}

body {
  -webkit-font-smoothing: antialiased;
  min-width: 1240px;
  font-family: "ProximaNova-Light", "Noto Sans KR", "Apple SD Gothic Neo",
    AppleGothic, "돋움", Dotum, Tahoma, Verdana, Geneva, Arial, Helvetica,
    sans-serif;
  font-size: 12px;
  font-weight: normal;
}

a {
  text-decoration: none;
  color: #000;
}

address,
em {
  font-style: normal;
}

hr {
  display: none;
}

table {
  width: 100%;
  border-collapse: separate;
}

th,
td {
  /* word-break: break-all; */
  background-clip: padding-box;
}

th.posr,
td.posr {
  position: relative;
}

caption {
  display: inline-block;
  height: 0;
  overflow: hidden;
  text-indent: -9999px;
  width: 0;
}

fieldset {
  border: none;
}

form {
  margin: 0;
}

input[type="radio"],
input[type="checkbox"] {
  padding: 0;
}

input[type="number"]::-webkit-inner-spin-button,
input[type="number"]::-webkit-outer-spin-button {
  -webkit-appearance: none;
  margin: 0;
}

button {
  width: auto;
  border: 0;
  padding: 0;
  background: none;
  font-weight: normal;
}

textarea {
  resize: none;
  border: 1px solid #bfbfbf;
}

a,
img,
input,
button,
select,
textarea {
  vertical-align: middle;
}

a:focus,
a:active,
a:hover,
button:focus,
button:active,
button:hover {
  text-decoration: none;
}

ul,
ol,
dl {
  margin: 0;
  padding: 0;
}

li,
dl {
  margin: 0;
  padding: 0;
  list-style: none !important;
}

p {
  margin: 0;
}

strong,
b {
  font-weight: normal;
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
  font-family: "ProximaNova-Semibold", "Apple SD Gothic Neo",
    "NotoSansKR-Medium", "Malgun Gothic", "맑은 고딕", sans-serif;
  font-size: 14px;
}

a.btn:disabled,
button.btn:disabled,
input.btn:disabled,
span.btn:disabled {
  border-color: #d9d9d9;
  color: #a4a4a4;
}

a.btn.black,
button.btn.black,
input.btn.black,
span.btn.black {
  border-color: #000;
  background-color: #000;
  color: #fff;
}

textarea {
  height: 270px;
  font-size: 14px;
  line-height: 22px;
  letter-spacing: -0.28px;
  color: #777;
  padding: 14px 20px;
}

textarea::-webkit-input-placeholder {
  line-height: 28px;
}

textarea::-moz-input-placeholder {
  line-height: 28px;
}

.tab {
  width: 100%;
  border-bottom: 2px solid #000;
}

.tab:after {
  display: block;
  clear: both;
  content: "";
}

.tab li {
  float: left;
  border: 1px solid #b5b5b5;
  border-bottom: none;
  border-left-width: 0;
  background-color: #fff;
  height: 60px;
}

.tab li:first-child {
  border-left-width: 1px;
}

.tab li button {
  display: block;
  height: 100%;
  width: 100%;
  text-align: center;
  color: #333;
  font-size: 18px;
  font-family: "Noto Sans KR";
}

.tab li.active {
  border: 2px solid #000;
  border-bottom: none;
  height: 62px;
  margin-bottom: -2px;
}

.tab li.active button {
  color: #000;
}

.tab.tab_item4 li {
  width: 25%;
}

@font-face {
  font-family: "NotoSansKR-Medium";
  font-style: normal;
  font-weight: 500;
  src: url("fonts/notosanskr/NotoSansKR-Medium.woff2") format("woff2"),
    url("fonts/notosanskr/NotoSansKR-Medium.woff") format("woff"),
    url("fonts/notosanskr/NotoSansKR-Medium.otf") format("opentype");
}

@font-face {
  font-family: "ProximaNova-Thin";
  src: url("fonts/proximanova/proximanova-thin-webfont.eot");
  src: url("fonts/proximanova/proximanova-thin-webfont.eot")
      format("embedded-opentype"),
    url("fonts/proximanova/proximanova-thin-webfont.woff2") format("woff2"),
    url("fonts/proximanova/proximanova-thin-webfont.woff") format("woff"),
    url("fonts/proximanova/proximanova-thin-webfont.ttf") format("truetype"),
    url("fonts/proximanova/proximanova-thin-webfont.svg") format("svg");
  font-weight: normal;
  font-style: normal;
}

@font-face {
  font-family: "ProximaNova-Thin_italic";
  src: url("fonts/proximanova/proximanova-thinit-webfont.eot");
  src: url("fonts/proximanova/proximanova-thinit-webfont.eot")
      format("embedded-opentype"),
    url("fonts/proximanova/proximanova-thinit-webfont.woff2") format("woff2"),
    url("fonts/proximanova/proximanova-thinit-webfont.woff") format("woff"),
    url("fonts/proximanova/proximanova-thinit-webfont.ttf") format("truetype"),
    url("fonts/proximanova/proximanova-thinit-webfont.svg") format("svg");
  font-weight: normal;
  font-style: normal;
}

@font-face {
  font-family: "ProximaNova-Light";
  src: url("fonts/proximanova/proximanova-light-webfont.eot");
  src: url("fonts/proximanova/proximanova-light-webfont.eot")
      format("embedded-opentype"),
    url("fonts/proximanova/proximanova-light-webfont.woff2") format("woff2"),
    url("fonts/proximanova/proximanova-light-webfont.woff") format("woff"),
    url("fonts/proximanova/proximanova-light-webfont.ttf") format("truetype"),
    url("fonts/proximanova/proximanova-light-webfont.svg") format("svg");
  font-weight: normal;
  font-style: normal;
}

@font-face {
  font-family: "ProximaNova-Light_italic";
  src: url("fonts/proximanova/proximanova-lightit-webfont.eot");
  src: url("fonts/proximanova/proximanova-lightit-webfont.eot")
      format("embedded-opentype"),
    url("fonts/proximanova/proximanova-lightit-webfont.woff2") format("woff2"),
    url("fonts/proximanova/proximanova-lightit-webfont.woff") format("woff"),
    url("fonts/proximanova/proximanova-lightit-webfont.ttf") format("truetype"),
    url("fonts/proximanova/proximanova-lightit-webfont.svg") format("svg");
  font-weight: normal;
  font-style: normal;
}

@font-face {
  font-family: "ProximaNova-Regular";
  src: url("fonts/proximanova/proximanova-regular-webfont.eot");
  src: url("fonts/proximanova/proximanova-regular-webfont.eot")
      format("embedded-opentype"),
    url("fonts/proximanova/proximanova-regular-webfont.woff2") format("woff2"),
    url("fonts/proximanova/proximanova-regular-webfont.woff") format("woff"),
    url("fonts/proximanova/proximanova-regular-webfont.ttf") format("truetype"),
    url("fonts/proximanova/proximanova-regular-webfont.svg") format("svg");
  font-weight: normal;
  font-style: normal;
}

@font-face {
  font-family: "ProximaNova_italic";
  src: url("fonts/proximanova/proximanova-regularit-webfont.eot");
  src: url("fonts/proximanova/proximanova-regularit-webfont.eot")
      format("embedded-opentype"),
    url("fonts/proximanova/proximanova-regularit-webfont.woff2") format("woff2"),
    url("fonts/proximanova/proximanova-regularit-webfont.woff") format("woff"),
    url("fonts/proximanova/proximanova-regularit-webfont.ttf")
      format("truetype"),
    url("fonts/proximanova/proximanova-regularit-webfont.svg") format("svg");
  font-weight: normal;
  font-style: normal;
}

@font-face {
  font-family: "ProximaNova-Medium";
  src: url("fonts/proximanova/proximanova-medium-webfont.eot");
  src: url("fonts/proximanova/proximanova-medium-webfont.eot")
      format("embedded-opentype"),
    url("fonts/proximanova/proximanova-medium-webfont.woff2") format("woff2"),
    url("fonts/proximanova/proximanova-medium-webfont.woff") format("woff"),
    url("fonts/proximanova/proximanova-medium-webfont.ttf") format("truetype"),
    url("fonts/proximanova/proximanova-medium-webfont.svg") format("svg");
  font-weight: normal;
  font-style: normal;
}

@font-face {
  font-family: "ProximaNova-Medium_italic";
  src: url("fonts/proximanova/proximanova-mediumit-webfont.eot");
  src: url("fonts/proximanova/proximanova-mediumit-webfont.eot")
      format("embedded-opentype"),
    url("fonts/proximanova/proximanova-mediumit-webfont.woff2") format("woff2"),
    url("fonts/proximanova/proximanova-mediumit-webfont.woff") format("woff"),
    url("fonts/proximanova/proximanova-mediumit-webfont.ttf") format("truetype"),
    url("fonts/proximanova/proximanova-mediumit-webfont.svg") format("svg");
  font-weight: normal;
  font-style: normal;
}

@font-face {
  font-family: "ProximaNova-Semibold";
  src: url("fonts/proximanova/proximanova-semibold-webfont.eot");
  src: url("fonts/proximanova/proximanova-semibold-webfont.eot")
      format("embedded-opentype"),
    url("fonts/proximanova/proximanova-semibold-webfont.woff2") format("woff2"),
    url("fonts/proximanova/proximanova-semibold-webfont.woff") format("woff"),
    url("fonts/proximanova/proximanova-semibold-webfont.ttf") format("truetype"),
    url("fonts/proximanova/proximanova-semibold-webfont.svg") format("svg");
  font-weight: normal;
  font-style: normal;
}

@font-face {
  font-family: "ProximaNova-Semibold_italic";
  src: url("fonts/proximanova/proximanova-semiboldit-webfont.eot");
  src: url("fonts/proximanova/proximanova-semiboldit-webfont.eot")
      format("embedded-opentype"),
    url("fonts/proximanova/proximanova-semiboldit-webfont.woff2")
      format("woff2"),
    url("fonts/proximanova/proximanova-semiboldit-webfont.woff") format("woff"),
    url("fonts/proximanova/proximanova-semiboldit-webfont.ttf")
      format("truetype"),
    url("fonts/proximanova/proximanova-semiboldit-webfont.svg") format("svg");
  font-weight: normal;
  font-style: normal;
}

@font-face {
  font-family: "ProximaNova-Bold";
  src: url("fonts/proximanova/proximanova-bold-webfont.eot");
  src: url("fonts/proximanova/proximanova-bold-webfont.eot")
      format("embedded-opentype"),
    url("fonts/proximanova/proximanova-bold-webfont.woff2") format("woff2"),
    url("fonts/proximanova/proximanova-bold-webfont.woff") format("woff"),
    url("fonts/proximanova/proximanova-bold-webfont.ttf") format("truetype"),
    url("fonts/proximanova/proximanova-bold-webfont.svg") format("svg");
  font-weight: normal;
  font-style: normal;
}

@font-face {
  font-family: "ProximaNova-Bold_italic";
  src: url("fonts/proximanova/proximanova-boldit-webfont.eot");
  src: url("fonts/proximanova/proximanova-boldit-webfont.eot")
      format("embedded-opentype"),
    url("fonts/proximanova/proximanova-boldit-webfont.woff2") format("woff2"),
    url("fonts/proximanova/proximanova-boldit-webfont.woff") format("woff"),
    url("fonts/proximanova/proximanova-boldit-webfont.ttf") format("truetype"),
    url("fonts/proximanova/proximanova-boldit-webfont.svg") format("svg");
  font-weight: normal;
  font-style: normal;
}

@font-face {
  font-family: "ProximaNova-Extrabld";
  src: url("fonts/proximanova/proximanova-extrabold-webfont.eot");
  src: url("fonts/proximanova/proximanova-extrabold-webfont.eot")
      format("embedded-opentype"),
    url("fonts/proximanova/proximanova-extrabold-webfont.woff2") format("woff2"),
    url("fonts/proximanova/proximanova-extrabold-webfont.woff") format("woff"),
    url("fonts/proximanova/proximanova-extrabold-webfont.ttf")
      format("truetype"),
    url("fonts/proximanova/proximanova-extrabold-webfont.svg") format("svg");
  font-weight: normal;
  font-style: normal;
}

@font-face {
  font-family: "ProximaNova-Extrabld_italic";
  src: url("fonts/proximanova/proximanova-extraboldit-webfont.eot");
  src: url("fonts/proximanova/proximanova-extraboldit-webfont.eot")
      format("embedded-opentype"),
    url("fonts/proximanova/proximanova-extraboldit-webfont.woff2")
      format("woff2"),
    url("fonts/proximanova/proximanova-extraboldit-webfont.woff") format("woff"),
    url("fonts/proximanova/proximanova-extraboldit-webfont.ttf")
      format("truetype"),
    url("fonts/proximanova/proximanova-extraboldit-webfont.svg") format("svg");
  font-weight: normal;
  font-style: normal;
}

@font-face {
  font-family: "ProximaNova-Black";
  src: url("fonts/proximanova/proximanova-black-webfont.eot");
  src: url("fonts/proximanova/proximanova-black-webfont.eot")
      format("embedded-opentype"),
    url("fonts/proximanova/proximanova-black-webfont.woff2") format("woff2"),
    url("fonts/proximanova/proximanova-black-webfont.woff") format("woff"),
    url("fonts/proximanova/proximanova-black-webfont.ttf") format("truetype"),
    url("fonts/proximanova/proximanova-black-webfont.svg") format("svg");
  font-weight: normal;
  font-style: normal;
}

@font-face {
  font-family: "ProximaNova-Black_italic";
  src: url("fonts/proximanova/proximanova-blackit-webfont.eot");
  src: url("fonts/proximanova/proximanova-blackit-webfont.eot")
      format("embedded-opentype"),
    url("fonts/proximanova/proximanova-blackit-webfont.woff2") format("woff2"),
    url("fonts/proximanova/proximanova-blackit-webfont.woff") format("woff"),
    url("fonts/proximanova/proximanova-blackit-webfont.ttf") format("truetype"),
    url("fonts/proximanova/proximanova-blackit-webfont.svg") format("svg");
  font-weight: normal;
  font-style: normal;
}

@font-face {
  font-family: "Noto Sans KR";
  font-style: normal;
  font-weight: 300;
  src: url("fonts/notosanskr/NotoSansKR-Light.woff2") format("woff2"),
    url("fonts/notosanskr/NotoSansKR-Light.woff") format("woff"),
    url("fonts/notosanskr/NotoSansKR-Light.otf") format("opentype");
}

@font-face {
  font-family: "Noto Sans KR";
  font-style: normal;
  font-weight: 400;
  src: url("fonts/notosanskr/NotoSansKR-Regular.woff2") format("woff2"),
    url("fonts/notosanskr/NotoSansKR-Regular.woff") format("woff"),
    url("fonts/notosanskr/NotoSansKR-Regular.otf") format("opentype");
}

@font-face {
  font-family: "Noto Sans KR";
  font-style: normal;
  font-weight: 500;
  src: url("fonts/notosanskr/NotoSansKR-Medium.woff2") format("woff2"),
    url("fonts/notosanskr/NotoSansKR-Medium.woff") format("woff"),
    url("fonts/notosanskr/NotoSansKR-Medium.otf") format("opentype");
}

@font-face {
  font-family: "Noto Sans KR";
  font-style: normal;
  font-weight: 700;
  src: url("fonts/notosanskr/NotoSansKR-Bold.woff2") format("woff2"),
    url("fonts/notosanskr/NotoSansKR-Bold.woff") format("woff"),
    url("fonts/notosanskr/NotoSansKR-Bold.otf") format("opentype");
}

@font-face {
  font-family: "NotoSerifKR";
  font-style: normal;
  font-weight: normal;
  src: url("fonts/notoserifkr/noto-serif-kr-v15-korean-regular.eot");
  src: url("fonts/notoserifkr/noto-serif-kr-v15-korean-regular.eot")
      format("embedded-opentype"),
    url("fonts/notoserifkr/noto-serif-kr-v15-korean-regular.woff2")
      format("woff2"),
    url("fonts/notoserifkr/noto-serif-kr-v15-korean-regular.woff")
      format("woff"),
    url("fonts/notoserifkr/noto-serif-kr-v15-korean-regular.svg") format("svg");
}

@font-face {
  font-family: "NotoSerifKR-Medium";
  font-weight: 500;
  font-style: normal;
  src: url("fonts/notoserifkr/noto-serif-kr-v15-korean-500.eot");
  src: url("fonts/notoserifkr/noto-serif-kr-v15-korean-500.eot")
      format("embedded-opentype"),
    url("fonts/notoserifkr/noto-serif-kr-v15-korean-500.woff2") format("woff2"),
    url("fonts/notoserifkr/noto-serif-kr-v15-korean-500.woff") format("woff"),
    url("fonts/notoserifkr/noto-serif-kr-v15-korean-500.svg") format("svg");
}

@font-face {
  font-family: "NotoSerifKR-Semibold";
  font-weight: 600;
  font-style: normal;
  src: url("fonts/notoserifkr/noto-serif-kr-v15-korean-600.eot");
  src: url("fonts/notoserifkr/noto-serif-kr-v15-korean-600.eot")
      format("embedded-opentype"),
    url("fonts/notoserifkr/noto-serif-kr-v15-korean-600.woff2") format("woff2"),
    url("fonts/notoserifkr/noto-serif-kr-v15-korean-600.woff") format("woff"),
    url("fonts/notoserifkr/noto-serif-kr-v15-korean-600.svg") format("svg");
}

@font-face {
  font-family: "NotoSerifKR-Bold";
  font-weight: bold;
  font-style: normal;
  src: url("fonts/notoserifkr/noto-serif-kr-v15-korean-700.eot");
  src: url("fonts/notoserifkr/noto-serif-kr-v15-korean-700.eot")
      format("embedded-opentype"),
    url("fonts/notoserifkr/noto-serif-kr-v15-korean-700.woff2") format("woff2"),
    url("fonts/notoserifkr/noto-serif-kr-v15-korean-700.woff") format("woff"),
    url("fonts/notoserifkr/noto-serif-kr-v15-korean-700.svg") format("svg");
}

@font-face {
  font-family: "Castoro";
  font-weight: normal;
  font-style: normal;
  src: url("fonts/castoro/castoro-v8-latin-regular.eot");
  src: url("fonts/castoro/castoro-v8-latin-regular.eot")
      format("embedded-opentype"),
    url("fonts/castoro/castoro-v8-latin-regular.woff2") format("woff2"),
    url("fonts/castoro/castoro-v8-latin-regular.woff") format("woff"),
    url("fonts/castoro/castoro-v8-latin-regular.ttf") format("truetype");
}

/*!
*  Font Awesome 4.7.0 by @davegandy - http://fontawesome.io - @fontawesome
*  License - http://fontawesome.io/license (Font: SIL OFL 1.1, CSS: MIT License)
*/
@font-face {
  font-family: "FontAwesome";
  src: url("fonts/fontawesome-webfont.eot");
  src: url("fonts/fontawesome-webfont-1.eot") format("embedded-opentype"),
    url("fonts/fontawesome-webfont.woff2") format("woff2"),
    url("fonts/fontawesome-webfont.woff") format("woff"),
    url("fonts/fontawesome-webfont.ttf") format("truetype"),
    url("fonts/fontawesome-webfont.svg") format("svg");
  font-weight: normal;
  font-style: normal;
}

.pdt {
  position: relative;
  width: 1240px;
  margin: 0 auto;
  padding-top: 20px;
  font-family: "ProximaNova-Regular", "Noto Sans KR", "Apple SD Gothic Neo",
    AppleGothic, "돋움", Dotum, Tahoma, Verdana, Geneva, Arial, Helvetica,
    sans-serif;
}

.pdt .tab {
  margin-top: 80px;
}

.pdt .tab li {
  position: relative;
}

.pdt .tab li a {
  display: block;
  height: 57px;
  color: #333;
  font-family: "ProximaNova-Regular";
  font-size: 18px;
  line-height: 57px;
  text-align: center;
}

.pdt_detail {
  position: relative;
  width: 1240px;
  margin-top: 20px;
}

.pdt_detail .pdt_info .h_group {
  position: relative;
  margin-top: 54px;
}

.pdt_detail .pdt_info .h_group .brand a {
  font-size: 35px;
}

.pdt_detail .pdt_info .h_group .product_front h3 {
  font-size: 25px;
}

.pdt_detail .pdt_info .h_group .my_brand {
  position: absolute;
  top: 0;
  right: 0;
  font-size: 0;
}

.pdt_detail .pdt_info .h_group .my_brand em {
  vertical-align: top;
  display: inline-block;
  font-size: 12px;
  color: #191919;
  height: 16px;
  line-height: 16px;
  font-family: "ProximaNova-Bold";
}

.pdt_detail .pdt_info .h_group .my_brand.active span {
  background-position: 0 bottom;
}

.pdt_detail .pdt_info .h_group .front {
  font-family: "ProximaNova-Regular", "Noto Sans KR";
  font-size: 14px;
  line-height: 20px;
  color: #000;
  padding-right: 30px;
  margin-top: 12px;
}

.pdt_detail .pdt_info dl {
  font-family: "ProximaNova-Regular", "Noto Sans KR";
  font-size: 30px;
}

.pdt_detail .pdt_info dl dt,
.pdt_detail .pdt_info dl dd {
  float: left;
  margin: 0;
}

.pdt_detail .pdt_info dl dt {
  width: 200px;
  color: #333;
}

.pdt_detail .pdt_info dl:after {
  display: block;
  clear: both;
  content: "";
}

.pdt_detail .pdt_info .btn_group {
  display: -webkit-flex;
  display: -ms-flex;
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 660px;
}

.pdt_detail .pdt_info .btn_group li {
  margin-left: 10px;
  position: relative;
}

.pdt_detail .pdt_info .btn_group li.box_btn {
  min-width: 70px;
  width: 70px;
}

.pdt_detail :not(.pdt_toolbar) .pdt_info .btn_group li:not(.box_btn) {
  flex: 1;
  min-width: 250px;
}

.pdt_detail .pdt_info .btn_group li:nth-child(4) {
  margin-left: 2px;
}

@-webkit-keyframes gift_tooltip {
  0% {
    top: -50%;
  }

  50% {
    top: -60%;
  }

  100% {
    top: -50%;
  }
}

@keyframes gift_tooltip {
  0% {
    top: -50%;
  }

  50% {
    top: -60%;
  }

  100% {
    top: -50%;
  }
}

.pdt_detail .pdt_info .btn_group li:first-child {
  margin-left: 0;
}

.pdt_detail .pdt_info .btn_group .btn {
  width: 100%;
  height: 70px;
  font-family: "ProximaNova-Semibold", "Noto Sans KR";
  font-weight: 500;
  line-height: 68px;
  font-size: 20px;
  border-color: #000;
}

.pdt_detail .pdt_info .btn_group:after {
  display: block;
  clear: both;
  content: "";
}

.pdt_head {
  position: relative;
}

.pdt_head .left_col {
  float: left;
  width: 525px;
  position: relative;
}

.pdt_head .right_col {
  float: right;
  width: 660px;
}

.pdt_head .img_goods .gallery_wrap {
  margin-top: 20px;
  height: 80px;
  overflow: hidden;
}

.pdt_head .img_goods .gallery_wrap:after {
  content: "";
  clear: both;
  display: block;
}

.pdt_head .img_goods {
  position: relative;
  margin-bottom: 30px;
}

.pdt_head .img_goods .img_area {
  width: 100%;
  height: 700px;
  position: relative;
}

.pdt_head .img_goods .img_area:hover {
  cursor: crosshair;
}

.pdt_head .img_goods ul {
  float: left;
  max-width: 447px;
  overflow: hidden;
}

.pdt_head .img_goods ul li {
  /* float: left;
  margin-left: 18px; */
}

.pdt_head .img_goods ul li a {
  width: 100%;
  opacity: 0.3;
  filter: alpha(opacity=30%);
}

.pdt_head .img_goods ul li a.active {
  opacity: 1;
  filter: alpha(opacity=100%);
}

.pdt_head .img_goods ul li:first-child {
  margin-left: 0;
}

.pdt_head .img_goods ul li:nth-child(even) {
  margin-left: 17px;
}

.pdt_head .img_goods ul:after {
  display: block;
  clear: both;
  content: "";
}

.pdt_head .price_wrap {
  position: relative;
}

.pdt_head dl {
  padding: 15px 0;
}

.pdt_head dl dt,
.pdt_head dl dd {
  padding: 7px 0;
}

.pdt_head dl dt {
  clear: both;
}

.pdt_head dl.price {
  border-top: 0;
  padding-top: 100px;
  padding-bottom: 100px;
  border-bottom: 2px solid #000;
  position: relative;
}

.pdt_head dl.price dt,
.pdt_head dl.price dd {
  height: 40px;
  line-height: 18px;
  padding: 10px 0;
}

.pdt_head dl.price dt {
  clear: both;
}

.pdt_head dl.price dd.normal + dt {
  display: inline-flex;
  justify-content: flex-start;
  align-items: center;
}

.pdt_head dl.price .normal {
  font-size: 25px;
}

.pdt_head dl.price .sale {
  font-size: 35px;
}

.pdt_head dl.price dd.normal {
  color: #ccc;
  text-decoration: line-through;
  font-size: 25px;
}

.pdt_head dl.price dd.sale {
  color: #000;
}

.pdt_head dl.info {
  background-color: #f8f8f8;
  padding: 15px 20px 21px;
  margin-bottom: 25px;
}

.pdt_head dl.info dt {
  width: 100%;
  float: none;
  font-family: "ProximaNova-Regular", "Noto Sans KR";
  color: #323232;
  font-size: 14px;
  padding: 0;
  margin-bottom: 10px;
}

.pdt_head dl.info dd {
  padding: 0;
}

.pdt_head dl.info li {
  position: relative;
  line-height: 18px;
  padding-left: 110px;
  color: #323232;
  font-size: 12px;
}

.pdt_head dl.info li:before {
  content: "";
  display: block;
  width: 3px;
  height: 3px;
  background-color: #171717;
  position: absolute;
  top: 6px;
  left: 0;
}

.pdt_head dl.info li span {
  position: absolute;
  top: 0;
  left: 12px;
  font-family: "NotoSansKR-Medium";
}

.pdt_head dl.info li p {
  margin: 0;
  padding: 0;
}

.pdt_head dl.info li:first-child {
  margin-top: 0;
}

.pdt_head .btn_group {
  padding: 30px 0;
}

.pdt_head:after {
  display: block;
  clear: both;
  content: "";
}

.pdt_contents {
  margin-top: 60px;
}

.pdt_contents h4 {
  margin-top: 80px;
  padding-bottom: 14px;
  font-family: "ProximaNova-Semibold", "NotoSansKR-Medium";
  font-size: 18px;
}

.pdt_contents.detail .marketing {
  text-align: center;
  width: 960px;
  margin: 0 auto;
}

.pdt_contents.detail .marketing img {
  display: block;
  margin: 0 auto;
  max-width: 100%;
}

.pdt_contents.detail .marketing table {
  border: none;
  padding: 0;
}

.pdt_contents.detail .marketing table tbody th,
.pdt_contents.detail .marketing table tbody td {
  height: auto;
  border: none;
  padding: 0;
}

.pdt_contents.detail table {
  margin-bottom: 0;
}

.pdt_contents.detail table tbody th {
  height: 48px;
  font-size: 12px;
}

.pdt_contents.detail table tbody td {
  padding: 0;
  font-size: 12px;
}

.pdt_head dl.price .discount_percent {
  color: #fa5500 !important;
  font-size: 18px !important;
  font-weight: bold !important;
  margin-left: 6px !important;
}

.pdt_head dl.price dd em {
  font-family: "proxima Nova";
}

.pdt_head .btn_question {
  display: inline-block;
  width: 15px;
  height: 15px;
  background-size: 200px auto;
  background-position: -50px 0;
  margin-left: 3px;
}

.pdt_head .btn_question .blind {
  overflow: hidden;
  position: absolute;
  top: 0;
  left: 0;
  width: 1px !important;
  height: 1px !important;
  font-size: 0 !important;
}

.pdt_detail .product_front {
  position: relative;
}

.pdt_contents.detail {
  height: auto !important;
  max-width: 100%;
  position: relative !important;
}

.discount_rate,
.price {
  display: block;
  position: absolute;
  top: 0;
  right: 0;
  font-size: 16px;
  color: #ff4000;
}
@keyframes ldio-f4nnk2ltl0v {
  0% {
    opacity: 1;
  }
  100% {
    opacity: 0;
  }
}
.ldio-f4nnk2ltl0v div {
  position: fixed;
  top: 30%;
  left: 50%;
  transform: translate(-50%, -50%);
  z-index: 999; /* 다른 요소 위에 표시하기 위한 z-index 값 */
  animation: ldio-f4nnk2ltl0v linear 1s infinite;
  background: #fe718d;
  width: 18.240000000000002px;
  height: 36.480000000000004px;
  border-radius: 9.120000000000001px / 18.240000000000002px;
  transform-origin: 9.120000000000001px 79.04px;
}
.ldio-f4nnk2ltl0v div:nth-child(1) {
  transform: rotate(0deg);
  animation-delay: -0.9166666666666666s;
  background: #fe718d;
}
.ldio-f4nnk2ltl0v div:nth-child(2) {
  transform: rotate(30deg);
  animation-delay: -0.8333333333333334s;
  background: #f47e60;
}
.ldio-f4nnk2ltl0v div:nth-child(3) {
  transform: rotate(60deg);
  animation-delay: -0.75s;
  background: #f8b26a;
}
.ldio-f4nnk2ltl0v div:nth-child(4) {
  transform: rotate(90deg);
  animation-delay: -0.6666666666666666s;
  background: #abbd81;
}
.ldio-f4nnk2ltl0v div:nth-child(5) {
  transform: rotate(120deg);
  animation-delay: -0.5833333333333334s;
  background: #849b87;
}
.ldio-f4nnk2ltl0v div:nth-child(6) {
  transform: rotate(150deg);
  animation-delay: -0.5s;
  background: #6492ac;
}
.ldio-f4nnk2ltl0v div:nth-child(7) {
  transform: rotate(180deg);
  animation-delay: -0.4166666666666667s;
  background: #637cb5;
}
.ldio-f4nnk2ltl0v div:nth-child(8) {
  transform: rotate(210deg);
  animation-delay: -0.3333333333333333s;
  background: #6a63b6;
}
.ldio-f4nnk2ltl0v div:nth-child(9) {
  transform: rotate(240deg);
  animation-delay: -0.25s;
  background: #fe718d;
}
.ldio-f4nnk2ltl0v div:nth-child(10) {
  transform: rotate(270deg);
  animation-delay: -0.16666666666666666s;
  background: #f47e60;
}
.ldio-f4nnk2ltl0v div:nth-child(11) {
  transform: rotate(300deg);
  animation-delay: -0.08333333333333333s;
  background: #f8b26a;
}
.ldio-f4nnk2ltl0v div:nth-child(12) {
  transform: rotate(330deg);
  animation-delay: 0s;
  background: #abbd81;
}
.loadingio-spinner-spinner-pz89b3jiaad {
  width: 304px;
  height: 304px;
  display: inline-block;
  overflow: hidden;
  background: #ffffff;
}
.ldio-f4nnk2ltl0v div {
  box-sizing: content-box;
}
</style>
