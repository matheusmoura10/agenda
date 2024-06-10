package com.wareline.agenda.shared.entities;
import java.util.Objects;
import com.wareline.agenda.shared.validation.ValidationHandlerInterface;
import com.wareline.agenda.shared.vo.Identifier;

public abstract class BaseEntity<ID extends Identifier> {
    protected final ID id;

    protected BaseEntity(final ID id) {
        this.id = id;
    }

    public abstract void validate(ValidationHandlerInterface handler);

    public ID getId() {
        return id;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        final BaseEntity<?> entity = (BaseEntity<?>) o;
        return getId().equals(entity.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
