package model;

/*
builds upon InfoCard by providing a series of answers the user can choose between
possible answers differ based on which species is chosen
 */
public class QuizCard extends InfoCard {
    String answerOne;
    String answerTwo;
    int correctAnswerIndex;

    /**
     * Constructor for Quiz cards
     * @param title
     * @param question
     * @param type
     */
    public QuizCard(String title, String question, Type type) {
        super(title,question,type);
        switch (type) {
            case WHITEPERCH:
                this.answerOne = "They can lay only a few eggs at once.";
                this.answerTwo = "They can lay up to 150,000 eggs at once!";
                this.correctAnswerIndex = 2;
                break;
            case STRIPEDBASS:
                this.answerOne = "Lay their eggs in freshwater.";
                this.answerTwo = "Catch birds by jumping out of the water.";
                this.correctAnswerIndex = 1;
                break;
            case NORTHERNSNAKEHEAD:
                this.answerOne = "No, they are an invasive species!";
                this.answerTwo = "Yes, they are a native species.";
                this.correctAnswerIndex = 1;
                break;
            case FLATHEADCATFISH:
                this.answerOne = "They primarily eat grasses and plants.";
                this.answerTwo = "They primarily feed on native fish.";
                this.correctAnswerIndex = 2;
                break;
            case BLUECATFISH:
                this.answerOne = "Yes, they are native.";
                this.answerTwo = "No, they are an invasive species!";
                this.correctAnswerIndex = 2;
                break;
            case CHAINPICKEREL:
                this.answerOne = "Chain Pickerels only eat small fish.";
                this.answerTwo = "Chain Pickerels also eat frogs, worms, and crayfish.";
                this.correctAnswerIndex = 2;
                break;
            case LARGEMOUTHBASS:
                this.answerOne = "Yes, they are an invasive species and even eat native species!";
                this.answerTwo = "No, they are a native species.";
                this.correctAnswerIndex = 1;
                break;
        }
    }

    /**
     * Getter for correctAnswerIndex
     * @return
     */
    public int getCorrectAnswerIndex() { return correctAnswerIndex; }

    /**
     * Getter for answerOne
     * @return
     */
    public String getAnswerOne() { return answerOne; }

    /**
     * Getter for answerTwo
     * @return
     */
    public String getAnswerTwo() { return answerTwo; }
}