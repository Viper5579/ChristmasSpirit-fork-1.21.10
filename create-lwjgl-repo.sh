#!/bin/bash

# Script to download LWJGL ARM64 natives and save them as natives-macos-patch
# This fixes the ForgeGradle issue where it requests a classifier that doesn't exist

REPO_DIR=".gradle-local-repo/org/lwjgl"
MAVEN_CENTRAL="https://repo1.maven.org/maven2"

# List of LWJGL modules
MODULES=("lwjgl" "lwjgl-glfw" "lwjgl-opengl" "lwjgl-stb" "lwjgl-tinyfd" "lwjgl-jemalloc" "lwjgl-freetype")

# Versions to support (3.3.3 is what Forge 1.21.1 uses, 3.3.4 for newer)
VERSIONS=("3.3.3" "3.3.4")

echo "Downloading LWJGL ARM64 natives and creating patch artifacts..."

for version in "${VERSIONS[@]}"; do
    for module in "${MODULES[@]}"; do
        # Create directory structure
        mkdir -p "${REPO_DIR}/${module}/${version}"

        # URL for the ARM64 native JAR
        ARM64_URL="${MAVEN_CENTRAL}/org/lwjgl/${module}/${version}/${module}-${version}-natives-macos-arm64.jar"
        ARM64_POM_URL="${MAVEN_CENTRAL}/org/lwjgl/${module}/${version}/${module}-${version}.pom"

        # Download the ARM64 JAR and save it as natives-macos-patch
        PATCH_JAR="${REPO_DIR}/${module}/${version}/${module}-${version}-natives-macos-patch.jar"
        PATCH_POM="${REPO_DIR}/${module}/${version}/${module}-${version}-natives-macos-patch.pom"

        echo "  Downloading ${module}-${version}-natives-macos-arm64.jar..."
        if curl -f -s -L -o "${PATCH_JAR}" "${ARM64_URL}"; then
            echo "    ✓ Downloaded and saved as natives-macos-patch"
        else
            echo "    ✗ Failed to download, creating empty JAR as fallback"
            touch "${PATCH_JAR}"
        fi

        # Download and modify the POM
        if curl -f -s -L -o "/tmp/${module}-${version}.pom" "${ARM64_POM_URL}"; then
            cp "/tmp/${module}-${version}.pom" "${PATCH_POM}"
        else
            # Create a minimal POM if download fails
            cat > "${PATCH_POM}" <<EOF
<?xml version="1.0" encoding="UTF-8"?>
<project>
    <modelVersion>4.0.0</modelVersion>
    <groupId>org.lwjgl</groupId>
    <artifactId>${module}</artifactId>
    <version>${version}</version>
</project>
EOF
        fi
    done
done

echo ""
echo "✓ Created local LWJGL repository at .gradle-local-repo"
echo "  Versions: ${VERSIONS[*]}"
echo "  Modules: ${#MODULES[@]} modules"
echo ""
echo "You can now run: ./gradlew clean build"
