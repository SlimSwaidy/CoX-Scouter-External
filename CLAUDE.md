# CoX Scouter (coxscouter)

Blackberry0Pie's CoX Scouter External, integrated: shows scouted rooms, hides once at Olm.

- GIT SUBMODULE (github.com/Blackberry0Pie/CoX-Scouter-External). Swaid's changes live on its local `swaid-integration`
  branch - commit inside `swaid/plugins/coxscouter`, then commit the new pointer in the main repo. Don't archive by hand.
- Package `com.a4o.coxscouter` (was `bbp.chambers`), main `CoxScouterExternalPlugin`, jar `Swaid-CoxScouter.jar`
- Events: RaidScouted / RaidReset (RuneLite raids plugin), varbits, overlay menu, game tick
- Core: a1central, kotoriutils, swaidutils declared in build.gradle, but the code imports none of them (2026-10-27)
- See `INTEGRATION_README.md` for what was changed from upstream
- Build `buildLaunchZillaCoxscouter` - Publish `publishCoxscouter`
