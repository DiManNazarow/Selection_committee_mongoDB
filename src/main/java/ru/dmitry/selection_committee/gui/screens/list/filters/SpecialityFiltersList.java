package ru.dmitry.selection_committee.gui.screens.list.filters;

import ru.dmitry.selection_committee.resourse.R;

public class SpecialityFiltersList extends FiltersView {

    private FilterItem institutionFilter;

    private FilterItem departmentFilter;

    private FilterItem pulpitItem;

    private OnSpecialityFilterStateListener specialityFilterStateListener;

    public interface OnSpecialityFilterStateListener {
        void onSpecialityFilterApply(String institution, String department, String pulpit);
        void onSpecialityFilterClear();
    }

    @Override
    protected FilterItem[] getFilters() {
        institutionFilter = new FilterItem(R.Strings.INSTITUTION);
        departmentFilter = new FilterItem(R.Strings.DEPARTMENT);
        pulpitItem = new FilterItem(R.Strings.PULPIT);
        return new FilterItem[]{institutionFilter, departmentFilter, pulpitItem};
    }

    @Override
    protected void applyFilters() {
        if (specialityFilterStateListener != null){
            specialityFilterStateListener.onSpecialityFilterApply(institutionFilter.getFilterText(), departmentFilter.getFilterText(), pulpitItem.getFilterText());
        }
    }

    @Override
    protected void clear() {
        if (specialityFilterStateListener != null){
            specialityFilterStateListener.onSpecialityFilterClear();
        }
    }

    public void setSpecialityFilterStateListener(OnSpecialityFilterStateListener specialityFilterStateListener) {
        this.specialityFilterStateListener = specialityFilterStateListener;
    }
}
