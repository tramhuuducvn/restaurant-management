package com.sdc.restaurantmanagement.payload;

import lombok.Getter;
import lombok.Data;
import lombok.Setter;
import lombok.AllArgsConstructor;


/**
 * Declare response data template
 * This class is build flow the Builder Design Pattern
 */
@Data
@Getter
@Setter
@AllArgsConstructor
public class APIResponse {
    private final String message;
    private final Object data;

    //Builder class
    public static class BodyResponseBuilder {
        private String message;
        private Object data;

        /**
         * Build message
         * @param message message
         * @return an instance of BodyResponseBuilder
         */
        public BodyResponseBuilder message(String message){
            this.message = message;
            return this;
        }

        /**
         * Build data
         * @param data object data need to return
         * @return an instance of BodyResponseBuilder
         */
        public BodyResponseBuilder data(Object data){
            this.data = data;
            return this;
        }

        /**
         * Build BodyResponse
         * @return BodyResponse
         */
        public APIResponse build(){
            return new APIResponse(this.message, this.data);
        }
    }

    /**
     * Get Builder of BodyResponse class, using for build BodyResponse
     * @return BodyResponseBuilder
     */
    public static BodyResponseBuilder builder(){
        return new BodyResponseBuilder();
    }
}