package ru.dmitry.selection_committee.gui.screens.list.filters;

import com.vaadin.data.HasValue;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class FilterItem extends VerticalLayout {

    private Label title;

    private TextField filter;

    private String filterText;

    private OnFilterValueTextChangedListener filterValueTextChangedListener;

    public interface OnFilterValueTextChangedListener {
        void onFilterValueChanged(String text);
    }

    public FilterItem(String title){
        setSpacing(false);
        setup(title);
    }

    protected void setup(String name){
        title = new Label(name);
        filter = new TextField();
        title.addStyleName("v-label-filter_title");
        filter.addStyleName("v-textfield-search_filter");
        filter.addValueChangeListener((HasValue.ValueChangeListener<String>) valueChangeEvent -> {
            filterText = valueChangeEvent.getValue();
            onFilterTextChanged(filterText);
        });
        addComponents(title, filter);
    }

    protected void onFilterTextChanged(String text){
        if (filterValueTextChangedListener != null){
            filterValueTextChangedListener.onFilterValueChanged(text);
        }
    }

    public String getFilterText() {
        return filterText;
    }

    public void clear(){
        filter.clear();
        filterText = null;
    }

    public void setFilterValueTextChangedListener(OnFilterValueTextChangedListener filterValueTextChangedListener) {
        this.filterValueTextChangedListener = filterValueTextChangedListener;
    }
}
