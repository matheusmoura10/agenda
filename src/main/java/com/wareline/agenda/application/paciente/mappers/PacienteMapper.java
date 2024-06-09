package com.wareline.agenda.application.paciente.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.wareline.agenda.application.paciente.dto.PacienteInputDTO;
import com.wareline.agenda.domain.paciente.PacienteEntity;
import com.wareline.agenda.infra.model.PacienteModel;

@Mapper(componentModel = "spring")
public interface PacienteMapper {

    PacienteMapper INSTANCE = Mappers.getMapper(PacienteMapper.class);

    @Mapping(target = "id", source = "id.value")
    public PacienteModel entityToModel(PacienteEntity entity);

    @Mapping(source = "id", target = "id")
    public PacienteEntity modelToEntity(PacienteModel model);

    public PacienteEntity dtoInputToEntity(PacienteInputDTO dto);
}
