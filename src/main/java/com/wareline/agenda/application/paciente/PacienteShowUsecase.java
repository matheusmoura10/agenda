package com.wareline.agenda.application.paciente;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.wareline.agenda.application.paciente.dto.PacienteOuputDTO;
import com.wareline.agenda.application.paciente.mappers.PacienteMapper;
import com.wareline.agenda.domain.paciente.PacienteEntity;
import com.wareline.agenda.infra.model.PacienteModel;
import com.wareline.agenda.infra.repository.PacienteRepository;
import com.wareline.agenda.shared.usecase.UseCase;
import com.wareline.agenda.shared.validation.handler.Notification;
import com.wareline.agenda.shared.validation.Error;

import io.vavr.control.Either;

@Service
public class PacienteShowUsecase extends UseCase<String, Either<Notification, PacienteOuputDTO>> {

    private final PacienteRepository repository;
    private final PacienteMapper mapper;

    public PacienteShowUsecase(
            PacienteRepository repository,
            PacienteMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Either<Notification, PacienteOuputDTO> execute(String input) {

        final var notification = Notification.create();

        Optional<PacienteModel> model = repository.findById(input);

        if (model.isEmpty()) {
            notification.append(new Error("Paciente não encontrado"));
            return Either.left(notification);
        }

        PacienteEntity paciente = mapper.modelToEntity(model.get());

        return notification.hasError() ? Either.left(notification) : Either.right(mapper.entityToDtoInput(paciente));
    }

}
