package de.ilay.vaadin.views;

import com.vaadin.guice.annotation.GuiceView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;

@GuiceView(name = "")
public class PublicView extends Panel implements View{

    PublicView(){
        setContent(new Label("this is public"));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }
}
