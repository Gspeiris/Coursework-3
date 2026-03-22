-- This schema is for reference. The application uses Hibernate's `ddl-auto=update` feature.

CREATE TABLE IF NOT EXISTS `users` (
  `id` varchar(255) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_r43af9ap4edm43mmtq01oddj6` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `projects` (
  `id` varchar(255) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `summary` longtext,
  `tags` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `pi_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK69e2223py3wen6gjtqj3p5d0i` (`pi_id`),
  CONSTRAINT `FK69e2223py3wen6gjtqj3p5d0i` FOREIGN KEY (`pi_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `milestones` (
  `id` varchar(255) NOT NULL,
  `description` longtext,
  `due_date` date DEFAULT NULL,
  `is_completed` bit(1) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `created_by_id` varchar(255) DEFAULT NULL,
  `project_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKiwsd9u94epn20wpcxwj1spsa2` (`created_by_id`),
  KEY `FKra1j033sihc1dlq0n2rhvj5y` (`project_id`),
  CONSTRAINT `FKiwsd9u94epn20wpcxwj1spsa2` FOREIGN KEY (`created_by_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKra1j033sihc1dlq0n2rhvj5y` FOREIGN KEY (`project_id`) REFERENCES `projects` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `documents` (
  `id` varchar(255) NOT NULL,
  `description` longtext,
  `title` varchar(255) DEFAULT NULL,
  `uploaded_at` datetime(6) DEFAULT NULL,
  `url_or_path` varchar(255) DEFAULT NULL,
  `project_id` varchar(255) DEFAULT NULL,
  `uploaded_by_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqca0b97w4wp2n9s5w1jeyf5l` (`project_id`),
  KEY `FKt7p0sxt0vj0erc090tgbygpx` (`uploaded_by_id`),
  CONSTRAINT `FKqca0b97w4wp2n9s5w1jeyf5l` FOREIGN KEY (`project_id`) REFERENCES `projects` (`id`),
  CONSTRAINT `FKt7p0sxt0vj0erc090tgbygpx` FOREIGN KEY (`uploaded_by_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;