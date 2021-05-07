package com.lob.rest.events;

import com.lob.rest.events.domain.Event;
import com.lob.rest.events.domain.Event.EventBuilder;
import com.lob.rest.events.controller.EventForm.Request.Add;
import com.lob.rest.events.controller.EventForm.Response.FindOne;
import javax.annotation.processing.Generated;

import com.lob.rest.events.mapper.EventMapper;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-05-06T14:00:31+0900",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 11.0.10 (ojdkbuild)"
)
@Component
public class EventMapperImpl implements EventMapper {

    @Override
    public Event toEntity(Add add) {
        if ( add == null ) {
            return null;
        }

        EventBuilder event = Event.builder();

        event.name( add.getName() );
        event.description( add.getDescription() );
        event.beginEnrollmentDateTime( add.getBeginEnrollmentDateTime() );
        event.closeEnrollmentDateTime( add.getCloseEnrollmentDateTime() );
        event.beginEventDateTime( add.getBeginEventDateTime() );
        event.endEventDateTime( add.getEndEventDateTime() );
        event.location( add.getLocation() );
        if ( add.getBasePrice() != null ) {
            event.basePrice( add.getBasePrice() );
        }
        if ( add.getMaxPrice() != null ) {
            event.maxPrice( add.getMaxPrice() );
        }
        if ( add.getLimitOfEnrollment() != null ) {
            event.limitOfEnrollment( add.getLimitOfEnrollment() );
        }

        return event.build();
    }

    @Override
    public FindOne toDto(Event event) {
        if ( event == null ) {
            return null;
        }

        FindOne findOne = new FindOne();

        return findOne;
    }
}
