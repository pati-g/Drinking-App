package com.patrycjagalant.drinking.model.vessel;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class VesselFactoryTest {
    private final VesselFactory vesselFactory = new VesselFactory();

    @Test
    void whenCreateVesselThenCorrectVesselChildIsReturned() {
        assertTrue(vesselFactory.createVessel("bottle", 5.0) instanceof Bottle);
        assertTrue(vesselFactory.createVessel("bowl", 2.0) instanceof Bowl);
    }
}
