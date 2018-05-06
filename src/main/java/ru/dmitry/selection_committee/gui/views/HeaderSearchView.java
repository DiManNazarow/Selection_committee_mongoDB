package ru.dmitry.selection_committee.gui.views;

import com.vaadin.data.HasValue;
import com.vaadin.event.MouseEvents;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.TextField;
import ru.dmitry.selection_committee.gui.screens.base.CustomLayoutScreen;
import ru.dmitry.selection_committee.utils.AppTextUtils;

public class HeaderSearchView extends CustomLayout {

    private final String SEARCH_ICON_PATH = "img/ic_search_white_48px.svg";

    private TextField search;

    private Image image;

    private String searchQuery;

    private OnSearchQueryChangeListener searchQueryChangeListener;

    private OnSearchIconClickListener searchIconClickListener;

    public interface OnSearchQueryChangeListener {
        void onSearchQueryChange(String text);
    }

    public interface OnSearchIconClickListener {
        void onSearchIconClick();
    }

    public HeaderSearchView(String hint){
        super("header_search_view");
        setSizeFull();
        setup(hint);
    }

    private void setup(String hint){
        search = new TextField();
        search.setPlaceholder(hint);
        search.addStyleName("v-textfield-header_search");
        search.addValueChangeListener((HasValue.ValueChangeListener<String>) valueChangeEvent -> {
            searchQuery = valueChangeEvent.getValue();
//            if (searchQueryChangeListener != null){
//                searchQueryChangeListener.onSearchQueryChange(searchQuery);
//            }
        });
        image = new Image(null, new ThemeResource(SEARCH_ICON_PATH));
        image.setHeight(28, Unit.PIXELS);
        image.setWidth(60, Unit.PIXELS);
        image.addStyleName("v-image-background");
        image.addClickListener((MouseEvents.ClickListener) clickEvent -> {
            if (searchIconClickListener != null){
                searchIconClickListener.onSearchIconClick();
            }
        });
        addComponent(search, "search_field");
        addComponent(image, "search_icon");
    }

    public void setSearchQueryChangeListener(OnSearchQueryChangeListener searchQueryChangeListener) {
        this.searchQueryChangeListener = searchQueryChangeListener;
    }

    public void setSearchIconClickListener(OnSearchIconClickListener searchIconClickListener) {
        this.searchIconClickListener = searchIconClickListener;
    }

    public String getSearchQuery() {
        return searchQuery;
    }
}
