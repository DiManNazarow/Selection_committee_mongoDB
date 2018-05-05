package ru.dmitry.selection_committee.gui.screens.list;

import com.vaadin.data.HasValue;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public abstract class FilterItem extends VerticalLayout {

    private Label title;

    private TextField filter;

    private String filterText;

    public FilterItem(String title){
        this.title = new Label(title);
        this.filter = new TextField();
        setSpacing(false);
    }

    protected void setup(){
        title.addStyleName("v-label-filter_title");
        filter.addStyleName("v-textfield-search_filter");
        filter.addValueChangeListener((HasValue.ValueChangeListener<String>) valueChangeEvent -> {
            filterText = valueChangeEvent.getValue();
            onFilterTextChanged(filterText);
        });
    }

    protected abstract void onFilterTextChanged(String text);

    public String getFilterText() {
        return filterText;
    }
}
