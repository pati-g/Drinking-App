package com.patrycjagalant.drinking.model.vessel;


import org.springframework.stereotype.Component;

@Component
public class VesselFactory {

    public Vessel createVessel(String type, double capacity) {
        if (type.equalsIgnoreCase("bottle")) {
            return new Bottle(capacity);
        } else if (type.equalsIgnoreCase("bowl")) {
            return new Bowl(capacity);
        }
        return null;
    }
}
