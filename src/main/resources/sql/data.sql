INSERT INTO MEMBER(USERNAME, ENCODED_PASSWORD, USER_TYPE, USER_STATUS) VALUES('username00', 'password00', 'EXCHANGE', 'ACTIVE');
INSERT INTO MEMBER(USERNAME, ENCODED_PASSWORD, USER_TYPE, USER_STATUS) VALUES('tkdgml1290', 'password01', 'EXCHANGE', 'ACTIVE');
INSERT INTO MEMBER(USERNAME, ENCODED_PASSWORD, USER_TYPE, USER_STATUS) VALUES('thsghdtjr68', 'password02', 'EXCHANGE', 'ACTIVE');
INSERT INTO MEMBER(USERNAME, ENCODED_PASSWORD, USER_TYPE, USER_STATUS) VALUES('wgwjh05169', 'password03', 'EXCHANGE', 'ACTIVE');
INSERT INTO MEMBER(USERNAME, ENCODED_PASSWORD, USER_TYPE, USER_STATUS) VALUES('gmldus01092', 'password20', 'DUAL_DEGREE', 'ACTIVE');
INSERT INTO MEMBER(USERNAME, ENCODED_PASSWORD, USER_TYPE, USER_STATUS) VALUES('rkskek0809', 'password21', 'DUAL_DEGREE', 'ACTIVE');
INSERT INTO MEMBER(USERNAME, ENCODED_PASSWORD, USER_TYPE, USER_STATUS) VALUES('akwlakr04123', 'password22', 'DUAL_DEGREE', 'ACTIVE');
INSERT INTO MEMBER(USERNAME, ENCODED_PASSWORD, USER_TYPE, USER_STATUS) VALUES('mois9876', 'password23', 'DUAL_DEGREE', 'ACTIVE');
INSERT INTO MEMBER(USERNAME, ENCODED_PASSWORD, USER_TYPE, USER_STATUS) VALUES('dkssudd1298', 'password40', 'INTERNATIONAL', 'ACTIVE');
INSERT INTO MEMBER(USERNAME, ENCODED_PASSWORD, USER_TYPE, USER_STATUS) VALUES('kokomois45679', 'password41', 'INTERNATIONAL', 'ACTIVE');
INSERT INTO MEMBER(USERNAME, ENCODED_PASSWORD, USER_TYPE, USER_STATUS) VALUES('asuhudfh4567', 'password42', 'INTERNATIONAL', 'ACTIVE');
INSERT INTO MEMBER(USERNAME, ENCODED_PASSWORD, USER_TYPE, USER_STATUS) VALUES('sisjzldkf00', 'password43', 'INTERNATIONAL', 'ACTIVE');

INSERT INTO
    ARTICLE(WRITER_ID, TITLE, CONTENT, ITEM_CATEGORY, SHARE_TYPE, CREATED_AT, STATUS, DELETED)
VALUES
    (1, '시디즈 의자', '2년 간 잘 사용하다가 이사가서 나눔합니다.', 'FURNITURE_INTERIOR', 'DONATION', NOW(), 'ONGOING', false),
    (1, '휴지', '이삿짐 정리하면서 물건 처분합니다.', 'MISCELLANEOUS_GOODS', 'DONATION', NOW(), 'ONGOING', false),
    (2, 'Computer Networking, 7/E', 'it''s a major book, and I wrote note in English.', 'BOOK_TICKET_ALBUM', 'DONATION', NOW(), 'ONGOING', false),
    (2, '노트북 거치대 나눔', '짱짱하고 튼튼합니다.', 'MISCELLANEOUS_GOODS', 'DONATION', NOW(), 'ONGOING', false),
    (3, 'GRAM2021 16inch', 'intel-i7, RAM 16GB', 'ELECTRONIC_DEVICES', 'DONATION', NOW(), 'ONGOING', false),
    (3, '공학용 계산기', '모두 잘 돼요', 'MISCELLANEOUS_GOODS', 'DONATION', NOW(), 'DONE', false),
    (4, '샤오미 C타입 충전기', '초고속 충전까지 가능한 충전기입니다.', 'ELECTRONIC_DEVICES', 'DONATION', NOW(), 'ONGOING', false),
    (6, '의자', '공간 차지가 적어서 좁은 집에서도 쓰기 좋았습니다.', 'FURNITURE_INTERIOR', 'DONATION', NOW(), 'ONGOING', false),
    (6, '멀티탭', '사용한 흔적이 조금 있어요. 감안해주세요.', 'HOME_APPLIANCES', 'DONATION', NOW(), 'ONGOING', false),
    (8, '헤어드라이기', '제가 사용하기엔 무거워서 나눔합니다.', 'HOME_APPLIANCES', 'DONATION', NOW(), 'ONGOING', false);

INSERT INTO
    ARTICLE(WRITER_ID, TITLE, CONTENT, ITEM_CATEGORY, SHARE_TYPE, CREATED_AT, STATUS, RENTAL_START_DATE, RENTAL_END_DATE, DELETED)
VALUES
    (4, '책상', '원목 책상입니다. 제가 집을 비우는 동안 빌려가 주세요.', 'FURNITURE_INTERIOR', 'RENTAL', NOW(), 'ONGOING', '2024-08-01', '2024-08-31', false),
    (5, '로지텍 키보드', '맥북과 함께 사용하기 적합한 키보드입니다.', 'ELECTRONIC_DEVICES', 'RENTAL', NOW(), 'ONGOING', '2024-08-02', '2024-08-12', false),
    (5, '의자', '허리가 편한 의자입니다. 여름 방학 동안 사용하고 돌려주세요.', 'FURNITURE_INTERIOR', 'RENTAL', NOW(), 'ONGOING', '2024-07-01', '2024-08-31', false);