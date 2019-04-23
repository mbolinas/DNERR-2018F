package model;

import java.io.Serializable;

/*
stores information about the different species the user could encounter during gameplay
contains both in-game and reallife images
 */
public class InfoCard implements Serializable {
    /////////////////////////////////////////////////////////////////////////////
    //Attributes
    private String title;
    private String info;
    String actualImage;
    String inGameImage;

    /////////////////////////////////////////////////////////////////////////////
    //Constuctor

    /**
     * Constructor for InfoCard
     * @param title
     * @param info
     * @param type
     */
    public InfoCard(String title, String info, Type type){
        this.title = title;
        this.info = info;
        try{
        switch (type) {
            case WHITEPERCH:
                actualImage = ("images/whitePerch/whiteperch.png");
                inGameImage = "images/whitePerch/whiteperchSingle.png";
                break;
            case STRIPEDBASS:
                actualImage = ("images/stripedBass/striped_bass.png");
                inGameImage = ("images/stripedBass/stripedbassSingle.png");
                break;
            case NORTHERNSNAKEHEAD:
                actualImage = ("images/northernSnakehead/snakehead.png");
                inGameImage = ("images/northernSnakehead/northernsnakeheadSingle.png");
                break;
            case FLATHEADCATFISH:
                actualImage = ("images/flatheadCatfish/Flathead_Catfish.png");
                inGameImage = ("images/flatheadCatfish/flatheadcatfishSingle.png");
                break;
            case BLUECATFISH:
                actualImage = ("images/blueCatfish/blue_catfish.png");
                inGameImage = ("images/blueCatfish/bluecatfishSingle.png");
                break;
            case CHAINPICKEREL:
                actualImage = ("images/chainPickerel/chain_pickerell.png");
                inGameImage = ("images/chainPickerel/chainpickerelSingle.png");
                break;
            case LARGEMOUTHBASS:
                actualImage = ("images/largeMouthBass/Largemouth_Bass.png");
                inGameImage = ("images/largeMouthBass/largemouthbassSingle.png");
                break;
        }
        }catch(Exception ex){

        }
    }
    ////////////////////////////////////////////////////////////////////////////////
    //GETTERS

    /**
     * Getter for title
     * @return
     */
    public String getTitle() { return title; }

    /**
     * Getter for info
     * @return
     */
    public String getInfo() { return info; }

    /**
     * Getter for actualImage
     * @return
     */
    public String getActualImage() {
        return actualImage;
    }

    /**
     * Getter for inGameImage
     * @return
     */
    public String getInGameImage() {
        return inGameImage;
    }
}
