package ru.dmitry.selection_committee.gui.views;

import com.vaadin.event.MouseEvents;
import com.vaadin.server.Resource;
import com.vaadin.ui.*;

public class ImageButton extends VerticalLayout {

    private final int DEFAULT_IMAGE_SIZE_PX = 75;

    private Image image;

    private Button caption;

    private int imageSize = DEFAULT_IMAGE_SIZE_PX;

    private OnImageButtonClickListener imageButtonClickListener;

    public interface OnImageButtonClickListener {
        void onImageButtonClick();
    }

    public ImageButton(String caption, Resource image){
        this.image = new Image(null, image);
        this.caption = new Button(caption);
        setMargin(false);
        setSpacing(false);
        addComponents();
    }

    protected void addComponents(){
        image.setWidth(imageSize, Unit.PIXELS);
        image.setHeight(imageSize, Unit.PIXELS);
        addComponent(image);
        caption.addStyleName("v-button-image_button");
        addComponent(caption);
        image.addClickListener(this::onImageClick);
        caption.addClickListener(this::onButtonClick);
        setComponentAlignment(image, Alignment.MIDDLE_CENTER);
    }

    private void onButtonClick(Button.ClickEvent clickEvent){
        if (imageButtonClickListener != null){
            imageButtonClickListener.onImageButtonClick();
        }
    }

    private void onImageClick(MouseEvents.ClickEvent clickEvent){
        if (imageButtonClickListener != null){
            imageButtonClickListener.onImageButtonClick();
        }
    }

    public void setImageButtonClickListener(OnImageButtonClickListener imageButtonClickListener) {
        this.imageButtonClickListener = imageButtonClickListener;
    }

    public void setImageSize(int imageSize) {
        this.imageSize = imageSize;
    }
}
