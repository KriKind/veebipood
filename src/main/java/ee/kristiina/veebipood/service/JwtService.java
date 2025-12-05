package ee.kristiina.veebipood.service;

import ee.kristiina.veebipood.entity.Person;
import ee.kristiina.veebipood.repository.PersonRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;

@Service
public class JwtService {

    @Autowired
    private PersonRepository personRepository;

    String superSecretKey = "FHoa3mlwn8oIPplRi-DQR4_7kIlkB88ETOxKHGvv2qY";
    SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(superSecretKey));

    public String generateToken(Long personId) {
        return Jwts
                .builder()
                .id(personId.toString())
                .signWith(secretKey)
                .compact();
    }

    public Person getPersonByToken(String token) {
        Claims claims = Jwts
                .parser()
                .verifyWith(secretKey)
                .build()
                .parseClaimsJws(token)
                .getPayload();
        Long personId = Long.parseLong(claims.getId());
        return  personRepository.findById(personId).orElseThrow();
    }
}
