package com.sdc.restaurantmanagement.payload.response;
import com.sdc.restaurantmanagement.entity.BillMenuItem;
import com.sdc.restaurantmanagement.entity.MenuItem;
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
public class BillMenuItemResponse {
    private Long id;
    private Integer number;
    private MenuItem menuItem;
    private boolean deleted;


    public static BillMenuItemResponse fromEntity(BillMenuItem entity){
        return BillMenuItemResponse.builder()
                .id(entity.getId())
                .number(entity.getNumber())
                .deleted(entity.isDeleted())
                .menuItem(entity.getMenuItem())
                .build();
    }
}