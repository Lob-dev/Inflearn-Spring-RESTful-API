package com.lob.rest.events.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

/**
 * @since       2021.03.26
 * @author      lob
 * @description Event
 **********************************************************************************************************************/
@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access=AccessLevel.PROTECTED)
public class Event {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable=false)
    private String name;

    @Column(nullable=false)
    private String description;

    @Column(nullable=false)
    private LocalDateTime beginEnrollmentDateTime;

    @Column(nullable=false)
    private LocalDateTime closeEnrollmentDateTime;

    @Column(nullable=false)
    private LocalDateTime beginEventDateTime;

    @Column(nullable=false)
    private LocalDateTime endEventDateTime;

    @Column(nullable=false)
    private String location; // (optional) 이게 없으면 온라인 모임

    @Column(nullable=false)
    private int basePrice; // (optional)

    @Column(nullable=false)
    private int maxPrice; // (optional)

    @Column(nullable=false)
    private int limitOfEnrollment;

    private boolean offline;

    private boolean free;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    @Builder.Default
    private EventStatus eventStatus = EventStatus.DRAFT;


    public void update() {
        free = basePrice == 0 && maxPrice == 0;
    }


}
