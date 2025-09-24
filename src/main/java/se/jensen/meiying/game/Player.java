package se.jensen.meiying.game;

public class Player {
    private String firstname;
    private String lastname;
    private int score;

    public Player() {
        this.score = 0;
    }

    public void setFirstname(String firstname) throws IllegalArgumentException {
        if (firstname == null || firstname.trim().isEmpty()) {
            throw new IllegalArgumentException("Firstname cannot be empty");
        }
        this.firstname = firstname;
    }

    public void setLastname(String lastname) throws IllegalArgumentException {
        if (lastname == null || lastname.trim().isEmpty()) {
            throw new IllegalArgumentException("Lastname cannot be empty");
        }
        this.lastname = lastname;
    }

    public void addToScore(int score) {
        this.score += score;
    }

    public int getScore() {
        return score;
    }

    public String getFullName() {
        return firstname.concat(" ").concat(lastname);
    }

    public void resetScore() {
        this.score = 0;
    }
}