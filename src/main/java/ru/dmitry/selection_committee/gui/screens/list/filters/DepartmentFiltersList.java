package ru.dmitry.selection_committee.gui.screens.list.filters;

import ru.dmitry.selection_committee.resourse.R;

public class DepartmentFiltersList extends FiltersView {

    private FilterItem institutionFilter;

    private OnDepartmentFilterStateListener departmentFilterStateListener;

    public interface OnDepartmentFilterStateListener {
        void onDepartmentFilterApply(String institution);
        void onDepartmentFilterClear();
    }

    @Override
    protected FilterItem[] getFilters() {

        institutionFilter = new FilterItem(R.Strings.INSTITUTION);

        return new FilterItem[]{institutionFilter};
    }

    @Override
    protected void applyFilters() {
        if (departmentFilterStateListener != null){
            departmentFilterStateListener.onDepartmentFilterApply(institutionFilter.getFilterText());
        }
    }

    @Override
    protected void clear() {
        if (departmentFilterStateListener != null){
            departmentFilterStateListener.onDepartmentFilterClear();
        }
    }

    public void setDepartmentFilterStateListener(OnDepartmentFilterStateListener departmentFilterStateListener) {
        this.departmentFilterStateListener = departmentFilterStateListener;
    }
}
