public interface Extras {
    enum ACType { MANUAL, AUTOMATIC }
    enum Interior { FABRIC, LEATHER }
    enum RimType { STEEL, ALLOY }

    ACType getAcType();
    void setAcType(ACType acType);

    Interior getInterior();
    void setInterior(Interior interior);

    RimType getRimType();
    void setRimType(RimType rimType);

    double extrasPrice();
}
