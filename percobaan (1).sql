-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 07 Jun 2018 pada 16.51
-- Versi Server: 10.1.16-MariaDB
-- PHP Version: 5.6.24

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `percobaan`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `tabel_admin`
--

CREATE TABLE `tabel_admin` (
  `id_admin` int(6) NOT NULL,
  `nama_admin` varchar(50) NOT NULL,
  `alamat` varchar(30) NOT NULL,
  `telp` varchar(12) NOT NULL,
  `user_name` varchar(30) NOT NULL,
  `password` int(30) NOT NULL,
  `status` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `tabel_pembayaran`
--

CREATE TABLE `tabel_pembayaran` (
  `id_pembayaran` int(6) NOT NULL,
  `jatuh_tempo` date NOT NULL,
  `tanggal_bayar` date NOT NULL,
  `status` varchar(5) NOT NULL,
  `foto` varchar(150) NOT NULL,
  `id_user` int(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tabel_pembayaran`
--

INSERT INTO `tabel_pembayaran` (`id_pembayaran`, `jatuh_tempo`, `tanggal_bayar`, `status`, `foto`, `id_user`) VALUES
(1, '2018-06-10', '2018-06-09', '1', 'mmm', 1),
(2, '2018-06-13', '2018-06-12', '1', 'aa', 1),
(3, '2018-06-08', '2018-06-09', '0', 'qwe', 1),
(4, '2018-06-08', '2018-06-09', '0', 'nmb', 1),
(5, '2018-06-08', '2018-06-09', '0', 'nmb', 1);

-- --------------------------------------------------------

--
-- Struktur dari tabel `tabel_pengaduan`
--

CREATE TABLE `tabel_pengaduan` (
  `id_pengaduan` int(6) NOT NULL,
  `tanggal_pengaduan` date NOT NULL,
  `isi_pengaduan` varchar(150) NOT NULL,
  `id_user` int(6) NOT NULL,
  `status` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tabel_pengaduan`
--

INSERT INTO `tabel_pengaduan` (`id_pengaduan`, `tanggal_pengaduan`, `isi_pengaduan`, `id_user`, `status`) VALUES
(1, '2018-06-07', 'mmmmmmmmm', 1, 1),
(2, '2018-06-08', 'kkkkkkkk', 1, 1);

-- --------------------------------------------------------

--
-- Struktur dari tabel `tabel_user`
--

CREATE TABLE `tabel_user` (
  `id_user` int(6) NOT NULL,
  `nama_user` varchar(50) NOT NULL,
  `alamat` varchar(50) NOT NULL,
  `telp` varchar(12) NOT NULL,
  `email` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `tarif` int(6) NOT NULL,
  `tanggal_daftar` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `tabel_user`
--

INSERT INTO `tabel_user` (`id_user`, `nama_user`, `alamat`, `telp`, `email`, `password`, `tarif`, `tanggal_daftar`) VALUES
(1, 'Lucianto', 'Jember ', '0822326176', 'lucianto@gmail.com', '123', 25000, '2018-06-08');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tabel_admin`
--
ALTER TABLE `tabel_admin`
  ADD PRIMARY KEY (`id_admin`);

--
-- Indexes for table `tabel_pembayaran`
--
ALTER TABLE `tabel_pembayaran`
  ADD PRIMARY KEY (`id_pembayaran`);

--
-- Indexes for table `tabel_pengaduan`
--
ALTER TABLE `tabel_pengaduan`
  ADD PRIMARY KEY (`id_pengaduan`);

--
-- Indexes for table `tabel_user`
--
ALTER TABLE `tabel_user`
  ADD PRIMARY KEY (`id_user`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tabel_admin`
--
ALTER TABLE `tabel_admin`
  MODIFY `id_admin` int(6) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `tabel_pembayaran`
--
ALTER TABLE `tabel_pembayaran`
  MODIFY `id_pembayaran` int(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `tabel_pengaduan`
--
ALTER TABLE `tabel_pengaduan`
  MODIFY `id_pengaduan` int(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `tabel_user`
--
ALTER TABLE `tabel_user`
  MODIFY `id_user` int(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
