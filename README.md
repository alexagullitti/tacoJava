# tacoJava

--intro-- I spent around 5 hours to complete this project. Between research(3hr), coding(2hr) and uploading/documenting(30min). I had originally completed the coding challenge in C# and I am slightly bummed because I think that application is better and passed all the tests! I was having trouble connecting to the sql database in java, I was using mySQL workbench. I wasn't able to export or copy the db file, so I made a file with the create statement for the table. The search all button does not work as expected, unforntunately I ran out of time this week and wanted to turn in what I had. 

Once you create the database with whichever sql database IDE you chose, you can adjust this string throuhgout the project and it should connect.
Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tacoDB");

While my Java is a little rusty, I have almost 4 years of OOP and relational database experience. I know my technical skills can be converted into whatever language is needed. Thank you for taking the time to look through this project!

--how to use--

When you run the application a windows form will appear. If you click on the 'search all' button you will see all the current rows of delivery data.

To add a delivery- Enter the data into the form and click add. You must have unique first name, that is the primary key. Obviously in a real taco delivery truck situation that would not be realistic, but for the purpose of the exercise it made it easier! If you enter a non unique first name it will not add the delivery. Regardless a window will appear to tell you if the row is added or not. To verify the entry is there, click the 'search all' button and it will refresh the table.

To Delete a delivery- Enter data into any or all of the fields and it will delete the rows containing that data. If you enter Smith into last name, it will delete all rows with the last name of Smith. If you enter last name smith and address 123 it will only delete rows that match both those conditions. To verify the entry is removed, click the 'search all' button and it will refresh the table.

To update a delivery- Enter data, you must enter a first name and it will update that row. To verify the entry is updated, click the 'search all' button and it will refresh the table.
