package com.wareline.agenda.shared.entities;
import com.wareline.agenda.shared.vo.Identifier;

public abstract class AggregateRoot<ID extends Identifier> extends BaseEntity<ID> {

    protected AggregateRoot(final ID id) {
        super(id);
    }
}