-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : lun. 28 déc. 2020 à 18:14
-- Version du serveur :  8.0.21
-- Version de PHP : 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `article-api`
--
DROP DATABASE IF EXISTS `article-api`;
CREATE DATABASE IF NOT EXISTS `article-api` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE `article-api`;

-- --------------------------------------------------------

--
-- Structure de la table `article`
--

DROP TABLE IF EXISTS `article`;
CREATE TABLE IF NOT EXISTS `article` (
  `id` int NOT NULL AUTO_INCREMENT,
  `category` int DEFAULT NULL,
  `content` varchar(8000) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `user` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `article`
--

INSERT INTO `article` (`id`, `category`, `content`, `date`, `user`) VALUES
(1, 1, "L'informatique est un domaine d'activité scientifique, technique, et industriel concernant le traitement automatique de l'information numérique par l'exécution de programmes informatiques par des machines : des systèmes embarqués, des ordinateurs, des robots, des automates, etc.", '2020-12-08', 1),
(2, 1, "Software est le mot anglais pour le logiciel, un ensemble d'instructions données à un appareil informatique.", '2020-12-08', 1),
(3, 2, "Java est un langage de programmation orienté objet créé par James Gosling et Patrick Naughton, employés de Sun Microsystems, avec le soutien de Bill Joy (cofondateur de Sun Microsystems en 1982), présenté officiellement le 23 mai 1995 au SunWorld.", '2020-12-15', 1),
(4, 3, "Spring Boot facilite la création d'applications autonomes basées sur Spring de qualité production que vous pouvez «simplement exécuter». Nous adoptons une vision avisée de la plate-forme Spring et des bibliothèques tierces afin que vous puissiez commencer avec un minimum de tracas. La plupart des applications Spring Boot nécessitent une configuration Spring minimale.", '2020-12-26', 2),
(5, 2, "Java stimule l'innovation dans notre monde numérique. Exploitez ce potentiel avec des ressources Java pour les étudiants codeurs, les amateurs, les développeurs et les responsables informatiques.", '2020-12-26', 2),
(6, 3, "Ce guide fournit un aperçu de la façon dont Spring Boot vous aide à accélérer le développement d'applications. Au fur et à mesure que vous lirez les guides de mise en route de Spring, vous verrez plus de cas d'utilisation pour Spring Boot. Ce guide est destiné à vous donner un aperçu rapide de Spring Boot. Si vous souhaitez créer votre propre projet basé sur Spring Boot, visitez Spring Initializr , renseignez les détails de votre projet, choisissez vos options et téléchargez un projet groupé sous forme de fichier zip.", '2020-12-26', 2);
-- --------------------------------------------------------

--
-- Structure de la table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(12);
--
-- Base de données : `category-api`
--
DROP DATABASE IF EXISTS `category-api`;
CREATE DATABASE IF NOT EXISTS `category-api` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE `category-api`;

-- --------------------------------------------------------

--
-- Structure de la table `category`
--

DROP TABLE IF EXISTS `category`;
CREATE TABLE IF NOT EXISTS `category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `category`
--

INSERT INTO `category` (`id`, `name`) VALUES
(1, 'informatique'),
(2, 'java'),
(3, 'framework');

-- --------------------------------------------------------

--
-- Structure de la table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(4);
--
-- Base de données : `comment-api`
--
DROP DATABASE IF EXISTS `comment-api`;
CREATE DATABASE IF NOT EXISTS `comment-api` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE `comment-api`;

-- --------------------------------------------------------

--
-- Structure de la table `comment`
--

DROP TABLE IF EXISTS `comment`;
CREATE TABLE IF NOT EXISTS `comment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user` int NOT NULL,
  `date` date NOT NULL,
  `content` varchar(255) NOT NULL,
  `article` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `comment`
--

INSERT INTO `comment` (`id`, `user`, `date`, `content`, `article`) VALUES
(1, 1, '2020-12-31', "L'informatique c'est trop cool", 1),
(2, 1, '2020-12-26', "Le langage java est un bon langage objet", 3),
(3, 1, '2020-12-26', "Spring boot pour le micro service c'est good", 4),
(4, 2, '2020-12-31', "David tu es le meilleur dev du monde", 1),
(5, 1, '2020-12-31', "Merci beaucoup jérémy", 1),
(6, 2, '2020-12-26', "Les erreurs sont chiante", 3),
(7, 2, '2020-12-26', "I like", 2);


-- --------------------------------------------------------

--
-- Structure de la table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(30);
--
-- Base de données : `user-api`
--
DROP DATABASE IF EXISTS `user-api`;
CREATE DATABASE IF NOT EXISTS `user-api` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE `user-api`;

-- --------------------------------------------------------

--
-- Structure de la table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(5);

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(255) NOT NULL,
  `mail` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `username`, `password`, `mail`) VALUES
(1, 'david', '$2a$10$2Wx0h9yRmlBhrMxzN1zS.ePNPi0rrqw/oRjSHmNKG3H6JAdr4t6/m', 'david@david.com'),
(2, 'jeremy', '$2a$10$2Wx0h9yRmlBhrMxzN1zS.ePNPi0rrqw/oRjSHmNKG3H6JAdr4t6/m', 'jeremy@jeremy.com'),
(3, 'louis', '$2a$10$2Wx0h9yRmlBhrMxzN1zS.ePNPi0rrqw/oRjSHmNKG3H6JAdr4t6/m', 'louis@louis.com');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
