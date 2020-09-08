# DB-project
Bakery cafe menu management system(2020-1 Database course)
Create a Java program using JDBC to insert, update, delete and retrieve information to/from a
MySQL database
- mariaDB
- JDBC
- Java
<br> text기반 ui로 구현

# Requirements
[ DB Schema ]

1. Should have at least 3 tables with each table having at least 3 columns
2. Should have at least 3 0 records inserted for initialization total records for all tables
3. Should include primary key in every table and also foreign key, not null constraints should exist in some tables
4. Tables should be in 3 rd Normal Form (3NF)
5. At least 1 index should be defined on the tables (The primary key(PK) columns have index automatically created, so do not create an index on a PK)
6. 1 view should be defined , and the view should be defined using at least two other tables
[ DB Queries and Program ]
7. All queries (in 8 to 1 4 below) should have parameterized variables
8. Should have at least 1 in terface (menu and user input) and query to insert into 1 table
9. Should have at least 1 interface (menu and user input) and query to update on 1 or 2 tables
10. One of the updates should occur on 2 tables by using transactions



# Schema Diagram

![Schema diagram image](https://user-images.githubusercontent.com/55357130/92497442-51093300-f234-11ea-9182-c5ce7c7ef57d.PNG)

6 tables contain information about product, product type, employee, employee type, orders and customers.


# Function of Code
- '손님 접속'과 '직원 접속' 으로 선택에 따라 서로 다른 기능을 보여줌
- view 구조를 사용해 세트 메뉴(-500) 구성
- jdbc preparedStatement 사용
- transaction 사용해 결제 기능 선택시 결제 내역 및 고객 포인트 자동 update
- 고객 포인트 5% 적립 기능
#### Customer
- 모든 제품 보기
- 특정 타입 제품 보기
- seasonal set menu 보기
#### Employee
- 메뉴 보기
- 메뉴 추가
- 메뉴 수정
- 메뉴 삭제
- 직원 추가 및 삭제
- 직원 정보 수정
- 결제 내역 관리
- 결제(결제시 결제 내역 및 손님 포인트 자동 update)


![Function of code image](https://user-images.githubusercontent.com/55357130/92497939-f45a4800-f234-11ea-907d-2702fde7e22a.PNG)



# Role
- 모든 기능 구현


