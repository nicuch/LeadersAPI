package ro.nicuch.leaders.utils;

import ro.nicuch.leaders.api.RankData;
import ro.nicuch.leaders.api.TaskDescription;
import ro.nicuch.leaders.enums.ComparatorType;
import ro.nicuch.leaders.enums.TaskType;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class SortingUtil {

    public static LinkedList<RankData> sortPlayerData(TaskDescription taskDescription, LinkedList<RankData> unsortedList) {
        ComparatorType comparatorType = taskDescription.getComparatorType();
        if (taskDescription.getTaskType() == TaskType.GROUPS_COUNT || taskDescription.getTaskType() == TaskType.GROUPS_SORT
                || taskDescription.getTaskType() == TaskType.GROUPS_INCREMENT)
            comparatorType = ComparatorType.NUMBER_COMPARATOR;
        switch (comparatorType) {
            case NUMBER_COMPARATOR:
                return unsortedList
                        .stream()
                        .sorted(reverseOrder(taskDescription.isReverseOrder(), (o1, o2) -> {
                            try {
                                BigDecimal value1 = new BigDecimal(o1.getValue());
                                BigDecimal value2 = new BigDecimal(o2.getValue());
                                return value1.compareTo(value2);
                            } catch (NumberFormatException ex) {
                                ex.printStackTrace();
                            }
                            return -1;
                        })).collect(Collectors.toCollection(LinkedList::new));
            case DATE_COMPARATOR:
                return unsortedList
                        .stream()
                        .sorted(reverseOrder(taskDescription.isReverseOrder(), (o1, o2) -> {
                            try {
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(taskDescription.getDateFormat());
                                Date date1 = simpleDateFormat.parse(o1.getValue());
                                Date date2 = simpleDateFormat.parse(o2.getValue());
                                return date1.compareTo(date2);
                            } catch (IllegalArgumentException | ParseException ex) {
                                ex.printStackTrace();
                            }
                            return -1;
                        })).collect(Collectors.toCollection(LinkedList::new));
            case STRING_COMPARATOR:
            default:
                return unsortedList
                        .stream()
                        .sorted(reverseOrder(taskDescription.isReverseOrder(), Comparator.comparing(RankData::getValue)))
                        .collect(Collectors.toCollection(LinkedList::new));
        }
    }

    public static <T> Comparator<T> reverseOrder(boolean reverse, Comparator<T> cmp) {
        if (reverse)
            return cmp;
        return Collections.reverseOrder(cmp);
    }
}
