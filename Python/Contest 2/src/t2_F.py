fout = open ("inverse.out", "w")
fin = open ("inverse.in", "r")

n = int (fin.readline())
mas = map(lambda x: int(x), fin.readline().split())
count_inv = 0

def calculate_inv(mas1, mas2):
    j=0
    global count_inv
    for i in range(len(mas1)):
        while j<len(mas2) and mas2[j]<mas1[i]:
            count_inv+= len(mas1)-i
            j += 1
        if j==len(mas2):
            break
        


def merge_sort(mas):
    result = []
    n = len(mas)
    if n==1:
        result = mas
    else:
        left = mas[:n/2]
        right = mas[n/2:]
        left = merge_sort(left)
        right = merge_sort(right)
        calculate_inv(left, right)
        i = 0
        j = 0
        while i < len(left) and j <len(right):
            x = min(left[i], right[j])
            result.append(x)
            if left[i]==x:
                i+=1
            else:
                j+=1
        if (i<len(left)):
            result += left[i:]
        if (j<len(right)):
            result += right[j:]
        
    return result

#a=[1,117,56,78,43]
#print merge_sort(a)
merge_sort(mas)
fout.write(str(count_inv)+"\n")