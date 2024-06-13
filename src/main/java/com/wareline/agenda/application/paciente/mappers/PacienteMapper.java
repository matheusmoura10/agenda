package com.wareline.agenda.application.paciente.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.wareline.agenda.application.paciente.dto.PacienteInputDTO;
import com.wareline.agenda.application.paciente.dto.PacienteOuputDTO;
import com.wareline.agenda.application.paciente.dto.PacienteUpdateDTO;
import com.wareline.agenda.domain.paciente.PacienteEntity;
import com.wareline.agenda.infra.model.PacienteModel;

@Mapper(componentModel = "spring")
public interface PacienteMapper {

    PacienteMapper INSTANCE = Mappers.getMapper(PacienteMapper.class);

    @Mapping(target = "id", source = "id.value")
    public PacienteModel entityToModel(PacienteEntity entity);

    @Mapping(target = "id", source = "id")
    public PacienteEntity modelToEntity(PacienteModel model);

    @Mapping(target = "id", ignore = true)
    public PacienteEntity dtoInputToEntity(PacienteInputDTO dto);

    @Mapping(target = "id", source = "id.value")
    public PacienteOuputDTO entityToDtoInput(PacienteEntity entity);

    @Mapping(target = "id", source = "input.id")
    @Mapping(target = "nome", source = "input.PacienteInputDTO.nome")
    @Mapping(target = "telefone", source = "input.PacienteInputDTO.telefone")
    @Mapping(target = "email", source = "input.PacienteInputDTO.email")
    @Mapping(target = "enderecos", source = "input.PacienteInputDTO.enderecos")
    public PacienteEntity updateEntityFromDto(PacienteUpdateDTO input, PacienteEntity pacienteEntity);

}
