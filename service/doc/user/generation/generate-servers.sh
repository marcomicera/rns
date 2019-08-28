#!/bin/bash

# Server generator stubs to be used (all JAX-RS ones)
GENERATORS=(
    # jaxrs-cxf 
    # jaxrs-cxf-cdi 
    # jaxrs-cxf-extended 
    # jaxrs-jersey 
    # jaxrs-resteasy 
    # jaxrs-resteasy-eap 
    jaxrs-spec
)

export JAVA_POST_PROCESS_FILE="/usr/bin/clang-format -i"

# Swagger's YAML file
YAML_FILE=~/git/rns/service/doc/user/swagger/swagger.yaml

# Output directory for all generators
OUTPUT_DIR=~/dp2/servers/cli

# Packages
BASE_PKG=it.polito.dp2.RNS.sol3.service
API_PKG=$BASE_PKG.api
MODEL_PKG=$BASE_PKG.model

# For each generator
for generator in "${GENERATORS[@]}"
do
    :
    # Delete previous stub
    echo Deleting $OUTPUT_DIR/$generator...
    rm -rf $OUTPUT_DIR/$generator
    echo ...done.
    # Generate a server stub
    echo Creating $generator stub...
    java -jar openapi-generator-cli.jar generate \
    --input-spec $YAML_FILE \
    --invoker-package $BASE_PKG \
    --api-package $API_PKG \
    --model-package $MODEL_PKG \
    --package-name $API_PKG \
    --output $OUTPUT_DIR/$generator \
    --generator-name $generator \
    --enable-post-process-file
    echo ...done with the $generator stub.
done
