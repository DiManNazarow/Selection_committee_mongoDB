package ru.dmitry.selection_committee.gui.views;

import com.vaadin.data.HasValue;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.VerticalLayout;

import java.util.List;
import java.util.Set;

public class ListSelectView extends VerticalLayout {

    private final int DEFAULT_ROW_COUNT = 5;

    private Label label;

    private ListSelect<String> listSelect;

    private List<String> data;

    private int rowCount = DEFAULT_ROW_COUNT;

    private OnItemSelectedListener onItemSelectedListener;

    public interface OnItemSelectedListener {
        void onItemSelected(String name);
    }

    public ListSelectView(String caption, List<String> data){
        label = new Label(caption);
        label.addStyleName("v-label-list_select");
        listSelect = new ListSelect<>();
        this.data = data;
        setup();
    }

    public ListSelectView(String caption){
        this(caption, null);
    }

    private void setup(){
        listSelect.setRows(rowCount);
        listSelect.addValueChangeListener(this::onListItemSelected);
        if (data != null) {
            listSelect.setItems(data);
        }
        setMargin(false);
        addComponents(label, listSelect);
        setComponentAlignment(label, Alignment.TOP_CENTER);
        setComponentAlignment(listSelect, Alignment.MIDDLE_CENTER);
    }

    private void onListItemSelected(HasValue.ValueChangeEvent<Set<String>> valueChangeEvent){
        if (onItemSelectedListener != null){
            Set<String> selected = valueChangeEvent.getValue();
            if (selected != null && selected.stream().findFirst().isPresent()){
                onItemSelectedListener.onItemSelected(selected.stream().findFirst().get());
            }
        }
    }

    public void setData(List<String> data) {
        this.data = data;
        if (data != null) {
            listSelect.setItems(data);
        } else {
            listSelect.clear();
        }
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public void setOnItemSelectedListener(OnItemSelectedListener onItemSelectedListener) {
        this.onItemSelectedListener = onItemSelectedListener;
    }

    public void addLabelStyle(String name){
        label.addStyleName(name);
    }

    public void select(String... select){
        listSelect.select(select);
    }

}
