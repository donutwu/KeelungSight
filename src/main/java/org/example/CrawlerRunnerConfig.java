package org.example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CrawlerRunnerConfig {

    @Autowired
    private KeelungSightsCrawler crawler;

    @Autowired
    private SightRepository sightRepository;

    @Bean
    public ApplicationRunner applicationRunner() {
        return args -> {

            String[] defaultZones = {"七堵", "中山", "中正","仁愛","安樂","信義","暖暖"};
            for (String zone : defaultZones) {
                Sight[] sights = crawler.getItems(zone);
                for (Sight sight : sights) {
                    sightRepository.save(sight);
                }
            }
            System.out.println("Default crawling and saving to MongoDB completed.");
        };
    }
}
