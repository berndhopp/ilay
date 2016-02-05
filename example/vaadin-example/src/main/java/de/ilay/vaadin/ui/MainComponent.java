package de.ilay.vaadin.ui;

import com.google.inject.Inject;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.guice.annotation.UIScope;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import de.ilay.example.vaadin.service.AuthenticationService;
import de.ilay.vaadin.impl.SampleAuthenticationService;
import de.ilay.vaadin.impl.SampleCredentials;
import de.ilay.vaadin.impl.SampleUser;

import java.util.Optional;

@UIScope
public class MainComponent extends VerticalLayout {

    @Inject
    MainComponent(MainViewContainer mainViewContainer, final SampleAuthenticationService authenticationService){
        HorizontalLayout headerLeft = new HorizontalLayout();

        Button lnkMain = new Button("start", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                UI.getCurrent().getNavigator().navigateTo("");
            }
        });

        Button lnkUser = new Button("user-only", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                UI.getCurrent().getNavigator().navigateTo("user");
            }
        });

        Button lnkAdmin = new Button("admin-only", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                UI.getCurrent().getNavigator().navigateTo("admin");
            }
        });

        headerLeft.addComponents(lnkMain, lnkUser, lnkAdmin);


        HorizontalLayout headerRight = new HorizontalLayout();

        final Label lblEmail = new Label("email");

        final Label lblPassword = new Label("Password");

        final TextField txtEmail = new TextField();
        final PasswordField txtPassword = new PasswordField();

        Button btnLogin = new Button("Login");
        btnLogin.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                final Optional<SampleUser> user = authenticationService.login(new SampleCredentials(txtEmail.getValue(), txtPassword.getValue()));

                if(user.isPresent()){
                    Notification.show("logged in");
                } else {
                    Notification.show("could not log in");
                }

                lblEmail.setValue("");
                lblPassword.setValue("");
            }
        });

        headerRight.addComponents(lblEmail, txtEmail, lblPassword, txtPassword, btnLogin);
        headerRight.setComponentAlignment(lblEmail, Alignment.TOP_RIGHT);
        headerRight.setComponentAlignment(txtEmail, Alignment.TOP_RIGHT);
        headerRight.setComponentAlignment(lblPassword, Alignment.TOP_RIGHT);
        headerRight.setComponentAlignment(txtPassword, Alignment.TOP_RIGHT);

        addComponents(headerRight, mainViewContainer);
        setExpandRatio(mainViewContainer, 1);
        setSizeFull();
    }
}
