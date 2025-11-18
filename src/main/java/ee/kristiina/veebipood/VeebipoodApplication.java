package ee.kristiina.veebipood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
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
//10. 28.11 N 9.00
//11. 01.12 E 9.00 auth
//12. 04.12 N 9.00 auth
//13. 08.12 E 9.00 auth
//14. 11.12 N 9.00 auth
//15. 15.12 E 9.00 auth
//16. 18.12 N 9.00 backend serverisse üles, frontend serverise üles, erinevad keskkonnad
//17. 22.12 E 9.00 logid, emaili saatmine, cashe,
// KOJU: lõpuprojekt teha
//18. 31.12 N 9.00-10.30 ---> lõpuprojekti esitlemine
