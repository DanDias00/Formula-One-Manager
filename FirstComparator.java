import java.util.Comparator;

class FirstComparator implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        Formula1Driver d1 = (Formula1Driver) o1;
        Formula1Driver d2 = (Formula1Driver) o2;


        if (d1.getFirstPosition() == d2.getFirstPosition()) {
            return 0;
        } else if (d1.getFirstPosition() < d2.getFirstPosition()) {
            return 1;
        } else
            return -1;
    }
}

class TotalComparator implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        Formula1Driver d1 = (Formula1Driver) o1;
        Formula1Driver d2 = (Formula1Driver) o2;
        if (d1.getTotalPoints() == d2.getTotalPoints()) {
            return 0;
        } else if (d1.getTotalPoints() < d2.getTotalPoints()) {
            return 1;
        } else
            return -1;
    }


}

class TotalComparatorReversed implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        Formula1Driver d1 = (Formula1Driver) o1;
        Formula1Driver d2 = (Formula1Driver) o2;

        if (d1.getTotalPoints() == d2.getTotalPoints()) {
            return 0;
        } else if (d1.getTotalPoints() > d2.getTotalPoints()) {
            return 1;
        } else
            return -1;
    }


}

class dateSorting implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        Race r1 = (Race) o1;
        Race r2 = (Race) o2;

        if (r1.getDate() == r2.getDate()) {
            return 0;
        } else if (r1.getDate().isAfter(r2.getDate())) {
            return 1;
        } else
            return -1;
    }
}



