# puzzle : https://www.codingame.com/ide/puzzle/sum-of-divisors
# Ressources :
# * Python
# ** http://stackoverflow.com/questions/23287/largest-prime-factor-of-a-number/412942#412942
# ** https://www.ilemaths.net/sujet-somme-des-diviseur-d-un-entier-318665.html
# ** http://stackoverflow.com/questions/15347174/python-finding-prime-factors
# ** http://stackoverflow.com/questions/16996217/prime-factorization-list
# ** http://stackoverflow.com/questions/33269193/python-find-prime-factors
# ** http://stackoverflow.com/questions/33269193/python-find-prime-factors
# * math
# ** https://fr.wikipedia.org/wiki/Somme_des_diviseurs
# ** http://villemin.gerard.free.fr/Referenc/Prof/APROF/DivSomme.htm

# Pour résumer, il faut pour chaque nombre connaitre la liste de ses diviseurs premiers
# Une fois qu'on a cette liste on applique la formule qu'on peut trouver
# sur https://fr.wikipedia.org/wiki/Somme_des_diviseurs ou http://villemin.gerard.free.fr/Referenc/Prof/APROF/DivSomme.htm
# Dans un premier temps il nous faut donc la liste des facteurs premiers d'un nombre
# Puis dans cette liste j'ai besoin de trouver chaque facteur unique
# Pour chaque facteur unique il faut que je détermine combien de fois il est présent.
# Ces informations me permettront d'appliquer la formule. Je pourrais ainsi calculer pour chaque nombre
# mais j'ai l'impression que c'est bien compliqué cette affaire... pas sûr que je n'explose pas toujours le temps
# Je me demande si le "trick" n'est pas ailleurs

# http://stackoverflow.com/questions/2600191/how-can-i-count-the-occurrences-of-a-list-item-in-python
#

import sys
import math
import pprint

def sum_divisors(num):
    the_sum = num
    for i in range(2,num):
        if num % i == 0:
            the_sum += i
    return the_sum

def prime_factors(n):
    """Returns all the prime factors of a positive integer"""
    factors = []
    d = 2
    while n > 1:
        while n % d == 0:
            factors.append(d)
            n //= d
        d = d + 1
        if d*d > n:
            if n > 1: factors.append(n)
            break
    return factors

n = int(input())

pfs = prime_factors(n)
print("List of prime factors of {} : {}".format(n, ', '.join(str(divisor) for divisor in pfs)), file=sys.stderr)
largest_prime_factor = max(pfs)
print("largest_prime_factor {}".format(largest_prime_factor), file=sys.stderr)
pfs_dict= dict((x,pfs.count(x)) for x in set(pfs))
pp=pprint.PrettyPrinter(stream=sys.stderr, compact=True)
pp.pprint(pfs_dict)

total_sum = n

if n>1:
    for i in range(2,n+1):
        total_sum += sum_divisors(i)

print(total_sum)
