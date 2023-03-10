import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class App {
    public static Scanner scanner = new Scanner(System.in);
    public static List<Integer> IDlist=new ArrayList<Integer>();
    public static List<String> UNIlist=new ArrayList<String>();
    public static List<String> STUDlist=new ArrayList<String>();
    public static int next=7;
    public static int current = 7;
    public static int number = 0;
    public static DecimalFormat df_obj = new DecimalFormat("#.#");
    public static HashMap<Float, String> univerityMap = new HashMap<Float, String>();
    public static HashMap<Float, String> studentMap = new HashMap<Float, String>();
    public static ArrayList<Float> sortedKeys= new ArrayList<Float>(univerityMap.keySet());
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
            IDlist.add(student[i].getID());
        }

        while (checkForGrades(student)) {
            setNewGrades(student);    
        }
        for (int i = 0; i < student.length; i++) {
            student[i].show();
        }

        while (true) {
            System.out.println("1. Add new student.");
            System.out.println("2. Show students info.");
            System.out.println("3. Add new grade.");
            System.out.println("4. Show all university and their student.");
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
                    IDlist.add(student[number-1].getID());
                    student[number-1].setGrades(0.0, 0);
                    while (checkForGrades(student)) {
                        setNewGrades(student);    
                    }
                    break;

                case 2:
                    for (int i = 0; i < student.length; i++) {
                        student[i].show();
                    }
                    break;

                case 3:
                    setNewGrades(student);
                    break;

                case 4:
                    lookForAllUnive(student);
                    sortbykey(student);
                    break;

                case 5:
                    lookForAllUnive(student);
                    System.out.println("\nAfter calculate GPA.\n");
                    studentGPA(student);
                    break;
            
                default:
                    System.out.println("Inncorect option!!");
                    break;
            }
        }
    }

    static void addNewStudent(Student student[]) { //Add new Student if you put more than 7
            for (int i = 0; i < current; i++) {
                IDlist.add(student[i].getID());
            }
            int newId= 0;
            do {
                System.out.print("Student ID: ");
                newId = scanner.nextInt();
            } while (isIdAvailableForNewStudent(IDlist, newId));
            
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
        for (int i = 0; i < student.length; i++) {
            n = 0;
            for (int j = 0; j < 10; j++) {
                if (student[i].getGrades(j) != 0) {
                    n++;
                }
            }
            if (n == 0) {
                studentWithouGrades.add(student[i].getID());
            }
        }
        if (studentWithouGrades.isEmpty()) {
            return false;
        }
        System.out.println("\nStudents ID without grades: " + studentWithouGrades + "\n");
        return true;
    }

    static void setGrades (Student student[]) { // Set a number of grades for any student
        double newGrades;
        int nextGrade = 0;
        int idToFind, studentId;
        do {
            System.out.print("Student ID: ");
            idToFind = scanner.nextInt();
            studentId = isIdAvailableForGrades(idToFind);
        } while (studentId == IDlist.size()+1);
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
    
    static int isIdAvailableForGrades(int number) { //Check if Id is available
        
        for(Integer id: IDlist)  {
            for (int i = 0; i < IDlist.size(); i++) {
                if (id == number) {
                    System.out.println("\nValid ID!!\n");
                    return id;
                }
            }
        }
        System.out.println("\nInvalid ID!!\n");
        return IDlist.size()+1;     
    }

    public static void lookForAllUnive (Student student[]) { //Creating a list of every university without duplicat it 
        for (int i = 0; i < number; i++) {
            if (!UNIlist.contains(student[i].getUniversity())) {
                UNIlist.add(student[i].getUniversity());
            }
        }
        int numberOffGrades;
        float grades;
        for(int i=0;i<UNIlist.size();i++){
            numberOffGrades = 0;
            grades = 0;
            for (int j = 0; j < student.length; j++) {
                if (UNIlist.get(i).equals(student[j].getUniversity())) {
                    for (int k = 0; k < 10; k++) {
                        if (student[j].getGrades(k) != 0.0){
                        numberOffGrades++;
                        grades += student[j].getGrades(k);
                        }
                    }
                }
            }
            grades = grades / numberOffGrades;
            df_obj.setRoundingMode(RoundingMode.FLOOR);
            univerityMap.put(grades, UNIlist.get(i));   
        }
        
    }
    
    public static void sortbykey(Student student[]) { //Sorting and displaying evrey Student from one university
            ArrayList<Float> sortedKeys= new ArrayList<Float>(univerityMap.keySet());
     
            Collections.sort(sortedKeys);
            Collections.reverse(sortedKeys);
    
            System.out.println("\n");
            // Display the TreeMap which is naturally sorted
            for (Float x : sortedKeys) {
                System.out.println("University: " + univerityMap.get(x));
                for (int i = 0; i < student.length; i++) {
                    if (univerityMap.get(x).equals(student[i].getUniversity())) {
                        System.out.println("    " + student[i].getName());
                    }
                }
            }
            System.out.println("\n");
    }


    public static void studentGPA(Student student[]) { //Sorting and displaying evrey Student from one university
        for (int i = 0; i < number; i++) {
                STUDlist.add(student[i].getName());
        }
        int numberOffGrades;
        float grades;
        for(int i=0;i<STUDlist.size();i++){
            numberOffGrades = 0;
            grades = 0;
            for (int j = 0; j < student.length; j++) {
                if (STUDlist.get(i).equals(student[j].getName())) {
                    for (int k = 0; k < 10; k++) {
                        if (student[j].getGrades(k) != 0.0){
                        numberOffGrades++;
                        grades += student[j].getGrades(k);
                        }
                    }

                }
            }
            grades = grades / numberOffGrades;
            df_obj.setRoundingMode(RoundingMode.FLOOR);
            studentMap.put(grades, STUDlist.get(i));   
        }
        ArrayList<Float> sortedKeysUNI= new ArrayList<Float>(univerityMap.keySet());
        ArrayList<Float> sortedKeysSTU= new ArrayList<Float>(studentMap.keySet());
     
        Collections.sort(sortedKeysUNI);
        Collections.sort(sortedKeysSTU);
        Collections.reverse(sortedKeysSTU);
        
        System.out.println("\n");
            // Display the TreeMap which is naturally sorted
            for (Float x : sortedKeysUNI) {
                System.out.println("University: " + univerityMap.get(x));
                int n = 0;
                for (Float y : sortedKeysSTU) {
                for (int i = 0; i < student.length; i++) {
                        if ((studentMap.get(y).equals(student[i].getName()))) {
                            if (univerityMap.get(x).equals(student[i].getUniversity())) {
                                if (n < 2) {
                                    System.out.println("    " + studentMap.get(y));
                                    n++;
                                }
                            }
                        }
                    }
                }
            System.out.println("\n");
            }
}}






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

