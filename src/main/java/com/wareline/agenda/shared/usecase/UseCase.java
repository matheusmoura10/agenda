package com.wareline.agenda.shared.usecase;

public abstract class UseCase<INPUT, OUTPUT> {
    public abstract OUTPUT execute(final INPUT input);
}
