insert into  WANDAUSER (id,  emailAdress, enabled, firstName, lastName,  password, role, username, aboutText) values (11, 'andika@flowsoft.com', 1,   'Andrea', 'Nagy',  '12345', 'ROLE_SUPERVISOR', 'andika', 'The names JOE NYAGGAH. I am a tireless seeker of knowledge, occassional purveyor of wisdom and also, coincidentally, a graphic designer. In as circuitous a reason as a philosopher is wont to offer, my school of thought is reliant on schooling my thought thoroughly, regularly keeping abreast of design, branding, advertising trends and solutions.');
insert into  WANDAUSER (id,  emailAdress, enabled, firstName, lastName,  password, role, username, aboutText) values (12,  'wrin@flowsoft.com', 1,   'Erwin', 'Booley',  '12345', 'ROLE_USER', 'erwin', 'I love to draw since I was a child and this hobby has leaded me into the design field. I found myself especially interested into the accessible website design ever since I come to know about the W3C web standard (year 2007). Nowadays, I spend most of my free time in researching web design technologies. I am good in constructing standard compliance XHTML website as well as interactive flash application. Hence, I have prepared myself to accept the challenges in this ever changing world and I will try my very best effort to become the TOP designer. I always believe that a good piece of design work is the combination of good layout with typography, attractive graphic, creative idea, with highly usable function, and I always do my best to meet these requirements.');
insert into  WANDAUSER (id,  emailAdress, enabled, firstName, lastName,  password, role, username, aboutText) values (13,  'noo@flowsoft.com', 1,   'Brian', 'Lindl',  '75a66ee83106aa4fc637ee30d9ec1440', 'ROLE_USER', 'noo', 'abc');
insert into  WANDAUSER (id,  emailAdress, enabled, firstName, lastName,  password, role, username, aboutText) values (14, 'brick@flowsoft.com', 1,   'Norb', 'Bricks',  '75a66ee83106aa4fc637ee30d9ec1440', 'ROLE_USER', 'brick', 'I am brick.');
insert into category (id,   categoryname, description, owner_id) values (1,  'BBC', 'BBC news', 11)
insert into category (id,   categoryname, description, owner_id) values (2,  'Personal', 'Personal things', 12)
insert into category (id,   categoryname, description, owner_id) values (3,  'Foods & Cookies', 'NomNomNom', 11)
insert into category (id,   categoryname, description, owner_id) values (4,  'Animals', 'Dogs & Cats', 12)
insert into article (id,   content, title, category_id, owner_id, rank) values (1,  'Three Kurdish women activists . including a co.founder of the militant nationalist PKK . have been found dead with gunshot wounds in a Kurdish information centre in Paris. The bodies of Sakine Cansiz and two others were found on Thursday. France and Turkey both condemned the killings. The motive for the shootings is unclear. Some 40,000 people have died in the 25.year conflict between the Turkish state and the PKK. However, Turkey has recently begun talks with the jailed PKK leader Abdullah Ocalan, with the aim of persuading the group to disarm.', 'Kurdish PKK', '1', '11', '0')
insert into article (id,   content, title, category_id, owner_id, rank) values (2,  'Qatars capital city is experiencing rapid development and some of the sights of the old city are being swept aside Doha, the capital of Qatar, is a city of contrasts. In a few decades it has been transformed from a crumbling pearling port to a gleaming metropolis. Today, its skyline is dominated by soaring skyscrapers but Old Doha . though fast disappearing . can still be found. On the dusty, uneven pavements of Al Diwan Street, deep in the old quarter of Doha, Indian and Pakistani men sit cross.legged outside tea shops and restaurants.', 'Quatar news', '1', '12','1')
insert into article (id,   content, title, category_id, owner_id, rank) values (3,  'Article content', 'Test article3', 1, 12, 2 )
insert into article (id,   content, title, category_id, owner_id, rank) values (4,  'Article content', 'Test article4', 1, 11, 5)
insert into wcomment (id,   commentcontent, commentedarticle_id, owner_id) values (1,  'comment 1', 1, 11)
insert into wcomment (id,   commentcontent, commentedarticle_id, owner_id) values (2,  'comment 2', 1, 12)
insert into TAG (id, tagname, rank) values (1,'TEST', 12)
insert into TAG (id, tagname, rank) values (2,'TAG_1', 14)
insert into TAG (id, tagname, rank  ) values (3,'TAG_2', 12)
insert into TAG (id, tagname, rank  ) values (4,'MY_TAG', 12)
insert into TAG (id, tagname, rank  ) values (5,'TAG_TAG', 16)
insert into TAG (id, tagname, rank  ) values (6,'TAG_Paramm', 12)
insert into TAG (id, tagname, rank  ) values (7,'Long lont tag', 14)
insert into TAG (id, tagname, rank  ) values (8,'TAG_3', 12)
insert into TAG (id, tagname, rank  ) values (9,'Article_TAG', 16)
insert into TAG (id, tagname, rank  ) values (10,'C.TAG', 12)
insert into TAG (id, tagname, rank  ) values (11,'F.TAG',  14)
insert into TAG (id, tagname, rank  ) values (12,'DD.TAG', 12)
