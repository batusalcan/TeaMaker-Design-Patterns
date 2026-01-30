
CREATE DATABASE teamaker_db;
USE teamaker_db;
CREATE TABLE brewing_log (
    id INT AUTO_INCREMENT PRIMARY KEY,
    num_cups INT NOT NULL,
    brew_date DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- 1. PROCEDURE TO ADD A LOG
-- It takes the number of cups and saves it with the current time.
DROP PROCEDURE IF EXISTS sp_insert_log;

DELIMITER //
CREATE PROCEDURE sp_insert_log(IN p_cups INT)
BEGIN
    INSERT INTO brewing_log (num_cups, brew_date) 
    VALUES (p_cups, NOW());
END //
DELIMITER ;


-- 2. PROCEDURE TO GET TOTAL CUPS
-- It takes the Month and Year, returns the total sum.
DROP PROCEDURE IF EXISTS sp_get_monthly_total;

DELIMITER //
CREATE PROCEDURE sp_get_monthly_total(IN p_month INT, IN p_year INT)
BEGIN
    SELECT SUM(num_cups) 
    FROM brewing_log 
    WHERE MONTH(brew_date) = p_month AND YEAR(brew_date) = p_year;
END //
DELIMITER ;

-- 3. PROCEDURE TO GET DALY CUPS
DELIMITER //
	CREATE PROCEDURE sp_get_daily_total(IN p_date DATE)
BEGIN
    SELECT SUM(num_cups) AS total_cups
    FROM brewing_log 
    WHERE DATE(brew_date) = p_date;
END //

DELIMITER ;
 
 select * from brewing_log;