//package sg.bowling.bowlingapp.config;
//
//import java.time.LocalDate;
//import java.util.List;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import sg.bowling.bowlingapp.repository.BowlingBallRepository;
//import sg.bowling.bowlingapp.model.Bowlingball;
//
//@Configuration
//public class BowlingBallConfig {
//	@Bean
//	CommandLineRunner commandLineRunner(BowlingBallRepository repository) {
//		return args -> {
//			Bowlingball infinitePhysix = new Bowlingball(
//					"Infinite Physix",
//					LocalDate.of(2022, 7, 8),
//					"R2X Pearl Reactive",
//					"heavy oil",
//					4000,
//					false,
//					14.4,
//					2.53,
//					0.052,
//					"INFINITE  PHYSIX.png-2022-07-06T16:32:44.950531600",
//					280.00,
//					false,
//					"<10",
//					"The Infinite PhysiX is destined to get the bowling world’s attention. "
//					+ "When you craft vivacity with the same detail you craft performance, "
//					+ "you create the Storm Premier Line. They’re more than mere bowling balls. ",
//					"Storm",
//					1,
//					"right 1 handed",
//					false,
//					Long.valueOf(1)
//					);
//			Bowlingball darkCode = new Bowlingball(
//					"Dark Code",
//					LocalDate.of(2021, 7, 2),
//					"R2X Pearl Reactive",
//					"heavy oil",
//					4000,
//					false,
//					15.2,
//					2.50,
//					0.058,
//					"DARK CODE.png-2022-07-06T16:30:49.345736900",
//					280.00,
//					false,
//					"<10",
//					"If you need the perfect combination of mid-lane roll and "
//					+ "backend reactivity with head-turn pin carry, the Dark Code "
//					+ "should be your very first choice. Easy power and a gorgeous look? "
//					+ "Sign us up.",
//					"Storm",
//					1,
//					"right 1 handed",
//					false,
//					Long.valueOf(2)
//					);
//			repository.saveAll(
//				List.of(infinitePhysix, darkCode)
//			);
//		};
//	}
//}
