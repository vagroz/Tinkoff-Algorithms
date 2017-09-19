fout = open ("ice-cream.out", "w")
fin = open ("ice-cream.in", "r")

(n, k) = map(lambda x: int(x), fin.readline().split())
mas = map(lambda x: int(x), fin.readline().split())

def is_possible(mas, minl):
    i = 0
    count = 0
    last = 0
    while i < len(mas):
        if i==0 or (mas[i]-last >= minl):
            count += 1
            if count == k:
                break
            last = mas[i]
        i += 1
    
    if count == k:
        return True
    else:
        return False
    
L = 1
R = mas[n-1]-mas[0]+1

while L+1<R:
    mid = (L+R)/2
    if is_possible(mas, mid):
        L = mid
    else:
        R = mid

fout.write(str(L))
print L