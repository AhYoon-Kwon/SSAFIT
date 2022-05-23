DROP SCHEMA ssafit;
CREATE SCHEMA IF NOT EXISTS `ssafit` DEFAULT CHARACTER SET utf8mb4 ;
USE `ssafit` ;
set sql_safe_updates=0;

-- -----------------------------------------------------
-- Table `video`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `video` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `video_key` VARCHAR(100) NOT NULL,
  `title` VARCHAR(100) NOT NULL,
  `url` VARCHAR(220) NOT NULL,
  `part` VARCHAR(50) NOT NULL,
  `channel_name` VARCHAR(50) NOT NULL,
  `view_cnt` INT NULL,
  PRIMARY KEY (`id`));


-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `user` (
  `id` INT AUTO_INCREMENT NOT NULL,
  `userid` VARCHAR(50) NOT NULL,
  `nickname` VARCHAR(45) NOT NULL,
  `pw` VARCHAR(100) NOT NULL,
  `email` VARCHAR(100) NOT NULL UNIQUE,
  `profile` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`));
  
-- -----------------------------------------------------
-- Table `review`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `review` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `vid` INT NOT NULL,
  `uid` INT NOT NULL,
  `rate` INT DEFAULT 0,
  `content` TEXT NOT NULL,
  `time` DATETIME DEFAULT NOW(),
  `depth` INT DEFAULT 0,
  `re_id` INT NULL,
  
  CONSTRAINT check( `rate` >=0 and `rate`<=10),
  PRIMARY KEY (`id`));

-- -----------------------------------------------------
-- Table `likes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `likes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `uid` INT NOT NULL,
  `liked` INT NOT NULL,
  FOREIGN KEY(`liked`) REFERENCES `video`(`id`),
  FOREIGN KEY(`uid`) REFERENCES `user`(`id`),
  PRIMARY KEY (`id`));
  
  -- -----------------------------------------------------
-- Table `part`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS part (
  id INT NOT NULL AUTO_INCREMENT,
  part VARCHAR(30),
  PRIMARY KEY (id));
  
-- -----------------------------------------------------
-- Table `watched`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `watched` (
  `id` int NOT NULL AUTO_INCREMENT,
  `uid` INT NOT NULL,
  `watched` INT NOT NULL,
  FOREIGN KEY(`watched`) REFERENCES `video`(`id`),
  FOREIGN KEY(`uid`) REFERENCES `user`(`id`),
  PRIMARY KEY (`id`));
  

commit;
SELECT * FROM video;
select * from user;
select * from likes;


DESC video;
DESC user;
DESC review;
DESC likes;
DESC watched;

SELECT * FROM video;

INSERT INTO user(userid, nickname, pw, email) VALUES
("hi", "안녕", "54878", "hi@com"),
("seongeun", "성은", "12345", "se@com"),
("hyunho", "현호", "12345", "hh@com"),
("ahyoun", "아윤", "12345", "ay@com"),
("junsung", "준성", "12345", "js@com"),
("eunjung", "은정", "12345", "ej@com"),
("daeun", "다은", "12345", "de@com");

INSERT INTO review (VID, UID, RATE, CONTENT, TIME, DEPTH, RE_ID)
values
(2, 1, 10, '재밌어요 또 보고 싶어요', now(), 0, 1), 
(2, 5, 0, '저두요', now(), 1, 1), 
(2, 2, 0, '인정~', now(), 1, 1),
(2, 2, 4, '음 그닥..', now(), 0, 4), 
(2, 3, 8, '좋았습니다', now(), 0, 5),
(2, 4, 4, '그저 그랬습니다', now(), 0, 6), 
(1, 1, 4, '이건 뭐지', now(), 0, 7), 
(1, 3, 10, '호불호갈릴듯 오히려조아~', now(), 0, 8), 
(3, 6, 10, '띵작', now(), 0, 9), 
(3, 4, 0, '킹정합니다', now(), 1, 9);

INSERT INTO part (part)
  VALUES ("등"), ("어깨"), ("가슴"), ("하체"), ("이두"), ("삼두"), ("복부");
  
INSERT INTO video (video_key, title, url, part, channel_name)
 VALUES
("o_pwMN40DLM","등 운동, 이거 하나면 됩니다!","https://i.ytimg.com/vi/o_pwMN40DLM/default.jpg","등","피지컬갤러리"),
("oThYdH8ejXk","1300만 운동 유튜버의 &#39;넓은 등근육&#39;만드는 방법!?","https://i.ytimg.com/vi/oThYdH8ejXk/default.jpg","등","보통사람을 위한 운동채널"),
("cfioLE83by0","짧고 굵게 5분도 충분한 등운동 l 효과최고!","https://i.ytimg.com/vi/cfioLE83by0/default.jpg","등","소미핏 SomiFit"),
("Yroqe56nNpM","등 훈련하는 가장 좋은 운동루틴 6가지","https://i.ytimg.com/vi/Yroqe56nNpM/default.jpg","등","준규빌더"),
("VNFAfAM35QA","다음날 등이 안펴집니다","https://i.ytimg.com/vi/VNFAfAM35QA/default.jpg","등","말왕TV"),
("EEqGCoTuYfQ","최고의 등 운동, 완벽한 바벨로우 강의 (운동의 정석)","https://i.ytimg.com/vi/EEqGCoTuYfQ/default.jpg","등","피지컬갤러리"),
("7YenKNY3-YU","역삼각형 벌어지는 프레임을 갖게해준 등 운동법","https://i.ytimg.com/vi/7YenKNY3-YU/default.jpg","등","지기TV"),
("pKoR-uJd_vk","상의 탈의하고 등 운동 + 초중급자 1시간 운동 할때 추천 운동법","https://i.ytimg.com/vi/pKoR-uJd_vk/default.jpg","등","강경원"),
("4t9u85AHQR0","요일별운동 화요일 등/허리 군살 모조리 파.괴.한.다💥 등운동 루틴","https://i.ytimg.com/vi/4t9u85AHQR0/default.jpg","등","힙으뜸"),
("E7TQPZCTYIs","등운동에 이걸 빼먹진 않겠죠? 등운동의 기초&amp;팁","https://i.ytimg.com/vi/E7TQPZCTYIs/default.jpg","등","김강민_Kim Kang min"),
("fr-5MKeRfVU","알고하는것과 모르고하는것은 다른 , 어깨넓히는 필수운동 [레이즈]","https://i.ytimg.com/vi/fr-5MKeRfVU/default.jpg","어깨","김강민_Kim Kang min"),
("cBZnbTeld2w","어깨운동루틴 5가지 I 내츄럴 운동법+꿀팁","https://i.ytimg.com/vi/cBZnbTeld2w/default.jpg","어깨","핏블리 FITVELY"),
("TN1UO5eziCU","근육량 세계1위는 어떤 &#39;어깨운동&#39;을 할까? (ft.미스터 올림피아 크리스 범스테드)","https://i.ytimg.com/vi/TN1UO5eziCU/default.jpg","어깨","보통사람을 위한 운동채널"),
("yxSt-h9A7_k","기다렸어? 내가 제일 효과 많이 본 어깨운동 6가지","https://i.ytimg.com/vi/yxSt-h9A7_k/default.jpg","어깨","준규빌더"),
("8thMw9JyxBE","어깨 운동만 &#39;한&#39; 남자","https://i.ytimg.com/vi/8thMw9JyxBE/default.jpg","어깨","김종국 GYM JONG KOOK"),
("8wNwg2mO5T8","&#39;어좁이&#39;가 &#39;어깨깡패&#39;가 되는 확실한 방법. (라이언테리의 프레임을 넓혀준 어깨 운동 루틴)","https://i.ytimg.com/vi/8wNwg2mO5T8/default.jpg","어깨","육체미 빅터"),
("pzXiTG1rbs0","헬스장 안가도 됩니다! 집에서 어깨근육 키우는 3가지방법.","https://i.ytimg.com/vi/pzXiTG1rbs0/default.jpg","어깨","보통사람을 위한 운동채널"),
("gsFShSYOovU","Lv.3 덤벨 하나로 어깨 깡패 만드는 루틴 (떠먹는홈트)","https://i.ytimg.com/vi/gsFShSYOovU/default.jpg","어깨","권혁 Hulk's TV"),
("_geC1KPo2og","어깨운동의 정석 ㅡ덤벨 프레스(김명섭과 함께 배워보는 어깨운동의 기본-덤벨프레스)","https://i.ytimg.com/vi/_geC1KPo2og/default.jpg","어깨","김명섭의 헬스교실"),
("hS7t-GzeyvE","열심히 어깨운동을 해도 어깨뽕이 안생기는 이유. 헬스 한다면 꼭 보세요!","https://i.ytimg.com/vi/hS7t-GzeyvE/default.jpg","어깨","지기TV"),
("bfxo9Phdkro","전세계가 경악한 &#39;가슴근육&#39; 2배 키우는 미친운동!? (※벤치프레스 아님 주의!)","https://i.ytimg.com/vi/bfxo9Phdkro/default.jpg","가슴","보통사람을 위한 운동채널"),
("UqlUZFZgczk","초보자를 위한 황철순의 가슴운동 루틴","https://i.ytimg.com/vi/UqlUZFZgczk/default.jpg","가슴","CHUL SOON HWANG"),
("qrt2fy_wmtU","가슴운동 따라해보세요! 설기관 루틴공개.","https://i.ytimg.com/vi/qrt2fy_wmtU/default.jpg","가슴","설기관"),
("d9m--yp_wyk","제발 가슴운동좀 하세요!! 여자 가슴운동루틴 순서+횟수+셋트 전부 알려드립니다","https://i.ytimg.com/vi/d9m--yp_wyk/default.jpg","가슴","핏블리 FITVELY"),
("_is-VfJW44Y","가슴근육을 키우려면 당신은 &#39;이 운동&#39;을 해야합니다!","https://i.ytimg.com/vi/_is-VfJW44Y/default.jpg","가슴","보통사람을 위한 운동채널"),
("c_5ENJWekbQ","Lv.4 10분 만에 집에서 가슴 작살내는 루틴 (누구나 쉽게 가능) [10mins Intense Chest Workout]","https://i.ytimg.com/vi/c_5ENJWekbQ/default.jpg","가슴","권혁 Hulk's TV"),
("Lsh5fbtaWyM","&#39;가슴 근육&#39;이 빠르게 커지는 놀라운 방법 3가지! (가슴운동, 벤치프레스)","https://i.ytimg.com/vi/Lsh5fbtaWyM/default.jpg","가슴","헬창극장"),
("mnLzv7brYxA","김강민의 가슴 운동법, 루틴","https://i.ytimg.com/vi/mnLzv7brYxA/default.jpg","가슴","김강민_Kim Kang min"),
("Eg8pR57_BB8","처진가슴운동 볼륨업운동 chest workout","https://i.ytimg.com/vi/Eg8pR57_BB8/default.jpg","가슴","삐약스핏 [살빼주는 병맛 다이어트채널]"),
("58ce8JMEpTA","벌크업 가슴 운동 루틴 레즈고🔥 / IFBBPRO / 이준호","https://i.ytimg.com/vi/58ce8JMEpTA/default.jpg","가슴","이준호의 The First"),
("QSCeJ__akl0","스쿼트 없이 하체를 키우는 최고의 하체운동","https://i.ytimg.com/vi/QSCeJ__akl0/default.jpg","하체","근력발전소"),
("tzN6ypk6Sps","하체운동이 중요한 이유? 이것만 보고 따라하자 ! [하체운동 교과서]","https://i.ytimg.com/vi/tzN6ypk6Sps/default.jpg","하체","김강민_Kim Kang min"),
("js8z5wIZ0wg","(층간소음X, 설명O) 진짜 힘듦주의.. 🔥초강력🔥 힙으뜸 하체운동 2주 챌린지","https://i.ytimg.com/vi/js8z5wIZ0wg/default.jpg","하체","힙으뜸"),
("3TyTGxBNwic","&#39;스쿼트&#39;를 하지 않고 하체 근육을 키우는 가장 빠른방법!? (feat.허벅지/엉덩이)","https://i.ytimg.com/vi/3TyTGxBNwic/default.jpg","하체","보통사람을 위한 운동채널"),
("9ehdY_9B5jY","&#39;종아리&#39;와 &#39;허벅지&#39;를 동시에 키우는 하체 운동 루틴!","https://i.ytimg.com/vi/9ehdY_9B5jY/default.jpg","하체","쇠질연구소"),
("KXYi6bI-UPE","Lv.5 13분만에 하체 마비시키는 루틴! 근육통100% 옵니다. (누구나 집에서 가능) 13mins intense Legs Workout","https://i.ytimg.com/vi/KXYi6bI-UPE/default.jpg","하체","권혁 Hulk's TV"),
("u5OgcZdNbMo","저는 &#39;하체&#39;식주의자 입니다..","https://i.ytimg.com/vi/u5OgcZdNbMo/default.jpg","하체","김종국 GYM JONG KOOK"),
("NDsjmxTROEo","하체비만 11자다리 최고의 운동 [하체 핵매운맛]","https://i.ytimg.com/vi/NDsjmxTROEo/default.jpg","하체","Thankyou BUBU"),
("pDFuLG0xrsU","🏅요일별운동🏅 금요일 하체집중 근력운동 15분 루틴!","https://i.ytimg.com/vi/pDFuLG0xrsU/default.jpg","하체","힙으뜸"),
("dpBYYEhdofI","앞벅지 볼록, 뒷벅지 셀룰라이트, 허벅지 안쪽살 모조리 불태우고🔥 [여리탄탄 일자 허벅지] 되는 7일 루틴","https://i.ytimg.com/vi/dpBYYEhdofI/default.jpg","하체","이지은 다이어트 Jiny diet"),
("NqSfpsJn8M8","당신에게 필요한 팔 운동을 찾아드립니다","https://i.ytimg.com/vi/NqSfpsJn8M8/default.jpg","이두","김강민_Kim Kang min"),
("JY-RTxvAjAs","반팔 꽉차게 입는 방법!! 이두운동 3가지","https://i.ytimg.com/vi/JY-RTxvAjAs/default.jpg","이두","준규빌더"),
("n4L2Kvdtg-8","전완근과 이두근 운동, 생각보다 어려운 이두근 운동 - 해머 컬, 얼터네이트 컬","https://i.ytimg.com/vi/n4L2Kvdtg-8/default.jpg","이두","강경원"),
("qkQdIMW1xlw","Lv.2 꽉! 차는 머슬핏 만드는 팔 루틴 (이두,삼두) 덤벨필요","https://i.ytimg.com/vi/qkQdIMW1xlw/default.jpg","이두","권혁 Hulk's TV"),
("tONpT5wPXwU","도망가다 붙잡혀서, 이두운동 Biceps workout (ENG Subtitle)","https://i.ytimg.com/vi/tONpT5wPXwU/default.jpg","이두","CHUL SOON HWANG"),
("JwbdNTw9om8","이두근 운동 루틴 l 두꺼운 팔을 위한 4가지 베이직 운동법 l 초급자 상급자 둘다 추천","https://i.ytimg.com/vi/JwbdNTw9om8/default.jpg","이두","홍지원 V 피지크"),
("6WPslmiv_yI","ENG SUB) 이두 운동시 최악의 실수 7가지, Top 7 Worst Biceps Mistakes to Avoid","https://i.ytimg.com/vi/6WPslmiv_yI/default.jpg","이두","고독한갯츠비"),
("y3aMzyKqvNM","덤벨컬 - 이두 운동 #2 [확실하게 팔 키우는 방법]","https://i.ytimg.com/vi/y3aMzyKqvNM/default.jpg","이두","설기관"),
("XAFQmkdH614","이두근 운동의 종류가 많은 이유와 최고의 운동 선택 방법","https://i.ytimg.com/vi/XAFQmkdH614/default.jpg","이두","세계적으로 유명한 운동 정보"),
("9GcM8R_uuQQ","과학이 밝혀낸 가장 효율적으로 &#39;굵은 팔&#39; 만드는 방법 (1/2)","https://i.ytimg.com/vi/9GcM8R_uuQQ/default.jpg","이두","시금치맨 운동상식"),
("H7chkYEglGg","NABBA 프로 3연속 우승을 만들어준 팔운동","https://i.ytimg.com/vi/H7chkYEglGg/default.jpg","삼두","김강민_Kim Kang min"),
("4vSP9HK3EN0","&#39;팔 사이즈&#39; 2배 키워주는 미친 운동방법!? (팔둘레 늘리는 삼두근 운동)","https://i.ytimg.com/vi/4vSP9HK3EN0/default.jpg","삼두","보통사람을 위한 운동채널"),
("ogyGQxuxxn4","라잉 트라이셉 익스텐션 l 제가 가장 추천하고 효과적인 삼두 운동","https://i.ytimg.com/vi/ogyGQxuxxn4/default.jpg","삼두","강경원"),
("5azIr-srI6U","어깨와팔꿈치아픈 헬린이들을 위한 삼두운동의 모든것!ㅡ김명섭과 함께 해부학적으로 풀어가는 쉽고 재미있는 팔운동^^","https://i.ytimg.com/vi/5azIr-srI6U/default.jpg","삼두","김명섭의 헬스교실"),
("OVxquTe8OmU","프레스 다운 - 삼두 운동#1","https://i.ytimg.com/vi/OVxquTe8OmU/default.jpg","삼두","설기관"),
("yCRy1o7tFnE","삼두 운동 딱 하나만 한다면 뭐가 제일 좋을까?","https://i.ytimg.com/vi/yCRy1o7tFnE/default.jpg","삼두","세계적으로 유명한 운동 정보"),
("EiEoZ0tlJ7o","덤벨로하는 최고의 팔운동/삼두운동 Best (오버헤드 익스텐션)","https://i.ytimg.com/vi/EiEoZ0tlJ7o/default.jpg","삼두","키다리형"),
("ObEtLS9heOo","많은 분들이 잘 못하고 있는 운동, 초중급자 추천 팔 운동 l 케이블 푸쉬다운","https://i.ytimg.com/vi/ObEtLS9heOo/default.jpg","삼두","강경원"),
("H-d4uvT9J2Y","김명섭과 함께 하는 삼두운동 ㅡ라잉 트라이셉스(팔꿈치를 안아프게!)","https://i.ytimg.com/vi/H-d4uvT9J2Y/default.jpg","삼두","김명섭의 헬스교실"),
("3Nqt05cRhpU","팔이 커지고 싶다면 꼭 해야되는 삼두운동 l 덤벨 오버헤드 익스텐션","https://i.ytimg.com/vi/3Nqt05cRhpU/default.jpg","삼두","한조바디"),
("PjGcOP-TQPE","11자복근 복부 최고의 운동 [복근 핵매운맛]","https://i.ytimg.com/vi/PjGcOP-TQPE/default.jpg","복부","Thankyou BUBU"),
("7TLk7pscICk","(Sub)누워서하는 5분 복부운동!! 효과보장! (매일 2주만 해보세요!)","https://i.ytimg.com/vi/7TLk7pscICk/default.jpg","복부","소미핏 SomiFit"),
("kETh8T3it4k","(층간소음X, 설명O) 복근운동과 유산소를 한번에❗️서서하는 복근운동 1탄🔥","https://i.ytimg.com/vi/kETh8T3it4k/default.jpg","복부","힙으뜸"),
("zcQ16cfJN9Q","9분! 초간단 누워서하는 11자 복근운동","https://i.ytimg.com/vi/zcQ16cfJN9Q/default.jpg","복부","Thankyou BUBU"),
("SKCEpgEucFM","복근 운동 마스터 하기 -  어떤 복근 운동을 해야 효과적일까?","https://i.ytimg.com/vi/SKCEpgEucFM/default.jpg","복부","강경원"),
("sqQpL1wKW6M","12분 서서하는 복근운동 홈트레이닝 - 체지방 태우기는 보너스","https://i.ytimg.com/vi/sqQpL1wKW6M/default.jpg","복부","빅씨스"),
("sVQqBDBZhmI","복부운동 짧고 굵게! 운동효율 갑!  [6 MINS ABS WORKOUT]","https://i.ytimg.com/vi/sVQqBDBZhmI/default.jpg","복부","소미핏 SomiFit"),
("lRJZC342Bs0","서서하는 복근운동 이 영상하나로 끝! 16MIN STANDING ABS FOR BEGINNER","https://i.ytimg.com/vi/lRJZC342Bs0/default.jpg","복부","MIZI"),
("iOSYLKBk894","무.조.건! 뱃살 빠지는 운동 베스트5","https://i.ytimg.com/vi/iOSYLKBk894/default.jpg","복부","Thankyou BUBU"),
("p623pewgTc0","[10분] 비곗살타파 매운맛 레전드운동! 2주후 식스팩 생겨요 (효과 최고!)","https://i.ytimg.com/vi/p623pewgTc0/default.jpg","복부","소미핏 SomiFit");

SELECT r.id, rate, content, r.time, r.depth, r.re_id FROM review r
    JOIN user u
    ON r.uid = u.id
    WHERE vid = 1;