for /f %%i in (jslist.txt) do type %%i >> eop.js
java -jar ../yuicompressor-2.4.2.jar --type js --charset utf-8  eop.js -o eop-min.js 