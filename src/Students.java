import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Students {
    private final ArrayList<Student> students;
    private int numberOfStudents, totalDays, totalEarnings, totalMarks;
    private final Map<Integer, Integer> individualStudentsOfClasses;
    private final Map<Integer, Integer> individualDaysOfClasses;
    private final Map<Integer, Integer> individualEarningsOfClasses;
    private final Map<String, Integer> individualEarningsOfSubjects;

    public Students() {
        students = new ArrayList<>();
        numberOfStudents = 0;
        totalDays = 0;
        totalEarnings = 0;
        totalMarks = 0;
        individualStudentsOfClasses = new HashMap<>();
        individualDaysOfClasses = new HashMap<>();
        individualEarningsOfClasses = new HashMap<>();
        individualEarningsOfSubjects = new HashMap<>();
        individualStudentsOfClasses.put(8, 0);
        individualStudentsOfClasses.put(9, 0);
        individualStudentsOfClasses.put(10, 0);
        individualDaysOfClasses.put(8, 0);
        individualDaysOfClasses.put(9, 0);
        individualDaysOfClasses.put(10, 0);
        individualEarningsOfClasses.put(8, 0);
        individualEarningsOfClasses.put(9, 0);
        individualEarningsOfClasses.put(10, 0);
        individualEarningsOfSubjects.put("Math", 0);
        individualEarningsOfSubjects.put("English", 0);
        individualEarningsOfSubjects.put("Bangla", 0);
    }

    public void addStudents(int cls, String name, Boolean[] subjects) {
        numberOfStudents++;

        students.add(new Student(numberOfStudents, cls, name, subjects));

        Integer prevStudentsOfClasses = individualStudentsOfClasses.get(cls);
        individualStudentsOfClasses.put(cls, prevStudentsOfClasses + 1);

        System.out.println("Added successfully. ID No: " + numberOfStudents);
    }

    public boolean editStudentDays(int id, int days) {
        Student student = getStudentById(id);

        if (student == null) return false;

        totalEarnings += student.addDays(days);
        totalDays += days;

        Integer cls = student.getCls();
        Integer prevDaysOfClasses = individualDaysOfClasses.get(cls);
        individualDaysOfClasses.put(cls, prevDaysOfClasses + days);

        Boolean[] subjects = student.getSubjects();
        int count = 0;
        if (subjects[0]) {
            Integer individualEarningsOfMath = individualEarningsOfSubjects.get("Math");
            individualEarningsOfSubjects.put("Math", individualEarningsOfMath + days);
            count++;
        }
        if (subjects[1]) {
            Integer individualEarningsOfEnglish = individualEarningsOfSubjects.get("English");
            individualEarningsOfSubjects.put("English", individualEarningsOfEnglish + days);
            count++;
        }
        if (subjects[2]) {
            Integer individualEarningsOfBangla = individualEarningsOfSubjects.get("Bangla");
            individualEarningsOfSubjects.put("Bangla", individualEarningsOfBangla + days);
            count++;
        }

        Integer prevEarningsOfClasses = individualEarningsOfClasses.get(cls);
        individualEarningsOfClasses.put(cls, prevEarningsOfClasses + (count * days));

        System.out.println("Successfully added " + days + " days to ID no " + id + ".");

        return true;
    }

    public boolean editStudentMarks(int id, int marks) {
        Student student = getStudentById(id);

        if (student == null) return false;

        student.addMarks(marks);
        totalMarks += marks;

        System.out.println("Successfully added " + marks + " marks to ID no " + id + ".");
        return true;
    }

    public boolean deleteStudent(int id) {
        Student student = getStudentById(id);

        if(student == null) return false;

        Integer days = student.getDaysTaught();
        Integer cls = student.getCls();

        Integer prevDaysOfClasses = individualDaysOfClasses.get(cls);
        individualDaysOfClasses.put(cls, prevDaysOfClasses - days);
        Integer prevStudentsOfClasses = individualStudentsOfClasses.get(cls);
        individualStudentsOfClasses.put(cls, prevStudentsOfClasses - 1);

        numberOfStudents--;
        totalDays -= student.getDaysTaught();
        totalMarks -= student.getTotalMarks();

        students.remove(student);

        System.out.println("Deleted ID no " + id + " successfully!");

        return true;
    }

    public Student getStudentById(int id) {
        for (Student student : students) {
            if (student.getId() == id) return student;
        }

        return null;
    }

    public boolean exist(int id) {
        return getStudentById(id) != null;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public int getTotalDays() {
        return totalDays;
    }

    public int getTotalEarnings() {
        return totalEarnings;
    }

    public int getIndividualStudentsOfClasses(Integer cls) {
        return individualStudentsOfClasses.get(cls);
    }

    public int getIndividualDaysOfClasses(Integer cls) {
        return individualDaysOfClasses.get(cls);
    }

    public int getIndividualEarningsOfClasses(Integer cls) {
        return individualEarningsOfClasses.get(cls);
    }

    public int getIndividualEarningsOfSubjects(String subject) {
        return individualEarningsOfSubjects.get(subject);
    }

    public String getAverageMarks() {
        if (numberOfStudents == 0) return "0";

        double avg = (double) totalMarks / numberOfStudents;

        return format(avg);
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
