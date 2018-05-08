package ru.dmitry.selection_committee.gui.views;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Window;

import java.util.List;

public class ListWindow extends Window {

    private ListSelectView listSelectView;

    private OnListItemSelectListener listItemSelectListener;

    public interface OnListItemSelectListener {
        void onListItemSelected(String name);
    }

    public ListWindow(String windowName, String listName){
        super(windowName);
        center();
        setup(listName);
    }

    private void setup(String listName){

        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setSizeFull();

        listSelectView = new ListSelectView(listName);
        listSelectView.setWidth("100%");
        listSelectView.setOnItemSelectedListener(name -> {
            if (listItemSelectListener != null){
                listItemSelectListener.onListItemSelected(name);
            }
            close();
        });
        horizontalLayout.addComponent(listSelectView);
        horizontalLayout.setComponentAlignment(listSelectView, Alignment.MIDDLE_CENTER);

        setContent(horizontalLayout);

        setHeight(400, Unit.PIXELS);
        setWidth(300, Unit.PIXELS);

    }

    public void addDataToList(List<String> data){
        listSelectView.setData(data);
    }

    public void setListItemSelectListener(OnListItemSelectListener listItemSelectListener) {
        this.listItemSelectListener = listItemSelectListener;
    }
}
