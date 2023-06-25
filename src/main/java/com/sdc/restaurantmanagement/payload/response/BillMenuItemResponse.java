package com.sdc.restaurantmanagement.payload.response;

import com.sdc.restaurantmanagement.entity.BillMenuItem;
import com.sdc.restaurantmanagement.entity.MenuItem;
import lombok.*;

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
    private boolean isDeleted;

    public static BillMenuItemResponse fromEntity(BillMenuItem entity) {
        return BillMenuItemResponse.builder()
                .id(entity.getId())
                .number(entity.getNumber())
                .isDeleted(entity.isDeleted())
                .menuItem(entity.getMenuItem())
                .build();
    }

    public Integer getNumber() {
        if (this.number == null) {
            return 0;
        }
        return this.number;
    }
}