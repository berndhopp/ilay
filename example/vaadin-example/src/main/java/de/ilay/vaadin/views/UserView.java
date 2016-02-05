package de.ilay.vaadin.views;

import com.vaadin.guice.annotation.GuiceView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;

@GuiceView(name = "user")
public class UserView extends Panel implements View{

    UserView(){
        setContent(new Label("this is user-only"));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }
}
