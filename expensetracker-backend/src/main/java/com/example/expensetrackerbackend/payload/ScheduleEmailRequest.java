package com.example.expensetrackerbackend.payload;

// import javax.validation.constraints.Email;
// import javax.validation.constraints.NotEmpty;
// import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.ZoneId;

// import lombok.Getter;
// import lombok.Setter;

// @Setter
// @Getter
public class ScheduleEmailRequest {

    private String email;

    private String subject;

    private String body;

    private LocalDateTime dateTime;

    private ZoneId timeZone;
    

    public String getEmail() {
        return email;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public ZoneId getTimeZone() {
        return timeZone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setTimeZone(ZoneId timeZone) {
        this.timeZone = timeZone;
    }
    

}
