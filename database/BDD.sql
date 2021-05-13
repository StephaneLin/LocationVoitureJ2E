-- phpMyAdmin SQL Dump
-- version 4.7.1
-- https://www.phpmyadmin.net/
--
-- Hôte : sql5.freemysqlhosting.net
-- Généré le :  mer. 10 mars 2021 à 21:48
-- Version du serveur :  5.5.62-0ubuntu0.14.04.1
-- Version de PHP :  7.0.33-0ubuntu0.16.04.3

SET
SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET
AUTOCOMMIT = 0;
START TRANSACTION;
SET
time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `sql5392141`
--

-- --------------------------------------------------------

--
-- Structure de la table `droit_acces`
--

CREATE TABLE `droit_acces`
(
    `droitAccesId` int(10) UNSIGNED NOT NULL,
    `role`         varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `droit_acces`
--

INSERT INTO `droit_acces` (`droitAccesId`, `role`)
VALUES (1, 'Utilisateur'),
       (2, 'Mecanicien'),
       (3, 'Responsable');

-- --------------------------------------------------------

--
-- Structure de la table `maintenance`
--

CREATE TABLE `maintenance`
(
    `maintenanceId` int(11) NOT NULL,
    `pneu`          tinyint(4) NOT NULL,
    `carosserie`    tinyint(4) NOT NULL,
    `electronique`  tinyint(4) NOT NULL,
    `moteur`        tinyint(4) NOT NULL,
    `conforme`      tinyint(4) NOT NULL,
    `vehiculeFK`    int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `maintenance`
--

INSERT INTO `maintenance` (`maintenanceId`, `pneu`, `carosserie`, `electronique`, `moteur`, `conforme`, `vehiculeFK`)
VALUES (1, 0, 0, 0, 0, 0, 0),
       (5, 1, 0, 1, 0, 0, 1),
       (6, 0, 0, 0, 0, 0, 1);

-- --------------------------------------------------------

--
-- Structure de la table `paiement`
--

CREATE TABLE `paiement`
(
    `paiement_id`  int(10) UNSIGNED ZEROFILL NOT NULL,
    `entitee`      int(11) NOT NULL,
    `prix_fixe`    int(11) DEFAULT NULL,
    `prix_hebdo`   int(11) DEFAULT NULL,
    `prix_mensuel` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `parking`
--

CREATE TABLE `parking`
(
    `parkingId`   int(11) NOT NULL,
    `name`        varchar(45) NOT NULL,
    `address`     varchar(45) NOT NULL,
    `nbrCar`      int(11) NOT NULL,
    `phoneNumber` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `parking`
--

INSERT INTO `parking` (`parkingId`, `name`, `address`, `nbrCar`, `phoneNumber`)
VALUES (1, 'Renter', '1 Rue Parking', 120, '0123456789'),
       (2, 'Chicouking', '67 Rue des Almas', 48, '0564827345'),
       (3, 'Lillking', '2 Rue Macéna', 54, '0654583529');

-- --------------------------------------------------------

--
-- Structure de la table `rendezvous`
--

CREATE TABLE `rendezvous`
(
    `rendezvous_id` int(10) UNSIGNED ZEROFILL NOT NULL,
    `date`          date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `reservation`
--

CREATE TABLE `reservation`
(
    `reservationId`   int(11) NOT NULL,
    `debutDate`       date NOT NULL,
    `finDate`         date NOT NULL,
    `status`          int(11) NOT NULL,
    `reservationDate` date NOT NULL,
    `vehiculeFK`      int(11) NOT NULL,
    `rendezvousFK`    int(11) NOT NULL,
    `utilisateurFK`   int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `reservation`
--

INSERT INTO `reservation` (`reservationId`, `debutDate`, `finDate`, `status`, `reservationDate`, `vehiculeFK`,
                           `rendezvousFK`, `utilisateurFK`)
VALUES (1, '2021-03-03', '2021-03-05', 1, '2021-03-02', 1, 0, 0),
       (2, '2021-03-01', '2021-03-03', 1, '2021-02-28', 4, 0, 0),
       (3, '2021-03-02', '2021-03-05', 1, '2021-03-01', 6, 0, 0);

-- --------------------------------------------------------

--
-- Structure de la table `service`
--

CREATE TABLE `service`
(
    `service_id` int(11) NOT NULL,
    `nom`        varchar(45) NOT NULL,
    `prix`       int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `service`
--

INSERT INTO `service` (`service_id`, `nom`, `prix`)
VALUES (0, 'livraison', 10),
       (1, 'recuperation', 10);

-- --------------------------------------------------------

--
-- Structure de la table `service_manager`
--

CREATE TABLE `service_manager`
(
    `ID`         int(11) NOT NULL,
    `parking_FK` int(11) NOT NULL,
    `service_FK` int(11) NOT NULL,
    `status`     tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=koi8r;

--
-- Déchargement des données de la table `service_manager`
--

INSERT INTO `service_manager` (`ID`, `parking_FK`, `service_FK`, `status`)
VALUES (1, 1, 1, 1),
       (2, 1, 0, 1);

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE `utilisateur`
(
    `userId`         int(10) NOT NULL,
    `entitee`        int(11) NOT NULL,
    `connexionDate`  date        NOT NULL,
    `companyName`    varchar(45)  DEFAULT NULL,
    `companyPhone`   int(11) DEFAULT NULL,
    `siret`          int(11) DEFAULT NULL,
    `userLastName`   varchar(45)  DEFAULT NULL,
    `userFirstName`  varchar(45)  DEFAULT NULL,
    `userPhone`      int(11) DEFAULT NULL,
    `userYearOld`    int(11) DEFAULT NULL,
    `email`          varchar(60) NOT NULL,
    `password`       varchar(200) DEFAULT NULL,
    `accessRightsFK` int(11) DEFAULT NULL,
    `paymentCardFK`  int(11) DEFAULT NULL,
    `licenceFK`      mediumblob
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`userId`, `entitee`, `connexionDate`, `companyName`, `companyPhone`, `siret`, `userLastName`,
                           `userFirstName`, `userPhone`, `userYearOld`, `email`, `password`, `accessRightsFK`,
                           `paymentCardFK`, `licenceFK`)
VALUES (2, 0, '0000-00-00', NULL, NULL, NULL, 'Lin', 'Stephane', 202022222, 23, 'test@test.com',
        '$argon2i$v=19$m=65536,t=2,p=1$Q5yfUzj7LY7Np5hFNXYAEA$o7uVsk0dYeFV9ZpQrN48CaLd6ZLqfFWRZAN1XM8Mxoo', 4, NULL,
        NULL),
       (3, 1, '2021-03-06', 'WAMB.INC', 654524552, 25543564, NULL, NULL, NULL, NULL, 'draloen.wamb@gmail.com', NULL, 1,
        NULL, NULL),
       (4, 0, '2021-03-02', NULL, NULL, NULL, 'ChicouLastName', 'ChicouFirstName', 51646481, NULL, 'search@search.com',
        NULL, 4, NULL, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `vehicule`
--

CREATE TABLE `vehicule`
(
    `vehiculeId`    int(11) NOT NULL,
    `marque`        varchar(40) NOT NULL,
    `modele`        varchar(45) NOT NULL,
    `annee`         int(11) NOT NULL,
    `kilometrage`   int(11) NOT NULL,
    `photo`         mediumblob,
    `prix`          int(11) NOT NULL,
    `reservationFK` int(11) DEFAULT NULL,
    `maintenanceFK` int(11) DEFAULT NULL,
    `parkingFK`     int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `vehicule`
--

INSERT INTO `vehicule` (`vehiculeId`, `marque`, `modele`, `annee`, `kilometrage`, `photo`, `prix`, `reservationFK`,
                        `maintenanceFK`, `parkingFK`)
VALUES (2, 'Chevrolet', 'Bolt EUV', 2021, 4000, NULL, 500, NULL, NULL, 2),
       (3, 'Audi', 'Q8', 2021, 3000, NULL, 600, NULL, NULL, 3),
       (5, 'Audi', 'Q5', 2019, 45250, NULL, 425, NULL, NULL, 2),
       (6, 'Volvo', 'GrandChicout', 2016, 120000, NULL, 1000, NULL, NULL, 1);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `droit_acces`
--
ALTER TABLE `droit_acces`
    ADD PRIMARY KEY (`droitAccesId`),
  ADD UNIQUE KEY `droit_acces_id_UNIQUE` (`droitAccesId`);

--
-- Index pour la table `maintenance`
--
ALTER TABLE `maintenance`
    ADD PRIMARY KEY (`maintenanceId`),
  ADD UNIQUE KEY `maintenance_id_UNIQUE` (`maintenanceId`);

--
-- Index pour la table `paiement`
--
ALTER TABLE `paiement`
    ADD PRIMARY KEY (`paiement_id`),
  ADD UNIQUE KEY `tarification_id_UNIQUE` (`paiement_id`);

--
-- Index pour la table `parking`
--
ALTER TABLE `parking`
    ADD PRIMARY KEY (`parkingId`),
  ADD UNIQUE KEY `parking_id_UNIQUE` (`parkingId`);

--
-- Index pour la table `rendezvous`
--
ALTER TABLE `rendezvous`
    ADD PRIMARY KEY (`rendezvous_id`),
  ADD UNIQUE KEY `rendezvous_id_UNIQUE` (`rendezvous_id`);

--
-- Index pour la table `reservation`
--
ALTER TABLE `reservation`
    ADD PRIMARY KEY (`reservationId`),
  ADD UNIQUE KEY `reservation_id_UNIQUE` (`reservationId`);

--
-- Index pour la table `service`
--
ALTER TABLE `service`
    ADD PRIMARY KEY (`service_id`),
  ADD UNIQUE KEY `service_id_UNIQUE` (`service_id`);

--
-- Index pour la table `service_manager`
--
ALTER TABLE `service_manager`
    ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
    ADD PRIMARY KEY (`userId`);

--
-- Index pour la table `vehicule`
--
ALTER TABLE `vehicule`
    ADD PRIMARY KEY (`vehiculeId`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `droit_acces`
--
ALTER TABLE `droit_acces`
    MODIFY `droitAccesId` int (10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT pour la table `maintenance`
--
ALTER TABLE `maintenance`
    MODIFY `maintenanceId` int (11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT pour la table `paiement`
--
ALTER TABLE `paiement`
    MODIFY `paiement_id` int (10) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `parking`
--
ALTER TABLE `parking`
    MODIFY `parkingId` int (11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT pour la table `rendezvous`
--
ALTER TABLE `rendezvous`
    MODIFY `rendezvous_id` int (10) UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `reservation`
--
ALTER TABLE `reservation`
    MODIFY `reservationId` int (11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT pour la table `service`
--
ALTER TABLE `service`
    MODIFY `service_id` int (11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT pour la table `service_manager`
--
ALTER TABLE `service_manager`
    MODIFY `ID` int (11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
    MODIFY `userId` int (10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;
--
-- AUTO_INCREMENT pour la table `vehicule`
--
ALTER TABLE `vehicule`
    MODIFY `vehiculeId` int (11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
