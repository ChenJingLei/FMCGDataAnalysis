package app.components;

import app.service.ProductItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by cjl20 on 2016/7/5.
 */
@Component
public class CaptureTimer {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    private ProductItemService productItemService;

    @Scheduled(fixedRate = 5000)
    public void CaptureData() {
        System.out.println("The time is now " + dateFormat.format(new Date()));

        try {
//            productItemService.CaptureData();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
