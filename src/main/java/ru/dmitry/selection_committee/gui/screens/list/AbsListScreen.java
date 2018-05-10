package ru.dmitry.selection_committee.gui.screens.list;

import com.vaadin.ui.Grid;
import ru.dmitry.selection_committee.gui.ScreenNavigator;
import ru.dmitry.selection_committee.gui.mvp.BasePresenter;
import ru.dmitry.selection_committee.gui.screens.admin.AdminMainPageScreen;
import ru.dmitry.selection_committee.gui.screens.base.CustomLayoutScreen;
import ru.dmitry.selection_committee.gui.screens.list.filters.FiltersView;
import ru.dmitry.selection_committee.gui.screens.list.mvp.ListScreenView;
import ru.dmitry.selection_committee.gui.screens.profile.EditEnrolleProfileScreen;
import ru.dmitry.selection_committee.gui.views.HeaderProfileView;
import ru.dmitry.selection_committee.server.models.Enrollee;
import ru.dmitry.selection_committee.server.models.Institution;

import java.util.List;

public abstract class AbsListScreen<Presenter extends BasePresenter, Filters extends FiltersView, Model> extends CustomLayoutScreen implements ListScreenView<Model> {

    protected ListScreenHeader listScreenHeader;

    protected Grid<Model> listGrid;

    protected Filters filtersListView;

    protected Presenter screenPresenter;

    public AbsListScreen(ScreenNavigator screenNavigator) {
        super(screenNavigator, "list_screen");
        setSizeFull();
        screenPresenter = getScreenPresenter();
        addComponents();
    }

    @Override
    protected void setupComponents(Object object) {

        listScreenHeader = new ListScreenHeader(screenNavigator.getAuthUser());
        listScreenHeader.setSizeFull();
        listScreenHeader.setHeaderActionProcessListener(new ListScreenHeader.OnHeaderActionProcessListener() {
            @Override
            public void onSearchQueryChange(String text) {
               onSearchQueryTextChanged(text);
            }

            @Override
            public void onSearchIconClick() {
                AbsListScreen.this.onSearchIconClick();
            }

            @Override
            public void onProfileClick() {

            }
        });
        listScreenHeader.setUserHeaderViewActionListener(new HeaderProfileView.HeaderActionListener() {
            @Override
            public void onGoToProfileAction() {
                EditEnrolleProfileScreen editEnrolleProfileScreen = new EditEnrolleProfileScreen(screenNavigator, (Enrollee)screenNavigator.getAuthUser());
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
        });
        addComponent(listScreenHeader, "header_layout");

        listGrid = new Grid<>();
        listGrid.setSizeFull();
        addGridColumn();
        addComponent(listGrid, "list_layout");

        filtersListView = getFiltersView();
        if (filtersListView != null){
            addComponent(filtersListView, "filter_layout");
        }

    }

    @Override
    public void onListReady(List<Model> list) {
        listGrid.setItems(list);
    }

    protected abstract void addGridColumn();

    protected abstract Filters getFiltersView();

    protected abstract Presenter getScreenPresenter();

    public abstract void onSearchQueryTextChanged(String text);

    public abstract void onSearchIconClick();

}
