package model;

public enum Type {

    DIVER("scubaSam"),
    BOAT("boat"),
    BLUECATFISH("blueCatfish"),
    STRIPEDBASS("StripedBass"),
    CHAINPICKEREL("chainPickerel"),
    FLATHEADCATFISH("flatheadCatfish"),
    WHITEPERCH("whitePerch"),
    LARGEMOUTHBASS("largeMouthBass"),
    NORTHERNSNAKEHEAD("northernSnakehead");

    private String name;

    /**
     * Constructor for Type
     * @param name
     */
    Type(String name) {
        this.name = name;
    }


    /**
     * toString for Type enum
     * @return
     */
    @Override
    public String toString() {
        return name;
    }

}

