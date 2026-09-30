import numpy as np
import matplotlib.pyplot as plt

loi_x=np.zeros((2,4))
loi_x[0]=[1,2,4,5]
for i in range(len(loi_x[1])):
    loi_x[1][i]=(1/10)*(3-loi_x[0][i])**2
print("loi_x :",loi_x)
plt.bar(loi_x[0],loi_x[1])
plt.title('Loi de X')
plt.show()
loi_x_cum=loi_x.copy()
loi_x_cum[1]=[np.sum(loi_x[1][:i+1])for i in range(len(loi_x[1]))]
fct_rep=np.insert(loi_x_cum,0,[-1,0],axis=1)
fct_rep=np.insert(loi_x_cum,len(loi_x_cum[0]),[7,1],axis=1)
print("loi_x_cum :",loi_x_cum)
print("fct_rep :",fct_rep)

for i in range(len(fct_rep[0])-1):
    plt.plot([fct_rep[0][i],fct_rep[0][i+1]],[fct_rep[1][i],fct_rep[1][i]],'b')
plt.grid()
plt.show()

#Espérance
def esperance_v1(loi):
    esp=0
    for i in range(len(loi[0])):
        esp+=loi[0][i]*loi[1][i]
    return esp

def esperance_v2(loi):
    return np.sum(loi_x[0]*loi_x[1])

print("esperance : ",esperance_v1(loi_x),esperance_v2(loi_x))

#Variance

def variance_v1(loi):
    var=0
    esp=esperance_v1(loi)
    for i in range(len(loi[0])):
        var+=(loi_x[0][i]-esp)**2*loi_x[1][i]
    return var

def variance_v2(loi):
    var=0
    esp=esperance_v1(loi)
    for i in range(len(loi[0])):
        var+=(loi_x[0][i])**2*loi_x[1][i]
    return var-esp**2

print(variance_v1(loi_x),variance_v2(loi_x))

#Simulation loi X

def simulation(loi,n):
    loi_cum=loi.copy()
    loi_cum[1]=np.cumsum(loi_cum[1])
    U=np.random.rand(n)
    res=[]
    for u in U:
        i=0
        while loi_cum[1][i]<u:  
            i=i+1
        res.append(loi_cum[0][i])
    return np.array(res)

vect_x=simulation(loi_x,1000)
print("moyenne_simu : ",np.mean(vect_x))

#Répartition moyenne sur grand nombre d'échantillons
def compar_repart_moy(loi,vect_n,nb_ech):
    fig,axs=plt.subplots(1,len(vect_n))
    vect_moyenne=np.zeros((len(vect_n),nb_ech))
    for i in range(len(vect_n)):
        for j in range(nb_ech):
            vect_moyenne[i][j]=np.mean(simulation(loi,vect_n[i]))
        axs[i].hist(vect_moyenne[i],bins=15)
    plt.show()

compar_repart_moy(loi_x,[100,1000,10000,100000],200)





