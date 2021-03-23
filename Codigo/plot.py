import matplotlib.pyplot as plt
x = []
y = []
n = int(input())

for i in range(0, n):
    a = list(map(int,input().strip().split()))[:n]
    x.append(a[0])
    y.append(a[1])

plt.plot(x,y,'ro-')
plt.show()
