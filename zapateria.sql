-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 17-11-2021 a las 01:48:02
-- Versión del servidor: 10.4.20-MariaDB
-- Versión de PHP: 8.0.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `zapateria`
--
CREATE DATABASE IF NOT EXISTS `zapateria` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `zapateria`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `nombreCliente` varchar(50) DEFAULT NULL,
  `emailCliente` longtext NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`nombreCliente`, `emailCliente`) VALUES
('pepe', 'pepe@gmail.com'),
('jose', 'jose@hotmail.com'),
('pedro', 'pedro@gmal.com'),
('ivan ', 'ivan1@mail.mx'),
('Nnnn', 'qweqwe'),
('0t', 'D0'),
('AEIOU', 'ABCDE'),
('qwe', 'asd'),
('qwe', 'qwe'),
('zxc', 'zxc'),
('pablo', 'pablo'),
('mmm', 'mmm'),
('mnmm', 'mnnn'),
('qwe', 'qwe@hola.com'),
('holaaa', 'holaaa@hola.com'),
('qwe', 'qwe@qe.com'),
('asdasd', 'ads@asd'),
('ñlklk', 'ñpñp@pl.com'),
('kjhg', 'nbv@sd-com'),
('Bbb', 'weqwe@hotm.com');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleado`
--

CREATE TABLE `empleado` (
  `Nempleado` int(11) NOT NULL,
  `Nombre` varchar(100) NOT NULL,
  `usuario` varchar(30) NOT NULL,
  `pass` varchar(20) NOT NULL,
  `puesto` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `empleado`
--

INSERT INTO `empleado` (`Nempleado`, `Nombre`, `usuario`, `pass`, `puesto`) VALUES
(7, 'Eminuns', 'Emnis', '4444', 'Gerente'),
(8, 'PrubeaC', 'cc', 'cccc', 'Gerente'),
(9, 'qq', 'qq', 'qq', '[Empleado,'),
(11, 'qq', 'qq', 'qq', 'Empleado'),
(12, 'WW', 'WW', 'WW', 'Gerente'),
(15, 'PP QQ', 'PP', '123', 'Empleado'),
(16, 'Flor Flor Flor', 'Rosa1', 'rosa1', 'Empleado'),
(17, 'Pedro Pedro', 'Pedr0', 'pedro', 'Gerente'),
(20, '123', '123', '123', 'Gerente'),
(21, 'Pedro', 'Pedro1', 'hola', 'Empleado'),
(22, 'Perez', 'Perz1', '1234', 'Empleado'),
(23, 'ooo', '321', '321', 'Empleado'),
(24, 'Pp', 'p1', 'qwe', 'Empleado');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `horario`
--

CREATE TABLE `horario` (
  `nombreHorario` varchar(30) NOT NULL,
  `horaHorario` longtext NOT NULL,
  `fechaHorario` longtext NOT NULL,
  `empleadoHorario` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `horario`
--

INSERT INTO `horario` (`nombreHorario`, `horaHorario`, `fechaHorario`, `empleadoHorario`) VALUES
('Apertura', '08:00', '09-11', 'Juan'),
('Cierre', '19:00', '10-11', 'Jose'),
('Apertura', '08:00', '12/11/2021', 'Pepe');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `promocion`
--

CREATE TABLE `promocion` (
  `idPromocion` int(11) NOT NULL,
  `nombreProducto` varchar(50) CHARACTER SET utf8 NOT NULL,
  `mensaje` varchar(100) CHARACTER SET utf8 NOT NULL,
  `descuento` varchar(10) CHARACTER SET utf8 NOT NULL,
  `precioAnterior` float NOT NULL,
  `precioNuevo` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `promocion`
--

INSERT INTO `promocion` (`idPromocion`, `nombreProducto`, `mensaje`, `descuento`, `precioAnterior`, `precioNuevo`) VALUES
(17, 'ad', 'fer', '23', 76, 344),
(21, 'qweqwe', 'qweqwe', '654', 645, 32564),
(23, 'dff', 'fggf', '7777', 664, 195),
(25, 'PrubaBto', 'bbb', '12', 43, 4343),
(26, 'qwe', 'aweas', '12', 12, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedor`
--

CREATE TABLE `proveedor` (
  `ProveedorId` int(11) NOT NULL,
  `ProveedorNombre` varchar(30) NOT NULL,
  `ProveedorTelefono` varchar(10) NOT NULL,
  `ProveedorDireccion` varchar(50) NOT NULL,
  `ProveedorProducto` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `proveedor`
--

INSERT INTO `proveedor` (`ProveedorId`, `ProveedorNombre`, `ProveedorTelefono`, `ProveedorDireccion`, `ProveedorProducto`) VALUES
(6, 'NIke', '123231', '1wewe', 'ewqeqwe'),
(7, 'Adidas', 'asdasdasd', 'AV. 1', 'Tenix'),
(8, 'reer', '43434', 'asda', 'asdad'),
(9, 'Con', '123', 'wewe', 'qewe');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tablaproductos`
--

CREATE TABLE `tablaproductos` (
  `claveProducto` int(100) NOT NULL,
  `marcaProducto` varchar(50) NOT NULL,
  `descripcionProducto` varchar(50) NOT NULL,
  `proveedorProducto` varchar(30) NOT NULL,
  `precioCProducto` double NOT NULL,
  `precioVProducto` double NOT NULL,
  `stockProducto` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `tablaproductos`
--

INSERT INTO `tablaproductos` (`claveProducto`, `marcaProducto`, `descripcionProducto`, `proveedorProducto`, `precioCProducto`, `precioVProducto`, `stockProducto`) VALUES
(5, 'nike', 'sdfsafsa', 'nike', 56, 542, 52),
(6, 'adidas', 'tenis', 'adidas', 600, 1800, 50),
(7, 'puma', 'tenis', 'puma', 500, 1000, 26),
(8, 'hhgf', 'hgfhg', 'hgfghf', 52, 258, 25),
(9, 'pppp', 'qqqq', 'zapato', 250, 500, 25),
(10, 'asd', 'dfsasd', 'tenis', 250, 522, 20),
(11, 'asdf', 'sdfds', 'zapato', 100, 200, 10);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ventas`
--

CREATE TABLE `ventas` (
  `IdVenta` int(11) NOT NULL,
  `NameV` varchar(30) COLLATE utf16_spanish2_ci NOT NULL,
  `PriceV` int(11) NOT NULL,
  `QuantityV` int(11) NOT NULL,
  `Total` int(11) NOT NULL,
  `Date` text COLLATE utf16_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_spanish2_ci;

--
-- Volcado de datos para la tabla `ventas`
--

INSERT INTO `ventas` (`IdVenta`, `NameV`, `PriceV`, `QuantityV`, `Total`, `Date`) VALUES
(2, 'Zapatillas', 200, 1, 200, '2021-10-21'),
(4, 'Tenis', 400, 1, 400, '2021-11-06');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `empleado`
--
ALTER TABLE `empleado`
  ADD PRIMARY KEY (`Nempleado`);

--
-- Indices de la tabla `promocion`
--
ALTER TABLE `promocion`
  ADD PRIMARY KEY (`idPromocion`);

--
-- Indices de la tabla `proveedor`
--
ALTER TABLE `proveedor`
  ADD PRIMARY KEY (`ProveedorId`);

--
-- Indices de la tabla `tablaproductos`
--
ALTER TABLE `tablaproductos`
  ADD PRIMARY KEY (`claveProducto`);

--
-- Indices de la tabla `ventas`
--
ALTER TABLE `ventas`
  ADD PRIMARY KEY (`IdVenta`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `empleado`
--
ALTER TABLE `empleado`
  MODIFY `Nempleado` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT de la tabla `promocion`
--
ALTER TABLE `promocion`
  MODIFY `idPromocion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT de la tabla `proveedor`
--
ALTER TABLE `proveedor`
  MODIFY `ProveedorId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `tablaproductos`
--
ALTER TABLE `tablaproductos`
  MODIFY `claveProducto` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT de la tabla `ventas`
--
ALTER TABLE `ventas`
  MODIFY `IdVenta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
