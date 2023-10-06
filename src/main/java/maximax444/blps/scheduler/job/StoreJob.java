package maximax444.blps.scheduler.job;

import maximax444.blps.service.StorageService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StoreJob implements Job {

    @Autowired
    private StorageService storeService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        storeService.checkStore();
        System.out.println("schedule");
    }
}