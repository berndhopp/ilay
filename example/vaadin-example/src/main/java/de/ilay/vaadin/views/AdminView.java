package de.ilay.vaadin.views;

import com.vaadin.guice.annotation.GuiceView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;

@GuiceView(name = "admin")
public class AdminView extends Panel implements View{

    AdminView(){
        setContent(new Label("this is admin-only"));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }
}
