package model;

public enum State {

    IDLE("idle"),
    LEFT("left"),
    RIGHT("right"),
    UP("up"),
    DOWN("down"),
    UP_LEFT("up_left"),
    UP_RIGHT("up_right"),
    DOWN_LEFT("down_left"),
    DOWN_RIGHT("down_right");

    private String name;

    /**
     * Enum Constructor
     * @param name
     */
    State(String name) {
        this.name = name;
    }


    /**
     * toString method for enum
     * @return
     */
    @Override
    public String toString() {
        return name;
    }

}

