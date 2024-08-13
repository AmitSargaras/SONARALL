@echo off
SETLOCAL

cls

set INTEGRO_HOME=E:\Production_Checkout\HDFC_CLIMS_ORACLE_SFR_13052024
set JAVA_HOME=C:\bea10\jdk1.8.0_221
set ANT_HOME=C:\apache-ant-1.8.4
set BEA_HOME=c:\bea10
set WEBLOGIC_HOME=C:\bea10\wlserver
set WLS_DOMAIN=CLIMS
set BUILD_FILE=E:\Production_Checkout\HDFC_CLIMS_ORACLE_SFR_13052024\build.xml
SET WAS_APP_HOME="C:\Program Files\IBM\WebSphere\AppServer"
set EAR_UPDATE_INFO_DIR=E:\Production_Checkout\HDFC_CLIMS_ORACLE_SFR_13052024\earUpdate

if "%~1" == "wls" goto :wls
if "%~1" == "batch" goto :batch
if "%~1" == "ejb" goto :ejb
if "%~1" == "ear" goto :ear
if "%~1" == "update" goto :update
if "%~1" == "patch" goto :patch
if "%~1" == "buildnumber" goto :buildnumber
if "%~1" == "migrateejb" goto :migrateejb
if "%~1" == "migrateejball" goto :migrateejball
goto :antbuild

:wls
if exist %INTEGRO_HOME%\bin\setlocalenv.cmd (call %INTEGRO_HOME%\bin\setlocalenv.cmd)

echo ------------------------------------------------------------------------
echo Starting Weblogic Server...
echo.
echo WEBLOGIC_HOME: [%WEBLOGIC_HOME%]
echo JAVA_HOME: [%JAVA_HOME%]
echo Weblogic Domain: [%BEA_HOME%\user_projects\domains\%WLS_DOMAIN%\]
echo ------------------------------------------------------------------------

pushd %BEA_HOME%\user_projects\domains\%WLS_DOMAIN%\

if exist startWls.cmd (
	echo [info] Invoking 'startWls.cmd' in [%BEA_HOME%\user_projects\domains\%WLS_DOMAIN%\]
	call startWls.cmd
) else (
	echo [info] Invoking 'wls.cmd' in [%BEA_HOME%\user_projects\domains\%WLS_DOMAIN%\]
	call wls.cmd
)

popd
goto :end

:batch
if "%~2" == "" (
	echo [error] Batch job name not provided.
	echo [info] Example: dev batch reportBatchJob "country=MY" "centre=123"
	goto :end
)

echo ------------------------------------------------------------------------
echo Executing Batch Job [%2] ...
echo Parameters [%3 %4 %5 %6 %7 %8]
echo ------------------------------------------------------------------------
echo.
echo CLASSPATH used:
echo 	%INTEGRO_HOME%\3rdpartylib\integrobase.jar
echo 	%INTEGRO_HOME%\build\classes
echo 	%INTEGRO_HOME%\config\properties
echo ------------------------------------------------------------------------
echo.
echo [info] For parameters having equal '=' sign, please use double quote '"' to enclose the parameter. 
echo Example:
echo.
echo 	dev batch reportBatchJob "country=MY" "centre=123"
echo.
echo.

%JAVA_HOME%\bin\java -cp %INTEGRO_HOME%\3rdpartylib\integrobase.jar;%INTEGRO_HOME%\build\classes;%INTEGRO_HOME%\config\properties com.integrosys.cms.ui.eventmonitor.EventMonitorClient %2 %3 %4 %5 %6 %7 %8
goto :end

:ear
echo ------------------------------------------------------------------------
echo Starting update cms.ear
echo.
echo [info] cms.ear must be found in [%INTEGRO_HOME%\build\dist]
echo [info] the update.info meta info file have to be in 
echo [%EAR_UPDATE_INFO_DIR%]
echo.
echo ------------------------------------------------------------------------

pushd %INTEGRO_HOME%\build\dist
call %INTEGRO_HOME%\bin\update_ear.cmd
popd
goto :end

:patch
echo ------------------------------------------------------------------------
echo Grabbing releases files from [%CD%] 
echo to [%~f3] 
echo based on file [%~f2]
echo.
echo ------------------------------------------------------------------------

if not exist "%~2" (
	echo [error] The file consist of List of patches [%~f2] doesn't exist, please provide.
	goto :end
)

if not exist "%~3" (
	echo [error] Patch Release Folder [%~f3] doesn't exist, please provide.
	goto :end
)

call %INTEGRO_HOME%\bin\grab.cmd %2 release . %3
goto :end

:update
echo ------------------------------------------------------------------------
echo Grabbing Source files from [%CD%] 
echo to [%~f3] 
echo based on file [%~f2]
echo.
echo ------------------------------------------------------------------------

if not exist "%~2" (
	echo [error] The file consist of List of patches [%~f2] doesn't exist, please provide.
	goto :end
)

if not exist "%~3" (
	echo [error] Destination Source Folder [%~f3] doesn't exist, please provide.
	goto :end
)

call %INTEGRO_HOME%\bin\grab.cmd %2 source . %3
goto :end

:buildnumber
echo ------------------------------------------------------------------------
echo Updating build.number entry in 
echo 	'cms.ear!/properties.jar!/ofa_env.properties'
echo using build.number [%~2]
echo cms.ear provided is [%~f3]
echo.
echo ------------------------------------------------------------------------

if "%~2" == "" (
	echo [error] Please provide value for build.number properties
	goto :end
)

call %INTEGRO_HOME%\bin\update_buildnumber.cmd %2 %3
goto :end

:ejb
if "%~2" == "all" goto :ejball
if "%~2" == "" (
	echo [error] Module name missing, please provide.
	echo [info] It has to be one of the following: 
	for /f "usebackq" %%i in (`dir /b %INTEGRO_HOME%\config\ejb\websphere`) do echo %%i
	goto :end
)
goto :ejbmodule

:ejball
echo ------------------------------------------------------------------------
echo Starting EJB deployment for all modules
echo.
echo [info] EJB Modules are found in %INTEGRO_HOME%\config\ejb\websphere
echo.
echo ------------------------------------------------------------------------

start "WebSphere EJB Deploy" %INTEGRO_HOME%\bin\websphere\ws_ejbdeploy_all.bat
goto :end

:ejbmodule
echo ------------------------------------------------------------------------
echo Starting EJB Deployment for [%2]
echo.
echo JAVA_HOME to be used for the Deployment will be replaced by the 
echo WebSphere one, which is IBM JDK 5
echo ------------------------------------------------------------------------

start "WebSphere EJB Deploy for [%2]" %INTEGRO_HOME%\bin\websphere\ws_ejbdeploy.bat %2 %INTEGRO_HOME%\build\dist
goto :end

:migrateejb
echo ------------------------------------------------------------------------
echo Starting Migrate EJB for [%3], using Task [%2]
echo.
echo JAVA_HOME to be used for the migration will be replaced by the 
echo WebSphere one, which is IBM JDK 5
echo ------------------------------------------------------------------------

pushd %INTEGRO_HOME%\config\ejb\migrate
call migrate.cmd %2 %3
popd

goto :end

:migrateejball
echo ------------------------------------------------------------------------
echo Starting Migrate EJB for all module listed in ejb_list.txt
echo.
echo JAVA_HOME to be used for the migration will be replaced by the 
echo WebSphere one, which is IBM JDK 5
echo ------------------------------------------------------------------------
pushd %INTEGRO_HOME%\config\ejb\migrate
for /f %%i in ( %INTEGRO_HOME%\config\ejb\ejb_list.txt ) do call migrate.cmd wls2was %%i -noexit
popd

goto :end


:antbuild
echo ------------------------------------------------------------------------
echo Accessing Development Package  : %INTEGRO_HOME%
echo ------------------------------------------------------------------------
echo.
echo Compile Time [%DATE% - %TIME%]
echo ANT_HOME: %ANT_HOME%
echo JAVA_HOME: %JAVA_HOME%
echo ------------------------------------------------------------------------
echo.

"%JAVA_HOME%/bin/java" -cp %ANT_HOME%/lib/ant.jar;%ANT_HOME%/lib/ant-launcher.jar;%ANT_HOME%/lib/ant-trax.jar;%ANT_HOME%/lib/ant-junit.jar;%ANT_HOME%/lib/ant-nodeps.jar;"%JAVA_HOME%/lib/tools.jar" org.apache.tools.ant.Main -buildfile %BUILD_FILE% %*

:end
echo.
echo.
ENDLOCAL
:finish