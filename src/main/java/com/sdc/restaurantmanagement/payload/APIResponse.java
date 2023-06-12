package com.sdc.restaurantmanagement.payload;

import lombok.*;
import org.springframework.http.HttpStatus;

/**
 * Declare response data template
 * This class is build flow the Builder Design Pattern
 */
@Data
@Getter
@Setter
@AllArgsConstructor
public class APIResponse {
    private final int status; // required
    private final String statusText; // required
    private final String message;
    private final Object data;

    //Builder class
    public static class BodyResponseBuilder {
        private int status;
        private String statusText;
        private String message;
        private Object data;

        public BodyResponseBuilder(HttpStatus status){
            this.status = status.value();
            this.statusText = status.toString();
        }

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
            return new APIResponse(this.status, this.statusText, this.message, this.data);
        }
    }

    /**
     * Get Builder of BodyResponse class, using for build BodyResponse
     * @param data required field of BodyResponse
     * @return BodyResponseBuilder
     */
    public static BodyResponseBuilder builder(HttpStatus data){
        return new BodyResponseBuilder(data);
    }
}