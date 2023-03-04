package com.rafaelmgr12.medvollapi.domain.doctors;

import com.rafaelmgr12.medvollapi.domain.address.AddressDTO;
import com.rafaelmgr12.medvollapi.domain.appointment.Appointment;
import com.rafaelmgr12.medvollapi.domain.patient.Patient;
import com.rafaelmgr12.medvollapi.domain.patient.RegisterPatientDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class DoctorRepositoryTest {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("Deveria devolver null quando unico médico cadastrado não estiver disponivél na data")
    void chooseRandomDoctorFreeInDateScenario1(){
        LocalDateTime nextMondayAt10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);
        Doctor doctor = registerDoctor("Medico", "medico@voll.med", "123456", Speciality.CARDIOLOGIA);
        Patient patient = registerPatient("Paciente", "paciente@email.com", "00000000000");
        registerAppointment(doctor, patient, nextMondayAt10);

        Doctor freeDoctor = doctorRepository.chooseRandomFreeDoctorOnFreeDate(Speciality.CARDIOLOGIA, nextMondayAt10);

        assertThat(freeDoctor).isNull();
    }

    @Test
    @DisplayName("Deveria devolver medico quando ele estiver disponivel na data")
    void chooseRandomDoctorFreeInDateScenario2(){
        LocalDateTime nextMondayAt10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);
        Doctor doctor = registerDoctor("Medico", "medico@voll.med", "123456", Speciality.CARDIOLOGIA);

        Doctor freeDoctor = doctorRepository.chooseRandomFreeDoctorOnFreeDate(Speciality.CARDIOLOGIA, nextMondayAt10);

        assertThat(freeDoctor).isEqualTo(doctor);

    }

    private void registerAppointment(Doctor doctor, Patient patient, LocalDateTime dateTime) {
        Appointment appointment = new Appointment(null,doctor, patient, dateTime);
        em.persist(appointment);
    }

    private Doctor registerDoctor(String name, String email, String crm, Speciality speciality) {
        Doctor doctor = new Doctor(registerDoctorDTO(name,email,crm,speciality));
        em.persist(doctor);
        return doctor;
    }



    private Patient registerPatient(String name,String email,String cpf) {
        Patient patient = new Patient(registerPatientDTO(name,email,cpf));
        em.persist(patient);
        return patient;
    }

    private RegisterDoctorDTO registerDoctorDTO(String nome,String email,String crm, Speciality speciality) {
        return new RegisterDoctorDTO(
                nome,
                email,
                "61999999999",
                crm,
                speciality,
                addressDTO()
        );
    }

    private RegisterPatientDTO registerPatientDTO(String name,String email,String cpf) {
        return new RegisterPatientDTO(
                name,
                email,
                "61999999999",
                cpf,
                addressDTO());
    }
    private AddressDTO addressDTO() {
        return new AddressDTO(
                "rua xpto",
                "bairro",
                "00000000",
                "Brasilia",
                "DF",
                null,
                null
        );
    }

}