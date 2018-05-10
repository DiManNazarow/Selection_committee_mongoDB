package ru.dmitry.selection_committee.gui.views;

import com.vaadin.ui.Component;
import ru.dmitry.selection_committee.server.models.Admin;
import ru.dmitry.selection_committee.server.models.Enrollee;
import ru.dmitry.selection_committee.server.models.Role;
import ru.dmitry.selection_committee.server.models.User;

public class UserHeaderView extends HeaderView<User> implements HeaderProfileView.HeaderActionListener {

    private HeaderProfileView headerProfileView;

    private UserHeaderView.HeaderActionListener headerActionListener;

    public interface HeaderActionListener {
        void onGoToProfileAction();
        void onGoToControlAction();
        void onSignOutAction();
    }

    public UserHeaderView(User user){
        super(user);
    }

    @Override
    protected Component[] getActionComponents() {
        if (model.getRole() == Role.ADMIN.getRoleCode()){
            headerProfileView = new HeaderProfileView((Admin)model);
        }
        if (model.getRole() == Role.ENROLLE.getRoleCode()) {
            headerProfileView = new HeaderProfileView((Enrollee) model);
        }
        headerProfileView.setHeaderActionListener(this);
        return new Component[]{headerProfileView};
    }

    @Override
    public void onGoToProfileAction() {
        if (headerActionListener != null){
            headerActionListener.onGoToProfileAction();
        }
    }

    @Override
    public void onGoToControlAction() {
        if (headerActionListener != null){
            headerActionListener.onGoToControlAction();
        }
    }

    @Override
    public void onSignOutAction() {
        if (headerActionListener != null){
            headerActionListener.onSignOutAction();
        }
    }

    public void setHeaderActionListener(HeaderActionListener headerActionListener) {
        this.headerActionListener = headerActionListener;
    }
}
