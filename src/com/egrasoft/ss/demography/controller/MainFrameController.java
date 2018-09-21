package com.egrasoft.ss.demography.controller;

import com.egrasoft.fxcommons.controller.FileWorkFrameController;
import com.egrasoft.fxcommons.service.FileManagerService;
import com.egrasoft.fxcommons.util.ControllerUtils;
import com.egrasoft.fxcommons.util.StringHelper;
import com.egrasoft.ss.demography.model.Citizen;
import com.egrasoft.ss.demography.model.CitizenList;
import com.egrasoft.ss.demography.service.DemographyFileManagerService;
import com.egrasoft.ss.demography.service.LocalizationService;
import com.egrasoft.ss.demography.util.Constants;
import com.egrasoft.ss.demography.util.DemographyIntegerStringConverter;
import com.egrasoft.ss.demography.util.DemographyLocaleStringConverter;
import com.egrasoft.ss.demography.util.DemographyStringHelper;
import javafx.beans.value.ChangeListener;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;

import static com.egrasoft.fxcommons.util.ControllerUtils.createMessageDialog;

public class MainFrameController extends FileWorkFrameController<CitizenList> {
    private DemographyFileManagerService fileManagerService = DemographyFileManagerService.getInstance();
    private LocalizationService localizationService = LocalizationService.getInstance();
    private StringHelper stringHelper = new DemographyStringHelper();

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
        super(stage);
        Runnable titleListener = () -> {
            String title = localizationService.getString(Constants.Frame.FRAME_TITLE_KEY);
            if (hasFile()) {
                title += " (" + (getCurrentFile() == null ?
                        localizationService.getString(Constants.Frame.ANONYMOUS_FILE_TITLE_KEY) :
                        getCurrentFile().getName()) + ")";
                if (hasUnsavedChanges())
                    title += " *";
            }
            stage.setTitle(title);
        };
        addCurrentFileEventHandler(titleListener);
        addHasUnsavedChangesEventHandler(titleListener);
        addHasFileEventHandler(titleListener);
    }

    @FXML
    @SuppressWarnings("unchecked")
    private void initialize() {
        items = FXCollections.observableArrayList();
        items.addListener((ListChangeListener<Citizen>) c -> markChanged(true));
        table.setItems(items);

        ChangeListener listener = (observable, oldValue, newValue) -> markChanged(true);
        StringConverter<String> stringStringConverter = new DefaultStringConverter();
        StringConverter<Integer> integerStringConverter = new DemographyIntegerStringConverter();
        StringConverter<Locale> localeStringConverter = new DemographyLocaleStringConverter();

        ControllerUtils.prepareTableColumn(firstNameColumn, Citizen::getFirstName, listener, stringStringConverter);
        ControllerUtils.prepareTableColumn(lastNameColumn, Citizen::getLastName, listener, stringStringConverter);
        ControllerUtils.prepareTableColumn(countryColumn, Citizen::getCountry, listener, stringStringConverter);
        ControllerUtils.prepareTableColumn(cityColumn, Citizen::getCity, listener, stringStringConverter);
        ControllerUtils.prepareTableColumn(addressColumn, Citizen::getAddress, listener, stringStringConverter);
        ControllerUtils.prepareTableColumn(ageColumn, Citizen::getAge, listener, integerStringConverter);
        ControllerUtils.prepareTableColumn(languageColumn, Citizen::getLanguage, listener, localeStringConverter);
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
        return new CitizenList(items);
    }

    @Override
    protected Consumer<CitizenList> getOpenFileHandler() {
        return this::updateItems;
    }

    @Override
    protected Runnable getCloseFileHandler() {
        return () -> updateItems(new ArrayList<>());
    }

    @Override
    protected Runnable getNewFileHandler() {
        return () -> updateItems(new ArrayList<>());
    }

    private void updateItems(List<Citizen> citizens) {
        items.clear();
        items.addAll(citizens);
    }
}
