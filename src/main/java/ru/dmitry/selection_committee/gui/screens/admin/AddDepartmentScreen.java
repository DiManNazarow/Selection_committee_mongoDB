package ru.dmitry.selection_committee.gui.screens.admin;

import com.vaadin.ui.*;
import ru.dmitry.selection_committee.gui.ScreenNavigator;
import ru.dmitry.selection_committee.gui.screens.admin.presenters.AddDepartmentScreenPresenter;
import ru.dmitry.selection_committee.gui.screens.admin.presenters.AddScreenView;
import ru.dmitry.selection_committee.gui.screens.base.CustomLayoutScreen;
import ru.dmitry.selection_committee.gui.views.InputView;
import ru.dmitry.selection_committee.gui.views.ListSelectView;
import ru.dmitry.selection_committee.resourse.R;
import ru.dmitry.selection_committee.server.models.Institution;

/**
 * Класс экрана добавления факультета
 */
public class AddDepartmentScreen extends CustomLayoutScreen implements AddScreenView {

    private final String URL = "add_department";

    private InputView shortNameInput;

    private InputView fullNameInput;

    private ListSelectView institution;

    private AddDepartmentScreenPresenter departmentScreenPresenter;

    public AddDepartmentScreen(ScreenNavigator screenNavigator) {
        super(screenNavigator, "add_department_screen");
        setSizeFull();
        departmentScreenPresenter = new AddDepartmentScreenPresenter(this, screenNavigator.getDepartmentService(), screenNavigator.getInstitutionService());
    }

    @Override
    protected void setupComponents(Object object) {

        Label header = new Label(R.Strings.DEPARTMENT);
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

        institution = new ListSelectView(R.Strings.UNIVERSITY_LIST);
        institution.setData(screenNavigator.getInstitutionService().getInstitutionsNames());
        institution.setOnItemSelectedListener(this::onInstitutionSelect);
        addComponent(institution, "institution_field");

        Button addButton = new Button(R.Strings.ADD);
        addButton.addStyleName("v-button-add_department");
        addButton.addClickListener(this::onAddButtonClick);
        addComponent(addButton, "add_button");

    }

    private void onShortNameChanged(CharSequence text){
        departmentScreenPresenter.setShortName(text.toString());
    }

    private void onLongNameChanged(CharSequence text){
        departmentScreenPresenter.setFullName(text.toString());
    }

    private void onInstitutionSelect(String name){
        Institution institution = screenNavigator.getInstitutionService().findByFullName(name);
        departmentScreenPresenter.setInstitution(institution);
    }

    private void onAddButtonClick(Button.ClickEvent clickEvent){
        departmentScreenPresenter.add();
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
        Notification.show("Ошибка добавления факультета");
    }

    @Override
    public void onShortNameEmpty() {
        shortNameInput.showEmptyTextError();
    }

    @Override
    public void onReferenceNotSelected() {
        Notification.show("Выберите учебное заведение из списка");
    }

    @Override
    public void onFullNameEmpty() {
        fullNameInput.showEmptyTextError();
    }

}
