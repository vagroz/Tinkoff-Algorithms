fout = open ("diplomas.out", "w")
fin = open ("diplomas.in", "r")

(w, h, n) = map (lambda x: int(x), fin.readline().split())

def is_enough(x):
    return (x/w)*(x/h)>=n

right = n*max(w,h)
left = max(w,h)-1

while left+1 < right:
    mid = (left+right)/2
    if (is_enough(mid)):
        right=mid
    else:
        left=mid

fout.write(str(right))
