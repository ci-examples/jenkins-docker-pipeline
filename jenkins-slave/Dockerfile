FROM java:8-jdk

ENV JENKINS_SWARM_VERSION 2.2
ENV JENKINS_HOME /home/jenkins

RUN DEBIAN_FRONTEND=noninteractive curl -sL https://deb.nodesource.com/setup_7.x | bash - \
 && apt-get install -y nodejs

RUN apt-get install -y apt-transport-https ca-certificates \
 && apt-key adv --keyserver hkp://p80.pool.sks-keyservers.net:80 --recv-keys 58118E89F3A912897C070ADBF76221572C52609D \
 && echo 'deb https://apt.dockerproject.org/repo debian-jessie main' > /etc/apt/sources.list.d/docker.list \
 && curl -sS https://dl.yarnpkg.com/debian/pubkey.gpg | apt-key add - \
 && echo "deb https://dl.yarnpkg.com/debian/ stable main" > /etc/apt/sources.list.d/yarn.list \
 && apt-get update \
 && apt-get install -y yarn docker-engine \
 && groupadd -g 1000 jenkins \
 && useradd -d $JENKINS_HOME -s /bin/bash -m jenkins -u 1000 -g jenkins -G staff \
 && echo "jenkins:jenkins" | chpasswd \
 && curl -L "https://github.com/docker/compose/releases/download/1.8.1/docker-compose-$(uname -s)-$(uname -m)" > /usr/local/bin/docker-compose \
 && chmod +x /usr/local/bin/docker-compose \
 && curl --create-dirs -sSLo /usr/share/jenkins/swarm-client-$JENKINS_SWARM_VERSION-jar-with-dependencies.jar https://repo.jenkins-ci.org/releases/org/jenkins-ci/plugins/swarm-client/$JENKINS_SWARM_VERSION/swarm-client-$JENKINS_SWARM_VERSION-jar-with-dependencies.jar \
 && chmod 755 /usr/share/jenkins \
 && rm -rf /var/lib/apt/lists/*

COPY jenkins-slave.sh /usr/local/bin/jenkins-slave.sh
USER jenkins

VOLUME $JENKINS_HOME
WORKDIR $JENKINS_HOME

ENTRYPOINT ["/usr/local/bin/jenkins-slave.sh"]
