package ru.dmitry.selection_committee.gui.screens.list;

import com.vaadin.ui.Component;
import ru.dmitry.selection_committee.gui.views.HeaderSearchView;
import ru.dmitry.selection_committee.gui.views.HeaderView;
import ru.dmitry.selection_committee.resourse.R;

public class ListScreenHeader extends HeaderView implements HeaderSearchView.OnSearchQueryChangeListener, HeaderSearchView.OnSearchIconClickListener {

    private HeaderSearchView headerSearchView;

    private OnHeaderActionProcessListener headerActionProcessListener;

    public interface OnHeaderActionProcessListener {
        void onSearchQueryChange(String text);
        void onSearchIconClick();
        void onProfileClick();
    }

    @Override
    protected Component[] getActionComponents() {
        headerSearchView = new HeaderSearchView(R.Strings.NAME_HINT);
        headerSearchView.setSearchIconClickListener(this);
        headerSearchView.setSearchQueryChangeListener(this);
        return new Component[]{headerSearchView};
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
}
