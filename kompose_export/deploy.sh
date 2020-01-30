echo "Mounting volumes..."
kubectl apply -f application-pv.yaml 
kubectl apply -f application-claim0-persistentvolumeclaim.yaml
kubectl apply -f api-gateway-pv.yaml
kubectl apply -f api-gateway-claim0-persistentvolumeclaim.yaml
kubectl apply -f spout-pv.yaml
kubectl apply -f spout-pvc.yaml
kubectl apply -f minio-claim0-persistentvolumeclaim.yaml 
kubectl apply -f mysql-claim0-persistentvolumeclaim.yaml
kubectl apply -f prometheus-claim0-persistentvolumeclaim.yaml
kubectl apply -f consumer-pv.yaml
kubectl apply -f consumer-pvc.yaml
echo "Create prometheus config map"
kubectl apply -f prometheus-config.yaml 
echo "Exposing services"
kubectl apply -f minio-headless-service.yaml
kubectl apply -f minio-service.yaml 
kubectl apply -f mysql-service.yaml
kubectl apply -f prometheus-service.yaml
kubectl apply -f kafka-service.yaml
kubectl apply -f zookeeper-service.yaml
kubectl apply -f spout-service.yaml
kubectl apply -f application-service.yaml
kubectl apply -f api-gateway-service.yaml
kubectl apply -f consumer-service.yaml
echo "Deploying microservices"
kubectl apply -f minio-deployment.yaml
kubectl apply -f mysql-deployment.yaml 
kubectl apply -f zookeeper-deployment.yaml
kubectl apply -f kafka-deployment.yaml
kubectl apply -f api-gateway-deployment.yaml 
kubectl apply -f application-deployment.yaml 
kubectl apply -f prometheus-deployment.yaml
kubectl apply -f spout-deployment.yaml 
kubectl apply -f consumer-deployment.yaml






