# truextend-problem2

Given each student has a geolocation lat/lon point, the following Java Project determines which students are physically in any classroom.

# Assumptions
 
Each classroom has a square shape of 20m X 20m and none of the classrooms intersect.
Students are dimensionless outside of their latitude / longitude point
Height is not a concern for either the student or the classroom
It doesn’t matter which student was in which classroom, we only care about the list of students found
This is intended to be performed in memory where you don’t have the usage of a database of some sort. 
Classrooms don't have declination.

To test it follow the steps:
- Register classrooms through POST /classrooms
- If you want to verify registered classrooms, use GET/classrooms
- Register students through POST /students
- If you want to verify registered students, use GET/students
- Obtain which students are physically in any classroom, use GET /students/classrooms
- To test again, you have to restart the application, because all data is being saved in memory.
