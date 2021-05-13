-- phpMyAdmin SQL Dump
-- version 4.7.1
-- https://www.phpmyadmin.net/
--
-- Hôte : sql5.freemysqlhosting.net
-- Généré le :  mer. 10 mars 2021 à 18:08
-- Version du serveur :  5.5.62-0ubuntu0.14.04.1
-- Version de PHP :  7.0.33-0ubuntu0.16.04.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `sql5392141`
--

--
-- Déchargement des données de la table `droit_acces`
--

INSERT INTO `droit_acces` (`droitAccesId`, `role`) VALUES
(1, 'Utilisateur'),
(2, 'Mecanicien'),
(3, 'Responsable');

--
-- Déchargement des données de la table `maintenance`
--

INSERT INTO `maintenance` (`maintenanceId`, `pneu`, `carosserie`, `electronique`, `moteur`, `conforme`, `vehiculeFK`) VALUES
(1, 0, 0, 0, 0, 0, 0),
(5, 1, 0, 1, 0, 0, 1);

--
-- Déchargement des données de la table `parking`
--

INSERT INTO `parking` (`parkingId`, `name`, `address`, `nbrCar`, `phoneNumber`) VALUES
(1, 'Renter', '1 Rue Parking', 120, '0123456789'),
(2, 'Chicouking', '67 Rue des Almas', 48, '0564827345'),
(3, 'Lillking', '2 Rue Macéna', 54, '0654583529');

--
-- Déchargement des données de la table `reservation`
--

INSERT INTO `reservation` (`reservationId`, `debutDate`, `finDate`, `status`, `reservationDate`, `vehiculeFK`, `rendezvousFK`, `utilisateurFK`) VALUES
(1, '2021-03-03', '2021-03-05', 1, '2021-03-02', 1, 0, 0),
(2, '2021-03-01', '2021-03-03', 1, '2021-02-28', 4, 0, 0),
(3, '2021-03-02', '2021-03-05', 1, '2021-03-01', 6, 0, 0);

--
-- Déchargement des données de la table `service`
--

INSERT INTO `service` (`service_id`, `nom`, `prix`) VALUES
(0, 'livraison', 10),
(1, 'recuperation', 10);

--
-- Déchargement des données de la table `service_manager`
--

INSERT INTO `service_manager` (`ID`, `parking_FK`, `service_FK`, `status`) VALUES
(1, 1, 1, 1),
(2, 1, 0, 1);

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`userId`, `entitee`, `connexionDate`, `companyName`, `companyPhone`, `siret`, `userLastName`, `userFirstName`, `userPhone`, `userYearOld`, `email`, `password`, `accessRightsFK`, `paymentCardFK`, `licenceFK`) VALUES
(2, 0, '0000-00-00', NULL, NULL, NULL, 'Lin', 'Stephane', 202022222, 23, 'test@test.com', '$argon2i$v=19$m=65536,t=2,p=1$Q5yfUzj7LY7Np5hFNXYAEA$o7uVsk0dYeFV9ZpQrN48CaLd6ZLqfFWRZAN1XM8Mxoo', 4, NULL, NULL),
(3, 1, '2021-03-06', 'WAMB.INC', 654524552, 25543564, NULL, NULL, NULL, NULL, 'draloen.wamb@gmail.com', NULL, 1, NULL, NULL),
(4, 0, '2021-03-02', NULL, NULL, NULL, 'ChicouLastName', 'ChicouFirstName', 51646481, NULL, 'search@search.com', NULL, 4, NULL, NULL);

--
-- Déchargement des données de la table `vehicule`
--

INSERT INTO `vehicule` (`vehiculeId`, `marque`, `modele`, `annee`, `kilometrage`, `photo`, `prix`, `reservationFK`, `maintenanceFK`, `parkingFK`) VALUES
(1, 'BMW', 'Berlin', 2010, 8500, NULL, 300, 1, NULL, 1),
(2, 'Chevrolet', 'Bolt EUV', 2021, 4000, NULL, 500, NULL, NULL, 2),
(3, 'Audi', 'Q8', 2021, 3000, NULL, 600, NULL, NULL, 3),
(5, 'Audi', 'Q5', 2019, 45250, NULL, 425, NULL, NULL, 2),
(6, 'Volvo', 'GrandChicout', 2016, 120000, NULL, 1000, NULL, NULL, 1);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
