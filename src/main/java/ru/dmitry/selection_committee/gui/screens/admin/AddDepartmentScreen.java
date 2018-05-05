package ru.dmitry.selection_committee.gui.screens.admin;

import com.vaadin.data.HasValue;
import com.vaadin.ui.*;
import ru.dmitry.selection_committee.gui.ScreenNavigator;
import ru.dmitry.selection_committee.gui.screens.admin.presenters.AddDepartmentScreenPresenter;
import ru.dmitry.selection_committee.gui.screens.admin.presenters.AddDepartmentScreenView;
import ru.dmitry.selection_committee.gui.screens.base.CustomLayoutScreen;
import ru.dmitry.selection_committee.gui.views.InputView;
import ru.dmitry.selection_committee.resourse.R;
import ru.dmitry.selection_committee.server.models.Institution;

import java.util.Set;

public class AddDepartmentScreen extends CustomLayoutScreen implements AddDepartmentScreenView {

    private final String URL = "add_department";

    private InputView shortNameInput;

    private InputView fullNameInput;

    private ListSelect<String> institution;

    private AddDepartmentScreenPresenter departmentScreenPresenter;

    public AddDepartmentScreen(ScreenNavigator screenNavigator) {
        super(screenNavigator, "add_department_screen");
        setSizeFull();
        departmentScreenPresenter = new AddDepartmentScreenPresenter(this, screenNavigator.getDepartmentService());
    }

    @Override
    protected void addComponents() {

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

        institution = new ListSelect<>(R.Strings.UNIVERSITY_LIST);
        institution.setItems(screenNavigator.getInstitutionService().getInstitutionsNames());
        institution.setRows(5);
        institution.addValueChangeListener(this::onInstitutionSelect);
        addComponent(institution, "institution_field");

        Button addButton = new Button(R.Strings.ADD);
        addButton.addStyleName("v-button-add");
        addButton.addClickListener(this::onAddButtonClick);
        addComponent(addButton, "add_button");

    }

    private void onShortNameChanged(CharSequence text){
        departmentScreenPresenter.setShortName(text.toString());
    }

    private void onLongNameChanged(CharSequence text){
        departmentScreenPresenter.setFullName(text.toString());
    }

    private void onInstitutionSelect(HasValue.ValueChangeEvent<Set<String>> valueChangeEvent){
        Set<String> selected = valueChangeEvent.getValue();
        Institution institution = screenNavigator.getInstitutionService().findByFullName(selected.stream().findFirst().get());
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
    public void onFullNameEmpty() {
        fullNameInput.showEmptyTextError();
    }

    @Override
    public void onInstitutionNotSelected() {
        Notification.show("Выберите учебное заведение из списка");
    }
}
