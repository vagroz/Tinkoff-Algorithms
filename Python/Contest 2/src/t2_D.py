fout = open ("ropes.out", "w")
fin = open ("ropes.in", "r")

(n, k) = map(lambda x: int(x), fin.readline().split())
mas = []
left=0
right=0
for i in range (n):
    l = int(fin.readline())
    if l>right:
        right=l
    mas.append(l)
right +=1

def count(length):
    summ = 0
    for l in mas:
        summ+= l/length
    return summ

while left+1<right:
    mid = (left+right)/2
    if count(mid)>=k:
        left=mid
    else:
        right=mid
fout.write(str(left))
print left

    
