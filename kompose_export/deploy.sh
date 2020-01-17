echo "Mounting volumes..."
kubectl apply -f application-claim0-persistentvolumeclaim.yaml
kubectl apply -f api-gateway-claim0-persistentvolumeclaim.yaml 
kubectl apply -f minio-claim0-persistentvolumeclaim.yaml 
kubectl apply -f mysql-claim0-persistentvolumeclaim.yaml
echo "Create prometheus config map"
kubectl apply -f prometheus-config.yaml 
echo "Deoplying microservices"
kubectl apply -f minio-deployment.yaml
kubectl apply -f mysql-deployment.yaml 
kubectl apply -f api-gateway-deployment.yaml 
kubectl apply -f application-deployment.yaml 
kubectl apply -f prometheus-deployment.yaml
echo "Exposing services"
kubectl expose mysql-deployment.yaml minio-deployment.yaml
