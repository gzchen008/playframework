import models.User;
import play.Logger;
import play.jobs.Job;
import play.jobs.OnApplicationStart;
import play.test.Fixtures;

/**
 * Created by cgz on 16-8-2.
 */
@OnApplicationStart
public class Bootstrap extends Job {
    @Override
    public void doJob() {
        if (User.count() == 0) {
            Fixtures.loadModels("inital-data.yml");
            Logger.info("init complated");
        }
    }
}
