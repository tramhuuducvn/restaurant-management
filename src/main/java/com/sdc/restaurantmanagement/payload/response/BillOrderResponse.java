package com.sdc.restaurantmanagement.payload.response;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.sdc.restaurantmanagement.entity.BillOrder;
import lombok.Getter;
import lombok.Data;
import lombok.Setter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BillOrderResponse {
    private Long id;
    private Date createTime;
    private Date orderTime;
    private List<BillMenuItemResponse> items;

    private Double total;

    public void calculateTotal(){
        if(items == null) {
            return;
        }
        this.total = this.items.stream().map(item->item.getMenuItem().getPrice()).reduce(0.0, Double::sum);
    }

    public static BillOrderResponse fromEntity(BillOrder entity){
        BillOrderResponse response = BillOrderResponse.builder()
                .id(entity.getId())
                .createTime(entity.getCreateTime())
                .orderTime(entity.getOrderTime())
                .items(entity.getItems().stream().map(BillMenuItemResponse::fromEntity).collect(Collectors.toList()))
                .build();

        response.calculateTotal();
        return response;
    }
}