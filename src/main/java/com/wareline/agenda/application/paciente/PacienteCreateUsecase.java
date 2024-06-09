package com.wareline.agenda.application.paciente;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.wareline.agenda.application.paciente.dto.PacienteInputDTO;
import com.wareline.agenda.application.paciente.mappers.PacienteMapper;
import com.wareline.agenda.domain.paciente.PacienteEntity;
import com.wareline.agenda.infra.model.PacienteModel;
import com.wareline.agenda.infra.repository.PacienteRepository;
import com.wareline.agenda.shared.usecase.UseCase;

@Service
public class PacienteCreateUsecase extends UseCase<PacienteInputDTO, PacienteEntity> {

    private final PacienteRepository repository;
    private final PacienteMapper mapper;

    public PacienteCreateUsecase(
            PacienteRepository repository,
            PacienteMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public PacienteEntity execute(@RequestBody PacienteInputDTO input) {

        PacienteEntity paciente = mapper.dtoInputToEntity(input);

        PacienteModel model = mapper.entityToModel(paciente);

        PacienteModel savedModel = repository.save(model);

        return mapper.modelToEntity(savedModel);
    }

}
