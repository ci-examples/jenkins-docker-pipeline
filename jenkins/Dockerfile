FROM jenkinsci/jenkins:2.27

USER root

RUN  curl -sL https://deb.nodesource.com/setup_7.x | bash - && apt-get install -y \
  docker.io \
  nodejs

USER jenkins
ENV JAVA_OPTS=-Djenkins.install.runSetupWizard=false

ADD JENKINS_HOME /usr/share/jenkins/ref

COPY plugins.txt /usr/share/jenkins/plugins.txt
RUN xargs -a /usr/share/jenkins/plugins.txt /usr/local/bin/install-plugins.sh

USER root
