package ru.dmitry.selection_committee.gui.screens.list.filters;

public class CityFilter extends FilterItem {

    private static final String NAME = "Город";

    private OnCityFilterTextChangedListener cityFilterTextChangedListener;

    public interface OnCityFilterTextChangedListener {
        void onCityFilterChanged(String text);
    }

    public CityFilter() {
        super(NAME);
    }

    @Override
    protected void onFilterTextChanged(String text) {
        if (cityFilterTextChangedListener != null){
            cityFilterTextChangedListener.onCityFilterChanged(text);
        }
    }

    public void setCityFilterTextChangedListener(OnCityFilterTextChangedListener cityFilterTextChangedListener) {
        this.cityFilterTextChangedListener = cityFilterTextChangedListener;
    }
}
