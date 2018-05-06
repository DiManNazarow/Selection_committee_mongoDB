package ru.dmitry.selection_committee.gui.screens.list.filters;

import com.vaadin.ui.*;
import ru.dmitry.selection_committee.resourse.R;

import java.util.List;

public abstract class FiltersView extends VerticalLayout {

    private Button clearButton;

    private Label title;

    private Button applyButton;

    private FilterItem[] filters;

    public FiltersView(){
        setup();
        setMargin(false);
        setSpacing(false);
    }

    private void setup(){
        clearButton = new Button(R.Strings.CLEAR_FILTERS);
        clearButton.addStyleName("v-button-clear");
        clearButton.addClickListener((Button.ClickListener) clickEvent -> clearInternal());
        clearButton.setSizeFull();
        title = new Label(R.Strings.FILTERS);
        title.addStyleName("v-label-filter_list_title");
        applyButton = new Button(R.Strings.APPLY_FILTERS);
        applyButton.addStyleName("v-button-apply");
        applyButton.addClickListener((Button.ClickListener) clickEvent -> applyFilters());
        HorizontalLayout header = new HorizontalLayout();
        header.addStyleName("v-filter-header");
        header.setHeight(50, Unit.PIXELS);
        header.addComponents(title, clearButton);
        header.setComponentAlignment(title, Alignment.MIDDLE_LEFT);
        header.setComponentAlignment(clearButton, Alignment.MIDDLE_RIGHT);
        addComponent(header);
        filters = getFilters();
        addComponents(filters);
        addComponent(applyButton);
        setComponentAlignment(applyButton, Alignment.MIDDLE_CENTER);
    }

    private void clearInternal(){
        if (filters != null){
            for (FilterItem filter : filters) {
                filter.clear();
            }
        }
        clear();
    }

    protected abstract FilterItem[] getFilters();

    protected abstract void applyFilters();

    protected abstract void clear();

}