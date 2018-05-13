package ru.dmitry.selection_committee.gui.screens.list.filters;

import com.vaadin.data.HasValue;
import com.vaadin.shared.ui.datefield.DateResolution;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Label;
import ru.dmitry.selection_committee.utils.DateUtils;

import java.time.LocalDate;

public class DateFilterItem extends FilterItem {

    private Label title;

    private DateField filter;

    private int year = DateUtils.getCurrentYear();

    private OnFilterValueTextChangedListener filterValueTextChangedListener;

    public interface OnFilterValueTextChangedListener {
        void onFilterValueChanged(int year);
    }

    public DateFilterItem(String title) {
        super(title);
    }

    protected void setup(String name){
        title = new Label(name);
        filter = new DateField();
        filter.setResolution(DateResolution.YEAR);
        title.addStyleName("v-label-filter_title");
        filter.addStyleName("v-datefield");
        filter.setValue(LocalDate.now());
        filter.addValueChangeListener((HasValue.ValueChangeListener<LocalDate>) valueChangeEvent -> {
            if (valueChangeEvent != null && valueChangeEvent.getValue() != null) {
                year = valueChangeEvent.getValue().getYear();
                onFilterTextChanged(year);
            }
        });
        addComponents(title, filter);
    }

    protected void onFilterTextChanged(int year){
        if (filterValueTextChangedListener != null){
            filterValueTextChangedListener.onFilterValueChanged(year - 1);
        }
    }

    public int getYear() {
        return year;
    }

    public void clear(){
        filter.clear();
        year = -1;
    }

    public void setFilterValueTextChangedListener(OnFilterValueTextChangedListener filterValueTextChangedListener) {
        this.filterValueTextChangedListener = filterValueTextChangedListener;
    }

}
