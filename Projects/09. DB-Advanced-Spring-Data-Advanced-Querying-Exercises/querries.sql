DELIMITER $$ 
CREATE PROCEDURE usp_select_total_number_of_books_by_author(IN first_name VARCHAR(255),
															IN last_name VARCHAR(255),
                                                            OUT books_count INT) 
BEGIN 
	SET books_count = 
		(SELECT COUNT(b.book_id)
		FROM authors AS a
		JOIN books AS b ON a.author_id = b.author_id
		WHERE a.first_name = first_name AND a.last_name = last_name
		GROUP BY a.author_id);
END $$ 
DELIMITER ;

SET @count = 0;

CALL usp_select_total_number_of_books_by_author('Amanda', 'Rice', @count);
CALL usp_select_total_number_of_books_by_author('Christina', 'Jordan', @count);
CALL usp_select_total_number_of_books_by_author('Wanda', 'Morales', @count);

SELECT @count;

