package am.buggle;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Seda Ghazaryan
 * @author Ara Hovhannisyan
 * @class BuggleApplication
 */

@SpringBootApplication
public class BuggleApplication implements CommandLineRunner {

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        SpringApplication.run(BuggleApplication.class, args);
    }


    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * @param args String
     * @throws Exception exception
     */
    @Override
    public void run(String... args) throws Exception {
    }

}
