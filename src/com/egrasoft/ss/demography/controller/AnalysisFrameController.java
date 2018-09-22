package com.egrasoft.ss.demography.controller;

import com.egrasoft.fxcommons.util.ControllerUtils;
import com.egrasoft.ss.demography.model.CitizenList;
import com.egrasoft.ss.demography.service.AnalysisService;
import com.egrasoft.ss.demography.service.LocalizationService;
import com.egrasoft.ss.demography.util.Constants;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.converter.DefaultStringConverter;
import javafx.util.converter.LongStringConverter;

import java.util.Map;

public class AnalysisFrameController {
    private LocalizationService localizationService = LocalizationService.getInstance();
    private AnalysisService analysisService = AnalysisService.getInstance();

    private CitizenList citizens;
    private Stage stage;

    private ObservableList<Map.Entry<String, Long>> items = FXCollections.observableArrayList();

    @FXML
    private TableView<Map.Entry<String, Long>> table;
    @FXML
    private TableColumn<Map.Entry<String, Long>, String> cityColumn;
    @FXML
    private TableColumn<Map.Entry<String, Long>, Long> countColumn;
    @FXML
    private Label label;

    public AnalysisFrameController(Stage stage, CitizenList citizens) {
        this.stage = stage;
        this.citizens = citizens;
    }

    @FXML
    private void initialize() {
        ControllerUtils.prepareTableColumn(cityColumn, Map.Entry::getKey, null, null, new DefaultStringConverter());
        ControllerUtils.prepareTableColumn(countColumn, Map.Entry::getValue, null, null, new LongStringConverter());

        Map<String, Long> peoplePerCity = analysisService.analyzePeoplePerCity(citizens);
        Integer totalCount = analysisService.analyzeTotalCount(citizens);

        items.setAll(peoplePerCity.entrySet());
        table.setItems(items);
        label.setText(localizationService.getString(Constants.Frame.ANALYSIS_TOTAL_COUNT_TEXT_KEY) + " " + totalCount);
    }

    @FXML
    private void doOk() {
        stage.close();
    }
}
