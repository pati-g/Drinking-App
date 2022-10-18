package com.patrycjagalant.drinking.model.vessel;

import lombok.Getter;

@Getter
public enum VesselType {
    BOTTLE, BOWL;

    @Override
    public String toString() {
        return this.name();
    }
}
