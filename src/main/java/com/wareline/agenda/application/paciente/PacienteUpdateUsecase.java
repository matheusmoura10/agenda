package com.wareline.agenda.application.paciente;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.wareline.agenda.application.paciente.dto.PacienteOuputDTO;
import com.wareline.agenda.application.paciente.dto.PacienteUpdateDTO;
import com.wareline.agenda.application.paciente.mappers.PacienteMapper;
import com.wareline.agenda.domain.paciente.PacienteEntity;
import com.wareline.agenda.infra.model.PacienteModel;
import com.wareline.agenda.infra.repository.PacienteRepository;
import com.wareline.agenda.shared.usecase.UseCase;
import com.wareline.agenda.shared.validation.Error;
import com.wareline.agenda.shared.validation.handler.Notification;

import io.vavr.control.Either;

@Service
public class PacienteUpdateUsecase extends UseCase<PacienteUpdateDTO, Either<Notification, PacienteOuputDTO>> {

    private final PacienteRepository repository;
    private final PacienteMapper mapper;

    public PacienteUpdateUsecase(PacienteRepository repository, PacienteMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Either<Notification, PacienteOuputDTO> execute(PacienteUpdateDTO input) {
        final var notification = Notification.create();

        // Retrieve the existing patient
        Optional<PacienteModel> pacienteModelOpt = repository.findById(input.id());

        if (pacienteModelOpt.isEmpty()) {
            notification.append(new Error("sistema","Paciente não encontrado"));
            return Either.left(notification);
        }

        PacienteModel pacienteModel = pacienteModelOpt.get();
        PacienteEntity pacienteEntity = mapper.modelToEntity(pacienteModel);

        try {
            pacienteEntity = mapper.updateEntityFromDto(input, pacienteEntity);
        } catch (IllegalArgumentException e) {
            notification.append(new Error("sistema","Erro ao mapear propriedades: " + e.getMessage()));
            return Either.left(notification);
        }

        PacienteModel updatedPacienteModel = mapper.entityToModel(pacienteEntity);
        repository.save(updatedPacienteModel);

        PacienteOuputDTO output = mapper.entityToDtoInput(pacienteEntity);

        return notification.hasError() ? Either.left(notification) : Either.right(output);
    }
}