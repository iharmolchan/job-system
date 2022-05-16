package by.intexsoft.imolchan.jobsystem.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.scheduling.support.CronExpression;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CronUtils {
    public static LocalDateTime getNextRunFromCronExpression(String cronString) {
        CronExpression expression = CronExpression.parse(cronString);
        return expression.next(LocalDateTime.now());
    }

    public static LocalDateTime getNextRunFromCronExpression(String cronString, LocalDateTime start) {
        CronExpression expression = CronExpression.parse(cronString);
        return expression.next(start);
    }
}
