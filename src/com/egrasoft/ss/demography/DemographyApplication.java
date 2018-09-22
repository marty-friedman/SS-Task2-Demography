package com.egrasoft.ss.demography;

import com.egrasoft.ss.demography.service.FrameService;
import javafx.application.Application;
import javafx.stage.Stage;

public class DemographyApplication extends Application {
    private FrameService frameService = FrameService.getInstance();

    @Override
    public void start(Stage stage) throws Exception{
        frameService.initMainFrame(stage);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
