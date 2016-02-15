package de.ilay.vaadin.guice;

public interface Authorized<PERMISSION> {
    PERMISSION getNeededPermission();
}
