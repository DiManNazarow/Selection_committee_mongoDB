package ru.dmitry.selection_committee.gui.screens.admin;

import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import ru.dmitry.selection_committee.gui.ScreenNavigator;
import ru.dmitry.selection_committee.gui.screens.admin.presenters.AddInstitutionScreenPresenter;
import ru.dmitry.selection_committee.gui.screens.admin.presenters.AddInstitutionScreenView;
import ru.dmitry.selection_committee.gui.screens.base.CustomLayoutScreen;
import ru.dmitry.selection_committee.gui.views.InputView;
import ru.dmitry.selection_committee.resourse.R;
import ru.dmitry.selection_committee.server.services.InstitutionService;

public class AddInstitutionScreen extends CustomLayoutScreen implements AddInstitutionScreenView {

    private final String URL = "add_institution";

    private InputView shortNameInput;

    private InputView fullNameInput;

    private InputView cityInput;

    private InputView streetInput;

    private InputView houseInput;

    private AddInstitutionScreenPresenter addInstitutionScreenPresenter;

    public AddInstitutionScreen(ScreenNavigator screenNavigator) {
        super(screenNavigator, "add_institution_screen");
        setSizeFull();
        addInstitutionScreenPresenter = new AddInstitutionScreenPresenter(this, screenNavigator.getInstitutionService());
    }

    @Override
    protected void addComponents() {

        Label header = new Label(R.Strings.INSTITUTION);
        header.addStyleName("v-headerlabel");
        addComponent(header, "header");

        shortNameInput = new InputView(new TextField(), R.Strings.SHORT_NAME_HINT);
        shortNameInput.addInputStyle("v-textfield-register");
        shortNameInput.setTextChangeListener(this::onShortNameChanged);
        addComponent(shortNameInput, "short_name_field");

        fullNameInput = new InputView(new TextField(), R.Strings.FULL_NAME_HINT);
        fullNameInput.addInputStyle("v-textfield-register");
        fullNameInput.setTextChangeListener(this::onLongNameChanged);
        addComponent(fullNameInput, "full_name_field");

        cityInput = new InputView(new TextField(), R.Strings.CITY_HINT);
        cityInput.addInputStyle("v-textfield-register");
        cityInput.setTextChangeListener(this::onCityChanged);
        addComponent(cityInput, "city_field");

        streetInput = new InputView(new TextField(), R.Strings.STREET_HINT);
        streetInput.addInputStyle("v-textfield-register");
        streetInput.setTextChangeListener(this::onStreetChanged);
        addComponent(streetInput, "street_field");

        houseInput = new InputView(new TextField(), R.Strings.HOUSE_HINT);
        houseInput.addInputStyle("v-textfield-register");
        houseInput.setTextChangeListener(this::onHouseChanged);
        addComponent(houseInput, "house_field");

        Button addButton = new Button(R.Strings.ADD);
        addButton.addStyleName("v-button-add");
        addButton.addClickListener(this::onAddButtonClick);
        addComponent(addButton, "add_button");

    }

    private void onShortNameChanged(CharSequence text){
        addInstitutionScreenPresenter.setShortName(text.toString());
    }

    private void onLongNameChanged(CharSequence text){
        addInstitutionScreenPresenter.setFullName(text.toString());
    }

    private void onCityChanged(CharSequence text){
        addInstitutionScreenPresenter.setCity(text.toString());
    }

    private void onStreetChanged(CharSequence text){
        addInstitutionScreenPresenter.setStreet(text.toString());
    }

    private void onHouseChanged(CharSequence text){
        addInstitutionScreenPresenter.setHouse(text.toString());
    }

    private void onAddButtonClick(Button.ClickEvent clickEvent){
        addInstitutionScreenPresenter.add();
    }

    @Override
    public String getUrl() {
        return URL;
    }

    @Override
    public void onSuccessAdded() {
        screenNavigator.goBack();
    }

    @Override
    public void onFailAdded() {
        Notification.show("Ошибка добавления учебного заведения");
    }

    @Override
    public void onShortNameEmpty() {
        shortNameInput.showEmptyTextError();
    }

    @Override
    public void onFullNameEmpty() {
        fullNameInput.showEmptyTextError();
    }

    @Override
    public void onCityEmpty() {
        cityInput.showEmptyTextError();
    }

    @Override
    public void onStreetEmpty() {
        streetInput.showEmptyTextError();
    }

    @Override
    public void onHouseEmpty() {
        houseInput.showEmptyTextError();
    }
}
