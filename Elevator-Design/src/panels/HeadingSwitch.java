package panels;

import enums.Heading;

public class HeadingSwitch extends Switch {
    private final Heading heading;

    public HeadingSwitch(Heading heading) {
        this.heading = heading;
    }

    public Heading getHeading() {
        return heading;
    }
}
