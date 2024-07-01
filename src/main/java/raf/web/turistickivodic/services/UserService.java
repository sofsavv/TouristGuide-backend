package raf.web.turistickivodic.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.commons.codec.digest.DigestUtils;
import raf.web.turistickivodic.entities.User;
import raf.web.turistickivodic.repositories.user.UserRepository;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

public class UserService {

    @Inject
    UserRepository userRepository;

    public String login(String email, String password)
    {

        String hashedPassword = DigestUtils.sha256Hex(password);
        User user = this.userRepository.findUser(email);

        if (user == null || !user.getHashedPassword().equals(hashedPassword)) {
            return null;
        }

        Date issuedAt = new Date();
        Date expiresAt = new Date(issuedAt.getTime() + 24*60*60*1000); // One day

        Algorithm algorithm = Algorithm.HMAC256("secret");

        // JWT-om mozete bezbedono poslati informacije na FE
        // Tako sto sve sto zelite da posaljete zapakujete u claims mapu
        return JWT.create()
                .withIssuedAt(issuedAt)
                .withExpiresAt(expiresAt)
                .withSubject(email)
                .withClaim("role", user.getRole())
                .sign(algorithm);
    }

    public boolean isAuthorized(String token, String path){

        Algorithm algorithm = Algorithm.HMAC256("secret");
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT jwt = verifier.verify(token);

        String email = jwt.getSubject();
        String role = jwt.getClaim("role").asString();
        User user = this.userRepository.findUser(email);

        if (!user.isActive()){
            return false;
        }else if (role.equalsIgnoreCase("editor") && path.startsWith("/users")) {
            return false;
        }

        return true;
    }

    public User addUser(User user) {
        return this.userRepository.addUser(user);
    }

    public List<User> allUsers() {
        return this.userRepository.allUsers();
    }

    public User findUser(String email) {
        return this.userRepository.findUser(email);
    }

    public User updateUser(String email, User user){
        return this.userRepository.editUser(email, user);
    }

    public void deleteUser(String user) {
        this.userRepository.deleteUser(user);
    }

    public void changeStatus(String user){
        this.userRepository.changeStatus(user);
    }

}
