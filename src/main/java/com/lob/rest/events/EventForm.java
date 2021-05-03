package com.lob.rest.events;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @author lob
 * @description EventForm
 * @since 2021.04.02
 **********************************************************************************************************************/
public class EventForm {

    public static class Request {

        @Getter
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Add {

            @NotBlank
            private String        name;

            @NotBlank
            private String        description;

            @DateTimeFormat(pattern = "yyyyMMdd")
            @NotNull
            private LocalDateTime beginEnrollmentDateTime;

            @DateTimeFormat(pattern = "yyyyMMdd")
            @NotNull
            private LocalDateTime closeEnrollmentDateTime;

            @DateTimeFormat(pattern = "yyyyMMdd")
            @NotNull
            private LocalDateTime beginEventDateTime;

            @DateTimeFormat(pattern = "yyyyMMdd")
            @NotNull
            private LocalDateTime endEventDateTime;

            @NotNull
            @Min(value=0)
            private Integer       limitOfEnrollment;

            @NotNull
            @Min(value=0)
            private Integer       basePrice; // (optional)

            @NotNull
            @Min(value=0)
            private Integer       maxPrice; // (optional)

            @NotBlank
            private String        location; // (optional) 이게 없으면 온라인 모임

        }

    }
}
