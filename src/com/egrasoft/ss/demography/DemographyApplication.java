package com.egrasoft.ss.demography;

import com.egrasoft.ss.demography.controller.MainFrameController;
import com.egrasoft.ss.demography.service.LocalizationService;
import com.egrasoft.ss.demography.util.Constants;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;

public class DemographyApplication extends Application {
    private LocalizationService localizationService = LocalizationService.getInstance();

    @Override
    public void start(Stage stage) throws Exception{
        URL frameView = getClass().getClassLoader().getResource(Constants.Location.MAIN_FRAME_VIEW_LOCATION);
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(frameView));
        loader.setController(new MainFrameController(stage));
        loader.setResources(localizationService.getCurrentBundle());
        Parent root = loader.load();
        stage.setTitle(localizationService.getString(Constants.Frame.FRAME_TITLE_KEY));
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
