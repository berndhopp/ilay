package de.ilay.vaadin;

import com.vaadin.server.VaadinSession;

import de.ilay.api.SessionConnector;
import de.ilay.api.User;

import java.util.Optional;

public class VaadinSessionConnector<USER extends User<?>> implements SessionConnector<USER> {

    private final Class<USER> userType;

    public VaadinSessionConnector(Class<USER> userType) {
        this.userType = userType;
    }

    public Optional<USER> getCurrent() {
        return Optional.ofNullable(VaadinSession.getCurrent().getAttribute(userType));
    }

    public void setCurrent(USER currentUser) {
        VaadinSession.getCurrent().setAttribute(userType, currentUser);
    }
}
