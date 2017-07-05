################################################
########## OTIMIZAÇÃO COMBINATÓRIA #############
########  KNAPSACK SHARING PROBLEM  ############
####### Filipe Joner e Rafael Amantea ##########
#######    208840    e     228433     ##########
#########                           ############
################################################

/* set of Groups */

set G;

/* set of Items */
set I;

/* Profit and Wheight of each item */
param p{i in I, j in G};
param w{i in I, j in G};

/* Items Number */
param n;

/* Bag capacity */
param c > 0;

/* Item is in the bag: 1 if item is in the bag, 0 if not*/
var x{i in I, j in G} binary;
var minimumBestProfit;

/* Maximize profit of group with minimum profit */

maximize BestProfit: minimumBestProfit;

/* Restrição da capacidade da mochila */

s.t. maxWeight: sum{i in I, j in G} (x[i,j] * w[i,j]) <= c;
s.t. minBestProfit{g in G}: minimumBestProfit <= sum{i in I} (x[i,g]*p[i,g]);

solve;

printf "\nLucro maximo que o grupo de menor lucro pode obter: ";
printf BestProfit;
printf "\n";

end;
