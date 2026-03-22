package lk.ijse.cmjd.researchtracker.user;

import lk.ijse.cmjd.researchtracker.user.dto.UserDto;
import lk.ijse.cmjd.researchtracker.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserDto> listAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    public UserDto viewUserProfile(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return userMapper.toDto(user);
    }

    public void deleteUser(String id) {
        if (!userRepository.existsById(id)) {
            throw new UsernameNotFoundException("User not found");
        }
        userRepository.deleteById(id);
    }
}
