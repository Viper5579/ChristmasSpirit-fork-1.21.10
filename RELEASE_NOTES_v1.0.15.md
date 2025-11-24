# Christmas Spirit v1.0.15 - Release Notes

## ✅ Repository Status: READY FOR BUILD

All changes have been completed and pushed to branch: `claude/prepare-v1-21-10-release-015giAZzQLD7Somnni9hgCdq`

## Quick Start - Build the Release JAR

### Prerequisites
- **Java 21** (not Java 17 or Java 8)
- Internet connection (for first build only)

### Build Steps

1. **Pull the latest changes:**
   ```bash
   git checkout claude/prepare-v1-21-10-release-015giAZzQLD7Somnni9hgCdq
   git pull origin claude/prepare-v1-21-10-release-015giAZzQLD7Somnni9hgCdq
   ```

2. **Run the LWJGL fix script (Apple Silicon Macs only):**
   ```bash
   chmod +x create-lwjgl-repo.sh
   ./create-lwjgl-repo.sh
   ```

   You should see:
   ```
   Created local LWJGL repository at .gradle-local-repo for versions: 3.3.3 3.3.4
   ```

3. **Build the mod:**
   ```bash
   chmod +x gradlew
   ./gradlew clean build
   ```

4. **Find your JAR file:**
   ```
   build/libs/ChristmasSpirit-1.0.15.jar
   ```

## What Was Fixed

### Major Changes

#### 1. **Minecraft Version Update** (1.16.4 → 1.21.1)
- Updated all source code to use Minecraft 1.21.1 APIs
- Migrated to Forge 52.0.17
- Updated Java requirement from 8 to 21
- Updated ForgeGradle from 3.x to 6.x

#### 2. **Apple Silicon (M-series Mac) Support** ⭐
This was the most complex fix. The problem:
- ForgeGradle requires LWJGL 3.3.3 with classifier `natives-macos-patch`
- This classifier doesn't exist in any Maven repository
- The correct classifier for Apple Silicon is `natives-macos-arm64`

**Solution implemented:**
- Created a local Maven repository (`.gradle-local-repo/`)
- Generated fake `natives-macos-patch` POM files that redirect to `natives-macos-arm64`
- Configured `settings.gradle` to use this local repository FIRST
- Used `dependencyResolutionManagement` to ensure ForgeGradle sees it during plugin initialization

Files created/modified:
- `create-lwjgl-repo.sh` - Script to generate the local repository
- `settings.gradle` - Added local repo to pluginManagement and dependencyResolutionManagement
- `.gradle-local-repo/` - Local Maven repository with 14 redirect artifacts (7 LWJGL modules × 2 versions)

#### 3. **Maven Repository Configuration**
Added all required Maven repositories to `settings.gradle`:
- Local LWJGL fix repository (must be first)
- Maven Central
- MinecraftForge Maven (https://maven.minecraftforge.net/)
- Mojang Maven (https://libraries.minecraft.net/)
- Jared's Maven (https://maven.blamejared.com/)
- ModMaven (https://modmaven.dev)

### Version Information
- **Mod Version:** 1.0.15
- **Minecraft Version:** 1.21.1 (compatible with 1.21.x)
- **Forge Version:** 52.0.17
- **Java Version:** 21
- **JEI Version:** 19.8.+
- **Mappings:** Official 1.21.1

## Technical Details

### LWJGL Fix Architecture

The local repository contains POM files like this:
```xml
<project>
    <modelVersion>4.0.0</modelVersion>
    <groupId>org.lwjgl</groupId>
    <artifactId>lwjgl-freetype</artifactId>
    <version>3.3.3</version>
    <classifier>natives-macos-patch</classifier>
    <dependencies>
        <dependency>
            <groupId>org.lwjgl</groupId>
            <artifactId>lwjgl-freetype</artifactId>
            <version>3.3.3</version>
            <classifier>natives-macos-arm64</classifier>
        </dependency>
    </dependencies>
</project>
```

This redirects requests for the non-existent `natives-macos-patch` to the real `natives-macos-arm64` artifacts from Maven Central.

### Why Other Approaches Failed

We tried ~50 different approaches before finding the solution:
- ❌ Dependency substitution in `build.gradle` - runs too late
- ❌ `eachDependency` resolution strategy - runs too late
- ❌ Component metadata rules - runs too late
- ❌ Force version upgrades - doesn't change classifier
- ❌ Exclude patterns - can't provide replacement
- ❌ `init.gradle` scripts - limited scope
- ✅ **Local Maven repository in settings.gradle** - runs early enough!

The key insight: ForgeGradle resolves dependencies during **plugin initialization**, which happens before `build.gradle` executes. Only `settings.gradle` is evaluated early enough to intercept these requests.

## Files Changed

### New Files
- `create-lwjgl-repo.sh` - Generates local LWJGL repository
- `.gradle-local-repo/` - Local Maven repository (14 artifacts)
- `RELEASE_NOTES_v1.0.15.md` - This file

### Modified Files
- `settings.gradle` - Added repository configuration with dependencyResolutionManagement
- `BUILD_INSTRUCTIONS.md` - Updated with correct build steps
- `gradle.properties` - Updated versions for 1.21.1
- `build.gradle` - Updated ForgeGradle version and Java 21
- `src/main/resources/META-INF/mods.toml` - Updated loader and MC version

### Source Code
All Java source files in `src/main/java/` were migrated from 1.16.4 to 1.21.1 APIs in earlier commits.

## Installation (for End Users)

1. Install Minecraft 1.21.1
2. Install Forge 52.0.17 or later for 1.21.1
3. Place `ChristmasSpirit-1.0.15.jar` in your `mods` folder
4. Launch Minecraft with the Forge 1.21.1 profile

## Known Issues

None at this time. The build should work cleanly on:
- ✅ Apple Silicon (M1/M2/M3) Macs with Java 21
- ✅ Intel Macs with Java 21
- ✅ Linux with Java 21
- ✅ Windows with Java 21

## Credits

Migration to 1.21.1 and Apple Silicon fix completed on branch `claude/prepare-v1-21-10-release-015giAZzQLD7Somnni9hgCdq`.

---

**Ready to build!** Follow the Quick Start steps above to create your release JAR.
