package S_7_3;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateBean {
    private Calendar calendar;

    /**
     * 获取当前日期时间
     */
    public String getDateTime(){
        getCalendar();
        Date curDate=calendar.getTime();
        System.out.println(curDate.getTime());
        SimpleDateFormat format=new SimpleDateFormat("yyyy年MM月dd日  hh:mm:ss");
        return format.format(curDate);
    }

    /**
     * 获取当前日期是星期几
     * @return '
     */
    public String getWeek(){
        String[] weeks={"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};
        int index=calendar.get(Calendar.DAY_OF_WEEK);
        return weeks[index-1];
    }

    private void getCalendar(){
        calendar=Calendar.getInstance();
    }
}
