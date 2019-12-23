sudo docker run -it -p 9000:9000 --name minio1 \
  -e "MINIO_ACCESS_KEY=mauro" \
  -e "MINIO_SECRET_KEY=maurolabruna" \
  -v storage:/data \
  minio/minio server /data 
