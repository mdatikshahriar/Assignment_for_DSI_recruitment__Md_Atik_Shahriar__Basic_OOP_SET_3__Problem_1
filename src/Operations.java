import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Operations {
    private final Scanner myScanner;
    private final Students students;

    Operations() {
        myScanner = new Scanner(System.in);
        students = new Students();
    }

    void autoAdd() {
        students.addStudents(8, "Sergio Ramos", new Boolean[] {true, false, true});
        students.addStudents(9, "Raphael Varane", new Boolean[] {false, false, true});
        students.addStudents(10, "Luka Modric", new Boolean[] {true, true, true});
        students.addStudents(10, "Toni Kroos", new Boolean[] {false, true, true});
        students.addStudents(8, "Karim Benzema", new Boolean[] {true, false, true});
        students.addStudents(8, "Casemiro", new Boolean[] {true, false, false});
        students.addStudents(9, "Dani Carvajal", new Boolean[] {true, true, false});
        students.addStudents(8, "Ferland Mendy", new Boolean[] {false, false, true});
        students.addStudents(9, "Eden Hazard", new Boolean[] {false, false, false});
        students.addStudents(10, "Marco Asensio", new Boolean[] {true, false, true});
    }

    void addStudentDays (int id, int days) {
        students.editStudentDays(id, days);
    }

    void addStudentMarks (int id, int marks) {
        students.editStudentMarks(id, marks);
    }

    void deleteStudent (int id) {
        if (!students.deleteStudent(id)) System.out.println("This student doesn't exist.");
    }

    void showOneStudent(int id) {
        Student student = students.getStudentById(id);
        if (student == null) {
            System.out.println("This student doesn't exist.");
            return;
        }
        System.out.println("The details of the student is as below.");
        System.out.println("ID: " + student.getId());
        System.out.println("Name: " + student.getName());
        System.out.println("Class: " + student.getCls());

        Boolean[] subjects = student.getSubjects();
        int i = 0;
        ArrayList<String> sbjs = new ArrayList<>();

        for (boolean status : subjects) {
            if (status) {
                switch (i) {
                    case 0 -> sbjs.add("Math");
                    case 1 -> sbjs.add("English");
                    case 2 -> sbjs.add("Bangla");
                }
            }
            i++;
        }

        System.out.print("Subjects taught: ");
        for (int j = 0; j < sbjs.size(); j++) {
            System.out.print(sbjs.get(j));
            if (j != sbjs.size() - 1) System.out.print(", ");
            else System.out.println();
        }

        System.out.println("Number of days taught: " + student.getDaysTaught());
        System.out.println("Earnings: " + student.getEarnings());
        System.out.println("Average marks in the exams got: " + student.getAvgMarks());
    }

    void showFullList () {
        String leftAlignFormat = "| %-4d | %-20s | %-10d     | %-10s    |%n";

        System.out.format("+------+----------------------+----------------+---------------+%n");
        System.out.format("| ID   | Student Name         | Total Earnings | Average Marks |%n");
        System.out.format("+------+----------------------+----------------+---------------+%n");

        for (Student student: students.getStudents()) {
            System.out.format(leftAlignFormat, student.getId(), student.getName(), student.getEarnings(), student.getAvgMarks());
        }
        System.out.format("+------+----------------------+----------------+---------------+%n");
    }

    void startUserInput() {
        while (true) {
            int input;

            System.out.println();
            System.out.print("""
                    What do you want to do? Type the associated number to do so.
                    1. Add a student
                    2. Edit a student
                    3. Delete a student
                    4. See the list of students individually
                    5. See overall info
                    6. Exit
                    Type Here:\s""");

            while (true) {
                try{
                    input = myScanner.nextInt();
                    if (input >= 1 && input <= 6) break;
                    else throw new Exception();
                } catch (Exception e) {
                    System.out.print("It should be a number between 1 to 6. Type again: ");
                    if (e instanceof InputMismatchException) myScanner.next();
                }
            }

            switch (input) {
                case 1 -> userAddStudent();
                case 2 -> userEditStudent();
                case 3 -> userDeleteStudent();
                case 4 -> userSeeList();
                case 5 -> seeOverall();
                case 6 -> {}
                default -> showError();
            }

            if (input == 6) {
                myScanner.close();
                break;
            }
        }
    }

    private void userAddStudent() {
        int cls;
        String name;
        Boolean[] subjects = new Boolean[3];
        System.out.print("Choose one class from 8, 9 or 10. Type here: ");

        while (true) {
            try{
                cls = myScanner.nextInt();
                if (cls >= 8 && cls <= 10) {
                    myScanner.nextLine();
                    break;
                }
                else throw new Exception();
            } catch (Exception e) {
                System.out.print("It should be a number from 8, 9 or 10. Type again: ");
                if (e instanceof InputMismatchException) myScanner.next();
            }
        }

        System.out.println("Also type the information asked below.");
        System.out.print("Name: ");
        name = myScanner.nextLine();

        System.out.println("Type 1 for the subjects you teach. Otherwise, type 0");

        System.out.print("Math: ");
        String mathResult;
        while (true) {
            mathResult = myScanner.next();
            if (mathResult.equals("1") || mathResult.equals("0")) break;
            else System.out.print("Invalid input! Type again: ");
        }
        subjects[0] = mathResult.equals("1");

        System.out.print("English: ");
        String englishResult;
        while (true) {
            englishResult = myScanner.next();
            if (englishResult.equals("1") || englishResult.equals("0")) break;
            else System.out.print("Invalid input! Type again: ");
        }
        subjects[1] = englishResult.equals("1");

        System.out.print("Bangla: ");
        String banglaResult;
        while (true) {
            banglaResult = myScanner.next();
            if (banglaResult.equals("1") || banglaResult.equals("0")) break;
            else System.out.print("Invalid input! Type again: ");
        }
        subjects[2] = banglaResult.equals("1");

        students.addStudents(cls, name, subjects);
    }

    private void userEditStudent() {
        System.out.print("Type the serial number of the student you want to edit. Type here: ");
        int id;

        while (true) {
            try {
                id = myScanner.nextInt();
                if (students.exist(id)) break;
                else throw new Exception();
            } catch (Exception e) {
                System.out.print("This student doesn't exist. Type again: ");
                if (e instanceof InputMismatchException) myScanner.next();
            }
        }

        System.out.print("""
                What do you want to edit? Type the associated number to do so.
                1. Add days to the number of days taught.
                2. Add marks got in exam.
                Type here:\s""");

        int input;

        while (true) {
            try{
                input = myScanner.nextInt();
                if (input == 1 || input == 2) break;
                else throw new Exception();
            } catch (Exception e) {
                System.out.print("It should be a number from either 1 or 2. Type again: ");
                if (e instanceof InputMismatchException) myScanner.next();
            }
        }

        if (input == 1) {
            System.out.print("Enter the number of days you want to add. Type here: ");
            int days;
            while (true) {
                try{
                    days = myScanner.nextInt();
                    break;
                } catch (Exception e) {
                    System.out.print("It should be a number. Type again: ");
                    if (e instanceof InputMismatchException) myScanner.next();
                }
            }
            if (!students.editStudentDays(id, days)) System.out.println("This student doesn't exist.");
        } else {
            System.out.print("Enter the marks you want to add. Type here: ");
            int marks;
            while (true) {
                try{
                    marks = myScanner.nextInt();
                    if (marks >= 0 && marks <= 100) break;
                    else throw new Exception();
                } catch (Exception e) {
                    System.out.println("It should be a number between 0 to 100. Type again: ");
                    if (e instanceof InputMismatchException) myScanner.next();
                }
            }
            if (!students.editStudentMarks(id ,marks)) System.out.println("This student doesn't exist.");
        }
    }

    private void userDeleteStudent() {
        System.out.print("Enter the ID of the student you want to delete. Type here: ");
        int id;

        while (true) {
            try {
                id = myScanner.nextInt();
                if (students.exist(id)) break;
                else throw new Exception();
            } catch (Exception e) {
                System.out.print("This student doesn't exist. Type again: ");
                if (e instanceof InputMismatchException) myScanner.next();
            }
        }

        if (!students.deleteStudent(id)) System.out.println("This student doesn't exist.");
    }

    private void userSeeList() {
        System.out.print("""
                How do you want to see the list? Type the associated number to do so.
                1. See the list of students of a specific class.
                2. See an individual student.
                Type here:\s""");

        int input;

        while (true) {
            try{
                input = myScanner.nextInt();
                if (input == 1 || input == 2) break;
                else throw new Exception();
            } catch (Exception e) {
                System.out.print("It should be a number from either 1 or 2. Type again: ");
                if (e instanceof InputMismatchException) myScanner.next();
            }
        }

        if (input == 1) {
            System.out.print("Choose one class from 8, 9 or 10. Type it here: ");

            int cls;

            while (true) {
                try{
                    cls = myScanner.nextInt();
                    if (cls >= 8 && cls <= 10) break;
                    else throw new Exception();
                } catch (Exception e) {
                    System.out.print("It should be a number from 8, 9 or 10. Type again: ");
                    if (e instanceof InputMismatchException) myScanner.next();
                }
            }

            if (students.getIndividualStudentsOfClasses(cls) == 0) {
                System.out.println("There is no student teaching in this class.");
            } else {
                String leftAlignFormat = "| %-4d | %-20s | %-10d     | %-10s    |%n";

                System.out.format("+------+----------------------+----------------+---------------+%n");
                System.out.format("| ID   | Student Name         | Total Earnings | Average Marks |%n");
                System.out.format("+------+----------------------+----------------+---------------+%n");

                for (Student student: students.getStudents()) {
                    if (student.getCls() == cls) {
                        System.out.format(leftAlignFormat, student.getId(), student.getName(), student.getEarnings(), student.getAvgMarks());
                    }
                }

                System.out.format("+------+----------------------+----------------+---------------+%n");
            }
        } else {
            System.out.print("Enter the ID of the student you want to see. Type here: ");
            int id;

            while (true) {
                try {
                    id = myScanner.nextInt();
                    if (students.exist(id)) break;
                    else throw new Exception();
                } catch (Exception e) {
                    System.out.print("This student doesn't exist. Type again: ");
                    if (e instanceof InputMismatchException) myScanner.next();
                }
            }

            Student student = students.getStudentById(id);
            System.out.println("The details of the student is as below.");
            System.out.println("ID: " + student.getId());
            System.out.println("Name: " + student.getName());
            System.out.println("Class: " + student.getCls());

            Boolean[] subjects = student.getSubjects();
            int i = 0;
            ArrayList<String> sbjs = new ArrayList<>();

            for (boolean status : subjects) {
                if (status) {
                    switch (i) {
                        case 0 -> sbjs.add("Math");
                        case 1 -> sbjs.add("English");
                        case 2 -> sbjs.add("Bangla");
                    }
                }
                i++;
            }

            System.out.print("Subjects taught: ");
            for (int j = 0; j < sbjs.size(); j++) {
                System.out.print(sbjs.get(j));
                if (j != sbjs.size() - 1) System.out.print(", ");
                else System.out.println();
            }

            System.out.println("Number of days taught: " + student.getDaysTaught());
            System.out.println("Earnings: " + student.getEarnings());
            System.out.println("Average marks in the exams got: " + student.getAvgMarks());
        }
    }

    public void seeOverall() {
        System.out.println("Overall info:");
        System.out.println("a. The total days taught across all classes: " + students.getTotalDays());
        System.out.println("b. Individual days taught in each class: "+
                "8 = " + students.getIndividualDaysOfClasses(8) +
                ", 9 = " + students.getIndividualDaysOfClasses(9) +
                ", 10 = " + students.getIndividualDaysOfClasses(10));
        System.out.println("c. Total earnings: " + students.getTotalEarnings());
        System.out.println("d. Individual earnings of each class: " +
                "8 = " + students.getIndividualEarningsOfClasses(8) +
                ", 9 = " + students.getIndividualEarningsOfClasses(9) +
                ", 10 = " + students.getIndividualEarningsOfClasses(10));
        System.out.println("e. Individual earnings of each subject: " +
                "Math = " + students.getIndividualEarningsOfSubjects("Math") +
                ", English = " + students.getIndividualEarningsOfSubjects("English") +
                ", Bangla = " + students.getIndividualEarningsOfSubjects("Bangla"));
        System.out.println("f. Average marks of all students: " + students.getAverageMarks());
    }

    private void showError() {
        System.out.println("Error occurred!!!");
    }

}
