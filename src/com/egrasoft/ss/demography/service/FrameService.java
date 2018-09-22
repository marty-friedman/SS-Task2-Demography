package com.egrasoft.ss.demography.service;

import com.egrasoft.ss.demography.controller.AnalysisFrameController;
import com.egrasoft.ss.demography.controller.MainFrameController;
import com.egrasoft.ss.demography.model.CitizenList;
import com.egrasoft.ss.demography.util.Constants;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class FrameService {
    private LocalizationService localizationService = LocalizationService.getInstance();

    private FrameService() {
    }

    public void initMainFrame(Stage stage) throws IOException {
        Parent root = loadView(Constants.Location.MAIN_FRAME_VIEW_LOCATION, new MainFrameController(stage));
        stage.setTitle(localizationService.getString(Constants.Frame.MAIN_FRAME_TITLE_KEY));
        stage.setScene(new Scene(root));
    }

    public Stage initAnalysisFrame(CitizenList citizens) throws IOException {
        Stage stage = new Stage();
        Parent root = loadView(Constants.Location.ANALYSIS_FRAME_VIEW_LOCATION, new AnalysisFrameController(stage, citizens));
        stage.setTitle(localizationService.getString(Constants.Frame.ANALYSIS_FRAME_TITLE_KEY));
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        return stage;
    }

    private Parent loadView(String location, Object controller) throws IOException {
        URL view = getClass().getClassLoader().getResource(location);
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(view));
        loader.setController(controller);
        loader.setResources(localizationService.getCurrentBundle());
        return loader.load();
    }

    public static FrameService getInstance() {
        return SingletonHelper.instance;
    }

    private static class SingletonHelper {
        private static final FrameService instance = new FrameService();
    }
}
