package com.javaemaildemo.controller;

import com.javaemaildemo.job.HelloJob;
import com.javaemaildemo.service.IQuartzService;
import com.javaemaildemo.service.impl.QuartzServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author ${lcl}
 * @Title: QuartzController
 * @ProjectName javaemaildemo
 * @Description: TODO
 * @date 2019/2/27 002716:11
 */
@RestController
public class QuartzController {

    @Resource
    private IQuartzService quartzService;


    @GetMapping("quartzStart")
    public String startNNoQuartz() {
        quartzService.startJob("0 11 11 * * ? ", "job1", "gropu1", HelloJob.class);
//        quartzService.startJob("0/2 * * * * ? ", "job2", "gropu2", HelloJoTwo.class);

        return "定时器任务开始执行，请注意观察控制台";
    }

    @GetMapping("pauseJob")
    public String pauseJob() {
        quartzService.pauseJob("job1", "gropu1");
        return "暂停一个定时器任务，请注意观察控制台";
    }


    @GetMapping("resumeJob") //shutdown关闭了，或者删除了就不能重启了
    public String resumeJob() {
        quartzService.resumeJob("job1", "gropu1");
        return "暂停重启一个定时器任务，请注意观察控制台";
    }

    @GetMapping("deleteJob")
    public String deleteJob() {
        quartzService.deleteJob("job1", "gropu1");
        return "删除一个定时器任务，请注意观察控制台，删除了，重启就没什么用了";
    }


    @GetMapping("doJob")
    public String doJob() {
        quartzService.doJob("job1", "gropu1");
        return "根据出发规则匹配任务，立即执行定时任务，暂停的时候可以用";
    }

    @GetMapping("startAllJob")
    public String startAllJob() {
        quartzService.startAllJob();
        return "开启定时器，这时才可以开始所有的任务，默认是开启的";
    }

    @GetMapping("shutdown")
    public String shutdown() {
        quartzService.shutdown();
        return "关闭定时器，则所有任务不能执行和创建";
    }
}
