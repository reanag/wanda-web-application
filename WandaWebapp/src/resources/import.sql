insert into  WANDAUSER (id, createdTS, emailAdress, enabled, firstName, lastName, modifiedTS, password, role, username) values ('11', '2012-11-10', 'andika@flowsoft.com', 'true',   'Andrea', 'Nagy', '2012-11-11', '12345', 'ROLE_SUPERVISOR', 'andika');
insert into  WANDAUSER (id, createdTS, emailAdress, enabled, firstName, lastName, modifiedTS, password, role, username) values ('12', '2012-11-10', 'wrin@flowsoft.com', 'true',   'Erwin', 'Booley', '2012-11-11', '12345', 'ROLE_USER', 'erwin');
insert into  WANDAUSER (id, createdTS, emailAdress, enabled, firstName, lastName, modifiedTS, password, role, username) values ('13', '2012-11-10', 'noo@flowsoft.com', 'true',   'Brian', 'Lindl', '2012-11-11', '75a66ee83106aa4fc637ee30d9ec1440', 'ROLE_USER', 'noo');
insert into  WANDAUSER (id, createdTS, emailAdress, enabled, firstName, lastName, modifiedTS, password, role, username) values ('14', '2012-11-10', 'brick@flowsoft.com', 'true',   'Norb', 'Bricks', '2012-11-11', '75a66ee83106aa4fc637ee30d9ec1440', 'ROLE_USER', 'brick');
insert into TAG (id, tagname, rank, createdTS, modifiedTS) values ('1','TEST', '12','2012-12-12', '2013-01-10')
insert into TAG (id, tagname, rank, createdTS, modifiedTS) values ('2','TAG_1', '14','2012-12-12', '2013-01-10')
insert into TAG (id, tagname, rank, createdTS, modifiedTS) values ('3','TAG_2', '12','2012-12-12', '2013-01-10')
insert into TAG (id, tagname, rank, createdTS, modifiedTS) values ('4','MY_TAG', '12','2012-12-12', '2013-01-10')
insert into TAG (id, tagname, rank, createdTS, modifiedTS) values ('5','TAG_TAG', '16','2012-12-12', '2013-01-10')
insert into TAG (id, tagname, rank, createdTS, modifiedTS) values ('6','TAG_Paramm', '12','2012-12-12', '2013-01-10')
insert into TAG (id, tagname, rank, createdTS, modifiedTS) values ('7','Long lont tag', '14','2012-12-12', '2013-01-10')
insert into TAG (id, tagname, rank, createdTS, modifiedTS) values ('8','TAG_3', '12','2012-12-12', '2013-01-10')
insert into TAG (id, tagname, rank, createdTS, modifiedTS) values ('9','Article_TAG', '16','2012-12-12', '2013-01-10')
insert into TAG (id, tagname, rank, createdTS, modifiedTS) values ('10','C-TAG', '12','2012-12-12', '2013-01-10')
insert into TAG (id, tagname, rank, createdTS, modifiedTS) values ('11','F-TAG', '12','2012-12-12', '2013-01-10')
insert into TAG (id, tagname, rank, createdTS, modifiedTS) values ('12','DD-TAG', '12','2012-12-12', '2013-01-10')
insert into category (id, createdts, modifiedts, categoryname, description, owner_id) values ('1', '2012-01-01', '2012-01-01', 'BBC', 'BBC news', '11')
insert into category (id, createdts, modifiedts, categoryname, description, owner_id) values ('2', '2012-01-01', '2012-01-01', 'Personal', 'Personal things', '12')
insert into category (id, createdts, modifiedts, categoryname, description, owner_id) values ('3', '2012-01-01', '2012-01-01', 'Foods & Cookies', 'NomNomNom', '11')
insert into category (id, createdts, modifiedts, categoryname, description, owner_id) values ('4', '2012-01-01', '2012-01-01', 'Animals', 'Dogs & Cats', '12')
insert into article (id, createdts, modifiedts, content, title, category_id, owner_id) values ('1', '2012-01-01', '2012-01-01', 'Article content', 'Test article1', '1', '11')
insert into article (id, createdts, modifiedts, content, title, category_id, owner_id) values ('2', '2012-01-01', '2012-01-01', 'Article content', 'Test article2', '1', '12')
insert into article (id, createdts, modifiedts, content, title, category_id, owner_id) values ('3', '2012-01-01', '2012-01-01', 'Article content', 'Test article3', '1', '12')
insert into article (id, createdts, modifiedts, content, title, category_id, owner_id) values ('4', '2012-01-01', '2012-01-01', 'Article content', 'Test article4', '1', '11')
insert into comment (id, createdts, modifiedts, commentcontent, commentedarticle_id, owner_id) values ('1', '2012-01-01', '2012-01-01', 'comment 1', '1', '11')
insert into comment (id, createdts, modifiedts, commentcontent, commentedarticle_id, owner_id) values ('2', '2012-01-01', '2012-01-01', 'comment 2', '1', '12')