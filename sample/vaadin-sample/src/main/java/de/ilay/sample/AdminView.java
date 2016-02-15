package de.ilay.sample;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;

import de.ilay.groups.GroupPermission;
import de.ilay.sample.auth.SampleGroup;
import de.ilay.vaadin.guice.AuthorizedView;

import static de.ilay.sample.auth.SampleGroup.ADMIN;

public class AdminView extends Panel implements AuthorizedView<GroupPermission<SampleGroup>> {

    private final GroupPermission<SampleGroup> neededPermission = GroupPermission.of(ADMIN);

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        setContent(new Label("this is confidental"));
    }

    @Override
    public GroupPermission<SampleGroup> getNeededPermission() {
        return neededPermission;
    }
}
