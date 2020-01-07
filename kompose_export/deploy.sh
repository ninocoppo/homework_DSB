echo "Mounting volumes..."
kubectl apply -f application-claim0-persistentvolumeclaim.yaml api-gateway-claim0-persistentvolumeclaim.yaml minio-claim0-persistentvolumeclaim.yaml mysql-claim0-persistentvolumeclaim.yaml
echo "Create prometheus config map"
kubectl apply -f prometheus-config.yaml 
echo "Deoplying microservices"
kubectl apply -f minio-deployment.yaml mysql-deployment.yaml api-gateway-deployment.yaml application-deployment.yaml prometheus-deployment.yaml
echo "Exposing services"
kubectl expose mysql-deployment.yaml minio-deployment.yaml
