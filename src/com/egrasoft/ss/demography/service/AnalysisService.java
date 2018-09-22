package com.egrasoft.ss.demography.service;

import com.egrasoft.ss.demography.model.Citizen;
import com.egrasoft.ss.demography.model.CitizenList;

import java.util.Map;
import java.util.stream.Collectors;

public class AnalysisService {
    private AnalysisService() {
    }

    public Map<String, Long> analyzePeoplePerCity(CitizenList list) {
        return list.stream().collect(Collectors.groupingBy(Citizen::getCity, Collectors.counting()));
    }

    public Integer analyzeTotalCount(CitizenList list) {
        return list.size();
    }

    public static AnalysisService getInstance() {
        return SingletonHelper.instance;
    }

    private static class SingletonHelper {
        private static final AnalysisService instance = new AnalysisService();
    }
}
