import sys
import math

# Auto-generated code below aims at helping you parse
# the standard input according to the problem statement.
# To debug: print("Debug messages...", file=sys.stderr)

n = int(input())
dot = False
minus = False
numbers = []
characters = input()
print("characters : {}".format(characters), file=sys.stderr)
for c in characters.split(' '):
    if c == '.': #Est-ce que le caractère lu est un '.'
        dot = True
    elif c == '-': #Is this a - minus sign
        minus = True
    else : #Is this a number
        numbers.append(int(c))

print("numbers : {}".format(",".join(map(str,numbers))), file=sys.stderr)
greatest = ""
number_of_zeros=numbers.count(0)
print("number_of_zeros : {}".format(number_of_zeros), file=sys.stderr)
if number_of_zeros == len(numbers):
    greatest = "0"
else :
    if minus: # On cherche à faire le nombre le plus petit possible en valeur absolue
        numbers.sort()
        greatest += '-'
        del numbers[0:number_of_zeros]
        if number_of_zeros > 0 and dot:
            greatest += '0'
            number_of_zeros -= 1
        else:
            greatest += str(numbers[0])
            del numbers[0:1]
        if dot:
            greatest += '.'
        if number_of_zeros > 0:
            greatest += '0' * number_of_zeros
        for n in numbers:
            greatest += str(n)
    else: #On cherche à faire le nombre le plus grand possible
        numbers.sort(reverse=True)
        if number_of_zeros > 0:
            del numbers[-number_of_zeros:]
            #qu'il y ait un '.' ou pas, s'il y des zeros, on fait un nombre sans partie décimale
            for i in range(len(numbers)):
                greatest += str(numbers[i])
            if dot:
                if number_of_zeros > 1:
                    greatest += '0' * (number_of_zeros - 1)
            else:
                greatest += '0' * number_of_zeros
            print("greatest : {}".format(greatest), file=sys.stderr)
        else:
            if dot:
                for i in range(len(numbers)-1):
                    greatest += str(numbers[i])
                greatest += '.' + str(numbers[len(numbers)-1])
                print("greatest : {}".format(greatest), file=sys.stderr)
            else:
                for i in range(len(numbers)):
                    greatest += str(numbers[i])
                print("greatest : {}".format(greatest), file=sys.stderr)
print(greatest)