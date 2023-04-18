<h1>Electricity Bill Payment System</h1>

Description:

This is a simple Electricity Bill Payment System designed for the Electricity bill corporation to provide an easy and efficient way for consumers to view and pay their monthly bills. The system allows consumers to view their bills, pay their bills, and view their transaction history. It also allows the administrator to view all the consumers, bills, paid and pending bills, and to delete a consumer.

Database:

The system is designed using a MySQL database. The database has two tables, a Consumer table, and a Bill table. The Consumer table stores information about the consumers, and the Bill table stores information about the bills generated for each consumer. The two tables are related, where a consumer can pay many bills, and a bill is related to one consumer.

The Consumer table has the following columns:

<h3>Consumer-Table</h3>
Consumer_id (Primary key)<br>
first_name<br>
last_name<br>
username (Unique, not null)<br>
password (Not null)<br>
address<br>
mobile_number<br>
email<br>
is_active<br> 

The Bill table has the following columns:

<h3>Bill-Table</h3>
bill_id (Primary key)<br>
consumer_id (Foreign key)<br>
amount <br>
bill_from<br>
bill_to<br>

The Transaction table has the following columns:

<h3>Transaction-Table</h3>
transaction_id (Primary key)<br>
consumer_id (Foreign key)<br>
amount<br>
payment_date<br>

<h2>ER-Diagram</h2><br>

![ER-Diagram](https://user-images.githubusercontent.com/115460959/229427285-79dc16ec-0f74-4a03-88d0-774841cbda8e.png)
