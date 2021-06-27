# Lab2Project
Main project repository. The aim is to demostrate the work with the git-flow system

Project contains:
- <i>Person</i> class (<i>Person</i>)
- Exception class for the <i>Person</i> class (<i>PersonException</i>)
- Database of drivers (<i>DriverDatabase</i>)
- Exception class for the <i>DriverDatabase</i> class (<i>DatabaseException</i>)

### <i>Person</i>
The class for description of persons, particularly for the drivers.
It has next attributes: 
- name: just string
- ID: any integer and non-negative number
- age: any positive number less or equal than 150
- vehicle categories: some of the A, B, C, D, M or T categories, that picked together in the one string

The functions:
- constructor with all attributes and check for their correctness
- constructor from the <i>Scanner</i> stream
- <i>write</i> function, which prints person's information with the <i>FileWriter</i>
- <i>update</i> function for age changes
- <i>get</i> functions
  
### <i>DriverDatabase</i>
The class for keeping information about vehicles, it is the wrapped Map of ID and list of <i>Vehicles</i>
It has storage, named <i>database</i> and next functions:
- default constructor with the <i>database</i> initialization
- constructor from the <i>Scanner</i> stream
- <i>dump</i> function, which saves the database information in the text file
- <i>add</i> function with match check between <i>Vehicle</i> information and <i>Person</i> information
- <i>get</i> functions

### VehicleLibrary
It is a submodule, library of the <i>Vehicles</i>, which is used there for the work wih different <i>Vehicles</i>.
Additional information can be found on the next link: https://github.com/Ales-san/Lab2Library

### LFS module
It is <i>dump.txt</i> which is tracked by the LFS and keeps the information about all <i>Vehicles</i> and their connections with the drivers.