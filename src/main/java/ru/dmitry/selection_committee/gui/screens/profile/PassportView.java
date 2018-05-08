package ru.dmitry.selection_committee.gui.screens.profile;

import com.vaadin.ui.*;
import ru.dmitry.selection_committee.gui.views.InputView;
import ru.dmitry.selection_committee.resourse.R;
import ru.dmitry.selection_committee.server.file.ImageUploader;

public class PassportView extends VerticalLayout {

    private InputView<TextField> pasportNumberInput;

    //private Upload mainPartPassportUploadView;

    //private Upload registrationPartPassportUploadView;

    //private Image image;

    private String number;

    private DocumentNumberChangeListener documentNumberChangeListener;

    public interface DocumentNumberChangeListener {
        void onDocumentNumberChanged(CharSequence text);
    }

    public PassportView(){
        setMargin(false);
        setSpacing(false);
        setWidth("90%");
        setup();
    }

    private void setup(){

        pasportNumberInput = new InputView<>(new TextField(), R.Strings.PASPORT_NUMBER);
        pasportNumberInput.addInputStyle("v-textfield-profile");
        pasportNumberInput.setTextChangeListener(this::onDocumentNumberChanged);

        //image = new Image(null);
        //ImageUploader imageUploader = new ImageUploader(image);
       // mainPartPassportUploadView = new Upload("Паспорт", imageUploader);
        //mainPartPassportUploadView.setButtonCaption("Загрузить");
        //mainPartPassportUploadView.addSucceededListener(imageUploader);

        addComponents(pasportNumberInput);
        setComponentAlignment(pasportNumberInput, Alignment.MIDDLE_CENTER);

    }

    private void onDocumentNumberChanged(CharSequence text){
        number = text.toString();
        if (documentNumberChangeListener != null){
            documentNumberChangeListener.onDocumentNumberChanged(text);
        }
    }

    public void setDocumentNumberChangeListener(DocumentNumberChangeListener documentNumberChangeListener) {
        this.documentNumberChangeListener = documentNumberChangeListener;
    }

    public String getNumber() {
        return number;
    }
}
