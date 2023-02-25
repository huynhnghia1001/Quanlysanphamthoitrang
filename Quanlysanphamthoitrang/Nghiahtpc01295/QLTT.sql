CREATE DATABASE QLTT
USE QLTT 

CREATE TABLE Accounts (
username nvarchar(50) not null primary key ,
password nvarchar(50)  null ,
role nvarchar(50));

CREATE TABLE Products(
MaSP nvarchar(50) not null primary key ,
TenSP nvarchar(50) null ,
Gia float null ,
Hinh nvarchar(15) null ,
Soluong int null 
);

CREATE TABLE ProductDetails(
ID int identity(1,1) primary key ,
MaSP nvarchar(50) foreign key references Products(MaSP),
Mau nvarchar(50) null ,
KichThuoc nvarchar(50) null,
ThongTin nvarchar(50) null
);







	/*-------Accounts--------*/

	INSERT INTO Accounts (username,password,role) VALUES ('admin','admin','Chu');
	INSERT INTO Accounts (username,password,role) VALUES ('nv01','123','NhanVien');

	
	
	/*-------Products--------*/
	
	/*Ao*/
	INSERT INTO Products(MaSP,TenSP,Gia,Hinh,SoLuong) VALUES ('SP01',N'Ao NOW',350000,'b.jpg',2);
	INSERT INTO Products(MaSP,TenSP,Gia,Hinh,SoLuong) VALUES ('SP02',N'Ao SaiGon',350000,'a.jpg',1);
	INSERT INTO Products(MaSP,TenSP,Gia,Hinh,SoLuong) VALUES ('SP03',N'Ao CanTho',350000,'c.jpg',2);
	INSERT INTO Products(MaSP,TenSP,Gia,Hinh,SoLuong) VALUES ('SP04',N'Ao BacLieu',350000,'cute.jpg',2);
	INSERT INTO Products(MaSP,TenSP,Gia,Hinh,SoLuong) VALUES ('SP05',N'Ao CaMau',350000,'e.jpg',1);

	/*Quan*/
	INSERT INTO Products(MaSP,TenSP,Gia,Hinh,SoLuong) VALUES ('SP06',N'Quan NOW',350000,'ee.jpg',2);
	INSERT INTO Products(MaSP,TenSP,Gia,Hinh,SoLuong) VALUES ('SP07',N'Quan SaiGon',350000,'k.jpg',1);
	INSERT INTO Products(MaSP,TenSP,Gia,Hinh,SoLuong) VALUES ('SP08',N'Quan CanTho',350000,'mm.jpg',2);
	INSERT INTO Products(MaSP,TenSP,Gia,Hinh,SoLuong) VALUES ('SP09',N'Quan BacLieu',350000,'CoVi_19_1.jpg',2);

	INSERT INTO ProductDetails(MaSP,Mau,KichThuoc,ThongTin) VALUES ('SP01',N'Trắng','M',N'Được dệt từ sợi cotton 100%');
	INSERT INTO ProductDetails(MaSP,Mau,KichThuoc,ThongTin) VALUES ('SP02',N'Đen','L',N'Được dệt từ sợi cotton 100%');
	INSERT INTO ProductDetails(MaSP,Mau,KichThuoc,ThongTin) VALUES ('SP03',N'Trắng','M',N'Được dệt từ sợi cotton 100%');
	INSERT INTO ProductDetails(MaSP,Mau,KichThuoc,ThongTin) VALUES ('SP04',N'Trắng','M',N'Được dệt từ sợi cotton 100%');
	INSERT INTO ProductDetails(MaSP,Mau,KichThuoc,ThongTin) VALUES ('SP05',N'Đen','S',N'Được dệt từ sợi cotton 100%');

	INSERT INTO ProductDetails(MaSP,Mau,KichThuoc,ThongTin) VALUES ('SP06',N'Trắng','M',N'Được dệt từ sợi cotton 100%');
	INSERT INTO ProductDetails(MaSP,Mau,KichThuoc,ThongTin) VALUES ('SP07',N'Đen','L',N'Được dệt từ sợi cotton 100%');
	INSERT INTO ProductDetails(MaSP,Mau,KichThuoc,ThongTin) VALUES ('SP08',N'Trắng','M',N'Được dệt từ sợi cotton 100%');
	INSERT INTO ProductDetails(MaSP,Mau,KichThuoc,ThongTin) VALUES ('SP09',N'Trắng','M',N'Được dệt từ sợi cotton 100%');
	
	



