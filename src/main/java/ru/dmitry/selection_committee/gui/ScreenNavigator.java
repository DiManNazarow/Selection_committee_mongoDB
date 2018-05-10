package ru.dmitry.selection_committee.gui;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.server.Page;
import com.vaadin.ui.Component;
import com.vaadin.ui.UI;
import ru.dmitry.selection_committee.gui.screens.auth.AuthorizationScreen;
import ru.dmitry.selection_committee.gui.screens.base.CustomLayoutScreen;
import ru.dmitry.selection_committee.gui.screens.base.Screen;
import ru.dmitry.selection_committee.server.models.User;
import ru.dmitry.selection_committee.server.services.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class ScreenNavigator {

    private UserServices userServices;

    private InstitutionService institutionService;

    private DepartmentService departmentService;

    private PulpitService pulpitService;

    private SpecialityService specialityService;

    private User authUser;

    public interface NavigationCallback {
        void onScreenOpenListener(String url);
        void onBackPressed();
    }

    private TreeMap<String, View> screens;

    private NavigationCallback navigationCallback;

    private Navigator vaadinNavigator;

    public ScreenNavigator(@NotNull NavigationCallback navigationCallback, @NotNull Navigator vaadinNavigator){
        this.navigationCallback = navigationCallback;
        this.vaadinNavigator = vaadinNavigator;
        screens = new TreeMap<>();
    }

    public void openScreen(String url, View screen) {
        throwNullNavigatorExceptionIfNecessary();
        if (!screens.containsKey(url)){
            screens.put(url, screen);
            vaadinNavigator.addView(url, screen);
        }
        navigationCallback.onScreenOpenListener(url);
    }
    public void goBack(){
        throwNullNavigatorExceptionIfNecessary();
        navigationCallback.onBackPressed();
    }

    public UserServices getUserServices() {
        return userServices;
    }

    public void setUserServices(UserServices userServices) {
        this.userServices = userServices;
    }

    public InstitutionService getInstitutionService() {
        return institutionService;
    }

    public void setInstitutionService(InstitutionService institutionService) {
        this.institutionService = institutionService;
    }

    public DepartmentService getDepartmentService() {
        return departmentService;
    }

    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    public PulpitService getPulpitService() {
        return pulpitService;
    }

    public void setPulpitService(PulpitService pulpitService) {
        this.pulpitService = pulpitService;
    }

    public SpecialityService getSpecialityService() {
        return specialityService;
    }

    public void setSpecialityService(SpecialityService specialityService) {
        this.specialityService = specialityService;
    }

    public void addScreen(String url, View screen){
        if (!screens.containsKey(url)){
            screens.put(url, screen);
            vaadinNavigator.addView(url, screen);
        }
    }

    public String getLastScreen(){
        if (!screens.isEmpty()){
            return screens.lastEntry().getKey();
        } else {
            return "/";
        }
    }

    public User getAuthUser() {
        return authUser;
    }

    public void setAuthUser(User authUser) {
        this.authUser = authUser;
    }

    public void signOut(){
        UI.getCurrent().close();
        authUser = null;
        Page.getCurrent().getJavaScript().execute("location.reload()");
    }

    private void throwNullNavigatorExceptionIfNecessary(){
        if (navigationCallback == null) throw new NullPointerException("ScreenNavigator can not be null");
    }

}
