# MovieTheaterSystem

### Project Description
This java project acts as a reservation system for movie theater - allows users to add reservations and edit orders.
The program is based on a user login system that keeps track of each customer's orders.

- A login is presented at the beginning of the program, giving access to a specific customer's orders or an admin profile.

#### Customers
- When creating a new order, a customer has the option to choose which auditorium they would like to reserve seats in.
The user then has the option of selecting the row, seats, and types of tickets in their order.
If the seat(s) are unavailable, and algorithm will calculate the best available set of seats (closest to center) in the auditorium and 
will ask the user if they would like to reserve them.
- The user also has the option of removing seats from an order, adding seats from an order, or deleting entire orders.
- The user may also select to view a reciept of their orders.
- The user may also log out and go back to the login menu.

#### Admin
- The admin has the option to generate a formatted report detailing what each auditorium is composed of and how much money is made in ticket
sales.
- The admin may also log out and go back to the login menu.
- The admin may exit the program, rewriting all auditorium files based on orders for each user.

### Information

- The username-password pairs are read from a data file (userdb.dat) and stored in a hashmap, username being the key, and the data 
including the password in addition to any orders the user has.
- Each Auditorium is stored in a text file (A1.txt, A2.txt, A3.txt) and has an arbitrary number of rows and columns ranged A-Z
- Each seat is either empty, Adult, Child, or Senior, represented by a period, A, C, or S, respectively.
