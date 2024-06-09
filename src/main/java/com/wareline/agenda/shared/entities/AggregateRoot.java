package com.wareline.agenda.shared.entities;

import java.util.List;

import com.wareline.agenda.shared.events.DomainEvent;
import com.wareline.agenda.shared.vo.Identifier;

public abstract class AggregateRoot<ID extends Identifier> extends BaseEntity<ID> {

    protected AggregateRoot(final ID id) {
        super(id);
    }

    protected AggregateRoot(final ID id, final List<DomainEvent> events) {
        super(id, events);
    }
}