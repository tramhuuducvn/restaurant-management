package com.sdc.restaurantmanagement.controller;

import com.sdc.restaurantmanagement.payload.APIResponse;
import com.sdc.restaurantmanagement.payload.request.BillMenuItemRequest;
import com.sdc.restaurantmanagement.payload.response.BillOrderResponse;
import com.sdc.restaurantmanagement.service.BillOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Receive request from client related to bill orders like:
 * <p>listing all bill orders</p>
 * <p>detail a bill order by id</p>
 * <p>create bill</p>
 * <p>add menu item to bill</p>
 * <p>remove menu item from bill</p>
 * <p>update quantity to a menu item of a bill order</p>
 */
@Tag(name = "Bill Order APIs", description = "REST APIs for Bill Order Collection")
@RestController
@RequestMapping(value = "/bill-orders")
public class BillOrderController {
    @Autowired
    private BillOrderService billOrderService;

    /**
     * Get all bill orders
     *
     * @return list bill orders
     */
    @Operation(summary = "Get all bill orders", description = "Show all bill orders information")
    @ApiResponses(value = {
            @ApiResponse(content = @Content(array = @ArraySchema( schema = @Schema(implementation = APIResponse.class))))
    })
    @GetMapping(value = "")
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public APIResponse getAll() {
        return APIResponse.builder().message("List bill order").data(billOrderService.getAll()).build();
    }

    /**
     * Get bill order by id
     *
     * @param id id of bill order
     * @return detail information of bill order
     */
    @Operation(summary = "Get bill order by id", description = "Show detail information of bill order with the given id")
    @ApiResponses(value = {
            @ApiResponse(content = @Content(array = @ArraySchema( schema = @Schema(implementation = APIResponse.class))))
    })
    @GetMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public APIResponse getById(@PathVariable Long id) {
        BillOrderResponse res = billOrderService.getById(id);
        return APIResponse.builder().message("Get bill order success").data(res).build();
    }

    /**
     * Create bill order with the given menu items
     * @param items list menu item want to add to the bill
     * @return return 201 code
     */
    @Operation(summary = "Create a bill order", description = "Create a bill order with the given information")
    @ApiResponses(value = {
            @ApiResponse(content = @Content(array = @ArraySchema( schema = @Schema(implementation = APIResponse.class))))
    })
    @PostMapping(value = "")
    @ResponseStatus(value = HttpStatus.CREATED)
    @ResponseBody
    public APIResponse create(@RequestBody(required = false) List<BillMenuItemRequest> items) {
        return APIResponse.builder().message("Create bill success").data(billOrderService.create(items)).build();
    }

    /**
     * Add Menu Item to bill
     *
     * @param id      id of bill order
     * @param request an object include id of menu item and number of that item want to add
     * @return success response message
     */
    @Operation(summary = "Add menu item to a bill order", description = "Add a menu item to a given bill order")
    @ApiResponses(value = {
            @ApiResponse(content = @Content(array = @ArraySchema( schema = @Schema(implementation = APIResponse.class))))
    })
    @PutMapping(value = "/{id}/add")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    @ResponseBody
    public APIResponse addMenuItem(@PathVariable Long id, @RequestBody BillMenuItemRequest request) throws Exception {
        billOrderService.addBillMenuItem(id, request);
        return APIResponse.builder().message("Add item to bill success").build();
    }

    /**
     * Update quantity of Menu Item to bill
     *
     * @param id      id of bill order
     * @param request an object include id of menu item and number of that item want to update
     * @return success response message
     */
    @Operation(summary = "Update quantity of menu item to a bill order", description = "Change quantity of a menu item in the given bill and save it to the database")
    @ApiResponses(value = {
            @ApiResponse(content = @Content(array = @ArraySchema( schema = @Schema(implementation = APIResponse.class))))
    })
    @PutMapping(value = "/{id}/update-quantity")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    @ResponseBody
    public APIResponse updateMenuItemQuantity(@PathVariable Long id, @RequestBody BillMenuItemRequest request) throws Exception {
        billOrderService.updateMenuItemQuantity(id, request);
        return APIResponse.builder().message("Update successful").build();
    }

    @Operation(summary = "Remove a menu item from a bill order", description = "Remove(soft delete) a menu item in the given bill")
    @ApiResponses(value = {
            @ApiResponse(content = @Content(array = @ArraySchema( schema = @Schema(implementation = APIResponse.class))))
    })
    @DeleteMapping(value = "/{billId}/{menuItemId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public APIResponse deleteBillMenuItem(@PathVariable Long billId, @PathVariable Long menuItemId) throws Exception {
        billOrderService.removeBillMenuItem(billId, menuItemId);
        return APIResponse.builder().message("Delete successful").build();
    }

    @Operation(summary = "Pay a bill order", description = "Pay & Export a bill order")
    @ApiResponses(value = {
            @ApiResponse(content = @Content(array = @ArraySchema( schema = @Schema(implementation = APIResponse.class))))
    })
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping(value = "/{id}/pay-bill-order")
    public APIResponse payBillOrder(@PathVariable Long id) throws Exception {
        billOrderService.payBillOrder(id);
        return APIResponse.builder().message("Bill order is paid successful").build();
    }

}