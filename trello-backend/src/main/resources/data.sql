insert into developers (id, nom, prenom) values
(1, 'Dupuis', 'Jérémy'),
(2, 'Duchmol', 'Robert');

insert into bugs (id, name, description, priority, progress, creation_date, developers_id) values
(1, 'bug 1', 'description du bug 1', 'NORMALE', 'TODO', '2020-11-29', 1);

insert into commentaire(id, texte, bug_id, developers_id) values
(1, 'commentaire 1 bug 1', 1, 1);