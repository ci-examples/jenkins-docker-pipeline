FROM java:8-jdk

RUN DEBIAN_FRONTEND=noninteractive curl -sL https://deb.nodesource.com/setup_7.x | bash - \
 && apt-get install -y nodejs \
 && npm install -g yarn

RUN apt-get install -y apt-transport-https ca-certificates \
 && apt-key adv --keyserver hkp://p80.pool.sks-keyservers.net:80 --recv-keys 58118E89F3A912897C070ADBF76221572C52609D \
 && echo 'deb https://apt.dockerproject.org/repo debian-jessie main' > /etc/apt/sources.list.d/docker.list \
 && apt-get update \
 && apt-get install -y docker-engine openssh-server \
 && sed -i 's|session    required     pam_loginuid.so|session    optional     pam_loginuid.so|g'  /etc/pam.d/sshd \
 && mkdir -p /var/run/sshd \
 && groupadd -g 1000 jenkins \
 && useradd -d /home/jenkins -s /bin/bash -m jenkins -u 1000 -g jenkins -G staff \
 && echo "jenkins:jenkins" | chpasswd \
 && curl -L "https://github.com/docker/compose/releases/download/1.8.1/docker-compose-$(uname -s)-$(uname -m)" > /usr/local/bin/docker-compose \
 && chmod +x /usr/local/bin/docker-compose \
 && rm -rf /var/lib/apt/lists/*


ENV HOME /home/jenkins

VOLUME /home/jenkins
WORKDIR /home/jenkins

EXPOSE 22

CMD [ "/usr/sbin/sshd", "-D" ]
