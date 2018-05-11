package ru.dmitry.selection_committee.gui.views;

import com.vaadin.event.MouseEvents;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.*;
import ru.dmitry.selection_committee.server.models.Admin;
import ru.dmitry.selection_committee.server.models.Enrollee;

public class HeaderProfileView extends HorizontalLayout {

    private Label name;

    private Image image;

    private PopupView popupView;

    private HeaderActionListener headerActionListener;

    public interface HeaderActionListener {
        void onGoToProfileAction();
        void onGoToControlAction();
        void onSignOutAction();
    }

    public HeaderProfileView(Admin admin) {
        setup(admin);
    }

    public HeaderProfileView(Enrollee enrollee) {
        setup(enrollee);
    }

    private void setup(Admin admin){

        name = new Label(admin.getLogin());
        name.addStyleName("v-label-enrolle_title");

        VerticalLayout divider = new VerticalLayout();
        divider.setWidth(2, Unit.PIXELS);
        divider.setHeight("100%");
        divider.setMargin(false);
        divider.setSpacing(false);
        divider.addStyleName("v-profile_divider");

        image = new Image(null, new ThemeResource("img/polygon.svg"));
        image.setWidth(20, Unit.PIXELS);
        image.setHeight(20, Unit.PIXELS);
        image.addClickListener(new MouseEvents.ClickListener() {
            @Override
            public void click(MouseEvents.ClickEvent clickEvent) {
                popupView.setPopupVisible(true);
            }
        });

        addComponents(name, divider, image);
        setComponentAlignment(name, Alignment.MIDDLE_LEFT);
        setComponentAlignment(divider, Alignment.MIDDLE_CENTER);
        setComponentAlignment(image, Alignment.MIDDLE_RIGHT);
        createPopupAdmin();

    }

    private void setup(Enrollee enrollee){

        name = new Label(enrollee.getFirstName());
        name.addStyleName("v-label-enrolle_title");

        VerticalLayout divider = new VerticalLayout();
        divider.setWidth(2, Unit.PIXELS);
        divider.setMargin(false);
        divider.setSpacing(false);
        divider.addStyleName("v-profile_divider");

        image = new Image(null, new ThemeResource("img/polygon.svg"));
        image.setWidth(20, Unit.PIXELS);
        image.setHeight(20, Unit.PIXELS);
        image.addClickListener(new MouseEvents.ClickListener() {
            @Override
            public void click(MouseEvents.ClickEvent clickEvent) {
                popupView.setPopupVisible(true);
            }
        });

        addComponents(name, divider, image);
        setComponentAlignment(name, Alignment.MIDDLE_LEFT);
        setComponentAlignment(divider, Alignment.MIDDLE_CENTER);
        setComponentAlignment(image, Alignment.MIDDLE_RIGHT);
        createPopupEnrolle();

    }

    private void createPopupAdmin(){
        VerticalLayout verticalLayout = new VerticalLayout();
        Button toControl = new Button("К управлению");
        toControl.addStyleName("v-button-to_");
        toControl.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                if (headerActionListener != null){
                    headerActionListener.onGoToControlAction();
                }
            }
        });
        Button signOut = new Button("Выйти");
        signOut.addStyleName("v-button-sign_out_popup");
        signOut.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                if (headerActionListener != null){
                    headerActionListener.onSignOutAction();
                }
            }
        });
        verticalLayout.addComponents(toControl, signOut);
        popupView = new PopupView(null, verticalLayout);
        addComponent(popupView);
    }

    private void createPopupEnrolle(){
        VerticalLayout verticalLayout = new VerticalLayout();
        Button toProfile = new Button("К профилю");
        toProfile.addStyleName("v-button-to_");
        toProfile.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                if (headerActionListener != null){
                    headerActionListener.onGoToProfileAction();
                }
            }
        });
        Button signOut = new Button("Выйти");
        signOut.addStyleName("v-button-sign_out_popup");
        signOut.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                if (headerActionListener != null){
                    headerActionListener.onSignOutAction();
                }
            }
        });
        verticalLayout.addComponents(toProfile, signOut);
        popupView = new PopupView(null, verticalLayout);
        addComponent(popupView);
    }

    public void setHeaderActionListener(HeaderActionListener headerActionListener) {
        this.headerActionListener = headerActionListener;
    }
}
