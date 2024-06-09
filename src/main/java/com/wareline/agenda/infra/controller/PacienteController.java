package com.wareline.agenda.infra.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wareline.agenda.application.paciente.PacienteCreateUsecase;
import com.wareline.agenda.application.paciente.dto.PacienteInputDTO;
import com.wareline.agenda.domain.paciente.PacienteEntity;

import jakarta.validation.Valid;

@RestController("paciente")
public class PacienteController {

    private final PacienteCreateUsecase pacienteCreateUsecase;

    public PacienteController(PacienteCreateUsecase pacienteCreateUsecase) {
        this.pacienteCreateUsecase = pacienteCreateUsecase;
    }

    @PostMapping()
    public PacienteEntity criar(@Valid @RequestBody PacienteInputDTO paciente) {
        return pacienteCreateUsecase.execute(paciente);
    }

}
