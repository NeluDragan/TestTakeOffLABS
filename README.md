CONDITION

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

You are given a number N and a list of N students. For each student you know their ID, name (a string of maximum 30 characters) and the name of the university they are studying (a string of maximum 10 characters).
You are given a number M and a list of M grades. For each grade you know its value and who it belongs to (student ID). Each student will have at least one grade.
A student’s GPA is defined as the average value among their grades.(rounded down to the first value after the decimal point. E.g.: 3.45 = 3.4).
The entry data can be read directly from the standard input or from an input file.

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

Sample Input
7
1 Mihai UBB
2 George UTCN
100 Maria UPT
13 Elena UPT
5 Andrei UTCN
77 Sergiu UTCN
99 Cristina UPT
10
8.0 77
9.9 100
6.0 77
8.8 100
7.1 77
6.1 1
6.8 2
9.5 13
6.3 5
9.0 99
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

Subtask 1
Print the universities sorted by their average GPA (the average of the students’ grades).
Within a university print the names of the students sorted alphabetically.

Output 1
UPT: Cristina Elena Maria
UTCN: Andrei George Sergiu
UBB: Mihai

Explanation 1
Cristina has only one grade: 9.0.
Elena has only one grade: 9.5.
Maria has 2 grades: 9.9 and 8.8.
Andrei has only one grade: 6.3.
George has only one grade: 6.8.
Sergiu has 3 grades: 8.0, 6.0 and 7.1.
Mihai has only one grade: 6.1.

UPT has 3 students: Cristina, Elena and Maria. The UPT’s average gpa is (9.0 + 9.5 + 9.9+8.8) / 4 = 9.3
UTCN has 3 students: Andrei, George, Sergiu. The UTCN’s average gpa is (6.3+6.8+8.0+6.0+7.1) / 5 = 6.8
UBB has only one student: Mihai. The UBB’s average gpa is 6.1
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

Subtask 2
Your goal is to find the students from each university who receive scholarships. Each university has 2 scholarships to give to their top students.
You should output the students using the same format as Subtask 1.

Output 2
UTCN: Sergiu George
UPT: Elena Maria
UBB: Mihai

Explanation 2
Cristina’s GPA is 9.0.
Elena’s GPA is 9.5.
Maria has 2 grades: 9.9 and 8.8. Her GPA is (9.9+8.8) / 2 = 9.35 which is rounded down to 9.3
Andrei’s GPA is 6.3.
George’s GPA is 6.8.
Sergiu has 3 grades: 8.0, 6.0 and 7.1. His GPA is (8.0+6.0+7.1) / 3 = 7.03 which is rounded down to 7.0
Mihai’s GPA is 6.1.
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

Subtask 3
The goal of this task is to balance the universities’ top GPAs. A university’s top GPA is calculated as the maximum of the students’ GPA.
Balancing the university’ top GPA means that the best university and the worst university are as close as possible. In other words, minimize the difference between the highest and lowest university top GPAs.
You can transfer any number of students from one university to another, but make sure not to leave any university empty.
You should output the students after completing the transfers using the same format as Subtask 1.

Output 3
UTCN: Maria Sergiu George Andrei
UPT: Elena
UBB: Cristina Mihai

Explanation 3
UTCN students’ top grades: 7.0, 6.8, 6.3.
UPT students’ top grades: 9.5, 9.3, 9.0.
UBB students’ top grades: 6.1.
UTCN’s best GPA is 7.0.
UPT’s best GPA is 9.5.
UBB’s best GPA is 6.1.
One possible solution (but not the only one) is transferring Maria from UPT to UTCN and Cristina from UPT to UBB . The universities’ top GPAs become the following:
UTCN’s best GPA is 9.3.
UPT’s best GPA is 9.5.
UBB’s best GPA is 9.0.
The difference between the worst and the best university is 0.5.
