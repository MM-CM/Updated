use project;
Select * from BookInventory;
Select * from Genres;
create table BookInventory(BookID bigint not null primary key, Title varchar(255), Author varchar(255),
ISBN varchar(50), Genre varchar(100), Availability boolean, Quantity int, BookStatus varchar(255), ShelfNum int,
YearPublished Date,  FOREIGN KEY (Genre) REFERENCES Genres(genreName) );

create table Genres(GenreID int auto_increment primary key, genreName varchar(100) unique not null);

SELECT b.BookID, b.Title, b.Author, b.ISBN, b.Quantity, b.Availability, g.genreName FROM BookInventory b
JOIN Genres g ON b.Genre = g.genreName;


create table UserAccounts(MemberID bigint, Username varchar(255), Password varchar(255),
Contact bigint, UserRole varchar(50));


drop table Genres;
drop table bookinventory;