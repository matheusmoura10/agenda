package com.wareline.agenda.shared.validation.validators;

import com.wareline.agenda.shared.validation.ValidationHandlerInterface;
import com.wareline.agenda.shared.validation.Error;
import com.wareline.agenda.shared.vo.EnderecoVO;

public class EnderecoValidator {

    private static final int LOGRADOURO_MAX_LENGTH = 100;
    private static final int LOGRADOURO_MIN_LENGTH = 3;
    private static final int NUMERO_MAX_LENGTH = 10;
    private static final int NUMERO_MIN_LENGTH = 1;
    private static final int COMPLEMENTO_MAX_LENGTH = 100;
    private static final int BAIRRO_MAX_LENGTH = 100;
    private static final int BAIRRO_MIN_LENGTH = 3;
    private static final int CIDADE_MAX_LENGTH = 100;
    private static final int CIDADE_MIN_LENGTH = 3;
    private static final int ESTADO_LENGTH = 2;
    private static final int CEP_LENGTH = 9;

    private final EnderecoVO endereco;
    private final ValidationHandlerInterface validationHandler;

    public EnderecoValidator(final EnderecoVO endereco, final ValidationHandlerInterface validationHandler) {
        this.endereco = endereco;
        this.validationHandler = validationHandler;
    }

    public void validate() {
        validateField(endereco.getLogradouro(), "Logradouro do endereço", LOGRADOURO_MAX_LENGTH, LOGRADOURO_MIN_LENGTH);
        validateField(endereco.getNumero(), "Número do endereço", NUMERO_MAX_LENGTH, NUMERO_MIN_LENGTH);
        validateOptionalField(endereco.getComplemento(), "Complemento do endereço", COMPLEMENTO_MAX_LENGTH);
        validateField(endereco.getBairro(), "Bairro do endereço", BAIRRO_MAX_LENGTH, BAIRRO_MIN_LENGTH);
        validateField(endereco.getCidade(), "Cidade do endereço", CIDADE_MAX_LENGTH, CIDADE_MIN_LENGTH);
        validateField(endereco.getEstado(), "Estado do endereço", ESTADO_LENGTH, ESTADO_LENGTH);
        validateCep(endereco.getCep());
    }

    private void validateField(String value, String fieldName, int maxLength, int minLength) {
        if (isNullOrEmpty(value)) {
            validationHandler.append(new Error(fieldName + " não pode ser vazio"));
        } else {
            if (value.length() > maxLength) {
                validationHandler.append(new Error(fieldName + " não pode ter mais de " + maxLength + " caracteres"));
            }
            if (value.length() < minLength) {
                validationHandler.append(new Error(fieldName + " não pode ter menos de " + minLength + " caracteres"));
            }
        }
    }

    private void validateOptionalField(String value, String fieldName, int maxLength) {
        if (value != null && value.length() > maxLength) {
            validationHandler.append(new Error(fieldName + " não pode ter mais de " + maxLength + " caracteres"));
        }
    }

    private void validateCep(String cep) {
        if (isNullOrEmpty(cep)) {
            validationHandler.append(new Error("CEP do endereço não pode ser vazio"));
        } else {
            if (cep.length() != CEP_LENGTH) {
                validationHandler.append(new Error("CEP do endereço deve ter " + CEP_LENGTH + " caracteres"));
            }
            if (!cep.matches("\\d{5}-\\d{3}")) {
                validationHandler.append(new Error("CEP do endereço inválido"));
            }
        }
    }

    private boolean isNullOrEmpty(String value) {
        return value == null || value.isEmpty();
    }
}