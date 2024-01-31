package com.example.lonua.orders.model.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatchUpdateOrdersReq {

    @NotNull(message = "주문 IDX는 필수 입력 항목입니다.")
    @Min(value = 1, message = "주문 IDX는 1이상의 양수입니다.")
    @ApiModelProperty(value = "주문 IDX( 1이상의 숫자 )", example = "1", required = true)
    private Integer ordersIdx;

    @NotNull(message = "주문 상태는 핋수 입력 항목입니다.")
    @Length(min=2, max=10, message = "주문 상태는 [ 주문접수 / 결제완료 / 상품준비중 / 배송중 / 배송완료 ] 만 입력 가능합니다.")
    @Pattern(regexp = "^(주문접수|결제완료|상품준비중|배송중|배송완료)$", message = "주문 상태는 [ 주문접수 / 결제완료 / 상품준비중 / 배송중 / 배송완료 ] 만 입력 가능합니다.")
    @ApiModelProperty(value = "주문 상태( 주문접수 / 결제완료 / 상품준비중 / 배송중 / 배송완료 )", example = "주문 접수", required = true)
    private String status;
}
