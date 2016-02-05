package de.ilay.vaadin.ui;

import com.google.inject.Inject;

import javax.servlet.annotation.WebServlet;

import com.vaadin.guice.annotation.Configuration;
import com.vaadin.guice.annotation.GuiceUI;
import com.vaadin.guice.server.GuiceVaadinServlet;
import com.vaadin.guice.annotation.ViewContainer;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

@Theme("mytheme")
@Widgetset("de.ilay.MyAppWidgetset")
@GuiceUI
public class IlayGuiceUI extends UI {

    @Inject
    private MainComponent mainComponent;

    @Inject
    @ViewContainer
    private MainViewContainer viewContainer;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        setContent(mainComponent);
    }

    @Configuration(modules = {}, basePackage = "de.ilay.vaadin.")
    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = IlayGuiceUI.class, productionMode = false)
    public static class MyUIServlet extends GuiceVaadinServlet {
    }
}
