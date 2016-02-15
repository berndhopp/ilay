package de.ilay.vaadin.guice;

import com.vaadin.ui.Component;

@SetInclusionAnnotation
public interface AuthorizedComponent<PERMISSION> extends Component {
    PERMISSION getNeededPermission();
}
