package models;

import enums.Heading;

public class HallCall extends ServiceCall {
    private final Heading heading;

    public HallCall(int originLevel, Heading heading) {
        super(originLevel);
        this.heading = heading;
    }

    public Heading getHeading() {
        return heading;
    }
}
