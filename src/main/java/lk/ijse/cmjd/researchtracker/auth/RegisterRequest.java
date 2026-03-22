package lk.ijse.cmjd.researchtracker.auth;

import lk.ijse.cmjd.researchtracker.user.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String fullName;
    private String username;
    private String password;
    private UserRole role;
}
