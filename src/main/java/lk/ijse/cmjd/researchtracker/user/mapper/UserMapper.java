package lk.ijse.cmjd.researchtracker.user.mapper;

import lk.ijse.cmjd.researchtracker.user.User;
import lk.ijse.cmjd.researchtracker.user.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public UserDto toDto(User user) {
        if (user == null) {
            return null;
        }
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getFullName(),
                user.getRole(),
                user.getCreatedAt()
        );
    }
}
