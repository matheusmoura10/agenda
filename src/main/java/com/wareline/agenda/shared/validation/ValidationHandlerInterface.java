package com.wareline.agenda.shared.validation;

import java.util.List;

public interface ValidationHandlerInterface {
    ValidationHandlerInterface append(Error anError);

    ValidationHandlerInterface append(ValidationHandlerInterface anHandler);

    <T> T validate(Validation<T> aValidation);

    List<Error> getErrors();

    default boolean hasError() {
        return getErrors() != null && !getErrors().isEmpty();
    }

    default Error firstError() {
        if (getErrors() != null && !getErrors().isEmpty()) {
            return getErrors().get(0);
        } else {
            return null;
        }
    }

    interface Validation<T> {
        T validate();
    }
}
