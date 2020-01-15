minikube start
nohup minikube mount ./:/storage &&
eval $(minikube docker-env)
