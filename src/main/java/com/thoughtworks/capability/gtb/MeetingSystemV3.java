package com.thoughtworks.capability.gtb;

import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 脑洞会议系统v3.0
 * 1.当前会议时间"2020-04-01 14:30:00"表示伦敦的本地时间，而输出的新会议时间是芝加哥的本地时间
 * 场景：
 * a:上个会议是伦敦的同事定的，他在界面上输入的时间是"2020-04-01 14:30:00"，所以我们要解析的字符串是伦敦的本地时间
 * b:而我们在当前时区(北京时区)使用系统
 * c:我们设置好新会议时间后，要发给芝加哥的同事查看，所以格式化后的新会议时间要求是芝加哥的本地时间
 * 2.用Period来实现下个会议时间的计算
 *
 * @author itutry
 * @create 2020-05-19_18:43
 */
public class MeetingSystemV3 {

    private static final String FORMATTER = "yyyy-MM-dd HH:mm:ss";

    private static final String LONDON_ZONE = "Europe/London";

    private static final String BEIJING_ZONE = "Asia/Shanghai";

    private static final String CHICAGO_ZONE = "America/Chicago";

    public static void main(String[] args) {
        String timeStr = "2020-04-01 14:30:00";

        // 根据格式创建格式化类
        DateTimeFormatter londonFormatter = DateTimeFormatter.ofPattern(FORMATTER).withZone(ZoneId.of(LONDON_ZONE));
        // 从字符串解析得到会议时间

        ZonedDateTime londonMeetingTime = ZonedDateTime.parse(timeStr, londonFormatter);
        ZonedDateTime beijingMeetingTime = londonMeetingTime.withZoneSameInstant(ZoneId.of(BEIJING_ZONE));
        ZonedDateTime beijingNowTime = ZonedDateTime.of(LocalDateTime.now(), ZoneId.of(BEIJING_ZONE));

        if (beijingNowTime.isAfter(beijingMeetingTime)) {
            Period period = Period.ofDays(1);
            ZonedDateTime zonedDateTime = beijingNowTime.plus(period);

            int newDayOfYear = zonedDateTime.getDayOfYear();
            beijingMeetingTime = beijingMeetingTime.withDayOfYear(newDayOfYear);

            ZonedDateTime chicagoMeetingTime = beijingMeetingTime.withZoneSameInstant(ZoneId.of(CHICAGO_ZONE));

            // 格式化新会议时间
            DateTimeFormatter chicagoFormatter = DateTimeFormatter.ofPattern(FORMATTER).withZone(ZoneId.of(CHICAGO_ZONE));
            String showTimeStr = chicagoFormatter.format(chicagoMeetingTime);
            System.out.println(showTimeStr);
        } else {
            System.out.println("会议还没开始呢");
        }
    }
}
