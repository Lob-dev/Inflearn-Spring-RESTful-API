package com.lob.rest.events;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lob.rest.events.controller.EventForm.Request;
import com.lob.rest.events.domain.Event;
import com.lob.rest.events.domain.EventRepository;
import com.lob.rest.events.domain.EventStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.lob.rest.events.EventTestHelper.getEventBadRequest;
import static com.lob.rest.events.EventTestHelper.getEventRequest;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.halLinks;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.linkWithRel;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.links;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
public class EventControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    //@MockBean
    @Autowired
    EventRepository eventRepository;

    /*
    1. 입력값들을 전달하면 JSON 응답으로 201이 나오는지 확인.
        1-1. Location 헤더에 생성된 이벤트를 조회할 수 있는 URI 담겨 있는지 확인.
        1-2. id는 DB에 들어갈 때 자동생성된 값으로 나오는지 확인
     */

    @Test
    void t01_요청_값이_정상적으로_전달되었을때_요청_처리는_성공해야_한다() throws Exception {
        // Given
        Request.Add create = getEventRequest();

        //Mock 진행시
        //Mockito.when(eventRepository.save(any(Event.class))).thenReturn(event);

        // When - Then
        mockMvc.perform(post("/api/events/")
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaTypes.HAL_JSON)
                            .content(toJson(create)))
                        .andDo(print())
                        .andExpect(status().isCreated())
        ;
    }

    @Test
    void t02_요청_값이_정상적으로_전달되었을때_응답에는_ID_Location_ContentType값이_있어야_한다() throws Exception {
        // Given
        Request.Add create = getEventRequest();

        //Mock 진행시
        //Mockito.when(eventRepository.save(any(Event.class))).thenReturn(event);

        // When - Then
        mockMvc.perform(post("/api/events/")
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaTypes.HAL_JSON)
                            .content(toJson(create)))
                        .andDo(print())
                        .andExpect(status().isCreated())
                        .andExpect(jsonPath("id").exists())
                        .andExpect(header().exists(HttpHeaders.LOCATION))
                        .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON+";charset=UTF-8"))
        ;
    }

    @Test
    void t03_요청_값이_잘못_전달되었을때_요청_처리는_실패해야_한다() throws Exception {
        // Given
        Event create = getEventBadRequest();

        // When - Then
        mockMvc.perform(post("/api/events/")
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaTypes.HAL_JSON)
                            .content(toJson(create)))
                        .andDo(print())
                        .andExpect(status().isBadRequest())
        ;
    }

    @Test
    void t04_요청_값의_일부가_비어있을때_요청_처리는_실패해야_한다() throws Exception {
        // Given
        Request.Add create = new Request.Add();

        // When - Then
        mockMvc.perform(post("/api/events/")
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaTypes.HAL_JSON)
                            .content(toJson(create)))
                        .andDo(print())
                        .andExpect(status().isBadRequest())
        ;
    }

    @Test
    void t05_요청_값이_잘_전달되었을떄_정상적으로_이벤트가_생성되어야_한다() throws Exception {

        // Given
        Request.Add create = getEventRequest();

        // When - Then
        mockMvc.perform(post("/api/events/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaTypes.HAL_JSON)
                .content(toJson(create)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().exists(HttpHeaders.LOCATION))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_VALUE+";charset=UTF-8"))
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("free").value(false))
                .andExpect(jsonPath("offline").value(false))
                .andExpect(jsonPath("eventStatus").value(EventStatus.DRAFT.name()))
                .andExpect(jsonPath("_links.self").exists())
                .andExpect(jsonPath("_links.query-events").exists())
                .andExpect(jsonPath("_links.update-event").exists())
                .andDo(document(
                        "create-event",
                        links(halLinks(),
                                linkWithRel("profile").description("Link to profile"),
                                linkWithRel("self").description("Link to create event"),
                                linkWithRel("event").description("Link to view all events"),
                                linkWithRel("update").description("Link to update the event")
                        )
                ))
        ;
    }


    private String toJson(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }

}
