package pl.jaczewski.GtN.game;

public interface Game {
    void resetGame();
    int getRandomNumberRolled();
    int getPlayerGuess();
    void setPlayerGuess(int guess);
    int getAvailableGuesses();
    int getRemainingGuesses();
    int getSmallest();
    int getBiggest();
    boolean isValidNumberRange();
    void check();
    boolean isGameWon();
    boolean isGameLost();
}
