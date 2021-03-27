import java.text.DecimalFormat;

public class Student {
    private final int id;
    private final int cls;
    private final int subjectCount;
    private final String name;
    private final Boolean[] subjects;
    private int daysTaught;
    private int earnings;
    private int totalMarks;

    public Student(int id, int cls, String name, Boolean[] subjects) {
        this.id = id;
        this.cls = cls;
        this.name = name;
        this.daysTaught = 0;
        this.earnings = 0;
        this.totalMarks = 0;
        this.subjects = subjects;
        int count = 0;

        for (boolean bl : subjects) if (bl) count++;
        subjectCount = count;
    }

    public int addDays(int days) {
        daysTaught += days;
        int extraEarnings = subjectCount * days;
        earnings += extraEarnings;

        return extraEarnings;
    }

    public void addMarks(int marks) {
        totalMarks += marks;
    }

    public int getId() {
        return id;
    }

    public int getCls() {
        return cls;
    }

    public int getDaysTaught() {
        return daysTaught;
    }

    public int getEarnings() {
        return earnings;
    }

    public String getAvgMarks() {
        if (subjectCount == 0) return "0";

        double avg = (double) totalMarks / subjectCount;

        return format(avg);
    }

    public String getName() {
        return name;
    }

    public Boolean[] getSubjects() {
        return subjects;
    }

    public int getTotalMarks() {
        return totalMarks;
    }

    private String format (double d) {
        if(d == (long) d)
            return String.format("%d", (long) d);
        else {
            DecimalFormat df = new DecimalFormat("0");
            df.setMinimumFractionDigits(0);
            df.setMaximumFractionDigits(6);
            return String.format("%s", df.format(d));
        }
    }
}
