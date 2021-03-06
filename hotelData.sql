USE [master]
GO
/****** Object:  Database [HotelBooking]    Script Date: 07/11/2020 4:48:28 PM ******/
CREATE DATABASE [HotelBooking]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'HotelBooking', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.SQLEXPRESS\MSSQL\DATA\HotelBooking.mdf' , SIZE = 3072KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'HotelBooking_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.SQLEXPRESS\MSSQL\DATA\HotelBooking_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [HotelBooking] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [HotelBooking].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [HotelBooking] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [HotelBooking] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [HotelBooking] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [HotelBooking] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [HotelBooking] SET ARITHABORT OFF 
GO
ALTER DATABASE [HotelBooking] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [HotelBooking] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [HotelBooking] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [HotelBooking] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [HotelBooking] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [HotelBooking] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [HotelBooking] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [HotelBooking] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [HotelBooking] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [HotelBooking] SET  DISABLE_BROKER 
GO
ALTER DATABASE [HotelBooking] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [HotelBooking] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [HotelBooking] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [HotelBooking] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [HotelBooking] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [HotelBooking] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [HotelBooking] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [HotelBooking] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [HotelBooking] SET  MULTI_USER 
GO
ALTER DATABASE [HotelBooking] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [HotelBooking] SET DB_CHAINING OFF 
GO
ALTER DATABASE [HotelBooking] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [HotelBooking] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [HotelBooking] SET DELAYED_DURABILITY = DISABLED 
GO
USE [HotelBooking]
GO
/****** Object:  Table [dbo].[tblDisCount]    Script Date: 07/11/2020 4:48:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tblDisCount](
	[codeID] [varchar](50) NOT NULL,
	[codeName] [varchar](50) NULL,
	[discountPercen] [int] NULL,
	[dateCreate] [datetime] NULL,
 CONSTRAINT [PK_tblDisCount] PRIMARY KEY CLUSTERED 
(
	[codeID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tblHotel]    Script Date: 07/11/2020 4:48:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tblHotel](
	[hotelID] [int] IDENTITY(1,1) NOT NULL,
	[hotelName] [nvarchar](50) NULL,
	[address] [nvarchar](100) NULL,
	[image] [varchar](50) NULL,
	[available] [bit] NULL,
 CONSTRAINT [PK_tblHotel] PRIMARY KEY CLUSTERED 
(
	[hotelID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tblOrder]    Script Date: 07/11/2020 4:48:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tblOrder](
	[orderID] [uniqueidentifier] NOT NULL CONSTRAINT [DF_tblOrder_orderID]  DEFAULT (newid()),
	[userID] [varchar](50) NULL,
	[checkout] [datetime] NULL,
	[price] [float] NULL,
 CONSTRAINT [PK_tblOrder] PRIMARY KEY CLUSTERED 
(
	[orderID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tblOrderDetail]    Script Date: 07/11/2020 4:48:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tblOrderDetail](
	[orderDetailID] [uniqueidentifier] NOT NULL CONSTRAINT [DF_tblOrderDetail_orderDetailID]  DEFAULT (newid()),
	[orderID] [uniqueidentifier] NULL,
	[roomID] [varchar](10) NULL,
	[quantity] [int] NULL,
	[checkinDate] [datetime] NULL,
	[checkoutDate] [datetime] NULL,
 CONSTRAINT [PK_tblOrderDetail] PRIMARY KEY CLUSTERED 
(
	[orderDetailID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tblRoom]    Script Date: 07/11/2020 4:48:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tblRoom](
	[roomID] [varchar](10) NOT NULL,
	[typeID] [varchar](10) NULL,
	[price] [float] NULL,
	[quantity] [int] NULL,
	[hotelID] [int] NULL,
	[status] [bit] NULL,
 CONSTRAINT [PK_tblRoom] PRIMARY KEY CLUSTERED 
(
	[roomID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tblRoomType]    Script Date: 07/11/2020 4:48:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tblRoomType](
	[typeID] [varchar](10) NOT NULL,
	[typeName] [varchar](100) NULL,
 CONSTRAINT [PK_tblRoomType] PRIMARY KEY CLUSTERED 
(
	[typeID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tblUser]    Script Date: 07/11/2020 4:48:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tblUser](
	[userId] [varchar](50) NOT NULL,
	[password] [varchar](300) NULL,
	[userName] [varchar](50) NULL,
	[status] [bit] NULL,
	[role] [varchar](10) NULL,
	[phone] [varchar](15) NULL,
	[address] [varchar](100) NULL,
	[createDate] [datetime] NULL,
 CONSTRAINT [PK_tblUser] PRIMARY KEY CLUSTERED 
(
	[userId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[tblDisCount] ([codeID], [codeName], [discountPercen], [dateCreate]) VALUES (N'123', N'12312313', 12, CAST(N'2020-11-07 02:45:42.917' AS DateTime))
INSERT [dbo].[tblDisCount] ([codeID], [codeName], [discountPercen], [dateCreate]) VALUES (N'HIEU', N'sn hieu', 15, CAST(N'2020-11-07 08:18:33.650' AS DateTime))
SET IDENTITY_INSERT [dbo].[tblHotel] ON 

INSERT [dbo].[tblHotel] ([hotelID], [hotelName], [address], [image], [available]) VALUES (1, N'Tulemar Bungalows & Villas', N'Manuel Antonio, Costa Rica', N'./img/TulemarBungalowsandVillas.jpg', 1)
INSERT [dbo].[tblHotel] ([hotelID], [hotelName], [address], [image], [available]) VALUES (2, N'Hotel Belvedere', N'Riccione, Italy', N'./img/HotelBelvedere.jpg', 1)
INSERT [dbo].[tblHotel] ([hotelID], [hotelName], [address], [image], [available]) VALUES (3, N'Viroth''s Hotel', N'Siem Reap, Cambodia', N'./img/VirothsHotel.jpg', 1)
INSERT [dbo].[tblHotel] ([hotelID], [hotelName], [address], [image], [available]) VALUES (4, N'Kenting Amanda Hotel', N'Hengchung, Taiwan', N'./img/KentingAmandaHotel.jpg', 1)
INSERT [dbo].[tblHotel] ([hotelID], [hotelName], [address], [image], [available]) VALUES (5, N'Hotel Alpin Spa Tuxerhof', N'Tux, Austria', N'./img/HotelAlpinSpaTuxerhof.jpg', 1)
INSERT [dbo].[tblHotel] ([hotelID], [hotelName], [address], [image], [available]) VALUES (6, N'French Quarter Inn ', N'Charleston, United States', N'./img/FrenchQuarterInn.jpg', 1)
INSERT [dbo].[tblHotel] ([hotelID], [hotelName], [address], [image], [available]) VALUES (7, N'The Resort at Pedregal', N'Cabo San Lucas, Mexico', N'./img/TheResortatPedregal.jpg', 1)
INSERT [dbo].[tblHotel] ([hotelID], [hotelName], [address], [image], [available]) VALUES (8, N'Belmond Palacio Nazarenas', N'Cusco, Peru', N'./img/BelmondPalacioNazarenas.jpg', 1)
INSERT [dbo].[tblHotel] ([hotelID], [hotelName], [address], [image], [available]) VALUES (9, N'Kayakapi Premium Caves Cappadocia', N'Urgup, Turkey', N'./img/KayakapiPremiumCavesCappadocia.jpg', 1)
INSERT [dbo].[tblHotel] ([hotelID], [hotelName], [address], [image], [available]) VALUES (10, N'Hanoi La Siesta Hotel & Spa', N'Hanoi, Vietnam', N'./img/HanoiLaSiestaHotel&Spa.jpg', 1)
SET IDENTITY_INSERT [dbo].[tblHotel] OFF
INSERT [dbo].[tblOrder] ([orderID], [userID], [checkout], [price]) VALUES (N'82c2136f-9a2c-4df0-bcb8-132980f0d314', N'phuong', CAST(N'2020-11-07 01:27:12.283' AS DateTime), 17)
INSERT [dbo].[tblOrder] ([orderID], [userID], [checkout], [price]) VALUES (N'790ac768-a901-48a4-9ca3-1e909a050f22', N'phuong', CAST(N'2020-11-07 01:09:30.757' AS DateTime), 2)
INSERT [dbo].[tblOrder] ([orderID], [userID], [checkout], [price]) VALUES (N'431a8835-96a5-47cf-8657-37e59fa0409e', N'phuong', CAST(N'2020-11-07 01:06:15.077' AS DateTime), 300)
INSERT [dbo].[tblOrder] ([orderID], [userID], [checkout], [price]) VALUES (N'1c4c748e-87c0-4faa-a3ae-59574cb26903', N'phuong', CAST(N'2020-10-29 00:00:00.000' AS DateTime), NULL)
INSERT [dbo].[tblOrder] ([orderID], [userID], [checkout], [price]) VALUES (N'42d643ee-65a2-464e-b4dc-5b5022c84fe7', N'tram', CAST(N'2020-10-11 00:00:00.000' AS DateTime), 1000)
INSERT [dbo].[tblOrder] ([orderID], [userID], [checkout], [price]) VALUES (N'785b56ed-cc6e-4173-8cf5-82e8862fc5d5', N'phuong', CAST(N'2020-11-07 08:42:01.633' AS DateTime), 1)
INSERT [dbo].[tblOrder] ([orderID], [userID], [checkout], [price]) VALUES (N'e5e104c3-ac61-48d2-bc06-998f6b68ca31', N'phuong', CAST(N'2020-11-07 08:40:27.237' AS DateTime), 2)
INSERT [dbo].[tblOrder] ([orderID], [userID], [checkout], [price]) VALUES (N'7cd1d74f-a599-4473-9e3d-a655fb38178e', N'phuong', CAST(N'2020-11-07 01:55:19.137' AS DateTime), 1)
INSERT [dbo].[tblOrder] ([orderID], [userID], [checkout], [price]) VALUES (N'a83eb3eb-68ad-4e28-8f4e-af1c8595c245', N'phuong', CAST(N'2020-11-07 01:43:12.733' AS DateTime), 5)
INSERT [dbo].[tblOrder] ([orderID], [userID], [checkout], [price]) VALUES (N'ba08d55d-48e8-42d9-98fe-f24fffb7a002', N'phuong', CAST(N'2020-11-07 00:47:03.763' AS DateTime), 100)
INSERT [dbo].[tblOrderDetail] ([orderDetailID], [orderID], [roomID], [quantity], [checkinDate], [checkoutDate]) VALUES (N'edd44d38-87d8-41cd-995a-578f8e2ce951', N'a83eb3eb-68ad-4e28-8f4e-af1c8595c245', N'R01', 3, CAST(N'2020-11-07 00:00:00.000' AS DateTime), CAST(N'2020-11-07 00:00:00.000' AS DateTime))
INSERT [dbo].[tblOrderDetail] ([orderDetailID], [orderID], [roomID], [quantity], [checkinDate], [checkoutDate]) VALUES (N'266aedaf-ca12-4ef8-8084-94746783e13c', N'82c2136f-9a2c-4df0-bcb8-132980f0d314', N'R02', 1, CAST(N'2020-11-07 00:00:00.000' AS DateTime), CAST(N'2020-11-13 00:00:00.000' AS DateTime))
INSERT [dbo].[tblOrderDetail] ([orderDetailID], [orderID], [roomID], [quantity], [checkinDate], [checkoutDate]) VALUES (N'42ce3f1e-0ba0-465b-b7af-bb5dfd808ed6', N'785b56ed-cc6e-4173-8cf5-82e8862fc5d5', N'R01', 1, CAST(N'2020-11-07 00:00:00.000' AS DateTime), CAST(N'2020-11-07 00:00:00.000' AS DateTime))
INSERT [dbo].[tblOrderDetail] ([orderDetailID], [orderID], [roomID], [quantity], [checkinDate], [checkoutDate]) VALUES (N'eed0073e-4d78-4657-96dd-c1198ae039d5', N'1c4c748e-87c0-4faa-a3ae-59574cb26903', N'R03', 2, CAST(N'2020-10-30 00:00:00.000' AS DateTime), CAST(N'2020-11-19 00:00:00.000' AS DateTime))
INSERT [dbo].[tblOrderDetail] ([orderDetailID], [orderID], [roomID], [quantity], [checkinDate], [checkoutDate]) VALUES (N'808ae7c6-ecad-4d0a-a433-c1cc6cc7a354', N'7cd1d74f-a599-4473-9e3d-a655fb38178e', N'R01', 1, CAST(N'2020-11-07 00:00:00.000' AS DateTime), CAST(N'2020-11-07 00:00:00.000' AS DateTime))
INSERT [dbo].[tblOrderDetail] ([orderDetailID], [orderID], [roomID], [quantity], [checkinDate], [checkoutDate]) VALUES (N'c0c750f4-748f-41f5-8e3b-d3a45d89af0a', N'82c2136f-9a2c-4df0-bcb8-132980f0d314', N'R01', 3, CAST(N'2020-11-07 00:00:00.000' AS DateTime), CAST(N'2020-11-07 00:00:00.000' AS DateTime))
INSERT [dbo].[tblOrderDetail] ([orderDetailID], [orderID], [roomID], [quantity], [checkinDate], [checkoutDate]) VALUES (N'd3ae1b65-de9c-4b9c-9ae6-f1ec1e7d0e5f', N'a83eb3eb-68ad-4e28-8f4e-af1c8595c245', N'R02', 1, CAST(N'2020-11-07 00:00:00.000' AS DateTime), CAST(N'2020-11-07 00:00:00.000' AS DateTime))
INSERT [dbo].[tblOrderDetail] ([orderDetailID], [orderID], [roomID], [quantity], [checkinDate], [checkoutDate]) VALUES (N'0170a697-5fca-4a19-bb19-f21eec4a62e7', N'e5e104c3-ac61-48d2-bc06-998f6b68ca31', N'R02', 1, CAST(N'2020-11-07 00:00:00.000' AS DateTime), CAST(N'2020-11-07 00:00:00.000' AS DateTime))
INSERT [dbo].[tblRoom] ([roomID], [typeID], [price], [quantity], [hotelID], [status]) VALUES (N'R01', N'V1_1S', 1, 10, 1, 1)
INSERT [dbo].[tblRoom] ([roomID], [typeID], [price], [quantity], [hotelID], [status]) VALUES (N'R02', N'V2_1S', 2, 5, 4, 1)
INSERT [dbo].[tblRoom] ([roomID], [typeID], [price], [quantity], [hotelID], [status]) VALUES (N'R03', N'V1_2S', 1, 3, 2, 1)
INSERT [dbo].[tblRoom] ([roomID], [typeID], [price], [quantity], [hotelID], [status]) VALUES (N'R04', N'V2_4S', 3, 2, 10, 1)
INSERT [dbo].[tblRoom] ([roomID], [typeID], [price], [quantity], [hotelID], [status]) VALUES (N'R05', N'V2_1S', 5, 2, 1, 1)
INSERT [dbo].[tblRoom] ([roomID], [typeID], [price], [quantity], [hotelID], [status]) VALUES (N'R06', N'V1_1S', 1, 5, 4, 1)
INSERT [dbo].[tblRoom] ([roomID], [typeID], [price], [quantity], [hotelID], [status]) VALUES (N'R07', N'V2_4S', 4, 5, 1, 1)
INSERT [dbo].[tblRoomType] ([typeID], [typeName]) VALUES (N'10S', N'Phong nhieu nguoi')
INSERT [dbo].[tblRoomType] ([typeID], [typeName]) VALUES (N'V1_1S', N'Phong don_VIP')
INSERT [dbo].[tblRoomType] ([typeID], [typeName]) VALUES (N'V1_2S', N'Phong doi-VIP')
INSERT [dbo].[tblRoomType] ([typeID], [typeName]) VALUES (N'V1_4S', N'Phong gia dinh_VIP')
INSERT [dbo].[tblRoomType] ([typeID], [typeName]) VALUES (N'V2_1S', N'Phong don')
INSERT [dbo].[tblRoomType] ([typeID], [typeName]) VALUES (N'V2_2S', N'Phong doi')
INSERT [dbo].[tblRoomType] ([typeID], [typeName]) VALUES (N'V2_4S', N'Phong gia dinh')
INSERT [dbo].[tblUser] ([userId], [password], [userName], [status], [role], [phone], [address], [createDate]) VALUES (N'hieu', N'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', N'Hieu Nguyen', 1, N'AD', N'0123455678', N'asd', CAST(N'2020-10-20 00:00:00.000' AS DateTime))
INSERT [dbo].[tblUser] ([userId], [password], [userName], [status], [role], [phone], [address], [createDate]) VALUES (N'hieu@gmail.com', N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', N'Hieu Hieu', 1, N'U', N'1234-456-789', N'c1123', CAST(N'2020-11-03 13:25:27.573' AS DateTime))
INSERT [dbo].[tblUser] ([userId], [password], [userName], [status], [role], [phone], [address], [createDate]) VALUES (N'hieu03@gmail.com', N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', N'hieu', 1, N'U', N'1234-456-789', N'c1123', CAST(N'2020-11-07 08:26:18.857' AS DateTime))
INSERT [dbo].[tblUser] ([userId], [password], [userName], [status], [role], [phone], [address], [createDate]) VALUES (N'phuong', N'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', N'Phuong Pham', 1, N'U', N'123123123', N'123', CAST(N'2020-10-20 00:00:00.000' AS DateTime))
INSERT [dbo].[tblUser] ([userId], [password], [userName], [status], [role], [phone], [address], [createDate]) VALUES (N'tram', N'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', N'Tram Dao', 0, N'U', N'2131231', N'23', CAST(N'2020-10-20 00:00:00.000' AS DateTime))
ALTER TABLE [dbo].[tblOrder]  WITH CHECK ADD  CONSTRAINT [FK_tblOrder_tblUser] FOREIGN KEY([userID])
REFERENCES [dbo].[tblUser] ([userId])
GO
ALTER TABLE [dbo].[tblOrder] CHECK CONSTRAINT [FK_tblOrder_tblUser]
GO
ALTER TABLE [dbo].[tblOrderDetail]  WITH CHECK ADD  CONSTRAINT [FK_tblOrderDetail_tblOrder] FOREIGN KEY([orderID])
REFERENCES [dbo].[tblOrder] ([orderID])
GO
ALTER TABLE [dbo].[tblOrderDetail] CHECK CONSTRAINT [FK_tblOrderDetail_tblOrder]
GO
ALTER TABLE [dbo].[tblOrderDetail]  WITH CHECK ADD  CONSTRAINT [FK_tblOrderDetail_tblRoom] FOREIGN KEY([roomID])
REFERENCES [dbo].[tblRoom] ([roomID])
GO
ALTER TABLE [dbo].[tblOrderDetail] CHECK CONSTRAINT [FK_tblOrderDetail_tblRoom]
GO
ALTER TABLE [dbo].[tblRoom]  WITH CHECK ADD  CONSTRAINT [FK_tblRoom_tblHotel] FOREIGN KEY([hotelID])
REFERENCES [dbo].[tblHotel] ([hotelID])
GO
ALTER TABLE [dbo].[tblRoom] CHECK CONSTRAINT [FK_tblRoom_tblHotel]
GO
ALTER TABLE [dbo].[tblRoom]  WITH CHECK ADD  CONSTRAINT [FK_tblRoom_tblRoomType] FOREIGN KEY([typeID])
REFERENCES [dbo].[tblRoomType] ([typeID])
GO
ALTER TABLE [dbo].[tblRoom] CHECK CONSTRAINT [FK_tblRoom_tblRoomType]
GO
USE [master]
GO
ALTER DATABASE [HotelBooking] SET  READ_WRITE 
GO
