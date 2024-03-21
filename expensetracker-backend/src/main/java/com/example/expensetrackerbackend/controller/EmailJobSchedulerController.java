package com.example.expensetrackerbackend.controller;

import com.example.expensetrackerbackend.job.EmailJob;
import com.example.expensetrackerbackend.payload.ScheduleEmailRequest;
import com.example.expensetrackerbackend.payload.ScheduleEmailResponse;

import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class EmailJobSchedulerController {
    private static final Logger logger = LoggerFactory.getLogger(EmailJobSchedulerController.class);

    @Autowired
    private Scheduler scheduler;

    @PostMapping("/scheduleEmail")
    public ResponseEntity<ScheduleEmailResponse> scheduleEmail(@RequestBody ScheduleEmailRequest scheduleEmailRequest) {
        System.out.println("email Job Scheduler Controller");
        try {
            ZonedDateTime dateTime = ZonedDateTime.of(scheduleEmailRequest.getDateTime(), scheduleEmailRequest.getTimeZone());
            if(dateTime.isBefore(ZonedDateTime.now())) {
                ScheduleEmailResponse scheduleEmailResponse = new ScheduleEmailResponse(false,
                        "dateTime must be after current time");
                System.out.println("dateTime must be after current time");
                return ResponseEntity.badRequest().body(scheduleEmailResponse);
            }
            JobDetail jobDetail = buildJobDetail(scheduleEmailRequest);
            Trigger trigger = buildJobTrigger(jobDetail, dateTime);
            scheduler.scheduleJob(jobDetail, trigger);

            ScheduleEmailResponse scheduleEmailResponse = new ScheduleEmailResponse(true,
                    jobDetail.getKey().getName(), jobDetail.getKey().getGroup(), "Email Scheduled Successfully!");

            return ResponseEntity.ok(scheduleEmailResponse);
        } catch (SchedulerException ex) {
            logger.error("Error scheduling email", ex);

            ScheduleEmailResponse scheduleEmailResponse = new ScheduleEmailResponse(false,
                    "Error scheduling email. Please try later!");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(scheduleEmailResponse);
        }
    }

    @DeleteMapping("/deleteScheduledEmail/{jobName}/{jobGroup}")
    public ResponseEntity<String> deleteScheduledEmail(@PathVariable String jobName, @PathVariable String jobGroup) {
        try {
            JobKey jobKey = new JobKey(jobName, jobGroup);
            boolean isJobDeleted = scheduler.deleteJob(jobKey);
            if (isJobDeleted) {
                return ResponseEntity.ok("Email scheduled job canceled successfully!");
            } else {
                return ResponseEntity.badRequest().body("No scheduled job found with the provided job name and group");
            }
        } catch (SchedulerException ex) {
            // Log the exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error canceling scheduled email job");
        }
    }

    @GetMapping("/getAllScheduledEmails")
    public ResponseEntity<List<String>> getAllScheduledEmails() {
        try {
            List<String> scheduledEmailDetails = new ArrayList<>();

            // Get all job keys
            for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.anyGroup())) {
                // Get job details
                JobDetail jobDetail = scheduler.getJobDetail(jobKey);

                // Get triggers for the job
                List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
                for (Trigger trigger : triggers) {
                    scheduledEmailDetails.add("Job Name: " + jobDetail.getKey().getName() +
                            ", Group: " + jobDetail.getKey().getGroup() +
                            ", Trigger: " + trigger.getKey().getName() +
                            ", Next fire time: " + trigger.getNextFireTime());
                }
            }

            return ResponseEntity.ok(scheduledEmailDetails);
        } catch (SchedulerException ex) {
            // Log the exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    private JobDetail buildJobDetail(ScheduleEmailRequest scheduleEmailRequest) {
        JobDataMap jobDataMap = new JobDataMap();

        jobDataMap.put("email", scheduleEmailRequest.getEmail());
        jobDataMap.put("subject", scheduleEmailRequest.getSubject());
        jobDataMap.put("body", scheduleEmailRequest.getBody());

        return JobBuilder.newJob(EmailJob.class)
                .withIdentity(UUID.randomUUID().toString(), "email-jobs")
                .withDescription("Send Email Job")
                .usingJobData(jobDataMap)
                .storeDurably()
                .build();
    }

    private Trigger buildJobTrigger(JobDetail jobDetail, ZonedDateTime startAt) {
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity(jobDetail.getKey().getName(), "email-triggers")
                .withDescription("Send Email Trigger")
                .startAt(Date.from(startAt.toInstant()))
                .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever())
                .build();
    }
}
