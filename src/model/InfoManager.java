package model;

import java.io.Serializable;

/*
contains information about the different types of fish
this information appears after the user collects a species for the first time
 */
public class InfoManager implements Serializable {
    //////////////////////////////////////////////////
    //ATTRIBUTES
    InfoCard card;
    boolean blueCatFish = false;
    boolean chainPickerel = false;
    boolean flatHeadCatFish = false;
    boolean largeMouthBass = false;
    boolean northernSnakeHead = false;
    boolean stripedBass = false;
    boolean whitePerch = false;

    //METHODS
    /**
     * Getter for card
     * @return
     */
    public InfoCard getCard() { return card; }
    //used to determine whether a fish has been discovered or not. if not it creates an infoCard with info for the view to display

    /**
     * Info for when a new fish is discovered
     * @param fish
     * @return
     */
    public boolean discover(Fish fish){
        switch (fish.getType()){
            case BLUECATFISH:
                if (blueCatFish==false){
                    card = new InfoCard("Blue Catfish","The Blue Catfish is an invasive species to the waters of Delaware. They typically grow to be " +
                            "25-46 inches in length and can weigh over 100 pounds! Blue catfish are also opportunistic predators and eat any species of fish they can catch," +
                            " along with crawfish, freshwater mussels, frogs, and other readily available aquatic food sources.",Type.BLUECATFISH);
                    blueCatFish = true;
                    return true;
                }
                return false;
            case CHAINPICKEREL:
                if (chainPickerel==false){
                    card = new InfoCard("Chain Pickerel","The Chain Pickerel is an invasive species to the waters of Delaware. " +
                            "It has a distinctive, dark, chain-like pattern on its greenish sides, hence the name, and can grow to be as long as 30 inches! " +
                            "The Chain Pickerel feeds primarily on smaller fish with its sharp teeth and are known to eat frogs, worms, crayfish, and a wide variety of other foods.",
                            Type.CHAINPICKEREL);
                    chainPickerel = true;
                    return true;
                }
                return false;
            case FLATHEADCATFISH:
                if (flatHeadCatFish==false){
                    card = new InfoCard("Flathead Catfish","The Flathead Catfish is an invasive species to the waters of Delaware. Like their larger cousin" +
                            " the Blue Catfish, they can grow to be quite large, measuring up to 46 inches in length and in extreme cases weighing over 100 pounds! " +
                            "Flathead Catfish have a diverse diet and are voracious carnivores, feeding primarily on other fish, insects, annelid worms, and crustaceans.",
                            Type.FLATHEADCATFISH);
                    flatHeadCatFish = true;
                    return true;
                }
                return false;
            case LARGEMOUTHBASS:
                if (largeMouthBass==false){
                    card = new InfoCard("Largemouth Bass","The Largemouth Bass is an invasive species to the waters of Delaware. As their name suggests they" +
                            " they are know for their big mouths. They are also olive green in colored, and marked by a series of dark blotches forming stripes along their sides." +
                            " They feed on fishes, crayfish, frogs, small birds, small mammals, amphibians, and reptiles.",Type.LARGEMOUTHBASS );
                    largeMouthBass = true;
                    return true;
                }
                return false;
            case NORTHERNSNAKEHEAD:
                if (northernSnakeHead==false){
                    card = new InfoCard("Northern Snakehead", "The Northern Snakehead is an invasive species originally from China and Russia." +
                            " They can be identified by their long dorsal and long anal fins, a rounded tail, and a large mouth that extends beyond the eye. " +
                            "They also have many, sharp teeth and are green or brown with darker, irregular shaped blotches along their sides",Type.NORTHERNSNAKEHEAD);
                    northernSnakeHead = true;
                    return true;
                }
                return false;
            case STRIPEDBASS:
                if (stripedBass==false){
                    card = new InfoCard("Striped Bass", "Striped Bass are a native species of Delaware and the entire East Coast of the United States. " +
                            "They can be greater than 44 inches in length and are anadromous, which means they spend the majority of their lives at sea and only " +
                            "enter freshwater in the spring to lay eggs.",Type.STRIPEDBASS);
                    stripedBass= true;
                    return true;
                }
                return false;
            case WHITEPERCH:
                if (whitePerch==false){
                    card = new InfoCard("White Perch", "The White Perch is a native species to the waters of Delaware. They can grow to be just shy of 20 inches " +
                            "long and can weigh several pounds as adults. They have an expansive diet that includes grass shrimp, razor clams, small minnows, fish eggs, and bloodworms." +
                            " They are also a prolific species meaning that they lay many eggs at once, up to 150,00 in a session! ",Type.WHITEPERCH);
                    whitePerch = true;
                    return true;
                }
                return false;
                default:
                    return false;
        }
    }
    //used to create a quizCard based on the native fish

    /**
     * Creates the questions for a randomly created quiz
     * @return
     */
    public QuizCard createQuiz(){
        String name = "";
        String question = "";
        Type type =Type.CHAINPICKEREL;
        switch ((int) (Math.random() * 6)) {
            case 0:
                name = "Largemouth Bass";
                question = "Are Largemouth Bass an invasive species to the Delaware water ways?";
                type =Type.LARGEMOUTHBASS;
                break;
            case 1:
                name = "Chain Pickerel";
                question = "The Chain pickerel is an invasive species to Delaware. It eats small fish and what else?";
                type=Type.CHAINPICKEREL;
                break;
            case 2:
                name = "Blue Catfish";
                question = "Blue Catfish are originally from the Mississippi River Caribbean Sea. Are they native to Delaware?";
                type = Type.BLUECATFISH;
                break;
            case 3:
                name = "Flathead Catfish";
                question = "Flathead Catfish are an invasive fish species and primarily feed on what?";
                type = Type.FLATHEADCATFISH;
                break;
            case 4:
                name = "Northern Snakehead";
                question = "The Northern Snakehead is a fish orignially from Russia and China. Is it native to Delaware?";
                type = Type.NORTHERNSNAKEHEAD;
                break;
            case 5:
                name = "Striped Bass";
                question = "Striped Bass are anadromous fish, which means they ...";
                type= Type.STRIPEDBASS;
                break;
            case 6:
                name = "White Perch";
                question = "White Perch fish are a native fish that can lay up to how many eggs at once?";
                type = Type.WHITEPERCH;
                break;
        }
        System.out.println(question);
        card = new QuizCard(name,question,type);
        return (QuizCard) card;
    }

}

