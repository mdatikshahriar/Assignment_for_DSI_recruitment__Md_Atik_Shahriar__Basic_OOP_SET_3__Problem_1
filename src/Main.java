public class Main {

    public static void main(String[] args) {
        Operations operations = new Operations();

        operations.autoAdd();
        operations.addStudentDays(1, 2);
        operations.addStudentDays(3, 2);
        operations.addStudentDays(10, 5);
        operations.addStudentDays(9, 3);
        operations.addStudentDays(1, 2);
        operations.addStudentDays(2, 6);
        operations.seeOverall();
        operations.addStudentMarks(3, 59);
        operations.addStudentMarks(6, 45);
        operations.addStudentMarks(1, 80);
        operations.addStudentMarks(4, 9);
        operations.addStudentMarks(6, 65);
        operations.seeOverall();
        operations.showFullList();
        operations.deleteStudent(2);
        operations.deleteStudent(5);
        operations.deleteStudent(9);
        operations.showFullList();
        operations.seeOverall();
        operations.showOneStudent(3);
        operations.showOneStudent(4);
        operations.showOneStudent(10);
        operations.showFullList();
        operations.seeOverall();
        operations.startUserInput();
        operations.showFullList();
        operations.seeOverall();
    }
}
