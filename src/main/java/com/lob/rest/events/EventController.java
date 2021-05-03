package com.lob.rest.events;

import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.lob.rest.events.EventForm.Request;

import javax.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Controller
@RequestMapping(value="/api/events", produces=MediaTypes.HAL_JSON_VALUE+";charset=UTF-8")
@RequiredArgsConstructor
public class EventController {

    private final EventRepository eventRepository;
    private final EventMapper mapper;

    @PostMapping
    public ResponseEntity<Event> create(@Valid @RequestBody Request.Add add) {
        System.out.println("add = " + add);
        Event save = eventRepository.save(mapper.toEntity(add));

        return ResponseEntity.created(
                linkTo(EventController.class)
                    .slash(save.getId())
                    .toUri())
                .body(save)
        ;
    }


}
