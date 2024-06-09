package com.wareline.agenda.domain.paciente;

import java.util.Objects;

import com.wareline.agenda.shared.helpers.IdHelper;
import com.wareline.agenda.shared.vo.Identifier;

public class PacienteID extends Identifier {
    private final String value;

    private PacienteID(final String value) {
        this.value = Objects.requireNonNull(value);
    }

    public static PacienteID unique() {
        return PacienteID.from(IdHelper.uuid());
    }

    public static PacienteID from(final String anId) {
        return new PacienteID(anId);
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final PacienteID that = (PacienteID) o;
        return getValue().equals(that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }

    @Override
    public String toString() {
        return "PacienteID{" +
                "value='" + value + '\'' +
                '}';
    }
}