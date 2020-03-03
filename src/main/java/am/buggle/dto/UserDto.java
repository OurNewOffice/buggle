package am.buggle.dto;

import am.buggle.enums.UserType;
import am.buggle.model.Car;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private Long id;
    private String name;
    private String phone;
    private Set<UserType> userTypes;
    private List<Car> cars;
    private Date regDate;

}
