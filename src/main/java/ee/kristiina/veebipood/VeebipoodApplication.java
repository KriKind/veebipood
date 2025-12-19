package ee.kristiina.veebipood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableCaching
@EnableScheduling
@EnableJpaAuditing
public class VeebipoodApplication {

	public static void main(String[] args) {
		System.out.println("VeebipoodApplication");
        SpringApplication.run(VeebipoodApplication.class, args);
	}

}

// 1. Spring algus - controller, entity, andmebaas
// 2. Veateated, Order, Person
// 3. Rendipood algus
// 4. Rendipood jätk
// 5. Unit Testing, Front-end
// KOJU:
// a) Unit Testid lõpetada rendipoes
// b) Unit Teste proovida kaardimängus
// c) Frontend luua Rendipoes + tsükkel filmide mudeli järgi (copy-paste andmed)
// d) Frontend luua Kaardimängus + kaardi välja näitamine (copy-paste andmed)
// 6. 14.11 R 9.00 Front-end
// 7. 17.11 E 9.00 custom repository päringud localStore
// 8. 18.11 N 9.00 ostukorvi kogus, ostukorvi kogusumma Navbaris, ModelMapper,
// 9. 26.11 E 9.00 @Autowired, API päringud Supplier, pakiautomaadid
//10. 01.12 N 9.00 makse lõpetamine, securityConfig +JWT
//11. 03.12 E 9.00 auth reacti context
//12. 05.12 N 9.00 auth token frontendis, veateated tõlkes, profile, parooli hashimine, login, signup
//13. 08.12 E 9.00 auth. parooli muutmine. hook, ainult sinu tellimus, lisamisel token, muutmisel token
//14. 11.12 N 9.00 auth. rollid: Admin, Superadmin, staatusi muuta
//15. 15.12 E 9.00 järgmine nupp kinni, teade toodet lisades (õnnestub, ei õnnestu), guava cash   //404 refreshi järgselt, logid, emaili saatmine, cache
//16. 17.12 K 9.00 redis cach, email, CRON, shell-script, automaatselt DB tekitab lisamise/muutmise, logid
//17. 19.12 R 9.00 backend serverisse üles, frontend serverise üles, erinevad keskkonnad
// KOJU: lõpuprojekt teha
//18. 05.01 N 9.00-10.30 ---> lõpuprojekti esitlemine

// veahaldus
// pakiautomaadid ostukorvis
// admin vaates toote kustutamine ja muutmine

// ERR_CONNECTION_REFUSED -- ei tööta localhost:6379
// EMPTY_RESPONSE -- töötab 6379
// proovida Dockeris redis alla laadida

//docker run -d --name redis-stack-server -p 6379:6379 redis/redis-stack-server:latest


// LinkedIn
// MeetFrank
// ettevõtete kodukad