/*  
create table: table에 대한 기본적인 정보
 */

/*제품의 분류 관련 테이블 : 속성 2개*/
create table ptypes(
type int primary key,
typeName varchar(20) not null );

/*제품 테이블 속성: 5개*/
create table products(
productCode int primary key,		/*제품의 고유 번호*/
type int,
productName varchar(20) not null ,
price int,
sellnow varchar(3),
FOREIGN KEY (type) REFERENCES ptypes (type) ON UPDATE CASCADE ); 

/*고객 정보 테이블*/
create table customers(
customerCode int primary key,	/*손님의 고유 코드: 전화번호 8자리로 구성*/
customerName varchar(20) not null,
money int,
point int,
visited DATE );

/*직원의 직위 정보 테이블*/
create table jobs(
jobCode int primary key,
jobName varchar(20) not null,
fullorpart varchar(10) );

/*직원 정보 테이블*/
create table employees(
employeeCode varchar(20) primary key,	/*직원의 고유 코드: 직원이 설정한 아이디로 구성*/
passWord varchar(20),
employeeName varchar(20) not null ,
phonenumber varchar(15),
startdate DATE,
jobCode int,
foreign key (jobCode) references jobs(jobCode) );

/*판매 내역 테이블*/
create table orders(
orderCode int(10) NOT NULL AUTO_INCREMENT PRIMARY KEY,	/*판매 번호 자동으로 증가하는 값*/
productCode int,		/*구매한 제품*/
customerCode int,		/*구매 손님*/	
employeeCode varchar(20),		/*판매 직원*/
orderDate DATE,	/*판매 일자*/
foreign key (productCode) references products(productCode) ON DELETE CASCADE,
foreign key (customerCode) references customers(customerCode),
foreign key (employeeCode) references employees(employeeCode) );
	



/*  
insert into table: 각 테이블에 값 넣기
 */
INSERT INTO ptypes VALUES
(1, 'bread'),
(2, 'coffee'),
(3, 'tea');



INSERT INTO products VALUES
(101, 1, 'white bread', 3500, 'y'),
(102, 1, 'pretzel', 2000, 'n'),
(103, 1, 'french bread', 2500, 'y'),
(104, 1, 'croissant', 2000, 'y'),
(105, 2, 'americano', 3000, 'y'),
(106, 2, 'cafelatte', 3500, 'y'),
(107, 2, 'espresso', 2500, 'y'),
(108, 3, 'greentea', 3000, 'n'),
(109, 2, 'cafemocha', 3500, 'y'),
(110, 1, 'bagel', 1500, 'y'),
(111, 3, 'camomile tea', 3000, 'y'),
(112, 3, 'black tea', 3500, 'y');


INSERT INTO customers VALUES
(12345678, 'Jane', 100000, 5000, '2020/06/19' ),
(87654321, 'Kate', 83000, 4150, '2020/06/01'),
(11112222, 'John', 27000, 0, '2020/04/14'),
(82829292, 'Emily', 12000, 600, '2020/02/13'),
(79427942, 'Tom', 90000, 4500, '2019/10/31');



INSERT INTO jobs VALUES
(1, 'owner', 'fulltime'),
(2, 'baker', 'fulltime'),
(3, 'part timer', 'parttime');


INSERT INTO employees VALUES
('suzy1023', 'suzy1023', 'suzy', '01032323232', '2019/10/26', 1),
('happy77', 'Tommy6789', 'Tommy', '01023456789', '2019/12/21', 2),
('ohio2929', 'amy3939', ' amy', '01029293939', '2020/01/10', 3),
('rainbow10', 'anne4050', 'anne', '01020304050', '2020/03/04', 3);


INSERT INTO orders (productCode, customerCode, employeeCode, orderDate) VALUES
(102, 79427942,  'suzy1023',  '2019/10/31'),
(102, 11112222, 'suzy1023', '2019/10/31'),
(107, 79427942,  'suzy1023', '2019/11/13'),
(101, 11112222, 'happy77', '2019/12/22'),
(101, 12345678, 'happy77', '2020/01/21'),
(104, 82829292, 'ohio2929', '2020/02/11'),
(102, 82829292, 'happy77',  '2020/02/13'),
(102, 79427942, 'ohio2929',  '2020/03/14'),
(108, 11112222, 'rainbow10', '2020/04/14'),
(109, 87654321, 'suzy1023',  '2020/06/01'),
(105, 12345678, 'suzy1023', '2020/06/19' );


/*product table의 productName에 인덱스 추가*/
CREATE INDEX pro_idx ON products(productName);

/*뷰 생성하기 - 직원의 직무와 정직원/아르바이트 여부를 나타내는 뷰*/
CREATE VIEW employeeJob AS 
SELECT * FROM employees NATURAL JOIN jobs;


/*뷰 생성하기 - 세트메뉴의 종류의 정보를 담은 뷰- 커피와 빵만 세트메뉴 구성 가능 & 가격은 -500 & 현재 판매하는 상품만  포함*/
CREATE VIEW setMenu AS
SELECT CONCAT(A.productNAme,"-", B.productName, "  set") as setName, (A.price + B.price - 500) as setPrice FROM products as A, products as B
WHERE A.type = 1 AND B.type =2 AND A.sellnow = 'y' AND B.sellnow = 'y';










	