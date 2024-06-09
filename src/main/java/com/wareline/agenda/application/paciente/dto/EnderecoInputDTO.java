package com.wareline.agenda.application.paciente.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EnderecoInputDTO(
    @NotBlank(message = "O CEP é obrigatório")
    @Size(min = 8, max = 8, message = "O CEP deve ter 8 caracteres")
    String cep,

    @NotBlank(message = "O logradouro é obrigatório")
    @Size(min = 3, max = 100, message = "O logradouro deve ter entre 3 e 100 caracteres")
    String logradouro,

    @NotBlank(message = "O número é obrigatório")
    @Size(min = 1, max = 10, message = "O número deve ter entre 1 e 10 caracteres")
    String numero,

    String complemento,

    @NotBlank(message = "O bairro é obrigatório")
    @Size(min = 3, max = 100, message = "O bairro deve ter entre 3 e 100 caracteres")
    String bairro,

    @NotBlank(message = "A cidade é obrigatória")
    @Size(min = 3, max = 100, message = "A cidade deve ter entre 3 e 100 caracteres")
    String cidade,

    @NotBlank(message = "O estado é obrigatório")
    @Size(min = 2, max = 2, message = "O estado deve ter 2 caracteres")
    String estado
) {

}
