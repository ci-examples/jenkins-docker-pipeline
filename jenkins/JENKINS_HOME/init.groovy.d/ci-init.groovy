import jenkins.*
import hudson.*
import com.cloudbees.plugins.credentials.*
import com.cloudbees.plugins.credentials.common.*
import com.cloudbees.plugins.credentials.domains.*
import com.cloudbees.jenkins.plugins.sshcredentials.impl.*
import hudson.plugins.sshslaves.*;
import hudson.model.*
import jenkins.model.*
import hudson.security.*

def instance = Jenkins.getInstance()

//Security
def hudsonRealm = new HudsonPrivateSecurityRealm(false);
hudsonRealm.createAccount("jenkins","jenkins");
instance.setSecurityRealm(hudsonRealm);

def strategy = new FullControlOnceLoggedInAuthorizationStrategy();
instance.setAuthorizationStrategy(strategy);

instance.save();

//Master executors
instance.setNumExecutors(0)

instance.save();

//SonarQube
def sonarGlobal=Jenkins.instance.getDescriptor('hudson.plugins.sonar.SonarGlobalConfiguration')

def sonarInst = new hudson.plugins.sonar.SonarInstallation(
  "sonar",
  "http://sonarqube:9000",
  "5.3",
  null,
  null,
  null,
  null,
  null,
  null,
  null,
  null,
  null,
  null
);

sonarGlobal.setInstallations(sonarInst);
sonarGlobal.save();

def sonarRunner=Jenkins.instance.getDescriptor('hudson.plugins.sonar.SonarRunnerInstallation')

def installer = new hudson.plugins.sonar.SonarRunnerInstaller("2.8")
def prop = new hudson.tools.InstallSourceProperty([installer])
def runnerInst = new hudson.plugins.sonar.SonarRunnerInstallation("sonar", "", [prop])
sonarRunner.setInstallations(runnerInst)

sonarRunner.save()

//JOB
import javaposse.jobdsl.dsl.DslScriptLoader
import javaposse.jobdsl.plugin.JenkinsJobManagement

def job = """
pipelineJob('petclinic') {
     definition {
       cpsScm {
            scm {
                git {
                    remote {
                        url('https://github.com/ci-examples/spring-petclinic-reactjs.git')
                    }
                   	branches('**/master')
                }
            }
            scriptPath('Jenkinsfile')
        }
    }
}
""";

def jobManagement = new JenkinsJobManagement(System.out, [:], new File('.'));

new DslScriptLoader(jobManagement).runScript(job)
