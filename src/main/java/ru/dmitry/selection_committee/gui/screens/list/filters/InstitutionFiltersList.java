package ru.dmitry.selection_committee.gui.screens.list.filters;

import ru.dmitry.selection_committee.resourse.R;

public class InstitutionFiltersList extends FiltersView {

    private FilterItem cityFilter;

    private OnInstitutionFilterStateListener institutionFilterStateListener;

    public interface OnInstitutionFilterStateListener {
        void onInstitutionFilterApply(String city);
        void onInstitutionFilterClear();
    }

    @Override
    protected FilterItem[] getFilters(Object object) {

        cityFilter = new FilterItem(R.Strings.CITY);
        cityFilter.setFilterValueTextChangedListener(text -> {

        });

        return new FilterItem[]{cityFilter};
    }

    @Override
    protected void applyFilters() {
        if (institutionFilterStateListener != null) {
            institutionFilterStateListener.onInstitutionFilterApply(cityFilter.getFilterText());
        }
    }

    @Override
    public void clear() {
        if (institutionFilterStateListener != null) {
            institutionFilterStateListener.onInstitutionFilterClear();
        }
    }

    public void setInstitutionFilterStateListener(OnInstitutionFilterStateListener institutionFilterStateListener) {
        this.institutionFilterStateListener = institutionFilterStateListener;
    }
}
