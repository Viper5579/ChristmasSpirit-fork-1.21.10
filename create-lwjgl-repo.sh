#!/bin/bash

# Script to create fake LWJGL artifacts that redirect to the correct arm64 versions

REPO_DIR=".gradle-local-repo/org/lwjgl"

# List of LWJGL modules
MODULES=("lwjgl" "lwjgl-glfw" "lwjgl-opengl" "lwjgl-stb" "lwjgl-tinyfd" "lwjgl-jemalloc" "lwjgl-freetype")

# Versions to support (3.3.3 is what Forge 1.21.1 uses, 3.3.4 for newer)
VERSIONS=("3.3.3" "3.3.4")

for version in "${VERSIONS[@]}"; do
    for module in "${MODULES[@]}"; do
        # Create directory structure
        mkdir -p "${REPO_DIR}/${module}/${version}"

        # Create POM that redirects to arm64
        cat > "${REPO_DIR}/${module}/${version}/${module}-${version}-natives-macos-patch.pom" <<EOF
<?xml version="1.0" encoding="UTF-8"?>
<project>
    <modelVersion>4.0.0</modelVersion>
    <groupId>org.lwjgl</groupId>
    <artifactId>${module}</artifactId>
    <version>${version}</version>
    <classifier>natives-macos-patch</classifier>

    <dependencies>
        <dependency>
            <groupId>org.lwjgl</groupId>
            <artifactId>${module}</artifactId>
            <version>${version}</version>
            <classifier>natives-macos-arm64</classifier>
        </dependency>
    </dependencies>
</project>
EOF

        # Create empty JAR (since the dependency will pull the real one)
        touch "${REPO_DIR}/${module}/${version}/${module}-${version}-natives-macos-patch.jar"
    done
done

echo "Created local LWJGL repository at .gradle-local-repo for versions: ${VERSIONS[*]}"
