FROM java:8-jdk

RUN DEBIAN_FRONTEND=noninteractive curl -sL https://deb.nodesource.com/setup_7.x | bash - \
 && apt-get install -y openssh-server sudo docker.io nodejs\
 && sed -i 's|session    required     pam_loginuid.so|session    optional     pam_loginuid.so|g' \
  /etc/pam.d/sshd \
 && mkdir -p /var/run/sshd \
 && groupadd -g 1000 jenkins \
 && useradd -d /home/jenkins -s /bin/bash -m jenkins -u 1000 -g jenkins \
 && usermod -a -G staff jenkins \
 && echo "jenkins:jenkins" | chpasswd \
 && echo "jenkins ALL=NOPASSWD: ALL" >> /etc/sudoers \
 && rm -rf /var/lib/apt/lists/*

ENV HOME /home/jenkins

RUN curl --create-dirs -sSLo /usr/share/jenkins/slave.jar http://repo.jenkins-ci.org/public/org/jenkins-ci/main/remoting/2.52/remoting-2.52.jar \
  && chmod 755 /usr/share/jenkins \
  && chmod 644 /usr/share/jenkins/slave.jar

VOLUME /home/jenkins
WORKDIR /home/jenkins

EXPOSE 22

CMD [ "/usr/sbin/sshd", "-D" ]
