import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static Scanner scanner = new Scanner(System.in);
    public static List<Integer> list=new ArrayList<Integer>();
    public static int next=7;
    public static int current = 7;
    public static int number = 0;
    public static void main(String[] args) throws Exception {
        
        while (number < 7) {
            System.out.print("Number of Students(min-7): ");
            number = scanner.nextInt();
        }
        Student student[] = new Student[number];
        student[0] = new Student(1, "Mihai", "UBB");
        student[1] = new Student(2, "Maria", "UPT");
        student[2] = new Student(100, "George", "UTCN");
        student[3] = new Student(13, "Elena", "UPT");
        student[4] = new Student(5, "Andrei", "UTCN");
        student[5] = new Student(77, "Sergiu", "UTCN");
        student[6] = new Student(99, "Cristina", "UPT");
        student[0].setGrades(9.35, 0);
        student[1].setGrades(9.1, 0);
        student[2].setGrades(8.5, 0);
        student[3].setGrades(5.6, 0);
        student[4].setGrades(3.0, 0);
        student[5].setGrades(10.0, 0);
        student[6].setGrades(5.0, 0);

        
        for (int i = 0; i < current; i++) {
            list.add(student[i].getID());
        }

        while (checkForGrades(student)) {
            setNewGrades(student);    
        }
        for (int i = 0; i < student.length; i++) {
            student[i].show();
        }

        while (true) {
            System.out.println("Your choice: ");
            int x = scanner.nextInt();
            switch (x) {
                case 1:
                    number++;
                    Student temp[] = new Student[number];
                    for (int i = 0; i < temp.length - 1; i++) {
                        temp[i] = student[i];
                    }
                    student = temp;
                    addNewStudent(student);
                    student[number-1].setGrades(0.0, 0);
                    checkForGrades(student);
                    do {
                        setNewGrades(student);    

                    } while (checkForGrades(student));
                    break;
                case 2:
                    for (int i = 0; i < student.length; i++) {
                        student[i].show();
                    }
                    break;
            
                default:
                    break;
            }
        }
    }

    static void addNewStudent(Student student[]) { //Add new Student if you put more than 7
            for (int i = 0; i < current; i++) {
                list.add(student[i].getID());
            }
            int newId= 0;
            do {
                System.out.print("Student ID: ");
                newId = scanner.nextInt();
            } while (isIdAvailableForNewStudent(list, newId));
            
            System.out.print("Student Name: ");
            String newName = scanner.nextLine();
            newName = scanner.nextLine();
            System.out.print("Student University: ");
            String newUni = scanner.nextLine();

            student[next] = new Student(newId, newName, newUni);
            current++;
            next++;
    }

    static boolean isIdAvailableForNewStudent(List<Integer> list, int number) { //Check if Id is available
        for(Integer id:list)  {
            for (int i = 0; i < list.size(); i++) {
                if (id == number) {
                    System.out.println("\nInvalid ID!!\n");
                    return true;
                }
            }
        }
        return false;     
    }
    
    static void setNewGrades (Student student[]) { //Look for the id of the student who needs to be graded
        

        int numberOffGrades;
        do {
            System.out.print("Number of grades(min-1): ");
            numberOffGrades = scanner.nextInt();
        } while (numberOffGrades <= 0);

        for (int i = 0; i < numberOffGrades; i++) {
            setGrades(student);
        }
        
    }

    static boolean checkForGrades (Student student[]) { // Returning false if every student has at least one grade
        List<Integer> studentWithouGrades=new ArrayList<Integer>();
        int n = 0;
        for (int i = 0; i <= student.length; i++) {
            n = 0;
            for (int j = 0; j < 10; j++) {
                if (student[i].getGrades(j) != 0.0) {
                    n++;
                }
            }
            if (n == 0) {
                studentWithouGrades.add(student[i].getID());
            }
            if (studentWithouGrades.isEmpty()) {
                return false;
            }
        }
        System.out.println("Students ID without grades: " + studentWithouGrades);
        return true;
    }

    static void setGrades (Student student[]) { // Set a number of grades for any student
        double newGrades;
        int nextGrade = 0;
        int idToFind, studentId;
        do {
            System.out.print("Student ID: ");
            idToFind = scanner.nextInt();
            studentId = isIdAvailableForGrades(list, idToFind);
        } while (studentId == list.size()+1);
        System.out.print("Grade: ");
        newGrades = scanner.nextFloat();
        for (int i = 0; i < student.length; i++) {
                if (studentId == student[i].getID()) {
                    for (int j = 0; j < 10; j++) {
                        if(student[i].getGrades(j) != 0.0) {
                            nextGrade++;
                        }
                    }
                    student[i].setGrades(newGrades, nextGrade);
                }     
        }
    }
    
    static int isIdAvailableForGrades(List<Integer> list, int number) { //Check if Id is available
        
        for(Integer id:list)  {
            for (int i = 0; i < list.size(); i++) {
                if (id == number) {
                    System.out.println("\nValid ID!!\n");
                    return id;
                }
            }
        }
        System.out.println("\nInvalid ID!!\n");
        return list.size()+1;     
    }
}





class Student {
    private int ID;
    private String name;
    private String university;
    double grades[] = new double[10];

    public Student(int ID, String name, String university){
        this.ID = ID;
        this.name = name;
        this.university = university;
    }

    public int getID() {
        return ID;
    }
    public String getName() {
        return name;
    }
    public String getUniversity() {
        return university;
    }
    public void setID(int iD) {
        ID = iD;
    }

    public void setGrades(Double grades, int i) {
        this.grades[i] = grades;
    }
    public double getGrades(int i) {
        return grades[i];
    }
    public void show() {
        System.out.println("Name:         " + getName());
        System.out.println("University:   " + getUniversity());
        System.out.println("ID:           " + getID());
        if (grades != null) {
            for (int i = 0; i < grades.length; i++) {
                if (grades[i] != 0.0){
                DecimalFormat df_obj = new DecimalFormat("#.#");
                df_obj.setRoundingMode(RoundingMode.FLOOR);
                System.out.format("Grade:        " + df_obj.format(grades[i]) + "\n");
            }
        }
        System.out.println("\n");
        } 
        if (grades == null) {
            System.out.println("\nGrades are null");
        }
        
    }
}
