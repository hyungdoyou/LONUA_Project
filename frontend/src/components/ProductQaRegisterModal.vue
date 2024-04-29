<template>
  <div v-show="isActive" class="qna_write active">
    <iframe
      name="qandainsert"
      width="0"
      height="0"
      frameborder="0"
      data-gtm-yt-inspected-26="true"
      data-gtm-yt-inspected-36="true"
    ></iframe>
    <form id="frmqanda" name="frmqanda">
      <input type="hidden" name="mcd" value="M33439436" />
      <input type="hidden" name="ca" value="133" />
      <input type="hidden" name="itemcd" id="qa_itemcd" value="301562429" />
      <input type="hidden" name="vendorcd" value="3005534" />
      <input type="hidden" name="dealtypecd" value="01" />
      <table>
        <colgroup>
          <col style="width: 295px" />
          <col />
        </colgroup>
        <tbody>
          <tr>
            <th>질문유형</th>
            <td>
              <select name="" id="" v-model="qna.questionType">
                <option value="사이즈">사이즈</option>
                <option value="배송">배송</option>
                <option value="재입고">재입고</option>
                <option value="상품 상세문의">상품 상세문의</option>
              </select>
            </td>
          </tr>
          <tr>
            <th>제목</th>
            <td>
              <input
                type="text"
                maxlength="100"
                placeholder="제목을 입력해주세요."
                style="width: 820px"
                name="title"
                id="qna_title"
                v-model="qna.questionTitle"
              />
            </td>
          </tr>
          <tr>
            <th>내용</th>
            <td class="multi_input_row">
              <textarea
                maxlength="4000"
                name="contents"
                id="qna_contents"
                placeholder="내용을 입력해 주세요."
                v-model="qna.questionContent"
                style="height: 300px; width: 820px;"
              ></textarea>
            </td>
          </tr>
          
          <tr>
            <th>공개여부</th>
            <td class="td_padding">
              <span class="input_button checkbox"
                ><input type="checkbox" id="checkbox1" name="issecret"  v-model="qna.isSecret"/><label
                  for="checkbox1"
                  >비공개</label
                ></span
              >
              <ul class="info_inquiry">
                <li>
                  ㆍ작성하신 문의는 마이페이지 &gt; 상품 Q&amp;A에서 확인하실 수
                  있습니다.
                </li>
                <li>ㆍ교환, 반품, 취소는 1:1문의를 통해 접수 부탁드립니다.</li>
                <li>
                  ㆍ부적절한 게시물 등록 시 비 노출 또는 무통보 삭제될 수
                  있습니다.
                  <ul class="info_inquiry_1">
                    <li>- 개인정보를 포함한 직거래 유도 내용</li>
                    <li>- 비방/욕설/명예훼손에 해당되는 게시물</li>
                    <li>- W컨셉과 관계없는 타사이트와 가격비교 및 구매 문의</li>
                  </ul>
                </li>
              </ul>
            </td>
          </tr>
        </tbody>
        
      </table>
    </form>
  
    <!-- //ITDEV-6888 -->
    <div class="btn_wrap">
      <button
        type="button"
        class="btn"
        @click="closeModal"
        style="cursor: pointer;"
      >
        취소하기
      </button>
      <button
        type="submit"
        class="btn black"
        @click="sendRegisterQnaData"
        style="cursor: pointer;"
      >
        작성하기
      </button>
    </div>
  </div>
</template>

<script>
import { mapStores } from "pinia";
import { useQnaStore } from "@/stores/useQnaStore.js";
import { useProductStore } from "@/stores/useProductStore.js";

export default {
  data() {
    return {
      isActive: false,
      qna:{
        questionType: "",
        questionTitle: "",
        questionContent: "",
        isSecret: false,
        productIdx: this.$route.params.idx
      }
    };
  },
  computed: {
    ...mapStores(useQnaStore, useProductStore),
  },
  methods: {
    openModal() {
      this.isActive = true;
    },
    closeModal() {
      this.isActive = false;
    },
    async sendRegisterQnaData(){
      await this.qnaStore.registerProductQna(this.qna);
      
    }
  },
};

</script>

<style></style>
