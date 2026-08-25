#!/usr/bin/env sh

#
# Copyright 2015 the original author or authors.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      https://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#

##############################################################################
##
##  Gradle start up script for UN*X
##
##############################################################################

# Attempt to set APP_HOME
# Resolve links: $0 may be a symlink
app_path=$0

# Need this for daisy-chained symlinks.
while
    APP_HOME=${app_path%"${app_path##*/}"}
    [ -h "$app_path" ]
do
    app_path=$(readlink "$app_path") || break
done

APP_HOME=$(cd "${APP_HOME:-.}" && pwd -P) || exit

APP_NAME="Gradle"
APP_BASE_NAME=${0##*/}
export APP_HOME
export APP_BASE_NAME

case "$OSTYPE" in
  *cygwin* | *msys* ) APP_HOME=$( cygpath --path --mixed "$APP_HOME" ) ;;
esac

DIR=$(cd "$(dirname "$0")" && pwd)
echo "$DIR" | grep -q ' ' && {
    echo "Error: Gradle path contains spaces. This is not supported."
    exit 1
}

# Add default JVM options here. You can also use JAVA_OPTS and GRADLE_OPTS to pass JVM options to this script.
DEFAULT_JVM_OPTS='-Xmx64m -Xms64m'

JAVA="$JAVA_HOME/bin/java"
if [ ! -x "$JAVA" ]; then
    JAVA=java
fi

if ! command -v "$JAVA" >/dev/null 2>&1; then
    echo "Error: JAVA_HOME is not set and java is not in PATH."
    exit 1
fi

cd "$APP_HOME" || exit 1

exec "$JAVA" $DEFAULT_JVM_OPTS $JAVA_OPTS $GRADLE_OPTS -classpath "$APP_HOME/gradle/wrapper/gradle-wrapper.jar" org.gradle.wrapper.GradleWrapperMain "$@"
