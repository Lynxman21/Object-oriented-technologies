package controller;


import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import model.Gallery;
import model.Photo;
import util.PhotoDownloader;

public class GalleryController {

    private Gallery galleryModel;

    @FXML
    private TextField imageNameField;

    @FXML
    private ImageView imageView;

    @FXML
    private ListView<Photo> imagesListView;

    @FXML
    private TextField searchTextField;

    @FXML
    public void initialize() {
        // TODO additional FX controls initialization
        imagesListView.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Photo item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    ImageView photoIcon = new ImageView(item.getPhotoData());
                    photoIcon.setPreserveRatio(true);
                    photoIcon.setFitHeight(50);
                    setGraphic(photoIcon);
                }
            }
        });

        ReadOnlyObjectProperty<Photo> selected = imagesListView.getSelectionModel().selectedItemProperty();
        selected.addListener((observable, oldValue, newValue) -> {
            if (oldValue != null) {
                imageNameField.textProperty().unbindBidirectional(oldValue.NameProperty());
                imageView.imageProperty().unbind();
            }
            if (newValue != null) {
                bindSelectedPhoto(newValue);
            }
            else {
                imageNameField.clear();
                imageView.setImage(null);
            }
        });
    }

    public void searchButtonClicked(ActionEvent event) {
        PhotoDownloader ph = new PhotoDownloader();

        galleryModel.clear();
        Observable<Photo> photos = ph.searchForPhotos(searchTextField.getText());
        photos.subscribeOn(Schedulers.io()).subscribe(
                photo -> {
                    Platform.runLater(() -> galleryModel.addPhoto(photo) );
                },
                error -> {
                    Platform.runLater(() -> {
                        error.printStackTrace();
                    });
                },
                () -> {
                    Platform.runLater(() -> System.out.println("Downloading finished"));
                }
        );
    }

    public void setModel(Gallery gallery) {
        this.galleryModel = gallery;
        imagesListView.setItems(gallery.getPhotos());
    }

    private void bindSelectedPhoto(Photo selectedPhoto) {
        // TODO view <-> model bindings configuration
        imageNameField.textProperty().bindBidirectional(selectedPhoto.NameProperty());
        imageView.imageProperty().bind(selectedPhoto.PhotoDataProperty());
    }
}

