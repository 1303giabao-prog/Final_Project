# Gym Management System

## Project Description
This project is a database-connected Gym Management System created in Java.
The purpose of the program is to help manage gym member information in a more organized and efficient way; Instead of storing information manually, the system allows the user to save and manage member data through a command-line application connected to a database.

The program is designed to perform the main CRUD operations, which means the user can create, read, update, and delete gym member records, this makes it easier to keep track of gym members and their membership information.

## Problem the Project Solves
Managing gym member records manually can be confusing and time-consuming, especially when there are many members to keep track of, this project helps solve that problem by storing the data in a database and allowing the user to interact with it through a Java application. 
This makes the information easier to organize, search, update, and remove when needed.

## Main Features
- Add new gym members
- View existing member records
- Update member information
- Delete member records
- Store data in a database
- Handle errors using custom exceptions

## Technologies Used
- Java language
- Eclipse IDE
- MariaDB

## How the System Works
The user interacts with the application through a command-line menu, based on the selected option, the program connects to the database and performs the requested operation;
For example, the user can add a new member, view stored members, update membership details, or delete a member record, the application also uses exception handling to prevent crashes and display meaningful error messages.

## OOP Concepts Used
This project uses Object-Oriented Programming concepts such as classes, objects, inheritance, interfaces or abstract classes, and custom exceptions, these to help make the system more organized, modular, and easier to maintain.

## Exception Handling
Custom exceptions were created to handle specific error cases in the program, these exceptions help make the application more user-friendly and reliable by showing clear messages when something goes wrong.

Examples include:
- InvalidMembershipException
- MemberNotFoundException
- DatabaseConnectionException
- InvalidMenuChoiceException

## Group Member Contributions
- Member 1: Database connection and CRUD operations
- Member 2: Architecture, inheritance, interfaces, and abstract classes
- Member 3: CLI design and input validation
- Member 4: Exception handling, README, project coordination, final packaging, and presentation sections

## How to Run the Program
1. Open the project in Eclipse
2. Make sure the database is set up and running
3. Run the main Java file
4. Use the command-line menu to select the desired option

## Final Notes
This project helped us apply Java programming, database integration, and Object-Oriented Programming concepts in a practical way, the project also shows how a real-world management system can be built using a database and a structured Java application.
