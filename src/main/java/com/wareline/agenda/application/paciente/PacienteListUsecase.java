package com.wareline.agenda.application.paciente;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.wareline.agenda.application.paciente.mappers.PacienteMapper;
import com.wareline.agenda.domain.paciente.PacienteEntity;
import com.wareline.agenda.infra.model.PacienteModel;
import com.wareline.agenda.infra.repository.PacienteRepository;
import com.wareline.agenda.shared.paginacao.Pagination;
import com.wareline.agenda.shared.paginacao.SearchQuery;
import com.wareline.agenda.shared.usecase.UseCase;
import com.wareline.agenda.shared.validation.handler.Notification;

import io.vavr.control.Either;

@Service
public class PacienteListUsecase extends UseCase<SearchQuery, Either<Notification, Pagination<PacienteEntity>>> {

    private final PacienteRepository repository;
    private final PacienteMapper mapper;

    public PacienteListUsecase(PacienteRepository repository, PacienteMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Either<Notification, Pagination<PacienteEntity>> execute(SearchQuery input) {
        PageRequest pageRequest = PageRequest.of(input.page(), input.perPage());
        Page<PacienteModel> pacientesPage = repository.findAll(pageRequest);

        final var notification = Notification.create();

        // Mapeia os pacientes para PacienteOuputDTO
        List<PacienteEntity> pacientesDTO = pacientesPage
                .getContent()
                .stream()
                .map(mapper::modelToEntity)
                .toList();

        Pagination pagination = new Pagination<>(
                pacientesPage.getNumber(),
                pacientesPage.getSize(),
                pacientesPage.getTotalElements(),
                pacientesDTO.stream().map(mapper::entityToDtoInput).toList());

        return notification.hasError() ? Either.left(notification) : Either.right(pagination);
    }
}