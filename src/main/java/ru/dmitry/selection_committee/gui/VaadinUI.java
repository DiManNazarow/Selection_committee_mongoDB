package ru.dmitry.selection_committee.gui;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.*;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;
import ru.dmitry.selection_committee.gui.screens.auth.AuthorizationScreen;
import ru.dmitry.selection_committee.server.services.*;

@SpringUI
@Theme("projecttheme")
@PreserveOnRefresh
public class VaadinUI extends UI implements ScreenNavigator.NavigationCallback {

    private ScreenNavigator screenNavigator;

    private Navigator vaadinNavigator;

    private UserServices userServices;

    private InstitutionService institutionService;

    private DepartmentService departmentService;

    private PulpitService pulpitService;

    private SpecialityService specialityService;

    public VaadinUI(UserServices userServices, InstitutionService institutionService, DepartmentService departmentService, PulpitService pulpitService, SpecialityService specialityService){
        super();
        this.userServices = userServices;
        this.institutionService = institutionService;
        this.departmentService = departmentService;
        this.pulpitService = pulpitService;
        this.specialityService = specialityService;
        vaadinNavigator = new Navigator(this, this);
        screenNavigator = new ScreenNavigator(this, vaadinNavigator);
        setupScreenNavigator();
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        vaadinNavigator.navigateTo(screenNavigator.getLastScreen());
    }

    @Override
    protected void refresh(VaadinRequest request){
        vaadinNavigator.navigateTo(screenNavigator.getLastScreen());
    }

    @Override
    public void onScreenOpenListener(String url) {
        vaadinNavigator.navigateTo(url);
    }

    @Override
    public void onBackPressed() {
        Page.getCurrent().getJavaScript().execute("history.back()");
    }

    private void setupScreenNavigator(){
        screenNavigator.setUserServices(userServices);
        screenNavigator.setInstitutionService(institutionService);
        screenNavigator.setDepartmentService(departmentService);
        screenNavigator.setPulpitService(pulpitService);
        screenNavigator.setSpecialityService(specialityService);
        AuthorizationScreen authorizationScreen = new AuthorizationScreen(screenNavigator, userServices);
        screenNavigator.addScreen(authorizationScreen.getUrl(), authorizationScreen);
    }
}
