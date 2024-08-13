README * Development Setup

	* [H1] Weblogic Server Setup
	* [H2] Database Setup
	* [H3] Development
	* [H4] Running Batch Job
	* [H5] Hibernate Schema Output command
	* [H6] Compile EJB (Weblogic)
	* [H7] Compile EJB (WebSphere)
	* [H8] Update cms.ear

[H1] Weblogic Server Setup

	1. Requirement

		* Weblogic 8.1 SP5 and above
		* JDK 1.4.2 (shipped with Weblogic)

	2. Create weblogic domain using GUI

		* domain name 'eoncms'
		* port number '7005'
		* User/Password 'weblogic/weblogic'
		* skip DataSource configuration

	* see \config\weblogic\config.local.xml for more information

[H2] Database Setup

	1. Requirement

		* DB2 v9 Enterprise Edition

	2. To setup CMS database please read %INTEGRO_HOME%\scripts\cms-fresh-db\db2\run.cmd for information

		* run 'db2cmd' to open db2 command line processor
		* change the DB Path accordingly

[H3] Development

	Requirement :
		* Weblogic (as above)
		* JDK (provided with Weblogic, or use IBM JDK 1.5)
		* Apache Ant (v1.7.0)
		* Database (as above)

	Pre-requisite :
		1. Copy 'dev.cmd' (from \bin) to the project folder, example: d:\dev\eon\cms\dev

		2. Amend dev.cmd (the one in project folder) to assign some variables
			a. INTEGRO_HOME (eg. d:\dev\eon\cms\dev, where you put your codes to)
			b. JAVA_HOME (eg. d:\dev\util\jdk1.4.2)
			c. ANT_HOME (eg. d:\dev\util\apache-ant-1.7.0)
			d. WAS_APP_HOME (if required to ejbc the EJB using WebSphere)
			e. BEA_HOME (c:\bea)
			f. WEBLOGIC_HOME (c:\bea\weblogic81)
			g. WLS_DOMAIN (eoncms)
			h. BUILD_FILE (build.xml)
			i. EAR_UPDATE_INFO_DIR (eg. %INTEGRO_HOME%\config\websphere\bank)

	Actual run :
		i. dev compile (only if there is .java file changes)

		ii. dev deploy (only if there is config files changes that required to be copied, see below)

			a. web.xml, weblogic.xml
				\public_html\WEB-INF\server\weblogic\ -> \public_html\WEB-INF\

			b. wls.local.cmd 
				\config\weblogic\ -> %BEA_HOME%\user_projects\domains\%WLS_DOMAIN%\startWls.cmd

			c. config.local.xml 
				\config\weblogic\ -> %BEA_HOME%\user_projects\domains\%WLS_DOMAIN%\config.xml

			d. AppContext_Master.xml 
				\config\weblogic\ -> \config\spring\

			e. ofa_env.properties, batch.properties, logging.xml
				\config\weblogic\ -> \config\properties\

		  Unless any files changes above, you doesn't need to run 'dev deploy'.
		  And also, if you need differents settings, change the folder after '->'				
				
		iii. dev wls (run weblogic server in new cmd windows)

			This will invoke 'startWls.cmd' in %BEA_HOME%\user_projects\domains\%WLS_DOMAIN%

	Junit Test Case :
		* *Test.java files to be placed inside \test folder
		* make sure all .java files in \src are compiled (dev compile)
		* dev compile-test
		* dev runtests
		* check \build\junit\ folder for Junit Report

	Reload class files :
		command: dev reload

		* So far, only support the changes inside a method. 
		  Adding of new class, new method, inner class will not get reflected by running this.
	
	Others :	
		* if intend to use own build.xml, create a buildLocal.xml and buildLocal.properties instead.
		  Change dev.cmd#BUILD_FILE variable will cater it.
	
		* extra CLASSPATH that need to be loaded to run server, also configure it in setlocalenv.cmd
		
		* new .properties file that need to be loaded into PropertyManager, configure it in web.xml

[H4] Running Batch Job

	dev batch [jobName] [parameters in key-value pair]

	* [jobName] is the bean name of the batch job, basically the implementations of 'com.integrosys.cms.batch.factory.BatchJob'
	
	* [parameters in key-value pair] is the parameters required by the batch job, it's in the key-value pair form. 

		eg. "country=MY", "countries=MY,HK,SG"

	* to put double quote is to escape the keyword '='

[H5] Hibernate Schema Output command

	Generating database schema based on a Hibernate Mapping file.
	* mostly used when developing hbm file first rather deal with DB directly.

	command as following:

		%JAVA_HOME%\bin\java -cp %CLASSPATH% -Dhibernate.dialect=org.hibernate.dialect.DB2Dialect org.hibernate.tool.hbm2ddl.SchemaExport --create --text --format --output=file.schema.ddl [hibernate mapping file absolute path]
		
[H6] Compile EJB (Weblogic)

	Generate and Compile EJB classes. This can be used to check the correctiveness of Deployment Descriptor

	%JAVA_HOME%\bin\java.exe -cp %WEBLOGIC_HOME%\server\lib\weblogic.jar;%CLASSPATH% weblogic.appc -keepgenerated -compiler %JAVA_HOME%\bin\javac.exe -output %INTEGRO_HOME%\build\dist\collateralAsset %INTEGRO_HOME%\config\ejb\weblogic\collateralAsset

[H7] Compile EJB (WebSphere)

	Generate and Compile EJB classes.

	1. dev ejb [ejb name]

	[ejb name] will be the folder name in \config\ejb\websphere

[H8] Update cms.ear

	To update cms.ear with the pre-configured properties files (more for Bank UAT/PROD environment)
	
	1. dev ear
	
	This will call \bin\update_ear.cmd command and pick up update.info in \config\websphere\bank or \config\websphere\bank\prod
	Set correctly for dev.cmd#EAR_UPDATE_INFO_DIR variable to let \bin\update_ear.cmd to pick up update.info