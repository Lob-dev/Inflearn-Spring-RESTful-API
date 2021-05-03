package com.lob.rest.events;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.lob.rest.events.EventTestHelper.getEventBadRequest;
import static com.lob.rest.events.EventTestHelper.getEventRequest;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
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
    void t01() throws Exception {
        // Given
        EventForm.Request.Add create = getEventRequest();

        //Mock 진행시
        //Mockito.when(eventRepository.save(any(Event.class))).thenReturn(event);

        // Then - When
        mockMvc.perform(
                post("/api/events/")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaTypes.HAL_JSON)
                    .content(toJson(create)))
                .andDo(print())
                .andExpect(status().isCreated())
        ;
    }

    @Test
    void t02() throws Exception {
        // Given
        EventForm.Request.Add create = getEventRequest();

        //Mock 진행시
        //Mockito.when(eventRepository.save(any(Event.class))).thenReturn(event);

        // Then - When
        mockMvc.perform(
                post("/api/events/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaTypes.HAL_JSON)
                        .content(toJson(create)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").exists())
                .andExpect(header().exists(HttpHeaders.LOCATION))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON.toString()+";charset=UTF-8"))
        ;
    }

    @Test
    void t03() throws Exception {
        // Given
        Event create = getEventBadRequest();

        mockMvc.perform(
                post("/api/events/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaTypes.HAL_JSON)
                        .content(toJson(create)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }


    private String toJson(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }

}
