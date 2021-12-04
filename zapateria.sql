-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 04-12-2021 a las 20:53:07
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
('Ivan', 'Ivan@gmail.com');

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
(52, 'pablo', 'pablo', 'asd', 'Empleado');

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
('Apertura', '13:19:50', '2021-12-04', 'Ivan'),
('Cierre', '13:20:01', '2021-12-04', 'Horus');

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
  `precioNuevo` float NOT NULL,
  `fechaInicio` varchar(50) NOT NULL,
  `fechaCierre` varchar(50) NOT NULL,
  `diasDuracion` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `promocion`
--

INSERT INTO `promocion` (`idPromocion`, `nombreProducto`, `mensaje`, `descuento`, `precioAnterior`, `precioNuevo`, `fechaInicio`, `fechaCierre`, `diasDuracion`) VALUES
(27, 'Tennis Nuevos', 'Aprovecha esta nueva oferta', '20%', 500, 400, '', '', 0);

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
(12, '3 hermanos ', '1100992233', 'xico', 'zapato');

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
(12, 'Chabelo', 'Zapatos para niñas 24 cms', 'Los 3 Hermanos', 600, 700, 20);

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
  ADD PRIMARY KEY (`ProveedorId`),
  ADD UNIQUE KEY `ProveedorNombre` (`ProveedorNombre`);

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
  MODIFY `Nempleado` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=56;

--
-- AUTO_INCREMENT de la tabla `promocion`
--
ALTER TABLE `promocion`
  MODIFY `idPromocion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;

--
-- AUTO_INCREMENT de la tabla `proveedor`
--
ALTER TABLE `proveedor`
  MODIFY `ProveedorId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT de la tabla `tablaproductos`
--
ALTER TABLE `tablaproductos`
  MODIFY `claveProducto` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT de la tabla `ventas`
--
ALTER TABLE `ventas`
  MODIFY `IdVenta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
