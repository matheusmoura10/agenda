package com.wareline.agenda.shared.vo;

public abstract class ValueObject {

    public abstract boolean equals(Object obj);

    public abstract int hashCode();

    public abstract String toString();
}
