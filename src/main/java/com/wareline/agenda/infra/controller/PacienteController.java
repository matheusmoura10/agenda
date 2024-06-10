package com.wareline.agenda.infra.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wareline.agenda.application.paciente.PacienteCreateUsecase;
import com.wareline.agenda.application.paciente.PacienteListUsecase;
import com.wareline.agenda.application.paciente.PacienteShowUsecase;
import com.wareline.agenda.application.paciente.PacienteUpdateUsecase;
import com.wareline.agenda.application.paciente.dto.PacienteInputDTO;
import com.wareline.agenda.application.paciente.dto.PacienteOuputDTO;
import com.wareline.agenda.application.paciente.dto.PacienteUpdateDTO;
import com.wareline.agenda.domain.paciente.PacienteEntity;
import com.wareline.agenda.shared.paginacao.Pagination;
import com.wareline.agenda.shared.paginacao.SearchQuery;
import com.wareline.agenda.shared.validation.handler.Notification;

import java.net.URI;
import jakarta.validation.Valid;
import java.util.function.Function;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;

@RestController("paciente")
public class PacienteController {

        private final PacienteCreateUsecase pacienteCreateUsecase;
        private final PacienteShowUsecase pacienteShowUsecase;
        private final PacienteUpdateUsecase pacienteUpdateUsecase;
        private final PacienteListUsecase pacienteListUsecase;

        public PacienteController(PacienteCreateUsecase pacienteCreateUsecase,
                        PacienteShowUsecase pacienteShowUsecase,
                        PacienteUpdateUsecase pacienteUpdateUsecase,
                        PacienteListUsecase pacienteListUsecase) {
                this.pacienteCreateUsecase = pacienteCreateUsecase;
                this.pacienteShowUsecase = pacienteShowUsecase;
                this.pacienteUpdateUsecase = pacienteUpdateUsecase;
                this.pacienteListUsecase = pacienteListUsecase;
        }

        @PostMapping()
        public ResponseEntity<?> criar(@Valid @RequestBody PacienteInputDTO paciente) {

                final Function<Notification, ResponseEntity<?>> onError = notification -> ResponseEntity
                                .unprocessableEntity()
                                .body(notification);

                final Function<PacienteOuputDTO, ResponseEntity<?>> onSuccess = output -> ResponseEntity
                                .created(URI.create("/categories/" + output.id())).body(output);

                return this.pacienteCreateUsecase.execute(paciente)
                                .fold(onError, onSuccess::apply);
        }

        @GetMapping(value = "{id}")
        public ResponseEntity<?> obterPaciente(@PathVariable("id") String id) {

                final Function<Notification, ResponseEntity<?>> onError = notification -> ResponseEntity
                                .unprocessableEntity()
                                .body(notification);

                final Function<PacienteOuputDTO, ResponseEntity<?>> onSuccess = output -> ResponseEntity.ok(output);

                return this.pacienteShowUsecase.execute(id)
                                .fold(onError, onSuccess::apply);
        }

        @PutMapping("{id}")
        public ResponseEntity<?> atualizar(@PathVariable String id, @RequestBody PacienteInputDTO entity) {

                PacienteUpdateDTO pacienteUpdateDTO = new PacienteUpdateDTO(
                                id, entity);

                final Function<Notification, ResponseEntity<?>> onError = notification -> ResponseEntity
                                .unprocessableEntity()
                                .body(notification);

                final Function<PacienteOuputDTO, ResponseEntity<?>> onSuccess = output -> ResponseEntity.ok(output);

                return this.pacienteUpdateUsecase.execute(pacienteUpdateDTO)
                                .fold(onError, onSuccess::apply);
        }

        @GetMapping()
        public ResponseEntity<?> listarPacientes(@RequestParam(value = "page", defaultValue = "0") int page,
                        @RequestParam(value = "perPage", defaultValue = "10") int perPage) {

                final Function<Notification, ResponseEntity<?>> onError = notification -> ResponseEntity
                                .unprocessableEntity()
                                .body(notification);

                final Function<Pagination<PacienteEntity>, ResponseEntity<?>> onSuccess = output -> ResponseEntity
                                .ok(output);

                return this.pacienteListUsecase.execute(new SearchQuery(page, perPage, null, null, null))
                                .fold(onError, onSuccess::apply);

        }

}
