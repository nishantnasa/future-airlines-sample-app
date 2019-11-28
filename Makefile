base_tag := simulator
default_repo := dsc
docker_repo := $(if $(DOCKER_REPO),$(DOCKER_REPO),$(default_repo))
tag := $(if $(DOCKER_GIT_VERSION),$(docker_repo):$(base_tag)-$(subst /,-,$(DOCKER_GIT_VERSION)),$(docker_repo):$(base_tag))

build:
	docker build --rm $(if $(CACHE),,--no-cache) -t $(tag) . && echo $(tag) > var_container

run: ## Run
	docker run --rm -it $(tag)

publish: build
	docker tag $(tag) $(ECR_URI)/$(tag) && docker push $(ECR_URI)/$(tag)

create-network:
	docker network create dsc_local_infra || true

compose-%: create-network
	docker-compose up --build -d $*

build-local-base:
	docker build -t dsc:simulator-local-base -f localhost.baseimage .

build-local: build-local-base
	docker build -t dsc:simulator-local -f localhost.dockerfile .

compose-local-python: create-network compose-s3
	find ./fileContainer/* -type f ! -name '.gitignore' -exec rm -f {} + || true
	find ./fileContainer/* -type d ! -name 'Action_History' -exec rm -rf {} + || true
	docker-compose up -d simulator-python

compose-local-java: create-network compose-s3
	find ./fileContainer/* -type f ! -name '.gitignore' -exec rm -f {} + || true
	find ./fileContainer/* -type d ! -name 'Action_History' -exec rm -rf {} + || true
	docker-compose up -d simulator-java

compose-s3:
	mkdir -p ./docker/s3/data/dsc-dev-input
	mkdir -p ./docker/s3/data/dsc-dev-output
	mkdir -p ./docker/s3/data/dsc-sit-input
	mkdir -p ./docker/s3/data/dsc-sit-output
	mkdir -p ./docker/s3/data/dsc-stg-input
	mkdir -p ./docker/s3/data/dsc-stg-output
	docker-compose up --build -d s3
	chmod -R 755 ./docker/s3/config/certs

destroy: clean
	docker-compose down
	docker network rm dsc_local_infra || true

clean:
	rm -rf ./docker/s3/data/.minio.sys
	rm -rf ./docker/s3/data/dsc-dev-input/*
	rm -rf ./docker/s3/data/dsc-dev-output/*
	rm -rf ./docker/s3/data/dsc-sit-input/*
	rm -rf ./docker/s3/data/dsc-sit-output/*
	rm -rf ./docker/s3/data/dsc-stg-input/*
	rm -rf ./docker/s3/data/dsc-stg-output/*
	find ./fileContainer/* -type f ! -name '.gitignore' -exec rm -f {} + || true
	find ./fileContainer/* -type d ! -name 'Action_History' -exec rm -rf {} + || true
