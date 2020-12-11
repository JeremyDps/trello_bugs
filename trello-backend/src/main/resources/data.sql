insert into developers (id, nom, prenom) values
(1, 'Dupuis', 'Jeremy'),
(2, 'Duchmol', 'Robert'),
(3, 'aa', 'bb');

insert into bugs (id, name, description, priority, progress, creation_date, developers_id) values
(1, 'bug 1', 'description du bug 1', 'NORMALE', 'TODO', '2020-11-29', 1),
(2, 'bug 2', 'description du bug 2', 'BASSE', 'ENCOURS', '2020-12-09', 1),
(3, 'bug 3', 'description du bug 3', 'HAUTE', 'TERMINE', '2020-12-09', 2);

insert into commentaire(id, texte, bug_id, developers_id) values
(1, 'commentaire 1 bug 1', 1, 1),
(2, 'commentaire 2 bug 1', 1, 2);