package com.wareline.agenda.infra.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.wareline.agenda.infra.model.PacienteModel;

public interface PacienteRepository extends MongoRepository<PacienteModel, String> {


}
