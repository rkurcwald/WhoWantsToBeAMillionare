-- phpMyAdmin SQL Dump
-- version 4.8.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 07 Cze 2018, 06:01
-- Wersja serwera: 10.1.32-MariaDB
-- Wersja PHP: 7.2.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `milionerzy`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `questions`
--

CREATE TABLE `questions` (
  `ID` int(10) NOT NULL,
  `Question` varchar(255) COLLATE utf8_bin NOT NULL,
  `Answers` varchar(7000) COLLATE utf8_bin NOT NULL,
  `CorrectAnswer` varchar(255) COLLATE utf8_bin NOT NULL,
  `WUUI` longtext COLLATE utf8_bin NOT NULL COMMENT 'Which Users Used It'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Zrzut danych tabeli `questions`
--

INSERT INTO `questions` (`ID`, `Question`, `Answers`, `CorrectAnswer`, `WUUI`) VALUES
(1, 'Kiedy obchodzony jest Dzień Matki?;', '21 stycznia;26 maja;8 marca;23 czerwca;', '26 maja', ''),
(2, '\"Iron man\" to przebój grupy:;', 'Deep People;Black Sabbath;AC/DC;Led Zeppelin;', 'Black Sabbath', ''),
(3, 'Kim dla Juliusza Kossaka była Zofia Kossak-Szczucka?;', 'Wnuczką;Matką;Żoną;Córką;', 'Wnuczką', ''),
(4, 'Jak inaczej nazywamy absynt?;', 'Piołunówka;Anyżówka;Jałowcówka;Miętówka;', 'Piołunówka', ''),
(5, 'Co było na początku świata w mitologii greckiej?;', 'Słowo;Chaos;Kosmos;Rozum;', 'Chaos;', ''),
(6, 'Kto nazywany jest \"prymasem tysiąclecia\"?;', 'August Hlond;Józef Glemp;Adam Sapiecha;Stefan Wyszyński;', 'Stefan Wyszyński', ''),
(7, 'Zenitówkę potocznie określa się jako:;', 'Moździerz;Działo przeciwlotnicze;Działo bezodrzutowe;Haubicoarmatę', 'Działo przeciwlotnicze', ''),
(8, 'W jakim kraju leży Pustynia Gibsona?', 'W Meksyku;W Australii;W USA;W Algerii;', 'W Australii', ''),
(9, 'Słynny astronom Jan Heweliusz był:;', 'torunianinem;gdańszczaninem;szczecinianinem;krakowianinem;', 'gdańszczaninem', ''),
(10, 'Których owoców nie jada się na surowo?;', 'kiwi,pigwy;grejpfruta;granatu;', 'pigwy;', ''),
(11, 'Wędzisko jest częścią:;', 'Wędki;Uzdy;Kagańca;Sieci rybackiej;', 'Wędki', ''),
(12, 'Do naczelnych nie należą:;', 'Lemurowate;Wyraki;Małpy wąskonose;Leniwce;', 'Leniwce', ''),
(13, 'Kształt jakiej litery ma bramka w rugby?;', 'U;Y;H;T;', 'H', ''),
(14, 'Pierwsze zęby niemowlęcia to:;', 'Zęby mądrości;Siekacze;Trzonowce;Kły;', 'Siekacze', ''),
(15, 'Wielkość, która określa odczyn roztworu to:;', 'pH;kcal;ASO;IQ;', 'pH', ''),
(16, 'Patronem jakiego kraju jest św. Patryk?;', 'Niemiec;Irlandii;Austrii;Francji;', 'Irlandii', '');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `users`
--

CREATE TABLE `users` (
  `ID` int(10) NOT NULL,
  `Nick` varchar(255) COLLATE utf8_bin NOT NULL,
  `Surname` varchar(255) COLLATE utf8_bin NOT NULL,
  `email` varchar(255) COLLATE utf8_bin NOT NULL,
  `password` varchar(255) COLLATE utf8_bin NOT NULL,
  `bestScore` int(255) NOT NULL COMMENT 'IN PLN',
  `games` int(255) NOT NULL COMMENT 'How many games user played',
  `HowManyWins` int(11) NOT NULL,
  `Type` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Zrzut danych tabeli `users`
--

INSERT INTO `users` (`ID`, `Nick`, `Surname`, `email`, `password`, `bestScore`, `games`, `HowManyWins`, `Type`) VALUES
(1, 'admin', 'Robert', 'test@test.pl', '123', 0, 0, 0, 0),
(4, 'Test', 'Test', 'Testy12345@gmail.com', 'Qw11', 1000000, 4, 4, 0),
(5, 'Test12', 'Test12', 'rob1@gmail.com', 'Qw11', 0, 0, 0, 0),
(6, 'Test22', 'Test22', 'tsta1@gmail.com', 'Qw11', 0, 0, 0, 0),
(7, 'Test3211312', 'Robert', 'Robert@gmail.pl', 'Qw11', 0, 0, 0, 0),
(8, 'Test121', 'Robert', 'Robert1@wp.pl', 'Qw11', 0, 0, 0, 0);

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `questions`
--
ALTER TABLE `questions`
  ADD PRIMARY KEY (`ID`);

--
-- Indeksy dla tabeli `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT dla tabeli `questions`
--
ALTER TABLE `questions`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT dla tabeli `users`
--
ALTER TABLE `users`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
