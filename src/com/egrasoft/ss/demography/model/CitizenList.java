package com.egrasoft.ss.demography.model;

import java.util.ArrayList;
import java.util.Collection;

public class CitizenList extends ArrayList<Citizen> {
    public CitizenList(Collection<? extends Citizen> c) {
        super(c);
    }
}
