package com.example.lonua.orders.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostCreateOrdersReq {

    @NotNull
    @Min(1)
    @ApiModelProperty(value = "총 주문 금액", example = "50000", required = true)
    private Integer amount;

    @NotNull
    @Pattern(regexp = "^imp_\\d{12}$")
    @ApiModelProperty(value = "결제 번호", example = "imp_581455476041", required = true)
    private String impUid;

    @NotNull
    @Length(min=1, max=18)
    @ApiModelProperty(value = "주문 번호", example = "202401062125264623", required = true)
    private String merchantUid;

    @NotNull
    @Length(max=45)
    @ApiModelProperty(value = "상품명(45자 이하)", example = "하프 집업 카라넥 케이블 니트 [카키]", required = true)
    private String productName;

    @NotNull
    @Length(min=1)
    @ApiModelProperty(value = "결제 수단", example = "카카오페이", required = true)
    private String payMethod;

    @NotNull
    @ApiModelProperty(value = "주문 상품 리스트", example = "[1, 2, 3]", required = true)
    private List<Integer> productIdxList;

}
