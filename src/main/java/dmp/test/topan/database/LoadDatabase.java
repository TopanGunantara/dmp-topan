package dmp.test.topan.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dmp.test.topan.entity.Users;
import dmp.test.topan.repository.UsersRepository;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(UsersRepository userRepository){
        return args -> {
            log.info("ENTRY : Initial RDMS using H2");

            log.info("Initial User : "+userRepository.save(new Users("username", "password")));
            
            log.info("EXIT : Initial RDMS using H2");
        };
    }
}
