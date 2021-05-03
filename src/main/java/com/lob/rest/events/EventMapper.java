package com.lob.rest.events;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import com.lob.rest.events.EventForm.Request;

/**
 * @author lob
 * @description EventMapper
 * @since 2021.04.02
 **********************************************************************************************************************/
@Mapper(
        injectionStrategy=InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy=ReportingPolicy.IGNORE,
        componentModel="spring"
)
public interface EventMapper {

    Event toEntity(Request.Add add);

}
