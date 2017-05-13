package utilities;

import java.io.File;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
@LocalBean
public class DirectoryInitilizer {

	public static final String JBOSS_HOME = System.getProperty("jboss.home.dir");
	public static final String OPENCARS_DIR = JBOSS_HOME + "/welcome-content/openCars";
	public static final String IMAGE_DIR = OPENCARS_DIR + "/images";

	@PostConstruct
	public void init() {
		File openCarsDir = new File(OPENCARS_DIR);
		File imagesDir = new File(IMAGE_DIR);

		if (!openCarsDir.exists()) {
			try {
				openCarsDir.mkdir();
			} catch (SecurityException se) {
			}
		}

		if (!imagesDir.exists()) {
			try {
				imagesDir.mkdir();
			} catch (SecurityException se) {
			}
		}
	}

}
