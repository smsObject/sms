cd /d %~dp0
java -jar mybatis-generator-core-1.3.2.jar -configfile config.xml -overwrite
pause