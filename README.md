# Akura Student Management System

How to install and run the software

<b>Database Configuration<b>
1.	Add backup.sql file to MySQL Workbench
2.	Open InteliJ
3.	Open Browse to the project folder wait until gradel build operation is finished.
4.	In project pane “akura->src->main->resources->application.properties”.
5.	In the file application-properties change
                   spring.datasource.password: root to spring.datasource.password: <your password of mysql>
6.	Close file
7.	Open akura->src->main->java->com.akura->controller->authprovider.
8.	Press ctrl+F. type setconnection. There change password:”root” to password:<your password>.
9.	Backup static folder to desktop by copy and pasting “Static” folder under 
a.	[akura-> out-> production->resources->Static]      
10.	Run the project. Wait until the gradel build is finished. Stop the project.
11.	Go to the Desktop and take the copy of static folder and paste in akura outproductionresource
12.	In the copy window click button “OK”
13.	Run the project.
14.	Open the web browser (Chrome). Type “localhost:8080/ui/login”. Press Enter.
15.	In the username text,type “admin” In the password text, type ‘123’ press ‘Login Button’.
