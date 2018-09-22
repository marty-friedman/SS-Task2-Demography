package com.egrasoft.ss.demography.service;

import com.egrasoft.fxcommons.service.FileManagerService;
import com.egrasoft.ss.demography.model.CitizenList;

import java.io.*;

public class DemographyFileManagerService implements FileManagerService<CitizenList> {
    private DemographyFileManagerService() {
    }

    @Override
    public void save(CitizenList objects, File file) throws IOException {
        ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(file));
        stream.writeObject(objects);
        stream.flush();
        stream.close();
    }

    @Override
    public CitizenList read(File file) throws IOException {
        try(ObjectInputStream stream = new ObjectInputStream(new FileInputStream(file))) {
            return (CitizenList) stream.readObject();
        } catch (ClassNotFoundException e) {
            throw new IOException(e);
        }
    }

    public static DemographyFileManagerService getInstance() {
        return SingletonHelper.instance;
    }

    private static class SingletonHelper {
        private static final DemographyFileManagerService instance = new DemographyFileManagerService();
    }
}
