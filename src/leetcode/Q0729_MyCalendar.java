package leetcode;

import java.util.Map;
import java.util.TreeMap;

public class Q0729_MyCalendar {

    TreeMap<Integer, Integer> calendarEvents;

    public Q0729_MyCalendar() {
        this.calendarEvents = new TreeMap<>();
    }

    public boolean book(int start, int end) {

        Map.Entry<Integer, Integer> entry = this.calendarEvents.ceilingEntry(start);
        if ((entry != null) && (entry.getKey() < end)) {
            return false;
        }

        Map.Entry<Integer, Integer> entry2 = this.calendarEvents.floorEntry(start);
        if ((entry2 != null) && (entry2.getValue() > start)) {
            return false;
        }

        this.calendarEvents.put(start, end);
        return true;
    }

}
