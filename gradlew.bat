#!/usr/bin/env pwsh

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
##  Gradle start up script for Windows
##
##############################################################################

# Attempt to set APP_HOME
# Resolve links: $0 may be a symlink
$script:APP_HOME = $PSScriptRoot

# Need this for daisy-chained symlinks.
while ((Get-Item $script:APP_HOME -ErrorAction SilentlyContinue).PSIsContainer) {
    $script:APP_HOME = (Get-ItemProperty $script:APP_HOME -Name PSPath).PSPath
    if ((Get-Item $script:APP_HOME).LinkType -ne "SymbolicLink") { break }
}

$APP_BASE_NAME = Split-Path $script:APP_HOME -Leaf
export APP_HOME
export APP_BASE_NAME

if ($PSVersionTable.PSVersion -lt "6.0" -or $IsWindows) {
    # PowerShell is in compat mode
    $isWindows = $true
}

if ($isWindows) {
    # Invoke gradle.bat from the root directory
    & (Join-Path $script:APP_HOME "gradle" "wrapper" "gradle-wrapper.jar") @args
} else {
    exec gradle $@
}
exit $?
