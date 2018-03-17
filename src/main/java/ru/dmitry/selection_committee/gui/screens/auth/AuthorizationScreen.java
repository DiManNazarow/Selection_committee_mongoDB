package ru.dmitry.selection_committee.gui.screens.auth;

import com.vaadin.shared.ui.AlignmentInfo;
import com.vaadin.ui.*;
import ru.dmitry.selection_committee.gui.Navigator;
import ru.dmitry.selection_committee.gui.screens.HorizontalLayoutScreen;
import ru.dmitry.selection_committee.resourse.R;

import java.util.ArrayList;
import java.util.List;

public class AuthorizationScreen extends CustomLayout {

    public AuthorizationScreen() {
        super("authorization_screen");
        setSizeFull();
        getScreenComponents();
    }

    protected void getScreenComponents() {

        List<Component> components = new ArrayList<>();

//        CustomLayout customLayout = new CustomLayout("authorization_screen");
//        customLayout.setSizeFull();

//        VerticalLayout descriptionPart = new VerticalLayout();
//        descriptionPart.setSizeFull();
//        descriptionPart.addStyleName("authScreenDescriptionBackColor");
//
//        VerticalLayout content = new VerticalLayout();
        //content.setWidth("100%");

        Label welcome = new Label(R.Strings.AUTH_WELCOME);
        welcome.addStyleName("authScreenDescriptionTextColor");
        addComponent(welcome, "welcome");
        Label introduce = new Label(R.Strings.AUTH_INTRODUCE);
        introduce.addStyleName("authScreenDescriptionTextColor");
        addComponent(introduce, "introduce");
        Label observe = new Label(R.Strings.AUTH_OBSERVE);
        observe.addStyleName("authScreenDescriptionTextColor");
        addComponent(observe, "observe");
        Label control = new Label(R.Strings.AUTH_CONTROL);
        control.addStyleName("authScreenDescriptionTextColor");
        addComponent(control, "control");

//        content.addComponents(welcome, introduce, observe, control);
//        descriptionPart.addComponent(content);
//        descriptionPart.setComponentAlignment(content, new Alignment(AlignmentInfo.Bits.ALIGNMENT_VERTICAL_CENTER));
//
//        VerticalLayout signInPart = new VerticalLayout();
//        signInPart.setSizeFull();
//        signInPart.addStyleName("authScreenSignInPartBackColor");
//
//        addComponent(descriptionPart);
//        getComponent(0).setWidth("60%");
    }

}
