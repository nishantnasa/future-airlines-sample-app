FROM dsc:simulator-local-base

ENV SIMULATOR_HOME /opt/build

ADD --chown=jenkins:jenkins src/requirements.txt ${SIMULATOR_HOME}/src/requirements.txt
RUN sudo pip install --no-cache-dir -r ${SIMULATOR_HOME}/src/requirements.txt

COPY --chown=jenkins:jenkins config/ ${SIMULATOR_HOME}/
COPY --chown=jenkins:jenkins pom.xml ${SIMULATOR_HOME}/pom.xml
COPY --chown=jenkins:jenkins ConvertCsvToParquet.py ${SIMULATOR_HOME}/ConvertCsvToParquet.py
COPY --chown=jenkins:jenkins target ${SIMULATOR_HOME}/target
COPY --chown=jenkins:jenkins fileContainer ${SIMULATOR_HOME}/fileContainer
COPY --chown=jenkins:jenkins src ${SIMULATOR_HOME}/src

WORKDIR ${SIMULATOR_HOME}

CMD ["--help"]
