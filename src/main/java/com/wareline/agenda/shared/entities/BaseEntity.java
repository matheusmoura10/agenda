package com.wareline.agenda.shared.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import com.wareline.agenda.shared.events.DomainEvent;
import com.wareline.agenda.shared.events.DomainEventPublisher;
import com.wareline.agenda.shared.validation.ValidationHandlerInterface;
import com.wareline.agenda.shared.vo.Identifier;

public abstract class BaseEntity<ID extends Identifier> {
    protected final ID id;
    private final List<DomainEvent> domainEvents;

    protected BaseEntity(final ID id) {
        this(id, null);
    }

    protected BaseEntity(final ID id, final List<DomainEvent> domainEvents) {

        this.id = id;
        this.domainEvents = new ArrayList<>(domainEvents == null ? Collections.emptyList() : domainEvents);
    }

    public abstract void validate(ValidationHandlerInterface handler);

    public ID getId() {
        return id;
    }

    public List<DomainEvent> getDomainEvents() {
        return Collections.unmodifiableList(domainEvents);
    }

    public void publishDomainEvents(final DomainEventPublisher publisher) {
        if (publisher == null) {
            return;
        }

        getDomainEvents()
                .forEach(publisher::publishEvent);

        this.domainEvents.clear();
    }

    public void registerEvent(final DomainEvent event) {
        if (event == null) {
            return;
        }

        this.domainEvents.add(event);
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
