package com.wareline.agenda.shared.validation;

public abstract class Validator {
    private final ValidationHandlerInterface handler;

    protected Validator(final ValidationHandlerInterface aHandler) {
        this.handler = aHandler;
    }

    public abstract void validate();

    protected ValidationHandlerInterface validationHandler() {
        return this.handler;
    }
}
