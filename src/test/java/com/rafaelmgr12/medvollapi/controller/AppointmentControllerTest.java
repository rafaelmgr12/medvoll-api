package com.rafaelmgr12.medvollapi.controller;

import com.rafaelmgr12.medvollapi.domain.appointment.AppointmentDetailDTO;
import com.rafaelmgr12.medvollapi.domain.appointment.DataSchedulingConsultationDTO;
import com.rafaelmgr12.medvollapi.domain.appointment.ScheduleAppointment;
import com.rafaelmgr12.medvollapi.domain.doctors.Speciality;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class AppointmentControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<AppointmentDetailDTO> appointmentDetailDTOJacksonTester;

    @Autowired
    private JacksonTester<DataSchedulingConsultationDTO> dataSchedulingConsultationDTOJacksonTester;

    @MockBean
    private ScheduleAppointment scheduleAppointment;

    @Test
    @DisplayName("Deveria devolver codigo http 400 quando informacoes estao invalidas")
    @WithMockUser
    void scheduleScenario1() throws Exception {
        MockHttpServletResponse response = mvc.perform(post("/consultas"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria devolver codigo http 200 quando informacoes estao validas")
    @WithMockUser
    void scheduleScenario2() throws Exception {
        LocalDateTime date = LocalDateTime.now().plusHours(1);
        Speciality speciality = Speciality.CARDIOLOGIA;

        AppointmentDetailDTO appointmentDetailDTO = new AppointmentDetailDTO(null, 2l, 5l, date);
        when(scheduleAppointment.schedule(any())).thenReturn(appointmentDetailDTO);

        MockHttpServletResponse response =  mvc
                .perform(
                        post("/consultas")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(dataSchedulingConsultationDTOJacksonTester.write(
                                        new DataSchedulingConsultationDTO(2l, 5l, date, speciality)
                                ).getJson())
                )
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var expectJson = appointmentDetailDTOJacksonTester.write(
                appointmentDetailDTO
        ).getJson();

        assertThat(response.getContentAsString()).isEqualTo(expectJson);

    }


}