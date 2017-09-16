def lower_bound (mas, x):
    left = -1
    right = len(mas)
    while left+1 < right:
        mid = (left+right)/2
        if mas[mid]<x:
            left = mid
        else:
            right = mid
    return right

def upper_bound (mas, x):
    left = -1
    right = len(mas)
    while left+1 < right:
        mid = (left+right)/2
        if mas[mid]<=x:
            left = mid
        else:
            right = mid
    return left

fout = open ("mutants.out", "w")
fin = open ("mutants.in", "r")

n = int(fin.readline())
collection = map(lambda x: int(x), fin.readline().split())

m = int(fin.readline())
requests = map(lambda x: int(x), fin.readline().split())

for r in requests:
    lb = lower_bound (collection, r)
    ub = upper_bound (collection, r)
    #print lb, ub
    if lb >= n or ub < 0 or collection[lb]!=r:
        fout.write("0\n")
    else:
        fout.write("%d\n" % (ub-lb+1))