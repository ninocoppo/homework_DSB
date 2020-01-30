minikube start --memory 4000
eval $(minikube docker-env)
minikube mount ./:/storage

