package am.buggle;

import am.buggle.enums.UserType;
import am.buggle.model.Car;
import am.buggle.model.User;
import am.buggle.repository.CarRepository;
import am.buggle.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.*;

@SpringBootApplication
public class BuggleApplication implements CommandLineRunner {

    public BuggleApplication(UserRepository userRepository, CarRepository carRepository) {
        this.userRepository = userRepository;
        this.carRepository = carRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(BuggleApplication.class, args);
    }

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final UserRepository userRepository;
    private final CarRepository carRepository;

    @Override
    public void run(String... args) throws Exception {


        if (userRepository.count() == 0) {
            Set<UserType> userTypes = new HashSet<>();
            userTypes.add(UserType.USER);
            userTypes.add(UserType.DRIVER);

            if(carRepository.count()==0){
                Car car = Car.builder()
                        .model("Mercedes")
                        .series("E")
                        .nymber("34DD789")
                        .seats(5)
                        .color("Black")
                        .year(2001)
                        .build();
                carRepository.save(car);

                ArrayList<Car> cars = new ArrayList<Car>(
                        Arrays.asList(car));

                User user = User.builder()
                        .phone("+37494066420")
                        .password(passwordEncoder.encode("admin"))
                        .regDate(new Date())
                        .name("Admin")
                        .cars(cars)
                        .userTypes(userTypes)
                        .build();
                userRepository.save(user);
            }
        }
    }
}
