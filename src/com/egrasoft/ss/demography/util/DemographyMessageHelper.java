package com.egrasoft.ss.demography.util;

import com.egrasoft.fxcommons.util.MessageHelper;
import com.egrasoft.ss.demography.service.LocalizationService;

import static com.egrasoft.ss.demography.util.Constants.Dialogs.*;

public class DemographyMessageHelper implements MessageHelper {
    private LocalizationService localizationService = LocalizationService.getInstance();

    @Override
    public String getSaveFileDialogTitle() {
        return localizationService.getString(FILE_SAVE_TITLE_KEY);
    }

    @Override
    public String getSaveFileErrorDialogTitle() {
        return localizationService.getString(FILE_ERROR_TITLE_KEY);
    }

    @Override
    public String getSaveFileErrorDialogText() {
        return localizationService.getString(FILE_SAVE_ERROR_CONTENT_TEXT_KEY);
    }

    @Override
    public String getOpenFileDialogTitle() {
        return localizationService.getString(FILE_OPEN_TITLE_KEY);
    }

    @Override
    public String getOpenFileErrorDialogTitle() {
        return localizationService.getString(FILE_ERROR_TITLE_KEY);
    }

    @Override
    public String getOpenFileErrorDialogText() {
        return localizationService.getString(FILE_OPEN_ERROR_CONTENT_TEXT_KEY);
    }

    @Override
    public String getAskForSavingDialogTitle() {
        return localizationService.getString(ASK_FOR_SAVING_TITLE_KEY);
    }

    @Override
    public String getAskForSavingDialogText() {
        return localizationService.getString(ASK_FOR_SAVING_CONTENT_TEXT_KEY);
    }

    @Override
    public String getAskForSavingOptionYes() {
        return localizationService.getString(ASK_FOR_SAVING_SAVE_KEY);
    }

    @Override
    public String getAskForSavingOptionNo() {
        return localizationService.getString(ASK_FOR_SAVING_DONT_SAVE_KEY);
    }

    @Override
    public String getAskForSavingOptionCancel() {
        return localizationService.getString(ASK_FOR_SAVING_CANCEL_KEY);
    }

    @Override
    public String getDataProcessingErrorDialogTitle() {
        return null; //no error can occur
    }

    @Override
    public String getDataProcessingErrorDialogText(String... params) {
        return null; //no error can occur
    }
}
