package ru.dmitry.selection_committee.gui.screens.profile;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import ru.dmitry.selection_committee.gui.views.InputView;
import ru.dmitry.selection_committee.resourse.R;

public class AddressView extends HorizontalLayout {

    private InputView<TextField> cityInput;

    private InputView<TextField> streetInput;

    private InputView<TextField> houseInput;

    private InputView<TextField> flatInput;

    public AddressView(){
        setSpacing(false);
        setSizeFull();
        setup();
    }

    private void setup(){

        cityInput = new InputView<>(new TextField(), R.Strings.CITY_HINT);
        cityInput.addInputStyle("v-textfield-profile");
        cityInput.setWidth("100%");

        streetInput = new InputView<>(new TextField(), R.Strings.STREET_HINT);
        streetInput.addInputStyle("v-textfield-profile");
        streetInput.setWidth("100%");

        houseInput = new InputView<>(new TextField(), R.Strings.HOUSE_HINT);
        houseInput.addInputStyle("v-textfield-profile");
        houseInput.setWidth("100%");

        flatInput = new InputView<>(new TextField(), R.Strings.FLAT);
        flatInput.addInputStyle("v-textfield-profile");
        flatInput.setWidth("100%");

        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setSpacing(false);
        verticalLayout.setMargin(false);
        verticalLayout.setWidth("90%");
        verticalLayout.addComponents(cityInput, houseInput);

        VerticalLayout verticalLayout1 = new VerticalLayout();
        verticalLayout1.setSpacing(false);
        verticalLayout1.setMargin(false);
        verticalLayout1.setWidth("90%");
        verticalLayout1.addComponents(streetInput, flatInput);

        addComponents(verticalLayout, verticalLayout1);

        setComponentAlignment(verticalLayout, Alignment.MIDDLE_CENTER);
        setComponentAlignment(verticalLayout1, Alignment.MIDDLE_CENTER);

    }

}
