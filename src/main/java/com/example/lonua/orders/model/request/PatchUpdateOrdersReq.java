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

    @NotNull
    @Min(1)
    @ApiModelProperty(value = "주문 IDX( 1이상의 숫자 )", example = "1", required = true)
    private Integer ordersIdx;

    @NotNull
    @Length(min=2, max=10)
    @Pattern(regexp = "^(주문 접수|배송 전|배송 중|배송 완료|환불 진행 중)$")
    @ApiModelProperty(value = "주문 상태( 주문 접수 / 배송 전 / 배송 중 / 배송 완료 / 환불 진행 중)", example = "주문 접수", required = true)
    private String status;
}
