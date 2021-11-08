package pl.jaczewski.GtN.game;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.jaczewski.GtN.custom_annotations.AvailableGuesses;

import javax.annotation.PostConstruct;

@Getter
@Component
public class GameImpl implements Game {

    @Getter(AccessLevel.NONE)
    private NumberGenerator numberGenerator;
    private int availableGuesses;
    private int randomNumberRolled;
    @Setter
    private int playerGuess;
    private int smallest;
    private int biggest;
    private int remainingGuesses;
    private boolean validNumberRange = true;

    @Autowired
    public GameImpl(NumberGenerator numberGenerator, @AvailableGuesses int availableGuesses) {
        this.numberGenerator = numberGenerator;
        this.availableGuesses = availableGuesses;
    }

    @PostConstruct
    @Override
    public void resetGame() {
        smallest = numberGenerator.getMinNumber();
        biggest = numberGenerator.getMaxNumber();
        playerGuess = Integer.MIN_VALUE;
        remainingGuesses = availableGuesses;
        randomNumberRolled = numberGenerator.next();
    }

    @Override
    public boolean isValidNumberRange() {
        return validNumberRange;
    }

    private void checkValidNumberRange() {
        validNumberRange = (playerGuess >= smallest) && (playerGuess <= biggest);
    }

    @Override
    public void check() {
        checkValidNumberRange();

        if (validNumberRange) {
            if (playerGuess > randomNumberRolled) {
                biggest = playerGuess - 1;
            }
            if (playerGuess < randomNumberRolled) {
                smallest = playerGuess + 1;
            }
        }
        remainingGuesses--;
    }

    @Override
    public boolean isGameWon() {
        return playerGuess == randomNumberRolled;
    }

    @Override
    public boolean isGameLost() {
        return !isGameWon() && remainingGuesses <= 0;
    }
}
