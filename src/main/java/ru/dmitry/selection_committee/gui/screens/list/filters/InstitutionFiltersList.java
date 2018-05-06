package ru.dmitry.selection_committee.gui.screens.list.filters;

public class InstitutionFiltersList extends FiltersView {

    private CityFilter cityFilter;

    private OnInstitutionFilterStateListener institutionFilterStateListener;

    public interface OnInstitutionFilterStateListener {
        void onInstitutionFilterApply(String city);
        void onInstitutionFilterClear();
    }

    @Override
    protected FilterItem[] getFilters() {

        cityFilter = new CityFilter();
        cityFilter.setCityFilterTextChangedListener(text -> {

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
    protected void clear() {
        if (institutionFilterStateListener != null) {
            institutionFilterStateListener.onInstitutionFilterClear();
        }
    }

    public void setInstitutionFilterStateListener(OnInstitutionFilterStateListener institutionFilterStateListener) {
        this.institutionFilterStateListener = institutionFilterStateListener;
    }
}
