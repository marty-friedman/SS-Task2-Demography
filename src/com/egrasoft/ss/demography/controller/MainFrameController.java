package com.egrasoft.ss.demography.controller;

import com.egrasoft.fxcommons.controller.FileWorkFrameController;
import com.egrasoft.fxcommons.service.FileManagerService;
import com.egrasoft.fxcommons.util.StringHelper;
import com.egrasoft.ss.demography.model.CitizenList;
import com.egrasoft.ss.demography.service.DemographyFileManagerService;
import com.egrasoft.ss.demography.util.DemographyStringHelper;
import javafx.stage.Stage;

import java.util.function.Consumer;

public class MainFrameController extends FileWorkFrameController<CitizenList> {
    private StringHelper stringHelper = new DemographyStringHelper();
    private DemographyFileManagerService fileManagerService = DemographyFileManagerService.getInstance();

    public MainFrameController(Stage stage) {
        super(stage);
    }

    @Override
    protected FileManagerService<CitizenList> getFileManagerService() {
        return fileManagerService;
    }

    @Override
    protected StringHelper getStringHelper() {
        return stringHelper;
    }

    @Override
    protected CitizenList getCurrentData() {
        return null; //todo
    }

    @Override
    protected Consumer<CitizenList> getOpenFileHandler() {
        return null; //todo
    }

    @Override
    protected Runnable getCloseFileHandler() {
        return null; //todo
    }

    @Override
    protected Runnable getNewFileHandler() {
        return null; //todo
    }
}
