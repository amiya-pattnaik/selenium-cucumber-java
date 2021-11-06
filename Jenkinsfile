#!groovy

// Anything we share across stages needs to get initialized here
def discoveredSpecs
def headCommitHash
def parentWorkspace
def dockerImage

// Other globals
def branchUrl = 'No branch. Failed before url assignment.'
def baseUrl = 'Tests died before url is assigned.'
def feVersion = 'undefined'
def apiVersion = 'undefined'
def restApiUrl
def callTrackingApiUrl
def backendUrl
def HCIUrl
def identityServerUrl = 'https://oidc-dev.immoscout24.ch' // XXX - hardcoded
def backofficeIdentityServerUrl = 'https://oidc-backoffice-dev.immoscout24.ch' // XXX - hardcoded
def testDataApiUrl = 'https://testdata-dev.immoscout24.ch/' // XXX - hardcoded

void abortOldBuild(previous) {
  if (!previous) {
    return
  }
  if (!previous.result) {
    // XXX - this needs to be whitelisted!
    def rawBuild = previous.getRawBuild()
    // XXX - this needs to be whitelisted!
    rawBuild.doStop()
  }
  abortOldBuild(previous.previousBuild)
}

pipeline {
  options {
    buildDiscarder logRotator(artifactDaysToKeepStr: '7', daysToKeepStr: '7')
    skipDefaultCheckout true
    lock 'dev-db'
    timeout(30)
  }

  // We want to use flyweight executors for everything possible
  agent none

  parameters {
    string(name: 'SLOT', defaultValue: 'dev-00', description: 'Api and web endpoint')
  }

  stages {
    // We're a flyweight executor here on purpose, so we can abort early
    stage('Abort previous') {
      steps {
        script {
          // Abort previous similar build
          if (env.BRANCH_NAME != 'master') {
            abortOldBuild(currentBuild.previousBuild)
          }
        }
      }
    } // stage: Abort previous

    stage('Assign variables') {
      steps {
        script {
          baseUrl = "https://web-${params.SLOT}.immoscout24.ch"
          restApiUrl = "https://rest-api-${params.SLOT}.immoscout24.ch/v4/de/"
          callTrackingApiUrl = "https://calltracking-api-${params.SLOT}.immoscout24.ch/v1/de/"
          backendUrl = "https://admin-${params.SLOT}.immoscout24.ch"
          HCIUrl = "https://hci-${params.SLOT}.immoscout24.ch"
          branchUrl = "<https://github.xmedia.ch/Immoscout24/react-frontend/tree/${env.BRANCH_NAME}|${env.BRANCH_NAME}>"
        }
      }
    } // stage: Assign variables

    stage('Clone') {
      // Real work done, we need a heavyweight executor
      agent any
      steps {
        script {
          // Store the parent workspace directory so we can run everything in
          // the same directory.
          parentWorkspace = "${env.WORKSPACE}"
        }
        checkout scm
        script {
          // XXX - The failure or success condition is convoluted:
          // 1) grep will exit 1 on finding lines and 2 on an error
          // 2) We need to catch the success exit and flip it with explanation
          // 3) We need to catch the failure exit and silently flip it
          //
          // XXX - We need to escape our in-line escape characters Groovy-side.
          sh '''
          grep -rE '(fit|it\\.(only|skip))\\(' cypress/ \
          && echo 'This push contains skipped tests!' \
          && exit 1 \
          || exit 0
          '''
          feVersion = sh(
            script: "curl -s 'http://isnode${params.SLOT[-2..-1]}-dev.is24docker.ch:8081/api/versions/current' | jq -r '.id'",
            returnStdout: true
            ).trim()
          apiVersion = sh(
            script: "curl -s 'https://rest-api-dev-${params.SLOT[-2..-1]}.immoscout24.ch/v4/en/admin/debug' | jq -r '.codeBranch'",
            returnStdout: true
            ).trim()
          currentBuild.displayName = "${params.SLOT}_${feVersion}_${apiVersion}"
          currentBuild.description = """
          Test run on slot ${params.SLOT}.
          FE version: ${feVersion}
          API version: ${apiVersion}
          Test branch: ${env.BRANCH_NAME}
          """
        }
        sh 'git rev-parse --verify HEAD > headCommitHash.txt'
        script {
          headCommitHash = readFile('headCommitHash.txt').trim()
        }
      }
    }  // stage: Clone

    stage('Build') {
      // Real work done, we need a heavyweight executor
      agent any
      steps {
        dir(parentWorkspace) {
          script {
            dockerImage = docker.build("react-tests:${headCommitHash}")
          }
        }
      }
    } // stage: Build

    stage('Prettier') {
      // Real work done, we need a heavyweight executor
      agent any
      steps {
        script {
          dockerImage.inside {
            dir(parentWorkspace) {
              sh 'su - cypress -c "npx prettier -c cypress"'
            }
          }
        }
      }
    } // stage: Prettier

    stage('ESLint') {
      // Real work done, we need a heavyweight executor
      agent any
      steps {
        script {
          dockerImage.inside {
            dir(parentWorkspace) {
              sh 'su - cypress -c "npx eslint cypress"'
            }
          }
        }
      }
    } // stage: ESLint

    stage('Discover') {
      // Real work done, we need a heavyweight executor
      agent any
      steps {
        dir(parentWorkspace) {
          sh "find cypress/integration -name '*.spec.js' > discoveredSpecs.txt"
          // We need to parse this manually as we skipped the default checkout
          script {
            discoveredSpecs = readFile('discoveredSpecs.txt').trim().split('\n')
          }
        }
      }
    } // stage: Discover

    stage('Prewarm') {
      // We're a flyweight executor here on purpose, as only child stages of
      // this stage should actually allocate resources.
      steps {
        script {
          def curl = 'curl -s -o /dev/null -D -'
          def prewarm = [:]

          prewarm['Rest API v4'] = {
            // Real work done, we need a heavyweight executor
            node {
              stage('Rest API v4') {
                // We need to ensure a local here as we collect a dict of closures
                def command = "${curl} ${restApiUrl}/meta/texts"
                sh "${command}"
              }
            }
          }

          prewarm['MVC'] = {
            // Real work done, we need a heavyweight executor
            node {
              stage('MVC') {
                // We need to ensure a local here as we collect a dict of closures
                def command = "${curl} ${baseUrl}/private/login"
                sh "${command}"
              }
            }
          }

          prewarm['Backend'] = {
            // Real work done, we need a heavyweight executor
            node {
              stage('Backend') {
                // We need to ensure a local here as we collect a dict of closures
                def command = "${curl} ${backendUrl}"
                sh "${command}"
              }
            }
          }

          prewarm['React Proxy'] = {
            // Real work done, we need a heavyweight executor
            node {
              stage('React Proxy') {
                // We need to ensure a local here as we collect a dict of closures
                def command = "${curl} ${baseUrl}"
                sh "${command}"
              }
            }
          }

          prewarm['HCI'] = {
            // Real work done, we need a heavyweight executor
            node {
              stage('HCI') {
                // We need to ensure a local here as we collect a dict of closures
                def command = "${curl} ${HCIUrl}"
                sh "${command}"
              }
            }
          }

          prewarm['Identity Server IS24'] = {
            // Real work done, we need a heavyweight executor
            node {
              stage('Identity Server IS24') {
                // We need to ensure a local here as we collect a dict of closures
                def command = "${curl} ${identityServerUrl}"
                sh "${command}"
              }
            }
          }

          prewarm['Identity Server backoffice'] = {
            // Real work done, we need a heavyweight executor
            node {
              stage('Identity Server backoffice') {
                // We need to ensure a local here as we collect a dict of closures
                def command = "${curl} ${backofficeIdentityServerUrl}"
                sh "${command}"
              }
            }
          }

          prewarm['Testdata API'] = {
            // Real work done, we need a heavyweight executor
            node {
              stage('Testdata API') {
                // We need to ensure a local here as we collect a dict of closures
                def command = "${curl} ${testDataApiUrl}"
                sh "${command}"
              }
            }
          }

          parallel prewarm
        }
      }
    } // stage: Prewarm

    stage('Dispatch') {
      // We're a flyweight executor here on purpose, as only child stages of
      // this stage should actually allocate resources.
      steps {
        script {
          def tests = [:]
          discoveredSpecs.eachWithIndex { spec, i ->
            // We need to ensure a local here as we collect a dict of closures
            def specName = "${spec.split('/').last()}"
            // Node -> Stage -> Docker inspect -> shell -> post shell
            tests["${specName}"] = {
            // Real work done, we need a heavyweight executor
            node {
                stage("${specName}") {
                  // XXX - Jenkins tries to help us by changing the entry point
                  // directory to the dynamically allocated workspace. We have
                  // to know where in the dockerfile we execute, or as which
                  // user, in this case as cypress in /home/cypress. We ensure
                  // this by an explicit `su -` login with a command execution.
                  //
                  // XXX - Electron trips up on itself if the IPC bursts up to a
                  // point where it easily exhausts the Docker default 64M SHM
                  // device. We thus allocate a 512M SHM device.
                  //
                  // XXX - We're implicitly relying on the implicit sibling
                  // container mounts to have all of the master agent side
                  // Jenkins home mounted so we can push the results files to
                  // where they need to go.
                  //
                  // XXX - This is also a touch heavy handed of an approach as
                  // we could not get stashing to work within containers in our
                  // runtime context. Something is off, as standard examples do
                  // not work for us. The most likely culprit is using a sibling
                  // Docker setup, which is not a supported use case.
                  //
                  // XXX - As a result we're rsyncing the results over, as the
                  // parent workspace is actually writable by us.
                  dockerImage.inside('--shm-size 2gb --ipc=host') {
                    try {
                      sh """
                      su - cypress -c "
                      export TZ=Europe/Zurich
                      export CYPRESS_baseUrl="${baseUrl}"
                      export CYPRESS_restApiUrl="${restApiUrl}"
                      export CYPRESS_callTrackingApiUrl="${callTrackingApiUrl}"
                      export CYPRESS_backendUrl="${backendUrl}"
                      export CYPRESS_HCIUrl="${HCIUrl}/public/hci"
                      export CYPRESS_testDataApiUrl="${testDataApiUrl}"
                      export MOCHA_FILE="cypress/results/${spec}.xml"
                      npx cypress run \
                      --spec "${spec}" \
                      --reporter cypress-multi-reporters \
                      --reporter-options configFile=.cypress-jenkins-reporters.json \
                      "
                      """
                    }
                    // XXX - This is what will get flagged as the failed step of
                    // the stage and the exit code as a reason of failure will
                    // go on the last executed step within this scope. This can
                    // be very confusing to parse at first, but we have no other
                    // way to ensure we actually grab the results.
                    finally {
                      // We will always have a snapshots directory to sync over
                      sh "rsync -av /home/cypress/cypress/snapshots/ ${parentWorkspace}/snapshots"
                      // This will and should fail with no results file
                      sh "rsync -av /home/cypress/cypress/results/ ${parentWorkspace}/results"
                    }
                  }
                }  // stage
            }
            }
          }
          // Map the list of closures to run in parallel
          parallel tests
        }
      }
    } // stage: Dispatch
  } // stages

  post {
    failure {
      slackSend(
        color: '#D13B23', // Red
        message: [
          "*FE*: ${feVersion}",
          "*API*: ${apiVersion}",
          "*Failed* on ${branchUrl}.",
          "${baseUrl}",
          // XXX - After the relevant PR is merged and in a release, we can use
          // RUN_TESTS_DISPLAY_URL here.
          // https://github.com/jenkinsci/blueocean-display-url-plugin/pull/24
          "<${env.RUN_DISPLAY_URL}|Results>",
          "Built in ${currentBuild.durationString.replace(' and counting', '')}.",
        ].join('\n')
      )
    } // failure

    unstable {
      slackSend(
        color: '#FFFF00', // Yellow
        message: [
          "*FE*: ${feVersion}",
          "*API*: ${apiVersion}",
          "*Failed* on ${branchUrl}.",
          "${baseUrl}",
          // XXX - After the relevant PR is merged and in a release, we can use
          // RUN_TESTS_DISPLAY_URL here.
          // https://github.com/jenkinsci/blueocean-display-url-plugin/pull/24
          "<${env.RUN_DISPLAY_URL}|Results>",
          "Built in ${currentBuild.durationString.replace(' and counting', '')}.",
        ].join('\n')
      )
    } // unstable

    success {
      slackSend(
        color: '#61AA3A', // Green
        message: [
          "*FE*: ${feVersion}",
          "*API*: ${apiVersion}",
          "*Passed* on ${branchUrl}.",
          "${baseUrl}",
          // XXX - After the relevant PR is merged and in a release, we
          // can use RUN_TESTS_DISPLAY_URL here.
          // https://github.com/jenkinsci/blueocean-display-url-plugin/pull/24
          "<${env.RUN_DISPLAY_URL}|Results>",
          "Built in ${currentBuild.durationString.replace(' and counting', '')}.",
        ].join('\n')
      )
    } // success

    always {
      // XXX - we need an explicitly labeled node as you cannot change the agent
      // in a post step and we default to agent none.
      //
      // Real work done here, so we need to ensure a heavyweight executor
      node('master') {
        // XXX - We need to know the git repo and docker image stuff here.
        dir(parentWorkspace) {
          junit 'results/**/*.js.xml'
          archiveArtifacts allowEmptyArchive: true, artifacts: 'snapshots/actual/*/*.png'
          archiveArtifacts allowEmptyArchive: true, artifacts: 'snapshots/snapshots/*/__diff_output__/*.png'
        }
      }
    } // always

    cleanup {
      // XXX - we need an explicitly labeled node as you cannot change the agent
      // in a post step and we default to agent none.
      //
      // Real work done here, so we need to ensure a heavyweight executor
      node('master') {
        dir(parentWorkspace) {
          // XXX - Recursively delete the contents of the parent workspace. Due
          // to us abusing the parent workspace, we cannot reliably do this in a
          // deferred manner. Using a `ws` scope instead of a `dir` scope could
          // end up assigning us a new parallel workspace.
          deleteDir()
        }
      }
    } // cleanup
  } // post
} // pipeline
