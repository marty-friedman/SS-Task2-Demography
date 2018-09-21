package com.egrasoft.ss.demography.service;

import com.egrasoft.fxcommons.service.FileManagerService;
import com.egrasoft.ss.demography.model.CitizenList;

import java.io.File;
import java.io.IOException;

public class DemographyFileManagerService implements FileManagerService<CitizenList> {
    private DemographyFileManagerService() {
    }

    @Override
    public void save(CitizenList objects, File file) throws IOException {
        //todo
    }

    @Override
    public CitizenList read(File file) throws IOException {
        return null; //todo
    }

    public static DemographyFileManagerService getInstance() {
        return SingletonHelper.instance;
    }

    private static class SingletonHelper {
        private static final DemographyFileManagerService instance = new DemographyFileManagerService();
    }
}
