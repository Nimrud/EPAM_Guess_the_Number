package pl.jaczewski.GtN.messages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.jaczewski.GtN.game.Game;

@Component
public class MessageGeneratorImpl implements MessageGenerator {

    @Autowired
    private Game game;

    @Override
    public String getMainMessage() {
        return "Number is in range from " + game.getSmallest() + " to " + game.getBiggest() + ".";
    }

    @Override
    public String getResultMessage() {
        if (game.isGameWon()) {
            return "You've guessed it! The number was " + game.getRandomNumberRolled() + ".";
        } else if (game.isGameLost()) {
            return "You have lost! The number was " + game.getRandomNumberRolled() + ".";
        } else if (!game.isValidNumberRange()){
            return "=> The number you've entered is outside the given range! " +
                    "It should be in range from " + game.getSmallest() + " to " +
                    game.getBiggest() + " (inclusive).";
        } else if (game.getRemainingGuesses() == game.getAvailableGuesses()) {
            return "What is your first guess?";
        } else {
            String hint = "=> Lower!";
            if (game.getPlayerGuess() < game.getRandomNumberRolled()) {
                hint = "=> Higher!";
            }
            return hint + " You have " + game.getRemainingGuesses() + " guesses left.";
        }
    }
}
