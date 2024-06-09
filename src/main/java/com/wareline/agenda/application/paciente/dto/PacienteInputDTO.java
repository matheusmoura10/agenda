package com.wareline.agenda.application.paciente.dto;

import java.util.Set;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PacienteInputDTO(
        @NotBlank(message = "O nome é obrigatório")
        @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
        String nome,

        @NotBlank(message = "O email é obrigatório")
        @Size(min = 3, max = 100, message = "O email deve ter entre 3 e 100 caracteres")
        @Email(message = "O email é inválido")
        String email,

        @NotBlank(message = "O telefone é obrigatório")
        @Size(min = 3, max = 20, message = "O telefone deve ter entre 3 e 20 caracteres")
        String telefone,

        @Valid
        @Size(min = 1, message = "Deve haver pelo menos um endereço")
        Set<EnderecoInputDTO> enderecos) {
}
