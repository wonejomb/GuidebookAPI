package de.wonejo.wuidebook.api.wgc.value;

public interface WgcNumericTagValue<T extends Number> extends WgcTagValue<T> {

    default byte getAsByte () { return this.getAsNumber().byteValue(); }
    default short getAsShort () { return this.getAsNumber().shortValue(); }
    default int getAsInt () { return this.getAsNumber().intValue(); }
    default float getAsFloat () { return this.getAsNumber().floatValue(); }
    default double getAsDouble () { return this.getAsNumber().doubleValue(); }

    Number getAsNumber ();

}
