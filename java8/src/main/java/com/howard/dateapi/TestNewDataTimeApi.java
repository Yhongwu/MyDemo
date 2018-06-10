package com.howard.dateapi;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.Set;

/**
 * 测试java8新日期api
 * Created by Howard Yao on 2018/6/5.
 */
public class TestNewDataTimeApi {

    /**
     * LocalDate、 LocalTime、 LocalDateTime
     * 操作类似
     */
    @Test
    public void test1() {
        //获取当前时间
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);

        //获取指定时间
        LocalDateTime ldt2 = LocalDateTime.of(2018, 6, 5, 14, 01, 00);
        System.out.println(ldt2);

        //增加指定的年份 类似的可以操作月份 日等
        LocalDateTime ldt3 = ldt2.plusYears(1);
        System.out.println(ldt3);

        //减少指定的月数
        LocalDateTime ldt4 = ldt2.minusMonths(1);
        System.out.println(ldt4);

        //获取相关参数
        System.out.println(ldt.getYear()); //年份
        System.out.println(ldt.getMonthValue()); //月份
        System.out.println(ldt.getDayOfMonth()); //日
        System.out.println(ldt.getHour()); //时
        System.out.println(ldt.getMinute()); //分
        System.out.println(ldt.getSecond()); //秒
    }

    /**
     * Instant 时间戳
     * 使用 Unix 元年  1970年1月1日 00:00:00 所经历的毫秒值
     */
    @Test
    public void test2() {
        //默认使用UTC时区，即与北京时间相差8小时
        Instant instant = Instant.now();
        System.out.println(instant);

        //带偏移量的时间表示
        OffsetDateTime offsetDateTime = instant.atOffset(ZoneOffset.ofHours(8));
        System.out.println(offsetDateTime);

        //获取时间戳
        System.out.println(instant.getNano());

        //获取元年+5秒
        Instant instant1 = instant.ofEpochSecond(5);
        System.out.println(instant1);

    }

    /**
     * Duration：用于计算两个“时间”间隔
     * Period : 用于计算两个“日期”间隔
     */
    @Test
    public void test3() {
        Instant instant1 = Instant.now();

        //睡眠前后产生时间差
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Instant instant2 = Instant.now();

        //两个时间间隔
        Duration duration = Duration.between(instant1, instant2);
        System.out.println(duration); //PT1.001S 默认输出的是 ISO-8601标准
        System.out.println(duration.toMillis()); //1001


        LocalDate ld1 = LocalDate.now();
        LocalDate ld2 = LocalDate.of(2019, 7, 1);

        //两个“日期”间隔
        Period period = Period.between(ld1, ld2);
        System.out.println(period.getYears());
        System.out.println(period.getMonths());
        System.out.println(period.getDays());

    }

    /**
     * TemporalAdjuster : 时间校正器
     */
    @Test
    public void test4() {
        //当前时间
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);

        //本月的第十天
        LocalDateTime ldt2 = ldt.withDayOfMonth(10);
        System.out.println(ldt2);

        //下一个周六
        //TemporalAdjusters提供了很多静态方法可辅助使用
        LocalDateTime ldt3 = ldt.with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
        System.out.println(ldt3);

        //自定义：下一个工作日
        LocalDateTime ldt5 = ldt.with((l) -> {
            LocalDateTime ldt4 = (LocalDateTime) l;

            DayOfWeek dow = ldt4.getDayOfWeek();
            //如果是周五，则+3，周六则+2,其余+1
            if(dow.equals(DayOfWeek.FRIDAY)){
                return ldt4.plusDays(3);
            }else if(dow.equals(DayOfWeek.SATURDAY)){
                return ldt4.plusDays(2);
            }else{
                return ldt4.plusDays(1);
            }
        });

        System.out.println(ldt5);
    }

    /**
     * DateTimeFormatter : 解析和格式化日期或时间
     */
    @Test
    public void test5() {
        //DateTimeFormatter默认提供了很多的格式ISO标准的格式
        //DateTimeFormatter dtf = DateTimeFormatter.ISO_WEEK_DATE;
        //System.out.println(dtf);

        //按自定义的格式解析与格式化
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss E");

        //格式化
        LocalDateTime ldt = LocalDateTime.now();
        String strDate = dtf.format(ldt);
        System.out.println(strDate);

        //解析
        LocalDateTime newLdt = ldt.parse(strDate, dtf);
        System.out.println(newLdt);

    }

    /**
     * 获取支持的时区
     */
    @Test
    public void test6(){
        Set<String> set = ZoneId.getAvailableZoneIds();
        set.forEach(System.out::println);
    }

    /**
     * 获取不同时区的时间
     */
    @Test
    public void test7(){
        LocalDateTime ldt = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
        System.out.println(ldt);

        ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of("US/Pacific"));
        System.out.println(zdt);
    }

    @Test
    public void test8() {
        Date date = new Date();
        System.out.println(date);

        //传统日期转换为instant
        Instant instant = date.toInstant();
        System.out.println(instant);

        //instant转换为传统date
        Date from = Date.from(instant);
        System.out.println(from);
    }

}
