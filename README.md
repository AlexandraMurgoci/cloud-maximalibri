kubernetes in local s-a realizat cu minikube

read-aholic este aplicatia ce s-a migrat
read-aholic ui - aplicatia de front-end, s-au inlocuit url-urile cu cele expuse de kubernetes

back-endul este format din microserviciile din folderele maximalibri sau maxima-libri

persistenta pentru baza de date s-a facut doar pentru baza microserviciului user (nu s-a dorit pentru celelalte pentru testare)

autoscaling-ul s-a facut cu comanda:
kubectl autoscale deployment book-description --cpu-percent=50 --min=1 --max=10

folderul AWS contine sample-uri testate si deployate in AWS EKS si Fargate (unde s-a reusit si utilizarea ingress)
