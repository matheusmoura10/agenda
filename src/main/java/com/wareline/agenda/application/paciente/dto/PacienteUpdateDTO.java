package com.wareline.agenda.application.paciente.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record PacienteUpdateDTO(
        @NotBlank(message = "O campo 'id' é obrigatório") String id,

        @Valid PacienteInputDTO PacienteInputDTO) {

}
