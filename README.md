# pglp_9.9

Guide d'utilisation
-

Le programme principal se compose de 6 commandes :
* La commande **new** permet de créer des figures ou des groupes
* La commande **del** permet de supprimer des figures, des groupes ou bien de supprimer d'un groupe une figure
* La commande **move** permet de bouger une figure ou un groupe de figure
* La commande **print** qui permet d'afficher une figure, un groupe de figure ou bien les
tables de la BD
* La commande **reset** qui permet de supprimer toutes les formes et tout les groupes stocker dans la BD
* La commande **exit** qui permet de quitter le programme

#### new
Les synopsis possible de la commande new sont les suivants :
* `new circle nameCircle centerX centerY radius`
* `new rectangle nameRectangle BottomLeftPointX BottomLeftPointY L H`
* `new square nameSquare BottomLeftPointX BottomLeftPointY size`
* `new triangle nameTriangle p1X p1Y p2X p2Y p3X p3Y`
* `new group nameGroup nameShape`

La figure nameShape doit **impérativement** e^tre déjà stocké dans la BD pour être attribuée au groupe

#### del
Les synopsis possible de la commande del sont les suivants :
* `del shape nameShape`
* `del group nameGroup`
* `del unlink nameGroup nameShape`

La commande `del unlink nameGroup nameShape` permet de retirer une figure d'un groupe

La commande `del shape nameShape` supprimera naturellement les liaisons entre la figure supprimé et tous les groupes
auquel elle était liée

#### move
Les synopsis possible de la commande move sont les suivants :
* `move shape nameShape dx dy`
* `move group nameGroup dx dy`

Le déplacement est une translation des objets. Leur position après le mouvement sera leur ancienne position + dy et dy.

La commande `move group nameGroup dx dy` permet de déplacer tout les membres d'un groupe d'un seul coup

#### print
Les synopsis possible de la commande move sont les suivants :
* `print shape nameShape`
* `print group nameGroup`
* `print table`

#### reset
Le synopsis de la commande reset est le suivant :
* `reset`

Cette commande à pour effet de supprimer supprimer entièrement la BD

