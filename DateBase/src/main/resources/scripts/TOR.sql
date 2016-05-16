USE forum;

-- Zadanie 1: Policz wszystkich aktywnych uzytkowników forum torepublic
SELECT COUNT(*) AS active_users_count FROM users u
WHERE u.num_posts > 0 AND DATE(FROM_UNIXTIME(u.last_post)) > DATE_SUB(CURDATE(), INTERVAL 5 MONTH)
ORDER BY u.num_posts DESC;

-- Zadanie 2: Znajdz najbardziej aktywnego uzytkownika forum torrepublic w roku 2015, 2014 i wczesniej. Sam zdefiniuj kryterium "aktywnosci".
SELECT MAX(years_table.posts_count) AS max_posts, years_table.post_date AS pd, years_table.username FROM (
	SELECT u.username AS username, count(*) AS posts_count, YEAR(DATE(FROM_UNIXTIME(p.posted))) AS post_date FROM users u
	JOIN posts p ON p.poster_id = u.id
	WHERE u.num_posts > 0
	GROUP BY u.id, post_date
	ORDER BY posts_count DESC
    ) AS years_table 
WHERE 1=1
GROUP BY pd
ORDER BY pd;
-- Zadanie 3: Znajdz pieciu uzytkowników, których suma dlugosci wszystkich komentarzy jest najwieksza.
SELECT SUM(CHAR_LENGTH(p.message)) AS length, u.username AS username FROM posts p
JOIN users u ON u.id = p.poster_id
WHERE 1=1
GROUP BY u.id
ORDER BY length DESC
LIMIT 5;
-- Zadanie 4: Znajdz uzytkownika, który nigdy nie napisal zadnego komentarza.
SELECT u.username, u.last_post FROM users u
WHERE u.last_post IS NULL;
-- Zadanie 5: Znajdz uzytkowników, którzy oferowali rzeczy lub uslugi niezgodne z prawem lub karalne. :)
SELECT * FROM escrows e
WHERE LOWER(e.subject) LIKE'%hasz%' OR LOWER(e.subject) LIKE'%debit%';

-- Zadanie 6: Pogrupuj uzytkownikow po skrzynce mailowej
SELECT count(*) AS user_count, SUBSTRING_INDEX(u.email, '@', -1) AS domain FROM users u
WHERE 1=1
GROUP BY domain
ORDER BY user_count DESC;
