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

    private static final String ERROR_EMPTY = "%s não pode ser vazio";
    private static final String ERROR_MIN_LENGTH = "%s não pode ter menos de %d caracteres";
    private static final String ERROR_MAX_LENGTH = "%s não pode ter mais de %d caracteres";
    private static final String ERROR_INVALID_CEP = "CEP do endereço inválido";
    private static final String ERROR_INVALID_CEP_LENGTH = "CEP do endereço deve ter " + CEP_LENGTH + " caracteres";

    private final EnderecoVO endereco;
    private final ValidationHandlerInterface validationHandler;

    public EnderecoValidator(final EnderecoVO endereco, final ValidationHandlerInterface validationHandler) {
        this.endereco = endereco;
        this.validationHandler = validationHandler;
    }

    public void validate() {
        validateFieldLength(endereco.getLogradouro(), "Logradouro do endereço", LOGRADOURO_MAX_LENGTH, LOGRADOURO_MIN_LENGTH);
        validateFieldLength(endereco.getNumero(), "Número do endereço", NUMERO_MAX_LENGTH, NUMERO_MIN_LENGTH);
        validateOptionalFieldLength(endereco.getComplemento(), "Complemento do endereço", COMPLEMENTO_MAX_LENGTH);
        validateFieldLength(endereco.getBairro(), "Bairro do endereço", BAIRRO_MAX_LENGTH, BAIRRO_MIN_LENGTH);
        validateFieldLength(endereco.getCidade(), "Cidade do endereço", CIDADE_MAX_LENGTH, CIDADE_MIN_LENGTH);
        validateExactFieldLength(endereco.getEstado(), "Estado do endereço", ESTADO_LENGTH);
        validateCep(endereco.getCep());
    }

    private void validateFieldLength(String value, String fieldName, int maxLength, int minLength) {
        if (isNullOrEmpty(value)) {
            validationHandler.append(new Error(fieldName, String.format(ERROR_EMPTY, fieldName)));
        } else {
            if (value.length() > maxLength) {
                validationHandler.append(new Error(fieldName, String.format(ERROR_MAX_LENGTH, fieldName, maxLength)));
            }
            if (value.length() < minLength) {
                validationHandler.append(new Error(fieldName, String.format(ERROR_MIN_LENGTH, fieldName, minLength)));
            }
        }
    }

    private void validateOptionalFieldLength(String value, String fieldName, int maxLength) {
        if (value != null && value.length() > maxLength) {
            validationHandler.append(new Error(fieldName, String.format(ERROR_MAX_LENGTH, fieldName, maxLength)));
        }
    }

    private void validateExactFieldLength(String value, String fieldName, int length) {
        if (isNullOrEmpty(value)) {
            validationHandler.append(new Error(fieldName, String.format(ERROR_EMPTY, fieldName)));
        } else if (value.length() != length) {
            validationHandler.append(new Error(fieldName, String.format(ERROR_MIN_LENGTH, fieldName, length)));
        }
    }

    private void validateCep(String cep) {
        if (isNullOrEmpty(cep)) {
            validationHandler.append(new Error("CEP", String.format(ERROR_EMPTY, "CEP")));
        } else {
            if (cep.length() != CEP_LENGTH) {
                validationHandler.append(new Error("CEP", ERROR_INVALID_CEP_LENGTH));
            } else if (!cep.matches("\\d{5}-\\d{3}")) {
                validationHandler.append(new Error("CEP", ERROR_INVALID_CEP));
            }
        }
    }

    private boolean isNullOrEmpty(String value) {
        return value == null || value.isEmpty();
    }
}