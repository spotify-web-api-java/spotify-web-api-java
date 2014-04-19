#!/bin/bash

SRC_DIR=./src/main/proto
DEST_DIR=./src/main/java
FILE=*.proto

protoc -I=$SRC_DIR --java_out=$DEST_DIR $SRC_DIR/$FILE
