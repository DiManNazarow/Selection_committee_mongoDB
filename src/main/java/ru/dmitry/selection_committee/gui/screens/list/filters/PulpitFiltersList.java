package ru.dmitry.selection_committee.gui.screens.list.filters;

import ru.dmitry.selection_committee.resourse.R;

public class PulpitFiltersList extends FiltersView {

    private FilterItem institutionFilter;

    private FilterItem departmentFilter;

    private OnPulpitFilterStateListener pulpitFilterStateListener;

    public interface OnPulpitFilterStateListener {
        void onPulpitFilterApply(String institution, String department);
        void onPulpitFilterClear();
    }

    @Override
    protected FilterItem[] getFilters(Object object) {
        institutionFilter = new FilterItem(R.Strings.INSTITUTION);
        departmentFilter = new FilterItem(R.Strings.DEPARTMENT);
        return new FilterItem[]{institutionFilter, departmentFilter};
    }

    @Override
    protected void applyFilters() {
        if (pulpitFilterStateListener != null){
            pulpitFilterStateListener.onPulpitFilterApply(institutionFilter.getFilterText(), departmentFilter.getFilterText());
        }
    }

    @Override
    public void clear() {
        institutionFilter.clear();
        departmentFilter.clear();
        if (pulpitFilterStateListener != null){
            pulpitFilterStateListener.onPulpitFilterClear();
        }
    }

    public void setPulpitFilterStateListener(OnPulpitFilterStateListener pulpitFilterStateListener) {
        this.pulpitFilterStateListener = pulpitFilterStateListener;
    }
}
