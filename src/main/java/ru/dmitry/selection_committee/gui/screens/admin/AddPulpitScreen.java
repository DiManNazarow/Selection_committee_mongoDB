package ru.dmitry.selection_committee.gui.screens.admin;

import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import ru.dmitry.selection_committee.gui.ScreenNavigator;
import ru.dmitry.selection_committee.gui.screens.admin.presenters.AddPulpitScreenPresenter;
import ru.dmitry.selection_committee.gui.screens.admin.presenters.AddScreenView;
import ru.dmitry.selection_committee.gui.screens.base.CustomLayoutScreen;
import ru.dmitry.selection_committee.gui.views.InputView;
import ru.dmitry.selection_committee.gui.views.ListSelectView;
import ru.dmitry.selection_committee.resourse.R;
import ru.dmitry.selection_committee.server.models.Department;

public class AddPulpitScreen extends CustomLayoutScreen implements AddScreenView {

    private final String URL = "add_pulpit";

    private InputView shortNameInput;

    private InputView fullNameInput;

    private ListSelectView department;

    private AddPulpitScreenPresenter pulpitScreenPresenter;

    public AddPulpitScreen(ScreenNavigator screenNavigator) {
        super(screenNavigator, "add_pulpit_screen");
        pulpitScreenPresenter = new AddPulpitScreenPresenter(this, screenNavigator.getPulpitService(), screenNavigator.getDepartmentService());
        setSizeFull();
    }

    @Override
    protected void setupComponents(Object object) {

        Label header = new Label(R.Strings.PULPIT);
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

        department = new ListSelectView(R.Strings.DEPARTMENT_LIST);
        department.setData(screenNavigator.getDepartmentService().getDepartmentNames());
        department.setOnItemSelectedListener(this::onInstitutionSelect);
        addComponent(department, "departments_field");

        Button addButton = new Button(R.Strings.ADD);
        addButton.addStyleName("v-button-add_department");
        addButton.addClickListener(this::onAddButtonClick);
        addComponent(addButton, "add_button");

    }

    private void onShortNameChanged(CharSequence text){
        pulpitScreenPresenter.setShortName(text.toString());
    }

    private void onLongNameChanged(CharSequence text){
        pulpitScreenPresenter.setFullName(text.toString());
    }

    private void onInstitutionSelect(CharSequence text){
        Department department = screenNavigator.getDepartmentService().findByFullName(text.toString());
        pulpitScreenPresenter.setDepartment(department);
    }

    private void onAddButtonClick(Button.ClickEvent clickEvent){
        pulpitScreenPresenter.add();
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
        Notification.show("Ошибка добавления кафедры");
    }

    @Override
    public void onFullNameEmpty() {
        fullNameInput.showEmptyTextError();
    }

    @Override
    public void onShortNameEmpty() {
        shortNameInput.showEmptyTextError();
    }

    @Override
    public void onReferenceNotSelected() {
        Notification.show("Выберите факультет из списка");
    }

}
