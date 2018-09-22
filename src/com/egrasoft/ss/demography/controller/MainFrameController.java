package com.egrasoft.ss.demography.controller;

import com.egrasoft.fxcommons.controller.FileWorkFrameController;
import com.egrasoft.fxcommons.service.FileManagerService;
import com.egrasoft.fxcommons.util.ControllerUtils;
import com.egrasoft.ss.demography.converter.DemographyIntegerStringConverter;
import com.egrasoft.ss.demography.converter.DemographyLocaleStringConverter;
import com.egrasoft.ss.demography.model.Citizen;
import com.egrasoft.ss.demography.model.CitizenList;
import com.egrasoft.ss.demography.service.DemographyFileManagerService;
import com.egrasoft.ss.demography.service.FrameService;
import com.egrasoft.ss.demography.service.LocalizationService;
import com.egrasoft.ss.demography.util.Constants;
import com.egrasoft.ss.demography.util.DemographyMessageHelper;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.DefaultStringConverter;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.function.Consumer;

import static com.egrasoft.fxcommons.util.ControllerUtils.createMessageDialog;

public class MainFrameController extends FileWorkFrameController<CitizenList> {
    private DemographyFileManagerService fileManagerService = DemographyFileManagerService.getInstance();
    private LocalizationService localizationService = LocalizationService.getInstance();
    private FrameService frameService = FrameService.getInstance();

    private Stage stage;

    private ObservableList<Citizen> items = FXCollections.observableArrayList();

    @FXML
    private TableView<Citizen> table;
    @FXML
    private TableColumn<Citizen, String> firstNameColumn;
    @FXML
    private TableColumn<Citizen, String> lastNameColumn;
    @FXML
    private TableColumn<Citizen, Integer> ageColumn;
    @FXML
    private TableColumn<Citizen, String> countryColumn;
    @FXML
    private TableColumn<Citizen, String> cityColumn;
    @FXML
    private TableColumn<Citizen, String> addressColumn;
    @FXML
    private TableColumn<Citizen, Locale> languageColumn;

    public MainFrameController(Stage stage) {
        super(stage, new DemographyMessageHelper());
        this.stage = stage;
    }

    @FXML
    @SuppressWarnings("unchecked")
    private void initialize() {
        items = FXCollections.observableArrayList();
        items.addListener((ListChangeListener<Citizen>) c -> { markChanged(true); updateTitle(); });
        table.setItems(items);
        ControllerUtils.markPresentRowsWithBorders(table, 1, "darkgrey");

        Consumer<TableColumn.CellEditEvent> listener = event -> { markChanged(true); updateTitle(); };
        StringConverter<String> stringStringConverter = new DefaultStringConverter();
        StringConverter<Integer> integerStringConverter = new DemographyIntegerStringConverter();
        StringConverter<Locale> localeStringConverter = new DemographyLocaleStringConverter();

        ControllerUtils.prepareTableColumn(firstNameColumn, Citizen::getFirstName, Citizen::setFirstName, listener, stringStringConverter);
        ControllerUtils.prepareTableColumn(lastNameColumn, Citizen::getLastName, Citizen::setLastName, listener, stringStringConverter);
        ControllerUtils.prepareTableColumn(countryColumn, Citizen::getCountry, Citizen::setCountry, listener, stringStringConverter);
        ControllerUtils.prepareTableColumn(cityColumn, Citizen::getCity, Citizen::setCity, listener, stringStringConverter);
        ControllerUtils.prepareTableColumn(addressColumn, Citizen::getAddress, Citizen::setAddress, listener, stringStringConverter);
        ControllerUtils.prepareTableColumn(ageColumn, Citizen::getAge, Citizen::setAge, listener, integerStringConverter);
        ControllerUtils.prepareTableColumn(languageColumn, Citizen::getLanguage, Citizen::setLanguage, listener, localeStringConverter);
    }

    @FXML
    private void doFileNew() {
        newFile();
    }

    @FXML
    private void doFileOpen() {
        openFile();
    }

    @FXML
    private void doFileSave() {
        save();
    }

    @FXML
    private void doFileSaveAs() {
        saveAs();
    }

    @FXML
    private void doFileClose() {
        closeFile();
    }

    @FXML
    private void doQuit() {
        closeWindow();
    }

    @FXML
    private void doAddEntry() {
        if (hasFile())
            items.add(new Citizen());
    }

    @FXML
    private void doDeleteEntry() {
        if (hasFile())
            items.removeAll(table.getSelectionModel().getSelectedItems());
    }

    @FXML
    private void doAbout() {
        createMessageDialog(Alert.AlertType.INFORMATION,
                localizationService.getString(Constants.Dialogs.ABOUT_TITLE_KEY),
                localizationService.getString(Constants.Dialogs.ABOUT_CONTENT_TEXT_KEY)).showAndWait();
    }

    @FXML
    private void doAnalysis() throws IOException {
        if (hasFile())
            frameService.initAnalysisFrame(getCurrentData()).show();
    }

    @Override
    protected FileManagerService<CitizenList> getFileManagerService() {
        return fileManagerService;
    }

    @Override
    protected CitizenList getCurrentData() {
        return new CitizenList(items);
    }

    @Override
    protected void onFileSaved() {
        updateTitle();
    }

    @Override
    protected void onFileOpened(CitizenList citizens) {
        items.setAll(citizens);
        markChanged(false);
        updateTitle();
    }

    @Override
    protected void onFileClosed() {
        items.clear();
        markChanged(false);
        updateTitle();
    }

    @Override
    protected void onFileNew() {
        items.clear();
        updateTitle();
    }

    private void updateTitle() {
        String title = localizationService.getString(Constants.Frame.MAIN_FRAME_TITLE_KEY);
        if (hasFile()) {
            File currentFile = getCurrentFile();
            String fileName = currentFile == null ?
                    localizationService.getString(Constants.Misc.ANONYMOUS_FILE_NAME_KEY) : currentFile.getName();
            title += " (" + fileName + ")";
            if (hasUnsavedChanges())
                title += " *";
        }
        stage.setTitle(title);
    }
}
