INSERT INTO MEMBER(USERNAME, ENCODED_PASSWORD, USER_TYPE, USER_STATUS) VALUES('username00', 'password00', 'EXCHANGE', 'ACTIVE');
INSERT INTO MEMBER(USERNAME, ENCODED_PASSWORD, USER_TYPE, USER_STATUS) VALUES('username01', 'password01', 'EXCHANGE', 'ACTIVE');
INSERT INTO MEMBER(USERNAME, ENCODED_PASSWORD, USER_TYPE, USER_STATUS) VALUES('username02', 'password02', 'EXCHANGE', 'ACTIVE');
INSERT INTO MEMBER(USERNAME, ENCODED_PASSWORD, USER_TYPE, USER_STATUS) VALUES('username03', 'password03', 'EXCHANGE', 'ACTIVE');
INSERT INTO MEMBER(USERNAME, ENCODED_PASSWORD, USER_TYPE, USER_STATUS) VALUES('username20', 'password20', 'DUAL_DEGREE', 'ACTIVE');
INSERT INTO MEMBER(USERNAME, ENCODED_PASSWORD, USER_TYPE, USER_STATUS) VALUES('username21', 'password21', 'DUAL_DEGREE', 'ACTIVE');
INSERT INTO MEMBER(USERNAME, ENCODED_PASSWORD, USER_TYPE, USER_STATUS) VALUES('username22', 'password22', 'DUAL_DEGREE', 'ACTIVE';
INSERT INTO MEMBER(USERNAME, ENCODED_PASSWORD, USER_TYPE, USER_STATUS) VALUES('username23', 'password23', 'DUAL_DEGREE', 'ACTIVE');
INSERT INTO MEMBER(USERNAME, ENCODED_PASSWORD, USER_TYPE, USER_STATUS) VALUES('username40', 'password40', 'INTERNATIONAL', 'ACTIVE');
INSERT INTO MEMBER(USERNAME, ENCODED_PASSWORD, USER_TYPE, USER_STATUS) VALUES('username41', 'password41', 'INTERNATIONAL', 'ACTIVE');
INSERT INTO MEMBER(USERNAME, ENCODED_PASSWORD, USER_TYPE, USER_STATUS) VALUES('username42', 'password42', 'INTERNATIONAL', 'ACTIVE');
INSERT INTO MEMBER(USERNAME, ENCODED_PASSWORD, USER_TYPE, USER_STATUS) VALUES('username43', 'password43', 'INTERNATIONAL', 'ACTIVE');

INSERT INTO ARTICLE(WRITER_ID, TITLE, CONTENT, ITEM_CATEGORY, SHARE_TYPE, CREATED_AT, STATUS, DELETED) VALUES(1, 'title00', 'content00', 'SPORTS', 'DONATION', NOW(), 'ONGOING', false);
INSERT INTO ARTICLE(WRITER_ID, TITLE, CONTENT, ITEM_CATEGORY, SHARE_TYPE, CREATED_AT, STATUS, DELETED) VALUES(1, 'title01', 'content01', 'MEN_CLOTHES', 'DONATION', NOW(), 'ONGOING', false);
INSERT INTO ARTICLE(WRITER_ID, TITLE, CONTENT, ITEM_CATEGORY, SHARE_TYPE, CREATED_AT, STATUS, DELETED) VALUES(2, 'title02', 'content02', 'BEAUTY', 'DONATION', NOW(), 'ONGOING', false);
INSERT INTO ARTICLE(WRITER_ID, TITLE, CONTENT, ITEM_CATEGORY, SHARE_TYPE, CREATED_AT, STATUS, DELETED) VALUES(2, 'title03', 'content03', 'WOMEN_CLOTHES', 'DONATION', NOW(), 'ONGOING', false);
INSERT INTO ARTICLE(WRITER_ID, TITLE, CONTENT, ITEM_CATEGORY, SHARE_TYPE, CREATED_AT, STATUS, DELETED) VALUES(3, 'title04', 'content04', 'HOME_APPLIANCES', 'DONATION', NOW(), 'ONGOING', false);
INSERT INTO ARTICLE(WRITER_ID, TITLE, CONTENT, ITEM_CATEGORY, SHARE_TYPE, CREATED_AT, STATUS, DELETED) VALUES(3, 'title05', 'content05', 'WOMEN_CLOTHES', 'DONATION', NOW(), 'ONGOING', false);
INSERT INTO ARTICLE(WRITER_ID, TITLE, CONTENT, ITEM_CATEGORY, SHARE_TYPE, CREATED_AT, STATUS, DELETED) VALUES(4, 'title06', 'content06', 'BEAUTY', 'DONATION', NOW(), 'ONGOING', false);
INSERT INTO ARTICLE(WRITER_ID, TITLE, CONTENT, ITEM_CATEGORY, SHARE_TYPE, CREATED_AT, STATUS, RENTAL_START_DATE, RENTAL_END_DATE, DELETED) VALUES(4, 'title07', 'content07', 'ETC', 'RENTAL', NOW(), 'ONGOING', '2024-08-01', '2024-08-31', false);
INSERT INTO ARTICLE(WRITER_ID, TITLE, CONTENT, ITEM_CATEGORY, SHARE_TYPE, CREATED_AT, STATUS, RENTAL_START_DATE, RENTAL_END_DATE, DELETED) VALUES(5, 'title08', 'content08', 'HOME_APPLIANCES', 'RENTAL', NOW(), 'ONGOING', '2024-08-01', '2024-08-31', false);
INSERT INTO ARTICLE(WRITER_ID, TITLE, CONTENT, ITEM_CATEGORY, SHARE_TYPE, CREATED_AT, STATUS, RENTAL_START_DATE, RENTAL_END_DATE, DELETED) VALUES(5, 'title09', 'content09', 'ELECTRONIC_DEVICES', 'RENTAL', NOW(), 'ONGOING', '2024-07-01', '2024-08-31', false);
INSERT INTO ARTICLE(WRITER_ID, TITLE, CONTENT, ITEM_CATEGORY, SHARE_TYPE, CREATED_AT, STATUS, RENTAL_START_DATE, RENTAL_END_DATE, DELETED) VALUES(6, 'title10', 'content10', 'ETC', 'RENTAL', NOW(), 'ONGOING', '2024-07-01', '2024-07-31', false);
INSERT INTO ARTICLE(WRITER_ID, TITLE, CONTENT, ITEM_CATEGORY, SHARE_TYPE, CREATED_AT, STATUS, DELETED) VALUES(6, 'title11', 'content11', 'WOMEN_CLOTHES', 'DONATION', NOW(), 'ONGOING', false);
INSERT INTO ARTICLE(WRITER_ID, TITLE, CONTENT, ITEM_CATEGORY, SHARE_TYPE, CREATED_AT, STATUS, DELETED) VALUES(6, 'title12', 'content12', 'WOMEN_CLOTHES', 'DONATION', NOW(), 'ONGOING', false);
INSERT INTO ARTICLE(WRITER_ID, TITLE, CONTENT, ITEM_CATEGORY, SHARE_TYPE, CREATED_AT, STATUS, DELETED) VALUES(8, 'title13', 'content13', 'WOMEN_CLOTHES', 'DONATION', NOW(), 'ONGOING', false);
INSERT INTO ARTICLE(WRITER_ID, TITLE, CONTENT, ITEM_CATEGORY, SHARE_TYPE, CREATED_AT, STATUS, DELETED) VALUES(8, 'title14', 'content14', 'WOMEN_CLOTHES', 'DONATION', NOW(), 'ONGOING', false);
INSERT INTO ARTICLE(WRITER_ID, TITLE, CONTENT, ITEM_CATEGORY, SHARE_TYPE, CREATED_AT, STATUS, DELETED) VALUES(8, 'title15', 'content15', 'WOMEN_CLOTHES', 'DONATION', NOW(), 'ONGOING', false);
INSERT INTO ARTICLE(WRITER_ID, TITLE, CONTENT, ITEM_CATEGORY, SHARE_TYPE, CREATED_AT, STATUS, RENTAL_START_DATE, RENTAL_END_DATE, DELETED) VALUES(6, 'title16', 'content16', 'ETC', 'RENTAL', NOW(), 'ONGOING', '2023-06-12', '2023-08-22', false);

INSERT INTO ARTICLE_LIKE(USER_ID, ARTICLE_ID, CREATED_AT) VALUES(7, 1, NOW());
INSERT INTO ARTICLE_LIKE(USER_ID, ARTICLE_ID, CREATED_AT) VALUES(7, 3, NOW());
INSERT INTO ARTICLE_LIKE(USER_ID, ARTICLE_ID, CREATED_AT) VALUES(7, 2, NOW());
INSERT INTO ARTICLE_LIKE(USER_ID, ARTICLE_ID, CREATED_AT) VALUES(1, 2, NOW());
INSERT INTO ARTICLE_LIKE(USER_ID, ARTICLE_ID, CREATED_AT) VALUES(1, 3, NOW());
INSERT INTO ARTICLE_LIKE(USER_ID, ARTICLE_ID, CREATED_AT) VALUES(1, 4, NOW());
INSERT INTO ARTICLE_LIKE(USER_ID, ARTICLE_ID, CREATED_AT) VALUES(9, 1, NOW());
INSERT INTO ARTICLE_LIKE(USER_ID, ARTICLE_ID, CREATED_AT) VALUES(9, 2, NOW());
INSERT INTO ARTICLE_LIKE(USER_ID, ARTICLE_ID, CREATED_AT) VALUES(9, 3, NOW());
INSERT INTO ARTICLE_LIKE(USER_ID, ARTICLE_ID, CREATED_AT) VALUES(9, 4, NOW());
INSERT INTO ARTICLE_LIKE(USER_ID, ARTICLE_ID, CREATED_AT) VALUES(9, 16, NOW());