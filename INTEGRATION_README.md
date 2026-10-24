# CoX Scouter External Integration

## Overview
This directory contains the **CoX Scouter External** plugin by Blackberry0Pie, integrated into the Swaid plugin ecosystem as a git submodule.

**Original Repository**: https://github.com/Blackberry0Pie/CoX-Scouter-External  
**License**: BSD-2-Clause (allows modification and redistribution)

## Integration Details

### What Was Modified
1. **Package Structure**: Changed from `bbp.chambers` to `com.example.coxscouter`
2. **Plugin Name**: Updated to "CoX Scouter (Swaid)" to distinguish from original
3. **Build System**: Integrated with Swaid's Gradle build system and JAR naming conventions
4. **Dependencies**: Added core module dependencies (A1Central, KotoriUtils, SwaidUtils)

### Build Integration
- **Build Task**: `./gradlew buildCoxScouter`
- **Output JAR**: `Swaid-CoxScouter.jar` → copied to `universal-jars/`
- **Combined Builds**: Included in raid-focused JAR combinations

## Updating from Upstream

### Getting Updates
To fetch updates from the original repository:

```bash
# Navigate to project root
cd "C:/Users/Swaid/Documents/RuneLite Projects/Swaid 2024"

# Update the submodule to latest upstream version
git submodule update --remote swaid/plugins/coxscouter

# This will pull the latest changes from Blackberry0Pie's repository
```

### Handling Update Conflicts
After updating from upstream, you may need to:

1. **Re-apply Package Changes**: The upstream updates will reset package names back to `bbp.chambers`
2. **Update Plugin Descriptor**: May need to re-update the plugin name and description  
3. **Resolve Merge Conflicts**: If your local changes conflict with upstream changes

### Quick Re-integration Script
If updates reset your customizations, re-run these package updates:

```bash
# Re-copy files to correct package structure
cp swaid/plugins/coxscouter/src/main/java/bbp/chambers/*.java swaid/plugins/coxscouter/src/main/java/com/example/coxscouter/

# Update package declarations (do this for each .java file):
# Change: package bbp.chambers;
# To:     package com.example.coxscouter;
```

## Privacy & Attribution

### Your Privacy
- ✅ **Anonymous Integration**: Original author cannot see you're using their plugin
- ✅ **Local Changes**: Your modifications stay private and local
- ✅ **No Push-back**: Your changes never get pushed to their repository
- ✅ **One-way Updates**: You pull their updates, they don't see your version

### Attribution Maintained
- Original BSD-2-Clause license preserved in all source files
- Copyright notices for Kamiel and Truth Forger (Blackberry0Pie) maintained
- Original repository URL documented for proper attribution

## Features Included

The integrated plugin provides:
- **Floor Layout Splitting**: Enhanced raid layout visualization
- **Item Overlay**: Recommended items display for different rooms
- **Room Highlighting**: Custom room highlighting with configurable colors  
- **Advanced Filtering**: Raid filtering based on rooms, layouts, rotations
- **Screenshot Integration**: Hotkey-based overlay screenshots
- **Tutorial Overlay**: Built-in help system

## Integration with Swaid Ecosystem

### Available Integrations
The plugin now has access to:
- **A1Central**: Shared PvM utilities and prayer management
- **KotoriUtils**: Common utility functions and danger detection
- **SwaidUtils**: Performance optimizations and shared components

### Future Enhancement Opportunities
- Replace duplicate prayer switching logic with A1Central's IncPrayerManager
- Integrate danger tile detection with shared utilities
- Leverage existing overlay patterns from other Swaid plugins
- Add integration with existing CoX Helper plugin for enhanced functionality

## Build Commands

```bash
# Build this plugin individually
./gradlew buildCoxScouter

# Build all raid plugins (includes this one)
./gradlew buildRaidsJar

# Build all plugins
./gradlew buildAllJar

# List all available plugins
./gradlew listPlugins
```

## Troubleshooting

### Build Issues
- Ensure the submodule is properly initialized: `git submodule update --init --recursive`
- Verify settings.gradle includes: `include 'swaid:plugins:coxscouter'`
- Check that package structure matches the expected `com.example.coxscouter` format

### Update Issues
- If package structure gets reset after updates, re-apply the package modifications
- Use `git status` in the submodule directory to see what changed after updates
- Consider creating a patch file for easy re-application of customizations

This integration provides the best of both worlds: access to upstream updates while maintaining your privacy and customizations.