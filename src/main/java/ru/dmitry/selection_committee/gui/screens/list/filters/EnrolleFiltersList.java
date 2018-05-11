package ru.dmitry.selection_committee.gui.screens.list.filters;

import ru.dmitry.selection_committee.gui.screens.profile.State;
import ru.dmitry.selection_committee.resourse.R;

public class EnrolleFiltersList extends FiltersView {

    private FilterItem institutionFilter;

    private FilterItem specialityFilter;

    private DateFilterItem yearOfEntered;

    private EnrolleFiltersStateListener enrolleFiltersStateListener;

    public interface EnrolleFiltersStateListener {
        void onEnrolleFiltersApply(String institution, String speciality, int year);
        void onEnrolleFilterClear();
    }

    public EnrolleFiltersList(State state){
        super(state);
    }

    @Override
    protected FilterItem[] getFilters(Object object) {
        State state = (State)object;
        institutionFilter = new FilterItem(R.Strings.INSTITUTION);
        specialityFilter = new FilterItem(R.Strings.SPECIALITY);
        yearOfEntered = new DateFilterItem(R.Strings.YEAR_OF_ENTERED);
        if (state.equals(State.ENROLLE_LIST_ADMIN)) {
            return new FilterItem[]{institutionFilter, specialityFilter, yearOfEntered};
        }
        return new FilterItem[]{institutionFilter, specialityFilter};
    }

    @Override
    protected void applyFilters() {
        if (enrolleFiltersStateListener != null){
            enrolleFiltersStateListener.onEnrolleFiltersApply(institutionFilter.getFilterText(),  specialityFilter.getFilterText(), yearOfEntered.getYear());
        }
    }

    @Override
    public void clear() {
        if (enrolleFiltersStateListener != null){
            enrolleFiltersStateListener.onEnrolleFilterClear();
        }
    }

    public void clearField(){
        institutionFilter.clear();
        specialityFilter.clear();
        yearOfEntered.clear();
    }

    public void setEnrolleFiltersStateListener(EnrolleFiltersStateListener enrolleFiltersStateListener) {
        this.enrolleFiltersStateListener = enrolleFiltersStateListener;
    }
}
