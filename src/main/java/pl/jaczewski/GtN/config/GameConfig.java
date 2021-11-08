package pl.jaczewski.GtN.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import pl.jaczewski.GtN.custom_annotations.AvailableGuesses;
import pl.jaczewski.GtN.custom_annotations.MaxNumber;
import pl.jaczewski.GtN.custom_annotations.MinNumber;

@Configuration
@PropertySource("classpath:game.properties")
@ComponentScan(basePackages = "pl.jaczewski")
public class GameConfig {

    @Value("${minNumber:1}")
    private int minNumber;

    @Value("${maxNumber:100}")
    private int maxNumber;

    @Value("${availableGuesses:20}")
    private int availableGuesses;

    @Bean
    @MinNumber
    public int minNumber() {
        return minNumber;
    }

    @Bean
    @MaxNumber
    public int maxNumber() {
        return maxNumber;
    }

    @Bean
    @AvailableGuesses
    public int AvailableGuesses() {
        return availableGuesses;
    }
}
