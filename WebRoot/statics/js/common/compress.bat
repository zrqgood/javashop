for /f %%i in (jslist.txt) do type %%i >> common.js
java -jar ../yuicompressor-2.4.2.jar --type js --charset utf-8  common.js -o common-min.js 