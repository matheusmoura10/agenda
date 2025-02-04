package com.wareline.agenda.application.paciente;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.wareline.agenda.application.paciente.dto.PacienteInputDTO;
import com.wareline.agenda.application.paciente.dto.PacienteOuputDTO;
import com.wareline.agenda.application.paciente.mappers.PacienteMapper;
import com.wareline.agenda.domain.paciente.PacienteEntity;
import com.wareline.agenda.infra.model.PacienteModel;
import com.wareline.agenda.infra.repository.PacienteRepository;
import com.wareline.agenda.shared.usecase.UseCase;
import com.wareline.agenda.shared.validation.handler.Notification;
import io.vavr.control.Either;

@Service
public class PacienteCreateUsecase extends UseCase<PacienteInputDTO, Either<Notification, PacienteOuputDTO>> {

    private final PacienteRepository repository;
    private final PacienteMapper mapper;

    public PacienteCreateUsecase(
            PacienteRepository repository,
            PacienteMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Either<Notification, PacienteOuputDTO> execute(@RequestBody PacienteInputDTO input) {
        final var notification = Notification.create();
        PacienteEntity paciente = mapper.dtoInputToEntity(input);
        paciente.validate(notification);

        PacienteModel model = mapper.entityToModel(paciente);

        repository.save(model);

        return notification.hasError() ? Either.left(notification) : Either.right(mapper.entityToDtoInput(paciente));
    }

}
