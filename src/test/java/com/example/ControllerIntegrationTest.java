package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ControllerIntegrationTest {

    private static final MockHttpServletRequestBuilder REQUEST_BUILDER =
            request(HttpMethod.POST, "/instructions")
                    .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                    .accept(APPLICATION_JSON);

    @Autowired
    private MockMvc mockMvc;

    @Test
    void whenRoomSizeIsMissing_shouldReturnBadRequest() throws Exception {
        final var requestPayload = "{\"coords\": [1, 2], \"patches\": [[1, 0], [2, 2], [2, 3]], \"instructions\": \"NNESEESWNWW\"}";
        mockMvc.perform(REQUEST_BUILDER.content(requestPayload))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenCoordsIsMissing_shouldReturnBadRequest() throws Exception {
        final var requestPayload = "{\"roomSize\": [5, 5], \"patches\": [[1, 0], [2, 2], [2, 3]], \"instructions\": \"NNESEESWNWW\"}";
        mockMvc.perform(REQUEST_BUILDER.content(requestPayload))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenPatchesIsMissing_shouldReturnBadRequest() throws Exception {
        final var requestPayload = "{\"roomSize\": [5, 5], \"coords\": [1, 2], \"instructions\": \"NNESEESWNWW\"}";
        mockMvc.perform(REQUEST_BUILDER.content(requestPayload))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenInstructionsAreMissing_shouldReturnBadRequest() throws Exception {
        final var requestPayload = "{\"roomSize\": [5, 5], \"coords\": [1, 2], \"patches\": [[1, 0], [2, 2], [2, 3]]}";
        mockMvc.perform(REQUEST_BUILDER.content(requestPayload))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenDirtPatchLocationIsOutsideOfRoomGrid_shouldReturnBadRequest() throws Exception {
        final var requestPayload = "{\"roomSize\": [2, 2], \"coords\": [1, 2], \"patches\": [[0, 0], [1, 2]], \"instructions\": \"N\"}";
        mockMvc.perform(REQUEST_BUILDER.content(requestPayload))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenTryingToNavigateOutsideOfRoomGrid_shouldReturnCorrectResponse() throws Exception {
        final var requestPayload = "{\"roomSize\": [2, 2], \"coords\": [0, 0], \"patches\": [[1, 1]], \"instructions\": \"NN\"}";
        final var responsePayload = "{\"coords\" : [0, 1], \"patches\" : 0}";
        mockMvc.perform(REQUEST_BUILDER.content(requestPayload))
                .andExpect(status().isOk())
                .andExpect(content().json(responsePayload));
    }

    @Test
    void whenHasValidInput_shouldReturnCorrectResponse() throws Exception {
        final var requestPayload = "{\"roomSize\": [5, 5], \"coords\": [1, 2], \"patches\": [[1, 0], [2, 2], [2, 3]], \"instructions\": \"NNESEESWNWW\"}";
        final var responsePayload = "{\"coords\" : [1, 3], \"patches\" : 1}";
        mockMvc.perform(REQUEST_BUILDER.content(requestPayload))
                .andExpect(status().isOk())
                .andExpect(content().json(responsePayload));
    }

    @Test
    void whenHasAnotherValidInput_shouldReturnCorrectResponse() throws Exception {
        final var requestPayload = "{\"roomSize\": [4, 4], \"coords\": [0, 1], \"patches\": [[0, 0], [0, 2], [1, 1], [1, 3], [2, 0], [2, 2], [3, 0], [3, 1]], \"instructions\": \"NNEEESSWWSEN\"}";
        final var responsePayload = "{\"coords\" : [2, 1], \"patches\" : 5}";
        mockMvc.perform(REQUEST_BUILDER.content(requestPayload))
                .andExpect(status().isOk())
                .andExpect(content().json(responsePayload));
    }
}