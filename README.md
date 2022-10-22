Test NG configuration for MobileRunner <br>
Add new TestNG config and use this for VM options <br><br>
<br>
-Dtest=MobileRunner
-Dapp=rjhauler
-DmobileDevice=emulator
-DmobileEnv=qa
-Dcucumber.options="src/test/resources/Mobile/features/relese.feature --tags @smoke"
-Xmx1024m
-DtestType=parallel