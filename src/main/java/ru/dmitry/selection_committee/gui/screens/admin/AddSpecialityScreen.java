package ru.dmitry.selection_committee.gui.screens.admin;

import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import ru.dmitry.selection_committee.gui.ScreenNavigator;
import ru.dmitry.selection_committee.gui.screens.admin.presenters.AddSpecialityScreenPresenter;
import ru.dmitry.selection_committee.gui.screens.admin.presenters.AddSpecialityScreenView;
import ru.dmitry.selection_committee.gui.screens.base.CustomLayoutScreen;
import ru.dmitry.selection_committee.gui.views.InputView;
import ru.dmitry.selection_committee.gui.views.ListSelectView;
import ru.dmitry.selection_committee.resourse.R;

public class AddSpecialityScreen extends CustomLayoutScreen implements AddSpecialityScreenView {

    private final String URL = "add_speciality";

    private InputView name;

    private InputView codeInput;

    private ListSelectView pulpits;

    private AddSpecialityScreenPresenter specialityPresenter;

    public AddSpecialityScreen(ScreenNavigator screenNavigator) {
        super(screenNavigator, "add_speciality_screen");
        specialityPresenter = new AddSpecialityScreenPresenter(this, screenNavigator.getSpecialityService(), screenNavigator.getPulpitService());
        setSizeFull();
    }

    @Override
    protected void setupComponents(Object object) {

        Label header = new Label(R.Strings.SPECIALITY);
        header.addStyleName("v-headerlabel");
        addComponent(header, "header");

        name = new InputView(new TextField(), R.Strings.NAME_HINT);
        name.addInputStyle("v-textfield-register");
        name.setTextChangeListener(this::onNameChanged);
        addComponent(name, "short_name_field");

        codeInput = new InputView(new TextField(), R.Strings.CODE_HINT);
        codeInput.addInputStyle("v-textfield-register");
        codeInput.setTextChangeListener(this::onCodeChanged);
        addComponent(codeInput, "code_field");

        pulpits = new ListSelectView(R.Strings.PULPIT_LIST);
        pulpits.setData(screenNavigator.getPulpitService().getPulpitNames());
        pulpits.setOnItemSelectedListener(this::onPulpitSelected);
        addComponent(pulpits, "pulpits_field");

        Button addButton = new Button(R.Strings.ADD);
        addButton.addStyleName("v-button-add_department");
        addButton.addClickListener(this::onAddButtonClick);
        addComponent(addButton, "add_button");

    }

    private void onNameChanged(CharSequence name){
        specialityPresenter.setName(name.toString());
    }

    private void onCodeChanged(CharSequence code){
        specialityPresenter.setCode(code.toString());
    }

    private void onPulpitSelected(String name){
        specialityPresenter.setPulpit(screenNavigator.getPulpitService().findByFullName(name));
    }

    private void onAddButtonClick(Button.ClickEvent clickEvent){
        specialityPresenter.add();
    }

    @Override
    public String getUrl() {
        return URL;
    }

    @Override
    public void onCodeEmpty() {
        codeInput.showEmptyTextError();
    }

    @Override
    public void onSuccessAdded() {
        screenNavigator.goBack();
    }

    @Override
    public void onFailAdded() {
        Notification.show("Ошибка добавления специальности");
    }

    @Override
    public void onFullNameEmpty() {
        name.showEmptyTextError();
    }

    @Override
    public void onShortNameEmpty() {

    }

    @Override
    public void onReferenceNotSelected() {
        Notification.show("Выберите кафедру из списка");
    }

}
