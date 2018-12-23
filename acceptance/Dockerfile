FROM ubuntu:trusty
RUN apt-get update && \
    apt-get install -yq curl
COPY acceptance.sh .
CMD ["bash", "acceptance.sh"]
