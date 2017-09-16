
fout = open ("collect.out", "w")
fin = open ("collect.in", "r")

n = int(fin.readline())
collection = map(lambda x: int(x), fin.readline().split())

m = int(fin.readline())

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

requests = map(lambda x: int(x), fin.readline().split())

for r in requests:
    lb = lower_bound (collection, r)
    if lb >= n or collection[lb]!=r:
        fout.write("NO\n")
    else:
        fout.write("YES\n")
        
        
