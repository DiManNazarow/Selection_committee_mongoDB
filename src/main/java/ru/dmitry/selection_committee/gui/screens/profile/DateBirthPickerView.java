package ru.dmitry.selection_committee.gui.screens.profile;

import com.vaadin.data.HasValue;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import org.vaadin.inputmask.InputMask;
import org.vaadin.inputmask.client.Alias;
import ru.dmitry.selection_committee.resourse.R;
import ru.dmitry.selection_committee.utils.AppTextUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateBirthPickerView extends VerticalLayout {

    private final String DATE_FORMAT = "dd.MM.yyyy";

    private final String PLACEHOLDER_DATE_FORMAT = "день.месяц.год";

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);

    private Label title;

    private DateField dateField;

    private String date;

    private OnDateChangeListener dateChangeListener;

    public interface OnDateChangeListener {
        void onDateChange(String date);
    }

    public DateBirthPickerView(){
        setMargin(false);
        setWidth("80%");
        setup();
    }

    private void setup(){
        title = new Label(R.Strings.DATE_BIRTH_TITLE);
        title.addStyleName("v-personal-data-title");

        dateField = new DateField();
        dateField.setDateFormat(DATE_FORMAT);

        InputMask dateInputMask = new InputMask(Alias.DATE);
        dateInputMask.setPlaceholder("__/__/____");
        dateInputMask.extend(dateField);

        //dateField.setPlaceholder(PLACEHOLDER_DATE_FORMAT);
        dateField.setValue(LocalDate.now());
        date = LocalDate.now().format(formatter);

        dateField.addValueChangeListener((HasValue.ValueChangeListener<LocalDate>) valueChangeEvent -> {
            if (valueChangeEvent.getValue() != null){
                date = valueChangeEvent.getValue().format(formatter);
            }
            if (dateChangeListener != null){
                dateChangeListener.onDateChange(date);
            }
        });
        addComponents(title, dateField);
        setComponentAlignment(title, Alignment.TOP_CENTER);
        setComponentAlignment(dateField, Alignment.MIDDLE_CENTER);
    }

    public void setDateChangeListener(OnDateChangeListener dateChangeListener) {
        this.dateChangeListener = dateChangeListener;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date){
        if (!AppTextUtils.isTextEmpty(date)) {
            dateField.setValue(LocalDate.parse(date, formatter));
            this.date = LocalDate.parse(date, formatter).format(formatter);
        }
    }

}
