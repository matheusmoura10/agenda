package com.wareline.agenda.application.paciente.dto;
import java.util.Set;
public record PacienteOuputDTO(
        String id,
        String nome,
        String email,
        String telefone,
        Set<EnderecoInputDTO> enderecos
) {

}
