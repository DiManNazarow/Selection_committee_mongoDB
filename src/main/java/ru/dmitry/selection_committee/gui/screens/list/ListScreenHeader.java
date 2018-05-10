package ru.dmitry.selection_committee.gui.screens.list;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import ru.dmitry.selection_committee.gui.views.HeaderProfileView;
import ru.dmitry.selection_committee.gui.views.HeaderSearchView;
import ru.dmitry.selection_committee.gui.views.HeaderView;
import ru.dmitry.selection_committee.gui.views.UserHeaderView;
import ru.dmitry.selection_committee.resourse.R;
import ru.dmitry.selection_committee.server.models.Admin;
import ru.dmitry.selection_committee.server.models.Enrollee;
import ru.dmitry.selection_committee.server.models.Role;
import ru.dmitry.selection_committee.server.models.User;

public class ListScreenHeader extends HeaderView<User> implements HeaderSearchView.OnSearchQueryChangeListener, HeaderSearchView.OnSearchIconClickListener {

    private HeaderSearchView headerSearchView;

    private HeaderProfileView headerProfileView;

    private OnHeaderActionProcessListener headerActionProcessListener;

    public interface OnHeaderActionProcessListener {
        void onSearchQueryChange(String text);
        void onSearchIconClick();
        void onProfileClick();
    }

    public ListScreenHeader(User user){
        super(user);
    }

    @Override
    protected Component[] getActionComponents() {
        headerSearchView = new HeaderSearchView(R.Strings.NAME_HINT);
        headerSearchView.setSearchIconClickListener(this);
        headerSearchView.setSearchQueryChangeListener(this);

        if (model.getRole() == Role.ENROLLE.getRoleCode()){
            headerProfileView = new HeaderProfileView((Enrollee)model);
        }
        if (model.getRole() == Role.ADMIN.getRoleCode()){
            headerProfileView = new HeaderProfileView((Admin)model);
        }
        return new Component[]{headerSearchView, headerProfileView};
    }

    protected void setComponentsAlign(){
        actionViewContainer.setComponentAlignment(headerSearchView, Alignment.MIDDLE_LEFT);
        actionViewContainer.setComponentAlignment(headerProfileView, Alignment.MIDDLE_CENTER);
    }

    @Override
    public void onSearchQueryChange(String text) {
        if (headerActionProcessListener != null){
            headerActionProcessListener.onSearchQueryChange(text);
        }
    }

    @Override
    public void onSearchIconClick() {
        if (headerActionProcessListener != null){
            headerActionProcessListener.onSearchIconClick();
        }
    }

    public String getSearchQuery(){
        return headerSearchView.getSearchQuery();
    }

    public void setHeaderActionProcessListener(OnHeaderActionProcessListener headerActionProcessListener) {
        this.headerActionProcessListener = headerActionProcessListener;
    }

    public void setUserHeaderViewActionListener(HeaderProfileView.HeaderActionListener headerActionListener){
        headerProfileView.setHeaderActionListener(headerActionListener);
    }

}
