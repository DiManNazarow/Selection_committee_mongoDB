package ru.dmitry.selection_committee.gui.screens.admin;

import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import ru.dmitry.selection_committee.gui.ScreenNavigator;
import ru.dmitry.selection_committee.gui.screens.base.CustomLayoutScreen;
import ru.dmitry.selection_committee.gui.screens.list.*;
import ru.dmitry.selection_committee.gui.screens.profile.EditEnrolleProfileScreen;
import ru.dmitry.selection_committee.gui.screens.profile.State;
import ru.dmitry.selection_committee.gui.screens.registration.RegistrationScreen;
import ru.dmitry.selection_committee.gui.views.ImageButton;
import ru.dmitry.selection_committee.gui.views.UserHeaderView;
import ru.dmitry.selection_committee.resourse.R;
import ru.dmitry.selection_committee.server.models.Enrollee;

public class AdminMainPageScreen extends CustomLayoutScreen implements UserHeaderView.HeaderActionListener {

    private final String URL = "admin_main_page";

    public AdminMainPageScreen(ScreenNavigator screenNavigator) {
        super(screenNavigator, "admin_main_page");
        setSizeFull();
    }

    @Override
    protected void setupComponents(Object object) {

        UserHeaderView userHeaderView = new UserHeaderView(screenNavigator.getAuthUser());
        userHeaderView.setSizeFull();
        userHeaderView.setHeaderActionListener(this);
        addComponent(userHeaderView, "admin_header");

        Button addUniversityButton = new Button(R.Strings.ADD_NEW_UNIVERSITY);
        addUniversityButton.addStyleName("v-button-add");
        addUniversityButton.addClickListener(this::onAddUniversityButtonClick);
        addComponent(addUniversityButton, "add_university");

        Button addDepartmentButton = new Button(R.Strings.ADD_NEW_DEPARTMENT);
        addDepartmentButton.addStyleName("v-button-add");
        addDepartmentButton.addClickListener(this::onAddDepartmentButtonClick);
        addComponent(addDepartmentButton, "add_department");

        Button addPulpitButton = new Button(R.Strings.ADD_NEW_PULPIT);
        addPulpitButton.addStyleName("v-button-add");
        addPulpitButton.addClickListener(this::onAddPulpitButtonClick);
        addComponent(addPulpitButton, "add_pulpit");

        Button addSpecialityButton = new Button(R.Strings.ADD_NEW_SPECIALITY);
        addSpecialityButton.addStyleName("v-button-add");
        addSpecialityButton.addClickListener(this::onAddSpecialityButtonClick);
        addComponent(addSpecialityButton, "add_speciality");

        Button addEnrolleButton = new Button(R.Strings.ADD_NEW_ENTOLLE);
        addEnrolleButton.addStyleName("v-button-add");
        addEnrolleButton.addClickListener(this::onAddEnrolleButtonClick);
        addComponent(addEnrolleButton, "add_enrolle");

        ImageButton enrolleListButton = new ImageButton(R.Strings.ENROLLE_LIST, new ThemeResource("img/ic_list_white_48px.svg"));
        enrolleListButton.setImageButtonClickListener(this::onListEnrolleClick);
        addComponent(enrolleListButton, "enrolle_list");

        ImageButton universityListButton = new ImageButton(R.Strings.UNIVERSITY_LIST, new ThemeResource("img/ic_list_white_48px.svg"));
        universityListButton.setImageButtonClickListener(this::onListInstitutionClick);
        addComponent(universityListButton, "university_list");

        ImageButton departmentListButton = new ImageButton(R.Strings.DEPARTMENT_LIST, new ThemeResource("img/ic_list_white_48px.svg"));
        departmentListButton.setImageButtonClickListener(this::onListDepartmentClick);
        addComponent(departmentListButton, "department_list");

        ImageButton pulpitListButton = new ImageButton(R.Strings.PULPIT_LIST, new ThemeResource("img/ic_list_white_48px.svg"));
        pulpitListButton.setImageButtonClickListener(this::onListPulpitClick);
        addComponent(pulpitListButton, "pulpit_list");

        ImageButton specialityListButton = new ImageButton(R.Strings.SPECIALITY_LIST, new ThemeResource("img/ic_list_white_48px.svg"));
        specialityListButton.setImageButtonClickListener(this::onListSpecialityClick);
        addComponent(specialityListButton, "speciality_list");

    }

    private void onAddUniversityButtonClick(Button.ClickEvent clickEvent){
        AddInstitutionScreen addInstitutionScreen = new AddInstitutionScreen(screenNavigator);
        screenNavigator.openScreen(addInstitutionScreen.getUrl(), addInstitutionScreen);
    }

    private void onAddDepartmentButtonClick(Button.ClickEvent clickEvent){
        AddDepartmentScreen addDepartmentScreen = new AddDepartmentScreen(screenNavigator);
        screenNavigator.openScreen(addDepartmentScreen.getUrl(), addDepartmentScreen);
    }

    private void onAddPulpitButtonClick(Button.ClickEvent clickEvent){
        AddPulpitScreen addPulpitScreen = new AddPulpitScreen(screenNavigator);
        screenNavigator.openScreen(addPulpitScreen.getUrl(), addPulpitScreen);
    }

    private void onAddSpecialityButtonClick(Button.ClickEvent clickEvent){
        AddSpecialityScreen addSpecialityScreen = new AddSpecialityScreen(screenNavigator);
        screenNavigator.openScreen(addSpecialityScreen.getUrl(), addSpecialityScreen);
    }

    private void onAddEnrolleButtonClick(Button.ClickEvent clickEvent){
        RegistrationScreen registrationScreen = new RegistrationScreen(screenNavigator, State.REGISTER_ADMIN);
        screenNavigator.openScreen(registrationScreen.getUrl(), registrationScreen);
    }

    private void onListInstitutionClick(){
        InstitutionListScreen institutionInstitutionListScreen = new InstitutionListScreen(screenNavigator);
        screenNavigator.openScreen(institutionInstitutionListScreen.getUrl(), institutionInstitutionListScreen);
    }

    private void onListDepartmentClick(){
        DepartmentListScreen departmentListScreen = new DepartmentListScreen(screenNavigator);
        screenNavigator.openScreen(departmentListScreen.getUrl(), departmentListScreen);
    }

    private void onListPulpitClick(){
        PulpitListScreen pulpitListScreen = new PulpitListScreen(screenNavigator);
        screenNavigator.openScreen(pulpitListScreen.getUrl(), pulpitListScreen);
    }

    private void onListSpecialityClick(){
        SpecialityListScreen specialityListScreen = new SpecialityListScreen(screenNavigator);
        screenNavigator.openScreen(specialityListScreen.getUrl(), specialityListScreen);
    }

    private void onListEnrolleClick(){
        EnrolleListScreen enrolleListScreen = new EnrolleListScreen(screenNavigator, State.ENROLLE_LIST_ADMIN);
        screenNavigator.openScreen(enrolleListScreen.getUrl(), enrolleListScreen);
    }

    @Override
    public String getUrl() {
        return URL;
    }

    @Override
    public void onGoToProfileAction() {
        EditEnrolleProfileScreen editEnrolleProfileScreen = new EditEnrolleProfileScreen(screenNavigator, State.EDIT_ADMIN, (Enrollee) screenNavigator.getAuthUser());
        screenNavigator.openScreen(editEnrolleProfileScreen.getUrl(), editEnrolleProfileScreen);
    }

    @Override
    public void onGoToControlAction() {
        AdminMainPageScreen adminMainPageScreen = new AdminMainPageScreen(screenNavigator);
        screenNavigator.openScreen(adminMainPageScreen.getUrl(), adminMainPageScreen);
    }

    @Override
    public void onSignOutAction() {
        screenNavigator.signOut();
    }
}
