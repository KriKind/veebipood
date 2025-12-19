package ee.kristiina.veebipood.service;

import org.springframework.scheduling.annotation.Scheduled;

import org.springframework.stereotype.Service;


@Service
public class CronService {

    // * - sekundid
    // * * - minutid
    // * * * - tunnid (max24)
    // * * * * - kuupäev kuus (max 31)
    // * * * * * - kuu (max 12)
    // * * * * * * - nädalapäev (0-7) kus pühapäev on nii 0 kui ka 7

//    @Scheduled(cron = "* * * * * *")
//    public void startEverySecond() {
//        System.out.println("Starting every second.");
//    }

//    @Scheduled(cron = "*/30 * * * * *")
//    public void startEverySecond() {
//        System.out.println("Starting every 30 second.");
//    }
//    // @Autowired
//    // private BronRepository bronRepository
//    @Scheduled(cron = "0 0 9-17 * * 1-5")
//    public void startEveryHourOnWorkhours() {
//        // custom päring kus on homne kuupäev. kui kell 9 käivitub, siis 9-10
//        System.out.println("Saada email kõigile kel on broneering 24 tunni pärast");
//    }
//
//    @Scheduled(cron ="0 0 13 22 * *")
//    public void startOnEvery22nd() {
//        // custom päring selle kuu arvete kohta, kellel on maksmata
//
//        System.out.println();
//    }
}
