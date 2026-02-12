package model;

import java.io.ByteArrayInputStream;
import java.util.UUID;

import javafx.beans.property.*;
import javafx.scene.image.Image;


public class Photo {

    private StringProperty name;

    private final ObjectProperty<Image> photoData;

    public Photo(String extension, byte[] photoData) {
        Image image = new Image(new ByteArrayInputStream(photoData));
        this.photoData =  new SimpleObjectProperty<>(image);
        String ex = UUID.randomUUID().toString() + "." + extension;
        this.name = new SimpleStringProperty(ex);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty NameProperty() {
        return name;
    }

    public Image getPhotoData() {
        return photoData.get();
    }

    public ObjectProperty<Image> PhotoDataProperty() {
        return photoData;
    }
}
