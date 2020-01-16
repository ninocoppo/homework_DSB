minikube start --memory 3000
eval $(minikube docker-env)
minikube mount ./:/storage

