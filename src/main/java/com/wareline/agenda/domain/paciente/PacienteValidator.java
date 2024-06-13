package com.wareline.agenda.domain.paciente;

import com.wareline.agenda.shared.validation.ValidationHandlerInterface;
import com.wareline.agenda.shared.validation.Validator;
import com.wareline.agenda.shared.validation.validators.EnderecoValidator;
import com.wareline.agenda.shared.vo.EnderecoVO;

import java.util.Set;

import com.wareline.agenda.shared.validation.Error;

public class PacienteValidator extends Validator {

    private static final int NAME_MAX_LENGTH = 100;
    private static final int NAME_MIN_LENGTH = 3;
    private static final int PHONE_MAX_LENGTH = 15;
    private static final int PHONE_MIN_LENGTH = 8;
    private static final int EMAIL_MAX_LENGTH = 100;
    private static final int EMAIL_MIN_LENGTH = 3;
    private static final int MAX_ADDRESSES = 5;

    private final PacienteEntity pacienteEntity;

    public PacienteValidator(final PacienteEntity aPacienteEntity, final ValidationHandlerInterface aHandler) {
        super(aHandler);
        this.pacienteEntity = aPacienteEntity;
    }

    @Override
    public void validate() {
        validateName();
        validatePhone();
        validateEmail();
        validateEnderecos();
    }

    private void validateName() {
        String nome = pacienteEntity.getNome();
        if (isNullOrEmpty(nome)) {
            validationHandler().append(new Error("nome","Nome do paciente não pode ser vazio"));
        } else {
            if (nome.length() > NAME_MAX_LENGTH) {
                validationHandler().append(new Error("nome","Nome do paciente não pode ter mais de " + NAME_MAX_LENGTH + " caracteres"));
            }
            if (nome.length() < NAME_MIN_LENGTH) {
                validationHandler().append(new Error("nome","Nome do paciente não pode ter menos de " + NAME_MIN_LENGTH + " caracteres"));
            }
            if (nome.matches(".*\\d.*")) {
                validationHandler().append(new Error("nome","Nome do paciente não pode conter números"));
            }
        }
    }

    private void validatePhone() {
        String telefone = pacienteEntity.getTelefone();
        if (isNullOrEmpty(telefone)) {
            validationHandler().append(new Error("telefone","Telefone do paciente não pode ser vazio"));
        } else {
            if (telefone.length() > PHONE_MAX_LENGTH) {
                validationHandler().append(new Error("telefone","Telefone do paciente não pode ter mais de " + PHONE_MAX_LENGTH + " caracteres"));
            }
            if (telefone.length() < PHONE_MIN_LENGTH) {
                validationHandler().append(new Error("telefone","Telefone do paciente não pode ter menos de " + PHONE_MIN_LENGTH + " caracteres"));
            }
            if (telefone.matches(".*\\D.*")) {
                validationHandler().append(new Error("telefone","Telefone do paciente não pode conter letras"));
            }
        }
    }

    private void validateEmail() {
        String email = pacienteEntity.getEmail();
        if (isNullOrEmpty(email)) {
            validationHandler().append(new Error("email","Email do paciente não pode ser vazio"));
        } else {
            if (email.length() > EMAIL_MAX_LENGTH) {
                validationHandler().append(new Error("email","Email do paciente não pode ter mais de " + EMAIL_MAX_LENGTH + " caracteres"));
            }
            if (email.length() < EMAIL_MIN_LENGTH) {
                validationHandler().append(new Error("email","Email do paciente não pode ter menos de " + EMAIL_MIN_LENGTH + " caracteres"));
            }
            if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
                validationHandler().append(new Error("email","Email do paciente inválido"));
            }
        }
    }

    private void validateEnderecos() {
        Set<EnderecoVO> enderecos = pacienteEntity.getEnderecos();
        if (enderecos == null || enderecos.isEmpty()) {
            validationHandler().append(new Error("endereços","Endereços do paciente não podem ser vazios"));
        } else {
            if (enderecos.size() > MAX_ADDRESSES) {
                validationHandler().append(new Error("endereços","Paciente não pode ter mais de " + MAX_ADDRESSES + " endereços"));
            }
            for (EnderecoVO endereco : enderecos) {
                new EnderecoValidator(endereco, validationHandler()).validate();
            }
        }
    }

    private boolean isNullOrEmpty(String value) {
        return value == null || value.isEmpty();
    }
}