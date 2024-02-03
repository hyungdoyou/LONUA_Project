package com.example.lonua.orders.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostCreateOrdersReq {

    @NotNull(message = "총 주문 금액은 필수 입력 항목입니다.")
    @Min(value = 1, message = "총 주문 금액은 1 이상의 양수입니다.")
    @ApiModelProperty(value = "총 주문 금액", example = "50000", required = true)
    private Integer amount;

    @NotNull(message = "결제 번호는 필수 입력 항목입니다.")
    @Pattern(regexp = "^imp_\\d{12}$", message = "결제 번호는 [ imp_ ] 뒤로 12자리의 숫자로 입력하여야 합니다.")
    @ApiModelProperty(value = "결제 번호", example = "imp_581455476041", required = true)
    private String impUid;

    @NotNull(message = "주문 번호는 필수 입력 항목입니다.")
    @Length(min=18, max=18, message = "주문 번호는 18자리로 입력해야 합니다.")
    @ApiModelProperty(value = "주문 번호", example = "202401062125264623", required = true)
    private String merchantUid;

    @NotNull(message = "상품명은 필수 입력 항목입니다.")
    @Length(min = 1, max=45, message = "상품명은 최대 45글자 이내로 입력 가능합니다.")
    @ApiModelProperty(value = "상품명(45자 이하)", example = "하프 집업 카라넥 케이블 니트 [카키]", required = true)
    private String productName;

    @NotNull(message = "결제 수단은 필수 입력 항목입니다.")
    @Length(min=1, message = "결제 수단은 최소 1글자 이상으로 입력해야 합니다.")
    @ApiModelProperty(value = "결제 수단", example = "카카오페이", required = true)
    private String payMethod;

    @NotNull(message = "주문 상품 리스트는 필수 입력 항목입니다.")
    @ApiModelProperty(value = "주문 상품 리스트", example = "[1, 2, 3]", required = true)
    private List<Integer> productIdxList;

    @ApiModelProperty(value = "사용한 쿠폰 리스트", example = "[1, 2, 3]", required = true)
    private List<Integer> couponIdxList;

    @ApiModelProperty(value = "사용한 상품과 쿠폰 맵", example = "{1:2}", required = true)
    private Map<Integer, Integer> productCouponMap;

    @ApiModelProperty(value = "주문한 장바구니 리스트", example = "[1, 2, 3]", required = true)
    private List<Integer> ordersCartIdxList;

    @NotNull(message = "마일리지 적립금액은 필수 입력 항목입니다.")
    @ApiModelProperty(value = "마일리지", example = "100", required = true)
    private Integer mileage;
}
